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
  private final Sensor sensor;
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
    this.sensor = new Sensor();
    //timer.reset();
    
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

  public void auto_two(){ // shoot, pickup ball, shoot
    double rotSpeed = 0.5; // adjust rotational speed
    double fwdSpeed = 0.4;


    double[] timeInts = {
      3, .45, 2, .50, 1, 1, 2
    }; // 0 = 1st shot, 1 = rotate time (2.1), 2 = drive forward, 3 = rotate 180deg, 4 = drive forward, 5 = lower ball, 6 = shoot

    for(int i = 0; i < timeInts.length-1; i++){ // process all time intervals (accumulate total time)
      if(i > 0){
        for(int x = 0; x < i; x++){
          timeInts[i] = timeInts[i] + timeInts[i-x];
        }
      }
    }

    fwdSpeed = -fwdSpeed; // v drive is inverted 


    timer.start(); // Begin auto period (max 15s)

    if (!timer.hasElapsed(timeInts[0])){ // shoot
      elevator.setEle(0.8);
      elevator.auto_shoot_two();
    }
    else if(!timer.hasElapsed(timeInts[1])){ // rotate
      elevator.setEle(0);
      elevator.setSh(false);
      driveTrain.setMotor(0, rotSpeed);
    }
    else if(!timer.hasElapsed(timeInts[2])){ // stop rot + intake ball (drive forward + run intake/elevator)
      intake.toggleIntake(false); // lower intake
      elevator.setIntake(true); // run intake motor
      elevator.setEle(0.8); // run elevator
      driveTrain.setMotor(fwdSpeed, 0); // Moves forward
    }
    else if(!timer.hasElapsed(timeInts[3])){ // retract intake + stop drive, rotate
      elevator.setIntake(false);
      intake.toggleIntake(true);
      driveTrain.setMotor(0, -rotSpeed);
    }
    else if(!timer.hasElapsed(timeInts[4])){
      driveTrain.setMotor(fwdSpeed, 0); // stop rot + move forward
    }
    else if(!timer.hasElapsed(timeInts[5])){ // stop drive + move ball down ele
      driveTrain.setMotor(0, 0); // stop moving
      elevator.setEle(-0.8);
    }
    else if(!timer.hasElapsed(timeInts[6])){ // activate shooter, elevate ball
      elevator.auto_shoot_two();
      elevator.setEle(0.8);
    }
} 


public void reset_timer(){
  timer.stop();
  timer.reset();
}

  public void test_auto(){
    timer.start();
    if(!timer.hasElapsed(3)){
      driveTrain.setMotor(0, 0.25);
    }
  }

  public void auto_three(){
    
    timer.start();
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
      if(timePassed <= 0.62){ // change this for back
        //m_robotContainer.auto_drive(true);
        driveTrain.setMotor(0.6, 0);//gets inverted
      }
      else{
        driveTrain.setMotor(0, 0);
      }
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
      //if(sensor.detectColor()){
        elevator.setUpper();
      //}
      
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