// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;



import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;


import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Elevator;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...




  private final DriveTrain driveTrain;  
  private final Intake intake;
  private final XboxController xboxController;
  private final XboxController xboxController2;
  private final Climber climber;
  private final Elevator elevator;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindingsd
    configureButtonBindings();
    this.xboxController = new XboxController(Constants.Xbox_Controller);
    this.driveTrain = new DriveTrain();
    this.intake = new Intake();
    this.climber = new Climber();
    this.elevator = new Elevator();
    this.xboxController2 = new XboxController(1);
    //

    //public double get_valY(){
      //return xboxController.getY(GenericHID.Hand.kRight);
    //}
    //public double get_valX(){
    //  return xboxController.getX(GenericHID.Hand.kLeft);
    //}
  }

  public void reset(){
    this.intake.reset();
    this.elevator.setEMotor(false);
    this.elevator.setEle(0);
    this.elevator.setSh(false);
  }

  public void setZero(boolean c){
    elevator.setEle(0);

  }

  public void get_val(){
    driveTrain.setMotor(xboxController.getLeftY()*0.9, xboxController.getRightY());
  }

  public void elevator(){
    elevator.setEle(0.8);
    elevator.setSh(true);
    SmartDashboard.putBoolean("elevator on", true);
  }

  public void trig_shooter(){
    if(xboxController2.getRightTriggerAxis()> 0){
      elevator.setSh(true);
    }

    else{
      elevator.setSh(false);
    }
    
  }


  public void PowerElevator(){
  
  if (xboxController.getLeftBumperPressed()){
    
    intake.toggleIntake(false);
    elevator.setEMotor(true);
    
   }


  if (xboxController.getRightBumper()){
    elevator.setEMotor(false);
    intake.toggleIntake(true);
  }
  
  if (xboxController.getAButton()){
    if(intake.getSol() == true){
      elevator.setEMotor(true);
    }
  } 
  if (xboxController.getBButton()){
    elevator.setEMotor(false);
  }

  /* if (xboxController2.getAButton()){
    elevator.setSh(true);
  } */
  if(xboxController2.getXButton()){
    elevator.setSh(false);
    elevator.revert_setSh(false);
  }

  if(xboxController2.getBButton()){
    elevator.revert_setSh(true);
  }

  if (xboxController2.getLeftBumper()){
    elevator.revert_setSh(true);
  }

  if(xboxController2.getRightBumper()){
    elevator.setSh(false);
    elevator.revert_setSh(false);
  }
  
  elevator.setEle(xboxController2.getRightY()*0.80);
  
  
  
  


  


  //elevator.setEMotor(true, xboxController.getRightTriggerAxis());
  //elevator.setEMotor(false, xboxController2.getLeftTriggerAxis());
  
}

  
  public void Climber_status(){
    double speed = xboxController2.getLeftY()*0.6;
    if (speed >= 0){
      climber.climber_set(speed, true);
    }

    if (speed <= 0){
      climber.climber_set(speed, false);
    }
  }

  



  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
  //}
}