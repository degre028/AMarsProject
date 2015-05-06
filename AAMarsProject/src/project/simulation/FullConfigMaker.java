package project.simulation;

import java.util.LinkedList;

import com.google.gwt.user.client.Window;

import project.backend.MarsConfiguration;
import project.backend.MarsModule;
import project.backend.ModuleSet;





public class FullConfigMaker {
	LinkedList<Integer> usedModules = new LinkedList<Integer>();
	LinkedList<MarsModule> modList;
	 int minx = 1;
	 int miny = 1;
	 int maxx = 100;
	 int maxy = 50;
	private ModuleSet modset;
	 public FullConfigMaker(ModuleSet modset) {
		  this.modset = modset;
		 }
	 
	 public MarsConfiguration genFullConfig(ModuleSet modset, int numConf) {
		  MarsConfiguration config = new MarsConfiguration(modset);
		  //constructPlain(config, numplain, cenX, cenY);
		  makeFull(config);
		  //recenterize(config, cenX, cenY);
		  if(inVoid(config)) {
			  moveVoid(config);
		  }
		  return config;
	 }
	 
	 private void makeFull(MarsConfiguration config) {

		  int numplain = modset.getCount("Plain");
		  //Window.alert("MiddleCount:" + numplain);
		  int cenX = getCenterOfGravity(true);
		  int cenY = getCenterOfGravity(false);
		  constructPlain(config, numplain, cenX, cenY);
		  recenterize(config, cenX, cenY);
	 }
	 
	 private Integer getCenterOfGravity(boolean isX) {
		  int center = 0;
		  int sum = 0;
		  
		  if (isX) {
		   for(int i = 0; i < modset.getCount("all"); i++) {
		    sum += modset.getModule(i).getX();
		    
		   }
		  } else {
		   for(int i = 0; i < modset.getCount("all"); i++) {
		    sum += modset.getModule(i).getY();  
		   }
		  }
		  
		  center = sum / modset.getCount("all");

		  return center;
		 } 
	 
	 private Integer getModuleOfType(String type) {
		  boolean found = false;
		  int index = -1;
		  int i = 0;
		  
		  while(!found && i < modset.getCount("all")) {
		   
		   if (usedModules.contains(i)) {
		    i++;
		   } 
		   else {
		    //module not used already, check the type.
		    if(modset.getModule(i).getType().equals(type)) {
		     found = true;
		     usedModules.add(i);
		     index = i;
		    }
		    else {
		     i++;
		    }
		   }
		  }
		  
		  //Window.alert("" + index);
		  return index;
		 }
	 
	 private void constructPlain(MarsConfiguration config, int numplain, int cenX, int cenY) {
		 boolean even = true;
		 int moveMod = -1;
		 int i = 0;
		 int count = 0;
		 while(count<numplain) {
			 //Window.alert("" + i);
			 if(even) {
				  //Window.alert("even");  
				  moveMod = getModuleOfType("Plain");
				  Window.alert("" + moveMod);
				  config.setIsUsed(moveMod, true);
				  config.setXCoord(moveMod, cenX);
				  config.setYCoord(moveMod, cenY-i);
				  //Window.alert("" + (cenY-i));
				  even = false;
				  if(i==2) {
					  makeTopLeft(config, numplain-5, cenX, cenY);
					  count=numplain;
				  }
			}
			 else {
				 //Window.alert("odd"); 
				 moveMod = getModuleOfType("Plain");
				 Window.alert("" + moveMod);
				  config.setIsUsed(moveMod, true);
				  config.setXCoord(moveMod, cenX);
				  config.setYCoord(moveMod, cenY+i);
				  //Window.alert("" + (cenY+i));
				  even = true;
				  i--;
			 }
			 i++;
			 count++;
		 }
	 }
	 private void makeTopLeft(MarsConfiguration config, int numplain, int cenX, int cenY) {
		 int moveMod = -1;
		 //Window.alert("TopLeftCount:" + numplain);
		 for(int i=1; i<=numplain; i++) {
			 if(i<=9) {
				  moveMod = getModuleOfType("Plain");
				  config.setIsUsed(moveMod, true);
				  config.setXCoord(moveMod, cenX-i);
				  config.setYCoord(moveMod, cenY+2);
				  if(i>=2) {
					  addNonPlain(config, moveMod, cenX,cenY, i);
				  }
			 }

			 else {
				  makeBottomLeft(config, numplain-9, cenX, cenY, moveMod);
				  i = numplain+1;
			  }
		 }
	 }
	 
