package frc.robot.subsystems;

import org.junit.Test;

import static org.junit.Assert.*;

import com.thegongoliers.mockHardware.output.MockSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;

import org.junit.Before;

/**
 * Tests for the HABClimber subsystem
 */
public class HABClimberTest {

    private HABClimber habClimber;
    private IPiston bigPiston, piston1, piston2;

    @Before
    public void setup(){
        piston1 = new Piston(new MockSolenoid());
        piston2 = new Piston(new MockSolenoid());
        bigPiston = new Piston(new MockSolenoid());

        habClimber = new HABClimber(bigPiston, piston1, piston2);
    }

    @Test
    public void testDefaultState(){
        // The climber should be retracted
        assertTrue(habClimber.isRetracted());
        assertFalse(habClimber.isExtended()); // Sanity check
        assertTrue(bigPiston.isRetracted());

        // And it should not be inverted
        assertFalse(habClimber.isInverted());

        // And the skids should be retracted
        assertTrue(piston1.isRetracted());
        assertTrue(piston2.isRetracted());
    }

    @Test
    public void testExtendingWithSafetyOn() {
        // Safety Enabled
        habClimber.setClimberSafety(true);

        // Try deploying skids and extending piston
        habClimber.deploySkids();
        habClimber.extend();

        // Should not be deployed or extended
        assertFalse(habClimber.isExtended());
        assertTrue(habClimber.isRetracted());
        assertTrue(piston1.isRetracted());
        assertTrue(piston2.isRetracted());
        assertTrue(bigPiston.isRetracted());
    }

    @Test
    public void testExtendingWithSafetyOff() {
        // Safety Disabled
        habClimber.setClimberSafety(false);

        // Try deploying skids and extending piston
        habClimber.deploySkids();
        habClimber.extend();

        // Should be deployed or extended
        assertTrue(habClimber.isExtended());
        assertFalse(habClimber.isRetracted());
        assertFalse(piston1.isRetracted());
        assertFalse(piston2.isRetracted());
        assertFalse(bigPiston.isRetracted());
    }

}
