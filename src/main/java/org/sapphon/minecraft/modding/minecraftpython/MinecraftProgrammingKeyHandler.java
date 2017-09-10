package org.sapphon.minecraft.modding.minecraftpython;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;
import org.sapphon.minecraft.modding.minecraftpython.command.CommandMPGetPlayerPosition;
import org.sapphon.minecraft.modding.minecraftpython.command.CommandMPTeleport;
import org.sapphon.minecraft.modding.minecraftpython.command.CommandQueueClientSide;
import org.sapphon.minecraft.modding.minecraftpython.command.CommandQueueServerSide;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.PythonProblemHandler;
import org.sapphon.minecraft.modding.techmage.ArcaneArmory;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class MinecraftProgrammingKeyHandler {
	public static final int CAST_SPELL_KEY_INDEX = 0;
	private static final String[] keyDescriptions = { "key.castspell.desc"};
	private static final int[] defaultKeyValues = { Keyboard.KEY_P };
	private final KeyBinding[] keyBindings;
	private IArcane device;
	
	public MinecraftProgrammingKeyHandler(IArcane magicDeviceToActivateWhenCastSpellIsPressed) {
		this.device = magicDeviceToActivateWhenCastSpellIsPressed;
		keyBindings = new KeyBinding[keyDescriptions.length];
		for (int i = 0; i < keyDescriptions.length; ++i) {
			keyBindings[i] = new KeyBinding(keyDescriptions[i],
					defaultKeyValues[i], "key."
							+ MinecraftPythonProgrammingMod.MODID + ".category");
			ClientRegistry.registerKeyBinding(keyBindings[i]);
		}
	}
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if (keyBindings[CAST_SPELL_KEY_INDEX].isPressed()) {
			device.doMagic();
		}
		//more ifs go here when there are more keys
	}
}