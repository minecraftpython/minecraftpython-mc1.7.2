package org.sapphon.minecraft.modding.base;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.registry.GameData;

public class BlockFinder {
	public static Block getBlockWithName(String name){
		Block registryResult = GameData.blockRegistry.get(name.toLowerCase());
		return registryResult == null ? Blocks.dirt : registryResult;
	}

}
