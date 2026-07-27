package fr.nocsy.mcpets.bukkit.data.flags;

import fr.nocsy.mcpets.bukkit.MCPets;

public class PetDamageableByPlayerFlag extends AbstractFlag {

    public static String NAME = "mcpets-pet-player-damage";

    public PetDamageableByPlayerFlag(MCPets instance) {
        super(NAME, false, instance);
    }

    @Override
    public void register() {
        super.register();
    }
}
