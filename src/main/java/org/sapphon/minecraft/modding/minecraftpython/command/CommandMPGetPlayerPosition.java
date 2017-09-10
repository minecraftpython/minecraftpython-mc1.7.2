package org.sapphon.minecraft.modding.minecraftpython.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandMPGetPlayerPosition{
	
	private String nameOfPlayer = "";
	
	public CommandMPGetPlayerPosition(){
		
	}
	public CommandMPGetPlayerPosition(String playerNameToGetPositionFor){
		nameOfPlayer = playerNameToGetPositionFor;
	}
	
	public int[] execute(){
		EntityPlayer player = getCorrectPlayer();
		return new int[]{
			(int)Math.round(player.posX),
			(int)Math.round(player.posY),
			(int)Math.round(player.posZ)
		};
	}
	
	private EntityPlayer getCorrectPlayer() {
		if(nameOfPlayer.equals("")){
			 return Minecraft.getMinecraft().thePlayer;
		}
		else{
			EntityPlayer possibleAnswer = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(nameOfPlayer);//Note this compares by getCommandSenderName whereas GameStart uses DisplayNames.  Never been a problem...yet.
			if(possibleAnswer == null){
				JavaProblemHandler.printErrorMessageToDialogBox(new Exception("Problem finding player " + this.nameOfPlayer +  " by name.  Are you sure that player exists on this server?"));
				possibleAnswer = Minecraft.getMinecraft().thePlayer;	//this isn't great behavior; we're just returning the only player we know for sure we have.  TODO would be implementing a DoNothingPlayerEntity to return.
			}
			return possibleAnswer;
		}
	}
}
