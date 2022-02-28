// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;


/** Add your docs here. */
public class Climber extends SubsystemBase{
    DigitalInput limit_switch_above;
    PWMSparkMax climb_motor;
    DigitalInput limit_switch_below;

    public Climber(){
        //Creating an instance for limit_swicth_above
        this.limit_switch_above = new DigitalInput(1);
        this.climb_motor = new PWMSparkMax(1);
        this.limit_switch_below = new DigitalInput(2);
    }

    public void climber_set(boolean direction){
        if (direction && !limit_switch_above.get() ){
            climb_motor.set(0.2);
        }    

        else if (!direction  && !limit_switch_below.get() ) {
            climb_motor.set(0.2);
        }   
    }
}
