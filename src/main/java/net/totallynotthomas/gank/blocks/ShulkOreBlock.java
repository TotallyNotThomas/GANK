package net.totallynotthomas.gank.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class ShulkOreBlock extends Block {
    public ShulkOreBlock() {
        super(FabricBlockSettings.of(Material.AGGREGATE).strength(15.0f).slipperiness(1f).collidable(true).breakByHand(false).jumpVelocityMultiplier(1.25f).breakByTool(FabricToolTags.HOES).sounds(BlockSoundGroup.ANVIL));
    }

}