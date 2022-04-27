package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.math.controller.PIDController;


public class PID_test extends CommandBase{
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final DriveTrain driveTrain;
    private final PIDController pid_controller = new PIDController(0.5, 0, 0);
    double counts = 400;

    public PID_test() {
        driveTrain = new DriveTrain();
        addRequirements(driveTrain);
        
    }
    
      @Override
      public void initialize() {
        
        driveTrain.encoder.setDistancePerPulse(2*Math.PI/counts);
      }

      @Override
      public void execute() {
        driveTrain.setMotor(-pid_controller.calculate(driveTrain.encoder.getDistance(), 60), 0);//120 is 10 feet in inches
      }
    
      @Override
      public boolean isFinished() {
        if (this.pid_controller.atSetpoint()){
          this.pid_controller.reset();
          driveTrain.encoder.reset();  
          return true;
        }
        
        return false;
      }
    }
      


   
  
    //private final ExampleSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  //public ExampleCommand(ExampleSubsystem subsystem) {
    //m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
  
//}


// An example command that uses an example subsystem. 
  