/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
 *****************************************************************/

package agent;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;



import action.globaldata;
import jade.content.ContentManager;
import jade.content.abs.AbsAggregate;
import jade.content.abs.AbsConcept;
import jade.content.abs.AbsPredicate;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.mobility.MobilityOntology;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.Logger;
import jade.util.leap.Iterator;
import jade.util.leap.Set;
import jade.util.leap.SortedSetImpl;


//#MIDP_EXCLUDE_END
import ontology.ChatOntology;
import service.ChangeMonth;
import service.PayBillService;

/**
 * This agent implements the logic of the chat client running on the user
 * terminal. User interactions are handled by the ChatGui in a
 * terminal-dependent way. The ChatClientAgent performs 3 types of behaviours: -
 * ParticipantsManager. A CyclicBehaviour that keeps the list of participants up
 * to date on the basis of the information received from the ChatManagerAgent.
 * This behaviour is also in charge of subscribing as a participant to the
 * ChatManagerAgent. - ChatListener. A CyclicBehaviour that handles messages
 * from other chat participants. - ChatSpeaker. A OneShotBehaviour that sends a
 * message conveying a sentence written by the user to other chat participants.
 * 
 * @author Giovanni Caire - TILAB
 */
public class ClientAgent extends Agent {
	private static final long serialVersionUID = 1594371294421614291L;

	private Logger logger = Logger.getMyLogger(this.getClass().getName());

	private static final String CHAT_ID = "__chat__";
	
	
	
	private Set participants = new SortedSetImpl();
	private Codec codec = new SLCodec();
	private Ontology onto = ChatOntology.getInstance();
	private ACLMessage spokenMsg;
	jade.core.Runtime runtime = jade.core.Runtime.instance();
	
	protected void setup() {
		// Register language and ontology
		ContentManager cm = getContentManager();
		cm.registerLanguage(codec);
		cm.registerOntology(onto);
		//getContentManager().registerOntology(MobilityOntology.getInstance());		//cm.setValidationMode(false);
		// Initialize the message used to convey spoken sentences
		spokenMsg = new ACLMessage(ACLMessage.INFORM);
		spokenMsg.setConversationId(CHAT_ID);
		Integer bill=(Integer)getArguments()[0];
		String year=(String)getArguments()[1];
		String month=(String)getArguments()[2];
		String lname=(String)getArguments()[3];
		HttpSession session=(HttpSession)getArguments()[4];
		Integer percent=(Integer)getArguments()[5];
		
		int tid=PayBillService.getTownid(lname);
		String town=PayBillService.getTownname(tid);
		String home=PayBillService.getHomename(lname);
		ChangeMonth ccm = new ChangeMonth();
		int smonth = ccm.StringtoInt2(month);
		Long bf=System.currentTimeMillis();
		globaldata.acash=PayBillService.payBill(tid, Integer.parseInt(year), smonth, lname,bill,percent);
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		if(globaldata.acash==false){
			session.setAttribute("noenough", 1);
			session.removeAttribute("enough");
			handleConfirm("fail",timeStamp);
		}
		else {
			handleSpoken(bill+" receives from "+home +" in "+town+" for "+month+" "+year);
			System.out.println("timestamp  "+timeStamp);
			handleConfirm("success",timeStamp);
			session.setAttribute("enough", 1);
			session.removeAttribute("noenough");
		}
		Long af=System.currentTimeMillis();
		Long df=af-bf;
		System.out.println("Pay Bill Process Completed !");
		System.out.println("Total Time : "+df+"ms");
		System.out.println("Confirm Process Completed !");
		System.out.println("Total Time : "+df+"ms");
		//addBehaviour(new ChatListener(this));
		session.setAttribute("myca", this);
		addBehaviour(new ParticipantsManager(this));			
	}

	class ParticipantsManager extends CyclicBehaviour {
		private static final long serialVersionUID = -4845730529175649756L;
		private MessageTemplate template;
		  

		ParticipantsManager(Agent a) {
			super(a);
		}

