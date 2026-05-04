package com.narxoz.rpg.guild;

import java.util.Random;

public class Scout extends GuildMember {
    private final Random random = new Random();

    public Scout(String name) {
        super(name);
    }

    @Override
    public void receive(String message, String topic, GuildMember sender) {
        System.out.println("[SCOUT " + name + "] Received message from " + sender.getName());

        switch (topic) {
            case "scout_request":
                handleScoutRequest(sender);
                break;
            case "intel_request":
                provideIntel(sender);
                break;
            case "supply_response":
            case "weapon_response":
            case "potion_response":
                System.out.println("[SCOUT " + name + "] Resource update: " + message);
                break;
            default:
                System.out.println("[SCOUT " + name + "] Ignoring topic: " + topic);
        }
    }

    private void handleScoutRequest(GuildMember sender) {
        System.out.println("[SCOUT " + name + "] Gathering intelligence for " + sender.getName());

        String[] threats = {"Goblin Camp", "Dragon Nest", "Undead Crypt", "Bandit Hideout", "Abandoned Mine"};
        String threat = threats[random.nextInt(threats.length)];
        int difficulty = random.nextInt(10) + 1;

        String report = threat + "|Difficulty:" + difficulty;
        System.out.println("[SCOUT " + name + "] Report: " + threat + " (Difficulty: " + difficulty + "/10)");
        sender.send(report, "scout_report");
    }

    private void provideIntel(GuildMember sender) {
        String[] intel = {"Enemy patrols at night", "Secret entrance found", "Reinforcements incoming", "Weakness: fire"};
        String info = intel[random.nextInt(intel.length)];
        System.out.println("[SCOUT " + name + "] Providing intel: " + info);
        sender.send(info, "intel_report");
    }
}