package org.usfirst.frc.team2910.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2910.robot.commands.SwerveModuleCommand;

public class SwerveDriveModule extends Subsystem {

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

	public void setTargetAngle(double targetAngle) {
		targetAngle += mZeroOffset;

		targetAngle %= 360;

		double currentAngle = mAngleMotor.getPosition() * (360.0 / 1024.0);
		double currentAngleMod = currentAngle % 360;
		if (currentAngleMod < 0) currentAngleMod += 360;


		SmartDashboard.putNumber("Current Angle " + mModuleNumber, currentAngle);
		SmartDashboard.putNumber("Target Angle " + mModuleNumber, targetAngle);

		double delta = currentAngleMod - targetAngle;

		SmartDashboard.putNumber("Delta " + mModuleNumber, delta);
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

		SmartDashboard.putNumber("Output Angle " + mModuleNumber, targetAngle);

		targetAngle = targetAngle * (1024.0 / 360.0);

		mAngleMotor.setSetpoint(targetAngle);
	}

	public void setTargetSpeed(double speed) {
		mDriveMotor.setSetpoint(speed);
		SmartDashboard.putNumber("Drivetrain Speed", speed);
	}
}
