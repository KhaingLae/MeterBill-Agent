package gui;

import jade.core.Location;
import jade.gui.GuiEvent;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import main.GUIAgent;

import main.SendNotiAgent;



public class SendGui extends JFrame implements ActionListener{
	 GUIAgent myAgent;
     JLabel town, block, street, home;
     JComboBox townlist, blocklist, streetlist, homelist;
     JPanel displayPanel;
     JButton all, select;
     
     private JList list;
     private DefaultListModel listModel;
     
	 
	public SendGui(GUIAgent aa, Set s)
	{
		super();
		myAgent = aa;
		setTitle("Send Meter Bill");
		setLayout( new FlowLayout(FlowLayout.CENTER, 50, 50 ));
		setSize( 500, 400 );
		setResizable( false );
		town=new JLabel();
		block=new JLabel();
		street=new JLabel();
		home=new JLabel();
		town.setText("Select Town : ");
		block.setText("Select Block : ");
		street.setText("Select Street : ");
		home.setText("Remaining Homes : ");
		townlist=new JComboBox();
		blocklist=new JComboBox();
		streetlist=new JComboBox();
		homelist=new JComboBox();
		displayPanel=new JPanel();
		displayPanel.setLayout( new GridLayout( 4, 2, 50, 30  ));
		displayPanel.add(town);
		displayPanel.add(townlist);
		displayPanel.add(block);
		displayPanel.add(blocklist);
		displayPanel.add(street);
		displayPanel.add(streetlist);
		displayPanel.add(home);
		displayPanel.add(homelist);
		all=new JButton("ALL");
		select=new JButton("SELECT");
		all.setText("Send All Agents");
		select.setText("Send Selected Agents");
		add(displayPanel);
		add(all);
		add(select);
		all.addActionListener(this);
		select.addActionListener(this);
		addWindowListener(
				new WindowAdapter()
				{
					public void windowClosing( WindowEvent evt )
				{
				System.exit( 0 );
				} // end method windowClosing
				} // end anonymous inner class
				); // end call to addWindowListener
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == select)  {
		  //Location dest;
			System.out.println("SELECT button...");
		  GuiEvent ev = new GuiEvent((Object) this,myAgent.MOVE_AGENT);
		  ev.addParameter(homelist.getSelectedItem());
		  myAgent.postGuiEvent(ev);	 
		}
				
	}
	
	public void updateList(Vector v) {
		// ----------------------------------

		      listModel.clear();
		      for (int i = 0; i < v.size(); i++){
		         listModel.addElement(v.get(i));
			  }
		   }
	
	public void notifyParticipantsChanged(String[] names) {
		System.out.println("-----notify-----");
		for(int i=0;i<names.length;i++)
			System.out.println(names[i]);
		homelist.removeAllItems();
		for(int i=0;i<names.length;i++)
			homelist.addItem(names[i]);
		
		}
	
	public void notifySpoken(String speaker, String sentence) {
		
	}
/*	public static void main(String args[]){
	 new SendGui();
	}*/

	
}
