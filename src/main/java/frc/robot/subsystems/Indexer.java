package frc.robot.subsystems;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Indexer is the subsystem that consists of the indexer motor.
public class Indexer extends SubsystemBase {
  private final SparkMax m_indexer_motor;

  public Indexer(int motorID) {
    m_indexer_motor = new SparkMax(motorID, MotorType.kBrushless);

    SparkMaxConfig cfg = new SparkMaxConfig();

    cfg.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
    m_indexer_motor.configure(cfg, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public void setSpeed(double spd) {
    m_indexer_motor.set(spd);
  }

  @Override
  public void periodic() {}
}
