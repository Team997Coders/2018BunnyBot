package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.RobotMap;
import frc.robot.commands.OutTakeBalls;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class OutTake extends Subsystem {

  public VictorSP outTakeMotor;

  public OutTake(){
    outTakeMotor = new VictorSP(RobotMap.Ports.outtakeMotorPort);

  }
  public void shoot(){
    outTakeMotor.set(1.0);

  }
  public void stop(){
    outTakeMotor.set(0);

  }

  @Override
  public void initDefaultCommand() {
  }

public void updateSmartDashboard(){
    SmartDashboard.putData("OutTake", new OutTakeBalls());
}
}