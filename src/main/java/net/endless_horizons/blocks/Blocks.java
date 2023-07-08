package net.endless_horizons.blocks;

import net.endless_horizons.blocks.end.EndlessEndBlock;
import net.endless_horizons.blocks.sky.EndlessSkyBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import java.util.EnumMap;

import static net.endless_horizons.EndlessHorizons.MOD_ID;


public class Blocks {
    public static final EnumMap<DyeColor, Block> coloredBlocks = new EnumMap<>(DyeColor.class);
    public static final TagKey<Block> NO_OUTLINE = TagKey.of(RegistryKeys.BLOCK, new Identifier(MOD_ID, "no_outline"));
    private static final Settings blockSettings = FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).luminance(15).requiresTool();

    public static final EndlessEndBlock EndlessEndBlock = new EndlessEndBlock(blockSettings);
    public static final EndlessSkyBlock EndlessSkyBlock = new EndlessSkyBlock(blockSettings);

    private static final ItemGroup endlessHorizonsGroup = FabricItemGroup.builder(new Identifier(MOD_ID, "endless_blocks"))
            .icon(() -> new ItemStack(coloredBlocks.get(DyeColor.WHITE)))
            .build();

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
        for (var color : DyeColor.values()) {
            register(new ButtonBlock(Settings.of(Material.DECORATION).noCollision().strength(0.5F), BlockSetType.STONE, 20, false), color.getName() + "_button");
        }
        for (var color : DyeColor.values()) {
            register(new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, Settings.of(Material.STONE).requiresTool().noCollision().strength(0.5F), BlockSetType.STONE), color.getName() + "_pressure_plate");
        }
    }
}
