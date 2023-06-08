package net.endless_horizons.blocks;

import net.endless_horizons.blocks.end_gateway.EndlessEndBlock;
import net.endless_horizons.blocks.end_gateway.EndlessEndEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.endless_horizons.EndlessHorizons.MOD_ID;


public class Blocks {
    public static final Block EndlessWhite = block();
    public static final Block EndlessOrange = block();
    public static final Block EndlessMagenta = block();
    public static final Block EndlessLightBlue = block();
    public static final Block EndlessYellow = block();
    public static final Block EndlessLime = block();
    public static final Block EndlessPink = block();
    public static final Block EndlessGray = block();
    public static final Block EndlessLightGray = block();
    public static final Block EndlessCyan = block();
    public static final Block EndlessPurple = block();
    public static final Block EndlessBlue = block();
    public static final Block EndlessBrown = block();
    public static final Block EndlessGreen = block();
    public static final Block EndlessRed = block();
    public static final Block EndlessBlack = block();
    public static final Block EndlessEndBlock = new EndlessEndBlock(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).luminance(15).requiresTool());
    //public static final Block EndlessWhiteDoor = doorBlock();
    //public static final Block EndlessOrangeDoor = doorBlock();
    //public static final Block EndlessMagentaDoor = doorBlock();
    //public static final Block EndlessLightBlueDoor = doorBlock();
    //public static final Block EndlessYellowDoor = doorBlock();
    //public static final Block EndlessLimeDoor = doorBlock();
    //public static final Block EndlessPinkDoor = doorBlock();
    //public static final Block EndlessGrayDoor = doorBlock();
    //public static final Block EndlessLightGrayDoor = doorBlock();
    //public static final Block EndlessCyanDoor = doorBlock();
    //public static final Block EndlessPurpleDoor = doorBlock();
    //public static final Block EndlessBlueDoor = doorBlock();
    //public static final Block EndlessBrownDoor = doorBlock();
    //public static final Block EndlessGreenDoor = doorBlock();
    //public static final Block EndlessRedDoor = doorBlock();
    //public static final Block EndlessBlackDoor = doorBlock();
    //public static final Block EndlessWhiteTrapdoor = trapdoorBlock();
    //public static final Block EndlessOrangeTrapdoor = trapdoorBlock();
    //public static final Block EndlessMagentaTrapdoor = trapdoorBlock();
    //public static final Block EndlessLightBlueTrapdoor = trapdoorBlock();
    //public static final Block EndlessYellowTrapdoor = trapdoorBlock();
    //public static final Block EndlessLimeTrapdoor = trapdoorBlock();
    //public static final Block EndlessPinkTrapdoor = trapdoorBlock();
    //public static final Block EndlessGrayTrapdoor = trapdoorBlock();
    //public static final Block EndlessLightGrayTrapdoor = trapdoorBlock();
    //public static final Block EndlessCyanTrapdoor = trapdoorBlock();
    //public static final Block EndlessPurpleTrapdoor = trapdoorBlock();
    //public static final Block EndlessBlueTrapdoor = trapdoorBlock();
    //public static final Block EndlessBrownTrapdoor = trapdoorBlock();
    //public static final Block EndlessGreenTrapdoor = trapdoorBlock();
    //public static final Block EndlessRedTrapdoor = trapdoorBlock();
    //public static final Block EndlessBlackTrapdoor = trapdoorBlock();
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


    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "endless_blocks"))
            .icon(() -> new ItemStack(EndlessWhite))
            .build();

    private static Block block() {
        return new Block(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).luminance(15).requiresTool());
    }

    //private static Block doorBlock() {
    //    return new EndlessDoor(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).luminance(15));
    //}

    //private static Block trapdoorBlock() {
    //    return new EndlessTrapdoor(FabricBlockSettings.of(Material.STONE).strength(1.5f, 6.0f).luminance(15));
    //}

    //private static Block buttonBlock() {
    //    return new EndlessButton(FabricBlockSettings.of(Material.DECORATION).strength(0.5f).noCollision().luminance(15));
    //}

    //private static Block pressurePlateBlock() {
    //    return new EndlessPressurePlate(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.of(Material.STONE)
    //            .strength(0.5f).noCollision().luminance(15).requiresTool());
    //}

    public static void register(Block block, String name) {
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "endless_" + name), block);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "endless_" + name), new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(content -> content.add(block));
    }

    public static void register() {
        register(EndlessWhite, "white");
        register(EndlessOrange, "orange");
        register(EndlessMagenta, "magenta");
        register(EndlessLightBlue, "light_blue");
        register(EndlessYellow, "yellow");
        register(EndlessLime, "lime");
        register(EndlessPink, "pink");
        register(EndlessGray, "gray");
        register(EndlessLightGray, "light_gray");
        register(EndlessCyan, "cyan");
        register(EndlessPurple, "purple");
        register(EndlessBlue, "blue");
        register(EndlessBrown, "brown");
        register(EndlessGreen, "green");
        register(EndlessRed, "red");
        register(EndlessBlack, "black");
        register(EndlessEndBlock, "end");
    }
}
