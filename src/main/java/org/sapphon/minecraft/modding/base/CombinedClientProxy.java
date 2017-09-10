package org.sapphon.minecraft.modding.base;

import org.sapphon.minecraft.modding.minecraftpython.command.ClientTickHandler;
import org.sapphon.minecraft.modding.minecraftpython.command.ServerTickHandler;

import cpw.mods.fml.common.FMLCommonHandler;

public class CombinedClientProxy extends CommonProxy {
	public CombinedClientProxy() {
		FMLCommonHandler.instance().bus()
				.register(new ClientTickHandler());
		FMLCommonHandler.instance().bus().register(new ServerTickHandler());
	}
}