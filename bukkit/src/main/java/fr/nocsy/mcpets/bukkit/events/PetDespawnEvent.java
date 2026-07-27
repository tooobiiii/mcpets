package fr.nocsy.mcpets.bukkit.events;

import fr.nocsy.mcpets.bukkit.data.Pet;
import fr.nocsy.mcpets.bukkit.data.PetDespawnReason;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PetDespawnEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    @Getter
    private final Pet pet;
    @Getter
    private final PetDespawnReason reason;

    public PetDespawnEvent(Pet pet, PetDespawnReason reason) {
        this.pet = pet;
        this.reason = reason;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
