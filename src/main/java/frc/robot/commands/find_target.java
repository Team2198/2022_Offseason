// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro_Programming;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.subsystems.Limelight_programming;

public class find_target extends CommandBase {
  /** Creates a new find_target. */
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain driveTrain;
  private final PIDController pid_controller = new PIDController(0.5, 0, 0);
  private final Limelight_programming limelight;
  private final Gyro_Programming gyro;
  double tv;
  boolean target = false;


  public find_target(DriveTrain drive, Gyro_Programming gyr, Limelight_programming lime) {
    // Use addRequirements() here to declare subsystem dependencies.
      driveTrain = drive;
      limelight = lime;
      gyro = gyr;
      addRequirements(driveTrain);
      addRequirements(limelight);
      addRequirements(gyro);
      //3rd element is tv
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    tv = limelight.getValues()[3];
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (tv!=1){
      driveTrain.setMotor(0, 0.4);

    }

    else{
      driveTrain.setMotor(0, 0);
      target = true;
      

    }


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (target==true){
      return true;
    }
    
    return false;
  }
}
