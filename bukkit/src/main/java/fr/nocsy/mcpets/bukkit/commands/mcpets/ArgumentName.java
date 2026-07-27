package fr.nocsy.mcpets.bukkit.commands.mcpets;

import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;

import fr.nocsy.mcpets.bukkit.data.Pet;
import fr.nocsy.mcpets.bukkit.utils.Utils;
import fr.nocsy.mcpets.bukkit.PPermission;
import fr.nocsy.mcpets.bukkit.commands.AArgument;
import fr.nocsy.mcpets.bukkit.data.config.Language;
import fr.nocsy.mcpets.bukkit.data.config.FormatArg;
import fr.nocsy.mcpets.bukkit.data.config.GlobalConfig;
import fr.nocsy.mcpets.bukkit.listeners.PetInteractionMenuListener;

public class ArgumentName extends AArgument {

    public ArgumentName(CommandSender sender, String[] args) {
        super("name", new int[]{1}, sender, args, "/mcpets name [name]");
    }

    @Override
    public void commandEffect() {
        if (!(sender instanceof Player p))
            return;

        if (!GlobalConfig.getInstance().isNameable()) {
            Language.NO_PERM.sendMessage(p);
            return;
        }

        Pet pet = Pet.fromOwner(p.getUniqueId());
        if (pet == null) {
            Language.NO_ACTIVE_PET.sendMessage(p);
            return;
        }

        // No name provided: prompt in chat (original behavior)
        if (args.length == 1) {
            pet.setLastInteractedWith(p);
            PetInteractionMenuListener.changeName(p);
            return;
        }

        // Name provided inline: apply directly
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            if (i > 1) sb.append(" ");
            sb.append(args[i]);
        }
        String name = sb.toString().replace("'", "").replace(";;", ";").replace(";;;", ";");

        String blackListedWord = Utils.isInBlackList(name);
        if (blackListedWord != null) {
            Language.BLACKLISTED_WORD.sendMessageFormatted(p, new FormatArg("%word%", blackListedWord));
            return;
        }

        boolean stripColor = !p.hasPermission(PPermission.COLOR.getPermission());

        if (name.isEmpty()) {
            Language.NICKNAME_NOT_CHANGED.sendMessage(p);
            return;
        }

        pet.setDisplayName(name, true, stripColor);
        Language.NICKNAME_CHANGED_SUCCESSFULY.sendMessage(p);
    }

    @Override
    protected boolean additionalConditions() {
        return true;
    }

    @Override
    public boolean conditionsVerified() {
        return args.length >= 1 && args[0].equalsIgnoreCase(argumentName);
    }
}
