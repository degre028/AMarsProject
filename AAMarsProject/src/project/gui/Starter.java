package project.gui;

import project.backend.MarsModule;
import project.backend.ModuleSet;
import project.backend.Passwd;

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
		
		modset.addModule(new MarsModule(32,16,2,"Good",0));
		modset.addModule(new MarsModule(88,55,63,"Good",0));
		modset.addModule(new MarsModule(32,65,65,"Good",0));
		modset.addModule(new MarsModule(102,18,91,"Good",0));
		modset.addModule(new MarsModule(99,14,3,"Good",0));
		modset.addModule(new MarsModule(1,19,132,"Good",0));
		modset.addModule(new MarsModule(55,5,184,"Good",0));
		modset.addModule(new MarsModule(100,100,172,"Good",0));
		modset.addModule(new MarsModule(32,17,150,"Good",0));
		
		
		RootPanel.get().add(modset.getGui());
	}
}
