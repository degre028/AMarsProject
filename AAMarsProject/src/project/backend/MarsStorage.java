package project.backend;

import java.util.LinkedList;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;

/**
 * This class is responsible for storing data into text files and reading them
 * from textfiles.
 * @author Rob
 *
 */
public class MarsStorage {

	ModuleSet modset;
	Storage localStorage;
	boolean localFail = false;
	
	LinkedList<String> modString = new LinkedList<String>();
	Integer modsetCounter = 0;
	boolean goAgain = true;
	
	long tendaytime = 0;
	
	/**
	 * The constructor simply gets the filename for the storage class.
	 * @param filename. The file to work on
	 */
	public MarsStorage(ModuleSet mset) {
		modset = mset;
		localStorage = Storage.getLocalStorageIfSupported();
		
		if (localStorage == null) {
			localFail = true;
		}
		
	}
	
	/**
	 * This method reads from the HTML file for sample data.
	 * @return modset. Returns a configuration class representing the list of modules.
	 */
	public void readServerHtml() {
		
		LinkedList<MarsModule> modlist = new LinkedList<MarsModule>();
		//Configuration config = new Configuration(modset,modlist);

		 Dictionary d;
		
		 d = Dictionary.getDictionary("dataset");
		 int numbmods = Integer.parseInt(d.get("number"));
		
		try {
			 for (int i = 1; i <= numbmods; i++) {
			 d = Dictionary.getDictionary("module"+i);
			 
			 Integer modID = Integer.parseInt(d.get("code"));
			 String modCond = d.get("status");
			 Integer modOri = Integer.parseInt(d.get("turns"));
			 Integer modX = Integer.parseInt(d.get("X"));
			 Integer modY = Integer.parseInt(d.get("Y"));
			 
			 modset.addModule(new MarsModule(modX, modY, modID, modCond, modOri));
			 
			 
			 }
			} 
			catch (Exception e) {
				Window.alert("Bad File Read! " + e.getMessage());			
			}
		
		
	}
	
	/**
	 * This method writes the contents of the modules to the local store.
	 */
	public void saveToLocalStore() {
		
		
		try {
		 if (!localFail) {
			 
			 StringBuilder mainSave = new StringBuilder();

			 mainSave.append("[");
			 
			 Integer i;
			 
			 for (i = 0; i < modset.getCount("all"); i++) {
				 
				 StringBuilder modString = new StringBuilder();
				 MarsModule mod = modset.getModule(i);
				 
				 modString.append("{code:");
				 modString.append(mod.getID());
				 modString.append(",status:\"");
				 modString.append(mod.getCondition());
				 modString.append("\",turns:");
				 modString.append(mod.getOrientation());
				 modString.append(",X:");
				 modString.append(mod.getX());
				 modString.append(",Y:");
				 modString.append(mod.getY());
				 modString.append("}");
				 
				 mainSave.append(modString);
				 
				 if(i != modset.getCount("all")-1) {
					 mainSave.append(",");
				 }
				 
			 }
			 mainSave.append("]");
			 
//			String sAll = "[" +
//						 "{id:091,status:\"undamaged\",turns:0,X:5,Y:5}," +
//						 "{code:002,status:\"undamaged\",turns:0,X:5,Y:6}," +
//						 "{code:003,status:\"undamaged\",turns:0,X:5,Y:7}" +
//						 "]";

			 //Write to local storage.
			 localStorage.setItem("number",i.toString());
			 localStorage.setItem("config0", mainSave.toString());
		 
		 }
		}
		catch (Exception e) {
			 Window.alert(e.getMessage());
		}
		
	}
	
