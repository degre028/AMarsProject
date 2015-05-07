package project.simulation;

import java.util.LinkedList;

import com.google.gwt.user.client.Window;

import project.backend.MarsConfiguration;
import project.backend.ModuleSet;

/**
 * This class builds a configuration given a module set.
 * @author Rob
 *
 */
public class ConfigMaker {
 LinkedList<Integer> usedModules = new LinkedList<Integer>();

 ModuleSet modset;
 int minx = 1;
 int miny = 1;
 int maxx = 100;
 int maxy = 50;
 
 public ConfigMaker(ModuleSet modset) {
  this.modset = modset;
 }
 
 /**
  * This method builds an apt minimum configuration if the correct
  * number of modules are present.
  */
 public MarsConfiguration genMinimumConfig(ModuleSet modset, int numConf) {
  MarsConfiguration config = new MarsConfiguration(modset);
  boolean tits = false;
  tits = inVoid(config);

  //makeFull1(config);
  
  if(numConf == 1) {
	  makeMin1(config);  
  }
  else {
	  makeMin2(config);  
  }
  
  
  
  if(inVoid(config)) {
	  moveVoid(config);
  }
  tits = inVoid(config);

  return config;
 }
 
 /**
  * Method returns an appropriate module index.
  */
 private Integer getModuleOfType(String type, MarsConfiguration config) {
  boolean found = false;
  int index = -1;
  int i = 0;
  
  while(!found && i < modset.getCount("all")) {
   
   if (usedModules.contains(i)) {
    i++;
   } 
   else {
    //module not used already, check the type.
    if(modset.getModule(i).getType().equals(type) && !config.getIsIgnored(i)) {
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
 
 /**
  * Method returns the center of gravity.
  * @param isX - True if the user requires the X center, false
  * for the Y center.
  */
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
 
 private void makeMin1(MarsConfiguration config) {
  int cenX = getCenterOfGravity(true);
  int cenY = getCenterOfGravity(false);

  
  int moveMod = -1;
  //Window.alert(""+cenY);  
  moveMod = getModuleOfType("Plain", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Plain", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Plain", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Canteen", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Power", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Control", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Dormitory", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX + 2);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Sanitation", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Airlock", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Food & Water", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX - 2);
  config.setYCoord(moveMod, cenY);
  
  recenterize(config, cenX, cenY);
 }
 
 private void makeMin2(MarsConfiguration config) {
  int cenX = getCenterOfGravity(true);
  int cenY = getCenterOfGravity(false);

  
  int moveMod = -1;
  //Window.alert(""+cenY);  
  moveMod = getModuleOfType("Plain", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Plain", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Plain", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Canteen", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Power", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Sanitation", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Dormitory", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Control", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Airlock", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY - 2);
  
  moveMod = getModuleOfType("Food & Water", config);
  config.setIsUsed(moveMod, true);
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY + 2);
  
  recenterize(config, cenX, cenY);
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
 
 //Airlock 4, Control 4, Power 4, Food & water storage 10, Dormitory 20
 //Canteen 4, Sanitation 10, Gym & relaxation 4, Medical 4, Plain 40

// private void makeFull1(MarsConfiguration config) {
//	  int cenX = getCenterOfGravity(true);
//	  int cenY = getCenterOfGravity(false);
//
//	  
//	  int moveMod = -1;
//	  //Window.alert(""+cenY);  
//	  moveMod = getModuleOfType("Plain", config);
//	  config.setXCoord(moveMod, cenX);
//	  config.setYCoord(moveMod, cenY);
//	  
//	  moveMod = getModuleOfType("Plain", config);
//	  config.setXCoord(moveMod, cenX);
//	  config.setYCoord(moveMod, cenY+1);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-1);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX - 2);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-3);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-4);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-5);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-6);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-7);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX - 8);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-9);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+1);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+2);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+3);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+4);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+5);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+6);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+7);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+8);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+9);
//	  config.setYCoord(moveMod, cenY+2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX);
//	  config.setYCoord(moveMod, cenY-1);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+1);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX + 2);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+3);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+4);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+5);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+6);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX+7);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX + 8);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-1);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-2);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-3);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-4);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-5);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-6);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-7);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-8);
//	  config.setYCoord(moveMod, cenY-2);
//	  
//	  moveMod = getModuleOfType("Plain");
//	  config.setXCoord(moveMod, cenX-9);
//	  config.setYCoord(moveMod, cenY-2);
//	  	  
//	  moveMod = getModuleOfType("Canteen");
//	  config.setXCoord(moveMod, cenX - 1);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Canteen");
//	  config.setXCoord(moveMod, cenX + 1);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Canteen");
//	  config.setXCoord(moveMod, cenX - 1);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Canteen");
//	  config.setXCoord(moveMod, cenX + 1);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Power");
//	  config.setXCoord(moveMod, cenX+7);
//	  config.setYCoord(moveMod, cenY+1);
//	  
//	  moveMod = getModuleOfType("Power");
//	  config.setXCoord(moveMod, cenX+7);
//	  config.setYCoord(moveMod, cenY-1);
//
//	  moveMod = getModuleOfType("Power");
//	  config.setXCoord(moveMod, cenX-8);
//	  config.setYCoord(moveMod, cenY+1);
//	  
//	  moveMod = getModuleOfType("Power");
//	  config.setXCoord(moveMod, cenX-8);
//	  config.setYCoord(moveMod, cenY-3);
//	  
//	  moveMod = getModuleOfType("Control");
//	  config.setXCoord(moveMod, cenX + 1);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Control");
//	  config.setXCoord(moveMod, cenX + 1);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Control");
//	  config.setXCoord(moveMod, cenX - 9);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Control");
//	  config.setXCoord(moveMod, cenX - 9);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX - 5);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX - 6);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX - 3);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX - 4);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX - 5);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX - 6);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 3);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 4);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 6);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 7);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 3);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 4);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 3);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 4);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 3);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 4);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 6);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX + 7);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX - 2);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Dormitory");
//	  config.setXCoord(moveMod, cenX - 3);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX + 5);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX + 8);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX + 6);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX - 5);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX + 5);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX + 8);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX - 4);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX + 6);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX - 4);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Sanitation");
//	  config.setXCoord(moveMod, cenX - 4);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Medical");
//	  config.setXCoord(moveMod, cenX + 8);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Medical");
//	  config.setXCoord(moveMod, cenX + 8);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Medical");
//	  config.setXCoord(moveMod, cenX - 8);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Medical");
//	  config.setXCoord(moveMod, cenX - 9);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Airlock");
//	  config.setXCoord(moveMod, cenX - 10);
//	  config.setYCoord(moveMod, cenY - 2);
//	  
//	  moveMod = getModuleOfType("Airlock");
//	  config.setXCoord(moveMod, cenX - 10);
//	  config.setYCoord(moveMod, cenY + 2);
//	  
//	  moveMod = getModuleOfType("Airlock");
//	  config.setXCoord(moveMod, cenX + 9);
//	  config.setYCoord(moveMod, cenY - 2);
//	  
//	  moveMod = getModuleOfType("Airlock");
//	  config.setXCoord(moveMod, cenX + 9);
//	  config.setYCoord(moveMod, cenY + 2);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX - 2);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX - 2);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX - 2);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX - 1);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX + 2);
//	  config.setYCoord(moveMod, cenY + 3);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX + 2);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX + 2);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX + 2);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Food & Water");
//	  config.setXCoord(moveMod, cenX - 1);
//	  config.setYCoord(moveMod, cenY - 3);
//	  
//	  moveMod = getModuleOfType("Gym & Relaxation");
//	  config.setXCoord(moveMod, cenX + 5);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Gym & Relaxation");
//	  config.setXCoord(moveMod, cenX + 5);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  moveMod = getModuleOfType("Gym & Relaxation");
//	  config.setXCoord(moveMod, cenX - 3);
//	  config.setYCoord(moveMod, cenY - 1);
//	  
//	  moveMod = getModuleOfType("Gym & Relaxation");
//	  config.setXCoord(moveMod, cenX - 3);
//	  config.setYCoord(moveMod, cenY + 1);
//	  
//	  recenterize(config, cenX, cenY);
//	 }
// 
}

