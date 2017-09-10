package org.sapphon.minecraft.modding.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

import org.apache.commons.io.FileUtils;
import org.sapphon.minecraft.modding.minecraftpython.MinecraftPythonProgrammingMod;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;
import org.sapphon.minecraft.modding.techmage.TechMageMod;

public class JavaFileIOHelper {
	//The purpose of this guy right now is to abstract file I/O for Spellcrafters.
	//Long term, my hope is for him to cache spells - he will collaborate with a guy
	//that, given a file, will tell you if it has changed since the last answer - and help
	//optimize this so students' changes are immediate, but we're not loading from
	//disk every time they right click.
	
	public static JavaFileIOHelper SINGLETON = new JavaFileIOHelper();
	
	private JavaFileIOHelper(){
	}

	public String getTextContentOfFile(File file){
		try {
			return FileUtils.readFileToString(file);
		} catch (IOException e) {
			JavaProblemHandler.printErrorMessageToDialogBox(new Exception("Could not get contents of file " + file.getAbsolutePath(), e));
			return "";
		}
}
	public void appendStringToFile(File file, String toAppend){
		try {
			FileUtils.write(file, toAppend, true);
		} catch (IOException e) {
			JavaProblemHandler.printErrorMessageToDialogBox(new Exception("Could not append to file " + file.getAbsolutePath(), e));
		}
	}
	
	public void copyResourcePathContentsToFile(String resourcePath, File copyToLocation){
        	ResourceLocation adminLocation = new ResourceLocation("minecraftpython", "scripts"+ File.separator + "base"+File.separator+ "admin.py");
        	ResourceLocation baseSpellLocation = new ResourceLocation("minecraftpython", "scripts"+ File.separator + "base"+File.separator+ "baseSpell.py");
        	ResourceLocation blocksLocation = new ResourceLocation("minecraftpython", "scripts"+ File.separator + "base"+File.separator+ "blocks.py");
        	ResourceLocation colorsLocation = new ResourceLocation("minecraftpython", "scripts"+ File.separator + "base"+File.separator+ "colors.py");
        	ResourceLocation entitiesLocation = new ResourceLocation("minecraftpython", "scripts"+ File.separator + "base"+File.separator+ "entities.py");
        	ResourceLocation gamestartvec3Location = new ResourceLocation("minecraftpython", "scripts"+ File.separator + "base"+File.separator+ "gamestartvec3.py");
        	ResourceLocation itemsLocation = new ResourceLocation("minecraftpython", "scripts"+ File.separator + "base"+File.separator+ "items.py");
        	ResourceLocation particlesLocation = new ResourceLocation("minecraftpython", "scripts"+ File.separator + "base"+File.separator+ "particles.py");
			
        	ResourceLocation ychLocation = new ResourceLocation("minecraftpython", "scripts"+ File.separator + "mp"+File.separator+ "your_code_here.py");
        	
        	try {
			
        		//TODO doesn't work yet
        		//	FileUtils.copyURLToFile(getClass().getClassLoader().getResource(adminLocation.getResourcePath()), copyToLocation);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
