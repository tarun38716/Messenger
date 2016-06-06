package org.tarun.javabrains.messenger.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.tarun.javabrains.messenger.database.DatabaseClass;
import org.tarun.javabrains.messenger.modal.Message;

public class MessageService {
	
	//We mocked DAO layer
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
//	public List<Message> getAllMessages(){
//		
//		Message m1 = new Message(1L, "First",  "Tarun");
//		Message m2 = new Message(2L, "Second",  "Suar");
//		List<Message> list = new ArrayList<Message>();
//		list.add(m1);
//		list.add(m2);
//		return list;
//		
//	}
	
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello World", "Tarun"));
		messages.put(2L, new Message(2, "Hello Jersey", "Suar"));
	}
	
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values()); 
	}
	
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	

}
