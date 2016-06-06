package org.tarun.javabrains.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.tarun.javabrains.messenger.modal.Message;
import org.tarun.javabrains.messenger.modal.Profile;
public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<Long, Profile> profiles = new HashMap<>();

	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<Long, Profile> getProfiles() {
		return profiles;
	}


}
