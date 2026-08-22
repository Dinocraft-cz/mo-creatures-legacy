package drzhark.mocreatures.achievements;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MoCAchievementEvents {
	
	private boolean isWyvernEgg(ItemStack itemStack) //test if itemStack is a wyvern egg  (mocegg ID: 50-61)
    {
		if (itemStack != null && itemStack.getItem() == MoCreatures.mocegg)
		{
			return (49 < itemStack.getItemDamage() && itemStack.getItemDamage() < 62);
		}
		
		return false;
    }
	
	@SubscribeEvent
	public void MoCItemPickupEvent(PlayerEvent.ItemPickupEvent event)
	{
		if (event.pickedUp == null || event.pickedUp.getEntityItem() == null) return;
		Item item = event.pickedUp.getEntityItem().getItem();
		if (item == null) return;

		if (item == MoCreatures.heartundead) {event.player.addStat(MoCAchievements.heart_undead, 1);}
		else if (item == MoCreatures.heartDarkness) {event.player.addStat(MoCAchievements.heart_darkness, 1);}
		else if (item == MoCreatures.heartFire) {event.player.addStat(MoCAchievements.heart_fire, 1);}
		else if (isWyvernEgg(event.pickedUp.getEntityItem())) {event.player.addStat(MoCAchievements.wyvern_egg, 1);}
		else if (item == MoCreatures.bigcatClaw) {event.player.addStat(MoCAchievements.big_cat_claw, 1);}
		else if (item == MoCreatures.sharkTeeth) {event.player.addStat(MoCAchievements.shark_tooth, 1);}
		else if (item == MoCreatures.katana) {event.player.addStat(MoCAchievements.leonardo, 1);}
		else if (item == MoCreatures.sai) {event.player.addStat(MoCAchievements.raphael, 1);}
		else if (item == MoCreatures.bo) {event.player.addStat(MoCAchievements.donatello, 1);}
		else if (item == MoCreatures.nunchaku) {event.player.addStat(MoCAchievements.michelangelo, 1);}
		else if (item == MoCreatures.silverSword) {event.player.addStat(MoCAchievements.silver_sword, 1);}
		else if (item == MoCreatures.fur) {event.player.addStat(MoCAchievements.get_fur, 1);}
		else if (item == MoCreatures.hide) {event.player.addStat(MoCAchievements.get_hide, 1);}
		else if (item == MoCreatures.hideReptile) {event.player.addStat(MoCAchievements.get_reptile_hide, 1);}
		else if (
				item == MoCreatures.chitin
				|| item == MoCreatures.chitinCave
				|| item == MoCreatures.chitinFrost
				|| item == MoCreatures.chitinNether
				|| item == MoCreatures.scorpStingDirt
				|| item == MoCreatures.scorpStingCave
				|| item == MoCreatures.scorpStingFrost
				|| item == MoCreatures.scorpStingNether
			) {event.player.addStat(MoCAchievements.get_scorpion_material, 1);}
		else if (item == MoCreatures.recordShuffle) {event.player.addStat(MoCAchievements.zebra_record, 1);}
	}
	
	@SubscribeEvent
	public void MoCItemCraftedEvent(PlayerEvent.ItemCraftedEvent event)
	{
		if (event.crafting == null) return;
		Item item = event.crafting.getItem();
		if (item == null) return;

		if (item == MoCreatures.craftedSaddle) {event.player.addStat(MoCAchievements.craft_saddle, 1);}
		else if (item == MoCreatures.essenceUndead) {event.player.addStat(MoCAchievements.essence_undead, 1);}
		else if (item == MoCreatures.amuletBone) {event.player.addStat(MoCAchievements.amulet_bone, 1);}
		else if (item == MoCreatures.amuletGhost) {event.player.addStat(MoCAchievements.amulet_ghost, 1);}
		else if (item == MoCreatures.essenceDarkness) {event.player.addStat(MoCAchievements.essence_darkness, 1);}
		else if (item == MoCreatures.horseArmorCrystal) {event.player.addStat(MoCAchievements.crystal_horse_armor, 1);}
		else if (item == MoCreatures.essenceFire) {event.player.addStat(MoCAchievements.essence_fire, 1);}
		else if (item == MoCreatures.essenceLight)
		{
			event.player.addStat(MoCAchievements.essence_light, 1);
			event.player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle, 2)); //give the player back 2 bottles
		}
		else if (item == MoCreatures.amuletFairy) {event.player.addStat(MoCAchievements.amulet_fairy, 1);}
		else if (item == MoCreatures.amuletPegasus) {event.player.addStat(MoCAchievements.amulet_sky, 1);}
		else if (item == MoCreatures.medallion) {event.player.addStat(MoCAchievements.craft_medallion, 1);}
		else if (item == MoCreatures.litterbox) {event.player.addStat(MoCAchievements.kitty_litter_box, 1);}
		else if (item == MoCreatures.kittybed) {event.player.addStat(MoCAchievements.kitty_bed, 1);}
		else if (item == MoCreatures.woolball) {event.player.addStat(MoCAchievements.wool_ball, 1);}
		else if (item == MoCreatures.whip) {event.player.addStat(MoCAchievements.craft_whip, 1);}
		else if (item == MoCreatures.fishNet) {event.player.addStat(MoCAchievements.fish_net, 1);}
		else if (item == MoCreatures.sharkSword) {event.player.addStat(MoCAchievements.shark_sword, 1);}
		else if (item == MoCreatures.petAmulet) {event.player.addStat(MoCAchievements.pet_amulet, 1);}
		else if (item == MoCreatures.turtleSoup) {event.player.addStat(MoCAchievements.cook_turtle, 1);}
		else if (item == MoCreatures.ratBurger) {event.player.addStat(MoCAchievements.rat_burger, 1);}
		else if (
				item == MoCreatures.helmetFur
				|| item == MoCreatures.chestFur
				|| item == MoCreatures.legsFur
				|| item == MoCreatures.bootsFur
			) {event.player.addStat(MoCAchievements.fur_armor, 1);}
		else if (
				item == MoCreatures.helmetHide
				|| item == MoCreatures.chestHide
				|| item == MoCreatures.legsHide
				|| item == MoCreatures.bootsHide
			) {event.player.addStat(MoCAchievements.hide_armor, 1);}
		else if (
				item == MoCreatures.helmetReptile
				|| item == MoCreatures.plateReptile
				|| item == MoCreatures.legsReptile
				|| item == MoCreatures.bootsReptile
			) {event.player.addStat(MoCAchievements.reptile_armor, 1);}
		else if (
				item == MoCreatures.scorpSwordDirt
				|| item == MoCreatures.scorpSwordCave
				|| item == MoCreatures.scorpSwordFrost
				|| item == MoCreatures.scorpSwordNether
			) {event.player.addStat(MoCAchievements.scorpion_sword, 1);}
		else if (
				item == MoCreatures.scorpHelmetDirt
				|| item == MoCreatures.scorpPlateDirt
				|| item == MoCreatures.scorpLegsDirt
				|| item == MoCreatures.scorpBootsDirt
				|| item == MoCreatures.scorpHelmetCave
				|| item == MoCreatures.scorpPlateCave
				|| item == MoCreatures.scorpLegsCave
				|| item == MoCreatures.scorpBootsCave
				|| item == MoCreatures.scorpHelmetFrost
				|| item == MoCreatures.scorpPlateFrost
				|| item == MoCreatures.scorpLegsFrost
				|| item == MoCreatures.scorpBootsFrost
				|| item == MoCreatures.scorpHelmetNether
				|| item == MoCreatures.scorpPlateNether
				|| item == MoCreatures.scorpLegsNether
				|| item == MoCreatures.scorpBootsNether
			) {event.player.addStat(MoCAchievements.scorpion_armor, 1);}
	}
	
	@SubscribeEvent
	public void MoCItemSmeltedEvent(PlayerEvent.ItemSmeltedEvent event)
	{
		if (event.smelting == null) return;
		Item item = event.smelting.getItem();
		if (item == null) return;

		if (item == MoCreatures.omelet) {event.player.addStat(MoCAchievements.cook_omelette, 1);}
		else if (item == MoCreatures.turkeyCooked) {event.player.addStat(MoCAchievements.cook_turkey, 1);}
		else if (item == MoCreatures.ostrichCooked) {event.player.addStat(MoCAchievements.cook_ostrich, 1);}
		else if (item == MoCreatures.ratCooked) {event.player.addStat(MoCAchievements.cook_rat, 1);}
		else if (item == MoCreatures.crabCooked) {event.player.addStat(MoCAchievements.cook_crab, 1);}
	}
}
