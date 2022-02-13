// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

/** Add your docs here. */
public class Climber extends SubsystemBase{
    DigitalInput limit_switch;
    PWMSparkMax climb_motor;
    public Climber(){
        this.limit_switch = new DigitalInput(1);
        this.climb_motor = new PWMSparkMax(1);
    }

    public void check(){
        if (limit_switch.get()){
            climb_motor.set(-1);
        }
        
        else{
            climb_motor.set(1);
        }

    }


}
