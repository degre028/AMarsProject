package project;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;



import com.google.gwt.junit.tools.GWTTestSuite;

import project.backend.MarsModule;
import project.backend.ModuleSet;
import project.simulation.Sim;



public class TestMin  {
	/*
	 * Checking to see if the following 10 modules are present
	 * Airlock, Control, Power, Food and Water Storage
	 * Dormitory, Canteen, Sanitation, 3 Plain
	 */
	@Test
	public void TestTest()  {
		final ModuleSet modset = new ModuleSet("demo", null);
		modset.addModule(new MarsModule(32,17,174,"Good",0));	//Airlock
		modset.addModule(new MarsModule(102,18,70,"Good",0)); 	//Dormitory

		Sim sim = new Sim();
		assertTrue(sim.testJUnit(modset));
	}
	
//	@Test
//	public void testMin() {
//		final ModuleSet testMin = new ModuleSet("demo", null);
//		
//		testMin.addModule(new MarsModule(32,16,2,"Good",0));	//Plain
//		testMin.addModule(new MarsModule(88,61,3,"Good",0)); 	//Plain
//		testMin.addModule(new MarsModule(32,65,4,"Good",0)); 	//Plain
//		testMin.addModule(new MarsModule(102,18,70,"Good",0)); 	//Dormitory
//		testMin.addModule(new MarsModule(99,14,95,"Good",0));	//Sanitation
//		testMin.addModule(new MarsModule(1,19,141,"Good",0));	//Canteen
//		testMin.addModule(new MarsModule(55,5,154,"Good",0));	//Power
//		testMin.addModule(new MarsModule(100,100,164,"Good",0));//Control
//		testMin.addModule(new MarsModule(32,17,174,"Good",0));	//Airlock
//		testMin.addModule(new MarsModule(32,17,111,"Good",0));	//Food and Water
//		
//		Sim sim = new Sim();
//		assertTrue(sim.isMinimum(testMin));        
//    }
}