package project.gui;

import project.backend.MarsConfiguration;
import project.backend.MarsModule;
import project.backend.ModuleSet;
import project.backend.Passwd;
import project.backend.MarsStorage;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

import project.backend.GraphicPack;
/**
 * This class is responsible for handling the start of the program.
 * It's startup method is the only thing called in onModuleLoad().
 * @author Rob
 *
 */
public class Starter extends FlowPanel {
	final FlowPanel mainPanel = new FlowPanel();
	
	final GraphicPack graphics = new GraphicPack();
	
	final ModuleSet modset = new ModuleSet("default",graphics);

	final Login log1 = new Login(new Passwd(), mainPanel, modset);
	
	/**
	 * Starter constructor.  It begins the process of creating
	 * desired system setup.
	 */
	public Starter() {


		try {
		//launchLoginDemo();
		//launchSkipLogin();
		//launchSomeData();
		//launchMinConfig();
		//launchServerStorage();
		//launchLocalStorage();
		//launchRemoteStorage();
		launchSomeConfigs();
		} catch (Exception ex) {
			ex.printStackTrace();
			Window.alert(ex.getMessage() + "\n");
		}
	}
	
	
	/**
	 * Method called if the user wants to demo the program with full
	 * login capabilities.
	 */
	private void launchLoginDemo() {
		mainPanel.add(log1);
		modset.getStorage().loadLocalStore();
		RootPanel.get().add(mainPanel);
	}
	
	/**
	 * Method called if the user wants to demo the program with full
	 * login capabilities.
	 */
	private void launchSkipLogin() {
		final ModuleSet modset = new ModuleSet("demo",graphics);
		RootPanel.get().add(modset.getGui());
	}
	
	/**
	 * Method called if the user wants to have some bs data entered
	 * at the start
	 */
	private void launchSomeData() {
		final ModuleSet modset = new ModuleSet("demo",graphics);
		
		modset.addModule(new MarsModule(1,1,2,"Good",0));
		modset.addModule(new MarsModule(100,50,63,"Good",0));
		modset.addModule(new MarsModule(100,1,65,"Good",0));
		modset.addModule(new MarsModule(1,50,91,"Good",0));
		modset.addModule(new MarsModule(1,5,3,"Good",0));
		modset.addModule(new MarsModule(1,6,132,"Good",0));
		modset.addModule(new MarsModule(1,7,184,"Good",0));
		modset.addModule(new MarsModule(1,8,172,"Good",0));
		modset.addModule(new MarsModule(1,9,171,"Good",0));
		modset.addModule(new MarsModule(50,25,171,"Good",0));
		modset.addModule(new MarsModule(40,40,171,"Good",0));
		modset.addModule(new MarsModule(90,34,171,"Good",0));
		modset.addModule(new MarsModule(23,23,171,"Good",0));
		modset.addModule(new MarsModule(67,45,171,"Good",0));
			
		RootPanel.get().add(modset.getGui());

	}
	private void launchMinConfig() {
		final ModuleSet modset = new ModuleSet("Minimum",graphics);
		
		modset.addModule(new MarsModule(32,16,2,"Good",0));		//Plain
		modset.addModule(new MarsModule(88,61,3,"Good",0)); 	//Plain
		modset.addModule(new MarsModule(32,65,4,"Good",0)); 	//Plain
		modset.addModule(new MarsModule(102,18,70,"Good",0)); 	//Dormitory
		modset.addModule(new MarsModule(99,14,95,"Good",0));	//Sanitation
		modset.addModule(new MarsModule(1,19,141,"Good",0));	//Canteen
		modset.addModule(new MarsModule(55,5,154,"Good",0));	//Power
		modset.addModule(new MarsModule(100,100,164,"Good",0));	//Control
		modset.addModule(new MarsModule(32,17,174,"Good",0));	//Airlock
		modset.addModule(new MarsModule(32,17,111,"Good",0));	//Food and Water
		
		modset.getGui().updateCanvasArea();
		RootPanel.get().add(modset.getGui());

	}
	
