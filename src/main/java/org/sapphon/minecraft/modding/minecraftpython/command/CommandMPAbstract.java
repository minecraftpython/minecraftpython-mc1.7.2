package org.sapphon.minecraft.modding.minecraftpython.command;

import java.util.List;

import net.minecraft.command.ICommandSender;

public abstract class CommandMPAbstract implements ICommand {
	
	public static final String SERIAL_DIV = "KwK40";

	@Override
	public void execute() {
		CommandQueueClientSide.SINGLETON().scheduleCommand(this);
	}
}
