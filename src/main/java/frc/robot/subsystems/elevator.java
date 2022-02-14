// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
 


/** Add your docs here. */
public class Elevator extends SubsystemBase {
  
    WPI_TalonSRX EOne;
    XboxController xboxController;   
    GenericHID genericHID;
    
    public  Elevator(){
     if (xboxController.getRightBumperPressed()== true){
        EOne.set(0.5);
    }
    else {
        EOne.set(0);
    }
}
}


