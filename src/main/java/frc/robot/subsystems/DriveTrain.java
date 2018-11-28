/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.commands.*;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class DriveTrain extends Subsystem {
 
  private TalonSRX leftTalon, rightTalon;
  //private Encoder leftEncoder, rightEncoder;
  public boolean lastGearState;
  private DoubleSolenoid shiftSolenoid;
  public double leftRate;
  public double rightRate;
	//temp
  private VictorSPX leftVictor1, leftVictor2;
	private VictorSPX rightVictor1, rightVictor2;

  //public DoubleSolenoid.Value currentGearNum = shiftSolenoid.get();

  public DriveTrain() {
    lastGearState = false;

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
		
		leftTalon.configPeakCurrentLimit(40, 10);
		leftTalon.configPeakCurrentDuration(100, 10);
		leftTalon.configContinuousCurrentLimit(30, 10);
    leftTalon.enableCurrentLimit(true);
    
		rightTalon.configNominalOutputForward(0, 10);
		rightTalon.configNominalOutputReverse(0, 10);
		//rightTalon.configPeakOutputForward(1, 10); //Use for PB
		//rightTalon.configPeakOutputReverse(-1, 10); //Use for PB
		rightTalon.configPeakOutputForward(0.6, 10);  //Use for extrasensitive CB
		rightTalon.configPeakOutputReverse(-0.6, 10); //Use for extrasensitive CB
		
		rightTalon.configPeakCurrentLimit(40, 10);
    rightTalon.configPeakCurrentDuration(100, 10);
		rightTalon.configContinuousCurrentLimit(30, 10);
    rightTalon.enableCurrentLimit(true);
    
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
  }

  public void setGear(boolean gearState) {
    if (lastGearState != gearState){
      if (gearState) {
        shiftSolenoid.set(Value.kReverse);
      } else {
        shiftSolenoid.set(Value.kForward);
      }

      lastGearState = gearState;
      System.out.println(lastGearState);
    }
  }

  public double getLeftEncoderRate() {
		return leftTalon.getSelectedSensorVelocity(0);
	}

	public double getRightEncoderRate() {
		return rightTalon.getSelectedSensorVelocity(0);
  }
  
  public double getLeftEncoderTicks() {
		/* CTRE Magnetic Encoder relative, same as Quadrature */
		leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
		return leftTalon.getSelectedSensorPosition(0);
	}

	public double getRightEncoderTicks() {
		rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
		return rightTalon.getSelectedSensorPosition(0);
  }

  /*public void automaticShifting(){
    if (getLeftRate() >= 6 && getRightRate() >= 6 && lastGearNum == 0 && Math.abs(Robot.oi.getLeftYAxis()) == 1){
    setGear(1);
    lastGearNum = 1;
    } else{}
  }*/

  public void setVolts(double leftSpeed, double rightSpeed) {
    leftTalon.set(ControlMode.PercentOutput, leftSpeed);
    rightTalon.set(ControlMode.PercentOutput, rightSpeed);
  }
  public void stopVolts() {
    leftTalon.set(ControlMode.PercentOutput, 0);
    rightTalon.set(ControlMode.PercentOutput, 0);
  }

  public void resetEncoders() {
		leftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
		leftTalon.setSelectedSensorPosition(0, 0, 10);
		rightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0); /* PIDLoop=0,timeoutMs=0 */
		rightTalon.setSelectedSensorPosition(0, 0, 10);
		System.out.println("Encoders reset!");
	}

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
  }

  public void updateSmartDashboard() {
    //SmartDashboard.putNumber("LeftEncoderCount", leftEncoder.get());
    //SmartDashboard.putNumber("RightEncoderCount", rightEncoder.get());
    SmartDashboard.putNumber("LeftEncoderRate", getLeftEncoderRate());
    SmartDashboard.putNumber("RightEncoderRate", getRightEncoderRate());
    SmartDashboard.putBoolean("Gear", lastGearState);
  }
}