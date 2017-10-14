package org.usfirst.frc.team2910.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2910.robot.commands.HolonomicDriveCommand;

public abstract class HolonomicDrivetrain extends Subsystem {

	public abstract void holonomicDrive(double forward, double strafe, double rotation);

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new HolonomicDriveCommand(this));
	}

	public abstract boolean isFieldOriented();

	public abstract void setFieldOriented(boolean fieldOrientedDrive);

	public abstract void stopDriveMotors();
}
