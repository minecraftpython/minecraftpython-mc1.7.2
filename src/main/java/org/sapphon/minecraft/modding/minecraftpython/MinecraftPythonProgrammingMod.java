package org.sapphon.minecraft.modding.minecraftpython;

import java.awt.Color;
import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings.Options;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import org.lwjgl.input.Keyboard;
import org.sapphon.minecraft.modding.base.ClientProxy;
import org.sapphon.minecraft.modding.base.CombinedClientProxy;
import org.sapphon.minecraft.modding.base.CommonProxy;
import org.sapphon.minecraft.modding.base.DedicatedServerProxy;
import org.sapphon.minecraft.modding.base.ModConfigurationFlags;
import org.sapphon.minecraft.modding.minecraftpython.command.CommandQueueServerSide;
import org.sapphon.minecraft.modding.minecraftpython.command.PacketHandlerServerSideCommand;
import org.sapphon.minecraft.modding.minecraftpython.command.PacketServerSideCommand;
import org.sapphon.minecraft.modding.minecraftpython.spells.ThreadFactory;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry.EntityRegistration;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = MinecraftPythonProgrammingMod.MODID, version = MinecraftPythonProgrammingMod.VERSION, name = MinecraftPythonProgrammingMod.MODID)
public class MinecraftPythonProgrammingMod {
	public static final String MODID = "minecraftpython";
	public static final String VERSION = "1.7.2-0.3.4";
	public static final int SCRIPT_RUN_COOLDOWN = 1500;

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@Instance(value = MinecraftPythonProgrammingMod.MODID)
	public static MinecraftPythonProgrammingMod instance;

	@SidedProxy(clientSide = "org.sapphon.minecraft.modding.base.CombinedClientProxy", serverSide = "org.sapphon.minecraft.modding.base.DedicatedServerProxy")
	public static CommonProxy proxy;
	public static SimpleNetworkWrapper serverCommandPacketChannel;
	public static SimpleNetworkWrapper clientCommandPacketChannel;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		if (isEnabled()) {
			if(!ScriptLoaderConstants.resourcePathExists()){
				ScriptLoaderConstants.setResourcePath(event);
			}
				serverCommandPacketChannel = NetworkRegistry.INSTANCE
						.newSimpleChannel("GSSServerCommand");
				serverCommandPacketChannel.registerMessage(
						PacketHandlerServerSideCommand.class,
						PacketServerSideCommand.class, 0, Side.SERVER);
				clientCommandPacketChannel = NetworkRegistry.INSTANCE
						.newSimpleChannel("GSSClientCommand");
				clientCommandPacketChannel.registerMessage(
						PacketHandlerClientSideCommand.class,
						PacketClientSideCommand.class, 0, Side.CLIENT);
		}
	}

	private boolean isEnabled() {
		return ModConfigurationFlags.MINECRAFT_PYTHON_PROGRAMMING();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		if (isEnabled() && !(proxy instanceof DedicatedServerProxy)) {
			FMLCommonHandler
					.instance()
					.bus()
					.register(
							new MinecraftProgrammingKeyHandler(
									MPOnlyScriptLoader.SINGLETON()
											.getMagicVessel()));
		}
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (!(proxy instanceof ClientProxy) && ModConfigurationFlags.MPPM_WEB()) {
			ThreadFactory.makeJavaGameLoopThread().start();
		}
	}
	
}