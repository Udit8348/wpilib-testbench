package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlywheelIntake extends SubsystemBase {

    private final SparkMax motor;
    private final RelativeEncoder encoder;

    private enum State {
        IDLE,
        FORWARD,
        REVERSE,
        STOPPING
    }

    private State currentState = State.IDLE;
    private State desiredState = State.IDLE;

    private static final double kForwardSpeed = 0.6;
    private static final double kReverseSpeed = -0.4;
    private static final double kStopThreshold = 1; // RPM

    public FlywheelIntake(int motorID) {
        motor = new SparkMax(motorID, MotorType.kBrushless);
        encoder = motor.getEncoder();
    }

    /* =========================
       Intent Methods (Buttons)
       ========================= */

    public void toggleForward() {
        // if we previously asked for forward, and we ask again toggle fw off
        if (desiredState == State.FORWARD) {
            desiredState = State.IDLE;
        } else {
            desiredState = State.FORWARD;
        }
    }

    public void requestReverse() {
        desiredState = State.REVERSE;
    }

    public void stop() {
        desiredState = State.IDLE;
    }

    public double getVelocity() {
        return encoder.getVelocity();
    }

    /* =========================
       State Machine Execution
       ========================= */

    public void applyOutput() {

        double velocity = encoder.getVelocity();

        switch (currentState) {

            case IDLE:
                motor.set(0.0);

                if (desiredState == State.FORWARD) {
                    currentState = State.FORWARD;
                } else if (desiredState == State.REVERSE) {
                    currentState = State.REVERSE;
                }
                break;

            case FORWARD:
                motor.set(kForwardSpeed);

                if (desiredState != State.FORWARD) {
                    currentState = State.STOPPING;
                }
                break;

            case REVERSE:
                motor.set(kReverseSpeed);

                if (desiredState != State.REVERSE) {
                    currentState = State.STOPPING;
                }
                break;

            case STOPPING:
                motor.set(0.0);

                // block exiting STOPPING state until the RPM is below the treshold
                if (Math.abs(velocity) <= kStopThreshold) {
                    currentState = desiredState;
                }
                break;
        }
    }
}