package org.app.data.database.complaints;

import java.util.ArrayList;

import org.app.data.database.Database;
import org.app.data.user_data.Complaint;

public class DatabaseComplaintsService {

	//pribavljanje zalbi vezanih za odr korisnika iz baze
	public static ArrayList<Complaint> getUserComplaints(String username){
		
		ArrayList<Complaint> myComplaints = new ArrayList<Complaint>();
		
		for(int i=0; i<Database.complaints.size(); i++){
			Complaint ccc = Database.complaints.get(i);
			
			for(int j=0; j<ccc.getAuthorities().size(); j++){
				if(ccc.getAuthorities().get(j).getUsername().equals(username)){
					myComplaints.add(ccc);
					break;
				}
			}
		}
		
		return myComplaints;
	}
}
