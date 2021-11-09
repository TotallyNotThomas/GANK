package net.totallynotthomas.gank.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Random;

import static net.totallynotthomas.gank.Main.DSKOBS_DRINK_EVENT;

public class DoubleSquashKnockoffBlackcurrantSquashJuiceBoxItem extends Item {
    Random rand = new Random();
    public DoubleSquashKnockoffBlackcurrantSquashJuiceBoxItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity PlayerEntity, Hand hand) {
        PlayerEntity.playSound(DSKOBS_DRINK_EVENT, SoundCategory.VOICE, 1.0F, 1.0F);
        return new TypedActionResult<>(ActionResult.SUCCESS, PlayerEntity.getStackInHand(hand));
    }
}