	/**
	 * Method called if the user wants to test the dictionary reader.
	 */
	private void launchServerStorage() {
		final ModuleSet modset = new ModuleSet("demostorage",graphics);
		
		modset.getStorage().readServerHtml();
		
		RootPanel.get().add(modset.getGui());
	}
	
	/**
	 * Method called if the user wants to test the JSON reader.
	 * Uses local web storage
	 */
	private void launchLocalStorage() {
		final ModuleSet modset = new ModuleSet("demolocal",graphics);
		
		modset.getStorage().loadLocalStore();
		
		RootPanel.get().add(modset.getGui());
	}
	
	
	private void launchRemoteStorage() {
		final ModuleSet modset = new ModuleSet("demoremote",graphics);
		modset.getStorage().loadTestData();
		
		RootPanel.get().add(modset.getGui());
	}
	
	private void launchSomeConfigs() {
		final ModuleSet modset = new ModuleSet("Minimum",graphics);
		
		modset.addModule(new MarsModule(1,50,2,"Good",0));		//Plain
		modset.addModule(new MarsModule(1,49,3,"Good",0)); 	//Plain
		modset.addModule(new MarsModule(1,48,4,"Good",0)); 	//Plain
		modset.addModule(new MarsModule(1,47,70,"Good",0)); 	//Dormitory
		modset.addModule(new MarsModule(1,46,95,"Good",0));	//Sanitation
		modset.addModule(new MarsModule(1,45,141,"Good",0));	//Canteen
		modset.addModule(new MarsModule(1,44,154,"Good",0));	//Power
		modset.addModule(new MarsModule(1,43,164,"Good",0));	//Control
		modset.addModule(new MarsModule(1,42,174,"Good",0));	//Airlock
		modset.addModule(new MarsModule(1,41,111,"Good",0));	//Food and Water
		modset.addModule(new MarsModule(1,40,181,"Good",0));	//Food and Water
		
		modset.getGui().updateCanvasArea();
		try {
		MarsConfiguration configmin = new MarsConfiguration(modset);
		configmin.setXCoord(0, 10);
		configmin.setYCoord(0, 42);
		configmin.setXCoord(1, 9);
		configmin.setYCoord(1, 42);
		configmin.setXCoord(2, 11);
		configmin.setYCoord(2, 42);
		configmin.setXCoord(3, 8);
		configmin.setYCoord(3, 42);
		configmin.setXCoord(4, 12);
		configmin.setYCoord(4, 42);
		configmin.setXCoord(5, 9);
		configmin.setYCoord(5, 43);
		configmin.setXCoord(6, 10);
		configmin.setYCoord(6, 43);
		configmin.setXCoord(7, 11);
		configmin.setYCoord(7, 43);
		configmin.setXCoord(8, 9);
		configmin.setYCoord(8, 41);
		configmin.setXCoord(9, 10);
		configmin.setYCoord(9, 41);
		configmin.setName("Base Try 1");
		modset.newConfig(configmin);
		} catch (Exception ex) {
			Window.alert(ex.getMessage());
		}
		
		try {
		MarsConfiguration configmin = new MarsConfiguration(modset);
		configmin.setXCoord(0, 10);
		configmin.setYCoord(0, 32);
		configmin.setXCoord(1, 9);
		configmin.setYCoord(1, 32);
		configmin.setXCoord(2, 11);
		configmin.setYCoord(2, 32);
		configmin.setXCoord(3, 8);
		configmin.setYCoord(3, 32);
		configmin.setXCoord(4, 12);
		configmin.setYCoord(4, 32);
		configmin.setXCoord(5, 9);
		configmin.setYCoord(5, 33);
		configmin.setXCoord(6, 10);
		configmin.setYCoord(6, 33);
		configmin.setXCoord(7, 11);
		configmin.setYCoord(7, 33);
		configmin.setXCoord(8, 9);
		configmin.setYCoord(8, 31);
		configmin.setXCoord(9, 10);
		configmin.setYCoord(9, 31);
		configmin.setName("Base Try 2");
		modset.newConfig(configmin);
		} catch (Exception ex) {
			Window.alert(ex.getMessage());
		}

		
		RootPanel.get().add(modset.getGui());
	}
}
