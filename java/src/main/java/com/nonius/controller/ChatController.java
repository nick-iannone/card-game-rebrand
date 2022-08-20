package com.nonius.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nonius.dao.ChatLogSqlDAO;
import com.nonius.websocket.model.ChatLog;
import com.nonius.websocket.model.ChatMessage;

@RestController
@CrossOrigin
public class ChatController {
	
	private ChatLogSqlDAO chatDAO;
	
	public ChatController(ChatLogSqlDAO chatDAO) {
		this.chatDAO = chatDAO;
	}
	
	@RequestMapping(path = "/chat/{username}", method = RequestMethod.GET)
	public ChatLog getMyChatLog(@PathVariable String username) {
		 return chatDAO.getMyChatLog(username);
	}
	
	@RequestMapping(path = "/chat/users", method = RequestMethod.GET)
	public List<String> getAllUsernames() {
		return chatDAO.getAllUsernames();
	}
	
	@ResponseStatus (HttpStatus.OK)
	@RequestMapping(path = "/chat/messages", method = RequestMethod.POST)
	public void markMessageAsRead(@RequestBody ChatMessage message) {
		chatDAO.markMessageAsRead(message);
	}
}
