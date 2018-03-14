package org.app.data.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.app.data.entities.Subforum;
import org.app.data.entities.Subject;
import org.app.data.user_data.Complaint;
import org.app.data.user_data.User;

public class Database {

	//strukture podataka koje predstavljaju bazu
	public static HashMap<String, User> users;
	public static HashMap<String, Subforum> forums;
	public static HashMap<Subject, Subforum> subjects;
	public static HashMap<Subject, Long> commentIds;
	public static ArrayList<Complaint> complaints;
	public static String servletPath;
	
	//citanje podataka iz fajla
	@SuppressWarnings("unchecked")
	public static void loadData(String path){
		
		servletPath = path;
		
		ObjectInputStream inU = null;
		try {
			inU = new ObjectInputStream(new FileInputStream(new File(servletPath+"/data/users.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		if(inU!=null){
			try {
				try {
					users = (HashMap<String, User>) inU.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} finally{
				try{
					inU.close();
				}catch(IOException e){
					
				}
			}
		}
		
		ObjectInputStream inF = null;
		try {
			inF = new ObjectInputStream(new FileInputStream(new File(servletPath+"/data/forums.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if(inF!=null){
			try {
				forums = (HashMap<String, Subforum>) inF.readObject();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try{
					inF.close();
				}catch(IOException e){
					
				}
			}
		}
		
		ObjectInputStream inS = null;
		try {
			inS = new ObjectInputStream(new FileInputStream(new File(servletPath+"/data/subjects.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if(inS!=null){
			try {
				try {
					subjects = (HashMap<Subject, Subforum>) inS.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} finally{
				try{
					inS.close();
				}catch(IOException e){
					
				}
			}
		}
		
		ObjectInputStream inC = null;
		try {
			inC = new ObjectInputStream(new FileInputStream(new File(servletPath+"/data/comments.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if(inC!=null){
			try {
				try {
					commentIds = (HashMap<Subject, Long>) inC.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} finally{
				try{
					inC.close();
				}catch(IOException e){
					
				}
			}
		}
		
		ObjectInputStream inM = null;
		try {
			inM = new ObjectInputStream(new FileInputStream(new File(servletPath+"/data/complaints.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		if(inM!=null){
			try {
				try {
					complaints = (ArrayList<Complaint>) inM.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} finally{
				try{
					inM.close();
				}catch(IOException e){
					
				}
			}
		}
		
		if(users==null){
			users = new HashMap<String, User>();
			User u = new User();
			u.setType("Administrator");
			u.setEmail("admin@mwa.com");
			u.setName("Admin");
			u.setSurname("Admin");
			u.setUsername("administrator");
			u.setPassword("admin");
			u.setRegistrationDate(new Date().toString());
			u.setPhoneNum("556685156533");
			users.put(u.getUsername(), u);
		}

		if(forums==null){
			forums = new HashMap<String, Subforum>();
		}
		
		if(subjects==null){
			subjects = new HashMap<Subject, Subforum>();
		}
		
		if(commentIds==null){
			commentIds = new HashMap<>();
		}
		
		if(complaints==null){
			complaints = new ArrayList<>();
		}
	}
	
	
	//cuvanje podataka
	public static void saveData(){
		
		ObjectOutputStream outU = null;
		try {
			outU = new ObjectOutputStream(new FileOutputStream(new File(servletPath+"/data/users.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(outU!=null){
			try {
				outU.writeObject(users);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try{
					outU.close();
				}catch(IOException e){
					
				}
			}
		}
		
		ObjectOutputStream outF = null;
		try {
			outF = new ObjectOutputStream(new FileOutputStream(new File(servletPath+"/data/forums.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(outF!=null){
			try {
				outF.writeObject(forums);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try{
					outF.close();
				}catch(IOException e){
					
				}
			}
		}
		
		ObjectOutputStream outS = null;
		try {
			outS = new ObjectOutputStream(new FileOutputStream(new File(servletPath+"/data/subjects.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(outS!=null){
			try {
				outS.writeObject(subjects);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try{
					outS.close();
				}catch(IOException e){
					
				}
			}
		}
		
		ObjectOutputStream outC = null;
		try {
			outC = new ObjectOutputStream(new FileOutputStream(new File(servletPath+"/data/comments.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(outC!=null){
			try {
				outC.writeObject(commentIds);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try{
					outC.close();
				}catch(IOException e){
					
				}
			}
		}
		
		ObjectOutputStream outM = null;
		try {
			outM = new ObjectOutputStream(new FileOutputStream(new File(servletPath+"/data/complaints.dat")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(outM!=null){
			try {
				outM.writeObject(complaints);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try{
					outM.close();
				}catch(IOException e){
					
				}
			}
		}
	}
	
}
