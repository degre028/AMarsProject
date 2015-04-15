package project.backend;


/**
 * The module class represents a module the mars team may encounter.  Each time the user adds a module
 * a new instance of the module class is created.
 * @author Rob
 *
 */
public class Module {
	//Private fields for the module class.
	int xcoord = -1;
	int ycoord = -1;
	int id = 0;
	String condition = "";
	int orientation = 0;
	String type = "";
	
	
	/**
	 * Constructor for the Module.
	 */
	public Module() {					
	}
	/**
	 * Secondary Constructor for Module.
	 */
	public Module(int xdim, int ydim, int idnum, String cond, int ori) {
		xcoord=xdim;
		ycoord=ydim;
		id=idnum;
		condition=cond;
		orientation=ori;				
	}
	
	/**
	 * Sets an integer value to the module
	 * Sets the type of module based on the int ID Number.
	 * @param x
	 * @method makeType
	 */
	public void SetID(int x) {
		id=x;
		type=makeType();
	}
	
	public String getType() {
		return type;
	}
	
	/**
	 * 
	 * @return the id of the module
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Sets the condition of the module in a string
	 * @param cond
	 */
	public void setCondition(String cond) {
		condition = cond;
	}
	
	/**
	 * 
	 * @return The condition of the module as a String.
	 */
	public String getCondition() {
		return condition;
	}
	
	/**
	 * Sets the orientation of the module as a 
	 * @param ori
	 */
	public void setOrientation(int ori) {
		orientation = ori;
	}
	
	/**
	 * 
	 * @return the orientation of the module as an int. (number of flips needed to be upright)
	 */
	public int getOrientation() {
		return orientation;
	}
	
	/**
	 * Sets the y-coordinate of the module as an int.
	 * @param y
	 */
	public void setY(int y) {
		ycoord = y;
	}
	
	/**
	 * 
	 * @return the ycoordinate of the module as an int
	 */	
	public int getY() {
		return ycoord;
	}
	
	/**
	 * 
	 * @return the ID of the module as the type it actually is - String value.
	 */
	private String makeType() {
		if(id > 0 && id < 41) {
			return "Plain";
		}
		else if(id > 60 && id < 81) {
			return "Dormitory";
		}
		else if(id > 90 && id < 101) {
			return "Sanitation";
		}
		else if(id > 110 && id < 121) {
			return "Food & Water";
		}
		else if(id > 130 && id < 135) {
			return "Gym & Relaxation";
		}
		else if(id > 140 && id < 145) {
			return "Canteen";
		}
		else if(id > 150 && id < 155) {
			return "Power";
		}
		else if(id > 160 && id < 165) {
			return "Control";
		}
		else if(id > 170 && id < 175) {
			return "Airlock";
		}
		else if(id > 180 && id < 185) {
			return "Medical";
		}
		else {
			return "";
		}
		
	}
	
}
