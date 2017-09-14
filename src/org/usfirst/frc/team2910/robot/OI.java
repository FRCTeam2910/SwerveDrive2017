package org.usfirst.frc.team2910.robot;

import org.usfirst.frc.team2910.robot.input.IGamepad;
import org.usfirst.frc.team2910.robot.input.XboxGamepad;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private IGamepad mController = new XboxGamepad(0);

	public IGamepad getController() {
		return mController;
	}
}
