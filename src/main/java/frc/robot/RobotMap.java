/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

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
    g1_rightXAxis = 2,
    g1_rightYAxis = 3,
    
    leftMotorPort = 1,
    rightMotorPort = 0,

    leftEncoderChannelA = 8,
    leftEncoderChannelB = 9,
    rightEncoderChannelA = 6,
    rightEncoderChannelB = 7,

    gearPistonFor = 2,
    gearPistonRev = 3,
    ButtonTriangle = 1,
    
    intakeMotorPort = 3,
    ButtonCircle = 2,
    ButtonX = 3;

  }
  public class Values{
    public static final double
    ticksPerFoot = 7565;
  }
}
