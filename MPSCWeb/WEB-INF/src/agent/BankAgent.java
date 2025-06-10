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
import jade.core.Runtime;

public class BankAgent extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --------------------------------------------
	private Logger logger = Logger.getMyLogger(this.getClass().getName());
	private static final String CHAT_ID = "__chat__";
	
	private ACLMessage spokenMsg;
	public String output ="";
	private Location destination;

	

	protected void setup() {

		// ------------------------
		System.out.println("bank agent enter...");
		 ((tweb.JadeBridgeBank)getArguments()[0]).ga = this;
		destination = here();

		getContentManager().registerLanguage(new SLCodec());
		//getContentManager().registerOntology(MobilityOntology.getInstance());
		getContentManager().registerOntology(ChatOntology.getInstance());

		// Add initial behaviours
		addBehaviour(new ChatListener(this));
		
		// Initialize the message used to convey spoken sentences
		spokenMsg = new ACLMessage(ACLMessage.INFORM);
		spokenMsg.setConversationId(CHAT_ID);
		// Create and show the gui
		

	}
	

	class ChatListener extends CyclicBehaviour {
		private static final long serialVersionUID = 741233963737842521L;
		private MessageTemplate template = MessageTemplate.MatchConversationId(CHAT_ID);

		ChatListener(Agent a) {
			super(a);
		}

		public void action() {
			Long bf=System.currentTimeMillis();
			ACLMessage msg = myAgent.receive(template);
			System.out.println("msg receive from bank is....");
			System.out.println(msg);
			String receive;
			if (msg != null) {
				if (msg.getPerformative() == ACLMessage.INFORM) {
					receive=msg.getContent();
					output=output+"\n"+msg.getContent();
					System.out.println(output);
					if(output.length()>500)output="";			
					
					
				} else {
					handleUnexpected(msg);
				}
			} else {
				block();
			}
			Long af=System.currentTimeMillis();
			Long df=af-bf;
			System.out.println("Receiving Paying Bill Message !");
			System.out.println("Total Time : "+df+"ms");
		}
	} // END of inner class ChatListener

	
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