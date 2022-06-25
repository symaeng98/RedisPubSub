package com.example.pubsub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.pubsub.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisSubService implements MessageListener {
    public static List<String> messageList = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            ChatMessage chatMessage = mapper.readValue(message.getBody(), ChatMessage.class);
            messageList.add(message.toString());

            System.out.println("받은 메시지 = " + message.toString());
            System.out.println("chatMessage.getSender() = " + chatMessage.getSender());
            System.out.println("chatMessage.getContext() = " + chatMessage.getContext());
            System.out.println(messageList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}