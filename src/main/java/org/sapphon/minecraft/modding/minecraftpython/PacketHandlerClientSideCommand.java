package org.sapphon.minecraft.modding.minecraftpython;

import org.sapphon.minecraft.modding.minecraftpython.command.CommandQueueClientSide;
import org.sapphon.minecraft.modding.minecraftpython.command.PacketServerSideCommand;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerClientSideCommand implements IMessageHandler<PacketClientSideCommand, IMessage> {

	@Override
	public IMessage onMessage(PacketClientSideCommand message,
			MessageContext ctx) {
		CommandQueueClientSide.SINGLETON().scheduleCommand(message.getCommand());
		return null;
	}

}
