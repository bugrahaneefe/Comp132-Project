package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class tutorcheck extends loginpage {

		
	public tutorcheck() {
		super();
	}
	
	public static void main() {
		JPanel paneltutor;
		JLabel label,labelpassword,label2,label3,label4,label5;
		JTextField tutortype,tutorpass,name,tckn,age,balance,lecturename,timeinfo;
		JFrame frame = new JFrame();
		JButton save,addlecture,checkstudent = new JButton();
		int tutorbalanceint = 0;
		JTextArea lecturesteach;
		JButton close = new JButton();

		
		/*
		 * I am deriving tutor information from registrarinfo.txt and store them in temporary arraylist
		 */
		ArrayList<String> temp = new ArrayList<String>();
		try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"))) {
			String line;
		      while ((line = reader.readLine()) != null) {
		    	  temp.add(line);
		}
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		
		
		/*
		 * I am iterating temporary arraylist and working on spesified line by using if condition.
		 */
		for (String line : temp) {
			if (line.split(",")[0].contains(password.getText())&& line.split(",")[1].contains(type.getText())) {
				/*
				 * Implementing required gui components
				 */
				
				frame.setSize(750,400);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				paneltutor = new JPanel();
				paneltutor.setSize(400,500);
				paneltutor.setLayout(null);
				frame.getContentPane().add(paneltutor);
				
				label = new JLabel("Type of your account:");
				labelpassword = new JLabel("Your password:");
				label2 = new JLabel("Your Name:");
				label3 = new JLabel("Your TCKN:");
				label4 = new JLabel("Your Age:");
				label5 = new JLabel("Your Monetary Balance:");
	
				
				label.setBounds(50, 50, 150, 20);
				paneltutor.add(label);
				
				tutortype = new JTextField(type.getText());
				tutortype.setBounds(50, 70, 150, 20);
				tutortype.setVisible(true);
				paneltutor.add(tutortype);
				
				labelpassword.setBounds(50, 90, 150, 20);
				paneltutor.add(labelpassword);
				
				tutorpass = new JTextField(line.split(",")[0]);
				tutorpass.setBounds(50, 110, 150, 20);
				tutorpass.setVisible(true);
				paneltutor.add(tutorpass);
				
				label2.setBounds(50, 130, 150, 20);
				paneltutor.add(label2);
				
				name = new JTextField(line.split(",")[2]);
				name.setBounds(50, 150, 150, 20);
				name.setVisible(true);
				paneltutor.add(name);
				
				label3.setBounds(50, 170, 150, 20);
				paneltutor.add(label3);
				
				tckn = new JTextField(line.split(",")[3]);
				tckn.setBounds(50, 190, 150, 20);
				tckn.setVisible(true);
				paneltutor.add(tckn);
				
				label4.setBounds(50, 210, 150, 20);
				paneltutor.add(label4);
				
				age = new JTextField(line.split(",")[4]);
				age.setBounds(50, 230, 150, 20);
				age.setVisible(true);
				paneltutor.add(age);
				
				label5.setBounds(50, 250, 150, 20);
				paneltutor.add(label5);
				

				/*
				 * Updating tutor balance based on tutor level and the number of students that he has.
				 */
				tutorbalanceint = Integer.parseInt(line.split(",")[5]);
						try (BufferedReader reader = new BufferedReader(new FileReader("newregisters.txt"))) {
							String lineA;
						     while ((lineA = reader.readLine()) != null) {

									if (name.getText().contains(lineA.split(",")[2])) {
										if(type.getText().contains("tutorA")) {
											tutorbalanceint += 1000;
										}else if (type.getText().contains("tutorB")){
											tutorbalanceint += 500;
										}
									}
						    }
									
						}catch (IOException e) {
						      e.printStackTrace();
						    }	
						/*
						 * Updating tutor balance in registarinfo.txt
						 */
				String tutorbalancestring = String.valueOf(tutorbalanceint);
						try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"));
								BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt",true))) {
							String lineB;
						      while ((lineB = reader.readLine()) != null) {
						    	  if (lineB.split(",")[0].contains(password.getText())) {
								      writer.write(tutorpass.getText()+","+type.getText()+","+name.getText()+","+tckn.getText()+","+age.getText()+","+tutorbalancestring);
								      writer.newLine();
						    	  }else {
							    	  writer.write(lineB + System.lineSeparator());
						    	  }
						    	  
						    	  }
						     
						    } catch (IOException e) {
						      e.printStackTrace();
						    }
						  new File("registrarinfo.txt").delete();
					      new File("temp.txt").renameTo(new File("registrarinfo.txt"));
						
					      balance = new JTextField(tutorbalancestring);
							balance.setBounds(50, 270, 150, 20);
							balance.setVisible(true);
							paneltutor.add(balance);
							
					      /*
					       * Checking which student takes the course that tutor give and visulize them.
					       */
					    ArrayList<String> lectures = new ArrayList<String>();
					    try (BufferedReader reader = new BufferedReader(new FileReader("newregisters.txt"))) {
										String lineA;
									     while ((lineA = reader.readLine()) != null) {

												if (name.getText().toLowerCase().contains(lineA.split(",")[2].toLowerCase())) {
													if (lectures.contains(lineA.split(",")[1]) == false) {
														lectures.add(lineA.split(",")[1]);
													}
												}
												
									    }
												
									}catch (IOException e) {
									      e.printStackTrace();
									    }	
					    HashMap<String,String> lecturestupair = new HashMap<String,String>();
					    try (BufferedReader reader = new BufferedReader(new FileReader("newregisters.txt"))) {
							String lineA;
							for (String ll: lectures) { 
								while ((lineA = reader.readLine()) != null) {

										if ((lineA.split(",")[1].contains(ll))&&(name.getText().toLowerCase().contains(lineA.split(",")[2].toLowerCase()))) {
											if (lecturestupair.get(ll) == null) {
												lecturestupair.put(ll,lineA.split(",")[0]);
											}else {
												lecturestupair.put(ll,lecturestupair.get(ll)+","+lineA.split(",")[0]);

											}
										}
						    }
							}
									
						}catch (IOException e) {
						      e.printStackTrace();
						    }
					    
					    String lecturesgiven = "";
						for (Map.Entry<String, String> entry : lecturestupair.entrySet()) {
							
							lecturesgiven += entry.getKey()+":"+entry.getValue()+"::";
							
						}
						
					
						lecturesteach = new JTextArea();
						lecturesteach.setLineWrap(true);
						lecturesteach.setWrapStyleWord(true);
						lecturesteach.setText("Registered Students: ");
						for (String pc: lecturesgiven.split("::")) {
							lecturesteach.setText(lecturesteach.getText()+"\n"+pc);
						}
						lecturesteach.setBounds(210, 50, 200, 230);
						paneltutor.add(lecturesteach);  
				
				
				/*
				 * Tutor opens a new course, and add it to the system. However, course must be added by the administrator to make students see them.
				 */
				
				lecturename = new JTextField("lecture name");
				lecturename.setBounds(420, 50, 150, 20);
				lecturename.setVisible(true);
				paneltutor.add(lecturename);
				
				timeinfo = new JTextField("time information");
				timeinfo.setBounds(420, 70, 150, 20);
				timeinfo.setVisible(true);
				paneltutor.add(timeinfo);
				
				addlecture = new JButton("Add Lecture");
				addlecture.setBounds(420, 100, 150, 30);
				addlecture.setForeground(Color.BLACK);
				addlecture.setBackground(Color.WHITE);
				addlecture.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
						
						try (BufferedWriter writer = new BufferedWriter(new FileWriter("tutoraddlecture.txt",true))) {
							//writer.newLine();
							writer.write(lecturename.getText()+","+name.getText()+","+timeinfo.getText()+"\n");
							JOptionPane.showMessageDialog(null, "Course is added, please refresh");
						} catch (IOException e) {
						      e.printStackTrace();
						    }
					}
						
				}
						
					);
				paneltutor.add(addlecture);
				

				/*
				 * Tutor reaches the students information, tutor can give pass or fail.
				 */
				checkstudent = new JButton("Check Student");
				checkstudent.setBounds(210, 300, 200, 30);
				checkstudent.setForeground(Color.BLACK);
				checkstudent.setBackground(Color.WHITE);
				checkstudent.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
						loginpage.checkstu();
					}
				});
				
				paneltutor.add(checkstudent);
				
				/*
				 * Implementing save button
				 * Save button updates the registrarinfo.txt based on text fields given in the tutor tab
				 */
				save = new JButton("Save");
				save.setBounds(50, 300, 150, 30);
				save.setForeground(Color.WHITE);
				save.setBackground(Color.BLACK);
				save.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
							
							String passwordaction = tutorpass.getText();
							String tutorname = name.getText();
							String tutortckn = tckn.getText();
							String tutorage = age.getText();
							String tutorbalance = balance.getText();
							
							ArrayList<String> temp = new ArrayList<String>();

							try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"));
									BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt",true))) {
								String line;
							      while ((line = reader.readLine()) != null) {
							    	  if (line.split(",")[0].contains(password.getText())) {
									      writer.write(passwordaction+","+type.getText()+","+tutorname+","+tutortckn+","+tutorage+","+tutorbalance);
									      writer.newLine();
							    	  }else {
								    	  writer.write(line + System.lineSeparator());
							    	  }
							    	  
							    	  }
							     
							    } catch (IOException e) {
							      e.printStackTrace();
							    }
							  new File("registrarinfo.txt").delete();
						      new File("temp.txt").renameTo(new File("registrarinfo.txt"));
						     
							

							
						        
					}
						
				}
						
					);
				paneltutor.add(save);
				
				
				
				/*
				 * close tab button
				 */
				close = new JButton("Close Tab");
				close.setBounds(580, 50, 150, 30);
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
				paneltutor.add(close);
				
				break;
		}
	}
		}
	
	}

	

