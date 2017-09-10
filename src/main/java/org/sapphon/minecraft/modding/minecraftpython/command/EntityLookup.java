package org.sapphon.minecraft.modding.minecraftpython.command;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.glu.Project;
import org.sapphon.minecraft.modding.minecraftpython.problemhandlers.JavaProblemHandler;
import org.sapphon.minecraft.modding.minecraftpython.spells.SpellParticle;
import org.sapphon.minecraft.modding.techmage.ArcaneArmory;
import org.sapphon.minecraft.modding.techmage.EntityWandProjectile;
import org.sapphon.minecraft.modding.techmage.MagicWand;

import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityLookup {

	private static Map<String, Class> entityNameToClassMap = new HashMap<String, Class>() {
		{
			put("pig", EntityPig.class);
			put("skeleton", EntitySkeleton.class);
			put("horse", EntityHorse.class);
			put("creeper", EntityCreeper.class);
			put("cow", EntityCow.class);
			put("chicken", EntityChicken.class);
			put("bat", EntityBat.class);
			put("arrow", EntityArrow.class);
			put("boat", EntityBoat.class);
			put("endercrystal", EntityEnderCrystal.class);
			put("largefireball", EntityLargeFireball.class);
			put("smallfireball", EntitySmallFireball.class);
			put("witherskull", EntityWitherSkull.class);
			put("fireworkrocket", EntityFireworkRocket.class);
			put("snowball", EntitySnowball.class);
			put("egg", EntityEgg.class);
			put("xporb", EntityXPOrb.class);
			put("minecart_tnt", EntityMinecartTNT.class);
			put("blaze", EntityBlaze.class);
			put("wolf", EntityWolf.class);
			put("ghast", EntityGhast.class);
			put("spider", EntitySpider.class);
			put("witch", EntityWitch.class);
			put("iron_golem", EntityIronGolem.class);
			put("zombie", EntityZombie.class);
			put("squid", EntitySquid.class);
			put("silverfish", EntitySilverfish.class);
			put("slime", EntitySlime.class);
			put("witherboss", EntityWither.class);
			put("enderdragon", EntityDragon.class);
			put("ocelot", EntityOcelot.class);
			put("zombiepigman", EntityPigZombie.class);
			put("enderman", EntityEnderman.class);
			put("magmacube", EntityMagmaCube.class);
			put("sheep", EntitySheep.class);
			put("player", EntityPlayer.class);
		}
	};

	public static Class getPlayerClass() {
		return EntityPlayer.class;
	}

	private static Map<Class, String> classToEntityNameMap = new HashMap<Class, String>() {
		{
			for (String name : entityNameToClassMap.keySet()) {
				put(entityNameToClassMap.get(name), name);
			}
		}
	};

	public static Class getEntityClassByName(String name) {
		if (entityNameToClassMap.containsKey(name.toLowerCase())) {

			Class entityClass = entityNameToClassMap.get(name.toLowerCase());
			if (entityClass != null) {
				return entityClass;
			}
		}
		return EntityTNTPrimed.class;
	}

	public static Entity getEntityByName(String name, World worldserver) {
		// first, check our static list of entity names
		if (entityNameToClassMap.containsKey(name.toLowerCase())) {

			Class entityClass = entityNameToClassMap.get(name.toLowerCase());

			try {
				// TODO: THE BRITTLENESS IS UNBELIEVABLE
				Constructor constructor = entityClass
						.getConstructor(World.class);
				Object entityToSpawn = constructor.newInstance(worldserver);
				if (entityToSpawn instanceof EntityXPOrb) {// TODO HAX: had to
															// add some xpValue
															// to an xpOrb or it
															// defaults to zero!
					EntityXPOrb orb = (EntityXPOrb) entityToSpawn;
					orb.xpValue = 5;
					entityToSpawn = orb;
				}

				return (Entity) entityToSpawn;

			} catch (Exception e) {
				JavaProblemHandler.printErrorMessageToDialogBox(e);
			}

		}
		return new EntityTNTPrimed(worldserver);
	}

	public static String getNameByEntity(Entity entity) {
		Class<? extends Entity> classOfEntity = entity.getClass();
		if (classToEntityNameMap.containsKey(classOfEntity)) {
			return classToEntityNameMap.get(entity);
		}
		return "primed_tnt";

	}
}