	/**
	 * This method reads in the data from the local store
	 * and adds it to the modset.
	 */
	public void loadLocalStore() {

		
		Integer i = 0;
		
		JSONArray jA;
		JSONNumber jN;
		JSONString jS;
		JSONObject jO;
		try {
		
			 //localStorage = Storage.getLocalStorageIfSupported();
			tendaytime = Long.parseLong(localStorage.getItem("tendaytime"));
			 String sConfigOne = localStorage.getItem("config1");
			 jA = (JSONArray)JSONParser. parseLenient(sConfigOne);
		

		
		if (!localFail) {
			
			for (i = 0; i < jA.size(); i++) {
				try {

			
					jO = (JSONObject)jA.get(i);
					
					jN = (JSONNumber) jO.get("code");
					Integer modID = (int) jN.doubleValue();
							
					jS = (JSONString) jO.get("status");
					String modCond = jS.stringValue();				
					
					jN = (JSONNumber) jO.get("turns");
					Integer modOri = (int) jN.doubleValue();

					jN = (JSONNumber) jO.get("X");
					Integer modX = (int) jN.doubleValue();
					
					jN = (JSONNumber) jO.get("Y");
					Integer modY = (int) jN.doubleValue();
					
					MarsModule newModule = new MarsModule(modX, modY, modID, modCond, modOri);
					modset.addModule(newModule);
					
				}
				catch (JSONException ex) {
					
					Window.alert(ex.getMessage() + "\n" + i.toString() + "\n in loop");
				}
				
				
				
			}
			
		} 
		
		}catch (Exception ex) {
			//Window.alert(ex.getMessage());
		}
			
		
		
		
	}
	
	
	/**
	 * This method loads data from remote locations
	 * Test cases (Use Case #1)
	 */
	public void loadTestData() {
		int i = 0;
		
		while(i < 20) {
			String proxy = "http://www.d.umn.edu/~degre028/Proxy.php?url=";
			String url = proxy+"http://www.d.umn.edu/~abrooks/SomeTests.php?q=" + i;
			url = URL.encode(url);
			
			// Send request to server and catch any errors.
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
			
			try {
			 Request request = builder.sendRequest(null, new RequestCallback() {
				 public void onError(Request request, Throwable exception) {
					 Window.alert("onError: Couldn't retrieve JSON");
					 
				 }
			 public void onResponseReceived(Request request, Response response) {
				 if (200 == response.getStatusCode()) {
					 String rt = response.getText();
					 addModsetString(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
				 } else {
//					 Window. alert("Couldn't retrieve JSON (" + response.getStatusCode()
//							 + ")\n" + "Modules Unchanged" + goAgain);
				 }
			 	}
			 	} ) ;
			 
			} catch (RequestException e) {
			 Window. alert("RequestException: Couldn't retrieve JSON");
			 goAgainOff();
			}
			i++;
		}
	}
	
	/**
	 * This method adds a string to the linked list
	 * containing a set of modules loaded from the PHP
	 * proxy.
	 */
	private synchronized void addModsetString(String rt) {
		if (rt.length() < 5) {
			// Do nothing.
		} else {
			modsetCounter++;
			modString.add(rt);
		}
		
	}
	
	/**
	 * This method takes in an integer and loads the appropriate 
	 * test data.
	 */
	public void updateModSet(int i) {

		i++;
		//ignore the string linked list to deal with asynch and pull again.
		
		String proxy = "http://www.d.umn.edu/~degre028/Proxy.php?url=";
		String url = proxy+"http://www.d.umn.edu/~abrooks/SomeTests.php?q=" + i;
		url = URL.encode(url);
		
		// Send request to server and catch any errors.
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		
		try {
		 Request request = builder.sendRequest(null, new RequestCallback() {
			 public void onError(Request request, Throwable exception) {
				 Window.alert("onError: Couldn't retrieve JSON");
				 
			 }
		 public void onResponseReceived(Request request, Response response) {
			 if (200 == response.getStatusCode()) {
				 String rt = response.getText();
				 updateSecondCall(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
			 } else {
//				 Window. alert("Couldn't retrieve JSON (" + response.getStatusCode()
//						 + ")\n" + "Modules Unchanged" + goAgain);
			 }
		 	}
		 	} ) ;
		 
		} catch (RequestException e) {
		 Window. alert("RequestException: Couldn't retrieve JSON");
		 goAgainOff();
		}


			
	}
	
	private void updateSecondCall(String sConfig) {
		
		JSONArray jA;
		JSONNumber jN;
		JSONString jS;
		JSONObject jO;
		
		jA = (JSONArray)JSONParser. parseLenient(sConfig);
		
		for (int i = 0; i < jA.size(); i++) {
			jO = (JSONObject)jA.get(i);
			
			jN = (JSONNumber) jO.get("code");
			Integer modID = (int) jN.doubleValue();
					
			jS = (JSONString) jO.get("status");
			String modCond = jS.stringValue();				
			
			jN = (JSONNumber) jO.get("turns");
			Integer modOri = (int) jN.doubleValue();
	
			jN = (JSONNumber) jO.get("X");
			Integer modX = (int) jN.doubleValue();
			
			jN = (JSONNumber) jO.get("Y");
			Integer modY = (int) jN.doubleValue();
			
			MarsModule newModule = new MarsModule(modX, modY, modID, modCond, modOri);
			modset.addModule(newModule);
		}
	}
	
	/**
	 * Method for switching off the goAgain flag.
	 */
	private void goAgainOff() {
		goAgain = false;
	}

	
	public Integer getModsetCounter() {
		return modString.size();
	}
	
	public long getTenDayTime() {
		return tendaytime;
	}
	
	public void saveTenDayTime(long time) {
		 localStorage.setItem("tendaytime", String.valueOf(time));
	}
	
	/**
	 * This method saves configuration data to the local store.
	 */
	public void saveConfigLocalStore() {
		try {
//			for(int i = 1; i <= modset.getConfigNumber() + 1; i++) {
//				localStorage.removeItem("config"+i);
//			}
			
			
			 if (!localFail) {
				 
				 StringBuilder mainSave = new StringBuilder();


				 
				 Integer i;
				 
				 StringBuilder configString = new StringBuilder();
				 
				 for (i = 1; i <= modset.getConfigNumber(); i++) {
					 
					 mainSave.append("[");
					 
					 MarsConfiguration config = modset.getConfig(i-1);
					 
					 for (int j = 0; j < modset.getCount("all"); j++) {
						 
						 configString.append("{X:");
						 configString.append(config.getXCoord(j));
						 configString.append(",Y:");
						 configString.append(config.getYCoord(j));
						 configString.append("}");
						 
						 mainSave.append(configString);
						 
						 if(j != modset.getCount("all")-1) {
							 mainSave.append(",");
						 }
						 
					 }	 

					 mainSave.append("]");
					 localStorage.setItem("config" + i, mainSave.toString());
				 }

//				String sAll = "[" +
//							 "{id:091,status:\"undamaged\",turns:0,X:5,Y:5}," +
//							 "{code:002,status:\"undamaged\",turns:0,X:5,Y:6}," +
//							 "{code:003,status:\"undamaged\",turns:0,X:5,Y:7}" +
//							 "]";

				 //Write to local storage.
				 //localStorage.setItem("number",i.toString());

			 
			 }
			}
			catch (Exception e) {
				 Window.alert(e.getMessage());
			}
	}
}
