package org.sapphon.minecraft.modding.minecraftpython.command;

import org.sapphon.minecraft.modding.base.MinecraftPythonVec3;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.command.ICommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommandMPCreateExplosion extends CommandMPServer {

	public int x;
	public int y;
	public int z;
	public int size;

	public CommandMPCreateExplosion(int x, int y, int z, int size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
	}

	public CommandMPCreateExplosion(double x, double y, double z,
			double size) {
		this((int) x, (int) y, (int) z, (int) size);
	}

	public CommandMPCreateExplosion(String[] commandAndArgs) {
		this(Double.parseDouble(commandAndArgs[1]),
					Double.parseDouble(commandAndArgs[2]),
					Double.parseDouble(commandAndArgs[3]),
					Double.parseDouble(commandAndArgs[4]));
	}

	public void doWork() {
		WorldServer worldserver = MinecraftServer.getServer()
				.worldServerForDimension(0); // TODO ONLY WORKS IN OVERWORLD FOR
												// NOW
		worldserver.createExplosion(new Entity(worldserver) {
			@Override
			protected void entityInit() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void readEntityFromNBT(NBTTagCompound var1) {
				// TODO Auto-generated method stub

			}

			@Override
			protected void writeEntityToNBT(NBTTagCompound var1) {
				// TODO Auto-generated method stub

			}
		}, x, y, z, size, true);
	}
	@Override
	public String serialize() {
		return CommandMPServer.CREATEEXPLOSION_NAME+ CommandMPAbstract.SERIAL_DIV + x + CommandMPAbstract.SERIAL_DIV + y + CommandMPAbstract.SERIAL_DIV + z + CommandMPAbstract.SERIAL_DIV + size;
	}
	
}