// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Motor;

public class SetMotor extends Command {
  Motor m_arm;
  double m_spd;

  public SetMotor(Motor arm, double spd) {
    m_arm = arm;
    m_spd = spd;
    addRequirements(m_arm);
  }

  // The initialize method is called when the command is initially scheduled.
  @Override
  public void initialize() {
    // set init values.
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_arm.setSpeed(m_spd);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Always return false so the command never ends on it's own. In this project we use the
    // scheduler to end the command when the button is released.
    return false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop the wheels when the command ends.
    m_arm.setSpeed(0);
  }
}
