package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.List;

public class QuestLog {
    private final List<Quest> quests = new ArrayList<>();

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public boolean removeQuest(String title) {
        return quests.removeIf(q -> q.getTitle().equals(title));
    }

    public QuestIterator getOrderedIterator() {
        return new OrderedQuestIterator(quests);
    }

    public QuestIterator getReverseIterator() {
        return new ReverseQuestIterator(quests);
    }

    public QuestIterator getPriorityIterator(QuestPriority minPriority) {
        return new PriorityQuestIterator(quests, minPriority);
    }

    public int size() {
        return quests.size();
    }

    public boolean isEmpty() {
        return quests.isEmpty();
    }
}