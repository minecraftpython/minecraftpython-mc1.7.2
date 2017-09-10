package org.sapphon.minecraft.modding.minecraftpython.command;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface ICommand {
	void doWork();
	void execute();
}
