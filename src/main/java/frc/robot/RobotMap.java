/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static class Ports {
    public static final int
    gamepad1 = 0,
    gamepad2 = 1,
    g1_leftYAxis = 1,
    g1_rightXAxis = 4,
    g1_rightYAxis = 5,
    
    leftTalonPort = 9,
    rightTalonPort =10,

    leftVictor1Port = 1,
    leftVictor2Port = 2,
    rightVictor1Port = 3,
    rightVictor2Port = 4,

    leftEncoderChannelA = 8,
    leftEncoderChannelB = 9,
    rightEncoderChannelA = 6,
    rightEncoderChannelB = 7,

    bunnyEjectorSolenoidPort1 = 4,
    bunnyEjectorSolenoidPort2 = 5,
    ballEjectorMotorPort = 9,
    gearPistonFor = 2,
    gearPistonRev = 3,
    ButtonY = 4,
    
    intakeMotorPort = 7,
    outtakeMotorPort = 8,
    ButtonB = 2,
    ButtonX = 3;

    public static final SerialPort.Port AHRS = SerialPort.Port.kUSB;
  }
  public class Values{
    public static final double
    ticksPerFoot = 4490.5,
    DriveAngleP = .01,
    DriveToDistanceMargin = 50,
    DriveP = 0, //.00002
    intakeMotorPort = 7, //Temporarily flipped before we fix version issues.
    outtakeMotorPort = 8,
    ButtonB = 1,
    ButtonX = 3,
    //shiftVelocityUp = 10.55* .97;
    shiftVelocityUp = 1;

  }
}