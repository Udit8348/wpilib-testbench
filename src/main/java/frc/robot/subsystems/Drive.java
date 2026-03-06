package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.kLeftBackID;
import static frc.robot.Constants.DrivetrainConstants.kLeftFrontID;
import static frc.robot.Constants.DrivetrainConstants.kRightBackID;
import static frc.robot.Constants.DrivetrainConstants.kRightFrontID;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

  DifferentialDrive m_drivetrain;
  private final SparkMax rightBackSpark;
  private final SparkMax rightFrontSpark;
  private final SparkMax leftBackSpark;
  private final SparkMax leftFrontSpark;

  public Drive() {
    // Brushless SparkMax for NEO motors
    rightBackSpark = new SparkMax(kRightBackID, MotorType.kBrushless);
    rightFrontSpark = new SparkMax(kRightFrontID, MotorType.kBrushless);
    leftBackSpark = new SparkMax(kLeftBackID, MotorType.kBrushless);
    leftFrontSpark = new SparkMax(kLeftFrontID, MotorType.kBrushless);

    // Configure Right Front SparkMax (Leader Motor)
    SparkMaxConfig rightFrontCfg = new SparkMaxConfig();
    rightFrontCfg.inverted(false).idleMode(IdleMode.kCoast).smartCurrentLimit(80, 50);
    rightFrontSpark.configure(rightFrontCfg, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    // Configure Right Back SparkMax (Follower Motor)
    SparkMaxConfig rightBackCfg = new SparkMaxConfig();
    rightBackCfg.inverted(false).follow(kRightFrontID).idleMode(IdleMode.kCoast).smartCurrentLimit(80, 50);
    rightBackSpark.configure(rightBackCfg, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // Configure Left Front SparkMax (Leader Motor)
    SparkMaxConfig leftFrontCfg = new SparkMaxConfig();
    leftFrontCfg.inverted(true).idleMode(IdleMode.kCoast).smartCurrentLimit(80, 50);
    leftFrontSpark.configure(leftFrontCfg, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    // Configure Left Back SparkMax (Follower Motor)
    SparkMaxConfig leftBackCfg = new SparkMaxConfig();
    leftBackCfg.inverted(true).follow(kLeftFrontID).idleMode(IdleMode.kCoast).smartCurrentLimit(80, 50);
    leftBackSpark.configure(leftBackCfg, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    

    // set up a differential drive with our motors
    m_drivetrain = new DifferentialDrive(leftFrontSpark::set, rightFrontSpark::set);

    SendableRegistry.addChild(m_drivetrain, leftFrontSpark);
    SendableRegistry.addChild(m_drivetrain, rightFrontSpark);
  }

  public void arcadeDrive(double speed, double rotation) {
    m_drivetrain.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    /*This method will be called once per scheduler run. It can be used for running tasks we know we want to update each
     * loop such as processing sensor data. Our drivetrain is simple so we don't have anything to put here */
  }
}
