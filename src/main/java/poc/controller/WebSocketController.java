package poc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import poc.dto.RequestDto;
import poc.dto.ResponseDto;
import poc.service.RandomTextSelector;

/**
 */
@Controller
public class WebSocketController {

    private final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    private final SimpMessageSendingOperations messagingTemplate;
    private final RandomTextSelector randomTextSelector;

    public WebSocketController(final SimpMessageSendingOperations messagingTemplate,
                               final RandomTextSelector randomTextSelector) {
        this.messagingTemplate = messagingTemplate;
        this.randomTextSelector = randomTextSelector;
    }

    @MessageMapping("/hello")
    public void message(RequestDto requestDto, SimpMessageHeaderAccessor headerAccessor){
        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        log.info("Unique identifier : " + sessionId);
        headerAccessor.setSessionId(sessionId);
        messagingTemplate.convertAndSend("/topic/generalMessage", new ResponseDto(randomTextSelector.getRandomText()),
                headerAccessor.getMessageHeaders());
    }
}
