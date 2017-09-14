package org.usfirst.frc.team2910.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2910.robot.sensors.AnalogEncoder;

public class SwerveDriveSubsystem extends Subsystem {

	private SwerveDriveModule mSwerveModule = new SwerveDriveModule(
			new AnalogEncoder(0), new Talon(0),
			new Talon(1));

	public SwerveDriveSubsystem() {
		mSwerveModule.getAngleMotor().setInverted(true);
	}

	@Override
	protected void initDefaultCommand() {

	}
}
