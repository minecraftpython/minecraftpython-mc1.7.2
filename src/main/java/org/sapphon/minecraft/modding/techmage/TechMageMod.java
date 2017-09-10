package org.sapphon.minecraft.modding.techmage;

import java.awt.Color;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.minecraft.block.Block;
import net.minecraft.block.BlockQuartz;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

import org.sapphon.minecraft.modding.base.CommonProxy;
import org.sapphon.minecraft.modding.base.ModConfigurationFlags;
import org.sapphon.minecraft.modding.minecraftpython.MinecraftPythonCreativeTab;
import org.sapphon.minecraft.modding.minecraftpython.ScriptLoaderConstants;
import org.sapphon.minecraft.modding.minecraftpython.command.CommandQueueClientSide;
import org.sapphon.minecraft.modding.minecraftpython.command.CommandQueueServerSide;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;
import org.sapphon.minecraft.modding.techmage.MagicWand;
import org.sapphon.minecraft.modding.techmage.wands.WandCreativeTab;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry.EntityRegistration;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = TechMageMod.MODID, version = TechMageMod.VERSION, name = TechMageMod.MODID)
public class TechMageMod {
	public static final String MODID = "techmage";
	public static final String VERSION = "1.7.2-0.1.1";

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@Instance(value = TechMageMod.MODID)
	public static TechMageMod instance;

	@SidedProxy(clientSide = "org.sapphon.minecraft.modding.base.ClientProxy", serverSide = "org.sapphon.minecraft.modding.base.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		if (isEnabled()) {
			if(!ScriptLoaderConstants.resourcePathExists()){
				ScriptLoaderConstants.setResourcePath(event);
			}
				for (MagicWand wand : ArcaneArmory.SINGLETON().getWands()) {
					GameRegistry.registerItem(wand, wand.getUnlocalizedName(),
							TechMageMod.MODID);
					String wandDisplayName = wand.getSpell().getDisplayName();
					if (wandDisplayName != SpellMetadataConstants.NONE) {
						LanguageRegistry.instance().addStringLocalization(
								wand.getUnlocalizedName() + ".name", "en_US",
								wandDisplayName);
					}
				}
		}
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	private boolean isEnabled() {
		return ModConfigurationFlags.SPELLCRAFTERS();
	}

}