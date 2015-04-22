package project.backend;

import java.util.LinkedList;

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
		Configuration config = new Configuration();
		LinkedList<MarsModule> modlist = new LinkedList<MarsModule>();
		

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
			 localStorage.setItem("config1", mainSave.toString());
		 
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
	
	
	
	
}
