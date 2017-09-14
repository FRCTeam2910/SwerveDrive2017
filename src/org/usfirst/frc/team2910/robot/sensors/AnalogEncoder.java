package org.usfirst.frc.team2910.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class AnalogEncoder extends AnalogInput implements PIDSource {
	private double maxVoltage = 4.7;

	public AnalogEncoder(int channel) {
		super(channel);
	}

	public double getAngle() {
		return getVoltage() / maxVoltage * 360;
	}

	public void setMaxVoltage(double maxVoltage) {
		this.maxVoltage = maxVoltage;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		if (pidSource != PIDSourceType.kDisplacement)
			throw new UnsupportedOperationException("Only the displacement source type is supported.");
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return getAngle() / 360;
	}
}