package org.usfirst.frc.team2910.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2910.robot.Robot;
import org.usfirst.frc.team2910.robot.subsystems.SwerveDriveModule;

public class SwerveModuleCommand extends Command {

	private SwerveDriveModule mDriveModule;

	public SwerveModuleCommand(SwerveDriveModule driveModule) {
		this.mDriveModule = driveModule;

		requires(driveModule);
	}

	@Override
	protected void execute() {
		SmartDashboard.putData("Swerve Module Angle PID", mDriveModule.getAnglePIDController());
		SmartDashboard.putNumber("Swerve Module Angle", mDriveModule.getAngleEncoder().getAngle());
		SmartDashboard.putNumber("Swerve Module Input", mDriveModule.getAngleEncoder().pidGet());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
