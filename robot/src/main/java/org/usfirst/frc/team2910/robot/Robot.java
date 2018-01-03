
package org.usfirst.frc.team2910.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2910.robot.subsystems.SwerveDriveModule;
import org.usfirst.frc.team2910.robot.subsystems.SwerveDriveSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static final boolean DEBUG = true;

	private static OI mOI;
	private static SwerveDriveSubsystem swerveDriveSubsystem;

	public static OI getOI() {
		return mOI;
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		mOI = new OI(this);

		swerveDriveSubsystem = new SwerveDriveSubsystem();

		mOI.registerControls();
	}

	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("Adjusted Drivetrain Angle", swerveDriveSubsystem.getGyroAngle());
		SmartDashboard.putNumber("Raw Drivetrain Angle", swerveDriveSubsystem.getRawGyroAngle());
		SmartDashboard.putNumber("Drivetrain Rate", swerveDriveSubsystem.getGyroRate());
		SmartDashboard.putNumber("Gyro Update Rate", swerveDriveSubsystem.getNavX().getActualUpdateRate());


		for (int i = 0; i < 4; i++) {
			SmartDashboard.putNumber("Drive Current Draw " + i, swerveDriveSubsystem.getSwerveModule(i).getDriveMotor().getOutputCurrent());
			SmartDashboard.putNumber("Angle Current Draw " + i, swerveDriveSubsystem.getSwerveModule(i).getAngleMotor().getOutputCurrent());
		}
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		for (int i = 0; i < 4; i++) {
			swerveDriveSubsystem.getSwerveModule(i).robotDisabledInit();
		}
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public SwerveDriveSubsystem getDrivetrain() {
		return swerveDriveSubsystem;
	}
}
