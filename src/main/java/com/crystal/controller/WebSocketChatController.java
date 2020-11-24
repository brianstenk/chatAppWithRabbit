package com.crystal.controller;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.crystal.domain.WebSocketChatMessage;

/*Define the controller class. Previously we have configured the websocket such that all messages coming
 from the client with prefix "/app" will be routed to the appropriate message
 handling methods annotated with @MessageMapping.
For example, a message with destination /app/chat.newUser will be routed to the newUser() method,
and a message with destination /app/chat.sendMessage will be routed to the sendMessage() method.s
*/

@Controller
public class WebSocketChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/javainuse")
    public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage webSocketChatMessage) {
        return webSocketChatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    public WebSocketChatMessage newUser(@Payload WebSocketChatMessage webSocketChatMessage,
                                        SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
        return webSocketChatMessage;
    }

}
