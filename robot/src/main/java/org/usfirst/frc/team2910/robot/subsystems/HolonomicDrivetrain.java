package org.usfirst.frc.team2910.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2910.robot.commands.HolonomicDriveCommand;

public abstract class HolonomicDrivetrain extends Drivetrain {

	private double mAdjustmentAngle = 0;
	private boolean mFieldOriented = true;


	public double getAdjustmentAngle() {
		return mAdjustmentAngle;
	}

	public abstract double getGyroAngle();

	public abstract void holonomicDrive(double forward, double strafe, double rotation);

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new HolonomicDriveCommand(this));
	}

	public boolean isFieldOriented() {
		return mFieldOriented;
	}

	public void setAdjustmentAngle(double adjustmentAngle) {
		mAdjustmentAngle = adjustmentAngle;
	}

	public void setFieldOriented(boolean fieldOriented) {
		mFieldOriented = fieldOriented;
	}

	public abstract void stopDriveMotors();

	public void zeroGyro() {
		setAdjustmentAngle(getGyroAngle() + getAdjustmentAngle());
	}
}
