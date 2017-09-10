package org.sapphon.minecraft.modding.techmage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sapphon.minecraft.modding.minecraftpython.spells.ISpell;


public class SpellExperienceLevelArbiter {

	public static int getRequiredExperienceLevelForSpell(ISpell spellForWand) {
		return spellForWand.getRequiredExperienceLevel();
	}

}
