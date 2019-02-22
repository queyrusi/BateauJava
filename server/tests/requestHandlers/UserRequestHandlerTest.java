package server.tests.requestHandlers;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import server.CentralContext;
import server.ConnectionProtocol;
import server.entities.User;

class UserRequestHandlerTest {

	public void EndofThread() {
		CentralContext context = new CentralContext();
		User user = new User("Ali", "here", "ali@yopmail.com", null, null);
		try {
			context.addUser("aliID",user);
			ConnectionProtocol protocol = new ConnectionProtocol();
			InputStream iStream = new InputStream() {
				String message = "@User\naliID\n@quit\n";
				int index = 0;
				@Override
				public int read() throws IOException {
					// TODO Auto-generated method stub
					int envoi = message.charAt(index);
					index = (index +1)%18;
					return envoi;				
				}
			};
			OutputStream oStream = new OutputStream() {
				@Override
				public void write(int arg0) throws IOException {
					// TODO Auto-generated method stub
					
				}
			};
			protocol.execute(context, iStream, oStream);
			try {
				TimeUnit.SECONDS.sleep(3);
				assertEquals(context.countRunningRequestHandler(),0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void UnknownUser() {
			CentralContext context = new CentralContext();
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
				assertEquals(context.countRunningRequestHandler(), 0);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void StartOfThread() {
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
					// TODO Auto-generated method stub
					int envoi = message.charAt(index);
					index = (index +1)%12;
					return envoi;				
				}
			};
			OutputStream oStream = new OutputStream() {
				@Override
				public void write(int arg0) throws IOException {
					// TODO Auto-generated method stub
					
				}
			};
			protocol.execute(context, iStream, oStream);
			try {
				TimeUnit.SECONDS.sleep(3);
				assertEquals(context.countRunningRequestHandler(),1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
