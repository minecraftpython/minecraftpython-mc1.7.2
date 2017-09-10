import sys
from org.gamestart.minecraft.modding.minecraftpython import command
from java.util import List, ArrayList
import gamestartvec3 as vector
import time
from blocks import *
from items import *
from entities import *
from particles import *
from colors import *
###ORIGINS
WORLD='world'
PLAYER='player'
###ENDORIGINS

def _get_my_position():
	return command.CommandGameStartGetPlayerPosition().execute()
	
def _get_my_look():
	return command.CommandGameStartGetPlayerLookVector().execute()

def _get_all_player_names():
	return command.CommandGameStartGetAllPlayerNames().execute()

def _get_player_name():
	return command.CommandGameStartGetPlayerName().execute()

def _get_player_position(playerName):
	return command.CommandGameStartGetPlayerPosition(playerName).execute()

def myX():
	return _get_my_position()[0]

def myY():
	return _get_my_position()[1]

def myZ():
	return _get_my_position()[2]

def opX():
	return _get_opponent_position[0]

def opY():
	return _get_opponent_position[1]

def opZ():
	return _get_opponent_position[2]
	
def lookX():
		return _get_my_look()[0]

def lookY():
		return _get_my_look()[1]

def lookZ():
		return _get_my_look()[2]
def up():
	up = player.look.cross(right())
	if(up.y < 0):
		up.y = -up.y
	return up.normalize()

def right():
	right=vector.GameStartVec3(-lookZ(),lookY(),lookX())
	return right.normalize()

class Player():
    @property
    def position(self):
        return vector.GameStartVec3(self.x,self.y,self.z)

    @property
    def x(self):
        return _get_my_position()[0]

    @property
    def y(self):
        return _get_my_position()[1]

    @property
    def z(self):
        return _get_my_position()[2]

    @property
    def look(self):
        return vector.GameStartVec3(self.lookx,self.looky,self.lookz)

    @property
    def lookx(self):
        return _get_my_look()[0]

    @property
    def looky(self):
        return _get_my_look()[1]

    @property
    def lookz(self):
        return _get_my_look()[2]

player=Player()

class SpellMetadata():
	def __init__(self):
		self._name=None
		self._author=None
		self._texture=None
		self._level=None
		self._cooldown=None
		self._target_type=None
	@property
	def name(self):
		return self._name
	@name.setter
	def name(self, newName):
		this._name=newName
	@property
	def author(self):
		return self._author
	@author.setter
	def author(self, newAuthor):
		this._author=newAuthor
	@property
	def texture(self):
		return self._texture
	@texture.setter
	def texture(self, newTexture):
		this._name=newTexture
	@property
	def level(self):
		return self._level
	@level.setter
	def level(self, newLevel):
		this._level=newLevel
	@property
	def cooldown(self):
		return self._cooldown
	@cooldown.setter
	def cooldown(self, newCooldown):
		this._cooldown=newCooldown
	@property
	def target_type(self):
		return self._target_type
	@target_type.setter
	def target_type(self, newType):
		this._target_type=newType

spell = SpellMetadata()

def console(commandText):
	command.CommandGameStartExecuteConsoleCommand(str(commandText)).execute()

def _relativizeCoordinates(x,y,z):
	transformedXOffset = right() * x
	transformedYOffset = up() * y
	transformedZOffset = player.look * z
	final_position = transformedXOffset + transformedYOffset + transformedZOffset + player.position
	yell("Player Position: " + player.position.stringify())
	yell("Player right vector: " + right().stringify())
	yell("Player up vector: " + up().stringify())
	yell("Player look vector: " + player.look.stringify())
	yell("OffsetX: " + transformedXOffset.stringify())
	yell("OffsetY: " + transformedYOffset.stringify())
	yell("OffsetZ: " + transformedZOffset.stringify())
	yell("Final: " + final_position.stringify())
	return final_position

def explosion(x,y,z,size):
	command.CommandGameStartCreateExplosion(x,y,z,size).execute()

def spawnitem(x,y,z,name=BOAT, numberOfItems=1,nbtData='{}'): 
	command.CommandGameStartSpawnItem(x,y,z,name,numberOfItems,nbtData).execute()

def spawnentity(x,y,z,name=PIG,nbtData='{}'):
	command.CommandGameStartSpawnEntity(x,y,z,name,nbtData).execute()

def lightningbolt():
	command.CommandGameStartSpawnLightningBolt().execute()

def spawnparticle(x,y,z,number=10, name=""):
    command.CommandGameStartSpawnParticle(x,y,z,number,name).execute()

def setblock(x=None,y=None,z=None,blocktype=DIRT,meta=0,origin=WORLD,tileEntityNbtData='{}'):
	if(meta is None):
			meta=0
	if(blocktype is None):
		if(not x.isdigit()): #if the argument in the X position is actually a block type, react appropriately
			blocktype=x
			x=None
		else:
			blocktype=DIRT
	if(x is None):
		x=myX()
		y=myY()
		z=myZ()
	command.CommandGameStartSetBlock(x,y,z,blocktype,meta,tileEntityNbtData).execute()
	#_setblock_sparkle(x,y,z)   #currently not performant enough for use

def block(x,y,z,blocktype=DIRT,meta=0):
	relativeInput = _relativizeCoordinates(x,y,z)
	setblock(relativeInput.x,relativeInput.y,relativeInput.z,blocktype,meta)

def propel(x,y,z,entityId="",origin=WORLD):
	if(origin==PLAYER):
		x=x*lookX()
		y=y*lookY()
		z=z*lookZ()
	if(entityId != ""):
		command.CommandGameStartPropelEntity(x,y,z,entityId).execute()
	else:
		#no entity specified, so propel the player
		command.CommandGameStartPropelEntity(x,y,z).execute()

def getblock(x,y,z):
		blockName = command.CommandGameStartGetBlock(x,y,z).execute()
		if('water' in blockName):
	   		return WATER
		elif('lava' in blockName):
	   		return LAVA
		return blockName
	
def teleport(x,y,z):
		command.CommandGameStartTeleport(x,y,z).execute()

def cube(x,y,z,blocktype=DIRT,size=2):
		for cubeX in range(0,size):
			for cubeY in range(0,size):
				for cubeZ in range(0, size):
					setblock(x+cubeX, y+cubeY, z+cubeZ, blocktype)

def setcube(x,y,z,blocktype=DIRT,size=2):
	halfSize = size/2
	otherHalfSize = size - size/2
	for cubeX in range(-halfSize,otherHalfSize):
		for cubeY in range(-halfSize,otherHalfSize):
			for cubeZ in range(-halfSize,otherHalfSize):
				setblock(x+cubeX, y+cubeY, z+cubeZ, blocktype)

def getrect(x,y,z,x2,y2,z2):    #since these names are less than cool, x,y,z, is one corner and x2,y2,z2 are the other corner of an imaginary a rectangle
   		return [[[getblock(i,j,k) for k in range(z,z2)] for j in range(y,y2)] for i in range(x,x2)]

def setrect(x,y,z, listOfListOfListsOfBlockTypes):
		for i in range(len(listOfListOfListsOfBlockTypes) - 1):
			for j in range(len(listOfListOfListsOfBlockTypes[0]) - 1):
				for k in range(len(listOfListOfListsOfBlockTypes[0][0]) - 1):
					setblock(x+i,y+j,z+k,listOfListOfListsOfBlockTypes[i][j][k])    

def yell(toYell):
	command.CommandGameStartBroadcast(str(toYell)).execute()

def makeitsuperweird(targetPlayerName):
	command.CommandGameStartApplyShader(targetPlayerName).execute()
