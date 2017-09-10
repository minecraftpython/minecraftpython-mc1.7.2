package org.sapphon.minecraft.modding.minecraftpython.command;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.Vec3;

public class CommandMPGetPlayerLookVector {
	public double[] execute(){
		Minecraft minecraft = Minecraft.getMinecraft();
		Vec3 lookVector = minecraft.thePlayer.getLook(1.0f);
		return new double[]{
			lookVector.xCoord, 
			lookVector.yCoord, 
			lookVector.zCoord
		};
	}
}
