package net.totallynotthomas.gank;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.totallynotthomas.gank.blocks.ShulkOreBlock;
import net.totallynotthomas.gank.fluids.KnockOffJuice;
import net.totallynotthomas.gank.blocks.GankOreBlock;

import static net.fabricmc.fabric.impl.networking.NetworkingImpl.MOD_ID;

public class Main implements ModInitializer {
    public static final Block SHULK_ORE = new ShulkOreBlock();
    public static final Block GANK_ORE = new GankOreBlock();
    public static final Item SHULK_BAR = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item GANK_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static FlowableFluid STILL_DOUBLE_SQUASH_KNOCKOFF_JUICE;
    public static FlowableFluid FLOWING_DOUBLE_SQUASH_KNOCKOFF_JUICE;
    public static Item KNOCKOFF_DOUBLE_SQUASH_JUICE_BUCKET;

    //region Configure Shulk Ore generation
    private static final ConfiguredFeature<?, ?> GEN_SHULK_ORE = Feature.ORE
            .configure(new OreFeatureConfig(
                    new BlockMatchRuleTest(Blocks.END_STONE), // Base block is end stone in The End biomes
                    Main.SHULK_ORE.getDefaultState(),
                    4))
            .range(new RangeDecoratorConfig(
                    UniformHeightProvider.create(YOffset.fixed(20), YOffset.fixed(25))))
            .spreadHorizontally()
            .repeat(4); // Veins per chunk
    //endregion

    @Override
    public void onInitialize() {
        System.out.println("GANK has initialized!");

        Registry.register(Registry.BLOCK, new Identifier("gank", "shulk_ore"), SHULK_ORE);
        Registry.register(Registry.BLOCK, new Identifier("gank", "gank_ore"), GANK_ORE);

        Registry.register(Registry.ITEM, new Identifier("gank", "shulk_bar"), SHULK_BAR);
        Registry.register(Registry.ITEM, new Identifier("gank", "gank_ingot"), GANK_INGOT);

        Registry.register(Registry.ITEM, new Identifier("gank", "gank_ore"), new BlockItem(GANK_ORE, new FabricItemSettings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("gank", "shulk_ore"), new BlockItem(SHULK_ORE, new FabricItemSettings().group(ItemGroup.MISC)));

        //region Knock Off Juice Stuff
        STILL_DOUBLE_SQUASH_KNOCKOFF_JUICE = Registry.register(Registry.FLUID, new Identifier(MOD_ID, "still_double_squash_knockoff_juice"), new KnockOffJuice());
        FLOWING_DOUBLE_SQUASH_KNOCKOFF_JUICE = Registry.register(Registry.FLUID, new Identifier(MOD_ID, "flowing_double_squash_knockoff_juice"), new KnockOffJuice());
        KNOCKOFF_DOUBLE_SQUASH_JUICE_BUCKET = Registry.register(Registry.ITEM, new Identifier(MOD_ID, "knockoff_double_squash_juice_bucket"),
        new BucketItem(STILL_DOUBLE_SQUASH_KNOCKOFF_JUICE, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
        // What have I made lol
        //endregion

        RegistryKey<ConfiguredFeature<?, ?>> GankOreEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("gank", "shulk_ore_end"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, GankOreEnd.getValue(), GEN_SHULK_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, GankOreEnd);
    }
}
