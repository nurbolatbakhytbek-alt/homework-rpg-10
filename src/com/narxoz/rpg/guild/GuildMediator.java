package com.narxoz.rpg.guild;

public interface GuildMediator {
    void send(String message, String topic, GuildMember sender);
    void register(GuildMember member);
    void unregister(GuildMember member);
}