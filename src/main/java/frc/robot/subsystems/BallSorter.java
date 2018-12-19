/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import java.util.Queue;
import java.util.LinkedList;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.networktables.NetworkTable;

public class BallSorter extends Subsystem { //true is our ball, false is their ball

  private AnalogInput ballSensor = new AnalogInput(RobotMap.Ports.ballSensor);
  boolean currentPos = true; //this is the flaps current position. True is good ball, false is bad ball path
  Queue<Boolean> queue = new LinkedList<Boolean>(); //ball colors are stored and used when they get to the 2 pathways
  boolean BallAtPiston = false; //if there's a ball infront of the sensor next to piston.
  boolean BallAtColor = false; //if there's a ball infront of the color sensor.
  Solenoid piston;
  NetworkTable NetworkTable;

  public BallSorter() {
    piston = new Solenoid(RobotMap.Ports.solenoid); //set to a correct port
  }

  //NEED:
  //   Detection of ball infront of sensor
  //   Ball color
  //   Our Ball Color

  public void ColorSensor() {
    if (!"Ball infront of color sensor") {
      BallAtColor = false;
    }
    if (!BallAtColor && "Ball infront of color sensor") {
      BallAtColor = true;
      queue.add("Ball color" == "our color"); //true if it's ours, false if theirs
    }
  }

  //fires out ball when it's bad and senses it next to the piston.
  public void SendBall() {
    if (ballSensor.getVoltage() > 2.5) { //may need to change value
      BallAtPiston = true;
    }
    if (BallAtPiston && !(ballSensor.getVoltage() > 2.5)) { //may need to change value
      BallAtPiston = false;
      if (!queue.peek()) {
        piston.set(true);
        piston.set(false);
      }
      queue.poll();
    }
  }

  NetworkTable.X;

  @Override
  public void initDefaultCommand() {

  }
}
