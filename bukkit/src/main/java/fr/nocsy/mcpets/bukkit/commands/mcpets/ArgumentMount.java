package fr.nocsy.mcpets.bukkit.commands.mcpets;

import fr.nocsy.mcpets.bukkit.commands.AArgument;
import fr.nocsy.mcpets.bukkit.data.Pet;
import fr.nocsy.mcpets.bukkit.data.config.Language;
import fr.nocsy.mcpets.bukkit.listeners.PetInteractionMenuListener;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArgumentMount extends AArgument {

    public ArgumentMount(CommandSender sender, String[] args) {
        super("mount", new int[]{1}, sender, args);
    }

    @Override
    public boolean additionalConditions() {
        return sender instanceof Player;
    }

    @Override
    public void commandEffect() {
        Player p = (Player) sender;
        Pet pet = Pet.fromOwner(p.getUniqueId());
        if (pet == null) {
            Language.NO_ACTIVE_PET.sendMessage(p);
            return;
        }

        PetInteractionMenuListener.mount(p, pet);
    }
}
