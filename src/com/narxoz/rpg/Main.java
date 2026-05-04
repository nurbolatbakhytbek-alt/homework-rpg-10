package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║     THE ADVENTURERS' GUILD - Homework 10              ║");
        System.out.println("║     Iterator Pattern + Mediator Pattern               ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");

        List<Hero> heroes = Arrays.asList(
                new Hero("Thorgrim", 350, 80),
                new Hero("Elara", 250, 150),
                new Hero("Merek", 300, 100)
        );

        QuestLog questLog = createQuestLog();

        System.out.println("\n📚 ITERATOR PATTERN DEMONSTRATION");
        System.out.println("=".repeat(50));

        System.out.println("\n--- Ordered Iterator (Insertion Order) ---");
        QuestIterator ordered = questLog.getOrderedIterator();
        while (ordered.hasNext()) {
            System.out.println("  " + ordered.next());
        }

        System.out.println("\n--- Reverse Iterator (Newest First) ---");
        QuestIterator reverse = questLog.getReverseIterator();
        while (reverse.hasNext()) {
            System.out.println("  " + reverse.next());
        }

        System.out.println("\n--- Priority Iterator (HIGH and above) ---");
        QuestIterator priority = questLog.getPriorityIterator(QuestPriority.HIGH);
        while (priority.hasNext()) {
            System.out.println("  ✓ " + priority.next());
        }

        System.out.println("\n--- Reset Demo (Ordered Iterator Reset) ---");
        ordered.reset();
        System.out.println("  Reset to beginning. First quest: " + ordered.next().getTitle());

        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎮 STARTING WAR COUNCIL");
        System.out.println("=".repeat(50));

        CouncilEngine engine = new CouncilEngine(heroes, questLog);
        CouncilRunResult result = engine.runCouncil();

        result.display();

        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("📊 PATTERN SUMMARY");
        System.out.println("═══════════════════════════════════════════════════════");

        System.out.println("\n🔹 ITERATOR PATTERN:");
        System.out.println("   - QuestLog aggregate hides internal List<Quest>");
        System.out.println("   - Three iterators: Ordered, Reverse, Priority");
        System.out.println("   - Each iterator implements custom traversal logic");
        System.out.println("   - Support for reset() to restart iteration");
        System.out.println("   - No exposure of underlying collection");

        System.out.println("\n🔸 MEDIATOR PATTERN:");
        System.out.println("   - GuildHall mediates all guild member communication");
        System.out.println("   - Members: Quartermaster, Scout, Healer, Captain");
        System.out.println("   - Topic-based subscription system");
        System.out.println("   - No direct references between colleagues");
        System.out.println("   - Captain coordinates mission via mediator");
        System.out.println("   - Scout provides intelligence through mediator");
        System.out.println("   - Quartermaster and Healer supply resources");

        System.out.println("\n✅ Council " + (result.isSuccess() ? "COMPLETED" : "FAILED"));
        System.out.println("   Quests reviewed: " + result.getQuestsReviewed());
        System.out.println("   Resources allocated: " + result.getResourcesAllocated());
        System.out.println("   Party size: " + result.getPartySize());
    }

    private static QuestLog createQuestLog() {
        QuestLog log = new QuestLog();

        log.addQuest(new Quest("Goblin Menace", "Clear the goblin camp near the village",
                QuestPriority.MEDIUM, 200, 50));

        log.addQuest(new Quest("Dragon's Hoard", "Defeat the dragon and claim its treasure",
                QuestPriority.CRITICAL, 2000, 500));

        log.addQuest(new Quest("Lost Artifact", "Recover the ancient amulet from the ruins",
                QuestPriority.HIGH, 500, 120));

        log.addQuest(new Quest("Rescue Mission", "Save the kidnapped villagers",
                QuestPriority.CRITICAL, 800, 200));

        log.addQuest(new Quest("Potion Delivery", "Deliver potions to the nearby town",
                QuestPriority.LOW, 50, 20));

        log.addQuest(new Quest("Investigate Cult", "Uncover the secret cult in the city",
                QuestPriority.HIGH, 600, 150));

        return log;
    }
}