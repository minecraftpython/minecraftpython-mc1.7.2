package org.sapphon.minecraft.modding.minecraftpython.command;

import java.util.ArrayList;

import org.sapphon.minecraft.modding.minecraftpython.PacketClientSideCommand;
import org.sapphon.minecraft.modding.minecraftpython.MinecraftPythonProgrammingMod;

public class CommandQueueServerSide extends CommandQueueAbstract {

	private static CommandQueueServerSide SINGLETON;

	private CommandQueueServerSide() {
		scheduledCommands = new ArrayList<ICommand>();
	}

	public synchronized void scheduleCommand(ICommand command) {
		if (command instanceof CommandMPServer) {
			this.scheduledCommands.add(command);
		} else if (command instanceof CommandMPClient) {
			CommandMPClient cast = (CommandMPClient) command;
			MinecraftPythonProgrammingMod.clientCommandPacketChannel.sendToAll(new PacketClientSideCommand(
					cast));
		}
	}

	public static ICommandQueue SINGLETON() {
		if (SINGLETON == null) {
			SINGLETON = new CommandQueueServerSide();
		}
		return SINGLETON;
	}

}
