
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class OutTakeBalls extends Command {

  private double timeout;
  private Timer timer;

  public OutTakeBalls() {
    requires(Robot.outtake);
  }

  public OutTakeBalls(double timeout) {
    requires(Robot.outtake);
    this.timeout = timeout;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timer.reset();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.outtake.shoot();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (timer.get() > timeout) { return true; }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.outtake.stop();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}