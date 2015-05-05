package project.simulation;

import project.backend.MarsConfiguration;
import project.backend.ModuleSet;



public class FullConfigMaker {
	private ModuleSet modset;
	 public FullConfigMaker(ModuleSet modset) {
		  this.modset = modset;
		 }
	 
	 public MarsConfiguration genFullConfig(ModuleSet modset, int numConf) {
		  MarsConfiguration config = new MarsConfiguration(modset);
		  
		  
		  return config;
	 }
}
