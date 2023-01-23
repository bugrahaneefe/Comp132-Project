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

public class student extends loginpage implements ActionListener{
	
	public student() {
		
	}
	
	public static void main() {	
		JPanel panelstudent;
		JLabel label,labelpassword,label2,label3,label4,label5,label9,label10,label11,label12;
		JTextField stutype,stupassword,name,tckn,age,balance,lecturename,lecturer,time;
		JFrame frame = new JFrame();
		JButton save = new JButton();
		JButton register = new JButton();
		JButton close = new JButton();

		JTextArea label7,label8,label14;
		
		/*
		 * I am deriving student information from registrarinfo.txt and store them in temporary arraylist
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
				  * Implementing course and equipment objects for further usage
				  */
				course Course = main.Course;
				equipment Equipment = main.Equipment;
				
				
				/*
				 * Implementing required gui components
				 */
				
				frame.setSize(980,670);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				panelstudent = new JPanel();
				panelstudent.setSize(400,500);
				panelstudent.setLayout(null);
				frame.getContentPane().add(panelstudent);
				
				label = new JLabel("Type of your account:");
				labelpassword = new JLabel("Your password:");
				label2 = new JLabel("Your Name:");
				label3 = new JLabel("Your TCKN:");
				label4 = new JLabel("Your Age:");
				label5 = new JLabel("Your Monetary Balance:");
				label10 = new JLabel("Lecture:");
				label11 = new JLabel("Lecturer:");
				label12 = new JLabel("Time");

				label.setBounds(50, 50, 150, 20);
				panelstudent.add(label);
				
				stutype = new JTextField(type.getText());
				stutype.setBounds(50, 70, 150, 20);
				stutype.setVisible(true);
				panelstudent.add(stutype);
				
				labelpassword.setBounds(50, 90, 150, 20);
				panelstudent.add(labelpassword);
				
				stupassword = new JTextField(line.split(",")[0]);
				stupassword.setBounds(50, 110, 150, 20);
				stupassword.setVisible(true);
				panelstudent.add(stupassword);
				
				label2.setBounds(50, 130, 150, 20);
				panelstudent.add(label2);
				
				name = new JTextField(line.split(",")[2]);
				name.setBounds(50, 150, 150, 20);
				name.setVisible(true);
				panelstudent.add(name);
				
				label3.setBounds(50, 170, 150, 20);
				panelstudent.add(label3);
				
				tckn = new JTextField(line.split(",")[3]);
				tckn.setBounds(50, 190, 150, 20);
				tckn.setVisible(true);
				panelstudent.add(tckn);
				
				label4.setBounds(50, 210, 150, 20);
				panelstudent.add(label4);
				
				age = new JTextField(line.split(",")[4]);
				age.setBounds(50, 230, 150, 20);
				age.setVisible(true);
				panelstudent.add(age);
				
				label5.setBounds(50, 250, 150, 20);
				panelstudent.add(label5);
				
				balance = new JTextField(line.split(",")[5]);
				balance.setBounds(50, 270, 150, 20);
				balance.setVisible(true);
				panelstudent.add(balance);
				
				/*
				 * I am visulazing the courses that student passed
				 */
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
				label7.setBounds(210, 50, 100, 290);
				panelstudent.add(label7);
				
				/*
				 * 
				 * By this part, I am checking that which courses that student can register depending on his balance
				 * ,the courses that he passed and course prerequisities 
				 */
				
				ArrayList<String> coursepairs=new ArrayList<String>();
				
				for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {
					ArrayList<ArrayList<String>> value = entry.getValue();
					
					int needed_money=0;

					for (String equname: value.get(4)) {
						if (Equipment.getEquipments().get(equname) != null) {
							needed_money += Equipment.getEquipments().get(equname);

						}
					}
					
					int intbalance = Integer.parseInt(balance.getText());

					if (value.get(3).isEmpty()) {
						continue;
					}else if (passedcourses.containsAll(value.get(0)) && intbalance >= needed_money ){
							for (String i :value.get(3)) {
								coursepairs.add(entry.getKey()+": "+i);

							}
							
					
					}
					
					
				}
								
				label8 = new JTextArea();
				label8.setLineWrap(true);
				label8.setWrapStyleWord(true);
				label8.setText("Courses:");
				for (String pc: coursepairs) {
					label8.setText(label8.getText()+"\n"+pc);
				}
				label8.setBounds(320, 50, 500, 290);
				panelstudent.add(label8);
				
				/*
				 * Student can register the courses, however, if it is not in the list "Courses that can be registered"
				 * it raises a warning and do not register student.
				 */
				label10.setBounds(50, 360, 150, 20);
				panelstudent.add(label10);
				
				lecturename = new JTextField();
				lecturename.setBounds(50, 380, 150, 20);
				lecturename.setVisible(true);
				panelstudent.add(lecturename);
				
				label11.setBounds(50, 400, 150, 20);
				panelstudent.add(label11);
				
				lecturer = new JTextField();
				lecturer.setBounds(50, 420, 150, 20);
				lecturer.setVisible(true);
				panelstudent.add(lecturer);
				
				label12.setBounds(50, 440, 150, 20);
				panelstudent.add(label12);
				
				time = new JTextField();
				time.setBounds(50, 460, 150, 20);
				time.setVisible(true);
				panelstudent.add(time);
				
				register = new JButton("Register");
				register.setBounds(50, 490, 150, 30);
				register.setForeground(Color.BLACK);
				register.setBackground(Color.WHITE);
				register.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
						int success = 0;
						for (String lecturestoregister:coursepairs) {
							if (lecturestoregister.contains(lecturename.getText())) {
								try (BufferedWriter writer = new BufferedWriter(new FileWriter("newregisters.txt",true))) {
									writer.write(stupassword.getText()+","+lecturename.getText()+","+lecturer.getText()+","+time.getText()+"\n");
									
									JOptionPane.showMessageDialog(null, "Registered to course, please refresh");
									frame.setVisible(false);
								} catch (IOException e) {
								      e.printStackTrace();
								    }
								success = 1;
								break;
							}
						
						}
						if (success==0) {
							JOptionPane.showMessageDialog(null, "You can not register that course!");
						}
					}
				}
						
					);
				panelstudent.add(register);
			
				
				/*
				 * Visulizing the courses that student already registered
				 */
				ArrayList<String> registeredcoursepairs=new ArrayList<String>();
				try (BufferedReader reader = new BufferedReader(new FileReader("newregisters.txt"))) {
					String lines;
				     while ((lines = reader.readLine()) != null) {

							for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {
								if (lines.split(",")[0].contains(password.getText())) {
									if (lines.split(",")[1].contains(entry.getKey())) {
										registeredcoursepairs.add(lines.split(",")[1]+":"+lines.split(",")[2]+" ");
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
				label14.setBounds(210, 360, 650, 300);
				panelstudent.add(label14);
				
				

				/*
				 * Implementing save button
				 * Save button updates the registrarinfo.txt based on text fields given in the student tab
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
							
							String passwordaction = stupassword.getText();
							String stuname = name.getText();
							String stutckn = tckn.getText();
							String stuage = age.getText();
							String stubalance = balance.getText();
							
							ArrayList<String> temp = new ArrayList<String>();

							try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"));
									BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt",true))) {
								String line;
							      while ((line = reader.readLine()) != null) {
							    	  if (line.split(",")[0].contains(password.getText())) {
									      writer.write(passwordaction+","+type.getText()+","+stuname+","+stutckn+","+stuage+","+stubalance);
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
				panelstudent.add(save);
				
				/*
				 * close tab button
				 */
				
				close = new JButton("Close Tab");
				close.setBounds(860, 50, 100, 30);
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
				panelstudent.add(close);
				break;
		}
		}

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
