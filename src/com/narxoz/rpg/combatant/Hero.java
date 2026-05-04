package com.narxoz.rpg.combatant;

public class Hero {
    private final String name;
    private int hp;
    private final int maxHp;
    private int mana;
    private final int maxMana;
    private int level;
    private int exp;

    public Hero(String name, int hp, int mana) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.mana = mana;
        this.maxMana = mana;
        this.level = 1;
        this.exp = 0;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getMana() { return mana; }
    public int getMaxMana() { return maxMana; }
    public int getLevel() { return level; }

    public boolean isAlive() { return hp > 0; }

    public void takeDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public void useMana(int amount) {
        mana = Math.max(0, mana - amount);
    }

    public void restoreMana(int amount) {
        mana = Math.min(maxMana, mana + amount);
    }

    public void gainExp(int amount) {
        exp += amount;
        if (exp >= level * 100) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;
        exp = 0;
        System.out.println("[HERO " + name + "] Level up! Now level " + level);
    }

    public void displayStatus() {
        System.out.println("  " + name + " | HP: " + hp + "/" + maxHp + " | Mana: " + mana + "/" + maxMana + " | Level: " + level);
    }
}