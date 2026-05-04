package com.narxoz.rpg.council;

import com.narxoz.rpg.quest.Quest;
import java.util.List;

public class CouncilRunResult {
    private final boolean success;
    private final int questsReviewed;
    private final List<Quest> selectedQuests;
    private final int resourcesAllocated;
    private final int partySize;

    public CouncilRunResult(boolean success, int questsReviewed, List<Quest> selectedQuests,
                            int resourcesAllocated, int partySize) {
        this.success = success;
        this.questsReviewed = questsReviewed;
        this.selectedQuests = selectedQuests;
        this.resourcesAllocated = resourcesAllocated;
        this.partySize = partySize;
    }

    public boolean isSuccess() { return success; }
    public int getQuestsReviewed() { return questsReviewed; }
    public List<Quest> getSelectedQuests() { return selectedQuests; }
    public int getResourcesAllocated() { return resourcesAllocated; }
    public int getPartySize() { return partySize; }

    public void display() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║          WAR COUNCIL RESULTS               ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("Council Success: " + (success ? "✓ YES" : "✗ NO"));
        System.out.println("Quests Reviewed: " + questsReviewed);
        System.out.println("Resources Allocated: " + resourcesAllocated);
        System.out.println("Party Size: " + partySize);

        if (!selectedQuests.isEmpty()) {
            System.out.println("\nSelected Quests:");
            for (Quest q : selectedQuests) {
                System.out.println("  - " + q);
            }
        }
    }
}