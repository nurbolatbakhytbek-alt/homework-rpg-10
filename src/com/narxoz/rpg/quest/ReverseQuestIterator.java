package com.narxoz.rpg.quest;

import java.util.List;

public class ReverseQuestIterator implements QuestIterator {
    private final List<Quest> quests;
    private int position;

    public ReverseQuestIterator(List<Quest> quests) {
        this.quests = quests;
        this.position = quests.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return position >= 0;
    }

    @Override
    public Quest next() {
        if (!hasNext()) {
            return null;
        }
        return quests.get(position--);
    }

    @Override
    public void reset() {
        position = quests.size() - 1;
    }
}