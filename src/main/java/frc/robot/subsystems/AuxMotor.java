package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// update this to neo motor
public class AuxMotor extends SubsystemBase {
  private final SparkMax motor;

  public AuxMotor(int motorID) {
    motor = new SparkMax(motorID, MotorType.kBrushless);

    SparkMaxConfig inkCFG = new SparkMaxConfig();

    inkCFG.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
    motor.configure(inkCFG, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public void setSpeed(double spd) {
    motor.set(spd);
  }

  @Override
  public void periodic() {

  }
}
