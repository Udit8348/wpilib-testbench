// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// Spin both shooter (intake) and indexer in the correct direction to bring balls into the hopper.
public class IntoHopper extends Command {
  Indexer m_indexer;
  Shooter m_intake;

  double m_spd_indexer;
  double m_spd_intake;

  public IntoHopper(Indexer indexer, double spd_indexer, Shooter intake, double spd_intake) {
    m_indexer = indexer;
    m_intake = intake;

    m_spd_indexer = spd_indexer;
    m_spd_intake = spd_intake;
    
    addRequirements(m_indexer);
    addRequirements(m_intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_indexer.setSpeed(m_spd_indexer);
    m_intake.setSpeed(m_spd_intake);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    m_indexer.setSpeed(0);
    m_intake.setSpeed(0);
  }
}
