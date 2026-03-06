// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;

// SetIndexer blocks forever. Until a button in teleop interrupts it.
public class SetIndexer extends Command {
  Indexer m_indexer;
  double m_spd;

  public SetIndexer(Indexer indexer, double spd) {
    m_indexer = indexer;
    m_spd = spd;
    addRequirements(m_indexer);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_indexer.setSpeed(m_spd);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    m_indexer.setSpeed(0);
  }
}
