package lego;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class LegoVehicle {
private int currentSpeed;
private EV3UltrasonicSensor us;

public LegoVehicle(int currentSpeed) {
	super();
	this.currentSpeed = currentSpeed;
	this.us = new EV3UltrasonicSensor(SensorPort.S1);
}


}
