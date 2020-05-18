package com.mqtt.reader.dashboard.controller;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;

import java.util.HashMap;

public class Subscribers {

    /**
     * Shared object
     * Keep list of all active clients and tokens
     */

    private HashMap<MqttAsyncClient,String[]> clients;

    public Subscribers() {
        this.clients = new HashMap<>();
    }

    public HashMap<MqttAsyncClient,String[]> getClients() {
        return this.clients;
    }

    public void add(MqttAsyncClient c,String[] t) {
        this.clients.put(c,t);
    }

    public void remove(MqttAsyncClient c) {
        this.clients.remove(c);
    }
}
