package main;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class course {
	/*
	 * This class is for creating course hashmap and providing necessary methods for setting default features of lectures.
	 * Courses hashmap map course name to the lecture arraylist
	 * Each lecture arraylist stores the features of lectures that are stored in a arraylist.
	 */
	public HashMap<String,ArrayList<ArrayList<String>>> courses; 
	/*
	 * For debugging purposes; it provides empty arraylist
	 */
	public ArrayList<String> stringlist = new ArrayList<>();

	public course() {
		courses = new HashMap<String,ArrayList<ArrayList<String>>>(); 
		
	}
	
	public void setCourses(HashMap<String, ArrayList<ArrayList<String>>> courses) {
		this.courses = courses;
	}


	public void setStringlist(ArrayList<String> stringlist) {
		this.stringlist = stringlist;
	}

	public HashMap<String, ArrayList<ArrayList<String>>> getCourses() {
		return courses;
	}

	
	public ArrayList<String> getStringlist() {
		return stringlist;
	}

	/*
	 * Sets default features of lectures
	 */
	public void setLectures(String lecturename, ArrayList<String> prerequisities,
			ArrayList<String> student_passed, ArrayList<String> student_registered,ArrayList<String> tutors,
			ArrayList<String> equipments) {
	
		ArrayList<ArrayList<String>> lecture = new ArrayList<ArrayList<String>>();
		courses.put(lecturename, lecture);
		lecture.add(prerequisities);//
		lecture.add(student_passed);//
		lecture.add(student_registered);//
		lecture.add(tutors);
		lecture.add(equipments);
	}

	/*
	 * implementing prerequisities to a lecture
	 */
	public static ArrayList<String> create_prerequisities(String prerequisities ) {
		
		
		String[] prereqlist = prerequisities.split(",");

		ArrayList<String> prereq = new ArrayList<>(Arrays.asList(prereqlist));
		
		return prereq;
		
	}
	public static void setPrerequisities(HashMap<String,ArrayList<ArrayList<String>>> courses,String lecturename,ArrayList<String> prerequisities ) {
		
		courses.get(lecturename).set(0, prerequisities);
			
	}
	/*
	 * implementing passed student to a lecture
	 */
	public static ArrayList<String> create_student_passed(String student_passed ) {
		
		
		String[] stulist = student_passed.split(",");

		ArrayList<String> studentpassedlist = new ArrayList<>(Arrays.asList(stulist));
		
		return studentpassedlist;
		
	}
	public static void setStudent_passed(HashMap<String,ArrayList<ArrayList<String>>> courses,String lecturename,ArrayList<String> student_passed ) {
		
		courses.get(lecturename).set(1, student_passed);
			
	}
	
	 /*
	  * Implementing registered students to lecture
	  */

	public static ArrayList<String> create_student_registered(String student_reg ) {
		
		
		String[] stulist = student_reg.split(",");

		ArrayList<String> studentreglist = new ArrayList<>(Arrays.asList(stulist));
		
		return studentreglist;
		
	}
	public static void setStudent_reg(HashMap<String,ArrayList<ArrayList<String>>> courses,String lecturename,ArrayList<String> student_reg ) {
		
		courses.get(lecturename).set(2, student_reg);
			
	}
	
	 /*
	  * Implementing tutors to lecture
	  */
	
	public void setTutor(HashMap<String,ArrayList<ArrayList<String>>> courses,String lecturename,String tutorname,String timeinfo) {
		
		String tutorinfo = tutorname+":"+timeinfo;
		
		ArrayList<String> tutor = new ArrayList<>(Arrays.asList(tutorinfo));
		
		courses.get(lecturename).set(3,tutor);
		
	}
	
	 /*
	  * Implementing default equipments of lecture
	  */
	
	public static ArrayList<String> create_equipments(String equipment) {
		
		
		String[] equlist = equipment.split(",");
		
		ArrayList<String> equipmentlist = new ArrayList<>(Arrays.asList(equlist));
		
		return equipmentlist;
		
	}
	
	public static void setEquipments(HashMap<String,ArrayList<ArrayList<String>>> courses,String lecturename,ArrayList<String> equipments ) {
		
		courses.get(lecturename).set(4, equipments);
			
	}
	
	
}
