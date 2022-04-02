// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.Timer;
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
  private Timer timer;
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
    this.timer = new Timer();

    //public double get_valY(){
      //return xboxController.getY(GenericHID.Hand.kRight);
    //}
    //public double get_valX(){
    //  return xboxController.getX(GenericHID.Hand.kLeft);
    //}
  }

  public void auto_one(){
    SmartDashboard.putString("bye", "hello");
    SmartDashboard.putNumber("curr timer", timer.get());
    if (!this.timer.hasElapsed(6)){ // change this for forward
      SmartDashboard.putNumber("next timer", timer.get());
      elevator.setEle(0.8);
      elevator.auto_shoot();
      SmartDashboard.putBoolean("elevator on", true);
    }
    else{ 
      
      elevator.setEle(0);
      elevator.setSh(false);
      
      double timePassed = timer.get() - 6;
      SmartDashboard.putNumber("seconds", timePassed);
      if(timePassed <= 0.75){ // change this for back
        //m_robotContainer.auto_drive(true);
        driveTrain.setMotor(0.6, 0);//gets inverted
      }
      else{
        //m_robotContainer.auto_drive(false);
        driveTrain.setMotor(0, 0);
        
      } 

    }  
  }

  public void auto_two(){
    SmartDashboard.putString("bye", "hello");
    SmartDashboard.putNumber("curr timer", timer.get());
    if (!this.timer.hasElapsed(4)){ // change this for forward
      SmartDashboard.putNumber("next timer", timer.get());
      elevator.setEle(0.8);
      elevator.auto_shoot_two();
      SmartDashboard.putBoolean("elevator on", true);
    }
    else{ 
      
      elevator.setEle(0);
      elevator.setSh(false);
      
      double timePassed = timer.get() - 4;
      SmartDashboard.putNumber("seconds", timePassed);
      if(timePassed <= 0.3){ // change this for back
        //m_robotContainer.auto_drive(true);
        driveTrain.setMotor(0.6, 0);//gets inverted
      }
      else{
        driveTrain.setMotor(0, 0);
        timePassed = timer.get() - 4.4; 
        if (timePassed <= 2){
          driveTrain.setMotor(0, 0.2);
        }

        else{
          driveTrain.setMotor(0, 0);
          timePassed = timer.get()-6.4;
          if (timePassed <= 2){
            intake.toggleIntake(false);
          } 

          else{
            timePassed = timer.get()-8.4;
            if (timePassed <= 4){
              elevator.setEle(0.8);
              elevator.auto_shoot_two();
              SmartDashboard.putBoolean("elevator on", true);
            }

            else{
              elevator.setEle(0);
              elevator.setSh(false);
            }
          }
          
          
        }
        //m_robotContainer.auto_drive(false);
        
        
      } 

    }  
  }



  public void auto_three(){
    if (!this.timer.hasElapsed(4)){ // change this for forward
      SmartDashboard.putNumber("next timer", timer.get());
      elevator.setEle(0.8);
      elevator.setTarmac();
      SmartDashboard.putBoolean("elevator on", true);
    }
    else{  
      elevator.setEle(0);
      elevator.setSh(false);
      double timePassed = timer.get() - 4;
      SmartDashboard.putNumber("seconds", timePassed);
      if(timePassed <= 0.3){ // change this for back
        //m_robotContainer.auto_drive(true);
        driveTrain.setMotor(0.6, 0);//gets inverted
      }
      else{
        driveTrain.setMotor(0, 0);
      }
    }
  }
  

  public void auto_drive(boolean yes){
    if (yes){
      driveTrain.setMotor(0.6, 0);
    }
    else{
      driveTrain.setMotor(0, 0);
    }
    
  }

  public void reset(){
    this.intake.reset();
    this.elevator.setIntake(false);
    this.elevator.setEle(0);
    this.elevator.setSh(false);
    timer.stop();
    timer.reset();
    
    timer.start();
  }

  public void setZero(boolean c){
    elevator.setEle(0);
    elevator.setSh(false);

  }

  public void get_val(){
    driveTrain.setMotor(xboxController.getLeftY(), xboxController.getRightX());
  }

  public void auto_elevator(){
    elevator.setEle(0.8);
    elevator.auto_shoot();
    SmartDashboard.putBoolean("elevator on", true);
  }

  public void trig_shooter(){
    if(xboxController2.getRightTriggerAxis()> 0.1){
      elevator.setSh(true);
    }

    if (xboxController2.getLeftTriggerAxis() > 0.1){
      elevator.setUpper();
    }

    if (xboxController2.getRightBumperPressed()){
      elevator.setTarmac();
    }
    
    
    
  }


  public void PowerElevator(){
  
  if (xboxController.getLeftBumperPressed()){
    
    intake.toggleIntake(false);
    elevator.setIntake(true);
    
   }


  if (xboxController.getRightBumper()){
    elevator.setIntake(false);
    intake.toggleIntake(true);
  }
  
  if (xboxController.getAButton()){
    if(intake.getSol() == true){
      elevator.setIntake(true);
    }
  } 
  if (xboxController.getBButton()){
    elevator.setIntake(false);
  }

  /* if (xboxController2.getAButton()){
    elevator.setSh(true);
  } */
  /* if(xboxController2.getXButton()){
    elevator.setSh(false);
    elevator.revert_setSh(false);
  } */

  /* if(xboxController2.getBButton()){
    elevator.revert_setSh(true);
    SmartDashboard.putBoolean("pressed b", true);
  } */

  if (xboxController2.getLeftBumper()){
    elevator.revert_setSh(true);
    SmartDashboard.putBoolean("pressed left", true);
  }

  if(xboxController2.getXButtonPressed()){
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