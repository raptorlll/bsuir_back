package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.FcmNotificationService;
import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.http.client.IFcmClient;
import de.bytefish.fcmjava.model.enums.ErrorCodeEnum;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import de.bytefish.fcmjava.requests.notification.NotificationUnicastMessage;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class FcmNotificationServiceImpl implements FcmNotificationService {
    @Value("${fcm.api-key}")
    private String apiKey;

    @Value("${fcm.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    private final IFcmClient fcmClient;

    private int id = 0;

    public FcmNotificationServiceImpl(IFcmClient fcmClient) {
        this.restTemplate = new RestTemplate();
        this.fcmClient = fcmClient;
    }

    @Override
    public void sendPushMessage(String str, User user) {

//        FcmMessageOptions options = FcmMessageOptions.builder()
//                .setTimeToLive(Duration.ofMinutes(2)).build();
//
//        NotificationPayload payload = NotificationPayload.builder()
//                .setBody(str)
//                .setTitle("Chuck Norris Joke")
//                .build();
//
//        // Send a message
//        System.out.println("Sending chuck joke...");
//        NotificationUnicastMessage message = new NotificationUnicastMessage(options, user.getToken(), payload);
//
//        FcmMessageResponse response = this.fcmClient.send(message);
//        System.out.println("push");
    }

    @Override
    public void sendPushMessageToDevice(User user, String title, String body) {
        if (user.getToken()==null) {
            return;
        }

        try {
            String result = "";

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + apiKey);
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject json = new JSONObject();

            json.put("to", java.net.URLDecoder.decode(user.getToken().trim(), "UTF-8"));
            JSONObject info = new JSONObject();
            info.put("title", title); // Notification title
            info.put("body", body); // Notification
            // body
            json.put("notification", info);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("GCM Notification is sent successfully");
    }

}