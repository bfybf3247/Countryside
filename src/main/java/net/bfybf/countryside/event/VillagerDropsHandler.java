package net.bfybf.countryside.event;

import net.bfybf.countryside.config.CommonConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VillagerDropsHandler {

    @SubscribeEvent
    public static void onMobDropsLoot(LivingDropsEvent event) {
        if (CommonConfig.EnableVillagerDrops()) {
            final LivingEntity entity = event.getEntity();
            Level level = entity.level();
            if (entity instanceof AbstractVillager villager  && !entity.isBaby()) {
                if(CommonConfig.VDRequirePlayer() && event.getSource().getEntity() instanceof Player player) {
                    if ( level.random.nextDouble() < Math.min(CommonConfig.VDTradeDropsChance() + event.getLootingLevel() * CommonConfig.VDLootingBonus() , 1) ) {
                        final MerchantOffers offers = villager.getOffers();
                        int villagerlevel = 0;
                        if(villager instanceof Villager realvillager)
                        {
                             villagerlevel = realvillager.getVillagerData().getLevel() - 1;
                        }
                        int drops =  level.random.nextInt(CommonConfig.VDTradeDropsMax() + villagerlevel * CommonConfig.VDLevelbonus()) + 1;
                        for (int i = 0; i < drops; i++) {
                            if (!offers.isEmpty()) {
                                final MerchantOffer offer = offers.get(level.random.nextInt(offers.size()));
                                if (!offer.isOutOfStock()) {
                                    event.getDrops().add(new ItemEntity(level, villager.getOnPos().getX(), villager.getOnPos().getY(), villager.getOnPos().getZ(), offer.getResult().copy()));
                                }
                            }
                        }
                    }

                    event.getDrops().add(new ItemEntity(level, villager.getOnPos().getX(), villager.getOnPos().getY(), villager.getOnPos().getZ(), new ItemStack(Items.EMERALD, level.random.nextInt(CommonConfig.VDBaseAmount() + 1))));
                }
            }
        }
    }
}
