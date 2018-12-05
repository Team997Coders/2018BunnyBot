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
import frc.robot.RobotMap;

public class BallSorter extends Subsystem { //true is our ball, false is their ball

  boolean currentPos = true; //this is the flaps current position. True is good ball, false is bad ball path
  Queue<Boolean> queue = new LinkedList<Boolean>(); //ball colors are stored and used when they get to the 2 pathways
  DigitalOutput AtPiston = new DigitalOutput(RobotMap.Ports.DigOut); //might need to change port in RobotMap
  boolean BallAtPiston = false; //if there's a ball infront of the sensor next to piston.
  boolean BallAtColor = false; //if there's a ball infront of the color sensor.

  public void ColorSensor() {
    if (!"Ball infront of color sensor") {
      BallAtColor = false;
    }
    if (!BallAtColor && "Ball infront of color sensor") {
      BallAtColor = true;
      queue.add("Ball color" == "our color"); //true if it's ours, false if theirs
    }
  }

  public void SendBall() { //When sensor senses a ball next to the flap:
    if (AtPiston.get()) {
      BallAtPiston = true;
    }
    if (BallAtPiston && !AtPiston.get()) {
      BallAtPiston = false;
      if (!queue.peek()) {
        //fire piston
      }
      queue.poll(); //get rid of last item in queue
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
