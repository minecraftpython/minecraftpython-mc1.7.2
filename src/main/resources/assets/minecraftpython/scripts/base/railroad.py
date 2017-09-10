class GameStartVec3:
	def __init__(self, x, y, z):
		self.x=x
		self.y=y
		self.z=z
	def __add__(self, other):
		return  GameStartVec3(other.x + self.x, other.y + self.y, other.z + self.z)
	def __sub__(self, other):
		return  GameStartVec3(-other.x + self.x, -other.y + self.y, -other.z + self.z)
	def __mul__(self, other):
		return GameStartVec3(self.x * other, self.y * other, self.z * other)

NORTH = vector.GameStartVec3(0,0,-1)
EAST = vector.GameStartVec3(1,0,0)
SOUTH = vector.GameStartVec3(0,0,1)
WEST = vector.GameStartVec3(-1,0,0)
UP = vector.GameStartVec3(0,1,0)
DOWN = vector.GameStartVec3(0,-1,0)

class Railroad():
	def __init__(self,x,y,z):
		self.curX=x
		self.curY=y
		self.curZ=z
	def straightaway(self,length=10,direction=EAST):
		spot = vector.GameStartVec3(self.curX,self.curY,self.curZ)
		finish = spot + (direction * length) #length minus one?
		for i in range(length):
			shouldPower = i % 4 == 0 and i != 0 and i != length-1
			self.rail(spot + (direction * i),shouldPower)
		self.curX=finish.x
		self.curY=finish.y
		self.curZ=finish.z

	def ramp(self,length=10,horizontalDirection=EAST, verticalDirection=UP):
		spot=vector.GameStartVec3(self.curX,self.curY,self.curZ)
		finish=spot + (horizontalDirection * length) + (verticalDirection * length)
		frequencyOfPower = 4
		if(verticalDirection == UP):
			frequencyOfPower = 2
		for i in range(length):
			shouldPower = i % frequencyOfPower == 0 and i != 0 and i != length-1
			self.rail(spot+(horizontalDirection+verticalDirection)*i,shouldPower)
		self.curX=finish.x
		self.curY=finish.y
		self.curZ=finish.z


	def rail(self,vec,makePowered=False):
		x=vec.x
		y=vec.y
		z=vec.z
		if(makePowered):
			#set a support for the rail that will generate continuous power
			setblock(x,y-1,z,REDSTONE_BLOCK)
			#next, place the rail itself
			setblock(x,y,z,GOLDEN_RAIL)
		else:
			#first, set some support for the rail
			setblock(x,y-1,z,DIRT)
			#next, place the rail itself
			setblock(x,y,z,RAIL)
			#some air above the rails would be nice, in case this is a tunnel
		setblock(x,y+1,z,AIR)
		setblock(x,y+2,z,AIR)