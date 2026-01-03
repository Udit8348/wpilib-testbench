package frc.robot.subsystems;

import static frc.robot.Constants.CANConstants;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
// import com.revrobotics.spark.SparkRelativeEncoder;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// update this to neo motor
public class Motor extends SubsystemBase {
  // private final SparkMax intake;
  private final SparkMax intake;
  private RelativeEncoder encoder;
  private final PIDController positionPID = new PIDController(0.1, 0.0, 0.0);
  private double targetRotations = 0.0;

  public Motor() {
    intake = new SparkMax(CANConstants.kMotorID, MotorType.kBrushless);

    SparkMaxConfig inkCFG = new SparkMaxConfig();

    inkCFG.inverted(true).idleMode(IdleMode.kBrake).smartCurrentLimit(60);
    intake.configure(inkCFG, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

    encoder = intake.getEncoder();
    encoder.setPosition(0.0);

    positionPID.setTolerance(0.02);
  }

  public void setSpeed(double spd) {
    intake.set(spd);
  }

  public double getPositionRotations() {
    return encoder.getPosition();
  }

  public void resetPosition() {
    encoder.setPosition(0.0);
  }

  public void turnToRotations(double rotations) {
    targetRotations = rotations;
  }

  public boolean atSetpoint() {
    return positionPID.atSetpoint();
  }

  @Override
  public void periodic() {
    double output = positionPID.calculate(getPositionRotations(), targetRotations);
    output = MathUtil.clamp(output, -1.0, 1.0);
    intake.set(output);
  }
}
