package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class administrator extends loginpage{


	static int tutorafee;
	static int tutorbfee;
	
	public administrator() {
	
	}
	
	public static void main() {
		/*
		 * Implementing required variables
		 */
		JButton close = new JButton();

		JPanel paneladmin;
		JLabel label,labelpassword,label2,label3,label4,label5;
		
		final JTextField lecturenameadmin= new JTextField("Lecture Name");
		final JTextField lecturer= new JTextField("Lecturer");
		final JTextField time= new JTextField("Time");
		
		final JTextField newcoursename= new JTextField("Course Name");
		final JTextField newcourseprereq= new JTextField("Prerequisities");
		final JTextField newcoursepassedstu= new JTextField("Passed Students");
		final JTextField newcourseregisteredstu= new JTextField("Registered Students");
		final JTextField newcoursetutors= new JTextField("Tutors");
		final JTextField newcourseequipments= new JTextField("Equipments");
		final JTextField newcoursetutorstime= new JTextField("Course time");
		
		final JTextField stulecture= new JTextField("Lecture name (Student List)");
		
		final JTextField lectureprereq= new JTextField("Lecture name");
		final JTextField newprereq= new JTextField("Prerequisities");
		
		final JTextField lecturepassed= new JTextField("Lecture name");
		final JTextField newpassed= new JTextField("Passed Student");
		
		final JTextField lectureregistered= new JTextField("Lecture name");
		final JTextField newregistered= new JTextField("Registered Student");
		
		final JTextField lectureequipments= new JTextField("Lecture name");
		final JTextField newequipments= new JTextField("New Equipments");
		
		final JTextField givencourse= new JTextField("Tutor Schedule for given course:");
		final JTextField tutorname= new JTextField("Tutor Name");

		final JTextField feeA= new JTextField("Fee for tutorA");
		final JTextField feeB= new JTextField("Fee for tutorB");
		
		JButton savefees,registeradmin,saveprereq,savenewcourse,display,savepassed,saveregistered,saveequipments,displaygivencourse,displaytutorname = new JButton();

		JFrame frame = new JFrame();

		
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
				frame.setSize(1000,600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				paneladmin = new JPanel();
				paneladmin.setSize(400,500);
				paneladmin.setLayout(null);
				frame.getContentPane().add(paneladmin);
				
				label = new JLabel("ADMINISTRATOR:");
				label.setBounds(50, 50, 150, 20);
				paneladmin.add(label);
				
				labelpassword = new JLabel("Admin Id: "+password.getText());
				labelpassword.setBounds(50, 65, 150, 20);
				paneladmin.add(labelpassword);
				
				lecturenameadmin.setBounds(50, 100, 150, 20);
				lecturenameadmin.setVisible(true);
				paneladmin.add(lecturenameadmin);
				
				lecturer.setBounds(50, 120, 150, 20);
				lecturer.setVisible(true);
				paneladmin.add(lecturer);
				
				time.setBounds(50, 140, 150, 20);
				time.setVisible(true);
				paneladmin.add(time);
				
				/*
				 * Admin can register any course that he likes
				 */
				registeradmin = new JButton("Register");
				registeradmin.setBounds(50, 160, 150, 30);
				registeradmin.setForeground(Color.BLACK);
				registeradmin.setBackground(Color.WHITE);
				registeradmin.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
					
						
						try (BufferedWriter writer = new BufferedWriter(new FileWriter("newregisters.txt",true))) {
							
							writer.write(password.getText()+","+lecturenameadmin.getText()+","+lecturer.getText()+","+time.getText()+"\n");
							JOptionPane.showMessageDialog(null, "Registered to course, please refresh");
							frame.setVisible(false);
						} catch (IOException e) {
						      e.printStackTrace();
						    }

					}
						
				}
						
					);
				paneladmin.add(registeradmin);
				
				/*
				 * Adding new course to the system
				 */
				
				newcoursename.setBounds(200, 100, 150, 20);
				newcoursename.setVisible(true);
				paneladmin.add(newcoursename);
				
				newcourseprereq.setBounds(200, 120, 150, 20);
				newcourseprereq.setVisible(true);
				paneladmin.add(newcourseprereq);
				
				newcoursepassedstu.setBounds(200, 140, 150, 20);
				newcoursepassedstu.setVisible(true);
				paneladmin.add(newcoursepassedstu);
				
				newcourseregisteredstu.setBounds(200, 160, 150, 20);
				newcourseregisteredstu.setVisible(true);
				paneladmin.add(newcourseregisteredstu);
				
				newcoursetutors.setBounds(200, 180, 150, 20);
				newcoursetutors.setVisible(true);
				paneladmin.add(newcoursetutors);
				
				newcoursetutorstime.setBounds(200, 200, 150, 20);
				newcoursetutorstime.setVisible(true);
				paneladmin.add(newcoursetutorstime);
				
				newcourseequipments.setBounds(200, 220, 150, 20);
				newcourseequipments.setVisible(true);
				paneladmin.add(newcourseequipments);
				
				savenewcourse = new JButton("Save new course");
				savenewcourse.setBounds(200, 240, 150, 30);
				savenewcourse.setForeground(Color.BLACK);
				savenewcourse.setBackground(Color.WHITE);
				savenewcourse.addActionListener(
						new ActionListener()
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
					
						
						try (BufferedWriter writer = new BufferedWriter(new FileWriter("newlectures.txt",true))) {
							
								writer.write(newcoursename.getText()+","+newcourseprereq.getText()+","+newcoursepassedstu.getText()+","+newcourseregisteredstu.getText()+","+newcoursetutors.getText()+","+newcoursetutorstime.getText()+","+newcourseequipments.getText()+","+"\n");
								JOptionPane.showMessageDialog(null, "New lecture is added to the system, please refresh!");
								//frame.setVisible(false);
							
							
						} catch (IOException e) {
						      e.printStackTrace();
						    }

					}
						
				}
						
					);
				paneladmin.add(savenewcourse);
				
				
				/*
				 * Editing the courses that are already implemented into the system
				 */
				
				
				//editing prerequisities:
				lectureprereq.setBounds(50, 190, 150, 20);
				lectureprereq.setVisible(true);
				paneladmin.add(lectureprereq);
				
				newprereq.setBounds(50, 210, 150, 20);
				newprereq.setVisible(true);
				paneladmin.add(newprereq);
				
				
				saveprereq = new JButton("Save");
				saveprereq.setBounds(50, 230, 150, 30);
				saveprereq.setForeground(Color.BLACK);
				saveprereq.setBackground(Color.WHITE);
				saveprereq.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
					
						
						try (BufferedWriter writer = new BufferedWriter(new FileWriter("newprereqs.txt",true))) {
							
							writer.write(newprereq.getText()+","+lectureprereq.getText()+"\n");
							JOptionPane.showMessageDialog(null, "Prerequisite of course is added");
							frame.setVisible(false);
						} catch (IOException e) {
						      e.printStackTrace();
						    }

					}
						
				}
						
					);
				paneladmin.add(saveprereq);

				//editing the passed students
				lecturepassed.setBounds(50, 260, 150, 20);
				lecturepassed.setVisible(true);
				paneladmin.add(lecturepassed);
				
				newpassed.setBounds(50, 280, 150, 20);
				newpassed.setVisible(true);
				paneladmin.add(newpassed);
				
				
				savepassed = new JButton("Save");
				savepassed.setBounds(50, 300, 150, 30);
				savepassed.setForeground(Color.BLACK);
				savepassed.setBackground(Color.WHITE);
				savepassed.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
					
						
						try (BufferedWriter writer = new BufferedWriter(new FileWriter("newpassed.txt",true))) {
							
							writer.write(newpassed.getText()+","+lecturepassed.getText()+"\n");
							JOptionPane.showMessageDialog(null, "Passed students are updated");
						} catch (IOException e) {
						      e.printStackTrace();
						    }

					}
						
				}
						
					);
				paneladmin.add(savepassed);
				
				//editing registered students
				lectureregistered.setBounds(50, 330, 150, 20);
				lectureregistered.setVisible(true);
				paneladmin.add(lectureregistered);
				
				newregistered.setBounds(50, 350, 150, 20);
				newregistered.setVisible(true);
				paneladmin.add(newregistered);
				
				
				saveregistered = new JButton("Save");
				saveregistered.setBounds(50, 370, 150, 30);
				saveregistered.setForeground(Color.BLACK);
				saveregistered.setBackground(Color.WHITE);
				saveregistered.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
					
						
						try (BufferedWriter writer = new BufferedWriter(new FileWriter("newregisters.txt",true))) {
							
							writer.write(newpassed.getText()+","+lecturepassed.getText()+"\n");
							JOptionPane.showMessageDialog(null, "Registered students are updated");
						} catch (IOException e) {
						      e.printStackTrace();
						    }

					}
						
				}
						
					);
				paneladmin.add(saveregistered);
				
				//editing equipments of lectures
				lectureequipments.setBounds(50, 400, 150, 20);
				lectureequipments.setVisible(true);
				paneladmin.add(lectureequipments);
				
				newequipments.setBounds(50, 420, 150, 20);
				newequipments.setVisible(true);
				paneladmin.add(newequipments);
				
				
				saveequipments = new JButton("Save");
				saveequipments.setBounds(50, 440, 150, 30);
				saveequipments.setForeground(Color.BLACK);
				saveequipments.setBackground(Color.WHITE);
				saveequipments.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
					
						
						try (BufferedWriter writer = new BufferedWriter(new FileWriter("newequipments.txt",true))) {
							
							writer.write(newpassed.getText()+","+lecturepassed.getText()+"\n");
							JOptionPane.showMessageDialog(null, "Equipments of lecture are updated");
						} catch (IOException e) {
						      e.printStackTrace();
						    }

					}
						
				}
						
					);
				paneladmin.add(saveequipments);
				
				/*
				 * Display the registered students in a given course
				 */
				course Course = main.Course;
				
				stulecture.setBounds(350, 100, 170, 20);
				stulecture.setVisible(true);
				paneladmin.add(stulecture);
				
				
				display = new JButton("Display List");
				display.setBounds(350, 125, 150, 30);
				display.setForeground(Color.BLACK);
				display.setBackground(Color.WHITE);
				display.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
						ArrayList<String[]> agelist = new ArrayList<String[]>();
						try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"))) {
							String lines;
						     while ((lines = reader.readLine()) != null) {
						    	 String[] linesplit = lines.split(",");
						    	 for (String students:Course.getCourses().get(stulecture.getText()).get(2)) {
										if(students.contains(linesplit[0])) {
											agelist.add(linesplit);
											break;
										}
									}
						    } 
						    
						    System.out.println(agelist);
						    
						    //Collections.sort(agelist, Comparator.comparing(a -> a[0]));
						    
						    Collections.sort(agelist, new Comparator<String[]>() {
						        @Override
						        public int compare(String[] a, String[] b) {
						          return a[4].compareTo(b[4]);  // Compare the first element of each array
						        }
						      });
						    
							ArrayList<String> agelistlast = new ArrayList<String>();

						    for (String[] b: agelist) {
						    	agelistlast.add(b[0]);
						    }
						    
						     JOptionPane.showMessageDialog(null, agelistlast );							
									
						}catch (IOException e) {
						      e.printStackTrace();
						    }
						
					}
						
				}
						
					);
				paneladmin.add(display);
				
				/*
				 * 
				 * Display all the tutors’ schedules for a given course
				 */
				givencourse.setBounds(520, 100, 225, 20);
				givencourse.setVisible(true);
				paneladmin.add(givencourse);
				
				
				displaygivencourse = new JButton("Display Tutors");
				displaygivencourse.setBounds(520, 125, 170, 30);
				displaygivencourse.setForeground(Color.BLACK);
				displaygivencourse.setBackground(Color.WHITE);
				displaygivencourse.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
						
					     JOptionPane.showMessageDialog(null, Course.getCourses().get(givencourse.getText()).get(3) );							
