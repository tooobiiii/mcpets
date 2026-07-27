package fr.nocsy.mcpets.bukkit.commands.mcpets;

import fr.nocsy.mcpets.bukkit.MCPets;
import fr.nocsy.mcpets.bukkit.PPermission;
import fr.nocsy.mcpets.bukkit.commands.AArgument;
import fr.nocsy.mcpets.bukkit.data.Pet;
import fr.nocsy.mcpets.bukkit.data.config.FormatArg;
import fr.nocsy.mcpets.bukkit.data.config.Language;
import fr.nocsy.mcpets.bukkit.data.sql.PlayerData;
import org.bukkit.command.CommandSender;

public class ArgumentReload extends AArgument {

    public ArgumentReload(CommandSender sender, String[] args) {
        super("reload", new int[]{1}, sender, args);
    }

    @Override
    public boolean additionalConditions() {
        return sender.hasPermission(PPermission.ADMIN.getPermission());
    }

    @Override
    public void commandEffect() {
        PlayerData.saveDB();
        MCPets.loadConfigs();
        Language.RELOAD_SUCCESS.sendMessage(sender);
        Language.HOW_MANY_PETS_LOADED.sendMessageFormatted(sender, new FormatArg("%numberofpets%", Integer.toString(Pet.getObjectPets().size())));
    }
}
