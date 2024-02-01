// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  private static DriveTrain mInstance = null;

  private TalonSRX rightMaster;
  private TalonSRX rightFollower;
  private TalonSRX leftMaster;
  private TalonSRX leftFollower;

  // Make singleton
  public static DriveTrain getInstance() {
    if (mInstance == null) {
      mInstance = new DriveTrain();
    }
    return mInstance;
  }


  /** Creates a new DriveTrain. */
  public DriveTrain() {
    // Initialize motors
    rightMaster = new TalonSRX(2); // Input correct values for your motors
    rightFollower = new TalonSRX(1);
    leftMaster = new TalonSRX(3);
    leftFollower = new TalonSRX(4);

    // Invert right motors for minitbot. Other drive bases may not need this.
    rightMaster.setInverted(true);
    rightFollower.setInverted(true);

    // Set followers
    rightFollower.follow(rightMaster);
    leftFollower.follow(leftMaster);
  }

  // Sets the speed of the drive motors according to supplied values
  public void drive(double speed, double rotation) {
    // Initialize right and left motor speeds
    double rightSpeed = speed - rotation;
    double leftSpeed = speed + rotation;

    // Set right and left motor speeds
    rightMaster.set(ControlMode.PercentOutput, rightSpeed);
    leftMaster.set(ControlMode.PercentOutput, leftSpeed);
  }

  // Stops the drive motors
  public void stop() {
    rightMaster.set(ControlMode.PercentOutput, 0);
    leftMaster.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
