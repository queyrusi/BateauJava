package server.entities;

import server.patterns.tcp.RequestHandler;

public abstract class Client {
	
	private RequestHandler requestHandler;
	
	public RequestHandler getRequestHandler() {
		return requestHandler;
	}

	public void setRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
}
