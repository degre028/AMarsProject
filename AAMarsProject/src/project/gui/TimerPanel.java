package project.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class TimerPanel extends Composite {
	public final long end = 5000;

	final Label timerLabel = new Label("10-Day Alert Test");
	final Label countLabel = new Label("");
	Integer counter = (int) (end / 1000);
	long startTime = 0;
	/**
	 * This method constructs a timer panel.
	 * @param startTime
	 */
	public TimerPanel(final long startTime) {
		//
		this.startTime = startTime;
		
		timerLabel.getElement().getStyle().setPaddingLeft(3.0, Unit.PX);
		countLabel.getElement().getStyle().setPaddingLeft(3.0, Unit.PX);
		
		final FlowPanel mainPanel = new FlowPanel();
		mainPanel.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		mainPanel.getElement().getStyle().setWidth(200, Unit.PX);
		mainPanel.getElement().getStyle().setHeight(100, Unit.PX);
		
		final Timer flasher = new Timer() {
			public void run() {
				if (mainPanel.getElement().getStyle().getBorderColor().equals("RED"))
				{
					mainPanel.getElement().getStyle().setBorderColor("BLACK");
				}
				else {
					mainPanel.getElement().getStyle().setBorderColor("RED");
				}
				
			}
		};
		
		
		final Timer time = new Timer() {
			public void run() {
				
				long currentTime = System.currentTimeMillis();
				if(currentTime > getStartTime() + end){
					countLabel.setText("Check Rover!!");
					//Window.alert("Check Rover");
					flasher.scheduleRepeating(200);					
					this.cancel();

				} else {
				countLabel.setText("You have: " + counter.toString() +
						" seconds remianing until fuel check.");
				incrementCounter();


				}
			}
		};
		time.scheduleRepeating(1000);
		
		Button btnReset = new Button("Reset");
		btnReset.addClickHandler( new ClickHandler() {
			public void onClick(ClickEvent ev) {
				resetTimes();
				time.cancel();
				flasher.cancel();
				mainPanel.getElement().getStyle().setBorderColor("BLACK");
				time.scheduleRepeating(1000);
			}
		});
			
		mainPanel.add(MakeTimer());
		mainPanel.add(btnReset);
		initWidget(mainPanel);
	}
	
	private FlowPanel MakeTimer() {
		FlowPanel timerPanel = new FlowPanel();
		
		
		
		timerPanel.add(timerLabel);
		timerPanel.add(countLabel);
		
		return timerPanel;
	}
	
	private void incrementCounter() {
		counter--;
	}
	
	private void resetTimes() {
		startTime = System.currentTimeMillis();
		counter = (int) (end / 1000);
	}
	
	private long getStartTime() {
		return startTime;
		
	}
}
