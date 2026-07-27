package fr.nocsy.mcpets.bukkit.commands.mcpets;

import fr.nocsy.mcpets.bukkit.PPermission;
import fr.nocsy.mcpets.bukkit.commands.AArgument;
import fr.nocsy.mcpets.bukkit.data.CategoryType;
import fr.nocsy.mcpets.bukkit.data.inventories.CategoriesMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Argument to filter the categories menu by only showing mounts
 */
public class ArgumentMounts extends AArgument {

    public ArgumentMounts(CommandSender sender, String[] args) {
        super("mounts", new int[]{1}, sender, args);
    }

    @Override
    public boolean additionalConditions() {
        return sender instanceof Player && sender.hasPermission(PPermission.USE.getPermission());
    }

    @Override
    public void commandEffect() {
        if (sender instanceof Player) {
            CategoriesMenu.openFiltered((Player) sender, CategoryType.MOUNT);
        }
    }
}
