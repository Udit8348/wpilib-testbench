// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ConfigConstants;
import frc.robot.commands.SetAuxMotor;
import frc.robot.commands.ApplyMotorOutput;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDrive;
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

  // driver station
  // todo: add settings to config speed limits from the driver's station
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    
    // Set default commands
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain, () -> m_controller.getLeftY() * 0.25, () -> -m_controller.getRightX() * -0.25));
    m_flywheel_intake.setDefaultCommand(new ApplyMotorOutput(m_flywheel_intake)); // statemachine handles requested state and current state for safety
    
    //indexer : right bumpers
    m_controllerCMD.rightBumper().whileTrue(new SetAuxMotor(m_indexer, 0.2));
    m_controllerCMD.rightTrigger().whileTrue(new SetAuxMotor(m_indexer, -0.2));

    // left bumper = toggle to request flywheel on and off
    m_controllerCMD.x().onTrue(
        Commands.runOnce(() -> m_flywheel_intake.toggleForward(), m_flywheel_intake)
    );

    // left trigger = hold to request intaking
    m_controllerCMD.b()
      .onTrue(Commands.runOnce(() -> m_flywheel_intake.requestReverse(), m_flywheel_intake))
      .onFalse(Commands.runOnce(() -> m_flywheel_intake.stop(), m_flywheel_intake));
    
    SmartDashboard.putData(m_chooser);
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
