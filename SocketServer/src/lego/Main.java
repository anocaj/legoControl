package lego;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) throws IOException {
		
		ServerSocket sersock = new ServerSocket(5000); 
	      
	    LCD.drawString("server is ready", 0, 4);//  message to know the server is running
	     
	    Socket sock = sersock.accept();               
	                                                                                         
	    InputStream istream = sock.getInputStream();  
	    
	    DataInputStream dstream = new DataInputStream(istream);//DataInputStream austauschen durch Scanner funktion
	    String message2 = dstream.readLine();
	    
	    
	    //Scanner s = new Scanner(iStream);
	    //String message2 = s.nextLine();
	    
	    LCD.drawString(message2, 0, 5);
	    Delay.msDelay(5000);
	    dstream .close(); //DataInputStream austauschen durch Scanner funktion
	    istream.close(); 
	    sock.close(); 
	    sersock.close();
		
		
	}

}
