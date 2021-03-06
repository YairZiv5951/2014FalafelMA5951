
package org.usfirst.frc.team5951.robot;

import org.usfirst.frc.team5951.subsystems.ForkJoint;
import org.usfirst.frc.team5951.subsystems.LowerJoint;
import org.usfirst.frc.team5951.subsystems.Thrower;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is a demo program showing the use of the RobotDrive class. The
 * SampleRobot class is the base of a robot application that will automatically
 * call your Autonomous and OperatorControl methods at the right time as
 * controlled by the switches on the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */
public class Robot extends SampleRobot {
	RobotDrive robotDrive;
	Thrower thrower;
	ForkJoint forkJoint;
	LowerJoint lowerJoint;
	Joystick stick;

	public Robot() {
		robotDrive = new RobotDrive(Ports.chassisLeftFront, Ports.chassisLeftBack, Ports.chassisRightFront,
				Ports.chassisRightBack);
		stick = new Joystick(0);
		thrower = new Thrower();
		forkJoint = new ForkJoint();
		lowerJoint = new LowerJoint();
	}

	public void robotInit() {

	}

	/**
	 */
	public void autonomous() {
		
	}

	/**
	 * 
	 */
	public void operatorControl() {
		robotDrive.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			robotDrive.arcadeDrive(stick); // drive with arcade style (use right
											// stick)
			Timer.delay(0.005); // wait for a motor update time
			if (stick.getRawButton(ButtonPorts.shoot)) {
				this.thrower.throwTubes();
			}
			if (stick.getRawButton(ButtonPorts.openThrower)) {
				this.thrower.openThrower();
			}
			if (stick.getRawButton(ButtonPorts.closeThrower)) {
				this.thrower.closeThrower();
			}
			if (stick.getRawButton(ButtonPorts.forkUp)) {
				this.forkJoint.rotateUp();
			}
			if (stick.getRawButton(ButtonPorts.forkDown)) {
				this.forkJoint.rotateDown();
			}
			if (!stick.getRawButton(ButtonPorts.forkUp) && !stick.getRawButton(ButtonPorts.forkDown)) {
				this.forkJoint.stop();
			}
			if (stick.getRawButton(ButtonPorts.lowerJointUp)) {
				this.lowerJoint.rotateUp();
			}
			if (stick.getRawButton(ButtonPorts.lowerJointDown)) {
				this.lowerJoint.rotateDown();
			}
			if (!stick.getRawButton(ButtonPorts.lowerJointUp) && !stick.getRawButton(ButtonPorts.lowerJointDown)) {
				this.lowerJoint.stop();
			}
		}
	}

	/**
	 * Runs during test mode
	 */
	public void test() {
	}
}
