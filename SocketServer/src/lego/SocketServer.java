package lego;


import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.PrintStream; 
import java.net.ServerSocket; 
import java.net.Socket;

import lejos.hardware.lcd.LCD;
//import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
//import lejos.hardware.port.MotorPort;
//import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay; 

public class SocketServer { 
    private final ServerSocket server; 
//    RegulatedMotor mleft = new EV3LargeRegulatedMotor(MotorPort.A);
//    RegulatedMotor mright = new EV3LargeRegulatedMotor(MotorPort.D);
    Wheel wheel1 = WheeledChassis.modelWheel(Motor.A, 55).offset(-65);
	Wheel wheel2 = WheeledChassis.modelWheel(Motor.D, 55).offset(65);
	Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
	MovePilot pilot = new MovePilot(chassis);
    
    public SocketServer(int port) throws IOException { 
        server = new ServerSocket(port); 
        LCD.drawString("server is ready", 0, 4);//  message to know the server is running
    } 

    private void verbinde() { 
    	
        while (true) { 
            Socket socket = null; 
            try { 
                socket = server.accept(); 
                reinRaus(socket); 
            } 

            catch (IOException e) { 
                e.printStackTrace(); 
            } finally { 
                if (socket != null) 
                    try { 
                        socket.close(); 
                    } catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
            } 
        } 
    } 

    private void reinRaus(Socket socket) throws IOException { 
        BufferedReader rein = new BufferedReader(new InputStreamReader(socket 
                .getInputStream())); 
        PrintStream raus = new PrintStream(socket.getOutputStream()); 
        String s; 
        
        
        
        
//        mleft.setSpeed(100);
//        mright.setSpeed(100);
         
        while(rein.ready()) { 
        	
            s = rein.readLine(); 
            raus.println(s); 
            
            if (s.equals("F")) {
            	pilot.forward();
            	
            	
//            	mleft.setSpeed(100);
//            	mleft.forward();
//            	mleft.setSpeed(100);
//            	mright.forward();
            	
            	//mleft.rotate(360, true);
            	//mright.rotate(360, true);
			} else if (s.equals("R")){
				pilot.arcForward(-500);
				
//				mleft.setSpeed(100);
//				mleft.forward();
//				mright.setSpeed(80);
//				mright.forward();
			} else if (s.equals("L")){
				pilot.arcForward(500);
//				mleft.setSpeed(80);
//				mleft.backward();
//				mright.setSpeed(100);
//				mright.backward();
			} else if (s.equals("B")){
				pilot.backward();
//				mleft.setSpeed(100);
//				mleft.backward();
//				
//				mleft.setSpeed(100);
//				mright.backward();
			} else if (s.startsWith("GF")){
				pilot.forward();
				pilot.setLinearSpeed(10);
//				mleft.setSpeed(100);
//				mleft.backward();
//				
//				mleft.setSpeed(100);
//				mright.backward();
			}
			
            LCD.drawString(s, 0, 5);
            Delay.msDelay(2000);
        } 
    } 

    public static void main(String[] args) throws IOException { 
        SocketServer server = new SocketServer(3141); 
        server.verbinde(); 
    } 
} 
