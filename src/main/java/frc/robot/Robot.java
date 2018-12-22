/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.BallIntake;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.subsystems.DriveTrain;
import frc.robot.misc.*;
import edu.wpi.first.networktables.*;

import frc.robot.subsystems.*;

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
  public static FrontHopper frontHopper;
  public static BackHopper backHopper;
  public static OutTake outtake;
  public static Sorter sorter;
  public static NetworkTableInstance inst;
  public static NetworkTable table;
  public static NetworkTableEntry ballType;
  
  public static boolean sortingBalls = true;

  public static String allianceColor = "blue"; //for our alliance
  public static String allianceColorEditable = "R"; 

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
    frontHopper = new FrontHopper();
    backHopper = new BackHopper();
    oi = new OI();
    outtake = new OutTake();
    sorter = new Sorter();

    //m_chooser.addDefault("Default Auto", new ExampleCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    

    chooser.addDefault("Default", null);
    chooser.addObject("Go to Angle P", new PDriveToAngle(-90));
    chooser.addObject("Go to Distance P", new PDriveToDistance(RobotMap.Values.ticksPerFoot));
    chooser.addObject("Got to 90 degress", new TurnToAngle(90));
    chooser.addObject("Anidentifyingthingthatwillreaduponthatdashboard", new ADriveForward());
    chooser.addObject("Go Forward Nerd", new ADriveForward());
    chooser.addObject("Go Forward a bit", new DriveToDistance(RobotMap.Values.ticksPerFoot));
    chooser.addObject("Sort things", new Sortstuff(false, sorter));
    SmartDashboard.putData("Auto commands", chooser);
    SmartDashboard.putData("PTurn to Angle", new PDriveToAngle(-90));
    chooser.addObject("Simple Auto", new SimpleAuto());
  
    /*inst = NetworkTableInstance.create();
    table = inst.getTable("fakecam");
    ballType = table.getEntry("ball");*/
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
    Scheduler.getInstance().add(new toggleHoppers(false));
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    updateSmartDashboard();

    if (SmartDashboard.getString("PUT ALLIANCE COLOR HERE (R | B)", "null").startsWith("R")) {
      allianceColor = "red";
    } else if (SmartDashboard.getString("PUT ALLIANCE COLOR HERE (R | B)", "null").startsWith("B")) {
      allianceColor = "blue";
    } else {
      allianceColor = SmartDashboard.getString("PUT ALLIANCE COLOR HERE (R | B)", "null");
    }
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
    driveTrain.updatePIDValues();

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

    Scheduler.getInstance().add(new toggleHoppers(true));
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

    Scheduler.getInstance().add(new toggleHoppers(true));
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    driveTrain.automaticShifting();
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
