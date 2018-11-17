/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.commands.*;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class DriveTrain extends Subsystem {
 
  private TalonSRX leftTalon, rightTalon;
  private Encoder leftEncoder, rightEncoder;
  public double lastGearNum;
  private DoubleSolenoid shiftSolenoid;
  public double leftRate;
  public double rightRate;
	//temp
  private VictorSPX leftVictor1, leftVictor2;
	private VictorSPX rightVictor1, rightVictor2;

  //public DoubleSolenoid.Value currentGearNum = shiftSolenoid.get();
  public int gear = 0;

  public DriveTrain() {
    lastGearNum = 0;

    leftTalon = new TalonSRX(RobotMap.Ports.leftTalonPort);
    rightTalon = new TalonSRX(RobotMap.Ports.rightTalonPort);
    leftVictor1 = new VictorSPX(RobotMap.Ports.leftVictor1Port);
    leftVictor2 = new VictorSPX(RobotMap.Ports.leftVictor2Port);
    rightVictor1 = new VictorSPX(RobotMap.Ports.rightVictor1Port);
    rightVictor2 = new VictorSPX(RobotMap.Ports.rightVictor2Port);

    leftVictor1.follow(leftTalon);
    leftVictor2.follow(leftTalon);
    rightVictor1.follow(rightTalon);
    rightVictor2.follow(rightTalon);

    leftTalon.setInverted(false);
    rightTalon.setInverted(true);

    leftVictor1.setInverted(false);
    leftVictor2.setInverted(false);
    rightVictor1.setInverted(true);
    rightVictor2.setInverted(true);

    leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		leftTalon.setSensorPhase(true);
		rightTalon.setSensorPhase(true);
		
		leftTalon.setNeutralMode(NeutralMode.Coast);
		rightTalon.setNeutralMode(NeutralMode.Coast);
		
		/* set the peak, nominal outputs */
		leftTalon.configNominalOutputForward(0, 10);
		leftTalon.configNominalOutputReverse(0, 10);
		//leftTalon.configPeakOutputForward(1, 10);	//Use for PB
		//leftTalon.configPeakOutputReverse(-1, 10); //Use for PB
		leftTalon.configPeakOutputForward(0.6, 10);	//Use for extrasensitive CB
		leftTalon.configPeakOutputReverse(-0.6, 10); //Use for extrasensitive CB
		
		leftTalon.enableCurrentLimit(true);
		leftTalon.configPeakCurrentLimit(40, 10);
		leftTalon.configPeakCurrentDuration(100, 10);
		leftTalon.configContinuousCurrentLimit(30, 10);
		
		rightTalon.configNominalOutputForward(0, 10);
		rightTalon.configNominalOutputReverse(0, 10);
		//rightTalon.configPeakOutputForward(1, 10); //Use for PB
		//rightTalon.configPeakOutputReverse(-1, 10); //Use for PB
		rightTalon.configPeakOutputForward(0.6, 10);  //Use for extrasensitive CB
		rightTalon.configPeakOutputReverse(-0.6, 10); //Use for extrasensitive CB
		
		rightTalon.enableCurrentLimit(true);
		rightTalon.configPeakCurrentLimit(40, 10);
		rightTalon.configPeakCurrentDuration(100, 10);
		rightTalon.configContinuousCurrentLimit(30, 10);
		
		leftTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 40, 10);
		//leftTalon.configOpenloopRamp(0.25, 10);
		rightTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 40, 10);
		//rightTalon.configOpenloopRamp(0.25, 10);
		
		/* set closed loop gains in slot0 */
		leftTalon.config_kF(0, 0.1097, 10);
		leftTalon.config_kP(0, 0.113333, 10);
		leftTalon.config_kI(0, 0, 10);
		leftTalon.config_kD(0, 0, 10);		

		rightTalon.config_kF(0, 0.1097, 10);
		rightTalon.config_kP(0, 0.113333, 10);
		rightTalon.config_kI(0, 0, 10);
		rightTalon.config_kD(0, 0, 10);	
		
		new SensorCollection(leftTalon);
		new SensorCollection(rightTalon);

    shiftSolenoid = new DoubleSolenoid(RobotMap.Ports.gearPistonFor, RobotMap.Ports.gearPistonRev);
    updateSmarts();
  }


  public void setGear(double gearNum) {
    if (gearNum == 0 && lastGearNum != 0){
      shiftSolenoid.set(Value.kForward);
      lastGearNum = 0;
      System.out.println(lastGearNum);
    } else {
      shiftSolenoid.set(Value.kReverse);
      lastGearNum = 1;
      System.out.println(lastGearNum);
    }
    leftEncoder.setDistancePerPulse(1/7565);
    rightEncoder.setDistancePerPulse(1/7565);
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public double getLeftRate(){
    if (Math.abs(leftEncoder.getRate()/(RobotMap.Values.ticksPerFoot)) < 20){
        return leftEncoder.getRate();/*Robot.oi.getLeftYAxis())*/
    }else{
      System.out.println(leftEncoder.getRate());
      return 0;
    }
  }

  public double getRightRate(){
    if (Math.abs(rightEncoder.getRate()/(RobotMap.Values.ticksPerFoot)) < 20){
        return rightEncoder.getRate();/*Robot.oi.getRightYAxis())*/
    }else{
      System.out.println(rightEncoder.getRate());
      return 0;
    }
  }

  public void automaticShifting(){
    if (getLeftRate() >= 6 && getRightRate() >= 6 && lastGearNum == 0 && Math.abs(Robot.oi.getLeftYAxis()) == 1){
    setGear(1);
    lastGearNum = 1;
  } else{}
}

  public void setVolts(double leftSpeed, double rightSpeed) {
    leftTalon.set(ControlMode.PercentOutput, leftSpeed);
    rightTalon.set(ControlMode.PercentOutput, rightSpeed);
  }
  public void stopVolts() {
    leftTalon.set(ControlMode.PercentOutput, 0);
    rightTalon.set(ControlMode.PercentOutput, 0);
  }

  public int getLeftTicks() { 
    return leftEncoder.get(); 
  }

  public int getRightTicks() { 
    return rightEncoder.get(); 
  }
  
  public void resetTicks() {
    leftEncoder.reset();
    rightEncoder.reset();
  
  }
  public void updateSmarts() {
    SmartDashboard.putString("idk", "YEEET");
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
  }
  public void UpdateSmartDashboard(){
    SmartDashboard.putNumber("LeftEncoderCount", leftEncoder.get());
    SmartDashboard.putNumber("RightEncoderCount", rightEncoder.get());
    SmartDashboard.putNumber("LeftEncoderRate", getLeftRate());
    SmartDashboard.putNumber("RightEncoderRate", getRightRate());
    SmartDashboard.putNumber("Gear", lastGearNum);
  }
}