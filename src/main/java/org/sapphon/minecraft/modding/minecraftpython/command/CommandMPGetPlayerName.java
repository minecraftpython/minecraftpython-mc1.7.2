package org.sapphon.minecraft.modding.minecraftpython.command;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class CommandMPGetPlayerName {
	public String execute(){
		return Minecraft.getMinecraft().thePlayer.getDisplayName();
	}
	
}
