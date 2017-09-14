package org.usfirst.frc.team2910.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * <p>IGamepad implementation for XBox-like gamepads.</p>
 *
 * <p>Currently known to work with:
 * <ul>
 *      <li>Xbox 360 wired controller</li>
 *      <li>Logitech F310</li>
 * </ul>
 * </p>
 *
 * @author Jacob Bublitz
 * @since 1.0
 */

public final class XboxGamepad extends Joystick implements IGamepad {
	
	
	private final Button[] mButtons = {
			new JoystickButton(this, 1), // A Button
			new JoystickButton(this, 2), // B Button
			new JoystickButton(this, 3), // X Button
			new JoystickButton(this, 4), // Y Button
			new JoystickButton(this, 5), // Left Bumper
			new JoystickButton(this, 6), // Right Bumper
			new JoystickButton(this, 7), // Back Button
			new JoystickButton(this, 8), // Start Button
			new JoystickButton(this, 9), // Left Stick Button
			new JoystickButton(this, 10), // Right Stick Button
			new AxisButton(this, 2,0.75), // Left Trigger Button
			new AxisButton(this, 3,0.75), // Right Trigger Button
	};

	private final DPadButton[] mDPadButtons;

	/**
	 * @param port The port the controller is on
	 */
	public XboxGamepad(int port) {
		super(port);

		mDPadButtons = new DPadButton[DPadButton.Direction.values().length];

		for (DPadButton.Direction dir : DPadButton.Direction.values()) {
			mDPadButtons[dir.ordinal()] = new DPadButton(this, dir);
		}
	}

	public double getLeftTriggerValue() {
		return getRawAxis(2);
	}

	public double getLeftXValue() {
		return getRawAxis(0);
	}

	public double getLeftYValue() {
		return -getRawAxis(1);
	}

	public double getRightTriggerValue() {
		return getRawAxis(3);
	}

	public double getRightXValue() {
		return getRawAxis(4);
	}

	public double getRightYValue() {
		return -getRawAxis(5);
	}

	public Button getAButton() {
		return mButtons[0];
	}

	public Button getBButton() {
		return mButtons[1];
	}

	public Button getXButton() {
		return mButtons[2];
	}

	public Button getYButton() {
		return mButtons[3];
	}

	public Button getLeftBumperButton() {
		return mButtons[4];
	}

	public Button getRightBumperButton() {
		return mButtons[5];
	}

	public Button getBackButton() {
		return mButtons[6];
	}

	public Button getStartButton() {
		return mButtons[7];
	}

	public Button getLeftJoystickButton() {
		return mButtons[8];
	}

	public Button getRightJoystickButton() {
		return mButtons[9];
	}

	public Button getLeftTriggerButton() {
		return mButtons[10];
	}

	public Button getRightTriggerButton() {
		return mButtons[11];
	}

	public Button getDPadButton(DPadButton.Direction direction) {
		return mDPadButtons[direction.ordinal()];
	}
}
