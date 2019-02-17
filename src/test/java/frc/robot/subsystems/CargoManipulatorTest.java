package frc.robot.subsystems;

import org.junit.Test;

import static org.junit.Assert.*;

import com.thegongoliers.mockHardware.input.MockSwitch;
import com.thegongoliers.mockHardware.output.MockSolenoid;
import com.thegongoliers.output.Piston;
import com.thegongoliers.output.interfaces.IPiston;
import com.thegongoliers.talonsrx.MockTalonSRX;

import org.junit.Before;

/**
 * Tests for the CargoManipulator subsystem
 */
public class CargoManipulatorTest {

    private CargoManipulator cargoManipulator;
    private IPiston piston1, piston2;
    private MockTalonSRX rollerTalon;
    private MockTalonSRX wristTalon;
    private MockPotentiometer potentiometer;
    private MockSwitch cargoSwitch;

    @Before
    public void setup(){
        piston1 = new Piston(new MockSolenoid());
        piston2 = new Piston(new MockSolenoid());
        rollerTalon = new MockTalonSRX();
        wristTalon = new MockTalonSRX();
        potentiometer = new MockPotentiometer(); // This should have been in the Library of Gongolierium, it's on my todo list though
        cargoSwitch = new MockSwitch();

        cargoManipulator = new CargoManipulator(piston1, piston2, cargoSwitch, potentiometer, wristTalon, rollerTalon);
    }

    @Test
    public void testDefaultState(){
        // The manipulator should be retracted
        assertTrue(cargoManipulator.isRetracted());
        assertFalse(cargoManipulator.isExtended()); // Sanity check

        // And it should not be inverted
        assertFalse(cargoManipulator.isInverted());

        // And all motors should be stopped
        assertEquals(wristTalon.get(), 0, 0.0001);
        assertEquals(rollerTalon.get(), 0, 0.0001);

        // And no cargo being held
        assertFalse(cargoManipulator.hasCargo());
    }

    @Test
    public void testExtend(){
        // When the cargo manipulator extends
        cargoManipulator.extend();
        
        // The pistons should be extended
        assertTrue(piston1.isExtended());
        assertTrue(piston2.isExtended());

        // And it should read as extended
        assertTrue(cargoManipulator.isExtended());
        assertFalse(cargoManipulator.isRetracted());
    }

    @Test
    public void testRetract(){
        // When the cargo manipulator retracts
        cargoManipulator.retract();

        // The pistons should be retracted
        assertTrue(piston1.isRetracted());
        assertTrue(piston2.isRetracted());

        // And it should read as retracted
        assertTrue(cargoManipulator.isRetracted());
    }

    @Test
    public void testInvert(){
        // When the cargo maniplator becomes inverted
        cargoManipulator.setInverted(true);

        // The pistons should be inverted
        assertTrue(piston1.isInverted());
        assertTrue(piston2.isInverted());

        // And it should read as inverted
        assertTrue(cargoManipulator.isInverted());
    }

    @Test
    public void testUp(){
        // When the cargo manipulator is going up
        cargoManipulator.raiseWrist(1.0);

        // The talon should be positive
        assertEquals(wristTalon.get(), 1.0, 0.0001);
    }

    @Test
    public void testDown(){
        // When the cargo manipulator is going up
        cargoManipulator.lowerWrist(1.0);

        // The talon should be negative
        assertEquals(wristTalon.get(), -1.0, 0.0001);
    }

    @Test
    public void testStopArm(){
        // When the cargo manipulator is stopped while moving
        cargoManipulator.raiseWrist(1.0);
        cargoManipulator.stopWrist();

        // The talon should be 0
        assertEquals(wristTalon.get(), 0.0, 0.0001);
    }

    @Test
    public void testIntake(){
        // When the cargo manipulator is intaking
        cargoManipulator.intake(1.0);

        // The talon should be positive
        assertEquals(rollerTalon.get(), 1.0, 0.0001);
    }

    @Test
    public void testOuttake(){
        // When the cargo manipulator is outtaking
        cargoManipulator.outtake(1.0);

        // The talon should be negative
        assertEquals(rollerTalon.get(), -1.0, 0.0001);
    }

    public void testStopIntake(){
        // When the cargo manipulator is stopped while intaking
        cargoManipulator.intake(1.0);
        cargoManipulator.stopRollers();

        // The talon should be 0
        assertEquals(rollerTalon.get(), 0.0, 0.0001);
    }

    @Test
    public void testPIDMethods(){
        // Return PID input should be the potentiometer angle
        potentiometer.set(20);
        assertEquals(cargoManipulator.returnPIDInput(), 20, 0.0001);

        potentiometer.set(50);
        assertEquals(cargoManipulator.returnPIDInput(), 50, 0.0001);

        // Use PID output should be the talon motor
        cargoManipulator.usePIDOutput(0.5);
        assertEquals(wristTalon.get(), 0.5, 0.0001);

        cargoManipulator.usePIDOutput(-0.25);
        assertEquals(wristTalon.get(), -0.25, 0.0001);
    }

    @Test
    public void testHasCargo(){
        // Activate cargo limit switch
        cargoSwitch.setTriggered(true);

        assertTrue(cargoManipulator.hasCargo());
    }

}
