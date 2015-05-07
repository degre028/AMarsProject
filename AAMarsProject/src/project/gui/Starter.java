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
		//launchSomeMaximumData();
		//launchMinConfig();
		//launchServerStorage();
		//launchLocalStorage();
		//launchRemoteStorage();
		launchSomeConfigs();
		//launchFullConfigs();


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
		//plain 1
		modset.addModule(new MarsModule(42, 42,2,"Good",0));
		modset.addModule(new MarsModule(43, 42,1,"Good",0));
		modset.addModule(new MarsModule(41, 42,1,"Good",0));		
		modset.addModule(new MarsModule(44, 42,1,"Good",0));
		modset.addModule(new MarsModule(40, 42,1,"Good",0));
		modset.addModule(new MarsModule(45, 42,1,"Good",0));		
		modset.addModule(new MarsModule(39, 42,1,"Good",0));
		modset.addModule(new MarsModule(46, 42,1,"Good",0));
		modset.addModule(new MarsModule(38, 42,1,"Good",0));		
		modset.addModule(new MarsModule(47, 42,1,"Good",0));
		//air
		modset.addModule(new MarsModule(48, 42,171,"Good",0));		
		modset.addModule(new MarsModule(37, 42,171,"Good",0));
		//plain 2
		modset.addModule(new MarsModule(38, 43,1,"Good",0));
		modset.addModule(new MarsModule(38, 44,1,"Good",0));
		modset.addModule(new MarsModule(38, 45,1,"Good",0));
		modset.addModule(new MarsModule(38, 46,1,"Good",0));		
		modset.addModule(new MarsModule(38, 47,1,"Good",0));
		
		//Dorms one
		modset.addModule(new MarsModule(37, 45,65,"Good",0));
		modset.addModule(new MarsModule(39, 45,65,"Good",0));
		modset.addModule(new MarsModule(37, 47,65,"Good",0));
		modset.addModule(new MarsModule(38, 48,65,"Good",0));
		//sanit
		modset.addModule(new MarsModule(37, 46,91,"Good",0));
		modset.addModule(new MarsModule(39, 47,92,"Good",0));
		//gym
		modset.addModule(new MarsModule(39, 46, 131,"Good",0));
		//food
		modset.addModule(new MarsModule(39, 43, 111,"Good",0));
		modset.addModule(new MarsModule(39, 44, 111,"Good",0));
		modset.addModule(new MarsModule(37, 44, 111,"Good",0));
		//cant
		modset.addModule(new MarsModule(37, 43, 141,"Good",0));
		//plain 3
		modset.addModule(new MarsModule(42, 43,1,"Good",0));
		modset.addModule(new MarsModule(42, 44,1,"Good",0));
		modset.addModule(new MarsModule(42, 45,1,"Good",0));
		modset.addModule(new MarsModule(42, 46,1,"Good",0));
		modset.addModule(new MarsModule(42, 47,1,"Good",0));
		//dorm
		modset.addModule(new MarsModule(41, 45,61,"Good",0));
		modset.addModule(new MarsModule(43, 45,61,"Good",0));
		modset.addModule(new MarsModule(41, 47,61,"Good",0));
		modset.addModule(new MarsModule(42, 48,61,"Good",0));
		//sanit
		modset.addModule(new MarsModule(41, 46,91,"Good",0));
		modset.addModule(new MarsModule(43, 47,91,"Good",0));
		//gym
		modset.addModule(new MarsModule(43, 46,131,"Good",0));
		//food water
		modset.addModule(new MarsModule(43, 43,111,"Good",0));
		modset.addModule(new MarsModule(43, 44,111,"Good",0));
		modset.addModule(new MarsModule(41, 44,111,"Good",0));
		//cant
		modset.addModule(new MarsModule(41, 43,141,"Good",0));
		//plain 4
		modset.addModule(new MarsModule(46, 43,1,"Good",0));
		modset.addModule(new MarsModule(46, 44,1,"Good",0));
		modset.addModule(new MarsModule(46, 45,1,"Good",0));
		modset.addModule(new MarsModule(46, 46,1,"Good",0));
		modset.addModule(new MarsModule(46, 47,1,"Good",0));
		//dromszzz
		modset.addModule(new MarsModule(45, 45,65,"Good",0));
		modset.addModule(new MarsModule(47, 45,65,"Good",0));
		modset.addModule(new MarsModule(45, 47,65,"Good",0));
		modset.addModule(new MarsModule(46, 48,65,"Good",0));
		//sanit
		modset.addModule(new MarsModule(45, 46,91,"Good",0));
		modset.addModule(new MarsModule(47, 47,91,"Good",0));
		//gym
		modset.addModule(new MarsModule(47, 46,131,"Good",0));
		//food water
		modset.addModule(new MarsModule(47, 43,111,"Good",0));
		modset.addModule(new MarsModule(47, 44,111,"Good",0));
		modset.addModule(new MarsModule(45, 44,111,"Good",0));
		//cant
		modset.addModule(new MarsModule(45, 43,141,"Good",0));
		//plain 4
		modset.addModule(new MarsModule(39, 41,1,"Good",0));
		modset.addModule(new MarsModule(39, 40,1,"Good",0));
		modset.addModule(new MarsModule(39, 39,1,"Good",0));
		modset.addModule(new MarsModule(39, 38,1,"Good",0));
		modset.addModule(new MarsModule(39, 37,1,"Good",0));
		//dromszzz
		modset.addModule(new MarsModule(40, 39,65,"Good",0));
		modset.addModule(new MarsModule(38, 39,65,"Good",0));
		modset.addModule(new MarsModule(40, 38,65,"Good",0));
		modset.addModule(new MarsModule(38, 38,65,"Good",0));
		modset.addModule(new MarsModule(38, 37,65,"Good",0));
		//food water
		modset.addModule(new MarsModule(39, 36,111,"Good",0));
		//cant
		modset.addModule(new MarsModule(40, 37,141,"Good",0));
		//med
		modset.addModule(new MarsModule(38, 41,181,"Good",0));
		//sanit
		modset.addModule(new MarsModule(40, 41,91,"Good",0));
		modset.addModule(new MarsModule(38, 40,91,"Good",0));
		//gym
		modset.addModule(new MarsModule(40, 40,131,"Good",0));
		//plain 4
		modset.addModule(new MarsModule(43, 41,1,"Good",0));
		modset.addModule(new MarsModule(43, 40,1,"Good",0));
		modset.addModule(new MarsModule(43, 39,1,"Good",0));
		modset.addModule(new MarsModule(43, 38,1,"Good",0));
		modset.addModule(new MarsModule(43, 37,1,"Good",0));
		//CONTROL
		modset.addModule(new MarsModule(44, 40,161,"Good",0));
		modset.addModule(new MarsModule(42, 40,161,"Good",0));
		modset.addModule(new MarsModule(42, 38,161,"Good",0));
		modset.addModule(new MarsModule(44, 38,161,"Good",0));
		//POW
		modset.addModule(new MarsModule(44, 41,151,"Good",0));
		modset.addModule(new MarsModule(42, 41,151,"Good",0));
		modset.addModule(new MarsModule(44, 39,151,"Good",0));
		modset.addModule(new MarsModule(42, 39,151,"Good",0));
		//med
		modset.addModule(new MarsModule(42, 37,181,"Good",0));
		//sanit
		modset.addModule(new MarsModule(44, 37,91,"Good",0));
		//air
		modset.addModule(new MarsModule(43, 36,171,"Good",0));	
		//plain 4
		modset.addModule(new MarsModule(47, 41,1,"Good",0));
		modset.addModule(new MarsModule(47, 40,1,"Good",0));
		modset.addModule(new MarsModule(47, 39,1,"Good",0));
		modset.addModule(new MarsModule(47, 38,1,"Good",0));
		modset.addModule(new MarsModule(47, 37,1,"Good",0));
		//sanit
		modset.addModule(new MarsModule(46, 41,91,"Good",0));
		//med
		modset.addModule(new MarsModule(48, 41,181,"Good",0));
		//dromszzz
		modset.addModule(new MarsModule(46, 40,65,"Good",0));
		modset.addModule(new MarsModule(48, 40,65,"Good",0));
		modset.addModule(new MarsModule(48, 39,65,"Good",0));
		//air
		modset.addModule(new MarsModule(47, 36,171,"Good",0));
		//med
		modset.addModule(new MarsModule(46, 37,181,"Good",0));
			
		
			
		RootPanel.get().add(modset.getGui());

	}
	private void launchSomeMaximumData() {
		final ModuleSet modset = new ModuleSet("demo",graphics);
		//plain 1
		modset.addModule(new MarsModule(1,2,2,"Good",0));
		modset.addModule(new MarsModule(1,3,1,"Good",0));
		modset.addModule(new MarsModule(1,4,1,"Good",0));		
		modset.addModule(new MarsModule(1,5,1,"Good",0));
		modset.addModule(new MarsModule(1,6,1,"Good",0));
		modset.addModule(new MarsModule(1,7,1,"Good",0));		
		modset.addModule(new MarsModule(1,8,1,"Good",0));
		modset.addModule(new MarsModule(1,9,1,"Good",0));
		modset.addModule(new MarsModule(1,10,1,"Good",0));		
		modset.addModule(new MarsModule(1,11,1,"Good",0));
		//air
		modset.addModule(new MarsModule(1,12,171,"Good",0));		
		modset.addModule(new MarsModule(1,13,171,"Good",0));
		//plain 2
		modset.addModule(new MarsModule(1,14,1,"Good",0));
		modset.addModule(new MarsModule(1,15,1,"Good",0));
		modset.addModule(new MarsModule(1,16,1,"Good",0));
		modset.addModule(new MarsModule(1,17,1,"Good",0));		
		modset.addModule(new MarsModule(1,18,1,"Good",0));
		
		//Dorms one
		modset.addModule(new MarsModule(1,19,65,"Good",0));
		modset.addModule(new MarsModule(1,20,65,"Good",0));
		modset.addModule(new MarsModule(1,21,65,"Good",0));
		modset.addModule(new MarsModule(1,22,65,"Good",0));
		//sanit
		modset.addModule(new MarsModule(1,23,91,"Good",0));
		modset.addModule(new MarsModule(1,24,92,"Good",0));
		//gym
		modset.addModule(new MarsModule(1,25, 131,"Good",0));
		//food
		modset.addModule(new MarsModule(1,26, 111,"Good",0));
		modset.addModule(new MarsModule(1,27, 111,"Good",0));
		modset.addModule(new MarsModule(1,28, 111,"Good",0));
		//cant
		modset.addModule(new MarsModule(1,29, 141,"Good",0));
		//plain 3
		modset.addModule(new MarsModule(2,2,1,"Good",0));
		modset.addModule(new MarsModule(2,3,1,"Good",0));
		modset.addModule(new MarsModule(2,4,1,"Good",0));
		modset.addModule(new MarsModule(2,5,1,"Good",0));
		modset.addModule(new MarsModule(2,6,1,"Good",0));
		//dorm
		modset.addModule(new MarsModule(2,7,61,"Good",0));
		modset.addModule(new MarsModule(2,8,61,"Good",0));
		modset.addModule(new MarsModule(2,9,61,"Good",0));
		modset.addModule(new MarsModule(2,10,61,"Good",0));
		//sanit
		modset.addModule(new MarsModule(2,11,91,"Good",0));
		modset.addModule(new MarsModule(2,12,91,"Good",0));
		//gym
		modset.addModule(new MarsModule(2,13,131,"Good",0));
		//food water
		modset.addModule(new MarsModule(2,14,111,"Good",0));
		modset.addModule(new MarsModule(2,15,111,"Good",0));
		modset.addModule(new MarsModule(2,16,111,"Good",0));
		//cant
		modset.addModule(new MarsModule(2,17,141,"Good",0));
		//plain 4
		modset.addModule(new MarsModule(2,18,1,"Good",0));
		modset.addModule(new MarsModule(2,19,1,"Good",0));
		modset.addModule(new MarsModule(2,20,1,"Good",0));
		modset.addModule(new MarsModule(2,21,1,"Good",0));
		modset.addModule(new MarsModule(2,22,1,"Good",0));
		//dromszzz
		modset.addModule(new MarsModule(80,5,65,"Good",0));
		modset.addModule(new MarsModule(81,5,65,"Good",0));
		modset.addModule(new MarsModule(82,5,65,"Good",0));
		modset.addModule(new MarsModule(83,5,65,"Good",0));
		//sanit
		modset.addModule(new MarsModule(84,5,91,"Good",0));
		modset.addModule(new MarsModule(85,5,91,"Good",0));
		//gym
		modset.addModule(new MarsModule(86,5,131,"Good",0));
		//food water
		modset.addModule(new MarsModule(87,5,111,"Good",0));
		modset.addModule(new MarsModule(88,5,111,"Good",0));
		modset.addModule(new MarsModule(89,5,111,"Good",0));
		//cant
		modset.addModule(new MarsModule(90,5,141,"Good",0));
		//plain 4
		modset.addModule(new MarsModule(91,5,1,"Good",0));
		modset.addModule(new MarsModule(92,5,1,"Good",0));
		modset.addModule(new MarsModule(93,5,1,"Good",0));
		modset.addModule(new MarsModule(94,5,1,"Good",0));
		modset.addModule(new MarsModule(95,5,1,"Good",0));
		//dromszzz
		modset.addModule(new MarsModule(96,5,65,"Good",0));
		modset.addModule(new MarsModule(97,5,65,"Good",0));
		modset.addModule(new MarsModule(98, 5,65,"Good",0));
		modset.addModule(new MarsModule(99,5,65,"Good",0));
		modset.addModule(new MarsModule(99,6,65,"Good",0));
		//food water
		modset.addModule(new MarsModule(98, 6,111,"Good",0));
		//cant
		modset.addModule(new MarsModule(97, 6,141,"Good",0));
		//med
		modset.addModule(new MarsModule(96, 6,181,"Good",0));
		//sanit
		modset.addModule(new MarsModule(95, 6,91,"Good",0));
		modset.addModule(new MarsModule(94, 6,91,"Good",0));
		//gym
		modset.addModule(new MarsModule(93, 6,131,"Good",0));
		//plain 4
		modset.addModule(new MarsModule(10,25,1,"Good",0));
		modset.addModule(new MarsModule(10,26,1,"Good",0));
		modset.addModule(new MarsModule(10,27,1,"Good",0));
		modset.addModule(new MarsModule(10,28,1,"Good",0));
		modset.addModule(new MarsModule(10,29,1,"Good",0));
		//CONTROL
		modset.addModule(new MarsModule(10,30,161,"Good",0));
		modset.addModule(new MarsModule(10,31,161,"Good",0));
		modset.addModule(new MarsModule(10,32,161,"Good",0));
		modset.addModule(new MarsModule(10,33,161,"Good",0));
		//POW
		modset.addModule(new MarsModule(10,34,151,"Good",0));
		modset.addModule(new MarsModule(10,35,151,"Good",0));
		modset.addModule(new MarsModule(10,36,151,"Good",0));
		modset.addModule(new MarsModule(10,37,151,"Good",0));
		//med
		modset.addModule(new MarsModule(10,24,181,"Good",0));
		//sanit
		modset.addModule(new MarsModule(10,23,91,"Good",0));
		//ai
		modset.addModule(new MarsModule(10,22,171,"Good",0));	
		//plain 4
		modset.addModule(new MarsModule(61, 20,1,"Good",0));
		modset.addModule(new MarsModule(62, 20,1,"Good",0));
		modset.addModule(new MarsModule(63, 20,1,"Good",0));
		modset.addModule(new MarsModule(64, 20,1,"Good",0));
		modset.addModule(new MarsModule(65, 20,1,"Good",0));
		//sanit
		modset.addModule(new MarsModule(66, 20,91,"Good",0));
		//med
		modset.addModule(new MarsModule(67, 20,181,"Good",0));
		//dromszzz
		modset.addModule(new MarsModule(68, 20,65,"Good",0));
		modset.addModule(new MarsModule(69, 20,65,"Good",0));
		modset.addModule(new MarsModule(70,20,65,"Good",0));
		//air
		modset.addModule(new MarsModule(71, 20,171,"Good",0));
		//med
		modset.addModule(new MarsModule(72, 20,181,"Good",0));
			
		
			
		RootPanel.get().add(modset.getGui());

	}
	private void launchMinConfig() {
		final ModuleSet modset = new ModuleSet("Minimum",graphics);
		

		
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
		modset.addModule(new MarsModule(15,50,2,"Good",0));		//Plain
		modset.addModule(new MarsModule(16,49,3,"Good",0)); 	//Plain
		modset.addModule(new MarsModule(13,48,4,"Good",0)); 	//Plain
		modset.addModule(new MarsModule(16,47,70,"Good",0)); 	//Dormitory
		modset.addModule(new MarsModule(16,46,95,"Good",0));	//Sanitation
		modset.addModule(new MarsModule(16,45,141,"Good",0));	//Canteen
		modset.addModule(new MarsModule(10,44,154,"Good",0));	//Power
		modset.addModule(new MarsModule(11,43,164,"Good",0));	//Control
		modset.addModule(new MarsModule(11,42,174,"Good",0));	//Airlock
		modset.addModule(new MarsModule(11,41,111,"Good",0));	//Food and Water
		modset.addModule(new MarsModule(11,40,181,"Good",0));	//Food and Water
		
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
