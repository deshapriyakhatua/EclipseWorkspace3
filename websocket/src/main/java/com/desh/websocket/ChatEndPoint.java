
package com.desh.websocket;

import java.util.Objects;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat", configurator= ChatConfigurator.class)
public final class ChatEndPoint{

}