		public void onStart() {
			
			// Subscribe as a chat participant to the ChatManager agent
			ACLMessage subscription = new ACLMessage(ACLMessage.SUBSCRIBE);
			subscription.setLanguage(codec.getName());
			subscription.setOntology(onto.getName());
			String convId = "C-" + myAgent.getLocalName();
			subscription.setConversationId(convId);
			subscription
					.addReceiver(new AID("SubscriberAgent", AID.ISLOCALNAME));
			myAgent.send(subscription);
			// Initialize the template used to receive notifications
			// from the ChatManagerAgent
			template = MessageTemplate.MatchConversationId(convId);
		}

		public void action() {
			// Receives information about people joining and leaving
			// the chat from the ChatManager agent
			ACLMessage msg = myAgent.receive(template);
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					try {
						AbsPredicate p = (AbsPredicate) myAgent
							.getContentManager().extractAbsContent(msg);
						if (p.getTypeName().equals(ChatOntology.JOINED)) {
							// Get new participants, add them to the list of
							// participants and notify the gui
							AbsAggregate agg = (AbsAggregate) p
									.getAbsTerm(ChatOntology.JOINED_WHO);
							if (agg != null) {
								Iterator it = agg.iterator();
								while (it.hasNext()) {
									AbsConcept c = (AbsConcept) it.next();
									participants.add(BasicOntology
											.getInstance().toObject(c));
								}
							}
							
						}
						if (p.getTypeName().equals(ChatOntology.LEFT)) {
							// Get old participants, remove them from the list
							// of participants and notify the gui
							AbsAggregate agg = (AbsAggregate) p
									.getAbsTerm(ChatOntology.JOINED_WHO);
							if (agg != null) {
								Iterator it = agg.iterator();
								while (it.hasNext()) {
									AbsConcept c = (AbsConcept) it.next();
									participants.remove(BasicOntology
											.getInstance().toObject(c));
								}
							}
							
						}
					} catch (Exception e) {
						Logger.println(e.toString());
						e.printStackTrace();
					}
				} else {
					handleUnexpected(msg);
				}
			} else {
				block();
			}
		}
	} // END of inner class ParticipantsManager


	/**
	 * Inner class ChatSpeaker. INFORMs other participants about a spoken
	 * sentence
	 */
	private class ChatSpeaker extends OneShotBehaviour {
		private static final long serialVersionUID = -1426033904935339194L;
		private String sentence;

		private ChatSpeaker(Agent a, String s) {
			super(a);
			sentence = s;
		}

		public void action() {
			spokenMsg.clearAllReceiver();
			spokenMsg.addReceiver(new AID("BankAgent",AID.ISLOCALNAME));
			spokenMsg.setContent(sentence);
			System.out.println("spoken..."+spokenMsg.getContent());
		//	notifySpoken(myAgent.getLocalName(), sentence);
			send(spokenMsg);
		}
	} // END of inner class ChatSpeaker

	/**
	 * Inner class ChatSpeaker. INFORMs other participants about a spoken
	 * sentence
	 */
	private class ChatSpeaker2 extends OneShotBehaviour {
		private static final long serialVersionUID = -1426033904935339194L;
		private String sentence;
		private String time;
		private ChatSpeaker2(Agent a, String s,String t) {
			super(a);
			sentence = s;
			time=t;
		}

		public void action() {
			spokenMsg.clearAllReceiver();
			spokenMsg.addReceiver(new AID("LoggerAgent",AID.ISLOCALNAME));
			spokenMsg.setContent(sentence+" : "+time+"\n");
			send(spokenMsg);
		}
	} // END of inner class ChatSpeaker

	// ///////////////////////////////////////
	// Methods called by the interface
	// ///////////////////////////////////////
	public void handleConfirm(String s,String t) {
		// Add a ChatSpeaker behaviour that INFORMs all participants about
		// the spoken sentence
		addBehaviour(new ChatSpeaker2(this, s,t));
	}
	
	public void handleSpoken(String s) {
		// Add a ChatSpeaker behaviour that INFORMs all participants about
		// the spoken sentence
		addBehaviour(new ChatSpeaker(this, s));
	}
	
	

	// ///////////////////////////////////////
	// Private utility method
	// ///////////////////////////////////////
	private void handleUnexpected(ACLMessage msg) {
		if (logger.isLoggable(Logger.WARNING)) {
			logger.log(Logger.WARNING, "Unexpected message received from "
					+ msg.getSender().getName());
			logger.log(Logger.WARNING, "Content is: " + msg.getContent());
		}
	}

}
