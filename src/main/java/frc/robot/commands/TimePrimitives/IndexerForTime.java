// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TimePrimitives;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;

public class IndexerForTime extends Command {
  private final Indexer m_indexer;
  private final double m_speed;
  private final double m_duration;
  private long m_startTime;

  public IndexerForTime(Indexer indexer, double speed, double time) {
    m_speed = speed;
    m_duration = time * 1000;
    m_indexer = indexer;
    addRequirements(m_indexer);
  }

  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
    m_indexer.setSpeed(0);
  }

  @Override
  public void execute() {
     m_indexer.setSpeed(m_speed);
  }

  @Override
  public void end(boolean interrupted) {
    m_indexer.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return (System.currentTimeMillis() - m_startTime) >= m_duration;
  }
}
