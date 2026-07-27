package fr.nocsy.mcpets.bukkit.listeners;

import fr.nocsy.mcpets.bukkit.data.inventories.PetInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class PetInventoryListener implements Listener {

    @EventHandler
    public void inventory(InventoryCloseEvent e) {
        Player p = (Player)e.getPlayer();
        PetInventory petInventory = PetInventory.fromCurrentView(p);

        if (petInventory != null) {
            petInventory.setInventory(e.getView().getTopInventory());
            petInventory.close(p);
        }
    }
}
