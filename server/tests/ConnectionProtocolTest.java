package server.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import server.CentralContext;
import server.ConnectionProtocol;
import server.entities.Ship;
import server.entities.User;
import server.requestHandlers.UserRequestHandler;

class ConnectionProtocolTest {

	@Test
	public void WrongConnectionCode(){
		assertThrows(IllegalArgumentException.class,()->
		{
			CentralContext context = new CentralContext();
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@Wrong\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					int envoi = message.charAt(index);
					index = (index +1)%7;
					return envoi;				
				}
			};
			OutputStream oStream = new OutputStream() {
				@Override
				public void write(int arg0) throws IOException {
					
				}
			};
			protocol.execute(context, iStream, oStream);
			try {
				TimeUnit.SECONDS.sleep(3);
				assertEquals(context.countRunningRequestHandler(), 0);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
	@Test 
	public void LoggedShip() {
		CentralContext context = new CentralContext();
		Ship ship = new Ship("AZER", "", "", "");
		try {
			context.addShip("AZER",ship);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@Ship\nAZER\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					int envoi = message.charAt(index);
					index = (index +1)%11;
					return envoi;				
				}
			};
			OutputStream oStream = new OutputStream() {
				@Override
				public void write(int arg0) throws IOException {
					
				}
			};
			protocol.execute(context, iStream, oStream);
			try {
				TimeUnit.SECONDS.sleep(3);
				assertNotEquals(ship.getRequestHandler(), null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void LoggedUser() {
		CentralContext context = new CentralContext();
		User user = new User("Ali", "here", "ali@yopmail.com", null, null);
		try {
			context.addUser("aliID",user);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@User\naliID\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					int envoi = message.charAt(index);
					index = (index +1)%12;
					return envoi;				
				}
			};
			OutputStream oStream = new OutputStream() {
				@Override
				public void write(int arg0) throws IOException {
					
				}
			};
			protocol.execute(context, iStream, oStream);
			try {
				TimeUnit.SECONDS.sleep(3);
				assertNotEquals(user.getRequestHandler(), null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