	 private void makeBottomLeft(MarsConfiguration config, int numplain, int cenX, int cenY, int moveMod) {
		 //Window.alert("BottomLeftCount:" + numplain);
		 for(int i=1; i<=numplain; i++) {
			 if(i<=9) {
				  Window.alert("17" + moveMod);
				  config.setIsUsed(moveMod, true);
				  config.setXCoord(moveMod, cenX-i);
				  config.setYCoord(moveMod, cenY-2);
			 }
			 else {
				  makeTopRight(config, numplain-9, cenX, cenY, moveMod);
				  i = numplain+1;
			  }
		 }
	 }
	 
	 private void makeTopRight(MarsConfiguration config, int numplain, int cenX, int cenY, int moveMod) {
		 //Window.alert("TopRightCount:" + numplain);
		 for(int i=1; i<=numplain; i++) {
			 if(i<=9) {
				  moveMod = getModuleOfType("Plain");
				  config.setIsUsed(moveMod, true);
				  config.setXCoord(moveMod, cenX+i);
				  config.setYCoord(moveMod, cenY+2);
			 }
			 else {
				  makeBottomRight(config, numplain-9, cenX, cenY, moveMod);
				  i = numplain+1;
			  }
		 }
	 }
	 
	 private void makeBottomRight(MarsConfiguration config, int numplain, int cenX, int cenY, int moveMod) {
		 //Window.alert("BottomRightCount:" + numplain);
		 for(int i=1; i<=numplain; i++) {
			 if(i<=8) {
				  moveMod = getModuleOfType("Plain");
				  config.setIsUsed(moveMod, true);
				  config.setXCoord(moveMod, cenX+i);
				  config.setYCoord(moveMod, cenY-2);
			 }
			 else {
				  i = numplain+1;
			  }
		 }
	 }
	 
	 private void recenterize(MarsConfiguration config, int x, int y){
		  int newX = 0;
		  int newY = 0;
		  int check = minX(config);
		  if(check!=0) {
		   newX += check;
		//   Window.alert("NewX: " + newX);
		  }
		  check = minY(config);
		  if(check!=0) {
		   newY += check;
		  }
		  check = maxX(config);
		  if(check!=0) {
		   newX -= check; 
		  }
		  check = maxY(config);
		  if(check!=0) {
		   newY -= check; 
		  }
		//  Window.alert("NewX: " + newX);
		  for(int i = 0; i<modset.getCount("all"); i++) {
		   int oldX = config.getXCoord(i);
		   int oldY = config.getYCoord(i);
		   if(config.getIsUsed(i)) {
			   config.setXCoord(i, oldX+newX);
			   config.setYCoord(i, oldY+newY);
		   }
		  }
		 }
	 
	 private int minX(MarsConfiguration config) {
		  for(int i=0; i<modset.getCount("all"); i++) {
		   if(config.getModule(i).getX()<minx) {
		    minx = config.getModule(i).getX();
		    //Window.alert(""  + minx);
		   }
		  }
		  int temp = 1 - minx;
		//  Window.alert("1-minx: "  + temp);
		  return 1-minx;
		 }
		 
		 private int minY(MarsConfiguration config) {
		  for(int i=0; i<modset.getCount("all"); i++) {
		   if(config.getModule(i).getY()<miny) {
		    miny = config.getModule(i).getY();
		   }
		  }
		  return 1-miny;
		 }
		 
