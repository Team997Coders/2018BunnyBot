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
  public class Ports {
    public static final int
    gamepad1 = 0,
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

    gearPistonFor = 2,
    gearPistonRev = 3,
    ButtonY = 3,
    
    intakeMotorPort = 7,
    outtakeMotorPort = 8,
    ButtonB = 1,
    ButtonX = 2;
    public final SerialPort.Port AHRS = SerialPort.Port.kUSB;

  }
  public class Values{
    public static final double
    ticksPerFoot = 1/7565,
    DriveP = 0;
      //.00002; 
  }
}