package project.simulation;

import java.util.LinkedList;

import com.google.gwt.user.client.Window;

import project.backend.MarsConfiguration;
import project.backend.MarsModule;
import project.backend.ModuleSet;





public class FullConfigMaker {
	LinkedList<Integer> usedModules = new LinkedList<Integer>();
	LinkedList<MarsModule> modList;
	private ModuleSet modset;
	 public FullConfigMaker(ModuleSet modset) {
		  this.modset = modset;
		 }
	 
	 public MarsConfiguration genFullConfig(ModuleSet modset, int numConf) {
		  MarsConfiguration config = new MarsConfiguration(modset);
		  makeFull(config);
		  
		  return config;
	 }
	 
	 private void makeFull(MarsConfiguration config) {

		  int numplain = modset.getCount("Plain");
		  Window.alert("MiddleCount:" + numplain);
		  int cenX = getCenterOfGravity(true);
		  int cenY = getCenterOfGravity(false);
		  constructPlain(config, numplain, cenX, cenY);
		  int moveMod = -1;
//		  Window.alert("X: "+cenX);  
//		  Window.alert("Y: "+cenY);
//		  moveMod = getModuleOfType("Plain");
//		  config.setIsUsed(moveMod, true);
//		  config.setXCoord(moveMod, cenX);
//		  config.setYCoord(moveMod, cenY);
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
		 Window.alert("TopLeftCount:" + numplain);
		 for(int i=1; i<=numplain; i++) {
			 if(i<=9) {
				  moveMod = getModuleOfType("Plain");
				  config.setIsUsed(moveMod, true);
				  config.setXCoord(moveMod, cenX-i);
				  config.setYCoord(moveMod, cenY+2);
			 }

			 else {
				  makeBottomLeft(config, numplain-9, cenX, cenY, moveMod);
				  i = numplain+1;
			  }
		 }
	 }
	 
	 private void makeBottomLeft(MarsConfiguration config, int numplain, int cenX, int cenY, int moveMod) {
		 Window.alert("BottomLeftCount:" + numplain);
		 for(int i=1; i<=numplain; i++) {
			 if(i<=9) {
				  moveMod = getModuleOfType("Plain");
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
		 Window.alert("TopRightCount:" + numplain);
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
		 Window.alert("BottomRightCount:" + numplain);
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
		
}
