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
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import project.backend.ModuleSet;

/**
 * Control Panel and layout creator for the weather widget.
 * @author Adam
 *
 */
public class CtrlWeather extends CtrlPanel{
	private String mResponse;

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
	}
	
	
	/**
	 * Method to "refresh" the control panel area.
	 * It is called each by the MarsButton click event
	 * but can be called from other places too.
	 */
	@Override
	public void setupDisplay() {
		//KEY: d52e7b82dd8d3349
		
		getHeaderLabel().setText("Weather");
		
		VerticalPanel vp = new VerticalPanel(); 
		//vp.add(new Label(text)); //TO VIEW 
		String sAll = mResponse; 
		JSONObject jA = (JSONObject)JSONParser.parseLenient(sAll);
		JSONValue jTry = jA.get("current_observation"); 
		JSONObject jB = (JSONObject)JSONParser.parseLenient(jTry.toString()); 
		JSONValue temp = jB.get("temp_c"); 
		JSONValue visibility = jB.get("visibility_km"); 
		String sTemp = temp.toString(); 
		String sVisibility = visibility.toString(); 
		vp.add(new Label(sTemp)); //TO VIEW 
		vp.add(new Label(sVisibility)); //TO VIEW
		Image wunder = new Image("resources/images/wunder.png");
		vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		
		wunder.getElement().getStyle().setWidth(80, Unit.PCT);
		wunder.getElement().getStyle().setHeight(80, Unit.PCT);
		vp.add(wunder);
		getPanel().add(vp);
		
	}
}
