// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.io.Console;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

/** Add your docs here. */
public class Climber extends SubsystemBase{
    DigitalInput limit_switch_above;
    WPI_VictorSPX climb_motor;
    DigitalInput limit_switch_below;

    public Climber(){
        //Creating an instance for limit_swicth_above
        this.limit_switch_above = new DigitalInput(1);
        this.climb_motor = new WPI_VictorSPX(Constants.Climb_Motor);
        this.limit_switch_below = new DigitalInput(2);
    }

    public void climber_set(double speed, boolean direction){
        //SmartDashboard.putBoolean("Limit Switch Below", limit_switch_below.get());
        //SmartDashboard.putBoolean("Limit Switch above", limit_switch_above.get());
        if (direction && limit_switch_above.get()){
            climb_motor.set(speed);
        }    
        else if(direction && !limit_switch_above.get()){
            climb_motor.set(0);
        }
       else if (!direction  && limit_switch_below.get()) {
            climb_motor.set(speed);

        }
        else if(!direction  && !limit_switch_below.get()){
            climb_motor.set(0);
            
        } 
    }
}