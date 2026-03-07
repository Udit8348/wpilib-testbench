// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TimePrimitives;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class ShootForTime extends Command {
  private final Shooter m_shooter;
  private final double m_duration;
  private final double m_speed;
  private long m_startTime;

  public ShootForTime(Shooter shooter, double speed, double time) {
    m_speed = speed;
    m_duration = time * 1000;
    m_shooter = shooter;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
    m_shooter.setSpeed(0);
  }

  @Override
  public void execute() {
    m_shooter.setSpeed(m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    // m_shooter.setSpeed(0);
    m_shooter.setSpeed(m_speed);
  }

  @Override
  public boolean isFinished() {
    return (System.currentTimeMillis() - m_startTime) >= m_duration;
  }
}
