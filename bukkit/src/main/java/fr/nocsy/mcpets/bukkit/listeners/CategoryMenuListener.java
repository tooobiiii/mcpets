package fr.nocsy.mcpets.bukkit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.nocsy.mcpets.bukkit.data.Pet;
import fr.nocsy.mcpets.bukkit.utils.PDCTag;
import fr.nocsy.mcpets.bukkit.data.Category;
import fr.nocsy.mcpets.bukkit.data.CategoryType;
import fr.nocsy.mcpets.bukkit.data.config.GlobalConfig;
import fr.nocsy.mcpets.bukkit.utils.MenuPaginationHelper;
import fr.nocsy.mcpets.bukkit.data.inventories.CategoriesMenu;
import fr.nocsy.mcpets.bukkit.data.inventories.PetInventoryHolder;

public class CategoryMenuListener implements Listener {

    @EventHandler
    public void click(final InventoryClickEvent e) {
        if (Category.getCategories().isEmpty()) {
            return;
        }

        if (!(e.getWhoClicked() instanceof final Player p)) {
            return;
        }

        final Category category = Category.getCategoryView(p);

        if (category == null) {
            return;
        }

        if (!(e.getInventory().getHolder() instanceof final PetInventoryHolder holder)) {
            return;
        }

        if (holder.getType() != PetInventoryHolder.Type.CATEGORY_MENU) {
            return;
        }

        e.setCancelled(true);

        if (e.getClickedInventory() == null && GlobalConfig.getInstance().isEnableClickBackToMenu()) {
            if (category.getCategoryType() == CategoryType.MOUNT) {
                    CategoriesMenu.openFiltered(p, CategoryType.MOUNT);
                } else {
                    CategoriesMenu.open(p);
                }
            return;
        }

        final ItemStack it = e.getCurrentItem();
        if (it == null || it.getType().isAir() || !it.hasItemMeta()) {
            return;
        }

        String tag = PDCTag.get(it.getItemMeta());
        if (tag != null) {
            if (MenuPaginationHelper.handlePagination(it, p,
                    "MCPetsPreviousPage;", "MCPetsNextPage;",
                    category::openInventory)) {
                return;
            }
        }

        final Pet petObject = Pet.getFromIcon(it);
        if (petObject != null) {
            p.closeInventory();
            final Pet pet = petObject.copy();
            pet.spawnWithMessage(p);
            Category.unregisterPlayerView(p);
        }
    }

}
