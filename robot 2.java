package frc.robot;


import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.SerialPort.StopBits;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.PneumaticsModuleType;
//import edu.wpi.first.wpilibj.PneumaticsBase;
//import edu.wpi.first.wpilibj.Compressor;




public class Robot extends TimedRobot {


private CANSparkMax frontLeft; //左前
private CANSparkMax rearLeft;  //左後
private CANSparkMax frontRight;//右前
private CANSparkMax rearRight; //右後


private CANSparkMax angleFL; //角度左前
private CANSparkMax angleRL; //角度左後
private CANSparkMax angleFR; //角度右前
private CANSparkMax angleRR; //角度右後


private Joystick m_stick;//搖桿

private DifferentialDrive differentialDrive;//差速器




 @Override
 public void robotInit() {


   m_stick = new Joystick(0);


   frontLeft = new CANSparkMax(1,MotorType.kBrushed);
   rearLeft = new CANSparkMax(2,MotorType.kBrushed);
   frontRight = new CANSparkMax(3,MotorType.kBrushed);
   rearRight = new CANSparkMax(4,MotorType.kBrushed);


   angleFL = new CANSparkMax(5,MotorType.kBrushed);
   angleRL = new CANSparkMax(6,MotorType.kBrushed);
   angleFR = new CANSparkMax(7,MotorType.kBrushed);
   angleRR = new CANSparkMax(8,MotorType.kBrushed);

   double angle = 1.0; // 替換

   differentialDrive = new DifferentialDrive(new MotorControllerGroup(frontLeft, rearLeft),
   new MotorControllerGroup(frontRight, rearRight));


 }
 @Override
 public void teleopInit() {
 }


 @Override
 public void teleopPeriodic() {
   // 左搖桿輸入
   if (m_stick.getRawAxis(0) < 0.0) {
      double leftX = m_stick.getRawAxis(0);

      // 左搖桿 X 軸控制角度馬達
      double angleMotorSpeed = leftX ;
      angleFL.set(angleMotorSpeed);
      angleRL.set(angleMotorSpeed);
      angleFR.set(angleMotorSpeed);
      angleRR.set(angleMotorSpeed);


   }else if (m_stick.getRawAxis(0) > 0.0) {
    
   }
  
   // 右 上下搖桿輸入
   if (m_stick.getRawAxis(1) <= 0.0) {
      
   }else if (m_stick.getRawAxis(1) >= 0.0) {
    
     }
   // 右 左右搖桿輸入
   if (m_stick.getRawAxis(1) <= 0.0) {
      
   }else if (m_stick.getRawAxis(1) >= 0.0) {
    
  
  
   }
 }
}
