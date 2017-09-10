package org.sapphon.minecraft.modding.minecraftpython;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import org.sapphon.minecraft.modding.base.ClientProxy;
import org.sapphon.minecraft.modding.base.CommonProxy;
import org.sapphon.minecraft.modding.base.DedicatedServerProxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EntityJoinWorldEventHandler {
	
	private CommonProxy proxy;
	public EntityJoinWorldEventHandler(CommonProxy proxy){
		this.proxy = proxy;
	}
	@SubscribeEvent
	public void joinWorldEventUSBCheck(PlayerLoggedInEvent event) {//doesn't work yet
		
	}
}
