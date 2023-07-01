// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.AbsoluteEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RotateSubsystem extends SubsystemBase {

  private final CANSparkMax m_turningSparkMax;
  private final AbsoluteEncoder m_turningEncoder;
  private final SparkMaxPIDController m_turningPIDController;

  private static final double kTurningEncoderPositionFactor = (2 * Math.PI); // radians
  private static final double kTurningEncoderVelocityFactor = (2 * Math.PI) / 60.0; // radians per second
  public static final double kTurningEncoderPositionPIDMinInput = 0; // radians
  public static final double kTurningEncoderPositionPIDMaxInput = kTurningEncoderPositionFactor; // radians

  public static final double kTurningP = 1;
  public static final double kTurningI = 0;
  public static final double kTurningD = 0;
  public static final double kTurningFF = 0;
  public static final double kTurningMinOutput = -1;
  public static final double kTurningMaxOutput = 1;

  public static final int kTurningMotorCurrentLimit = 20; 

  private static final int turningCANId = 38;

  /** Creates a new RotateSubsystem. */
  public RotateSubsystem() {
    m_turningSparkMax = new CANSparkMax(turningCANId, MotorType.kBrushless);
    m_turningSparkMax.restoreFactoryDefaults();

    m_turningEncoder = m_turningSparkMax.getAbsoluteEncoder(Type.kDutyCycle);
    m_turningPIDController = m_turningSparkMax.getPIDController();
    m_turningPIDController.setFeedbackDevice(m_turningEncoder);

    m_turningEncoder.setPositionConversionFactor(kTurningEncoderPositionFactor);
    m_turningEncoder.setVelocityConversionFactor(kTurningEncoderVelocityFactor);

    // Enable PID wrap around for the turning motor. This will allow the PID
    // controller to go through 0 to get to the setpoint i.e. going from 350 degrees
    // to 10 degrees will go through 0 rather than the other direction which is a
    // longer route.
    m_turningPIDController.setPositionPIDWrappingEnabled(true);
    m_turningPIDController.setPositionPIDWrappingMinInput(kTurningEncoderPositionPIDMinInput);
    m_turningPIDController.setPositionPIDWrappingMaxInput(kTurningEncoderPositionPIDMaxInput);

    // Set the PID gains for the turning motor. Note these are example gains, and you
    // may need to tune them for your own robot!
    m_turningPIDController.setP(kTurningP);
    m_turningPIDController.setI(kTurningI);
    m_turningPIDController.setD(kTurningD);
    m_turningPIDController.setFF(kTurningFF);
    m_turningPIDController.setOutputRange(kTurningMinOutput, kTurningMaxOutput);

    m_turningSparkMax.setIdleMode(IdleMode.kBrake);
    m_turningSparkMax.setSmartCurrentLimit(kTurningMotorCurrentLimit);

    m_turningSparkMax.burnFlash();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
