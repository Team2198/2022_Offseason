package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro_Programming;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.subsystems.Limelight_programming;


public class Position extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveTrain driveTrain;
    private final PIDController pid_controller = new PIDController(0.5, 0, 0);
    private final Limelight_programming limelight = new Limelight_programming();
    private final Gyro_Programming gyro = new Gyro_Programming();
    double offset;
    double angle_needed;
    public Position() {
        driveTrain = new DriveTrain();
        addRequirements(driveTrain);
        addRequirements(limelight);
        addRequirements(gyro);
        
    }

    @Override
      public void initialize() {
        offset = limelight.getValues()[0]; 
        angle_needed = gyro.gyro_angle() + offset;

      }

    @Override
      public void execute() {
        driveTrain.setMotor(0, pid_controller.calculate(gyro.gyro_angle(), angle_needed));//120 is 10 feet in inches
      }
    
      @Override
      public boolean isFinished() {
        if (this.pid_controller.atSetpoint()){
          this.pid_controller.reset();
        
          return true;
        }
        
        return false;
      }
}
