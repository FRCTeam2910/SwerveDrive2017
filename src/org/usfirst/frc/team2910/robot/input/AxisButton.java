package org.usfirst.frc.team2910.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * A button that becomes depressed when an axis reaches a certain value.
 *
 * @author Jacob Bublitz
 * @since 1.0
 */
public class AxisButton extends Button {

	private final Joystick mJoystick;
	private final int mAxis;
	private final double mGive;

	/**
	 * @param joystick The joystick the button is on.
	 * @param axis What axis the button is on.
	 * @param give What value the axis value needs to be greater than for the button to be depressed.
	 */
	public AxisButton(Joystick joystick, int axis, double give) {
		mJoystick = joystick;
		mAxis = axis;
		mGive = give;
	}

	@Override
	public boolean get() {
		return mJoystick.getRawAxis(mAxis) >= mGive;
	}
}
