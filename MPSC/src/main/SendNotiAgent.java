package main;

import java.util.Iterator;


import chat.ontology.ChatOntology;

import gui.SendGui;


import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Result;
import jade.core.AID;
import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Location;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.KillAgent;
import jade.domain.mobility.CloneAction;
import jade.domain.mobility.MobileAgentDescription;
import jade.domain.mobility.MobilityOntology;
import jade.domain.mobility.MoveAction;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.Logger;

public class SendNotiAgent extends Agent {
	private AID controller;
	private Location destination;
	private ACLMessage spokenMsg;
	private static final String CHAT_ID = "__chat__";
	private Logger logger = Logger.getMyLogger(this.getClass().getName());
	public void setup() {
		// register the SL0 content language
		// Retrieve arguments passed during this agent creation
		Object[] args = getArguments();
		controller = (AID) args[0];
		System.out.println("controller agent id..."+controller.getName());
		destination = here();

		getContentManager().registerLanguage(new SLCodec());
		getContentManager().registerOntology(ChatOntology.getInstance());

		// Program the main behaviour of this agent
		addBehaviour(new ChatListener(this));

		// Initialize the message used to convey spoken sentences
		spokenMsg = new ACLMessage(ACLMessage.INFORM);
		spokenMsg.setConversationId(CHAT_ID);

	}

	
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
	private void notifySpoken(String speaker, String sentence) {
		
	}
	private void handleUnexpected(ACLMessage msg) {
		if (logger.isLoggable(Logger.WARNING)) {
			logger.log(Logger.WARNING, "Unexpected message received from "
					+ msg.getSender().getName());
			logger.log(Logger.WARNING, "Content is: " + msg.getContent());
		}
	}

	public void handleSpoken(String s) {
		// Add a ChatSpeaker behaviour that INFORMs all participants about
		// the spoken sentence
		addBehaviour(new ChatSpeaker(this, s));
	}
	private class ChatSpeaker extends OneShotBehaviour {
		private static final long serialVersionUID = -1426033904935339194L;
		private String sentence;

		private ChatSpeaker(Agent a, String s) {
			super(a);
			sentence = s;
		}

		public void action() {
			spokenMsg.clearAllReceiver();
			String guiAgent = "GUIAgent";
			AID aid = new AID(guiAgent, AID.ISLOCALNAME);
			spokenMsg.addReceiver(aid);			
			spokenMsg.setContent(sentence);
			notifySpoken(myAgent.getLocalName(), sentence);
			send(spokenMsg);
		}
	} // END of inner class ChatSpeaker
}
