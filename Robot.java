package frc.robot;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.SerialPort.StopBits;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.PneumaticsBase;
//import edu.wpi.first.wpilibj.Compressor;

public class Robot extends TimedRobot {
  private double startTime;
  private WPI_VictorSPX m_motor0;
  private WPI_VictorSPX m_motor1;
  private WPI_VictorSPX m_motor2;
  private WPI_VictorSPX m_motor3;
  private MotorControllerGroup m_right;
  private MotorControllerGroup m_left;
  private DifferentialDrive c1;
  private CANSparkMax intake;

  private DoubleSolenoid m_doubleSolenoid;
  private Joystick m_stick;
  private double w;
  DigitalInput Downlimit = new DigitalInput(2);
  DigitalInput Uplimit = new DigitalInput(3);
  double ArmPower;
  // private Object pcmCompressor;

  @Override
  public void robotInit() {
    m_motor0 = new WPI_VictorSPX(1);
    m_motor1 = new WPI_VictorSPX(2);
    m_motor2 = new WPI_VictorSPX(3);
    m_motor3 = new WPI_VictorSPX(4);
    m_right = new MotorControllerGroup(m_motor0, m_motor1);
    m_left = new MotorControllerGroup(m_motor2, m_motor3);
    c1 = new DifferentialDrive(m_right, m_left);
    intake = new CANSparkMax(1, MotorType.kBrushless);
    // compressor = new Compressor(PneumaticsModuleType.CTREPCM);
    m_doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 7, 5);
    m_stick = new Joystick(0);

    m_right.setInverted(true);
    intake.setInverted(false);
    intake.setIdleMode(IdleMode.kBrake);
    intake.setSmartCurrentLimit(20);

  }

  @Override
  public void teleopPeriodic() {
    intake.set(m_stick.getRawButton(6) ? 0.6 : (m_stick.getRawButton(5) ? -0.6 : 0));

    // if (m_stick.getRawButton(6)) {
    // intake.set(0.6);
    // }
    // if (m_stick.getRawButton(5)) {
    // intake.set(-0.6);
    // }

    // m_doubleSolenoid.set(m_stick.getRawButton(2) ? kForward :
    // (m_stick.getRawButton(3) ? kReverse : ));
    if (m_stick.getRawButton(4)) {
      m_doubleSolenoid.set(kForward);
    } else if (m_stick.getRawButton(1)) {
      m_doubleSolenoid.set(kReverse);
    }

    w = m_stick.getRawButton(9) ? 0.5 : 1;

    c1.curvatureDrive(-m_stick.getRawAxis(1) * w, m_stick.getRawAxis(4) * 0.4, true);

    // c1.curvatureDrive((m_stick.getRawAxis(3)*0.8-m_stick.getRawAxis(2)*0.8>=0)
    // ?m_stick.getRawAxis(0)*0.3:m_stick.getRawAxis(0)*-0.3
    // ,m_stick.getRawAxis(3)*0.8-m_stick.getRawAxis(2)*0.8,true);

    // c1.arcadeDrive(m_stick.getRawAxis(1), m_stick.getRawAxis(4));
  }

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();
    if (time - startTime <= 2) {
      m_left.set(0.2);

      m_right.set(0.2);
    } else {
      if (time - startTime <= 6) {

        intake.set(0.5);
      } else {
        m_left.set(0);

        m_right.set(0);

      }

    }

  }
}
