/*******************************************************************************
 * Copyright 2009 OpenSHA.org in partnership with
 * the Southern California Earthquake Center (SCEC, http://www.scec.org)
 * at the University of Southern California and the UnitedStates Geological
 * Survey (USGS; http://www.usgs.gov)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.opensha.commons.calc.magScalingRelations.magScalingRelImpl;

import org.opensha.commons.calc.magScalingRelations.MagAreaRelationship;
import org.opensha.commons.exceptions.InvalidRangeException;
import org.opensha.commons.util.FaultUtils;

/**
 * <b>Title:</b>TMG2017_MagAreaRel<br>
 *
 * <b>Description:</b>
 * <p>
 * <b>Description:</b>
 * <p>
 * This implements SUBDUCTION specific magnitude versus rupture area relations
 * of Thingbaijam K.K.S., P.M. Mai and K. Goda 2017, Bull. Seism. Soc. Am., 107,
 * 2225–2246.
 * 
 * 
 * We consider rake to differentiate different broad faulting-types: interface
 * (reverse-faulting) and inslab (normal-faulting). The classification is as
 * follows: Interface (reverse-faulting): Rake angles within 45 to 135, and
 * Inslab (normal-faulting): Rake angles within -45 to -135.
 * 
 * Notes: [1] Relations for strike-slip events are not defined. [2] The standard
 * deviation for area as a function of mag is given for log(area) (base-10) not
 * area.
 * 
 * Also see: https://github.com/thingbaijam/sceqsrc
 * </p>
 *
 * @version 0.0
 */

public class TMG2017SubMagAreaRel extends MagAreaRelationship {

	final static String C = "TMG2017SubMagAreaRel";
	public final static String NAME = "Thingbaijam et al.(2017)";

	/**
	 * Computes the median magnitude from rupture area for previously set rake
	 * values that distinguish between interface (reverse) events and inslab normal
	 * faulting events .
	 * 
	 * @param area in km
	 * @return median magnitude MW
	 */
	public double getMedianMag(double area) {

		// FaultUtils.assertValidRake(rake) does not invalidate NaN - :(
		if (Double.isNaN(rake)) {
			// return NaN because this estimate is not available.
			return Double.NaN;
		}

		if ((rake <= 45 && rake >= -45) || rake >= 135 || rake <= -135) {
			return Double.NaN;
		} else if (rake > 0) {
			// interface
			return 3.469 + 1.054 * Math.log(area) * lnToLog;
		} else {
			// normal faulting
			return 3.157 + 1.238 * Math.log(area) * lnToLog;
		}
	}

	/**
	 * Gives the standard deviation for the magnitude as a function of area for
	 * previously-set rake and regime values
	 * 
	 * @param area in km
	 * @return standard deviation
	 */
	public double getMagStdDev() {
		if (Double.isNaN(rake)) {
			// Not available
			return Double.NaN;
		}

		if ((rake <= 45 && rake >= -45) || rake >= 135 || rake <= -135) {
			return Double.NaN;
		} else if (rake > 0) {
			// interface
			return 0.150;
		} else {
			// normal
			return 0.181;
		}
	}

	/**
	 * Computes the median rupture area from magnitude (for the previously set rake
	 * and regime values).
	 * 
	 * @param mag - moment magnitude
	 * @return median area in km
	 */
	public double getMedianArea(double mag) {
		if (Double.isNaN(rake)) {
			// this estimate is not available
			return Double.NaN;
		}
		if ((rake <= 45 && rake >= -45) || rake >= 135 || rake <= -135) {
			// strike slip
<<<<<<< HEAD
			return  Double.NaN;
=======
			return Math.pow(10.0, -3.486 + 0.942 * mag);
>>>>>>> f7db9733c6623890e744e003aa0de0caf82bda05
		} else if (rake > 0) {
			// interface
			return Math.pow(10.0, -3.292 + 0.949 * mag);
		} else {
			// inslab normal
			return Math.pow(10.0, -2.551 + 0.808 * mag);
		}
	}

	/**
	 * Computes the standard deviation of log(area) (base-10) from magnitude (for
	 * the previously set rake and regime values)
	 * 
	 * @return standard deviation
	 */
	public double getAreaStdDev() {
		return getMagStdDev();

	}
	
    /**
     * This overrides the parent method to disallow strike-slip
     * faulting type.
     * @param rake
     */
    public void setRake(double rake) {
    	if ((rake <= 45 && rake >= -45) || rake >= 135 || rake <= -135) {
    		throw new InvalidRangeException(
<<<<<<< HEAD
    				"Rake angle should be either within (45, 135) for interface or (-45, -135) for inslab-normal events"
=======
    				"Rake angle should be either within (45, 135) for interface or (-45, -135) for inslab-normal"
>>>>>>> f7db9733c6623890e744e003aa0de0caf82bda05
    				);
    	}	
    	else {
    		FaultUtils.assertValidRake(rake);
    		this.rake = rake;
    	}
    }

	/**
	 * Returns the name of the object
	 *
	 */
	public String getName() {
		String type;
		if (Double.isNaN(rake)) {
			type = "InvalidRake";
		}

		if ((rake <= 45 && rake >= -45) || rake >= 135 || rake <= -135) {
			type = "InvalidRake";
		} else if (rake > 0) {
			type = "Interface";
		} else {
			type = "Inslab-Normal";
		}
		return NAME + " for " + type + " events";
	}
}
