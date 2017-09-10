package org.sapphon.minecraft.modding.techmage.wands;

import java.util.Random;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WandCreativeTab extends CreativeTabs {

	public WandCreativeTab(String tabLabel) {
		super(tabLabel);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
//		return ScriptLoader.SINGLETON.getWands().get(new Random().nextInt() % ScriptLoader.SINGLETON.getWands().size());	//TODO circular reference
		return Items.blaze_rod;
	}

}