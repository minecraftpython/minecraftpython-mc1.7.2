package org.sapphon.minecraft.modding.minecraftpython.command;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;

public class ClientTickHandler {
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event){
		if(event.phase.compareTo(Phase.END) == 0){
			CommandQueueClientSide.SINGLETON().runAndClearScheduledCommands();
		}
	}
}
