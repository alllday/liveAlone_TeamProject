package com.multi.liveAlone.party.bbsParty;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	//채팅방 이름 설정
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}
	
	//채팅 내용을 보낼 주소(endPoint == url)
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//chat: 실시간 채팅용 
		registry.addEndpoint("/chat/{roomId}"); //자바 소켓 통신 가능 
		registry.addEndpoint("/chat/{roomId}").withSockJS(); //자바 스크립트 소켓 통신 
		
		}

}
