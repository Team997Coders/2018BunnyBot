/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  Joystick gamepad1;
  Joystick gamepad2;

  // GamePad 1
  JoystickButton shift;
  JoystickButton reverse;

  // GamePad 2
  JoystickButton collect;
  JoystickButton eject;
  JoystickButton outtake;
  JoystickButton toggle;
  JoystickButton yeet;

  public OI() {
    gamepad1 = new Joystick(RobotMap.Ports.gamepad1);
    gamepad2 = new Joystick(RobotMap.Ports.gamepad2);

    shift = new JoystickButton(gamepad1, RobotMap.Ports.ButtonX);
    shift.whenPressed(new GearShift());

    reverse = new JoystickButton(gamepad1, RobotMap.Ports.ButtonY);
    reverse.whenPressed(new SwapDirection());

    collect = new JoystickButton(gamepad2, RobotMap.Ports.ButtonA);
    collect.whileHeld(new CollectBalls());

    eject = new JoystickButton(gamepad2, RobotMap.Ports.ButtonY);
    eject.whileHeld(new EjectBalls());

    outtake = new JoystickButton(gamepad2, RobotMap.Ports.ButtonX);
    outtake.whileHeld(new OutTakeBalls()); //TODO: Might not like, you know, work.

    yeet = new JoystickButton(gamepad2, RobotMap.Ports.ButtonB);
    yeet.whenPressed(new EjectBunny());

    toggle = new JoystickButton(gamepad2, RobotMap.Ports.ButtonStart);
    toggle.whenPressed(new toggleHoppers());
  }

  public double getRightYAxis() {
    return gamepad1.getRawAxis(RobotMap.Ports.g1_rightYAxis);
  }

  public double getRightXAxis() {
    return -gamepad1.getRawAxis(RobotMap.Ports.g1_rightXAxis);
  }

  public double getLeftYAxis() {
    return gamepad1.getRawAxis(RobotMap.Ports.g1_leftYAxis);
  }
}