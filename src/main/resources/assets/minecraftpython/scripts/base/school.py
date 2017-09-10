"def school(x,y,z):" + 
"	yplane(x,y,z,15,15,GRASS)" + 
"	facade(x,y+1,z+4,7,5)" + 
"	facade(x+8,y+1,z+4,7,5)" + 
"	facade(x+6,y+1,z+7,4,4)" + 
"	facade(x+11,y+1,z+7,4,4)" + 
"	parking_lot(x+1,y,z+1,13,2)" + 
"	pool(x,y,z+11)" + 
"	athletic_field(x+7,y,z+10)" + 
"	auditorium(x+1,y+1,z+7)" + 
"" + 
"def auditorium(x,y,z):" + 
"	cube(x,y,z,STONE,3)" + 
"	yplane(x,y+1,z,3,3,GLASS)" + 
"	setblock(x+1,y+3,z+1,GLOWSTONE)" + 
"" + 
"def parking_lot(x,y,z,width,depth):" + 
"	#the actual parking lot" + 
"	yplane(x,y,z,width,depth,OBSIDIAN)" + 
"	#a "driveway" in" + 
"	setblock(x+width/2,y,z-1,OBSIDIAN)" + 
"	#some scattered trees around the parking lot" + 
"	tree(x+9,y+1,z+2)" + 
"	tree(x,y+1,z+2)" + 
"	tree(x+7,y+1,z-1)" + 
"	tree(x+2,y+1,z-1)" + 
"	tree(x+13,y+1,z-1)" + 
"" + 
"def pool(x,y,z):" + 
"	yplane(x,y,z,5,4,SANDSTONE)" + 
"	yplane(x+1,y,z+1,3,2,WATER)" + 
"" + 
"def athletic_field(x,y,z):" + 
"	def goalpost(x,y,z):" + 
"		setblock(x,y,z,WOOL,4)" + 
"		setblock(x,y+1,z+1,WOOL,4)" + 
"		setblock(x,y+1,z-1,WOOL,4)" + 
"		" + 
"	for xCounter in range(7):" + 
"		color=5" + 
"		if xCounter % 2 == 0:" + 
"			color = 0" + 
"		zrow(x+xCounter,y,z,5,WOOL,color)" + 
"	goalpost(x,y+1,z+2)" + 
"	goalpost(x+6,y+1,z+2)" + 
"" + 
"def zrow(x,y,z,depth,blockType,meta=0):" + 
"	for zCounter in range(depth):" + 
"		setblock(x,y,z+zCounter,blockType,meta)" + 
"" + 
"def yplane(x,y,z,width,depth,blockType,meta=0):" + 
"	for xCounter in range(width):" + 
"		zrow(x+xCounter,y,z,depth,blockType,meta)" + 
"" + 
"def tree(x,y,z):" + 
"	setblock(x,y,z,WOOD)" + 
"	setblock(x,y+1,z,WOOD)" + 
"	setblock(x,y+2,z,LEAVES)" + 
"" + 
"def facade(x,y,z,length,height):" + 
"	for lengthCounter in range(length):" + 
"		for heightCounter in range(height):" + 
"			block=BRICK_BLOCK" + 
"			if lengthCounter % 3 != 0 and heightCounter > 0 and heightCounter < (height-1):" + 
"				block=GLASS" + 
"			setblock(x+lengthCounter,y+heightCounter,z,block)" + 
"			setblock(x+lengthCounter,y+heightCounter,z+1,block)" + 
"school(myX(),myY(),myZ())" + 