		 private int maxX(MarsConfiguration config) {
		  for(int i=0; i<modset.getCount("all"); i++) {
		   if(config.getModule(i).getX()>maxx) {
		    maxx = config.getModule(i).getX();
		   }
		  }
		  return maxx-100;
		 }
		 
		 private int maxY(MarsConfiguration config) {
		  for(int i=0; i<modset.getCount("all"); i++) {
		   if(config.getModule(i).getY()>maxy) {
		    maxy = config.getModule(i).getY();
		   }
		  }
		  return maxy-50;
		 }
		 
		 private boolean inVoid(MarsConfiguration config) {
			  for(int i = 0; i<modset.getCount("all"); i++) {
				  boolean temp = false; 
				  //Window.alert("FOR: " + "X: " + config.getXCoord(i) + "Y: " + config.getYCoord(i) + "Temp: " + temp);
				  if((config.getXCoord(i) > 40) && (config.getYCoord(i) <51)) {
					  //Window.alert( "X: " + config.getXCoord(i) + "Y: " + config.getYCoord(i) + "Temp: " + temp);
					  temp = true;
				  }
				  if(config.getXCoord(i) > 40 && config.getYCoord(i) <51 && temp==true) {
					  //Window.alert( "X: " + config.getXCoord(i) + "Y: " + config.getYCoord(i) + "Temp: " + temp);
					  return true;
				  }
				  }
			  return false;
		 }
		 
		 //Generate Avg x and decide where to put config based on that! Probably just put it below!
		 private void moveVoid(MarsConfiguration config) {
			 int ymax=40;
			 for(int i = 0; i<modset.getCount("all"); i++) {
				  boolean temp = false; 
				  //Window.alert("FOR: " + "X: " + config.getXCoord(i) + "Y: " + config.getYCoord(i) + "Temp: " + temp);
				  if((config.getXCoord(i) > 40) && (config.getYCoord(i) <51)) {
					  //Window.alert( "X: " + config.getXCoord(i) + "Y: " + config.getYCoord(i) + "Temp: " + temp);
					  temp = true;
				  }
				  if(config.getXCoord(i) > 40 && config.getYCoord(i) <51 && temp==true) {
					  //Window.alert( "X: " + config.getXCoord(i) + "Y: " + config.getYCoord(i) + "Temp: " + temp);
					  if(config.getModule(i).getY()>ymax) {
						  ymax = config.getModule(i).getY();
					  }
				  }
				  }
			 
			  for(int i = 0; i<modset.getCount("all"); i++) {
				   int oldY = config.getYCoord(i);
				   if (config.getIsUsed(i)) {
					   config.setYCoord(i, oldY-(ymax-39));
				   }
				  }
			  
		 }
		 
		 private void addNonPlain(MarsConfiguration config, int moveMod, int cenX,int cenY, int i) {
			  int numdor = modset.getCount("Dormitory");
			  int numsan = modset.getCount("Sanitation");
			  int numfoo = modset.getCount("Food & Storage");
			  int numgym = modset.getCount("Gym & Relaxation");
			  int numcan = modset.getCount("Canteen");
			  int numpow = modset.getCount("Power");
			  int numcon = modset.getCount("Control");
			  int nummed = modset.getCount("Medical");
			  int numair = modset.getCount("Airlock");
			  int all = modset.getCount("All");
			  LinkedList<Integer> modules = new LinkedList<Integer>();
			  
			  for(int j = 0; j < all; j++) {
				  
			  }
			  
			  if(i==2 && numdor != 0) {
			  moveMod = getModuleOfType("Dormitory");
			  config.setIsUsed(moveMod, true);
			  config.setXCoord(moveMod, cenX-i);
			  config.setYCoord(moveMod, cenY+1);
			  numdor--;
			  }
		 }
		 
		 private LinkedList<Integer> getNonPlain(MarsConfiguration config) {
			  LinkedList<Integer> modules = new LinkedList<Integer>();
			  for(int i = 0; i<modset.getCount("All"); i++) {
				  if(!modset.getModule(i).getType().equals("Plain") && config.) {
					  modules.add(i);
				  }
			  }
		 }
}
