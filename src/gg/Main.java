package gg;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main  {
	
	
	private static Scanner sc;

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, GeneralSecurityException {
		try {
		    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e){

		}
		
		final JFrame f= new JFrame("TextField Example");  

	    final JLabel l1 = new JLabel("Please Enter your IP subnet (192.169.10)");
	    l1.setBounds(80, 50, 400, 30);
	    f.add(l1);
	    
	    final JFileChooser jf = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
	    jf.setFileFilter(filter);
	    jf.setCurrentDirectory(new File(System.getProperty("user.home")));
	    
	    final JTextField t1;
	    t1=new JTextField("192.168.10");  
	    t1.setBounds(120,100, 200,30);  
	    f.add(t1);
	    
	    final JTextField t2;
	    t2 =new JTextField("filePassword");  
	    t2.setBounds(120,150, 200,30);  
	    f.add(t2);
	    
	    final JLabel l2 = new JLabel("Scanning. Please Wait ....");
	    l2.setBounds(80, 50, 400, 30);
	    
	    final JTextArea l3 = new JTextArea("IP addresses = ");
	    l3.setEditable(false);
	    l3.setBounds(80, 100, 400, 300);
	    
	    
	    final JPanel p2 = new JPanel();
	    p2.setLayout(null);
	    p2.setBounds(80, 150, 400, 30);
	    
	    final JButton b1 = new JButton("Scan");
	    b1.setBounds(180, 200, 80, 30);
	    f.add(b1);
	    b1.addActionListener(new ActionListener(){
		    	 @Override
				public void actionPerformed(ActionEvent e) {
		    		 int result = jf.showOpenDialog(jf);
		    		  if (result == JFileChooser.APPROVE_OPTION) {
		    		        final String filePath = jf.getSelectedFile().getAbsolutePath();
		    		    
		    		        final String subnet = t1.getText();
				    		 
				    		 l1.hide();
				    		 t1.hide();
				    		 t2.hide();
				    		 b1.hide();
				    		 

				    		 f.add(l2);
				    		 f.add(p2);
				    		 f.repaint();
				    		 
				    		 final int timeout = 1000;
				    		 
				    		 new Thread(new Runnable() {
				    		     @Override
				    		     public void run() {
						    		 for(int i = 1; i < 5; i++) {
					    				String host = subnet + "." + i;
					    				try {
					    					if (InetAddress.getByName(host).isReachable(timeout)) {
					    						System.out.println("IP = " + host);
					    						f.add(l3);
					    						PortScanner portscan = new PortScanner();
					    						ArrayList<Integer> ports = portscan.getOpenPorts(host);
					    						System.out.println("ports are" + ports);
					    						
					    						l3.setText(l3.getText() + " \n " + host);
					    						EncryptFile.writeTofile(filePath, "Ip Address = " + host + "\n");
					    						EncryptFile.writeTofile(filePath, "Open ports = " + ports.toString() + "\n");
					    					
					    					
					    					}
					    				} catch (IOException e1) {
					    					e1.printStackTrace();
					    				} catch (InterruptedException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} catch (ExecutionException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
					    		 }
						    		 	try {
						    		 		l2.setText("Scan completed. Encrypting your files");
											EncryptFile.encryptFile(filePath, t2.getText());
											EncryptFile.decryptFile(filePath, t2.getText());
											l2.setText("Done !!!");
											File file = new File (filePath);
						    		 	} catch (IOException | GeneralSecurityException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
						    			
					    		 
				    		     }
				    		}).start();
				    		
		    		  
		    		  }
		    		 	

		    		
		    		 
				}
	    	});
	    
	    f.setSize(500,500);  
	    f.setLayout(null);  
	    f.setVisible(true);  
	    
		
		
		
		
		
		
//		System.out.println("Please enter your subnet");
//		sc = new Scanner(System.in);
//		String subnet = sc.nextLine();
//		scanNetwork(subnet);
	}

	public static void scanNetwork(String subnet) throws InterruptedException, ExecutionException, IOException, GeneralSecurityException {
//		Socket socket = new Socket();
//		System.out.println(socket.getLocalAddress().getLocalHost());
//		InetAddress IP=InetAddress.getLocalHost();
//		System.out.println("IP of my system is := "+IP.getHostAddress());
		int timeout = 1000;
		String filePath = "/home/qasim.ali@ebryx.com/Desktop/haseeb.txt";
		for (int i = 1; i < 5; i++) {
			String host = subnet + "." + i;
			try {
				if (InetAddress.getByName(host).isReachable(timeout)) {
					System.out.println("IP = " + host);
					PortScanner portscan = new PortScanner();
					ArrayList<Integer> ports = portscan.getOpenPorts(host);
					System.out.println("ports are" + ports);
					EncryptFile.writeTofile(filePath, "Ip Address = " + host + "\n");
					EncryptFile.writeTofile(filePath, "Open ports = " + ports.toString() + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		EncryptFile.encryptFile(filePath, "myPassword");
		EncryptFile.decryptFile(filePath, "myPassword");
		
	}
}