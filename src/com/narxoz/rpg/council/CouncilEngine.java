package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;
import java.util.ArrayList;
import java.util.List;

public class CouncilEngine {
    private final List<Hero> heroes;
    private final QuestLog questLog;
    private final GuildHall guildHall;
    private final Quartermaster quartermaster;
    private final Scout scout;
    private final Healer healer;
    private final Captain captain;

    public CouncilEngine(List<Hero> heroes, QuestLog questLog) {
        this.heroes = heroes;
        this.questLog = questLog;
        this.guildHall = new GuildHall();

        this.quartermaster = new Quartermaster("Boromir");
        this.scout = new Scout("Legolas");
        this.healer = new Healer("Anduin");
        this.captain = new Captain("Aragorn");

        setupMediator();
    }

    private void setupMediator() {
        guildHall.register(quartermaster);
        guildHall.register(scout);
        guildHall.register(healer);
        guildHall.register(captain);

        guildHall.subscribe("supply_request", quartermaster);
        guildHall.subscribe("weapon_request", quartermaster);
        guildHall.subscribe("potion_request", quartermaster);
        guildHall.subscribe("scout_request", scout);
        guildHall.subscribe("intel_request", scout);
        guildHall.subscribe("healing_request", healer);
        guildHall.subscribe("revive_request", healer);
        guildHall.subscribe("scout_report", captain);
        guildHall.subscribe("intel_report", captain);
        guildHall.subscribe("healing_response", captain);
        guildHall.subscribe("potion_response", captain);
        guildHall.subscribe("weapon_response", captain);
        guildHall.subscribe("supply_response", captain);
        guildHall.subscribe("revive_response", captain);

        guildHall.subscribe("supply_response", scout);
        guildHall.subscribe("weapon_response", scout);
        guildHall.subscribe("potion_response", scout);

        guildHall.subscribe("supply_response", healer);
        guildHall.subscribe("potion_response", healer);
    }

    public CouncilRunResult runCouncil() {
        System.out.println("\n⚔️ WAR COUNCIL ASSEMBLED ⚔️");
        System.out.println("The Adventurers' Guild prepares for the next campaign...\n");

        displayHeroes();

        captain.assignMission("Retrieve the Crystal of Time from the Chronomancer's Tower");

        System.out.println("\n📜 REVIEWING QUESTS");
        System.out.println("-".repeat(50));

        List<Quest> selectedQuests = new ArrayList<>();
        int resourcesAllocated = 0;

        QuestIterator orderedIterator = questLog.getOrderedIterator();
        System.out.println("\n--- Quests in arrival order ---");
        while (orderedIterator.hasNext()) {
            Quest quest = orderedIterator.next();
            System.out.println("  " + quest);
        }

        QuestIterator priorityIterator = questLog.getPriorityIterator(QuestPriority.HIGH);
        System.out.println("\n--- High priority and above quests ---");
        while (priorityIterator.hasNext()) {
            Quest quest = priorityIterator.next();
            System.out.println("  ✓ " + quest);
            selectedQuests.add(quest);
        }

        QuestIterator reverseIterator = questLog.getReverseIterator();
        System.out.println("\n--- Most recent quests (reverse order) ---");
        int count = 0;
        while (reverseIterator.hasNext() && count < 3) {
            Quest quest = reverseIterator.next();
            System.out.println("  " + quest);
            count++;
        }

        System.out.println("\n💰 RESOURCE ALLOCATION");
        System.out.println("-".repeat(50));

        captain.send("10", "supply_request");
        captain.send("5", "weapon_request");
        captain.send("8", "potion_request");

        captain.send("Scout needed for reconnaissance", "scout_request");
        captain.send("Gather intel on enemy positions", "intel_request");

        captain.send("50", "healing_request");

        System.out.println("\n🔄 COORDINATION RESPONSES");
        System.out.println("-".repeat(50));

        quartermaster.reportStock();
        healer.reportInventory();
        captain.reportStatus();

        resourcesAllocated = 10 + 5 + 8;

        System.out.println("\n🎯 BUILDING THE PARTY");
        System.out.println("-".repeat(50));

        for (Hero hero : heroes) {
            captain.addToParty(hero.getName());
        }

        boolean success = !selectedQuests.isEmpty() && resourcesAllocated > 0;

        return new CouncilRunResult(success, questLog.size(), selectedQuests, resourcesAllocated, heroes.size());
    }

    private void displayHeroes() {
        System.out.println("Party Members:");
        for (Hero hero : heroes) {
            System.out.println("  - " + hero.getName() + " (Level " + hero.getLevel() + ")");
        }
    }
}