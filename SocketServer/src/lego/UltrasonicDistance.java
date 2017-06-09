package lego;

import java.io.IOException;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.utility.Delay;


public class UltrasonicDistance {
	private static final float MAX_DISTANCE = 0.4f;
	private static final int DETECTOR_DELAY = 20;
	Ultrasonic ultrasonic;
	float distance;
	Wheel wheel1 = WheeledChassis.modelWheel(Motor.A, 55).offset(-65);
	Wheel wheel2 = WheeledChassis.modelWheel(Motor.D, 55).offset(65);
	Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
	MovePilot pilot = new MovePilot(chassis);

	public static void main(String[] args) throws IOException {
		new UltrasonicDistance();
	}

	public UltrasonicDistance() {

		EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S1);
		RangeFeatureDetector detector = new RangeFeatureDetector(new RangeFinderAdapter(us.getDistanceMode()), MAX_DISTANCE, DETECTOR_DELAY);
		detector.enableDetection(true);
		
        pilot.forward();
        
        
        
        detector.addListener(new FeatureListener() {
        	@Override
        	public void featureDetected(Feature feature, FeatureDetector detector) {
                detector.enableDetection(false);
                pilot.travel(-30);
                pilot.rotate(30);
                detector.enableDetection(true);
                pilot.forward();
                
            }

			      
        });
        
        
        if (Button.ESCAPE.isDown()) {
			pilot.stop();
			us.close();
			System.exit(0);

		}
        
        
        
//		ultrasonic = new Ultrasonic(us.getDistanceMode());
//		while (true) {
//			
//
//			distance = ultrasonic.distance();
//			if (distance < 0.3) {
//				pilot.backward();
//
//			} else if (distance > 0.4) {
//				pilot.forward();
//			} else {
//				pilot.stop();
//			}

			
		}

	}

