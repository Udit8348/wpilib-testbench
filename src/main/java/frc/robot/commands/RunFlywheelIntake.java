package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlywheelIntake;

public class RunFlywheelIntake extends Command {

    private final FlywheelIntake flywheel;
    private final DoubleSupplier targetForwardSpeed;
    private final DoubleSupplier targetReverseSpeed;

    public RunFlywheelIntake(
            FlywheelIntake flywheel,
            DoubleSupplier targetForwardSpeed,
            DoubleSupplier targetReverseSpeed) {

        this.flywheel = flywheel;
        this.targetForwardSpeed = targetForwardSpeed;
        this.targetReverseSpeed = targetReverseSpeed;

        addRequirements(flywheel);
    }

    @Override
    public void execute() {
        flywheel.applyOutput(targetForwardSpeed, targetReverseSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}