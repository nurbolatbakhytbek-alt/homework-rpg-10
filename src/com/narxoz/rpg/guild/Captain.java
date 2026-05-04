package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.List;

public class Captain extends GuildMember {
    private final List<String> partyMembers = new ArrayList<>();
    private String currentMission = null;

    public Captain(String name) {
        super(name);
    }

    @Override
    public void receive(String message, String topic, GuildMember sender) {
        System.out.println("[CAPTAIN " + name + "] Received message from " + sender.getName());

        switch (topic) {
            case "scout_report":
                handleScoutReport(message);
                break;
            case "intel_report":
                handleIntelReport(message);
                break;
            case "healing_response":
            case "potion_response":
            case "weapon_response":
            case "supply_response":
                System.out.println("[CAPTAIN " + name + "] Resource confirmation: " + message);
                break;
            case "revive_response":
                System.out.println("[CAPTAIN " + name + "] Revive status: " + message);
                break;
            default:
                System.out.println("[CAPTAIN " + name + "] Ignoring topic: " + topic);
        }
    }

    private void handleScoutReport(String report) {
        String[] parts = report.split("\\|");
        String threat = parts[0];
        String difficulty = parts[1].split(":")[1];
        System.out.println("[CAPTAIN " + name + "] Analyzed threat: " + threat + " (Difficulty: " + difficulty + "/10)");

        if (Integer.parseInt(difficulty) > 7) {
            System.out.println("[CAPTAIN " + name + "] HIGH THREAT DETECTED! Requesting additional resources...");
            send("Need extra supplies for " + threat, "supply_request");
            send("Requesting healing support", "healing_request");
        }
    }

    private void handleIntelReport(String intel) {
        System.out.println("[CAPTAIN " + name + "] Strategic update: " + intel);
        currentMission = intel;
    }

    public void assignMission(String mission) {
        this.currentMission = mission;
        System.out.println("[CAPTAIN " + name + "] Mission assigned: " + mission);
        send("Scout needed for " + mission, "scout_request");
    }

    public void addToParty(String memberName) {
        partyMembers.add(memberName);
        System.out.println("[CAPTAIN " + name + "] " + memberName + " joined the party. Party size: " + partyMembers.size());
    }

    public void reportStatus() {
        System.out.println("[CAPTAIN " + name + "] Status: Mission=" + (currentMission != null ? currentMission : "None") +
                ", Party=" + partyMembers.size() + " members");
    }
}