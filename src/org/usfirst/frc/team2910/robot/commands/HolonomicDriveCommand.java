package org.usfirst.frc.team2910.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2910.robot.Robot;
import org.usfirst.frc.team2910.robot.subsystems.HolonomicDrivetrain;

public class HolonomicDriveCommand extends Command {
	private final HolonomicDrivetrain mDrivetrain;

	public HolonomicDriveCommand(HolonomicDrivetrain drivetrain) {
		mDrivetrain = drivetrain;

		requires(drivetrain);
	}

	@Override
	protected void execute() {
		double forward = -Robot.getOI().getController().getLeftYValue();
		double strafe = Robot.getOI().getController().getLeftXValue();
		double rotation = Robot.getOI().getController().getRightXValue();

		SmartDashboard.putNumber("Forward", forward);
		SmartDashboard.putNumber("Strafe", strafe);
		SmartDashboard.putNumber("Rotation", rotation);
		
		mDrivetrain.holonomicDrive(forward, strafe, rotation);
	}

	@Override
	protected void end() {
		mDrivetrain.stopDriveMotors();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
