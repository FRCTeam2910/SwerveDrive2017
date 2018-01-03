package org.usfirst.frc.team2910.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2910.robot.subsystems.HolonomicDrivetrain;

public class ToggleFieldOrientedCommand extends Command {

	private final HolonomicDrivetrain mDrivetrain;

	public ToggleFieldOrientedCommand(HolonomicDrivetrain drivetrain) {
		mDrivetrain = drivetrain;
	}

	@Override
	protected void execute() {
		mDrivetrain.setFieldOriented(!mDrivetrain.isFieldOriented());
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
