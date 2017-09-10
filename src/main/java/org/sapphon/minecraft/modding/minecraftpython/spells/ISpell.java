package org.sapphon.minecraft.modding.minecraftpython.spells;

import java.io.File;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;
import org.sapphon.minecraft.modding.techmage.wands.WandType;


public interface ISpell {
	public String getPythonScriptAsString();

	public File getPythonScriptAsFile();

	public PyCode getCompiledPythonCode(PythonInterpreter interpreter);

	String getSpellShortName();

	public int getRequiredExperienceLevel();
	
	public String getAuthorName();
	
	public String getDisplayName();

	public boolean hasCustomTexture();
	
	public String getCustomTextureName();
	
	public WandType getWandType();

	public long getCooldownInMilliseconds();
}
