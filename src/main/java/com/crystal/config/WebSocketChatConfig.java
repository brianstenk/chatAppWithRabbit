package com.crystal.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/*WebSocket Configuration class.
@Configuration tells that it is a Spring configuration class. @EnableWebSocketMessageBroker enables WebSocket
message handling, backed by a message broker. Here we are using STOMP as a mesage broker.
The method configureMessageBroker() enables a rabbitmq message broker to carry the messages
back to the client on destinations prefixed with "/topic" and "/queue".
 Also here we have configured that all messages with "/app" prefix will
  be routed to @MessageMapping-annotated methods in controller class.
 For example "/app/chat.sendMessage" is the endpoint that the WebSocketController.sendMessage() method is mapped to handle
       .*/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketChatConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocketApp").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableStompBrokerRelay("/topic").setRelayHost("localhost").setRelayPort(61613).setClientLogin("guest")
                .setClientPasscode("guest");

    }
}
