package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class signuppage extends JFrame implements ActionListener{

	private JLabel label,label2,label3,label4,label5,label6;
	JPanel panelsignup;
	protected JTextField type,password,name,tckn,age,balance;
	private JButton button;	
	

	public signuppage() {
		super("Sign-up Page");
		
		/*
		 * I am creating the sign-up page and
		 * Labels and buttons required implemented below:
		 */
		panelsignup = new JPanel();
		panelsignup.setLayout(null);
		
		label = new JLabel("Type of your account:");
		label2 = new JLabel("Your Password:");
		label3 = new JLabel("Your Name:");
		label4 = new JLabel("Your TCKN:");
		label5 = new JLabel("Your Age:");
		label6 = new JLabel("Your Monetary Balance:");
		
		label.setBounds(50, 50, 150, 20);
		panelsignup.add(label);
		
		type = new JTextField();
		type.setBounds(50, 70, 150, 20);
		panelsignup.add(type);
		
		label2.setBounds(50, 90, 150, 20);
		panelsignup.add(label2);
		
		password = new JTextField();
		password.setBounds(50, 110, 150, 20);
		panelsignup.add(password);
		
		label3.setBounds(50, 130, 150, 20);
		panelsignup.add(label3);
		
		name = new JTextField();
		name.setBounds(50, 150, 150, 20);
		panelsignup.add(name);
		
		label4.setBounds(50, 170, 150, 20);
		panelsignup.add(label4);
		
		tckn = new JTextField();
		tckn.setBounds(50, 190, 150, 20);
		panelsignup.add(tckn);
		
		label5.setBounds(50, 210, 150, 20);
		panelsignup.add(label5);
		
		age = new JTextField();
		age.setBounds(50, 230, 150, 20);
		panelsignup.add(age);
		
		label6.setBounds(50, 250, 150, 20);
		panelsignup.add(label6);
		
		balance = new JTextField();
		balance.setBounds(50, 270, 150, 20);
		panelsignup.add(balance);
		
		/*
		 * By sign-up button, I am saving the new members information to the registrarinfo.txt:
		 * Each type of person (student,tutor,admin) has their unique password. Type's can be common.
		 */
		button = new JButton("Sign-up");
		button.setBounds(50, 300, 150, 30);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.addActionListener(
				new ActionListener() // anonymous inner class
				{ 
			@Override
				public void actionPerformed(ActionEvent arg0) {
				String userreg = type.getText();
				String passreg = password.getText();
				String namereg = name.getText();
				String tcknreg = tckn.getText();
				String agereg = age.getText();
				String balancereg = balance.getText();
				
				/*
				 * Deriving information from registrarinfo.txt and storing them in a arraylist
				 */
				ArrayList<String> temp = new ArrayList<String>();
				int success = 1;
				try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"))) {
					String line;
				      while ((line = reader.readLine()) != null) {
				    	  temp.add(line);
				}
				    } catch (IOException e) {
				      e.printStackTrace();
				    }
				
				/*
				 * By iterating that arraylist, I am validating the passwords.
				 */
				for (String line : temp) {
					if (line.split(",")[0].contains(password.getText())) {
						JOptionPane.showMessageDialog(null, "Password is not valid !");
						success = 0;
						break;
				}
				}
				
				/*
				 * If password is valid, I am writing that information to registrarinfo.txt
				 */
				if (success ==1){
					try (BufferedWriter writer = new BufferedWriter(new FileWriter("registrarinfo.txt",true))) {
						
					    //writer.newLine();
						writer.write(passreg+","+userreg+","+namereg+","+tcknreg+","+agereg+","+balancereg+"\n");
					    } catch (IOException e) {
					      e.printStackTrace();
					    }
					setVisible(false);
				}
					}
				
		});
		panelsignup.add(button);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
