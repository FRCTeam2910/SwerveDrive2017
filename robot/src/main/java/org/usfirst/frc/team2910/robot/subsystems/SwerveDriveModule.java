package org.usfirst.frc.team2910.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2910.robot.commands.SwerveModuleCommand;
import org.usfirst.frc.team2910.robot.util.MotorStallException;

public class SwerveDriveModule extends Subsystem {
	private static final long STALL_TIMEOUT = 2000;

	private long mStallTimeBegin = Long.MAX_VALUE;

	private double mLastError = 0, mLastTargetAngle = 0;

	private final int mModuleNumber;

	private final double mZeroOffset;

	private final CANTalon mAngleMotor;
	private final CANTalon mDriveMotor;

	public SwerveDriveModule(int moduleNumber, CANTalon angleMotor, CANTalon driveMotor, double zeroOffset) {
		mModuleNumber = moduleNumber;

		mAngleMotor = angleMotor;
		mDriveMotor = driveMotor;

		mZeroOffset = zeroOffset;

		angleMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		angleMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);
		angleMotor.reverseSensor(true);
		angleMotor.setPID(20, 0, 200); // P: 20, I: 0, D: 200
		angleMotor.set(0);
		angleMotor.enableControl();

		driveMotor.enableBrakeMode(true);

		// Set amperage limits
		angleMotor.setCurrentLimit(50);
		angleMotor.EnableCurrentLimit(true);

		driveMotor.setCurrentLimit(50);
		driveMotor.EnableCurrentLimit(true);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SwerveModuleCommand(this));
	}

	public CANTalon getAngleMotor() {
		return mAngleMotor;
	}

	public CANTalon getDriveMotor() {
		return mDriveMotor;
	}

	public double getTargetAngle() {
		return mLastTargetAngle;
	}

	public void robotDisabledInit() {
		mStallTimeBegin = Long.MAX_VALUE;
	}

	public void setTargetAngle(double targetAngle) {
		mLastTargetAngle = targetAngle;

		targetAngle %= 360;
		targetAngle += mZeroOffset;

		double currentAngle = mAngleMotor.getPosition() * (360.0 / 1024.0);
		double currentAngleMod = currentAngle % 360;
		if (currentAngleMod < 0) currentAngleMod += 360;

		double delta = currentAngleMod - targetAngle;

		if (delta > 180) {
			targetAngle += 360;
		} else if (delta < -180) {
			targetAngle -= 360;
		}

		delta = currentAngleMod - targetAngle;
		if (delta > 90 || delta < -90) {
			if (delta > 90)
				targetAngle += 180;
			else if (delta < -90)
				targetAngle -= 180;
			mDriveMotor.setInverted(false);
		} else {
			mDriveMotor.setInverted(true);
		}

		targetAngle += currentAngle - currentAngleMod;

		double currentError = mAngleMotor.getError();
		if (Math.abs(currentError - mLastError) < 7.5 &&
				Math.abs(currentAngle - targetAngle) > 5) {
			if (mStallTimeBegin == Long.MAX_VALUE) mStallTimeBegin = System.currentTimeMillis();
			if (System.currentTimeMillis() - mStallTimeBegin > STALL_TIMEOUT) {
				throw new MotorStallException(String.format("Angle motor on swerve module '%d' has stalled.",
						mModuleNumber));
			}
		} else {
			mStallTimeBegin = Long.MAX_VALUE;
		}
		mLastError = currentError;


		targetAngle *= 1024.0 / 360.0;
		mAngleMotor.setSetpoint(targetAngle);
	}

	public void setTargetSpeed(double speed) {
		mDriveMotor.setSetpoint(speed);
	}
}
