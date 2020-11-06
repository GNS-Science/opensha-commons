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
import org.opensha.commons.util.FaultUtils;

/**
 * <b>Title:</b>TMG2017_MagAreaRel<br>
 *
 * <b>Description:</b>
 * <p>
 * This implements the set of magnitude versus rupture area relation of
 * Thingbaijam K.K.S., P.M. Mai and K. Goda (2017, Bull. Seism. Soc. Am., 107,
 * 2225–2246.
 * 
 * Addition to rake, we also use regime (="crustal" or "interface") as an
 * additional parameter to distinguish shallow crustal and subduction-interface.
 * The default is "crustal". However, the relations for normal faults are also
 * applicable to inslab events.
 * 
 * Notes: (1) Valid rake is in the range -180 to 180 degrees. (2) the standard
 * deviation for area as a function of mag is given for log(area) (base-10) not
 * area. (2) The current implementation does not handle fixed seismogenic width
 * (or saturated rupture width); It will require implementing MagLenRel and
 * MagWidRel as well.
 * 
 * Also see: https://github.com/thingbaijam/sceqsrc
 * 
 * </p>
 *
 * @version 0.0
 */

public class TMG2017_MagAreaRel extends MagAreaRelationship {

	public enum Regime{
		CRUSTAL,
		INTERFACE
	}

	final static String C = "TMG2017_MagAreaRel";
	public final static String NAME = "Thingbaijam et al.(2017)";
	protected Regime regime = Regime.CRUSTAL;

	/**
	 * set regime
	 * 
	 * @param regime the regime
	 */
	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	/**
	 * Computes the median magnitude from rupture area for previously set rake and
	 * regime values.
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

		if (regime == Regime.CRUSTAL) {
			if ((rake <= 45 && rake >= -45) || rake >= 135 || rake <= -135) {
				// strike slip
				return 3.701 + 1.062 * Math.log(area) * lnToLog;
			} else if (rake > 0) {
				// shallow crustal thrust/reverse faulting
				return 4.158 + 0.953 * Math.log(area) * lnToLog;
			} else {
				// normal faulting
				return 3.157 + 1.238 * Math.log(area) * lnToLog;
			}
		} else {
			// subduction-interface
			return 3.469 + 1.054 * Math.log(area) * lnToLog;
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
		if (Double.isNaN(rake))
			// Not available
			return Double.NaN;

		if (regime == Regime.CRUSTAL) {
			if ((rake <= 45 && rake >= -45) || rake >= 135 || rake <= -135) {
				// strike slip
				return 0.184;
			} else if (rake > 0) {
				// thrust/reverse
				return 0.121;
			} else {
				// normal
				return 0.181;
			}
		} else {
			// else subduction-interface
			return 0.150;
		}
	}

	public double getMagStdDev(Double rake, Regime regime) {
		this.setRegime(regime);
		this.setRake(rake);
		return getMagStdDev();
	}

	/**
	 * overload to additionally consider regime
	 */
	public double getMedianMag(double area, double rake, Regime regime) {
		this.setRegime(regime);
		this.setRake(rake);
		return this.getMedianMag(area);
	}

	/**
	 * Computes the median rupture area from magnitude (for the previously set rake
	 * and regime values).
	 * 
	 * @param mag - moment magnitude
	 * @return median area in km
	 */
	public double getMedianArea(double mag) {
		if (Double.isNaN(rake))
			// this estimate is not available
			return Double.NaN;
		if (regime == Regime.CRUSTAL) {
			if ((rake <= 45 && rake >= -45) || rake >= 135 || rake <= -135) {
				// strike slip
				return Math.pow(10.0, -3.486 + 0.942 * mag);
			} else if (rake > 0) {
				// shallow thrust/reverse
				return Math.pow(10.0, -4.362 + 1.049 * mag);
			} else {
				// normal
				return Math.pow(10.0, -2.551 + 0.808 * mag);
			}
		} else {
			// subduction-interface
			return Math.pow(10.0, -3.292 + 0.949 * mag);
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

	public double getAreaStdDev(Double rake, Regime regime) {
		this.setRegime(regime);
		this.setRake(rake);
		return getMagStdDev();
	}

	/**
	 * overload to additionally consider regime
	 */
	public double getMedianArea(double mag, double rake, Regime regime) {
		this.setRegime(regime);
		this.setRake(rake);
		return this.getMedianArea(mag);
	}

	/**
	 * Returns the name of the object
	 *
	 */
	public String getName() {
		String type;
		if (Double.isNaN(rake)) {
			type = "not available";
		}

		if (regime == Regime.CRUSTAL) {
			if ((rake <= 45 && rake >= -45) || rake >= 135 || rake <= -135) {
				type = "strike-slip";
			} else if (rake > 0) {
				type = "shallow reverse-faulting";
			} else {
				type = "normal-faulting";
			}
		} else {
			type = "interface";
		}

		return NAME + " for " + type + " events";
	}
}
