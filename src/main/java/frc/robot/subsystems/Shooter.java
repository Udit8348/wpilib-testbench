package frc.robot.subsystems;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Shooter is the subsystem that consists of the flywheel and intake motors.
public class Shooter extends SubsystemBase {
  private final SparkMax m_flywheel_intake_motor;
  
  public Shooter(int motorID) {
    m_flywheel_intake_motor = new SparkMax(motorID, MotorType.kBrushless);

    SparkMaxConfig cfg = new SparkMaxConfig();

    cfg.inverted(true).idleMode(IdleMode.kCoast).smartCurrentLimit(60);
    m_flywheel_intake_motor.configure(cfg, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public void setSpeed(double spd) {
    m_flywheel_intake_motor.set(spd);
  }

  @Override
  public void periodic() {}
}
