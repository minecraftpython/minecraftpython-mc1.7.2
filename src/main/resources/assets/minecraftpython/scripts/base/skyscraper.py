def skyscraper(x,y,z,height=20,segments=4,blocktype=STONE):
	heightPerSegment=height/segments
	for i in range(segments):
		width = (segments-i)
		segmentXStart=x-width
		segmentZStart=z-width
		segmentXEnd=x+width
		segmentZEnd=z+width
		
		setrect(segmentXStart,y+heightPerSegment*i,segmentZStart,segmentXEnd,y+heightPerSegment*(i+1),segmentZEnd,blocktype)
		
		#add windows if the segments are tall enough
		if(heightPerSegment >= 3):
			for j in range(segmentXStart,segmentXEnd+1,2):
				for k in range(y+heightPerSegment*i + 2, y+heightPerSegment*(i+1)):
					for l in range(segmentZStart,segmentZEnd+1,2):
						if((j == segmentXStart and l == segmentZStart) or (j == segmentXStart and l == segmentZEnd) or (j == segmentXEnd and l == segmentZStart) or (j == segmentXEnd and l == segmentZEnd)):	#don't put glass on corners
							pass
						else:
							setblock(j,k,l,GLASS)


def setrect(x,y,z,x2,y2,z2,blocktype=STONE):
	if(x2<x):
		tmp=x
		x=x2
		x2=tmp
	if(y2<y):
		tmp=y
		y=y2
		y2=tmp
	if(z2<z):
		tmp=z
		z=z2
		z2=tmp
	for i in range(x2-x+1):
		for j in range(y2-y+1):
			for k in range(z2-z+1):
				setblock(x+i,y+j,z+k,blocktype)

skyscraper(myX(),myY(),myZ(),36,12)