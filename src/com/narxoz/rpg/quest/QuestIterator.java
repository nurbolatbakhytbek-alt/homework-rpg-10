package com.narxoz.rpg.quest;

import java.util.Iterator;

public interface QuestIterator extends Iterator<Quest> {
    boolean hasNext();
    Quest next();
    void reset();
}