// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;

import javax.print.DocFlavor.SERVICE_FORMATTED;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Gyro_Programming;
import frc.robot.subsystems.Intake;



public class turn extends CommandBase {
  /** Creates a new turn. */
  DriveTrain driveTrain;
  Gyro_Programming gyro;
  PIDController pid;
  double offset;
  Intake intake;
  double setpoint;
  double angle;
  Elevator elevator;

  public turn(DriveTrain drive, Gyro_Programming gyr, Elevator ele, double ang) {
    driveTrain = drive;
    gyro = gyr;
    pid = new PIDController(0.5, 0,0);
    angle = ang;
    elevator = ele;
    //intake = inta;
    addRequirements(driveTrain);
    addRequirements(gyro);
    addRequirements(elevator);
    
    offset  = 0;
    setpoint = 0;



    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Turning", true);
    offset = gyro.gyro_angle();
    setpoint = angle*18/360 + offset;
    SmartDashboard.putNumber("setpoint", setpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.setMotor(0, -pid.calculate(gyro.gyro_angle(), setpoint));//
    SmartDashboard.putNumber("angle read", gyro.gyro_angle());
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (this.pid.atSetpoint()){
      //intake.toggleIntake(false);
      //elevator.setIntake(true);
      
      return true;
    }
    
    
    return false;
  }
}
