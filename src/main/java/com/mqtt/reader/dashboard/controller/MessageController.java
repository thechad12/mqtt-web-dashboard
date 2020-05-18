package com.mqtt.reader.dashboard.controller;

import com.mqtt.reader.dashboard.client.Subscriber;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Controller
@RequestMapping("/dashboard")
public class MessageController {

    @Autowired
    public Subscribers subscribers;

    @RequestMapping("/messages")
    public String viewMessages(@RequestParam String[] topics,
                               Model model) {
        model.addAttribute("topics",topics);
        ArrayList<MqttAsyncClient> subClients = new ArrayList<>();
        HashMap<MqttAsyncClient,String[]> clients = subscribers.getClients();
        clients.forEach((key,value) -> {
            for(int i=0;i<value.length;i++) {
                String topic = value[i];
                if (Arrays.asList(topics).contains(topic)) {
                    subClients.add(key);
                }
            }
        });
        model.addAttribute("clients",subClients);
        return "dashboard";
    }

}
