// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drive;

public class AutonNothing extends SequentialCommandGroup {
  /**
   * Creates a new Autonomous Drive based on time. This will drive forward for a period of time.
   *
   * @param drivetrain The drive subsystem on which this command will run
   */
  public AutonNothing(Drive drivetrain) {
    addCommands(new DriveTime(0.0, 0.1, drivetrain));
  }
}
