// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.ConfigConstants;
import frc.robot.commands.Auton.AutonNothing;
import frc.robot.commands.Auton.FlywheelShoot;
import frc.robot.commands.Teleop.ArcadeDrive;
import frc.robot.commands.Teleop.ArcadeDriveSlew;
import frc.robot.commands.Teleop.SetIndexer;
import frc.robot.commands.Teleop.SetShooter;
import frc.robot.commands.Teleop.IntoHopper;
import frc.robot.commands.Teleop.Launch;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class RobotContainer {

  // controller
  private final XboxController m_controller = new XboxController(ConfigConstants.kDriverControllerPort);
  private final CommandXboxController m_controllerCMD = new CommandXboxController(ConfigConstants.kDriverControllerPort);

  // subsystems
  private final Shooter m_shooter = new Shooter(5);
  private final Indexer m_indexer = new Indexer(6);
  private final Drive m_drivetrain = new Drive();

  // driver station
  private final SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // m_drivetrain.setDefaultCommand(new ArcadeDriveSlew(m_drivetrain, () -> m_controller.getLeftY() * 0.7, () -> -m_controller.getRightX() * -0.7, 0.5));
    // m_controllerCMD.leftTrigger().whileTrue(new Launch(m_indexer, -0.2, m_shooter, 0.2, 3.0, 6.0, false));
    // m_chooser.addOption("AutonSlowSpeedTest", new Launch(m_indexer, -0.2, m_shooter, 0.2, 3.0, 6.0, true));
    
    // drivetrain
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain, () -> m_controller.getLeftY() * 0.7, () -> -m_controller.getRightX() * -0.7));
    
    // intake and shoot macros
    m_controllerCMD.a().whileTrue(new IntoHopper(m_indexer, -0.8, m_shooter, -0.8)); //unloads the hopper
    m_controllerCMD.x().whileTrue(new IntoHopper(m_indexer, 0.0, m_shooter, -0.8)); //
    m_controllerCMD.b().whileTrue(new IntoHopper(m_indexer, 0.8, m_shooter, 0.0)); //unjams back into the hopper
    m_controllerCMD.rightTrigger().whileTrue(new IntoHopper(m_indexer, 0.5, m_shooter, 0.5));
    m_controllerCMD.leftTrigger().whileTrue(new Launch(m_indexer, -0.8, 0.5, m_shooter, 0.7, 3.0, -1.0, false));
                            
    // Setup SmartDashboard options for auton
    m_chooser.setDefaultOption("AutonNothing", new AutonNothing(m_drivetrain));
    m_chooser.addOption("DontMoveShoot", new Launch(m_indexer, -0.8, 0.5, m_shooter, 0.6, 3.0, 8.0, true));
    SmartDashboard.putData(m_chooser);
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
