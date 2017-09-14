package org.usfirst.frc.team2910.robot.input;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * <p>An interface for easily implementing gamepads as an input source.</p>
 *
 * @see XboxGamepad
 *
 * @author Jacob Bublitz
 *
 * @since 1.0
 */
public interface IGamepad {
	/**
	 * Get the value of the left trigger.
	 * @return The value of the left trigger in the range [0, 1]
	 *
	 * @since 1.0
	 */
	double getLeftTriggerValue();

	/**
	 * Get the X value of the left joystick.
	 * @return The X value of the left joystick in the range [-1, 1]
	 *
	 * @since 1.0
	 */
	double getLeftXValue();

	/**
	 * Get the Y value of the left joystick.
	 * @return The Y value of the left joystick in the range [-1, 1]
	 *
	 * @since 1.0
	 */
	double getLeftYValue();

	/**
	 * Get the value of the right trigger.
	 * @return The value of the right trigger in the range [0, 1]
	 *
	 * @since 1.0
	 */
	double getRightTriggerValue();

	/**
	 * Get the X value of the right joystick.
	 * @return The X value of the right joystick in the range [-1, 1]
	 *
	 * @since 1.0
	 */
	double getRightXValue();

	/**
	 * Get the Y value of the right joystick.
	 * @return The Y value of the right joystick in the range [-1, 1]
	 *
	 * @since 1.0
	 */
	double getRightYValue();

	/**
	 * Get the A button of the controller.
	 * @return The A button
	 *
	 * @since 1.0
	 */
	Button getAButton();

	/**
	 * Get the B button of the controller.
	 * @return The B button
	 *
	 * @since 1.0
	 */
	Button getBButton();

	/**
	 * Get the X button of the controller.
	 * @return The X button
	 *
	 * @since 1.0
	 */
	Button getXButton();

	/**
	 * Get the Y button of the controller.
	 * @return The Y button
	 *
	 * @since 1.0
	 */
	Button getYButton();

	/**
	 * Get the left bumper button of the controller.
	 * @return The left bumper button
	 *
	 * @since 1.0
	 */
	Button getLeftBumperButton();

	/**
	 * Get the right bumper button of the controller.
	 * @return The right bumper button
	 *
	 * @since 1.0
	 */
	Button getRightBumperButton();

	/**
	 * Get the back button of the controller.
	 * @return The back button
	 *
	 * @since 1.0
	 */
	Button getBackButton();

	/**
	 * Get the start button of the controller.
	 * @return The start button
	 *
	 * @since 1.0
	 */
	Button getStartButton();

	/**
	 * Get the left joystick button of the controller.
	 * @return The left joystick button
	 *
	 * @since 1.0
	 */
	Button getLeftJoystickButton();

	/**
	 * Get the right joystick button of the controller.
	 * @return The right joystick button
	 *
	 * @since 1.0
	 */
	Button getRightJoystickButton();

	/**
	 * Get the left trigger button of the controller.
	 * @return The left trigger button
	 *
	 * @since 1.0
	 */
	Button getLeftTriggerButton();

	/**
	 * Get the right trigger button of the controller.
	 * @return The right trigger button
	 *
	 * @since 1.0
	 */
	Button getRightTriggerButton();

	/**
	 * Get a D-Pad button of the controller.
	 * @param direction The direction of the D-Pad button
	 * @return The D-Pad button of the specified direction
	 *
	 * @since 1.0
	 */
	Button getDPadButton(DPadButton.Direction direction);
}
