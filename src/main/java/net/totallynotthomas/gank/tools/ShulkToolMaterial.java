package net.totallynotthomas.gank.tools;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class ShulkToolMaterial implements ToolMaterial {

    @Override
    public int getDurability() {
        return 1800;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    public static final ShulkToolMaterial INSTANCE = new ShulkToolMaterial();

}
