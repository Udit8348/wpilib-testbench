// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.ConfigConstants;

import frc.robot.commands.SetAuxMotor;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.RunFlywheelIntake;

import frc.robot.subsystems.FlywheelIntake;
import frc.robot.subsystems.AuxMotor;
import frc.robot.subsystems.Drive;

public class RobotContainer {

  // controller
  private final XboxController m_controller = new XboxController(ConfigConstants.kDriverControllerPort);
  private final CommandXboxController m_controllerCMD = new CommandXboxController(ConfigConstants.kDriverControllerPort);

  // subsystems
  private final AuxMotor m_indexer = new AuxMotor(5);
  private final FlywheelIntake m_flywheel_intake = new FlywheelIntake(6);
  private final Drive m_drivetrain = new Drive();

  private static final String kIndexerSpeed = "Indexer Speed";
  private static final String kFlywheelTargetForwardSpeed = "Flywheel Target Forward Speed";
  private static final String kFlywheelTargetReverseSpeed = "Flywheel Target Reverse Speed";

  // driver station
  // todo: add settings to config speed limits from the driver's station
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  public RobotContainer() {
    SmartDashboard.putNumber(kFlywheelTargetForwardSpeed, 0.6);
    SmartDashboard.putNumber(kFlywheelTargetReverseSpeed, -0.4);
    SmartDashboard.putNumber(kIndexerSpeed, 0.2);
    configureBindings();
  }

  private void configureBindings() {
    
    // Set default commands
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain, () -> m_controller.getLeftY() * 0.25, () -> -m_controller.getRightX() * -0.25));
    
    
    // indexer spins forward while the right bumper is pressed
    // indexer spins backwards while the right trigger is pressed
    // speed is configurable from the driver's station gui
    double indexer_speed = SmartDashboard.getNumber(kIndexerSpeed, 0.6);
    m_controllerCMD.rightBumper().whileTrue(new SetAuxMotor(m_indexer, indexer_speed));
    m_controllerCMD.rightTrigger().whileTrue(new SetAuxMotor(m_indexer, -indexer_speed));


    // the flywheel and intake shared motor continuously runs a state machine to safely handle motor direction changes
    // speeds can be configured from the driver's station gui
    m_flywheel_intake.setDefaultCommand(
      new RunFlywheelIntake(
        m_flywheel_intake,
        () -> SmartDashboard.getNumber(kFlywheelTargetForwardSpeed, 0.6),
        () -> SmartDashboard.getNumber(kFlywheelTargetReverseSpeed, -0.4)
      )
    );
    
    // press x to make a request for the flywheel to toggle on / off
    m_controllerCMD.x().onTrue(
        Commands.runOnce(() -> m_flywheel_intake.toggleForward(), m_flywheel_intake)
    );

    // hold down left trigger to make a request for the intake to turn on
    m_controllerCMD.leftTrigger()
      .onTrue(Commands.runOnce(() -> m_flywheel_intake.requestReverse(), m_flywheel_intake))
      .onFalse(Commands.runOnce(() -> m_flywheel_intake.stop(), m_flywheel_intake));
    
    SmartDashboard.putData(m_chooser);
    SmartDashboard.putNumber(kIndexerSpeed, 0.2);
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
