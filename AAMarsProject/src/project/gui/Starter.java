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
		//launchSomeConfigs();
		launchFullConfigs();
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
		
		modset.addModule(new MarsModule(45,50,2,"Good",0));		//Plain
		modset.addModule(new MarsModule(46,49,3,"Good",0)); 	//Plain
		modset.addModule(new MarsModule(43,48,4,"Good",0)); 	//Plain
		modset.addModule(new MarsModule(46,47,70,"Good",0)); 	//Dormitory
		modset.addModule(new MarsModule(46,46,95,"Good",0));	//Sanitation
		modset.addModule(new MarsModule(46,45,141,"Good",0));	//Canteen
		modset.addModule(new MarsModule(40,44,154,"Good",0));	//Power
		modset.addModule(new MarsModule(41,43,164,"Good",0));	//Control
		modset.addModule(new MarsModule(41,42,174,"Good",0));	//Airlock
		modset.addModule(new MarsModule(41,41,111,"Good",0));	//Food and Water
		modset.addModule(new MarsModule(41,40,181,"Good",0));	//Food and Water
		
		modset.getGui().updateCanvasArea();
		
		RootPanel.get().add(modset.getGui());
	}
	private void launchFullConfigs() {
		final ModuleSet modset = new ModuleSet("Minimum",graphics);
		
		modset.addModule(new MarsModule(45,1,1,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,2,2,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,3,3,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,4,4,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,5,5,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,6,40,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,7,6,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,8,7,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,9,8,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,10,9,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,11,10,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,12,11,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,13,12,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,14,13,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,15,14,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,16,15,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,17,16,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,18,17,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,19,18,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,20,19,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,21,20,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,22,21,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,23,22,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,24,23,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,25,24,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,26,25,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,27,26,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,28,27,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,29,28,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,30,29,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,31,30,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,32,31,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,33,32,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,34,33,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,35,34,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,36,35,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,37,36,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,38,37,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,39,38,"Good",0));		//Plain
		modset.addModule(new MarsModule(45,40,39,"Good",0));		//Plain
		modset.addModule(new MarsModule(1,10,61,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,11,62,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,12,63,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,13,64,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,14,65,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,15,66,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,16,67,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,17,68,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,18,69,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,19,70,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,20,71,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,21,72,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,22,73,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,23,74,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,24,75,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,25,76,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,26,77,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,27,78,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,28,79,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(1,29,80,"Good",0));		//Dormitory
		modset.addModule(new MarsModule(2,1,91,"Good",0));		//Sanitation
		modset.addModule(new MarsModule(2,2,92,"Good",0));		//Sanitation
		modset.addModule(new MarsModule(2,3,93,"Good",0));		//Sanitation
		modset.addModule(new MarsModule(2,4,94,"Good",0));		//Sanitation
		modset.addModule(new MarsModule(2,5,95,"Good",0));		//Sanitation
		modset.addModule(new MarsModule(2,6,96,"Good",0));		//Sanitation
		modset.addModule(new MarsModule(2,7,97,"Good",0));		//Sanitation
		modset.addModule(new MarsModule(2,8,98,"Good",0));		//Sanitation
		modset.addModule(new MarsModule(2,9,99,"Good",0));		//Sanitation
		modset.addModule(new MarsModule(2,10,100,"Good",0));	//Sanitation
		modset.addModule(new MarsModule(3,1,111,"Good",0));	//Food & Water
		modset.addModule(new MarsModule(3,2,112,"Good",0));	//Food & Water
		modset.addModule(new MarsModule(3,3,113,"Good",0));	//Food & Water
		modset.addModule(new MarsModule(3,4,114,"Good",0));	//Food & Water
		modset.addModule(new MarsModule(3,5,115,"Good",0));	//Food & Water
		modset.addModule(new MarsModule(3,6,116,"Good",0));	//Food & Water
		modset.addModule(new MarsModule(3,7,117,"Good",0));	//Food & Water
		modset.addModule(new MarsModule(3,8,118,"Good",0));	//Food & Water
		modset.addModule(new MarsModule(3,9,119,"Good",0));	//Food & Water
		modset.addModule(new MarsModule(3,10,120,"Good",0));//Food & Water
		modset.addModule(new MarsModule(4,1,131,"Good",0));//Gym & Relaxation
		modset.addModule(new MarsModule(4,2,132,"Good",0));//Gym & Relaxation
		modset.addModule(new MarsModule(4,3,133,"Good",0));//Gym & Relaxation
		modset.addModule(new MarsModule(4,4,134,"Good",0));//Gym & Relaxation
		modset.addModule(new MarsModule(5,1,141,"Good",0));//Canteen
		modset.addModule(new MarsModule(5,2,142,"Good",0));//Canteen
		modset.addModule(new MarsModule(5,3,143,"Good",0));//Canteen
		modset.addModule(new MarsModule(5,4,144,"Good",0));//Canteen
		modset.addModule(new MarsModule(6,1,151,"Good",0));//Power
		modset.addModule(new MarsModule(6,2,152,"Good",0));//Power
		modset.addModule(new MarsModule(6,3,153,"Good",0));//Power
		modset.addModule(new MarsModule(6,4,154,"Good",0));//Power
		modset.addModule(new MarsModule(7,1,161,"Good",0));//Control
		modset.addModule(new MarsModule(7,2,162,"Good",0));//Control
		modset.addModule(new MarsModule(7,3,163,"Good",0));//Control
		modset.addModule(new MarsModule(7,4,164,"Good",0));//Control
		modset.addModule(new MarsModule(8,1,171,"Good",0));//Airlock
		modset.addModule(new MarsModule(8,2,171,"Good",0));//Airlock
		modset.addModule(new MarsModule(8,3,171,"Good",0));//Airlock
		modset.addModule(new MarsModule(8,4,171,"Good",0));//Airlock
		modset.addModule(new MarsModule(9,1,181,"Good",0));//Medical
		modset.addModule(new MarsModule(9,2,182,"Good",0));//Medical
		modset.addModule(new MarsModule(9,3,183,"Good",0));//Medical
		modset.addModule(new MarsModule(9,4,184,"Good",0));//Medical
		
		
		modset.getGui().updateCanvasArea();
		
		RootPanel.get().add(modset.getGui());
	}
}
