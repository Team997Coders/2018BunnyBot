/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.PDriveToDistance;
import frc.robot.subsystems.BallIntake;
import frc.robot.commands.DriveToDistance;
import frc.robot.commands.PDriveToAngle;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.TurnToAngle;
import frc.robot.commands.SimpleAuto;
import frc.robot.commands.ADriveForward;

import frc.robot.subsystem.FrontHopper;
import frc.robot.subsystems.BallIntake;
import frc.robot.subsystems.BackHopper;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static DriveTrain driveTrain;
  public static OI oi;
  public static BallIntake intake;
  Public static FrontHopper frontHopper;
  public static BackHopper backHopper;

  Command m_autonomousCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    if ((!SmartDashboard.getKeys().contains("P") || !SmartDashboard.getKeys().contains("I")) || !SmartDashboard.getKeys().contains("D")) {
      SmartDashboard.putNumber("P", 0.0);
      SmartDashboard.putNumber("I", 0.0);
      SmartDashboard.putNumber("D", 0.0);

      SmartDashboard.setPersistent("P");
      SmartDashboard.setPersistent("I");
      SmartDashboard.setPersistent("D");
    }

    driveTrain = new DriveTrain();
    intake = new BallIntake();
    frontHopper = newFrontHopper();
    backHopper = new BackHopper();
    oi = new OI();

    //m_chooser.addDefault("Default Auto", new ExampleCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    

    chooser.addDefault("Default", null);
    chooser.addObject("Go to Angle P", new PDriveToAngle(-90));
    chooser.addObject("Go to Distance P", new PDriveToDistance(RobotMap.Values.ticksPerFoot));
    chooser.addObject("Got to 90 degress", new TurnToAngle(90));
    chooser.addObject("Anidentifyingthingthatwillreaduponthatdashboard", new ADriveForward());
    chooser.addObject("Go Forward Nerd", new ADriveForward());
    chooser.addObject("Go Forward a bit", new DriveToDistance(RobotMap.Values.ticksPerFoot));
    SmartDashboard.putData("Auto commands", chooser);
    SmartDashboard.putData("PTurn to Angle", new PDriveToAngle(-90));
    chooser.addObject("Simple Auto", new SimpleAuto());
  
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //driveTrain.automaticShifting();
    updateSmartDashboard();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
  public void updateSmartDashboard(){
    driveTrain.updateSmartDashboard();
  }

}
