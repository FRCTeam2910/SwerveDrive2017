package org.usfirst.frc.team2910.robot.subsystems;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import org.usfirst.frc.team2910.robot.input.XboxGamepad;

public class SwerveDriveSubsystem extends HolonomicDrivetrain {
	private static final double WHEELBASE = 12.5;
	private static final double TRACKWIDTH = 13.5;
	private static final double RATIO = Math.sqrt(Math.pow(WHEELBASE, 2) + Math.pow(TRACKWIDTH, 2));

	/*
	 * 0 is Front Right
	 * 1 is Front Left
	 * 2 is Back Left
	 * 3 is Back Right
	 */
	private SwerveDriveModule[] mSwerveModules = new SwerveDriveModule[] {
		new SwerveDriveModule(0, new CANTalon(6), new CANTalon(5), 75.5),
		new SwerveDriveModule(1, new CANTalon(3), new CANTalon(4), 13.7),
		new SwerveDriveModule(2, new CANTalon(2), new CANTalon(1), 254),
		new SwerveDriveModule(3, new CANTalon(7), new CANTalon(8), 157)
	};

	private AHRS mNavX = new AHRS(SPI.Port.kMXP, (byte) 200);

	public SwerveDriveSubsystem() {
		zeroGyro();

		mSwerveModules[0].getDriveMotor().setInverted(true);
		mSwerveModules[1].getDriveMotor().setInverted(true);
		mSwerveModules[2].getDriveMotor().setInverted(true);
		mSwerveModules[3].getDriveMotor().setInverted(true);

		mSwerveModules[0].getAngleMotor().setInverted(true);
		mSwerveModules[3].getAngleMotor().setInverted(true);

		for (SwerveDriveModule module : mSwerveModules) {
			module.setTargetAngle(0);
		}
	}

	public AHRS getNavX() {
		return mNavX;
	}

	public double getGyroAngle() {
		return (mNavX.getAngle() - getAdjustmentAngle());
	}

	public double getGyroRate() {
		return mNavX.getRate();
	}

	public double getRawGyroAngle() {
		return mNavX.getAngle();
	}

	public SwerveDriveModule getSwerveModule(int i) {
		return mSwerveModules[i];
	}

	@Override
	public void holonomicDrive(double forward, double strafe, double rotation) {
		forward *= getSpeedMultiplier();
		strafe *= getSpeedMultiplier();
		if (isFieldOriented()) {
			double angleRad = Math.toRadians(getGyroAngle());
			double temp = forward * Math.cos(angleRad) +
					strafe * Math.sin(angleRad);
			strafe = -forward * Math.sin(angleRad) + strafe * Math.cos(angleRad);
			forward = temp;
		}

		double a = strafe - rotation * (WHEELBASE / TRACKWIDTH);
		double b = strafe + rotation * (WHEELBASE / TRACKWIDTH);
		double c = forward - rotation * (TRACKWIDTH / WHEELBASE);
		double d = forward + rotation * (TRACKWIDTH / WHEELBASE);

		double[] angles = new double[]{
				Math.atan2(b, c) * 180 / Math.PI,
				Math.atan2(b, d) * 180 / Math.PI,
				Math.atan2(a, d) * 180 / Math.PI,
				Math.atan2(a, c) * 180 / Math.PI
		};

		double[] speeds = new double[]{
				Math.sqrt(b * b + c * c),
				Math.sqrt(b * b + d * d),
				Math.sqrt(a * a + d * d),
				Math.sqrt(a * a + c * c)
		};

		double max = speeds[0];

		for (double speed : speeds) {
			if (speed > max) {
				max = speed;
			}
		}

		for (int i = 0; i < 4; i++) {
			if (Math.abs(forward) > 0.05 ||
			    Math.abs(strafe) > 0.05 ||
			    Math.abs(rotation) > 0.05) {
				mSwerveModules[i].setTargetAngle(angles[i] + 180);
			} else {
				mSwerveModules[i].setTargetAngle(mSwerveModules[i].getTargetAngle());
			}
			mSwerveModules[i].setTargetSpeed(speeds[i]);
		}
	}

	@Override
	public void stopDriveMotors() {
		for (SwerveDriveModule module : mSwerveModules) {
			module.setTargetSpeed(0);
		}
	}
}
