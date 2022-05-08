// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;//ojfhorklfwejerkjgekjggtej
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Limelight_programming;
import frc.robot.commands.PID_test;
import frc.robot.commands.Position;
import frc.robot.commands.Position_Scheduler;
import frc.robot.commands.find_target;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Gyro_Programming;


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
  private Gyro_Programming gyro = new Gyro_Programming();
  double prevAngle;
  private final Limelight_programming limelight;
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindingsd
    
    configureButtonBindings();
    this.xboxController = new XboxController(0);
    this.driveTrain = new DriveTrain();
    this.intake = new Intake();
    this.climber = new Climber();
    this.elevator = new Elevator();
    this.xboxController2 = new XboxController(1);
    this.limelight = new Limelight_programming();
    //
    this.timer = new Timer();
    this.sensor = new Sensor();
  }

  
  
  public void auto_one(){
    timer.start();
    SmartDashboard.putNumber("curr timer", timer.get());
    if (!timer.hasElapsed(6)){ // change this for forward

      elevator.setEle(0.8);
      elevator.auto_shoot_two();
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

  public void calibrate(){
    gyro.calibrate_gyro();
  }

  public boolean turn(Double dir){
    dir = -dir; // invert because gyro is inverted
    double scale = 0.0369444444444444;
    dir = dir*scale;
    double curAngle = gyro.gyro_angle(); // 0 = no rotation, - clockwise, + counter-clockwise 
    double minSpeed = .17; // minimum rotational power required to turn

    //double turnRate = -(prevAngle-curAngle); // - is right, + is left


    SmartDashboard.putNumber("deviation", curAngle);
    
    if(curAngle > dir){ // turn right
      if(curAngle > dir*.7){
        driveTrain.setMotor(0, .25); // full speed
      }
      else if(curAngle > dir){
        double tS = -.13*(dir-curAngle)+.25;
        if(tS < minSpeed){
          tS = minSpeed;
        }
        driveTrain.setMotor(0,tS); // slow down,  linear function goes here, min speed is .17
      }
    }
    else if(curAngle < dir){ // turn left
      if(curAngle < dir*.7){
        driveTrain.setMotor(0, -.25);
      }
      else if(curAngle < dir){
        double tS = -(-.13*(dir-curAngle)+.25); // inverse for left
        if(tS > -minSpeed){
          tS = -minSpeed;
        }
        driveTrain.setMotor(0,tS); // slow down,  linear function goes here, min speed is .17
      }
    }
    else{
      return true;
    }
    return false;
  }

  public void auto_two(){ // shoot, pickup ball, shoot
    //double rotSpeed = 0.5; // adjust rotational speed
    double fwdSpeed = 0.4;


    double[] timeInts = {
      7, .2, 1, 1.5, .2, 1, .3, 2
    }; // 0 = 1st shot, 1 = rotate time (2.1), 2 = drive forward, 3 = rotate 180deg, 4 = drive forward, 5 = lower ball, 6 = shoot

    for(int i = 0; i <= timeInts.length-1; i++){ // process all time intervals (accumulate total time)
      if (i!=0){
      timeInts[i] = timeInts[i] + timeInts[i-1];}
      /* if(i > 0){
        for(int x = 0; x <= i; x++){
          if(x > 0){
            timeInts[i] = timeInts[i] + timeInts[i-x];
          }
        }
      } */
    }
    SmartDashboard.putNumber("index 0", timeInts[0]);
    SmartDashboard.putNumber("index 1 (problem)", timeInts[1]);
    SmartDashboard.putNumber("index 2", timeInts[2]);
    SmartDashboard.putNumber("index 3", timeInts[3]);
    SmartDashboard.putNumber("index 4", timeInts[4]);
    SmartDashboard.putNumber("index 5", timeInts[5]);
    fwdSpeed = -fwdSpeed; // v drive is inverted 


    timer.start(); // Begin auto period (max 15s)

    if (!timer.hasElapsed(timeInts[0])){ // shoot
      elevator.setEle(0.8);
      elevator.auto_shoot_two();
    }
    else if(!timer.hasElapsed(timeInts[1])){ // rotate
      SmartDashboard.putNumber("index 1", timeInts[1]);
      elevator.setEle(0);
      elevator.setSh(false);
      timer.stop();
      boolean advance = turn(180.0); // orient robot 180 degrees to calibration
      if(advance == true){
        timer.start();
      }
      //turn_drive_two(true);
    }
    else if(!timer.hasElapsed(timeInts[2])){ // stop rot + intake ball (drive forward + run intake/elevator)
      intake.toggleIntake(false); // lower intake
      elevator.setIntake(true); // run intake motor
      elevator.setEle(0.8); // run elevator
    }
    else if(!timer.hasElapsed(timeInts[3])){
      driveTrain.setMotor(fwdSpeed, 0); // Moves forward
      //offset = gyro.getAngle()
    }
    else if(!timer.hasElapsed(timeInts[4])){ // retract intake + stop drive, rotate
      timer.stop();
      boolean advance = turn(0.0); // orient robot in same pos as calibration
      if(advance == true){
        timer.start();
      }
      //turn_drive_two(false);
    }
    else if(!timer.hasElapsed(timeInts[5])){
      driveTrain.setMotor(fwdSpeed, 0); // stop rot + move forward
    }
    else if(!timer.hasElapsed(timeInts[6])){ // stop drive + move ball down ele
      driveTrain.setMotor(0, 0); // stop moving
      elevator.setEle(-0.8);
      
    }
    else if(!timer.hasElapsed(timeInts[7])){ // activate shooter, elevate ball
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
    /*if(!timer.hasElapsed(3)){
      driveTrain.setMotor(0, 0.25);
    }*/
    driveTrain.setMotor(0, .25); // Clockwise
    //SmartDashboard.putNumber("angle", gyro.getAngle()); // should go up
    SmartDashboard.putNumber("cumulative time", timer.get());
  }

 
 /*  public void accurateturnTest(){
    timer.start();
    //attempt to turn 180
    //double deviation = gyro.getAngle();
    SmartDashboard.putNumber("angle", deviation);
    if(deviation > -6.65){ // -6.65 desired
      driveTrain.setMotor(0, .25);
    }
    else if(deviation < -7){
      driveTrain.setMotor(0, -.25);
    }
    else{
      driveTrain.setMotor(0, 0);
    }
  }
 */

  public void auto_three(){
    //SmartDashboard.putNumber("angle", gyro.getAngle());
    timer.start();
    SmartDashboard.putNumber("next timer", timer.get());
    if (!this.timer.hasElapsed(4)){ // change this for forward
      
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
    timer.reset();//wfjhfrerhkghgkjghg
    timer.start();
  }

  public void setZero(boolean c){
    elevator.setEle(0);
    elevator.setSh(false);

  }

  public void get_val(){
    driveTrain.setMotor(xboxController.getLeftY(), xboxController.getRightX()*0.75);
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

    /* if (xboxController2.getLeftTriggerAxis() > 0.1){
      //if(sensor.detectColor()){
        elevator.setUpper();
      //}
      
    }

    if (xboxController2.getRightBumperPressed()){
      elevator.setTarmac();
    } */
    
    
    
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

  if (xboxController2.getAButton()){
    elevator.setSh(true);
  } 
  if(xboxController2.getXButton()){
    elevator.setSh(false);
    elevator.revert_setSh(false);
  } 

  if(xboxController2.getBButton()){
    elevator.revert_setSh(true);
    SmartDashboard.putBoolean("pressed b", true);
  }

  if (xboxController2.getLeftBumper()){
    elevator.revert_setSh(true);
    SmartDashboard.putBoolean("pressed left", true);
  }

  if(xboxController2.getXButtonPressed()){
    elevator.setSh(false);
    elevator.revert_setSh(false);
    SmartDashboard.putBoolean("on", true);
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

  
  public void limelight(){
    this.limelight.getValues();
    
  }


  public Command getAutonomousCommand() {
      return new PID_test();
  
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Joystick Right = new Joystick(1);
    
    JoystickButton RB = new JoystickButton(xboxController, XboxController.Button.kRightBumper.value);
    RB.whenPressed(
      new SequentialCommandGroup(new find_target(driveTrain), new Position(driveTrain, limelight, gyro)
      )
    );
  }

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