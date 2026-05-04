package com.narxoz.rpg.quest;

public class Quest {
    private final String title;
    private final String description;
    private final QuestPriority priority;
    private final int rewardGold;
    private final int rewardExp;

    public Quest(String title, String description, QuestPriority priority, int rewardGold, int rewardExp) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.rewardGold = rewardGold;
        this.rewardExp = rewardExp;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public QuestPriority getPriority() { return priority; }
    public int getRewardGold() { return rewardGold; }
    public int getRewardExp() { return rewardExp; }

    @Override
    public String toString() {
        return String.format("[%s] %s (%d gold, %d exp)", priority, title, rewardGold, rewardExp);
    }
}