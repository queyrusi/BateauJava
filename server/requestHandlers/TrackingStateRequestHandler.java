package server.requestHandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import server.CentralContext;
import server.entities.Ship;
import server.patterns.tcp.IContext;
import server.patterns.tcp.ShipStateRequestHandler;

public class TrackingStateRequestHandler extends ShipStateRequestHandler {

	private CentralContext centralContext;
	private BufferedReader socketInput;
	private BufferedWriter socketOutput;
	private Ship ship;

	public TrackingStateRequestHandler(Ship ship,IContext aContext, BufferedReader socketIn, BufferedWriter socketOut) {
		// TODO Auto-generated constructor stub
		this.centralContext = (CentralContext) aContext;
		this.socketInput = socketIn;
		this.socketOutput = socketOut;
		this.ship = ship;
	}
	
	@Override
	public void run() {
		try {
			centralContext.addRequestHandler(this);
			String line;
			while ((line = socketInput.readLine()).compareTo("@quit") != 0) {
			}
			centralContext.removeRequestHandler(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}
	}
}
