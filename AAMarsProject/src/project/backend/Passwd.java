package project.backend;

import java.util.LinkedList;

public class Passwd {
	LinkedList<String> usernames = new LinkedList<String>();
	LinkedList<String> passwds = new LinkedList<String>();

	
	public Passwd(){
		usernames.add("adam");
		passwds.add("adamPass");

		usernames.add("will");
		passwds.add("willPass");

		usernames.add("rob");
		passwds.add("robPass");
		
		usernames.add("1");
		passwds.add("1");
		
		usernames.add("user");
		passwds.add("pass");
				
	}
	public boolean passChecker(String user, String pass){
		boolean useryes = false;
		boolean passyes = false;
		int index = 0;
		for(int i=0; i < usernames.size(); i++){
			if(usernames.get(i).equals(user)){
				useryes = true;
				index = i;
			}
		}
		
		if(passwds.get(index).equals(pass)){
			return true;
		} else {
			return false;
		}
	}
}
