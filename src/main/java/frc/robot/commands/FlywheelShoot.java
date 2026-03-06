// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;

public class FlywheelShoot extends SequentialCommandGroup {
  /**
   * Creates a new Autonomous Drive based on time. This will drive forward for a period of time.
   *
   * @param drivetrain The drive subsystem on which this command will run
   */
  public FlywheelShoot(Shooter fw, Indexer idxr) {
    addCommands(
        new ShootTime(0.7, 2, fw),
        new SetIndexer(idxr, -0.8),
        new ShootTime(0.8, 6, fw)
    );
  }
}
