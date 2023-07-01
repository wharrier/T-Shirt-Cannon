// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  private static final int solenoidForward = 0;
  private static final int solenoidReverse = 1;
  private DoubleSolenoid solenoid;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    solenoid = new DoubleSolenoid(1, PneumaticsModuleType.REVPH, solenoidForward, solenoidReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot() {
    solenoid.set(Value.kForward);
  }

  public void disable() {
    solenoid.set(Value.kReverse);
  }
}
