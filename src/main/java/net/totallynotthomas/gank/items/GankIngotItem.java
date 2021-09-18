package net.totallynotthomas.gank.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class GankIngotItem extends Item {
    public GankIngotItem() {
        super(new FabricItemSettings().group(ItemGroup.MATERIALS));
    }

}