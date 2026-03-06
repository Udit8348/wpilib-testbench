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
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutonNothing;
import frc.robot.commands.FlywheelShoot;
import frc.robot.commands.SetIndexer;
import frc.robot.commands.SetShooter;
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
    // drivetrain
    m_drivetrain.setDefaultCommand(new ArcadeDrive(m_drivetrain, () -> m_controller.getLeftY() * 0.7, () -> -m_controller.getRightX() * -0.7));

    //joystick button X/B (indexer)
    m_controllerCMD.leftTrigger().whileTrue(new SetShooter(m_shooter, 0.8));
    m_controllerCMD.x().whileTrue(new SetShooter(m_shooter, 0.3));
    m_controllerCMD.y().whileTrue(new SetShooter(m_shooter, -0.2));
    m_controllerCMD.a().whileTrue(new SetShooter(m_shooter, -0.8));
    
    /**
     *  basic indexer: press and hold for the direction you want
     */

    m_controllerCMD.rightTrigger().whileTrue(new SetIndexer(m_indexer, 0.5));
    m_controllerCMD.rightBumper().whileTrue(new SetIndexer(m_indexer, -0.5)); // this is the direction for shooting

    
    /**
     *  Intermediate Indexer: toggle on for either forwards or backward direction.
     *  This means you can drive without holding this down.
     *  Press to turn on, press same button to turn off
     */

    // m_controllerCMD.rightTrigger().toggleOnTrue(new SetIndexer(m_indexer, 0.25)); // spin slowly for collecting into the hopper
    // m_controllerCMD.rightBumper().toggleOnTrue(new SetIndexer(m_indexer, -1.0));     // spin spin fast for shooting (might have directions mixed up, need to check)


    /**
     *  basic flywheel
     */

    // m_controllerCMD.leftTrigger().whileTrue(new SetShooter(m_shooter, 0.8)); // shoot
    // m_controllerCMD.y().whileTrue(new SetShooter(m_shooter, -0.2));              // dumping (normal)
    // m_controllerCMD.a().whileTrue(new SetShooter(m_shooter, -0.8));              // dumping (more power)

    /*
     * Advanced: Example Combined Macros for Automating Flywheel and Indexer
     */

    // press and hold

    // m_controllerCMD.b().whileTrue(
    //   Commands.parallel(
    //         new SetIndexer(m_indexer, 0.25),
    //         new SetShooter(m_shooter, 0.35)
    //     )
    // );
    
    // tap on / tap off

    // m_controllerCMD.b().toggleOnTrue(
    //   Commands.parallel(
    //         new SetIndexer(m_indexer, 0.25),
    //         new SetShooter(m_shooter, 0.35)
    //     )
    // );

    // complex sequence example

    // m_controllerCMD.b().onTrue(
    // Commands.deadline(
    //     Commands.waitSeconds(0.75),
    //     new SetIndexer(m_indexer, 0.25)
    // ).andThen(
    //     Commands.parallel(
    //         new SetIndexer(m_indexer, 0.25),
    //         new SetShooter(m_shooter, 0.35)
    //     )
    // );
    
    // Setup SmartDashboard options for auton
    m_chooser.setDefaultOption("AutonNothing", new AutonNothing(m_drivetrain));
    m_chooser.addOption("DontMoveShoot", new FlywheelShoot(m_shooter, m_indexer));
    SmartDashboard.putData(m_chooser);
  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
