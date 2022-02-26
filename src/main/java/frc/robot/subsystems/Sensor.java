// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/** Add your docs here. */
public class Sensor extends SubsystemBase {
    private final ColorSensorV3 sensor;
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorMatch m_colorMatcher;
    private final Color color_blue;
    private final Color color_red;
    private final Color color_grey;
    private Ultrasonic sound_sensor;

    
    public Sensor(){
        this.sensor = new ColorSensorV3(i2cPort);
        this.m_colorMatcher = new ColorMatch();
        this.color_blue = new Color(0.143, 0.427, 0.429); 
        this.color_red = new Color(0.561, 0.232, 0.114);
        this.color_grey = new Color(0.6627451f, 0.6627451f, 0.6627451f); 
        this.m_colorMatcher.addColorMatch(color_blue);
        this.m_colorMatcher.addColorMatch(color_red);
        this.m_colorMatcher.addColorMatch(color_grey);
        this.sound_sensor = new Ultrasonic(0, 1);
    }

    public void detectColor(){
        Color detectedColor = sensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        
        if (match.color == color_blue){
            SmartDashboard.putString("Color", "Blue");
            SmartDashboard.putString("test", "idk");                    
        }

        if (match.color == color_red){
           SmartDashboard.putString("Color", "Red");
        }

        if (match.color == color_grey){
            SmartDashboard.putString("Color", "Grey");
        //I think we will continue turning the robot if it detects the ground
        }
    }


    public void detect_distance(){
        double distance = sound_sensor.getRangeInches();
        SmartDashboard.putNumber("distance", distance);
    }
}
