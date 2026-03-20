// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// Spin flywheel upto speed. Then spin indexer out of hopper. Start a timer during cmd init.
// Keep an instance of this cmd running while a trigger button is held. Unpressing the button will end this cmd.
// Improvement: Use velocity to gate the indexer.
public class LaunchFixed extends Command {
  Indexer m_indexer;
  Shooter m_shooter;

  double m_spd_indexer_launch;
  double m_spd_indexer_unjam;
  double m_spd_shooter;
  double spinup_time;
  double deadline_time;
  boolean isAuto;

  private final Timer m_timer = new Timer();

  public LaunchFixed(Indexer indexer, double spd_indexer_launch, double spd_indexer_unjam, Shooter shooter, double spd_shooter, double _spinup_time, double _deadline_time, boolean _isAuto) {
    m_indexer = indexer;
    m_shooter = shooter;

    m_spd_indexer_launch = spd_indexer_launch;
    m_spd_indexer_unjam = spd_indexer_unjam;
    m_spd_shooter = spd_shooter;
    spinup_time = _spinup_time;
    deadline_time = _deadline_time;
    isAuto = _isAuto;
    
    addRequirements(m_indexer);
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void execute() {
    // keep shooter spinning
    m_shooter.setSpeed(m_spd_shooter);

    // only start the indexer after a certain time delay
    if (m_timer.hasElapsed(spinup_time)) {
      m_indexer.setSpeed(m_spd_indexer_launch);
    } else {
      m_indexer.setSpeed(m_spd_indexer_unjam);
    }
  }

  @Override
  public boolean isFinished() {
    if (!isAuto) {
      // let the controller deschedule this
      return false;
    } else {
      // in auto: exit when the deadline time is set
      if (m_timer.hasElapsed(deadline_time)) {
        return true;
      } else {
        return false;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_indexer.setSpeed(0);
    m_shooter.setSpeed(0);
  }
}
