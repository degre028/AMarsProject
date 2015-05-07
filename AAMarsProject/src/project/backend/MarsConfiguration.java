package project.backend;

import java.util.LinkedList;

import project.simulation.ConfigStats;


/**
 * This class contains a list of modules in different positions.
 * @author Rob
 *
 */
public class MarsConfiguration {
	//Define private variables.
	private ModuleSet modset;
	private LinkedList<Integer> xCoords = new LinkedList<Integer>();
	private LinkedList<Integer> yCoords = new LinkedList<Integer>();
	private LinkedList<Integer> moves = new LinkedList<Integer>();
	String name = "";
	Integer conQuality = 100;
	private LinkedList<Boolean> isUsed = new LinkedList<Boolean>();
	private LinkedList<Boolean> isIgnored = new LinkedList<Boolean>();
	
	/**
	 * Constructor for configuration.
	 * @param modset
	 */
	public MarsConfiguration(ModuleSet modset) {
		this.modset = modset;
		
		for(int i = 0; i < modset.getCount("all"); i++) {
			xCoords.add(modset.getModule(i).getX());
			yCoords.add(modset.getModule(i).getY());
			isUsed.add(false);
			if(modset.getModule(i).getType().equals("Unknown") || modset.getModule(i).getCondition().equals("Damaged")) {
				isIgnored.add(true);
			}
			else if(modset.getModule(i).getX() < 1 || modset.getModule(i).getX() > 100) {
				isIgnored.add(true);
			}
			else if(modset.getModule(i).getY() < 1 || modset.getModule(i).getY() > 50) {
				isIgnored.add(true);
			}
			else if(modset.getModule(i).getX() > 40 && modset.getModule(i).getX() < 51) {
				if(modset.getModule(i).getY() > 39 && modset.getModule(i).getY() < 51 ) {
					isIgnored.add(true);
				}
				else{
					isIgnored.add(false);
				}
			}
			else {
				isIgnored.add(false);
			}
			
			moves.add(0);
		}
	}
	
	

	public Integer getXCoord(int i) {
		return xCoords.get(i);
	}
	
	public Integer getYCoord(int i) {
		return yCoords.get(i);
	}
	
	public void setXCoord(int index, int xcoord) {
		xCoords.set(index, xcoord);
		if(xcoord != modset.getModule(index).getX()) {
			isUsed.set(index, true);
		}
		updateStats();
	}
	
	public void setYCoord(int index, int ycoord) {
		yCoords.set(index, ycoord);
		if(ycoord != modset.getModule(index).getY()) {
			isUsed.set(index, true);
		}
		updateStats();
	}
	
	public MarsModule getModule(int i) {
		MarsModule mod = new MarsModule();
		mod.setCondition(modset.getModule(i).getCondition());
		mod.setID(modset.getModule(i).getID());
		mod.setOrientation(modset.getModule(i).getOrientation());
		mod.setX(xCoords.get(i));
		mod.setY(yCoords.get(i));
		
		return mod;
	}
	
	public void setName(String name) {
		this.name = name;
	}	
	
	public String getName() {
		return name;
	}
	
	public Integer getQuality() {
		return conQuality;
	}
	
	public void setQuality(int qual) {
		this.conQuality = qual;
	}
	
	private void updateStats() {
		ConfigStats stats = new ConfigStats(modset,this);
		
		for(int i = 0; i < modset.getCount("all"); i++) {
			moves.set(i, stats.moduleMove(i));
		}
	}
	
	public Integer getMoves(int index) {
		return moves.get(index);
	}


	public boolean getIsUsed(int index) {
		return isUsed.get(index);
	}


	public void setIsUsed(int i, boolean used) {
		isUsed.set(i, used);
	}
	
	/**
	 * This method shifts the entire configuration by the
	 * specified amount.
	 * @param xMod
	 * @param yMod
	 */
	public void moveConfig(int xMod, int yMod) {
		for (int i = 0; i < modset.getCount("all"); i++) {
			if (getIsUsed(i)) {
				setXCoord(i, getXCoord(i) + xMod);
				setYCoord(i, getYCoord(i) + yMod);
			}
		}
	}
	
	public int getConfigTypeCount(String type) {
		
		if (type.equals("all")) {
			int count = 0;
			for(int i=0; i<modset.getCount("all"); i++) {
				if(isUsed.get(i)) {
					count++;
				}
			}
			return count;
		}
		
		int count = 0;
		
		for(int i=0; i<modset.getCount("all"); i++) {
			if(modset.getModule(i).getType().equals(type) && isUsed.get(i)) {
				count++;
			}
		}
		
		return count;
	}



	public boolean getIsIgnored(int index) {
		return isIgnored.get(index);
	}



	public void setIsIgnored(int index, boolean setter) {
		isIgnored.set(index, setter);
	}
	
}
