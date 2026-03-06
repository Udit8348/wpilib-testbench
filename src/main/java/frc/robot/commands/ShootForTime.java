package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;


// hold shooter speed and run indexer. 
public class ShootForTime extends ParallelCommandGroup {
    public ShootForTime(Shooter fw, Indexer idxr, double time) {
        addCommands(
            new ShootTime(0.8, 6, fw),
            new IndexerTime(time, time, idxr)
        );
    }
}
