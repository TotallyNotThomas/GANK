package net.totallynotthomas.gank;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.totallynotthomas.gank.armor.GankArmour;
import net.totallynotthomas.gank.blocks.ShulkOreBlock;
import net.totallynotthomas.gank.blocks.GankOreBlock;
import net.totallynotthomas.gank.items.DoubleSquashKnockoffBlackcurrantSquashJuiceBoxItem;
import net.totallynotthomas.gank.items.ShulkBarItem;
import net.totallynotthomas.gank.tools.ShulkToolMaterial;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("GANK");

    //region Config
    public static final ArmorMaterial GANK_ARMOUR_MATERIAL = new GankArmour();
    public static final Item GANK_ARMOUR_CROWN = new ArmorItem(GANK_ARMOUR_MATERIAL, EquipmentSlot.HEAD,new Item.Settings());
    public static final Item GANK_ARMOUR_HOODIE = new ArmorItem(GANK_ARMOUR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings());
    public static final Item GANK_ARMOUR_TROUSERS = new ArmorItem(GANK_ARMOUR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings());
    public static final Item GANK_ARMOUR_SHOES = new ArmorItem(GANK_ARMOUR_MATERIAL, EquipmentSlot.FEET, new Item.Settings());

    public static final Block SHULK_ORE = new ShulkOreBlock();
    public static final Block GANKIUM_ORE = new GankOreBlock();
    public static final Item SHULK_BAR = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item GANKIUM_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    public static ToolItem SHULK_SWORD = new SwordItem(ShulkToolMaterial.INSTANCE, 7, -3F, new Item.Settings().group(ItemGroup.COMBAT));

    public static final Item DOUBLE_SQUASH_KNOCKOFF_BLACKCURRANT_SQUASH_JUICE_BOX = new DoubleSquashKnockoffBlackcurrantSquashJuiceBoxItem(new FabricItemSettings().group(ItemGroup.FOOD));
    public static final Identifier DSKOBS_DRINK_ID = new Identifier("gank:dskobs_drink");
    public static SoundEvent DSKOBS_DRINK_EVENT = new SoundEvent(DSKOBS_DRINK_ID);
    //endregion

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
        LOGGER.info("GANK has initialized!");

        //region DSK-OBSJBI
        Registry.register(Registry.ITEM, new Identifier("gank", "double_squash_knockoff_blackcurrant_squash_juice_box"), DOUBLE_SQUASH_KNOCKOFF_BLACKCURRANT_SQUASH_JUICE_BOX);
        //endregion

        //region Register Shulk Items
        Registry.register(Registry.BLOCK, new Identifier("gank", "shulk_ore"), SHULK_ORE);
        Registry.register(Registry.ITEM, new Identifier("gank", "shulk_ore"), new BlockItem(SHULK_ORE, new FabricItemSettings().group(ItemGroup.MISC)));

        Registry.register(Registry.ITEM, new Identifier("gank", "shulk_bar"), SHULK_BAR);
        Registry.register(Registry.ITEM, new Identifier("gank", "shulk_sword"), SHULK_SWORD);
        //endregion

        //region Register Gank Items
        Registry.register(Registry.ITEM, new Identifier("gank", "gank_crown"), GANK_ARMOUR_CROWN);
        Registry.register(Registry.ITEM, new Identifier("gank", "gank_hoodie"), GANK_ARMOUR_HOODIE);
        Registry.register(Registry.ITEM, new Identifier("gank", "gank_trousers"), GANK_ARMOUR_TROUSERS);
        Registry.register(Registry.ITEM, new Identifier("gank", "gank_shoes"), GANK_ARMOUR_SHOES);

        Registry.register(Registry.BLOCK, new Identifier("gank", "gankium_ore"), GANKIUM_ORE);
        Registry.register(Registry.ITEM, new Identifier("gank", "gankium_ingot"), GANKIUM_INGOT);
        Registry.register(Registry.ITEM, new Identifier("gank", "gankium_ore"), new BlockItem(GANKIUM_ORE, new FabricItemSettings().group(ItemGroup.MISC)));
        //endregion

        //region Generate Shulk Ore
        RegistryKey<ConfiguredFeature<?, ?>> GankOreEnd = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("gank", "shulk_ore_end"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, GankOreEnd.getValue(), GEN_SHULK_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, GankOreEnd);
        //endregion

        //region Register DSKOBS Stuff
        Registry.register(Registry.SOUND_EVENT, Main.DSKOBS_DRINK_ID, DSKOBS_DRINK_EVENT);
        //endregion
    }
}
