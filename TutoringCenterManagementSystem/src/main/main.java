package main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class main {

	public static course Course;
	public static equipment Equipment;
	
	public static void main(String[] args) {
		/************** Pledge of Honor ******************************************
		I hereby certify that I have completed this programming project on my own
		without any help from anyone else. The effort in the project thus belongs
		completely to me. I did not search for a solution, or I did not consult any
		program written by others or did not copy any program from other sources. I
		read and followed the guidelines provided in the project description.
		READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
		SIGNATURE: <Buğrahan Efe, 71703>
		*************************************************************************/
		
		/*
		 * Initializing Login page
		 */
		loginpage login = new loginpage();
		login.add(login.panellogin);
		login.setSize(400,400);
		login.setVisible(true);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		/*
		 * Course and Equipment objects:
		 */
		Course = new course();
		Equipment = new equipment();
		
		/*
		 * Setting default course features, these features can be updated by tutors or administrator.
		 */
		Equipment.addEquipment("Laptop", 5000);
		Equipment.addEquipment("Tablet", 2500);
		Equipment.addEquipment("Transistor", 3000);
		Equipment.addEquipment("Telephone", 1000);
		Equipment.addEquipment("FPGA", 1500);


		Course.setLectures("B Mathematic", Course.getStringlist(), Course.getStringlist(), Course.getStringlist(), Course.getStringlist(), Course.getStringlist());
		Course.setLectures("B Linear Algebra", Course.getStringlist(), Course.getStringlist(), Course.getStringlist(), Course.getStringlist(), Course.getStringlist());
		Course.setLectures("A Computer Networks", Course.getStringlist(), Course.getStringlist(), Course.getStringlist(), Course.getStringlist(), Course.getStringlist());
		Course.setLectures("A Machine Learning", Course.getStringlist(), Course.getStringlist(), Course.getStringlist(), Course.getStringlist(), Course.getStringlist());

		Course.setStudent_passed(Course.getCourses(), "B Mathematic",Course.create_student_passed("000,111"));
		Course.setStudent_passed(Course.getCourses(), "B Linear Algebra",Course.create_student_passed("000"));

		Course.setPrerequisities(Course.getCourses(), "A Computer Networks",Course.create_prerequisities("B Mathematic"));
		Course.setStudent_passed(Course.getCourses(), "A Computer Networks",Course.create_student_passed("456,000,111"));
		Course.setTutor(Course.getCourses(), "A Computer Networks", "Oznur Ozkasap", "MoWe 14.00");
		Course.setEquipments(Course.getCourses(), "A Computer Networks",Course.create_equipments("Laptop,Tablet"));

		Course.setPrerequisities(Course.getCourses(), "A Machine Learning",Course.create_prerequisities("B Mathematic,B Linear Algebra"));
		Course.setStudent_passed(Course.getCourses(), "A Machine Learning",Course.create_student_passed("456"));
		Course.setStudent_reg(Course.getCourses(), "A Machine Learning",Course.create_student_registered(""));
		Course.setStudent_reg(Course.getCourses(), "A Computer Networks",Course.create_student_registered(""));

		Course.setTutor(Course.getCourses(), "A Machine Learning", "Mehmet Gönen", "MoWe 14.00");
		Course.setEquipments(Course.getCourses(), "A Machine Learning",Course.create_equipments("Laptop"));
		
		
		
		/*
		 * You can see the code for updating course features such as registered student, tutors of lectures ext. below:
		 */

		/*
		 * Updating new lectures:
		 */
		try (BufferedReader reader = new BufferedReader(new FileReader("newlectures.txt"))) {
			String lines;
		     while ((lines = reader.readLine()) != null) {
		    	 String[] linesplitted = lines.split(",");
		    	 if (linesplitted.length != 7) {
		    		 Course.setLectures(linesplitted[0], Course.create_prerequisities(linesplitted[1]), Course.create_student_passed(linesplitted[2]), Course.create_student_registered(linesplitted[3]), Course.getStringlist(), Course.getStringlist());
			 		Course.setTutor(Course.getCourses(), linesplitted[0], linesplitted[4],linesplitted[5] );
			 			
		    	 }else {
		    		 Course.setLectures(linesplitted[0], Course.create_prerequisities(linesplitted[1]), Course.create_student_passed(linesplitted[2]), Course.create_student_registered(linesplitted[3]), Course.getStringlist(), Course.create_equipments(linesplitted[6]));
			 			Course.setTutor(Course.getCourses(), linesplitted[0], linesplitted[4],linesplitted[5] );
			 			
		    	 }
		 			
		    } 
					
		}catch (IOException e) {
		      e.printStackTrace();
		    }
		
		
		
		/*
		 * Updating prerequisities of lectures:
		 */
		try (BufferedReader reader = new BufferedReader(new FileReader("newprereqs.txt"))) {
			String lines;
		     while ((lines = reader.readLine()) != null) {

					for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {

						if (lines.split(",")[1].contains(entry.getKey())) {							
							Course.getCourses().get(entry.getKey()).get(0).add(lines.split(",")[0]);

						}else {
							continue;
						}

					}
		    } 
					
		}catch (IOException e) {
		      e.printStackTrace();
		    }
		
		
		/*
		 * Updating passed students:
		 */
		try (BufferedReader reader = new BufferedReader(new FileReader("newpassed.txt"))) {
			String lines;
		     while ((lines = reader.readLine()) != null) {

					for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {

						if (lines.split(",")[1].contains(entry.getKey())) {							
							Course.getCourses().get(entry.getKey()).get(1).add(lines.split(",")[0]);

						}else {
							continue;
						}

					}
		    } 
					
		}catch (IOException e) {
		      e.printStackTrace();
		    }
		
		/*
		 * Updating registered students:
		 */
		try (BufferedReader reader = new BufferedReader(new FileReader("newregisters.txt"))) {
			String lines;
		     while ((lines = reader.readLine()) != null) {

					for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {

						if (lines.split(",")[1].contains(entry.getKey())) {							
							Course.getCourses().get(entry.getKey()).get(2).add(lines.split(",")[0]);

						}else {
							continue;
						}

					}
		    } 
					
		}catch (IOException e) {
		      e.printStackTrace();
		    }
		
		/*
		 * Updating tutors of lectures:
		 */
		try (BufferedReader reader = new BufferedReader(new FileReader("tutoraddlecture.txt"))) {
			String lines;
		     while ((lines = reader.readLine()) != null) {

					for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {

						if (lines.split(",")[0].contains(entry.getKey())) {	
							Course.getCourses().get(entry.getKey()).get(3).add(lines.split(",")[1]+":"+lines.split(",")[2]);

						}else {
							continue;
						}

					}
		    } 
					
		}catch (IOException e) {
		      e.printStackTrace();
		    }
		
		/*
		 * Updating equipments of lectures:
		 */
		
		try (BufferedReader reader = new BufferedReader(new FileReader("newequipments.txt"))) {
			String lines;
		     while ((lines = reader.readLine()) != null) {

					for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : Course.getCourses().entrySet()) {

						if (lines.split(",")[1].contains(entry.getKey())) {							
							Course.getCourses().get(entry.getKey()).get(4).add(lines.split(",")[0]);

						}else {
							continue;
						}

					}
		    } 
					
		}catch (IOException e) {
		      e.printStackTrace();
		    }
		 
		
		
		////////////////////////////////////////////////////////////////
		/*
		 * for demonstration purposes
		
		System.out.println(Course.getCourses());
		
		System.out.println(Course.getCourses().get("Computer Networks"));
		System.out.println(Course.getCourses().get("Machine Learning"));
		 */
		}

}
