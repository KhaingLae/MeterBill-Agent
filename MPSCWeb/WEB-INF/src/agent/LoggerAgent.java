package agent;


import java.util.*;
import java.io.*;

import database.query;

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

public class LoggerAgent extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------
	private Logger logger = Logger.getMyLogger(this.getClass().getName());
	private static final String CHAT_ID = "__chat__";
	private static final String CHAT_MANAGER_NAME = "SubscriberAgent";

	private Set participants = new SortedSetImpl();
	private Codec codec = new SLCodec();
	private Ontology onto = ChatOntology.getInstance();
	public String print="";
//	transient protected SendGui myGui;

	private Location destination;

	
	// Get a JADE Runtime instance
	jade.core.Runtime runtime = jade.core.Runtime.instance();

	protected void setup() {

		// ------------------------
		System.out.println("logger agent enter...");
		 ((tweb.JadeBridgeLogger)getArguments()[0]).la = this;
		destination = here();

		getContentManager().registerLanguage(new SLCodec());
		getContentManager().registerOntology(ChatOntology.getInstance());

		// Add initial behaviours
		addBehaviour(new ParticipantsManager(this));
		addBehaviour(new ChatListener(this));		
	}
	
	

	private void notifyParticipantsChanged() {
		//myGui.notifyParticipantsChanged(getParticipantNames());
		String[] pl=getParticipantNames();
		for(int i=0;i<pl.length;i++){
			System.out.println("---------participant names---------------");
			System.out.println(pl[i]);
		}
		
	}

	private void notifySpoken(String speaker, String sentence) {
		//myGui.notifySpoken(speaker, sentence);		
		print=print+"\n"+speaker+"  "+sentence ;		
		System.out.println(print);
		if(print.length()>500)print="";	
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
			System.out.println("participant manager start..........");
			
			Long subbf=System.currentTimeMillis();
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
			Long subaf=System.currentTimeMillis();
			Long sudif=subaf-subbf;
			System.out.println("Subscribtion of Logger Agent Completed !");
			System.out.println("Total Time : "+sudif+"ms");
		}

		public void action() {
			// Receives information about people joining and leaving
			// the chat from the ChatManager agent
			System.out.println("msg receive from subscriber agent is.......");
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
			Long bf=System.currentTimeMillis();
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
			Long af=System.currentTimeMillis();
			Long df=af-bf;
			System.out.println("Output Logging !");
			System.out.println("Total Time : "+df+"ms");
		}
	} // END of inner class ChatListener


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