;
						
						
					}
						
				}
						
					);
				paneladmin.add(displaygivencourse);
				
				/*
				 * Inquire about a student’s information from a tutor, and display the
					student’s course history.
				 * I thought it like reaching the tutor's page and tutor's can already reach the students information.
				 */
				
				tutorname.setBounds(520, 160, 225, 20);
				tutorname.setVisible(true);
				paneladmin.add(tutorname);
				
				displaytutorname = new JButton("Reach Tutor");
				displaytutorname.setBounds(520, 180, 170, 30);
				displaytutorname.setForeground(Color.BLACK);
				displaytutorname.setBackground(Color.WHITE);
				displaytutorname.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
						
						try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"))) {
							String lines;
						     while ((lines = reader.readLine()) != null) {
						    	 if (lines.split(",")[2].contains(tutorname.getText())) {
						    		 JOptionPane.showMessageDialog(null,"Tutor's Id: "+lines.split(",")[0]);
						    			
						    	 }else {
						    		continue;	
						    	 }
						 			
						    } 
									
						}catch (IOException e) {
						      e.printStackTrace();
						    }
						
						loginpage.checktutor();
						
					}
						
				}
						
					);
				paneladmin.add(displaytutorname);
				
				/*
				 * close tab button
				 */
				close = new JButton("Close Tab");
				close.setBounds(755, 100, 100, 30);
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
				paneladmin.add(close);
				
				
				/*
				 * charging tutor's fees
				 */
				feeA.setBounds(700, 220, 150, 20);
				feeA.setVisible(true);
				paneladmin.add(feeA);
				
				feeB.setBounds(700, 240, 150, 20);
				feeB.setVisible(true);
				paneladmin.add(feeB);
				
				savefees = new JButton("Save Fees");
				savefees.setBounds(700, 270, 150, 30);
				savefees.setForeground(Color.WHITE);
				savefees.setBackground(Color.BLACK);
				savefees.addActionListener(
						new ActionListener() // anonymous inner class
						{ 
					@Override
						public void actionPerformed(ActionEvent arg0) {
							tutorafee = Integer.parseInt(feeA.getText());
							tutorbfee = Integer.parseInt(feeB.getText());
							setTutorafee(tutorafee);
							setTutorbfee(tutorbfee);
					}
						
				}
						
					);
				paneladmin.add(savefees);
				
				
			}
			
			
		}
		
		
		
	}
	
	public static int getTutorafee() {
		return tutorafee;
	}

	public static void setTutorafee(int tutorafee) {
		administrator.tutorafee = tutorafee;
	}

	public static int getTutorbfee() {
		return tutorbfee;
	}

	public static void setTutorbfee(int tutorbfee) {
		administrator.tutorbfee = tutorbfee;
	}
		
	
}
