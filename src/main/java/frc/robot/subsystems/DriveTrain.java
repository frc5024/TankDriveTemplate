// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  private static DriveTrain mInstance = null;

  private TalonFX rightMaster;
  private TalonFX rightFollower;
  private TalonFX leftMaster;
  private TalonFX leftFollower;

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
    rightMaster = new TalonFX(3); // Input correct values for your motors
    rightFollower = new TalonFX(4);
    leftMaster = new TalonFX(1);
    leftFollower = new TalonFX(2);

    // Invert right motors for minitbot. Other drive bases may not need this.
    rightMaster.setInverted(true);
    rightFollower.setInverted(true);

    // Set followers
    rightFollower.setControl(new Follower(rightMaster.getDeviceID(), false));
    leftFollower.setControl(new Follower(leftMaster.getDeviceID(), false));
  }

  // Sets the speed of the drive motors according to supplied values
  public void drive(double speed, double rotation) {
    // Initialize right and left motor speeds
    double rightSpeed = speed - rotation;
    double leftSpeed = speed + rotation;

    // Set right and left motor speeds
    rightMaster.set(rightSpeed);
    leftMaster.set(leftSpeed);
  }

  // Stops the drive motors
  public void stop() {
    rightMaster.stopMotor();
    leftMaster.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
