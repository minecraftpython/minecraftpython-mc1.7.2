package org.sapphon.minecraft.modding.minecraftpython.command;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketHandlerServerSideCommand implements
		IMessageHandler<PacketServerSideCommand, IMessage> {
	public PacketHandlerServerSideCommand(){
		
	}
	@Override
	public IMessage onMessage(PacketServerSideCommand message, MessageContext ctx) {
		CommandQueueServerSide.SINGLETON().scheduleCommand(message.command);
		return null;
	}

}
