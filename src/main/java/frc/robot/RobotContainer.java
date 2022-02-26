// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
<<<<<<< HEAD
=======

import javax.swing.plaf.TreeUI;

>>>>>>> c8b86e82b8441ff75eb428467ed435f09ceaa4e6
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
<<<<<<< HEAD


=======
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Elevator;
>>>>>>> c8b86e82b8441ff75eb428467ed435f09ceaa4e6

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  Elevator elevator;


  private final DriveTrain driveTrain;  
  private final Intake intake;
  private final XboxController xboxController;
  private final Climber climber;
  private final Elevator elevator;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindingsd
    configureButtonBindings();
    this.xboxController = new XboxController(1);
    this.driveTrain = new DriveTrain();
    this.intake = new Intake();
    this.climber = new Climber();
    this.elevator = new Elevator();
    //

    //public double get_valY(){
      //return xboxController.getY(GenericHID.Hand.kRight);
    //}
    //public double get_valX(){
    //  return xboxController.getX(GenericHID.Hand.kLeft);
    //}
  }

  public void get_val(){
    driveTrain.setMotor(xboxController.getRightY(), xboxController.getLeftX());
  }

  public void pressButton(){
  if (xboxController.getAButton()){
    intake.setComp(true); 
  }
  if (xboxController.getBButton()){
    intake.setComp(false);    
  }
 
  }
public void pressButton(){
 if (xboxController.getAButton() == true){
  intake.setComp(true);
 }
  if (xboxController.getBButton() == true){
    intake.setComp(false);

 }
}
public void PowerElevator(){
  if (xboxController.getRightBumperPressed()== true){
    elevator.setEMotar(true);
   }
  else if (xboxController.getLeftBumperPressed()== true){
    elevator.setEMotar(false);
  }
}

  
  public void Climber_status(){
    double speed = xboxController.getRightTriggerAxis() - xboxController.getLeftTriggerAxis();
    if (speed > 0){
      climber.climber_set(true);
    }

    if (speed < 0){
      climber.climber_set(false);
    }
  }

  public void autonomous(){

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
