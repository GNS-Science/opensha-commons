package org.opensha.commons.calc.magScalingRelations.magScalingRelImpl;

import static org.junit.Assert.*;

import org.junit.Test;

// tested against sceqsrc (https://github.com/thingbaijam/sceqsrc)

public class TestTMG2017_MagAreaRel {

    @Test
    public void testGetMedianMag() {
        TMG2017_MagAreaRel tmg = new TMG2017_MagAreaRel();

        assertTrue(Double.isNaN(tmg.getMedianMag(1, Double.NaN, TMG2017_MagAreaRel.Regime.CRUSTAL)));

        // area 1
        assertEquals(4.158246, tmg.getMedianMag(1, 90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(3.157178, tmg.getMedianMag(1, -90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(3.700637, tmg.getMedianMag(1, 0, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(3.468915, tmg.getMedianMag(1, 90, TMG2017_MagAreaRel.Regime.INTERFACE), 0.000001);
        // area 500
        assertEquals(6.731144, tmg.getMedianMag(500, 90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(6.497488, tmg.getMedianMag(500, -90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(6.565786, tmg.getMedianMag(500, 0, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(6.312929, tmg.getMedianMag(500, 90, TMG2017_MagAreaRel.Regime.INTERFACE), 0.000001);
        // area 10000
        assertEquals(7.971401, tmg.getMedianMag(10000, 90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(8.107673, tmg.getMedianMag(10000, -90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(7.946921, tmg.getMedianMag(10000, 0, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(7.683878, tmg.getMedianMag(10000, 90, TMG2017_MagAreaRel.Regime.INTERFACE), 0.000001);
        // area 100000
        assertEquals(8.924690, tmg.getMedianMag(100000, 90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(9.345297, tmg.getMedianMag(100000, -90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(9.008493, tmg.getMedianMag(100000, 0, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(8.737619, tmg.getMedianMag(100000, 90, TMG2017_MagAreaRel.Regime.INTERFACE), 0.000001);
    }

    @Test
    public void testGetMedianArea() {
        TMG2017_MagAreaRel tmg = new TMG2017_MagAreaRel();

        assertTrue(Double.isNaN(tmg.getMedianArea(1, Double.NaN, TMG2017_MagAreaRel.Regime.CRUSTAL)));

        // magnitude 4
        assertEquals(0.682339, tmg.getMedianArea(4, 90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(4.797334, tmg.getMedianArea(4, -90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(1.914256, tmg.getMedianArea(4, 0, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(3.191538, tmg.getMedianArea(4, 90, TMG2017_MagAreaRel.Regime.INTERFACE), 0.000001);
        // magnitude 6
        assertEquals(85.506671, tmg.getMedianArea(6, 90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(198.152703, tmg.getMedianArea(6, -90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(146.554784, tmg.getMedianArea(6, 0, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(252.348077, tmg.getMedianArea(6, 90, TMG2017_MagAreaRel.Regime.INTERFACE), 0.000001);
        // magnitude 8
        assertEquals(10715.193052, tmg.getMedianArea(8, 90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(8184.647881, tmg.getMedianArea(8, -90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(11220.184543, tmg.getMedianArea(8, 0, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(19952.623150, tmg.getMedianArea(8, 90, TMG2017_MagAreaRel.Regime.INTERFACE), 0.000001);
        // magnitude 9
        assertEquals(119949.930315, tmg.getMedianArea(9, 90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(52601.726639, tmg.getMedianArea(9, -90, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(98174.794302, tmg.getMedianArea(9, 0, TMG2017_MagAreaRel.Regime.CRUSTAL), 0.000001);
        assertEquals(177418.948089, tmg.getMedianArea(9, 90, TMG2017_MagAreaRel.Regime.INTERFACE), 0.000001);
    }
}
