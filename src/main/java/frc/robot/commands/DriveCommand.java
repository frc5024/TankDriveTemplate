// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveCommand extends Command {
  private DriveTrain driveTrain = DriveTrain.getInstance();

  private DoubleSupplier forwardSup;
  private DoubleSupplier reverseSup;
  private DoubleSupplier rotationSup;

  /** Creates a new DriveCommand. */
  public DriveCommand(DoubleSupplier forwardSup, DoubleSupplier reverseSup, DoubleSupplier rotationSup) {
    addRequirements(driveTrain);

    this.forwardSup = forwardSup;
    this.reverseSup = reverseSup;
    this.rotationSup = rotationSup;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Stop the drive train
    driveTrain.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Get values from suppliers
    double driveVal = forwardSup.getAsDouble() - reverseSup.getAsDouble();
    double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.stickDeadband);

    // Set drive train to drive according to drive and rotation values
    driveTrain.drive(driveVal, rotationVal);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
