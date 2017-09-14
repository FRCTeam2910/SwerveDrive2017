package org.usfirst.frc.team2910.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2910.robot.commands.SwerveModuleCommand;
import org.usfirst.frc.team2910.robot.sensors.AnalogEncoder;

public class SwerveDriveModule extends Subsystem {
	private static final double DEFAULT_P = 3, DEFAULT_I = 0.1, DEFAULT_D = 3;

	private AnalogEncoder mAngleEncoder;
	private SpeedController mAngleMotor;
	private SpeedController mDriveMotor;

	private PIDController mAnglePIDController;
	private PIDController mSpeedPIDController;

	public SwerveDriveModule(AnalogEncoder angleEncoder, SpeedController angleMotor, SpeedController driveMotor) {
		mAngleEncoder = angleEncoder;
		mAngleMotor = angleMotor;
		mDriveMotor = driveMotor;

		mAnglePIDController = new PIDController(DEFAULT_P, DEFAULT_I, DEFAULT_D, angleEncoder, angleMotor);
		mAnglePIDController.setContinuous(true);
		mAnglePIDController.setPercentTolerance(0.02);
		mAnglePIDController.setInputRange(0, 359.0 / 360.0);
		mAnglePIDController.setOutputRange(-1, 1);

		mAnglePIDController.enable();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SwerveModuleCommand(this));
	}

	public double getAngle() {
		return mAnglePIDController.getSetpoint() * 360;
	}

	public AnalogEncoder getAngleEncoder() {
		return mAngleEncoder;
	}

	public SpeedController getAngleMotor() {
		return mAngleMotor;
	}

	public PIDController getAnglePIDController() {
		return mAnglePIDController;
	}

	public void setAngle(double angle) {
		mAnglePIDController.setSetpoint((angle % 360) / 360);
	}

	public void setAngleRelative(double deltaAngle) {
		setAngle(getAngle() + deltaAngle);
	}
}
