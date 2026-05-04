package com.narxoz.rpg.guild;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class GuildHall implements GuildMediator {
    private final Map<String, List<GuildMember>> topicSubscribers = new HashMap<>();
    private final List<GuildMember> allMembers = new ArrayList<>();

    @Override
    public void register(GuildMember member) {
        if (!allMembers.contains(member)) {
            allMembers.add(member);
            member.setMediator(this);
            System.out.println("[GUILD HALL] " + member.getName() + " has joined the guild.");
        }
    }

    @Override
    public void unregister(GuildMember member) {
        allMembers.remove(member);
        for (List<GuildMember> subscribers : topicSubscribers.values()) {
            subscribers.remove(member);
        }
        System.out.println("[GUILD HALL] " + member.getName() + " has left the guild.");
    }

    public void subscribe(String topic, GuildMember member) {
        topicSubscribers.computeIfAbsent(topic, k -> new ArrayList<>());
        if (!topicSubscribers.get(topic).contains(member)) {
            topicSubscribers.get(topic).add(member);
        }
    }

    public void unsubscribe(String topic, GuildMember member) {
        if (topicSubscribers.containsKey(topic)) {
            topicSubscribers.get(topic).remove(member);
        }
    }

    @Override
    public void send(String message, String topic, GuildMember sender) {
        System.out.println("\n[GUILD HALL] Routing message from " + sender.getName() + " on topic '" + topic + "'");

        List<GuildMember> subscribers = topicSubscribers.getOrDefault(topic, new ArrayList<>());

        if (subscribers.isEmpty()) {
            System.out.println("[GUILD HALL] No subscribers for topic '" + topic + "'");
            return;
        }

        for (GuildMember member : subscribers) {
            if (member != sender) {
                member.receive(message, topic, sender);
            }
        }
    }

    public void broadcast(String message, GuildMember sender) {
        System.out.println("\n[GUILD HALL] Broadcasting message from " + sender.getName());
        for (GuildMember member : allMembers) {
            if (member != sender) {
                member.receive(message, "BROADCAST", sender);
            }
        }
    }
}