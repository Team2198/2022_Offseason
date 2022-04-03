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
    //private final Color color_blue;
    //private final Color color_red;
    //private final Color color_grey;
    private Ultrasonic sound_sensor;

    
    public Sensor(){
        this.sensor = new ColorSensorV3(i2cPort);
        this.m_colorMatcher = new ColorMatch();
        //this.color_blue = new Color(0.143, 0.427, 0.429); 
        //this.color_red = new Color(0.561, 0.232, 0.114);
        //this.color_grey = new Color(0.6627451f, 0.6627451f, 0.6627451f); 
        //this.m_colorMatcher.addColorMatch(color_blue);
        //this.m_colorMatcher.addColorMatch(color_red);
        //this.m_colorMatcher.addColorMatch(color_grey);
        this.m_colorMatcher.addColorMatch(Color.kRed);
        this.m_colorMatcher.addColorMatch(Color.kBlue);
        this.m_colorMatcher.addColorMatch(Color.kGray);
        //this.sound_sensor = new Ultrasonic(0, 1);
    }

    public boolean detectColor(){
        Color detectedColor = sensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        SmartDashboard.putString("hello", match.color.toString());
        if (match.color == Color.kRed || match.color == Color.kBlue){
            SmartDashboard.putString("Color", "Blue");
            SmartDashboard.putString("test", "idk");                    
            return true;
        }

        

        else{
            SmartDashboard.putString("Color", "Grey");
            return false;
        //I think we will continue turning the robot if it detects the ground
        }
    }


    public void detect_distance(){
        double distance = sound_sensor.getRangeInches();
        SmartDashboard.putNumber("distance", distance);
    }
}
