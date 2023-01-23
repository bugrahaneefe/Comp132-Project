package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class studentcheck extends loginpage implements ActionListener{
	
	/*
	 * This class works almost same with student tab, however it is used for reaching student page from tutor tab.
	 * This tab can not edit student information.
	 * This tab can give an pass or fail to the student on the courses that student registered.
	 */
	
	public studentcheck() {
		
	}
	
	public static void main() {	
		JButton close = new JButton();

		JPanel panelstudentcheck;
		JLabel label,labelpassword,label2,label3,label4,label5,label6,label8,label9,label10,label11,label12,label13;
		JTextField stutype,stupassword,name,tckn,age,balance,lecturename;
		JFrame frame = new JFrame();
		JButton save = new JButton();
		JButton pass = new JButton();
		JButton fail = new JButton();
		JTextArea label7,label14;

		ArrayList<String> temp = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"))) {
			String line;
		      while ((line = reader.readLine()) != null) {
		    	  temp.add(line);
		}
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		
		for (String line : temp) {
			if (line.split(",")[0].contains(password.getText())&& line.split(",")[1].contains(type.getText())) {
				frame.setSize(750,600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				panelstudentcheck = new JPanel();
				panelstudentcheck.setSize(400,500);
				panelstudentcheck.setLayout(null);
				frame.getContentPane().add(panelstudentcheck);
				
				label = new JLabel("Type of your account:");
				labelpassword = new JLabel("Your password:");
				label2 = new JLabel("Your Name:");
				label3 = new JLabel("Your TCKN:");
				label4 = new JLabel("Your Age:");
				label5 = new JLabel("Your Monetary Balance:");
				label6 = new JLabel("Courses Passed:");
				label10 = new JLabel("Lecture:");

				label.setBounds(50, 50, 150, 20);
				panelstudentcheck.add(label);
				
				stutype = new JTextField(type.getText());
				stutype.setBounds(50, 70, 150, 20);
				stutype.setVisible(true);
				panelstudentcheck.add(stutype);
				
				labelpassword.setBounds(50, 90, 150, 20);
				panelstudentcheck.add(labelpassword);
				
				stupassword = new JTextField(line.split(",")[0]);
				stupassword.setBounds(50, 110, 150, 20);
				stupassword.setVisible(true);
				panelstudentcheck.add(stupassword);
				
				label2.setBounds(50, 130, 150, 20);
				panelstudentcheck.add(label2);
				
				name = new JTextField(line.split(",")[2]);
				name.setBounds(50, 150, 150, 20);
				name.setVisible(true);
				panelstudentcheck.add(name);
				
				label3.setBounds(50, 170, 150, 20);
				panelstudentcheck.add(label3);
				
				tckn = new JTextField(line.split(",")[3]);
				tckn.setBounds(50, 190, 150, 20);
				tckn.setVisible(true);
				panelstudentcheck.add(tckn);
				
				label4.setBounds(50, 210, 150, 20);
				panelstudentcheck.add(label4);
				
				age = new JTextField(line.split(",")[4]);
				age.setBounds(50, 230, 150, 20);
				age.setVisible(true);
				panelstudentcheck.add(age);
				
				label5.setBounds(50, 250, 150, 20);
				panelstudentcheck.add(label5);
				
				balance = new JTextField(line.split(",")[5]);
				balance.setBounds(50, 270, 150, 20);
				balance.setVisible(true);
				panelstudentcheck.add(balance);
			
				course Course = main.Course;
				equipment Equipment = main.Equipment;
				
				ArrayList<String> passedcourses = new ArrayList<String>();
				
				for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {
					
					ArrayList<ArrayList<String>> value = entry.getValue();
					if (value.get(1).isEmpty()) {
						continue;
					}else if (value.get(1).contains(stupassword.getText())) {

						passedcourses.add(entry.getKey());
					
					}
					
				}

				label7 = new JTextArea();
				label7.setLineWrap(true);
				label7.setWrapStyleWord(true);
				label7.setText("Passed Courses:");
				for (String pc: passedcourses) {
					label7.setText(label7.getText()+"\n"+pc);
				}
				label7.setBounds(210, 50, 150, 250);
				panelstudentcheck.add(label7);
				
		
				label10.setBounds(300, 320, 150, 20);
				panelstudentcheck.add(label10);
				
				lecturename = new JTextField();
				lecturename.setBounds(300, 340, 150, 20);
				lecturename.setVisible(true);
				panelstudentcheck.add(lecturename);
				
				pass = new JButton("Pass");
				pass.setBounds(260, 370, 100, 30);
				pass.setForeground(Color.WHITE);
				pass.setBackground(Color.BLACK);
				pass.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
											
						try (BufferedWriter writer = new BufferedWriter(new FileWriter("newpassed.txt",true))) {
							writer.write(stupassword.getText()+","+lecturename.getText()+"\n");
							
							JOptionPane.showMessageDialog(null, "Student has passed the course");
						} catch (IOException e) {
						      e.printStackTrace();
						    }
						frame.setVisible(false);


					}
						
				}
						
					);
				panelstudentcheck.add(pass);
			
				
				ArrayList<String> registeredcoursepairs=new ArrayList<String>();
				try (BufferedReader reader = new BufferedReader(new FileReader("newregisters.txt"))) {
					String lines;
				     while ((lines = reader.readLine()) != null) {

							for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {
								if (lines.split(",")[0].contains(password.getText())) {
									if (lines.split(",")[1].contains(entry.getKey())) {
										registeredcoursepairs.add(lines.split(",")[1]+":"+lines.split(",")[2]);
									}else {
										continue;
									}
								}
											
							}
				    } 
							
				}catch (IOException e) {
				      e.printStackTrace();
				    }
				
				label14 = new JTextArea();
				label14.setLineWrap(true);
				label14.setWrapStyleWord(true);
				label14.setText("Registered Courses:");
				for (String pc: registeredcoursepairs) {
					label14.setText(label14.getText()+"\n"+pc);
				}
				label14.setBounds(380, 50, 150, 250);
				panelstudentcheck.add(label14);
				
				
				
				

				fail = new JButton("Fail");
				fail.setBounds(360, 370, 100, 30);
				fail.setForeground(Color.WHITE);
				fail.setBackground(Color.BLACK);
				fail.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
							
							try (BufferedReader reader = new BufferedReader(new FileReader("newregisters.txt"))) {
								String lines;
							     while ((lines = reader.readLine()) != null) {

										for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {

											if (lines.split(",")[1].contains(entry.getKey())) {							
												Course.getCourses().get(entry.getKey()).get(1).remove(lines.split(",")[0]);
												JOptionPane.showMessageDialog(null, "Student has failed the course and removed from registered student list");

											}else {
												continue;
											}

										}
							    } 
										
							}catch (IOException e) {
							      e.printStackTrace();
							    } 
							frame.setVisible(false);

					}
						
				}
						
					);
				panelstudentcheck.add(fail);
				
				
				/*
				 * close tab button
				 */
				close = new JButton("Close Tab");
				close.setBounds(550, 50, 100, 30);
				close.setForeground(Color.WHITE);
				close.setBackground(Color.BLACK);
				close.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
							frame.setVisible(false);
					}
						
				}
						
					);
				panelstudentcheck.add(close);
				break;
		}
		}
		
		
		
		
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
