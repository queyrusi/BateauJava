package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import server.patterns.tcp.IContext;
import server.patterns.tcp.IProtocole;
import server.requestHandlers.MonitoredStateRequestHandler;
import server.requestHandlers.UserRequestHandler;

public class ConnectionProtocol implements IProtocole {

	public ConnectionProtocol() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(IContext aContext, InputStream anInputStream, OutputStream anOutputStream) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		BufferedReader socketIn = new BufferedReader(new InputStreamReader(anInputStream));
		BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(anOutputStream));
		try {
			String connectionCode = socketIn.readLine();
			switch (connectionCode) {
			case "@Ship" :
				MonitoredStateRequestHandler MSt = new MonitoredStateRequestHandler(aContext, socketIn, socketOut);
				MSt.start();
				break;
			case "@User" :
				UserRequestHandler URt = new UserRequestHandler(aContext, socketIn, socketOut);
				URt.start();
				break;
			default :
				throw new IllegalArgumentException();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read from client socket");
			e.printStackTrace();
		}

	}

}
