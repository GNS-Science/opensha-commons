package org.opensha.commons.calc.magScalingRelations.magScalingRelImpl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
//import org.opensha.commons.calc.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestTMG2017CruMagAreaRel.class,
        TestTMG2017SubMagAreaRel.class
})

public class MagScalingRelSuite {
	public static void main(String args[])
	{
		org.junit.runner.JUnitCore.runClasses(MagScalingRelSuite .class);
	}
}

