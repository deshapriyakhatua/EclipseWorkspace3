package com.desh.websocket;

import jakarta.websocket.server.*;
import jakarta.websocket.HandshakeResponse;

public class ChatConfigurator extends ServerEndpointConfig.Configurator{
	
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest req, HandshakeResponse resp) {
		
	}
}
