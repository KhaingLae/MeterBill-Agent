package main;

import gui.SendGui;

import java.util.*;
import java.io.*;

import ontology.ChatOntology;

import jade.lang.acl.*;
import jade.proto.SubscriptionResponder.Subscription;
import jade.util.Logger;
import jade.util.leap.Iterator;
import jade.util.leap.Set;
import jade.util.leap.SortedSetImpl;
import jade.wrapper.StaleProxyException;
import jade.content.*;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.*;
import jade.content.abs.AbsAggregate;
import jade.content.abs.AbsConcept;
import jade.content.abs.AbsPredicate;
import jade.content.lang.*;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.*;
import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.domain.mobility.*;
import jade.domain.JADEAgentManagement.*;
import jade.gui.*;

public class GUIAgent extends GuiAgent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------
	private Logger logger = Logger.getMyLogger(this.getClass().getName());
	private static final String CHAT_ID = "__chat__";
	private static final String CHAT_MANAGER_NAME = "manager";

	private Set participants = new SortedSetImpl();
	private Codec codec = new SLCodec();
	private Ontology onto = ChatOntology.getInstance();
	private ACLMessage spokenMsg;

	private jade.wrapper.AgentContainer home;
	private jade.wrapper.AgentContainer[] container = null;
	private Map locations = new HashMap();
	private Vector agents = new Vector();
	private int agentCnt = 0;
	private int command;
	transient protected SendGui myGui;
	private Map<AID, Subscription> current = new HashMap<AID, Subscription>();
	private AID controller;
	private Location destination;

	public static final int QUIT = 0;
	public static final int NEW_AGENT = 1;
	public static final int MOVE_AGENT = 2;
	public static final int CLONE_AGENT = 3;
	public static final int KILL_AGENT = 4;

	// Get a JADE Runtime instance
	jade.core.Runtime runtime = jade.core.Runtime.instance();

	protected void setup() {

		// ------------------------
		System.out.println("enter...");
		destination = here();

		home = runtime.createAgentContainer(new ProfileImpl());

		// Register language and ontology
		getContentManager().registerLanguage(new SLCodec());
		//getContentManager().registerOntology(MobilityOntology.getInstance());
		getContentManager().registerOntology(ChatOntology.getInstance());

		// Add initial behaviours
		addBehaviour(new ParticipantsManager(this));
		addBehaviour(new ChatListener(this));

		// Initialize the message used to convey spoken sentences
		spokenMsg = new ACLMessage(ACLMessage.INFORM);
		spokenMsg.setConversationId(CHAT_ID);
		// Create and show the gui
		myGui = new SendGui(this, locations.keySet());
		myGui.setVisible(true);

	}

	protected void onGuiEvent(GuiEvent ev) {
		// ----------------------------------------

		command = ev.getType();
		String to = (String) ev.getParameter(0);
		System.out.println("command........." + command);

		String notiAgent = "MPSC-Agent";
		AID aid = new AID(notiAgent, AID.ISLOCALNAME);

		if (command == MOVE_AGENT) {
			String homeno = (String) ev.getParameter(0);
			System.out.println("Home no.." + homeno);
			jade.wrapper.AgentController a = null;
			// try {
			Object[] args = new Object[2];
			args[0] = getAID();

			System.out.println("Class.." + SendNotiAgent.class.getName()
					+ "args0..." + args[0]);

			try {
				a = home.createNewAgent(notiAgent, SendNotiAgent.class.getName(),
						args);
				a.start();
			} catch (StaleProxyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("destName..." + to);
			AID[] pids = getParticipantAIDs();
			for (int i = 0; i < pids.length; i++)
				System.out.println("pids..." + pids[i].getName());
			WhereIsAgentAction wia = new WhereIsAgentAction();
			wia.setAgentIdentifier(pids[0]);
			sendRequest(new Action(getAMS(), wia));

			// Receive response from AMS
			MessageTemplate mt = MessageTemplate.and(
					MessageTemplate.MatchSender(getAMS()),
					MessageTemplate.MatchPerformative(ACLMessage.INFORM));
			ACLMessage resp = blockingReceive(mt);
			ContentElement ce;
			try {
				ce = getContentManager().extractContent(resp);
				Result result = (Result) ce;
				Location dest = (Location) result.getValue();
				System.out.println("dest..." + dest.getAddress() + ","
						+ dest.getID() + "," + dest.getName());
				MobileAgentDescription mad = new MobileAgentDescription();
				mad.setName(aid);
				mad.setDestination(dest);
				
				MoveAction ma = new MoveAction();
				ma.setMobileAgentDescription(mad);
				sendRequest(new Action(aid, ma));
			} catch (UngroundedException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CodecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OntologyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (command == CLONE_AGENT) {

			String destName = (String) ev.getParameter(1);
			Location dest = (Location) locations.get(destName);
			MobileAgentDescription mad = new MobileAgentDescription();
			mad.setName(aid);
			mad.setDestination(dest);
			String newName = "Clone-" + notiAgent;
			CloneAction ca = new CloneAction();
			ca.setNewName(newName);
			ca.setMobileAgentDescription(mad);
			sendRequest(new Action(aid, ca));
			agents.add(newName);
			myGui.updateList(agents);
		} else if (command == KILL_AGENT) {

			KillAgent ka = new KillAgent();
			ka.setAgent(aid);
			sendRequest(new Action(aid, ka));
			agents.remove(notiAgent);
			myGui.updateList(agents);
		}
	}

	void sendRequest(Action action) {
		// ---------------------------------

		ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
		request.setLanguage(new SLCodec().getName());
		request.setOntology(MobilityOntology.getInstance().getName());
		try {
			getContentManager().fillContent(request, action);
			System.out.println("actor..."+action.getActor());
			request.addReceiver(action.getActor());
			send(request);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void takeDown() {
		if (myGui != null) {
			myGui.dispose();
		}
	}

	private void notifyParticipantsChanged() {
		myGui.notifyParticipantsChanged(getParticipantNames());
	}

	private void notifySpoken(String speaker, String sentence) {
		myGui.notifySpoken(speaker, sentence);
	}

	/**
	 * Inner class ParticipantsManager. This behaviour registers as a chat
	 * participant and keeps the list of participants up to date by managing the
	 * information received from the ChatManager agent.
	 */
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
					.addReceiver(new AID(CHAT_MANAGER_NAME, AID.ISLOCALNAME));
			myAgent.send(subscription);
			// Initialize the template used to receive notifications
			// from the ChatManagerAgent
			template = MessageTemplate.MatchConversationId(convId);
		}

		public void action() {
			// Receives information about people joining and leaving
			// the chat from the ChatManager agent
			System.out.println("msg receive from manager agent is.......");
			ACLMessage msg = myAgent.receive(template);
			System.out.println(msg);
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
							notifyParticipantsChanged();
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
							notifyParticipantsChanged();
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
	 * Inner class ChatListener. This behaviour registers as a chat participant
	 * and keeps the list of participants up to date by managing the information
	 * received from the ChatManager agent.
	 */
	class ChatListener extends CyclicBehaviour {
		private static final long serialVersionUID = 741233963737842521L;
		private MessageTemplate template = MessageTemplate
				.MatchConversationId(CHAT_ID);

		ChatListener(Agent a) {
			super(a);
		}

		public void action() {
			ACLMessage msg = myAgent.receive(template);
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					notifySpoken(msg.getSender().getLocalName(),
							msg.getContent());
				} else {
					handleUnexpected(msg);
				}
			} else {
				block();
			}
		}
	} // END of inner class ChatListener

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
			Iterator it = participants.iterator();
			while (it.hasNext()) {
				spokenMsg.addReceiver((AID) it.next());
			}
			spokenMsg.setContent(sentence);
			notifySpoken(myAgent.getLocalName(), sentence);
			send(spokenMsg);
		}
	} // END of inner class ChatSpeaker

	// ///////////////////////////////////////
	// Methods called by the interface
	// ///////////////////////////////////////
	public void handleSpoken(String s) {
		// Add a ChatSpeaker behaviour that INFORMs all participants about
		// the spoken sentence
		addBehaviour(new ChatSpeaker(this, s));
	}

	public String[] getParticipantNames() {
		String[] pp = new String[participants.size()];
		Iterator it = participants.iterator();
		int i = 0;
		while (it.hasNext()) {
			AID id = (AID) it.next();
			pp[i++] = id.getLocalName();
		}
		return pp;
	}

	public AID[] getParticipantAIDs() {
		AID[] aids = new AID[participants.size()];
		Iterator it = participants.iterator();
		int i = 0;
		while (it.hasNext()) {
			AID id = (AID) it.next();
			aids[i++] = id;
		}
		return aids;
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

}// class Controller