package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.service.FcmNotificationService;
import de.bytefish.fcmjava.http.client.IFcmClient;
import de.bytefish.fcmjava.model.enums.ErrorCodeEnum;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class FcmNotificationServiceImpl implements FcmNotificationService {
    private final RestTemplate restTemplate;

    private final IFcmClient fcmClient;

    private int id = 0;

    public FcmNotificationServiceImpl(IFcmClient fcmClient) {
        this.restTemplate = new RestTemplate();
        this.fcmClient = fcmClient;
    }


    public void sendPushMessage(String str) {
        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofMinutes(2)).build();

        NotificationPayload payload = NotificationPayload.builder()
                .setBody("A new Chuck Norris joke has arrived")
                .setTitle("Chuck Norris Joke")
                .setTag("chuck").build();

        Map<String, Object> data = new HashMap<>();
        data.put("id", ++this.id);
        data.put("text", str);

        // Send a message
        System.out.println("Sending chuck joke...");

        Topic topic = new Topic("chuck");
        TopicUnicastMessage message = new TopicUnicastMessage(options, topic, data, payload);

        TopicMessageResponse response = this.fcmClient.send(message);
        ErrorCodeEnum errorCode = response.getErrorCode();
        if (errorCode != null) {
            System.out.println("Topic message sending failed: " + errorCode);
        }
    }
}