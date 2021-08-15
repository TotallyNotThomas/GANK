package net.totallynotthomas.gank;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.totallynotthomas.gank.blocks.ShulkOreBlock;

public class Main implements ModInitializer {
    public static final Block SHULK_ORE = new ShulkOreBlock();
    public static final Item SHULK_BAR = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    private static ConfiguredFeature<?, ?> GEN_SHULK_ORE = Feature.ORE
            .configure(new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.END_STONE), // Base block is end stone in The End biomes
                    Main.SHULK_ORE.getDefaultState(),
                    9))
            .range(new RangeDecoratorConfig(
                    UniformHeightProvider.create(YOffset.fixed(0), YOffset.fixed(64))))
            .spreadHorizontally()
            .repeat(20);

    @Override
    public void onInitialize() {
        System.out.println("GANK has initialized!");
        Registry.register(Registry.BLOCK, new Identifier("gank", "shulk_ore"), SHULK_ORE);
        Registry.register(Registry.ITEM, new Identifier("gank", "shulk_ore"), new BlockItem(SHULK_ORE, new Item.Settings().group(ItemGroup.MATERIALS)));
        Registry.register(Registry.ITEM, new Identifier("gank", "shulk_bar"), SHULK_BAR);
    }
}
