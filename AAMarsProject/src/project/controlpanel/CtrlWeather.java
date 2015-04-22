package project.controlpanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import project.backend.ModuleSet;

/**
 * Control Panel and layout creator for the weather widget.
 * @author Adam
 *
 */
public class CtrlWeather extends CtrlPanel{
	private String mResponse;
	private VerticalPanel weatherPanel = new VerticalPanel(); 
	private Label tempWeather = new Label("Default Temp");
	private Label visWeather = new Label("Default Visiblility");



	/**
	 * Constructor for the weather control panel.
	 * Should create the layout and ping the proxy service.
	 * Should call setupDisplay() to refresh.
	 */
	public CtrlWeather(ModuleSet modset) {
		
		String url = "http://api.wunderground.com/api/d52e7b82dd8d3349/conditions/q/CA/San_Francisco.json";
		url = URL.encode(url);
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url); 
		try { 
			Request request = builder.sendRequest(null, new RequestCallback()
			{ 
				public void onError(Request request, Throwable exception) { 
				Window.alert("onError: Couldn't retrieve JSON");
				}
			@Override	
			public void onResponseReceived(Request request, Response response) { 
				if (200 == response.getStatusCode()) { 
					String rt = response.getText(); 
					mResponse = rt;
					} else { 
						Window.alert("Couldn't retrieve JSON (" + response.getStatusCode() + ")");
						}
				}
			});
			} 
		catch (RequestException e) { 
			Window.alert("RequestException: Couldn't retrieve JSON"); 
		}
		
		super.getHeaderLabel().setText("Weather");
		weatherPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		weatherPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		Label tempLabel = new Label("Tempurature");
		weatherPanel.add(tempLabel);
		weatherPanel.add(tempWeather); //TO VIEW
		
		Label visLabel = new Label("Visibility in km");
		weatherPanel.add(visLabel);
		weatherPanel.add(visWeather); //TO VIEW
		
		Image wunder = new Image("resources/images/wunder.png");
		weatherPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		weatherPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		
		wunder.getElement().getStyle().setWidth(80, Unit.PCT);
		wunder.getElement().getStyle().setHeight(40, Unit.PCT);
		weatherPanel.add(wunder);
		getPanel().add(weatherPanel);
		
		//vp.add(new Label(text)); //TO VIEW 
		//VerticalPanel testPanel = new VerticalPanel();
		//Label test = new Label("TEST TEST");
		//testPanel.add(test);
		//getPanel().add(testPanel);
		//setupDisplay();
		

	}
	
	
	/**
	 * Method to "refresh" the control panel area.
	 * It is called each by the MarsButton click event
	 * but can be called from other places too.
	 */
	@Override
	public void setupDisplay() {
		//KEY: d52e7b82dd8d3349

		//vp.add(new Label(text)); //TO VIEW 
		String sAll = mResponse; 
		JSONObject jA = (JSONObject)JSONParser.parseLenient(sAll);
		JSONValue jTry = jA.get("current_observation"); 
		JSONObject jB = (JSONObject)JSONParser.parseLenient(jTry.toString()); 
		
		
		JSONValue temp = jB.get("temp_c"); 
		String sTemp = temp.toString();
		tempWeather.setText(sTemp);
		
		JSONValue visibility = jB.get("visibility_km");
		
		String sVisibility = visibility.toString();	
		visWeather.setText(sVisibility);

		
			
	}
}
