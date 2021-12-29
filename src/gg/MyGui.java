package gg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyGui implements ActionListener {

	JFrame f= new JFrame("Network scan"); 
	JLabel l1 = new JLabel("Please Enter your IP subnet (192.169.10)");
	JTextField t1 = new JTextField();
	JButton b1 = new JButton("Scan");

	MyGui() {
		prepareGUI();
	}
	
	public void prepareGUI(){ 
		
		l1.setBounds(80, 50, 400, 30);
	    f.add(l1);
	    
	    t1.setBounds(120,100, 200,30);  
	    f.add(t1);
	    
	    b1.setBounds(180, 150, 80, 30);
	    f.add(b1);
	    
	    f.setSize(500,500);  
	    f.setLayout(null);  
	    f.setVisible(true);  
	
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
