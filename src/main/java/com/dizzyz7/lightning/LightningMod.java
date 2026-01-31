package com.dizzyz7.lightning;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;

public class LightningMod implements ModInitializer {
    
    // Алмазный меч дает +7 урона. Нам вообщем я решил и нужно +8 (на 1 больше), 
    // но через Callback мы просто проверим предмет и добавим магический урон.
    
    @Override
    public void onInitialize() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);
            
            // Проверяем: это сервер и в руках наш "Меч Громовержца" (пусть будет Незеритовый для солидности)
            if (!world.isClient && stack.isOf(Items.NETHERITE_SWORD) && entity instanceof LivingEntity target) {
                
                // 1. Проверка кулдауна, чтобы не спамить
                if (player.getAttackCooldownProgress(0f) >= 0.9f) {
                    ServerWorld serverWorld = (ServerWorld) world;

                    // 2. Создаем молнию
                    LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                    if (lightning != null) {
                        lightning.refreshPositionAfterTeleport(target.getX(), target.getY(), target.getZ());
                        
                        // Указываем, что молния "косметическая" (не поджигает блоки), 
                        // а урон нанесем программно, чтобы он был ровно 10 сердец
                        lightning.setCosmetic(true); 
                        serverWorld.spawnEntity(lightning);
                    }

                    // 3. Наносим урон цели (20 единиц = 10 сердец)
                    // bypassesArmor() гарантирует, что это чистый урон от молнии
                    target.damage(world.getDamageSources().lightningBolt(), 20.0f);

                    // 4. Добавляем +1 урона сверху (разница между Незеритом/Алмазом и нашей задумкой)
                    target.damage(world.getDamageSources().playerAttack(player), 1.0f);

                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }
}
