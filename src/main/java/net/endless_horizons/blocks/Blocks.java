package net.endless_horizons.blocks;

import net.endless_horizons.blocks.end_gateway.EndlessEndBlock;
import net.endless_horizons.blocks.sky.EndlessSkyBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.EnumMap;

import static net.endless_horizons.EndlessHorizons.MOD_ID;


public class Blocks {
    private static final EnumMap<DyeColor, Block> coloredBlocks = new EnumMap<>(DyeColor.class);
    public static final TagKey<Block> NO_OUTLINE = TagKey.of(RegistryKeys.BLOCK, new Identifier(MOD_ID, "no_outline"));
    private static final Settings blockSettings = FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).luminance(15).requiresTool();

    public static final Block EndlessEndBlock = new EndlessEndBlock(blockSettings);
    public static final Block EndlessSkyBlock = new EndlessSkyBlock(blockSettings);
    //public static final Block EndlessWhiteButton = buttonBlock();
    //public static final Block EndlessOrangeButton = buttonBlock();
    //public static final Block EndlessMagentaButton = buttonBlock();
    //public static final Block EndlessLightBlueButton = buttonBlock();
    //public static final Block EndlessYellowButton = buttonBlock();
    //public static final Block EndlessLimeButton = buttonBlock();
    //public static final Block EndlessPinkButton = buttonBlock();
    //public static final Block EndlessGrayButton = buttonBlock();
    //public static final Block EndlessLightGrayButton = buttonBlock();
    //public static final Block EndlessCyanButton = buttonBlock();
    //public static final Block EndlessPurpleButton = buttonBlock();
    //public static final Block EndlessBlueButton = buttonBlock();
    //public static final Block EndlessBrownButton = buttonBlock();
    //public static final Block EndlessGreenButton = buttonBlock();
    //public static final Block EndlessRedButton = buttonBlock();
    //public static final Block EndlessBlackButton = buttonBlock();
    //public static final Block EndlessWhitePressurePlate = pressurePlateBlock();
    //public static final Block EndlessOrangePressurePlate = pressurePlateBlock();
    //public static final Block EndlessMagentaPressurePlate = pressurePlateBlock();
    //public static final Block EndlessLightBluePressurePlate = pressurePlateBlock();
    //public static final Block EndlessYellowPressurePlate = pressurePlateBlock();
    //public static final Block EndlessLimePressurePlate = pressurePlateBlock();
    //public static final Block EndlessPinkPressurePlate = pressurePlateBlock();
    //public static final Block EndlessGrayPressurePlate = pressurePlateBlock();
    //public static final Block EndlessLightGrayPressurePlate = pressurePlateBlock();
    //public static final Block EndlessCyanPressurePlate = pressurePlateBlock();
    //public static final Block EndlessPurplePressurePlate = pressurePlateBlock();
    //public static final Block EndlessBluePressurePlate = pressurePlateBlock();
    //public static final Block EndlessBrownPressurePlate = pressurePlateBlock();
    //public static final Block EndlessGreenPressurePlate = pressurePlateBlock();
    //public static final Block EndlessRedPressurePlate = pressurePlateBlock();
    //public static final Block EndlessBlackPressurePlate = pressurePlateBlock();


    private static final ItemGroup endlessHorizonsGroup = FabricItemGroup.builder(new Identifier(MOD_ID, "endless_blocks"))
            .icon(() -> new ItemStack(coloredBlocks.get(DyeColor.WHITE)))
            .build();

    //private static Block buttonBlock() {
    //    return new EndlessButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).noCollision().luminance(15));
    //}

    //private static Block pressurePlateBlock() {
    //    return new EndlessPressurePlate(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.of(Material.STONE)
    //            .strength(0.5f).noCollision().luminance(15).requiresTool());
    //}


    private static void register(Block block, String name) {
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "endless_" + name), block);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "endless_" + name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(endlessHorizonsGroup).register(content -> content.add(block));
    }

    public static void register() {
        for (var color : DyeColor.values()) {
            Block block = new Block(blockSettings.mapColor(color.getMapColor()));
            register(block, color.getName());
            coloredBlocks.put(color, block);
        }

        register(EndlessEndBlock, "end");
        register(EndlessSkyBlock, "sky");

        for (var color : DyeColor.values()) {
            register(new DoorBlock(blockSettings.mapColor(color.getMapColor()), BlockSetType.STONE), color.getName() + "_door");
        }

        for (var color : DyeColor.values()) {
            register(new TrapdoorBlock(blockSettings.mapColor(color.getMapColor()), BlockSetType.STONE), color.getName() + "_trapdoor");
        }
    }
}
