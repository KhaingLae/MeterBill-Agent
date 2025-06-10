package agent;
import jade.core.Agent;
import jade.core.AID;
import jade.core.Location;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.BasicOntology;
import jade.content.abs.*;
import jade.proto.SubscriptionResponder;
import jade.proto.SubscriptionResponder.SubscriptionManager;
import jade.proto.SubscriptionResponder.Subscription;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.introspection.IntrospectionOntology;
import jade.domain.introspection.Event;
import jade.domain.introspection.DeadAgent;
import jade.domain.introspection.AMSSubscriber;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import ontology.*;
public class SubscriberAgent extends Agent implements SubscriptionManager {
	private Map<AID, Subscription> participants = new HashMap<AID, Subscription>();
	private Codec codec = new SLCodec();
	private Ontology onto = ChatOntology.getInstance();
	private AMSSubscriber myAMSSubscriber;
	private Location destination;
	protected void setup() {
		System.out.println("subscriber agent enter...");
		 ((tweb.JadeBridgeSubscriber)getArguments()[0]).su = this;
		destination = here();		
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(onto);
		MessageTemplate sTemplate = MessageTemplate.and(MessageTemplate
				.MatchPerformative(ACLMessage.SUBSCRIBE), MessageTemplate.and(
				MessageTemplate.MatchLanguage(codec.getName()),
				MessageTemplate.MatchOntology(onto.getName())));
		addBehaviour(new SubscriptionResponder(this, sTemplate, this));
		myAMSSubscriber = new AMSSubscriber() {
			protected void installHandlers(Map handlersTable) {
				handlersTable.put(IntrospectionOntology.DEADAGENT,
						new EventHandler() {
							public void handle(Event ev) {
								DeadAgent da = (DeadAgent) ev;
								AID id = da.getAgent();
								// If the agent was attending the chat -->
								// other participants that it has just left.
								if (participants.containsKey(id)) {
									try {
										deregister((Subscription) participants
												.get(id));
									} catch (Exception e) {
										// Should never happen
										e.printStackTrace();
									}
								}
							}
						});
			}
		};
		addBehaviour(myAMSSubscriber);
	}	
	protected void takeDown() {
		send(myAMSSubscriber.getCancel());
		}
	public boolean register(Subscription s) throws RefuseException,
			NotUnderstoodException {
		try {
			AID newId = s.getMessage().getSender();
			System.out.println("new aid....." + newId);
			System.out.println(s.getMessage());
			if (!participants.isEmpty()) {
				ACLMessage notif1 = s.getMessage().createReply();
				notif1.setPerformative(ACLMessage.INFORM);
				ACLMessage notif2 = (ACLMessage) notif1.clone();
				notif2.clearAllReceiver();
				Joined joined = new Joined();
				List<AID> who = new ArrayList<AID>(1);
				who.add(newId);
				joined.setWho(who);
				getContentManager().fillContent(notif2, joined);
				who.clear();
				Iterator<AID> it = participants.keySet().iterator();
				while (it.hasNext()) {
					AID oldId = it.next();
					Subscription oldS = (Subscription) participants.get(oldId);
					oldS.notify(notif2);
					who.add(oldId);
				}
				getContentManager().fillContent(notif1, joined);
				s.notify(notif1);
			}
			participants.put(newId, s);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RefuseException("Subscription error");
		}
	}

	public boolean deregister(Subscription s) throws FailureException {
		AID oldId = s.getMessage().getSender();
		// Remove the subscription
		if (participants.remove(oldId) != null) {
			if (!participants.isEmpty()) {
				try {
					ACLMessage notif = s.getMessage().createReply();
					notif.setPerformative(ACLMessage.INFORM);
					notif.clearAllReceiver();
					AbsPredicate p = new AbsPredicate(ChatOntology.LEFT);
					AbsAggregate agg = new AbsAggregate(BasicOntology.SEQUENCE);
					agg.add((AbsTerm) BasicOntology.getInstance().fromObject(
							oldId));
					p.set(ChatOntology.LEFT_WHO, agg);
					getContentManager().fillContent(notif, p);

					Iterator it = participants.values().iterator();
					while (it.hasNext()) {
						Subscription s1 = (Subscription) it.next();
						s1.notify(notif);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}