package org.sapphon.minecraft.modding.minecraftpython.spells;

import java.io.File;
import java.util.LinkedHashMap;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;
import org.sapphon.minecraft.modding.base.JavaFileIOHelper;


public class CachingSpell extends AbstractSpell{

	public CachingSpell(String name, File pythonScript){
		super(name, pythonScript);
	}

	private boolean isCached(){
		if(pythonCompiledCode == null)
			return false;
		return true;
	}
	
	@Override
	public PyCode getCompiledPythonCode(PythonInterpreter interpreter) {
		if(!isCached()){
			compileSpell(interpreter);
		}
		return this.pythonCompiledCode;
	
	}
}
