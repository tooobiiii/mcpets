package fr.nocsy.mcpets.bukkit.data;

import lombok.Getter;

import fr.nocsy.mcpets.bukkit.data.config.Language;

/**
 * Enumeration of the different categories of pets
 */
public enum CategoryType {

    PET(Language.INVENTORY_PETS_MENU),
    MOUNT(Language.INVENTORY_MOUNTS_MENU),
    DEFAULT(Language.CATEGORY_MENU_TITLE);

    @Getter
    private final Language title;

    CategoryType(Language title) {
        this.title = title;
    }

}
