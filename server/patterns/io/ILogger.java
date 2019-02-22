package server.patterns.io;

import java.io.OutputStream;

public interface ILogger {
	
	public void writeLog(String line, OutputStream oStream);

}
