package fr.nocsy.mcpets.bukkit.commands.mcpets;

import fr.nocsy.mcpets.bukkit.PPermission;
import fr.nocsy.mcpets.bukkit.commands.AArgument;
import fr.nocsy.mcpets.bukkit.data.editor.Editor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArgumentEditor extends AArgument {

    public ArgumentEditor(CommandSender sender, String[] args) {
        super("editor", new int[]{1}, sender, args);
    }

    @Override
    public boolean additionalConditions() {
        return sender instanceof Player && sender.hasPermission(PPermission.ADMIN.getPermission());
    }

    @Override
    public void commandEffect() {
        Player p = (Player) sender;
        Editor editor = Editor.getEditor(p);
        editor.openEditor();
    }
}
