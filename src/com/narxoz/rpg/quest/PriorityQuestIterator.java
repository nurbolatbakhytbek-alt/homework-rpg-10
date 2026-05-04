package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.List;

public class PriorityQuestIterator implements QuestIterator {
    private final List<Quest> filteredQuests;
    private int position = 0;

    public PriorityQuestIterator(List<Quest> quests, QuestPriority minPriority) {
        this.filteredQuests = new ArrayList<>();
        for (Quest quest : quests) {
            if (quest.getPriority().ordinal() >= minPriority.ordinal()) {
                filteredQuests.add(quest);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return position < filteredQuests.size();
    }

    @Override
    public Quest next() {
        if (!hasNext()) {
            return null;
        }
        return filteredQuests.get(position++);
    }

    @Override
    public void reset() {
        position = 0;
    }

    public int size() {
        return filteredQuests.size();
    }
}