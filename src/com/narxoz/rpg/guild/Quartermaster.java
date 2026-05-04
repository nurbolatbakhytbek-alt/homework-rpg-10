package com.narxoz.rpg.guild;

public class Quartermaster extends GuildMember {
    private int suppliesStock = 100;
    private int weaponsStock = 50;
    private int potionsStock = 75;

    public Quartermaster(String name) {
        super(name);
    }

    @Override
    public void receive(String message, String topic, GuildMember sender) {
        System.out.println("[QUARTERMASTER " + name + "] Received message from " + sender.getName());

        switch (topic) {
            case "supply_request":
                handleSupplyRequest(message, sender);
                break;
            case "weapon_request":
                handleWeaponRequest(message, sender);
                break;
            case "potion_request":
                handlePotionRequest(message, sender);
                break;
            default:
                System.out.println("[QUARTERMASTER " + name + "] Ignoring topic: " + topic);
        }
    }

    private void handleSupplyRequest(String message, GuildMember sender) {
        int requested = Integer.parseInt(message);
        if (suppliesStock >= requested) {
            suppliesStock -= requested;
            System.out.println("[QUARTERMASTER " + name + "] Granted " + requested + " supplies to " + sender.getName());
            sender.send(requested + " supplies delivered", "supply_response");
        } else {
            System.out.println("[QUARTERMASTER " + name + "] Insufficient supplies! Available: " + suppliesStock);
            sender.send("Insufficient supplies", "supply_response");
        }
    }

    private void handleWeaponRequest(String message, GuildMember sender) {
        int requested = Integer.parseInt(message);
        if (weaponsStock >= requested) {
            weaponsStock -= requested;
            System.out.println("[QUARTERMASTER " + name + "] Granted " + requested + " weapons to " + sender.getName());
            sender.send(requested + " weapons delivered", "weapon_response");
        } else {
            System.out.println("[QUARTERMASTER " + name + "] Insufficient weapons! Available: " + weaponsStock);
            sender.send("Insufficient weapons", "weapon_response");
        }
    }

    private void handlePotionRequest(String message, GuildMember sender) {
        int requested = Integer.parseInt(message);
        if (potionsStock >= requested) {
            potionsStock -= requested;
            System.out.println("[QUARTERMASTER " + name + "] Granted " + requested + " potions to " + sender.getName());
            sender.send(requested + " potions delivered", "potion_response");
        } else {
            System.out.println("[QUARTERMASTER " + name + "] Insufficient potions! Available: " + potionsStock);
            sender.send("Insufficient potions", "potion_response");
        }
    }

    public void reportStock() {
        System.out.println("[QUARTERMASTER " + name + "] Stock: Supplies=" + suppliesStock +
                ", Weapons=" + weaponsStock + ", Potions=" + potionsStock);
    }
}