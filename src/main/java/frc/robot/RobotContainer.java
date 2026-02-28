// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ConfigConstants;
import frc.robot.commands.Shoot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.subsystems.AuxMotor;
import frc.robot.subsystems.Drive;

public class RobotContainer {

  // controller
  private final XboxController m_controller = new XboxController(ConfigConstants.kDriverControllerPort);
  private final CommandXboxController m_controllerCMD = new CommandXboxController(ConfigConstants.kDriverControllerPort);

  // subsystems
  private final AuxMotor m_indexer = new AuxMotor(6);
  private final AuxMotor m_flywheel = new AuxMotor(5);
  private final Drive m_drivetrain = new Drive();

  // driver station
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // drive train
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain, () -> m_controller.getLeftY() * 0.8, () -> -m_controller.getRightX() * -0.8
    ));
    
    //joystick button Y/A
   // m_controllerCMD.y().whileTrue(new Shoot(AuxMotor(m_flywheel,m_indexer)));
    m_controllerCMD.leftBumper().whileTrue(new Shoot(m_flywheel,m_indexer));

    //joystick button X/B
  //  m_controllerCMD.x().whileTrue(new SetMotor(m_flywheel, 0.8));
  //  m_controllerCMD.b().whileTrue(new SetMotor(m_flywheel, 0.4));
    
    SmartDashboard.putData(m_chooser);
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
