package org.sapphon.minecraft.modding.minecraftpython.command;

import ibxm.Player;

import java.util.ArrayList;
import java.util.List;

import org.sapphon.minecraft.modding.base.BlockFinder;
import org.sapphon.minecraft.modding.base.MinecraftPythonVec3;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.PythonProblemHandler;

import cpw.mods.fml.common.network.handshake.NetworkDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CommandMPTeleport extends CommandMPServer {

	public double x;
	public double y;
	public double z;
	public String teleportingPlayer;

	public CommandMPTeleport(double x, double y, double z){
		this(x,y,z, Minecraft.getMinecraft().thePlayer.getDisplayName());
	}
	
	public CommandMPTeleport(double x, double y, double z, String teleportingPlayerDisplayName){
		this.x = x;
		this.y = y;
		this.z = z;
		this.teleportingPlayer = teleportingPlayerDisplayName;
	}
	
	public CommandMPTeleport(String[] commandAndArgsToDeserialize) {
		this(Double.parseDouble(commandAndArgsToDeserialize[1]),
				Double.parseDouble(commandAndArgsToDeserialize[2]),
				Double.parseDouble(commandAndArgsToDeserialize[3]), commandAndArgsToDeserialize[4]);
	}


	public void doWork(){
		WorldServer world = MinecraftServer.getServer().worldServerForDimension(0);
		List<EntityPlayerMP> players = new ArrayList<EntityPlayerMP>((List<EntityPlayerMP>) world.playerEntities);	//I have never seen a ConcurrentModException with this guy but why risk it?
		for (EntityPlayerMP entityPlayerMP : players) {
			if(entityPlayerMP.getDisplayName().equals(teleportingPlayer)){
				entityPlayerMP.playerNetServerHandler.setPlayerLocation(x, y, z, entityPlayerMP.rotationYaw, entityPlayerMP.rotationPitch);
				return;
			}
		}
	}
	
	@Override
	public String serialize() {
		return CommandMPServer.TELEPORT_NAME + CommandMPAbstract.SERIAL_DIV + x + CommandMPAbstract.SERIAL_DIV + y + CommandMPAbstract.SERIAL_DIV + z + CommandMPAbstract.SERIAL_DIV + teleportingPlayer;
	}
	
	
}