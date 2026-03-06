// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

// SetShooter blocks forever. Until a button in teleop interrupts it.
public class SetShooter extends Command {
  Shooter m_shooter;
  double m_spd;

  public SetShooter(Shooter shooter, double spd) {
    m_shooter = shooter;
    m_spd = spd;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_shooter.setSpeed(m_spd);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.setSpeed(0);
  }
}
