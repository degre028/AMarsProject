package project.client;

import project.backend.MarsConfiguration;
import project.backend.ModuleSet;
import project.backend.Passwd;
import project.gui.GUI;
import project.gui.Login;
import project.gui.Starter;
import project.shared.FieldVerifier;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AAMarsProject implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote serviceproxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		

		try {
			
		
		 GWT.setUncaughtExceptionHandler(new
			      GWT.UncaughtExceptionHandler() {
			      public void onUncaughtException(Throwable e) {
			        // do exception handling stuff
			      }
			    });
			    DeferredCommand.addCommand(new Command() {
			      public void execute() {
			        onModuleLoad2();
			      }
			    });
		} catch (Exception e) {
			
		}
	  }
	
	public void onModuleLoad2() {
		Starter starter = new Starter();
		RootPanel.get().add(starter);
	}
			
		
	}

