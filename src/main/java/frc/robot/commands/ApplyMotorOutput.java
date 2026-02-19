package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlywheelIntake;

public class ApplyMotorOutput extends Command {

    private final FlywheelIntake motor;

    public ApplyMotorOutput(FlywheelIntake motor) {
        this.motor = motor;
        addRequirements(motor);
    }

    @Override
    public void execute() {
        motor.applyOutput();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}