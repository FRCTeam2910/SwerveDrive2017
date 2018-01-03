package org.usfirst.frc.team2910.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * A button for different directions on a directional-pad.
 *
 * @author Jacob Bublitz
 * @since 1.0
 */
public class DPadButton extends Button {

	public enum Direction {
		UP(0),
		UPRIGHT(45),
		RIGHT(90),
		DOWNRIGHT(135),
		DOWN(180),
		DOWNLEFT(225),
		LEFT(270),
		UPLEFT(315),
		CENTER(-1);

		private final int mAngle;

		Direction(int angle) {
			mAngle = angle;
		}

		public int getAngle() {
			return mAngle;
		}
	}

	private Joystick mJoystick;
	private Direction mDirection;
	private final int mPov;

	public DPadButton(Joystick joystick, Direction direction, int pov) {
		mJoystick = joystick;
		mDirection = direction;
		mPov = pov;
	}

	public DPadButton(Joystick joystick, Direction direction) {
		this(joystick, direction, 0);
	}

	@Override
	public boolean get() {
		return mJoystick.getPOV(mPov) == mDirection.getAngle();
	}
}
