package net.bfybf.countryside.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class CommonConfig {

    private static ForgeConfigSpec.BooleanValue enable_villager_drops;

    private static ForgeConfigSpec.BooleanValue VD_require_player;

    private static ForgeConfigSpec.IntValue VD_base_amount;

    private static ForgeConfigSpec.DoubleValue VD_trade_drops_chance;

    private static ForgeConfigSpec.DoubleValue VD_looting_bonus;

    private static ForgeConfigSpec.IntValue VD_trade_drops_max;

    private static ForgeConfigSpec.IntValue VD_level_bonus;


    public static boolean EnableVillagerDrops() {
        return enable_villager_drops.get();
    }

    public static boolean VDRequirePlayer(){
        return VD_require_player.get();
    }

    public static int VDBaseAmount() {
        return VD_base_amount.get();
    }

    public static double VDTradeDropsChance() {
        return VD_trade_drops_chance.get();
    }

    public static double VDLootingBonus() {
        return VD_looting_bonus.get();
    }

    public static int VDTradeDropsMax() {
        return VD_trade_drops_max.get();
    }


    public static int VDLevelbonus() {
        return VD_level_bonus.get();
    }



    public CommonConfig(ForgeConfigSpec.Builder builder){
        builder.comment("General settings for the countryside mod.");
        builder.push("Villager drops");

        enable_villager_drops = builder.define("enable-villager-drops", true);

        VD_require_player = builder.define("player-required", true);

        VD_base_amount = builder.comment("The maximum drops of emerald as basic loot.").defineInRange("base-max-drops", 2, 0, 2147483647);

        VD_trade_drops_chance = builder.comment("The basic chance should villager drop their trade items").defineInRange("trade-drops-chance",0.25,0,1);

        VD_looting_bonus = builder.comment("The additional chance bonus that one level of looting adds").defineInRange("trade-looting-bonus",0.05,0,1);

        VD_trade_drops_max = builder.comment("The maximum drops should villager drop their trade items").defineInRange("trade-max-drops", 2, 0, 2147483647);

        VD_level_bonus = builder.comment("The additional drops bonus that one level of villager level adds").defineInRange("trade-level-bonus", 2, 0, 2147483647);

        builder.pop();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, builder.build());
    }
}
