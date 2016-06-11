package org.tarun.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.tarun.javabrains.messenger.modal.Message;
import org.tarun.javabrains.messenger.resources.beans.MessageFilterBean;
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
	public List<Message> getAllMessages(@QueryParam("year") int year,
			 @QueryParam("start") int start,
			 @QueryParam("size") int size){//Query params does`nt change the URI mappings so we have to add the conditions in this method only
		//Jersey itself looks for query param in the URL
//		System.out.println(year);
		//If we dont pass the Queryparams in the URL . It initializes to 0 or null
		if (year > 0) {
			return messageService.getAllMessagesForYear(year);
		}
		if (start >= 0 && size > 0) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
		
		
	}
	
	//Using annotation BeanParam by getting all the params in the same object
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getAllMessages(@BeanParam MessageFilterBean filterBean){
		
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
		
		
	}*/
	
	@Path("/test") // Jersey looks for subpath - it will be /messages/test  this test can be made variable
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "test";
	}
	
	@Path("/{messageId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") Long messageId){//Pathparam to tell jersey that there is a parameter in the path
		//Long in the parameter converts the String to long itself by jersey
		// We can have n numbers of Path params
		//We can have regular expression in path param
		return messageService.getMessage(messageId);
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)//to tell jersey body of Post method will be Json
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message){//Jersey coverts the JSON to java object
		messageService.addMessage(message);
		return message;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)//Both these Consumes and Produces are common to all methods we can annotate to the class 
	@Path("/{messageId}")
	public Message updateMessage(Message message,@PathParam("messageId") Long messageId){
		message.setId(messageId);//we can send it via JSON from client but it is a best practice
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") Long messageId){
		messageService.removeMessage(messageId);
	}
	
	
	
	
	
	
}
