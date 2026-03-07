package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.TimePrimitives.IndexerForTime;
import frc.robot.commands.TimePrimitives.ShootForTime;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// Set a deadline for how long we want to keep the shooter on.
// Warn: .deadlineWith is marked for removal

/*
public class FlywheelShootDeadline extends SequentialCommandGroup {
  public FlywheelShootDeadline(Shooter fw, Indexer idxr) {
    addCommands(
      new ShootForTime(fw, 0.7, 2.0),
      new IndexerForTime(idxr, -0.8, 2.0)
      .deadlineWith(new ShootForTime(fw, 0.7, 10.0))
      );
    }
  }
*/