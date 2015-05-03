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
 public MarsConfiguration genMinimumConfig(ModuleSet modset) {
  MarsConfiguration config = new MarsConfiguration(modset);
  //makeMin1(config);
  makeMin2(config);
  return config;
 }
 
 /**
  * Method returns an appropriate module index.
  */
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
   config.setXCoord(i, oldX+newX);
   config.setYCoord(i, oldY+newY);
  }
 }
 
 private void makeMin1(MarsConfiguration config) {
  int cenX = getCenterOfGravity(true);
  int cenY = getCenterOfGravity(false);

  
  int moveMod = -1;
  //Window.alert(""+cenY);  
  moveMod = getModuleOfType("Plain");
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Plain");
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Plain");
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Canteen");
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Power");
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Control");
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Dormitory");
  config.setXCoord(moveMod, cenX + 2);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Sanitation");
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Medical");
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Airlock");
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Food & Water");
  config.setXCoord(moveMod, cenX - 2);
  config.setYCoord(moveMod, cenY);
  
  recenterize(config, cenX, cenY);
 }
 
 private void makeMin2(MarsConfiguration config) {
  int cenX = getCenterOfGravity(true);
  int cenY = getCenterOfGravity(false);

  
  int moveMod = -1;
  //Window.alert(""+cenY);  
  moveMod = getModuleOfType("Plain");
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Plain");
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Plain");
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Canteen");
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Power");
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Sanitation");
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY - 1);
  
  moveMod = getModuleOfType("Dormitory");
  config.setXCoord(moveMod, cenX - 1);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Control");
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY + 1);
  
  moveMod = getModuleOfType("Medical");
  config.setXCoord(moveMod, cenX + 1);
  config.setYCoord(moveMod, cenY);
  
  moveMod = getModuleOfType("Airlock");
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY - 2);
  
  moveMod = getModuleOfType("Food & Water");
  config.setXCoord(moveMod, cenX);
  config.setYCoord(moveMod, cenY + 2);
  
  recenterize(config, cenX, cenY);
 }
 
 }