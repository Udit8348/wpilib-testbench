package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.TimePrimitives.IndexerForTime;
import frc.robot.commands.TimePrimitives.ShootForTime;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// deprecated: only worked with buggy functions
public class FlywheelShoot extends SequentialCommandGroup {
  public FlywheelShoot(Shooter fw, Indexer idxr) {
    addCommands(
        // Step 1: spin up flywheel only
        new ShootForTime(fw, 0.7, 2.0),

        // Step 2: keep flywheel running while feeding
        new ParallelCommandGroup(
          new IndexerForTime(idxr, -0.8, 2.0)),
          new ShootForTime(fw, 0.7, 2.0)
    );
  }
}
