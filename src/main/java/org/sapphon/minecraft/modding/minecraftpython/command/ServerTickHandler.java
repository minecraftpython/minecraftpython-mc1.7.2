package org.sapphon.minecraft.modding.minecraftpython.command;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class ServerTickHandler {
	@SubscribeEvent
	public void onServerTick(ServerTickEvent event){
		if(event.phase.compareTo(Phase.END) == 0){
			CommandQueueServerSide.SINGLETON().runAndClearScheduledCommands();
		}
	}
}
