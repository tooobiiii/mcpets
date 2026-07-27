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
import fr.nocsy.mcpets.bukkit.data.sql.PlayerData;
import fr.nocsy.mcpets.bukkit.data.config.Language;
import fr.nocsy.mcpets.bukkit.utils.MenuPaginationHelper;
import fr.nocsy.mcpets.bukkit.utils.MenuPaginationHelper.PaginationConfig;

public class PetMenu {

    @Getter
    private static final String title = Language.INVENTORY_PETS_MENU.getMessage();

    @Getter
    private final Inventory inventory;

    @Getter
    private final UUID owner;

    public PetMenu(final Player p, final int page) {
        // Load the data from the player
        // Mainly for the pet stats
        PlayerData.get(p.getUniqueId());
        owner = p.getUniqueId();

        final List<Pet> availablePets = Pet.getAvailablePets(p);

        PaginationConfig config = MenuPaginationHelper.calculatePagination(page, availablePets.size());
        
        final List<Pet> selectedPets = new ArrayList<>();
        for (int i = config.startIndex(); i < config.startIndex() + config.itemsToShow() && i < availablePets.size(); i++) {
            selectedPets.add(availablePets.get(i));
        }

        inventory = new PetInventoryHolder(config.invSize(), title, PetInventoryHolder.Type.PET_MENU).getInventory();
        int slot = config.startSlot();
        for (final Pet pet : selectedPets) {
            inventory.setItem(slot++, pet.buildItem(pet.getIcon(), true));
        }

        int maxPages = MenuPaginationHelper.calculateMaxPages(availablePets.size());

        if (config.needsPreviousButton()) {
            inventory.setItem(0, Items.previousPage(page, p, maxPages));
        }

        if (config.needsNextButton()) {
            inventory.setItem(config.invSize() - 1, Items.nextPage(page, p, maxPages));
        }
    }

    public void open(final Player p) {
        if (p.getUniqueId().equals(owner) && !Category.getCategories().isEmpty()) {
            CategoriesMenu.open(p);
            return;
        }
        p.openInventory(inventory);
    }

}
