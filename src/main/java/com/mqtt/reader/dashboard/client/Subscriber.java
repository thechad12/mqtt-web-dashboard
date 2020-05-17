package com.mqtt.reader.dashboard.client;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.lang.Throwable;
import java.util.UUID;


public class Subscriber implements MqttCallback {

    private String serverURI;
    private MqttAsyncClient client;
    private String message;
    private String clientID = UUID.randomUUID().toString();

    public Subscriber(String serverURI) throws MqttException {
        this.serverURI = serverURI;
        this.client = new MqttAsyncClient(serverURI,clientID,new MemoryPersistence());
        this.client.setCallback(this);
        this.client.connect();
    }

    @Override
    public void connectionLost(Throwable ex) {
        System.out.println("Connection Lost " + ex.toString());
        System.exit(1);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
       this.message = new String(message.getPayload());
    }

    public String getMessage() {
        return this.message;
    }
}
