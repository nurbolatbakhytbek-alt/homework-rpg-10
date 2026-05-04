package com.narxoz.rpg.guild;

public abstract class GuildMember {
    protected final String name;
    protected GuildMediator mediator;

    public GuildMember(String name) {
        this.name = name;
    }

    public void setMediator(GuildMediator mediator) {
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public abstract void receive(String message, String topic, GuildMember sender);

    public void send(String message, String topic) {
        if (mediator != null) {
            mediator.send(message, topic, this);
        }
    }
}