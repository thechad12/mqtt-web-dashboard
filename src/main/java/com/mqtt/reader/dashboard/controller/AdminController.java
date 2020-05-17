package com.mqtt.reader.dashboard.controller;

import com.mqtt.reader.dashboard.client.Subscriber;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

   @RequestMapping("/connections")
    public String getConnections() {
       jdbcTemplate.execute("SELECT * FROM endpoints");
       return "connections";
   }

   @RequestMapping("/addConnection")
    public String addConnection(@RequestParam(name="endpoint") String endpoint, Model model) {
       model.addAttribute("endpoint", endpoint);
       return "addConnection";
   }

   @RequestMapping("/subscribe")
    public String subscribe(@RequestParam String topic,
                            @RequestParam String endpoint, Model model) throws MqttException {
       String[] topics = new String[1];
       int[] qos = new int[1];
       qos[0] = 1;
       topics[0] = topic;
       model.addAttribute("topic", topic);
       model.addAttribute("endpoint", endpoint);
       MqttAsyncClient client = new MqttAsyncClient(endpoint,UUID.randomUUID().toString());
       client.subscribe(topics,qos);
       client.setCallback(new Subscriber(endpoint));
       return "subscribe";
   }
}
