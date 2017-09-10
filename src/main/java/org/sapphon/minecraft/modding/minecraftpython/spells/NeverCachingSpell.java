package org.sapphon.minecraft.modding.minecraftpython.spells;

import java.io.File;
import java.util.LinkedHashMap;

import org.python.core.PyCode;
import org.python.util.PythonInterpreter;
import org.sapphon.minecraft.modding.base.JavaFileIOHelper;


public class NeverCachingSpell extends AbstractSpell{

	
	public NeverCachingSpell(String name, File pythonScript) {
		super(name, pythonScript);
	}

	@Override
	public PyCode getCompiledPythonCode(PythonInterpreter interpreter) {
		compileSpell(interpreter);
		return this.pythonCompiledCode;
	
	}
}
