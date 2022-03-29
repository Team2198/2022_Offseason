// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.DriveTrain;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  
  private Command m_autonomousCommand;
  private Timer timer;
  private RobotContainer m_robotContainer;
  private DriveTrain driveTrain;

  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    //reset everything
    this.m_robotContainer = new RobotContainer();
    this.timer = new Timer();
    this.driveTrain = new DriveTrain();
    m_robotContainer.reset();
    timer.reset();
    

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    

    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    timer.stop();
    timer.reset();
    SmartDashboard.putNumber("curr timer", timer.get());
    timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    SmartDashboard.putString("bye", "hello");
    m_robotContainer.reset();
    if (!this.timer.hasElapsed(6)){ // change this for forward
      SmartDashboard.putNumber("next timer", timer.get());
      m_robotContainer.elevator();
    }
    else{ 
      
      m_robotContainer.setZero(true);
      
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
    //schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }


  }

  @Override
  public void teleopInit() {
    
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();

    }

    m_robotContainer.reset();
    
    
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    m_robotContainer.get_val();
    /* if (timer.hasElapsed(121)){
      m_robotContainer.Climber_status();
    }  */
    m_robotContainer.Climber_status();
    m_robotContainer.PowerElevator();
    m_robotContainer.trig_shooter();



  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}