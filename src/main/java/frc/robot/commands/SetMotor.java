// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;
import frc.robot.subsystems.AuxMotor;

public class SetMotor extends Command {
  AuxMotor m_motor;
  double m_spd;
  boolean m_usePosition = false;
  double m_targetRotations = 0.0;
  boolean m_resetOnInit = false;

  public SetMotor(AuxMotor motor1, double spd) {
    m_motor = motor1;
    m_spd = spd;
    addRequirements(m_motor);
  }

  // // New: position-target constructor using the NEO integrated encoder
  // public SetMotor(Motor motor1, double targetRotations, boolean resetOnInit) {
  //   m_motor = motor1;
  //   m_usePosition = true;
  //   m_targetRotations = targetRotations;
  //   m_resetOnInit = resetOnInit;
  //   addRequirements(m_motor);
  // }

  // The initialize method is called when the command is initially scheduled.
  @Override
  public void initialize() {
    // // set init values.
    // if (m_usePosition) {
    //   if (m_resetOnInit) {
    //     m_motor.resetPosition();
    //   }
    //   m_motor.turnToRotations(m_targetRotations);
    // }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // the Command does not set the motor speed, motor periodic does.
    m_motor.setSpeed(m_spd);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // // For position mode, finish when we're at the setpoint; otherwise never finish on its own.
    // if (m_usePosition) {
    //   return m_motor.atSetpoint();
    // }
    return false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop the wheels when the command ends.
    m_motor.setSpeed(0);
  }
}
