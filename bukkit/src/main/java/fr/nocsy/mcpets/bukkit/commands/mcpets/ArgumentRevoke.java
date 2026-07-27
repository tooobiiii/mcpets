package fr.nocsy.mcpets.bukkit.commands.mcpets;

import fr.nocsy.mcpets.bukkit.PPermission;
import fr.nocsy.mcpets.bukkit.commands.AArgument;
import fr.nocsy.mcpets.bukkit.data.Pet;
import fr.nocsy.mcpets.bukkit.data.config.FormatArg;
import fr.nocsy.mcpets.bukkit.data.config.Language;
import fr.nocsy.mcpets.bukkit.listeners.PetInteractionMenuListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArgumentRevoke extends AArgument {

    public ArgumentRevoke(CommandSender sender, String[] args) {
        super("revoke", new int[]{1, 2}, sender, args);
    }

    @Override
    public void commandEffect() {

        if (args.length == 1 && sender instanceof Player) {
            Player p = (Player) sender;
            Pet pet = Pet.fromOwner(p.getUniqueId());
            if (pet == null) {
                Language.NO_ACTIVE_PET.sendMessage(p);
                return;
            }

            PetInteractionMenuListener.revoke(p, pet);
        }
        else if (args.length == 2 && sender.hasPermission(PPermission.ADMIN.getPermission())) {

            String playerName = args[1];
            Player p = Bukkit.getPlayer(playerName);
            if (p == null) {
                Language.PLAYER_NOT_CONNECTED.sendMessageFormatted(sender, new FormatArg("%player%", playerName));
                return;
            }

            Pet pet = Pet.fromOwner(p.getUniqueId());
            if (pet == null) {
                return;
            }

            PetInteractionMenuListener.revoke(p, pet);
        }
    }

    @Override
    protected boolean additionalConditions() {
        return true;
    }
}
