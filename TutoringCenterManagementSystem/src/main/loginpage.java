package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class loginpage extends JFrame implements ActionListener{
	
	static private JButton button;
	static private JButton button2;
	static private JLabel label,label2;
	static JPanel panellogin,panelcheck;
	protected static JTextField type,password;
	
	
	public  loginpage() {
		super("Log-in Page");		
		
				
		/*
		 * I am creating the log-in page and
		 * implementing the sign-up page into log-in page
		 * Labels and buttons required implemented below:
		 */
		signuppage signup = new signuppage();

		panellogin = new JPanel();
		panellogin.setLayout(null);

		label = new JLabel("Type of your account:");
		button = new JButton("Log-in");
		button2 = new JButton("Sign-up");
		
		label.setBounds(100, 100, 150, 20);
		panellogin.add(label);	
		
		type = new JTextField();
		type.setBounds(100, 125, 150, 20);
		panellogin.add(type);
		
		label2 = new JLabel("Your Id:");
		label2.setBounds(100, 150, 150, 20);
		panellogin.add(label2);

		password = new JTextField();
		password.setBounds(100, 175, 150, 20);
		panellogin.add(password);
		
		
		/*
		 * By log-in button,
		 * We are able to determine whose account is logged-in by reading theirs information from the registrarinfo.txt:
		 */
		button.setBounds(100, 220, 150, 40);
		button.setForeground(Color.BLACK);
		button.setBackground(Color.WHITE);
		button.addActionListener(
				new ActionListener() 
				{ 
			@Override
				public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> registrar = new ArrayList<String>();
				int success = 0;
				/*
				 * Deriving registrar information from sign-up page
				 */
				try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"))) {
					String line;
				      while ((line = reader.readLine()) != null) {
				    	  registrar.add(line);
				}
				    } catch (IOException e) {
				      e.printStackTrace();
				    }
				
				/*
				 * Based on the information derived from type and password, we are reaching the spesified tab.
				 */
				for (String line: registrar) {
					if (line.split(",")[0].contains(password.getText()) && line.split(",")[1].contains(type.getText())) {
						JOptionPane.showMessageDialog(null, "Login Successful");
						
						if (type.getText().contains("student")) {
							student.main();
						}else if (type.getText().contains("tutorA")||type.getText().contains("tutorB")) {
							tutor.main();
						}else if (type.getText().contains("admin")) {
							administrator.main();
						}
						success = 1;
						break;
					}
				}
				if (success == 0) {
					JOptionPane.showMessageDialog(null, "Username or Password mismatch ");
				}
				
				
			}
		});
		
		panellogin.add(button);

		/*
		 * Opening the sign-up page
		 */
		button2.setBounds(130, 265, 100, 20);
		button2.setForeground(Color.WHITE);
		button2.setBackground(Color.BLACK);
		button2.addActionListener(
				new ActionListener()
				{ 
			@Override
				public void actionPerformed(ActionEvent arg0) {
				
				signup.add(signup.panelsignup);
				signup.setSize(400,400);
				signup.setVisible(true);
				signup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
		});

		panellogin.add(button2);
		
			
	}
	
	
	/*
	 * Method for reaching student page from another tabs (Admin,tutor)
	 */
	public static void checkstu() {	
		
		JFrame frame = new JFrame();

		panelcheck = new JPanel();
		panelcheck.setLayout(null);

		button = new JButton("Check Student");

		type = new JTextField("student");
		type.setBounds(100, 250, 193, 28);
		panellogin.add(type);
		
		password = new JTextField("Student Id");
		password.setBounds(95, 25, 70, 20);
		panelcheck.add(password);
		
		button.setBounds(65, 55 , 150, 30);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.addActionListener(
				new ActionListener() 
				{ 
			@Override
				public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> registrar = new ArrayList<String>();
				/*
				 * Deriving registrar information from sign-up page
				 */
				try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"))) {
					String line;
				      while ((line = reader.readLine()) != null) {
				    	  registrar.add(line);
				}
				    } catch (IOException e) {
				      e.printStackTrace();
				    }
				
				for (String line: registrar) {
					if (line.split(",")[0].contains(password.getText())) {
						JOptionPane.showMessageDialog(null, "Login Successful");
						
						studentcheck.main();
						frame.setVisible(false);

						break;
					}
				}
			
				
			}
		});
		
		panelcheck.add(button);

		frame.add(panelcheck);
		frame.setSize(300,150);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
			
			
	}
	
	
	/*
	 * Method for reaching tutor page from another administrator tab
	 */
	public static void checktutor() {	
		JFrame frame = new JFrame();

		panelcheck = new JPanel();
		panelcheck.setLayout(null);

		button = new JButton("Check Tutor");

		type = new JTextField("tutorA");
		type.setBounds(100, 250, 193, 28);
		panellogin.add(type);
		
		password = new JTextField("Tutor Id");
		password.setBounds(95, 25, 70, 20);
		panelcheck.add(password);
		
		button.setBounds(65, 55 , 150, 30);
		button.setForeground(Color.BLACK);
		button.setBackground(Color.WHITE);
		button.addActionListener(
				new ActionListener() // anonymous inner class
				{ 
			@Override
				public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> registrar = new ArrayList<String>();
				/*
				 * Deriving registrar information from sign-up page
				 */
				try (BufferedReader reader = new BufferedReader(new FileReader("registrarinfo.txt"))) {
					String line;
				      while ((line = reader.readLine()) != null) {
				    	  registrar.add(line);
				}
				    } catch (IOException e) {
				      e.printStackTrace();
				    }
				
				for (String line: registrar) {
					if (line.split(",")[0].contains(password.getText())) {
						JOptionPane.showMessageDialog(null, "Login Successful");
						
						tutorcheck.main();
						frame.setVisible(false);

						break;
					}
				}
			
				
			}
		});
		
		panelcheck.add(button);

		frame.add(panelcheck);
		frame.setSize(300,150);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
			
			
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {		
	}
	
}
