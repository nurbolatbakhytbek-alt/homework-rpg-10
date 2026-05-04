package com.narxoz.rpg.guild;

public class Healer extends GuildMember {
    private int healingPotions = 30;
    private int manaPotions = 25;
    private int reviveScrolls = 5;

    public Healer(String name) {
        super(name);
    }

    @Override
    public void receive(String message, String topic, GuildMember sender) {
        System.out.println("[HEALER " + name + "] Received message from " + sender.getName());

        switch (topic) {
            case "healing_request":
                handleHealingRequest(message, sender);
                break;
            case "potion_request":
                handlePotionRequest(message, sender);
                break;
            case "revive_request":
                handleReviveRequest(sender);
                break;
            case "supply_response":
                System.out.println("[HEALER " + name + "] Supply update: " + message);
                break;
            default:
                System.out.println("[HEALER " + name + "] Ignoring topic: " + topic);
        }
    }

    private void handleHealingRequest(String message, GuildMember sender) {
        int healAmount = Integer.parseInt(message);
        System.out.println("[HEALER " + name + "] Providing " + healAmount + " HP healing to " + sender.getName());
        sender.send("Healed for " + healAmount, "healing_response");
    }

    private void handlePotionRequest(String message, GuildMember sender) {
        String[] parts = message.split(":");
        String potionType = parts[0];
        int count = Integer.parseInt(parts[1]);

        switch (potionType) {
            case "healing":
                if (healingPotions >= count) {
                    healingPotions -= count;
                    System.out.println("[HEALER " + name + "] Gave " + count + " healing potions to " + sender.getName());
                    sender.send(count + " healing potions delivered", "potion_response");
                } else {
                    sender.send("Insufficient healing potions", "potion_response");
                }
                break;
            case "mana":
                if (manaPotions >= count) {
                    manaPotions -= count;
                    System.out.println("[HEALER " + name + "] Gave " + count + " mana potions to " + sender.getName());
                    sender.send(count + " mana potions delivered", "potion_response");
                } else {
                    sender.send("Insufficient mana potions", "potion_response");
                }
                break;
        }
    }

    private void handleReviveRequest(GuildMember sender) {
        if (reviveScrolls > 0) {
            reviveScrolls--;
            System.out.println("[HEALER " + name + "] Used revive scroll on " + sender.getName());
            sender.send("Revived", "revive_response");
        } else {
            System.out.println("[HEALER " + name + "] No revive scrolls left!");
            sender.send("No revive scrolls available", "revive_response");
        }
    }

    public void reportInventory() {
        System.out.println("[HEALER " + name + "] Inventory: Healing=" + healingPotions +
                ", Mana=" + manaPotions + ", Revive=" + reviveScrolls);
    }
}