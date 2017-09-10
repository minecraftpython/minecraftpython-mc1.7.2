package org.sapphon.minecraft.modding.minecraftpython;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.minecraft.client.Minecraft;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.sapphon.minecraft.modding.base.JavaFileIOHelper;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ScriptLoaderConstants {

	private static String RESOURCES_PATH;
	public static final String PYTHON_SCRIPT_EXTENSION = ".py";
	public static String SCRIPTS_PATH;
	public static String MINECRAFT_PROGRAMMING_PATH;
	public static String TECHMAGE_SCRIPTS_PATH;
	public static String WAND_TEXTURE_LOCATION;
	public static final String WAND_TEXTURE_NAME_SUFFIX = "Wand";

	public static boolean isAPythonScript(File script) {
		return FilenameUtils.getExtension(script.getAbsolutePath()).matches(
				"[Pp][Yy][Cc]?");
	}
	
	public static boolean resourcePathExists() {
		return RESOURCES_PATH != null && new File(RESOURCES_PATH).exists();
	}

	public static boolean setResourcePath(FMLPreInitializationEvent event){
		if(new File(event.getModConfigurationDirectory().getAbsolutePath()).exists()){
			RESOURCES_PATH = event.getModConfigurationDirectory().getAbsolutePath();
			SCRIPTS_PATH = RESOURCES_PATH + File.separatorChar + "scripts";
			if(!new File(SCRIPTS_PATH).exists()){
				//emplace resources if they aren't there
				JavaFileIOHelper.SINGLETON.copyResourcePathContentsToFile("scripts"+ File.separator+"base", new File(SCRIPTS_PATH+File.separator+"admin.py"));
			}
			MINECRAFT_PROGRAMMING_PATH = SCRIPTS_PATH
					+ File.separatorChar+"mp";
			TECHMAGE_SCRIPTS_PATH = SCRIPTS_PATH
					+ File.separatorChar+"techmage";
			WAND_TEXTURE_LOCATION = RESOURCES_PATH + File.separatorChar+"assets"+File.separatorChar+"techmage"+File.separatorChar+"textures"+File.separatorChar+"items"+File.separatorChar;
			return true;
		}
		else{
			return false;
		}
	}
}
