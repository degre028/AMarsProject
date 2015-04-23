package project.simulation;

import project.backend.ModuleSet;

public class Sim {
	public Sim() {
		
	}
	
		
	/*
	 * Must check to see if the following 7 modules are present
	 * Airlock, Control, Power, Food and Water Storage
	 * Dormitory, Canteen, Sanitation, 3 Plain
	 */
//	public boolean testJUnit(ModuleSet modset){
//		int test = modset.getCount("Dormitory");
//		if(test == 1) {
//			return true;
//		}
//		return false;
//	}
	
	public boolean isMinimum(ModuleSet modset) {
		boolean isMin = false;
		
		int countDorm = modset.getCount("Dormitory");
		int countAir = modset.getCount("Airlock");
		int countPow = modset.getCount("Power");
		int countCant = modset.getCount("Canteen");
		int countFoodWater = modset.getCount("Food & Water");
		int countSant = modset.getCount("Sanitation");
		int countCtrl = modset.getCount("Control");
		int countPlain = modset.getCount("Plain");
		

		
		if(modset != null){
			if(countDorm >= 1 && countAir >= 1 && countPow >= 1 &&
					countCant >= 1 && countFoodWater >= 1 &&
					countSant >= 1 && countCtrl >= 1  && countPlain >= 3){
				isMin = true;
				return isMin;
			}						
		}
		return isMin;
	}
	
}
