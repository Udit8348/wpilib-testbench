package frc.robot.subsystems;

import static frc.robot.Constants.CANConstants;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
  // private final SparkMax intake;
  private final SparkMax intake;

  public Motor() {
    intake = new SparkMax(CANConstants.kMotorID, MotorType.kBrushed);

    SparkMaxConfig inkCFG = new SparkMaxConfig();

    inkCFG.inverted(true).idleMode(IdleMode.kCoast).smartCurrentLimit(80, 80);
  }

  public void setSpeed(double spd) {
    intake.set(spd);
  }

  @Override
  public void periodic() {}
}
