// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TiltSubsystem extends SubsystemBase {

  private static final int tiltID = 4;
  private CANSparkMax m_tiltMotor;

  private DigitalInput top;
  private DigitalInput bottom;

  /** Creates a new TiltSubsystem. */
  public TiltSubsystem() {
    m_tiltMotor = new CANSparkMax(tiltID, MotorType.kBrushless);
    top = new DigitalInput(0);
    bottom = new DigitalInput(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void tilt() {
    m_tiltMotor.set(.6);
  }

  public void reverse() {
    m_tiltMotor.set(-.6);
  }

  public boolean atTop() {
    return top.get();
  }

  public boolean atBottom() {
    return bottom.get();
  }
}
