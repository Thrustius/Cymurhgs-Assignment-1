/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Solenoid;
import java.sql.Driver;
import edu.wpi.first.wpilibj.SolenoidControl;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final Joystick m_stick1 = new Joystick(0);
  private final Joystick m_stick2 = new Joystick(1);
  private final Timer m_timer = new Timer();
  private final JoystickButton oth = new JoystickButton(2);

  private final SpeedControllerGroup m_leftMotors =
  new SpeedControllerGroup(new PWMVictorSPX(DriveConstants.kLeftMotor1Port),
    new PWMVictorSPX(DriveConstants.kLeftMotor2Port));

    private final SpeedControllerGroup m_rightMotors =
    new SpeedControllerGroup(new PWMVictorSPX(DriveConstants.kRightMotor1Port),
       new PWMVictorSPX(DriveConstants.kRightMotor2Port));

    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

      
    private  Spark spark = new Spark(0);
    private final Solenoid valve = new Solenoid(2);
  public class OI {
    
    Joystick leftJoy = new Joystick(0);
    Button button1 = new JoystickButton(leftJoy, 1);
    Button button2 = new JoystickButton(leftJoy, 2);
  

  }

  public OI() {
    button1.whileHeld(new motorhiz());
    button2.whileHeld(new SolenoidControl());
  }


   @Override
     public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        if (m_timer.get() < 4){
        m_robotDrive.tankDrive(1, 1);
        }
      // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  
   
  @Override
  public void teleopInit() {
  }

  
  @Override
  public void teleopPeriodic() {
    m_robotDrive.tankDrive(m_stick1.getY(), m_stick2.getY());
     OI(); {
        button1.whileHeld(spark.set(1));
        
        button2.whileHeld(valve.set(true));
      


    }



  }

  /**
   * This function is called once when the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  /**
   * This function is called periodically when disabled.
   */
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
