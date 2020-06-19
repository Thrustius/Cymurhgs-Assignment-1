/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


// Java EE yada bilmedigim bir hatadan kaynakli olarak errorlarin cogunlugunu goremiyorum.Hatalarim ile ilgili tam feedback almak isterim.Kod maymun elinden çıkmış gibiyse özür
package frc.robot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jdk.internal.editor.external.ExternalEditor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Solenoid;
//paketler
import edu.wpi.first.wpilibj.SolenoidBase;

  public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;

  public Solenoid solenoid = new Solenoid(0);
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final Joystick m_stick = new Joystick(0);
  private final Timer m_timer = new Timer();
  public JoystickButton jbh = new JoystickButton(m_stick,10);
  public JoystickButton jbh2 = new JoystickButton(m_stick,11);
 
  public motor motor = new motor();
  public class motor {
    Spark m_frontLeft = new Spark(1);
    Spark m_rearLeft = new Spark(2);
    Spark m_frontRight = new Spark(3);
    Spark m_rearRight = new Spark(4);
    SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
    DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
    Spark external = new Spark(5);
  }




  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }
  
 
  @Override
  public void robotPeriodic() {
  }

 
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    
    System.out.println("Auto selected: " + m_autoSelected);
    m_timer.reset();
    m_timer.start();
  }

  
  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 4.0){
      motor.m_drive.tankDrive(1.0, 1.0);
      
    }// stopmotoru denemek icin yapiyorum.hizini 0.0 , 0.0 yapmanin da dogru oldunu biliyom.
    else{
    motor.m_drive.stopMotor();
    }
    
  }

  
  @Override
  public void teleopInit() {
    

  // 
   // jbh.whileHeld(solenoid.set(true));
   // jbh.whenReleased(solenoid.get());
    //jbh2.whileHeld(motor.external.setSpeed(1.0));
    //

    
  }


// buralar muhtemelen hatalı bool-command yapısından dolayı ama göremediğimden debug zor oluyor.feedback pls
  @Override
  public void teleopPeriodic() {
   jbh.whileHeld(motor.external.setSpeed(1.0));
   jbh2.whileHeld(solenoid.set(1.0));
   motor.m_drive.tankDrive(m_stick.getY(1),m_stick.getY(3));

//double deger = motor.external.get(); 
//jbh.whileHeld
 // if (deger == 0);{
//motor.external.set(1.0);}
 // if (deger == 1.0);{
 // motor.external.set(0.0);}

 // garip denemeler onemseme
  }
     
  
  @Override
  public void disabledInit() {
  }

 
  @Override
  public void disabledPeriodic() {
  }

 
  @Override
  public void testInit() {
  }

  
  
  @Override
  public void testPeriodic() {
  }
}
