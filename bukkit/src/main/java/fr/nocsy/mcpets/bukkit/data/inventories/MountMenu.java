package fr.nocsy.mcpets.bukkit.data.inventories;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import lombok.Getter;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.nocsy.mcpets.bukkit.data.Pet;
import fr.nocsy.mcpets.bukkit.data.Items;
import fr.nocsy.mcpets.bukkit.data.Category;
import fr.nocsy.mcpets.bukkit.data.CategoryType;
import fr.nocsy.mcpets.bukkit.data.sql.PlayerData;
import fr.nocsy.mcpets.bukkit.data.config.Language;
import fr.nocsy.mcpets.bukkit.utils.MenuPaginationHelper;
import fr.nocsy.mcpets.bukkit.utils.MenuPaginationHelper.PaginationConfig;

/**
 * Menu to display the list of available mounts for a player
 */
public class MountMenu {

    @Getter
    private static final String title = Language.INVENTORY_MOUNTS_MENU.getMessage();

    private static final PetInventoryHolder.Type petInvType = PetInventoryHolder.Type.MOUNT_MENU;

    @Getter
    private final Inventory inventory;

    @Getter
    private final UUID owner;

    @Getter
    private final int page;

    public MountMenu(final Player p, final int page) {
        this.page = page;
        // Load the data from the player
        // Mainly for the pet stats
        PlayerData.get(p.getUniqueId());
        owner = p.getUniqueId();

        // Get only mounts (pets that are mountable)
        final List<Pet> availablePets = Pet.getAvailablePets(p);
        final List<Pet> availableMounts = new ArrayList<>();
        
        for (final Pet pet : availablePets) {
            if (pet.isMountable()) {
                availableMounts.add(pet);
            }
        }

        PaginationConfig config = MenuPaginationHelper.calculatePagination(page, availableMounts.size());
        
        final List<Pet> selectedMounts = new ArrayList<>();
        for (int i = config.startIndex(); i < config.startIndex() + config.itemsToShow() && i < availableMounts.size(); i++) {
            selectedMounts.add(availableMounts.get(i));
        }
        
        inventory = new PetInventoryHolder(config.invSize(), title, petInvType).getInventory();
        int slot = config.startSlot();
        for (final Pet mount : selectedMounts) {
            inventory.setItem(slot++, mount.buildItem(mount.getIcon(), true));
        }
        
        if (config.needsPreviousButton()) {
            inventory.setItem(0, Items.previousPage(page, p, 1));
        }
        
        if (config.needsNextButton()) {
            inventory.setItem(config.invSize() - 1, Items.nextPage(page, p, 1));
        }
    }

    public void open(final Player p) {
        if (page == 0 && p.getUniqueId().equals(owner) && !Category.getCategories(CategoryType.MOUNT).isEmpty()) {
            CategoriesMenu.openFiltered(p, CategoryType.MOUNT);
            return;
        }
        p.openInventory(inventory);
    }

}
