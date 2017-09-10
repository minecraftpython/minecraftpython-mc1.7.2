package org.sapphon.minecraft.modding.base;

import org.sapphon.minecraft.modding.minecraftpython.command.ServerTickHandler;

import cpw.mods.fml.common.FMLCommonHandler;

public class DedicatedServerProxy extends CommonProxy {
	public DedicatedServerProxy(){
		FMLCommonHandler.instance().bus().register(new ServerTickHandler());
	}
}
