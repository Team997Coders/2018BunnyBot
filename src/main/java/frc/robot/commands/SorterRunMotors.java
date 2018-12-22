/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.FrontHopper;
import frc.robot.subsystems.Sorter;
import java.util.concurrent.*;

public class SorterRunMotors extends Command {

  long currentTime;
  long oldTime;
  boolean verbose;
  Sorter sorter;

  public SorterRunMotors(boolean verbose, Sorter sorter) { 
    requires(Robot.sorter);
    requires(Robot.frontHopper);
    this.verbose = verbose;
    this.sorter = sorter;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    sorter.setMotor(1);
    Robot.frontHopper.setVolts(-.48);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
        sorter.stopMotors();
        Robot.frontHopper.stopVolts();
  }

  @Override
  protected void interrupted() {
    sorter.stopMotors();
    Robot.frontHopper.stopVolts();
  }
}