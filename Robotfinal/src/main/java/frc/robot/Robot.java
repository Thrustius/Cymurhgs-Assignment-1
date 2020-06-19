/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Solenoid;
//paketler


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
    Spark external = new Spark(0);
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
      
    }// stopmotoru denemek icin yapiyorum.
    else{
    motor.m_drive.stopMotor();
    }
    
  }

  
  @Override
  public void teleopInit() {
    

  
   // jbh.whileHeld(solenoid.set(true));
   // jbh.whenReleased(solenoid.get());
    //jbh.whileHeld(motor.external.setSpeed(1.0));
  }
  
   
  
  @Override
  public void teleopPeriodic() {
    motor.m_drive.tankDrive(m_stick.getRawAxis(1),m_stick.getRawAxis(3));

   if (jbh.get()){
   motor.external.set(1);
   }
   else{
   motor.external.set(0);
   }


   if (jbh2.get()){
   solenoid.set(true);
   }
   else {
   solenoid.set(false);}

   

  

  }
   

  //jbh.whileHeld(motor.external.set(0));
 // motor.m_drive.tankDrive(m_stick.getY(),m_stick.getY());
// double deger = motor.external.get(); 
 // if (deger == 0);{
  //motor.external.set(1.0);}
  //if (deger == 1.0);{
  //motor.external.set(1.0);}
  


  
     
  
  @Override
  public void disabledInit() {
  }

 
  @Override
  public void disabledPeriodic() {
  }

  /**
   * This function is called once when test mode is enabled.
   */
  @Override
  public void testInit() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
