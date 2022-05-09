// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro_Programming;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.subsystems.Limelight_programming;
import frc.robot.subsystems.Elevator;

public class shooter extends CommandBase {
  /** Creates a new shooter. */

  
  
  private final DriveTrain driveTrain;
  private final PIDController pid_controller = new PIDController(0.5, 0, 0);
  private final Elevator elevator_shooter;

  public shooter(DriveTrain drive,  Elevator ele) {

    driveTrain = drive;
    elevator_shooter = ele;
    addRequirements(elevator_shooter);
    addRequirements(driveTrain);

    
    
    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevator_shooter.setEle(0.8);
    elevator_shooter.setSh(true);
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
