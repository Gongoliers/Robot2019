package frc.robot.subsystems;

import org.junit.Test;

import static org.junit.Assert.*;

import com.thegongoliers.mockHardware.output.MockSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;
import com.thegongoliers.talonsrx.MockTalonSRX;

import org.junit.Before;

/**
 * Tests for the HatchManipulator subsystem
 */
public class HatchManipulatorTest {

    private HatchManipulator hatchManipulator;
    private IPiston piston;
    private MockTalonSRX talon;
    private MockPotentiometer potentiometer;

    @Before
    public void setup(){
        piston = new Piston(new MockSolenoid());
        talon = new MockTalonSRX();
        potentiometer = new MockPotentiometer(); // This should have been in the Library of Gongolierium, it's on my todo list though
        hatchManipulator = new HatchManipulator(piston, talon, potentiometer);
    }

    @Test
    public void testDefaultState(){
        // The manipulator should be retracted
        assertTrue(hatchManipulator.isRetracted());
        assertFalse(hatchManipulator.isExtended()); // Sanity check

        // And it should not be inverted
        assertFalse(hatchManipulator.isInverted());

        // And the motor should be stopped
        assertEquals(talon.get(), 0, 0.0001);
    }

    @Test
    public void testExtend(){
        // When the hatch manipulator extends
        hatchManipulator.extend();
        
        // The pistons should be extended
        assertTrue(piston.isExtended());

        // And it should read as extended
        assertTrue(hatchManipulator.isExtended());
    }

    @Test
    public void testRetract(){
        // When the hatch manipulator retracts
        hatchManipulator.retract();

        // The pistons should be retracted
        assertTrue(piston.isRetracted());

        // And it should read as retracted
        assertTrue(hatchManipulator.isRetracted());
    }

    @Test
    public void testInvert(){
        // When the hatch maniplator becomes inverted
        hatchManipulator.setInverted(true);

        // The pistons should be inverted
        assertTrue(piston.isInverted());

        // And it should read as inverted
        assertTrue(hatchManipulator.isInverted());
    }

    @Test
    public void testUp(){
        // When the hatch manipulator is going up
        hatchManipulator.up(1.0);

        // The talon should be positive
        assertEquals(talon.get(), 1.0, 0.0001);
    }

    @Test
    public void testDown(){
        // When the hatch manipulator is going up
        hatchManipulator.down(1.0);

        // The talon should be negative
        assertEquals(talon.get(), -1.0, 0.0001);
    }

    @Test
    public void testStopArm(){
        // When the hatch manipulator is stopped while moving
        hatchManipulator.up(1.0);
        hatchManipulator.stopArm();

        // The talon should be 0
        assertEquals(talon.get(), 0.0, 0.0001);
    }

    @Test
    public void testIsAtBottom(){
        // When the hatch manipulator is at the bottom
        potentiometer.set(HatchManipulator.BOTTOM_ANGLE);

        // The hatch manipulator should read at bottom
        assertTrue(hatchManipulator.isAtBottom());

        // And not at the top
        assertFalse(hatchManipulator.isAtTop());
    }

    @Test
    public void testIsAtTop(){
        // When the hatch manipulator is at the top
        potentiometer.set(HatchManipulator.TOP_ANGLE);

        // The hatch manipulator should read at top
        assertTrue(hatchManipulator.isAtTop());

        // And not at the bottom
        assertFalse(hatchManipulator.isAtBottom());
    }

    @Test
    public void testAngleInBetween(){
        // When the hatch manipulator is between the bottom and top
        potentiometer.set((HatchManipulator.BOTTOM_ANGLE + HatchManipulator.TOP_ANGLE) / 2.0);

        // The hatch manipulator should not be at the bottom
        assertFalse(hatchManipulator.isAtBottom());

        // And not at the top
        assertFalse(hatchManipulator.isAtTop());
    }

    @Test
    public void testPIDMethods(){
        // Return PID input should be the potentiometer angle
        potentiometer.set(20);
        assertEquals(hatchManipulator.returnPIDInput(), 20, 0.0001);

        potentiometer.set(50);
        assertEquals(hatchManipulator.returnPIDInput(), 50, 0.0001);

        // Use PID output should be the talon motor
        hatchManipulator.usePIDOutput(0.5);
        assertEquals(talon.get(), 0.5, 0.0001);

        hatchManipulator.usePIDOutput(-0.25);
        assertEquals(talon.get(), -0.25, 0.0001);
    }

}
