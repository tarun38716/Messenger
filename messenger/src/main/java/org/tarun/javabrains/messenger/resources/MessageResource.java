package org.tarun.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.tarun.javabrains.messenger.modal.Message;
import org.tarun.javabrains.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
//	@GET//Mapping HTTP methods to java methods
//	@Produces(MediaType.TEXT_PLAIN)//Indicates the response type
//	public String getMessages(){
//		return "All messages";
//	}
//	
//	@POST
//	public String gedtMessages(){
//		return "My messages";
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getAllMessages(){
		return messageService.getAllMessages();
	}
	
	@Path("/test") // Jersey looks for subpath - it will be /messages/test  this test can be made variable
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "test";
	}
	
	@Path("/{messageId}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Message getMessage(@PathParam("messageId") Long messageId){//Pathparam to tell jersey that there is a parameter in the path
		//Long in the parameter converts the String to long itself
		// We can have n numbers of Path params
		return messageService.getMessage(messageId);
	}
	
	
	
	
	
}
