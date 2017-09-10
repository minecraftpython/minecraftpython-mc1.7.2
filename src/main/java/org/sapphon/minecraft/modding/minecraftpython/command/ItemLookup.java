package org.sapphon.minecraft.modding.minecraftpython.command;

import org.sapphon.minecraft.modding.techmage.ArcaneArmory;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.world.WorldServer;

public class ItemLookup {
	public static Item getItemByName(String name, WorldServer worldserver) {
		Object defaultResult = Item.itemRegistry.getObject(name.toLowerCase());
		if(defaultResult != null){
			return (Item)defaultResult;
		}else if(ArcaneArmory.SINGLETON().hasWandWithSpellNamed(name)){
			return ArcaneArmory.SINGLETON().getWandBySpellName(name);
		}
		return Items.boat;
		
	}
}
