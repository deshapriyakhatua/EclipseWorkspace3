package webSocket.chat;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.SendMessage;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat")
public class Chat {

	private static final String USERID_KEY = "userid";
	private static final String RECIPIENT_ID = "recipientid";
	private static final String GROUPID_KEY = "groupid";
	private static Map<String, Session> chatRooms = Collections.synchronizedMap(new LinkedHashMap<String, Session>());

	@OnOpen
	public void onOpen(Session session) throws Exception {

		Map<String, List<String>> parameter = session.getRequestParameterMap();
		
		List<String> list1 = parameter.get(USERID_KEY);
		List<String> list2 = parameter.get(RECIPIENT_ID);
		List<String> list3 = parameter.get(GROUPID_KEY);
		
		String userid = list1.get(0);
		String recipientid = list2.get(0);
		String groupid = list3.get(0);

		chatRooms.put(userid, session);

		session.getUserProperties().put(USERID_KEY, userid);
		session.getUserProperties().put(RECIPIENT_ID, recipientid);
		session.getUserProperties().put(GROUPID_KEY, groupid);

		System.out.println("User : " + userid + "joined on group : " + groupid + " with User : " + recipientid);
		
	}

	@OnMessage
	public void onMessage(Session session, String message) throws Exception {

		
		String userid = (String) session.getUserProperties().get(USERID_KEY);
		String recipientid = (String) session.getUserProperties().get(RECIPIENT_ID);
		
		SendMessage sendMessage = new SendMessage();
		sendMessage.send(userid, recipientid, message);
		System.out.println(message);
		Session recipient = chatRooms.get(recipientid);
		System.out.println(recipient);
		if(recipient != null) recipient.getBasicRemote().sendText(message);

	}

	@OnClose
	public void onClose(Session session) throws Exception {

		String userid = (String) session.getUserProperties().get(USERID_KEY);
		chatRooms.remove(userid);
		System.out.println("User : " + userid + "left chat");
	}

}
