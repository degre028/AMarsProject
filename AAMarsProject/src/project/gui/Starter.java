package project.gui;

import project.backend.MarsModule;
import project.backend.ModuleSet;
import project.backend.Passwd;
import project.backend.MarsStorage;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * This class is responsible for handling the start of the program.
 * It's startup method is the only thing called in onModuleLoad().
 * @author Rob
 *
 */
public class Starter extends FlowPanel {
	
	
	/**
	 * Starter constructor.  It begins the process of creating
	 * desired system setup.
	 */
	public Starter() {
		//launchLoginDemo();
		//launchSkipLogin();
		launchSomeData();
		//launchServerStorage();
		//launchLocalStorage();
	}
	
	
	/**
	 * Method called if the user wants to demo the program with full
	 * login capabilities.
	 */
	private void launchLoginDemo() {
		final FlowPanel flo1 = new FlowPanel();
		final Login log1 = new Login(new Passwd(), flo1);
		flo1.add(log1);
		RootPanel.get().add(flo1);
	}
	
	/**
	 * Method called if the user wants to demo the program with full
	 * login capabilities.
	 */
	private void launchSkipLogin() {
		final ModuleSet modset = new ModuleSet("demo");
		RootPanel.get().add(modset.getGui());
	}
	
	/**
	 * Method called if the user wants to have some bs data entered
	 * at the start
	 */
	private void launchSomeData() {
		final ModuleSet modset = new ModuleSet("demo");
		
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
		
		modset.BuildGUI();		
		RootPanel.get().add(modset.getGui());

	}
	
	/**
	 * Method called if the user wants to test the dictionary reader.
	 */
	private void launchServerStorage() {
		final ModuleSet modset = new ModuleSet("demostorage");
		
		modset.getStorage().readServerHtml();
		
		RootPanel.get().add(modset.getGui());
	}
	
	/**
	 * Method called if the user wants to test the JSON reader.
	 * Uses local web storage
	 */
	private void launchLocalStorage() {
		final ModuleSet modset = new ModuleSet("demolocal");
		
		modset.getStorage().loadLocalStore();
		
		RootPanel.get().add(modset.getGui());
	}
}
