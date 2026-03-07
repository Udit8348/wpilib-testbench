// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TimePrimitives;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class DriveForTime extends Command {
  private final Drive m_drive;
  private final double m_speed;
  private final double m_duration;
  private long m_startTime;

  public DriveForTime(Drive drive, double speed, double time) {
    m_drive = drive;
    m_speed = speed;
    m_duration = time * 1000;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return (System.currentTimeMillis() - m_startTime) >= m_duration;
  }
}
