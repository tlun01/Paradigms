import pygame
import time

from pygame.locals import*
from time import sleep

class Sprite():
	def __init__(self, x1, y1, w1, h1):
		self.x = x1
		self.y = y1
		self.w = w1
		self.h = h1
		self.flip = False
	
	def checkCollision(self, s):
		if(self.x + self.w <= s.x):  #this right < sprite's left
			return False
		if(self.x >= s.x + s.w):  #this left > sprite's right
			return False
		if(self.y >= s.y + s.h):  #this top underneath sprite base
			return False
		if(self.y + self.h <= s.y):   #this base over sprite's top
			return False
		return True

	def isBrick(self):
		return False

	def isCoinBrick(self):
		return False


class Brick(Sprite):
	def __init__(self, x, y, w, h):
		Sprite.__init__(self, x, y, w, h)

	def update(self):
		return True

	def isBrick(self):
		return True


class Mario(Sprite):
	def __init__(self, x, y, w, h):
		Sprite.__init__(self, x, y, w, h)
		self.flip = False
		self.vert_vel = 0.0

		self.screenLocation = 150
		self.imageNum = 0

		self.py = 0
		self.px = 0

		self.marioImage1 = pygame.image.load("mario1.png")
		self.marioImage2 = pygame.image.load("mario2.png")
		self.marioImage3 = pygame.image.load("mario3.png")
		self.marioImage4 = pygame.image.load("mario4.png")
		self.marioImage5 = pygame.image.load("mario5.png")

		self.marioImages = []
		self.marioImages.append(self.marioImage1)
		self.marioImages.append(self.marioImage2)
		self.marioImages.append(self.marioImage3)
		self.marioImages.append(self.marioImage4)
		self.marioImages.append(self.marioImage5)

	def update(self):
		if(self.vert_vel != 0.0): #if not standing on ground or brick start counting frames
			self.frameCounter += 1
		else:   #once landed set frames back to zero
			self.frameCounter = 0

		self.savePrevY()
		self.vert_vel += 1.2
		self.y += self.vert_vel

		if(self.y > 350):
			self.vert_vel = 0.0
			self.y = 350; #snap back to the ground

		return True

	def getOutOfTheObstacle(self, s):
		if(self.x + self.w > s.x and self.px + self.w <= s.x): #collision with left side of brick
				self.x = s.x - self.w
		if(self.x < s.x + s.w and self.px >= s.x + s.w): #collision with right side of brick     
				self.x = s.x + s.w
		if(self.y + self.h >= s.y and self.py + self.h <= s.y):   #collision with top of brick
			self.y = s.y - self.h
			self.vert_vel = 0.0
		if(self.y <= s.y + s.h and self.py >= s.y + s.h): #collision with bottom of brick
			self.y = s.y + s.h + 1
			self.frameCounter = 6
			self.vert_vel = 0.1
			if(s.isCoinBrick()):
				return True
			return False
	
	def savePrevX(self):
		self.px = self.x
	
	def savePrevY(self):
		self.py = self.y

	def updateImageNum(self):
		self.imageNum += 1
		if(self.imageNum > 4):
			self.imageNum = 0
		
		self.image = self.marioImages[self.imageNum]


class Model():
	def __init__(self):
		self.sprites = []
		self.mario = Mario(0, 350, 60, 95)
		self.sprites.append(self.mario)
		self.brick = Brick(400, 100, 50, 50)
		self.sprites.append(self.brick)
		self.brick = Brick(600, 225, 50, 50)
		self.sprites.append(self.brick)
		self.brick2 = Brick(200, 200, 50, 50)
		self.sprites.append(self.brick2)
		self.brick3 = Brick(250, 250, 50, 50)
		self.sprites.append(self.brick3)
		self.brick4 = Brick(400, 400, 50, 50)
		self.sprites.append(self.brick4)
		# self.coinBrick = CoinBrick(25, 250, 50, 50)
		# self.sprites.append(self.coinBrick)
		# self.coinBrick1 = CoinBrick(100, 100, 50, 50)
		# self.sprites.append(self.coinBrick1)

	def update(self):
		for i in range(len(self.sprites)):
			if(not self.sprites[i].update()):
				self.sprites.splice(i,1)
				# break
			if(self.sprites[i].isBrick() or self.sprites[i].isCoinBrick()):
				if(self.mario.checkCollision(self.sprites[i])):
					self.mario.getOutOfTheObstacle(self.sprites[i])
						# turn ^ into an if statement after adding coins and coinbricks
						# self.coin = Coin(self.sprites[i].x, self.sprites[i].y, self.sprites[i].w, self.sprites[i].h, "coin.png")
						# self.sprites.push(self.coin)
						# self.sprites[i].coinCounter += 1
						# if(self.sprites[i].coinCounter >= 5):
						# 	self.tempBrick = Brick(self.sprites[i].x, self.sprites[i].y, self.sprites[i].w, self.sprites[i].h, "brick.png")
						# 	self.sprites.push(self.tempBrick)
						# 	self.sprites.splice(i, 1)


class View():
	def __init__(self, model):
		screen_size = (800,600)
		self.screen = pygame.display.set_mode(screen_size, 32)
		self.backgroundLocation = 0
		self.ground = pygame.image.load("floor.png")
		self.background = pygame.image.load("cloudbackground.jpg")
		self.turtle_image = pygame.image.load("turtle.png")
		self.model = model
		self.model.rect = self.turtle_image.get_rect()

	def update(self):    
		self.screen.fill([0,200,100])
		self.screen.blit(self.turtle_image, self.model.rect)
		pygame.display.flip()

class Controller():
	def __init__(self, model, view):
		self.model = model
		self.view = view
		self.keep_going = True

	def update(self):
		for event in pygame.event.get():
			if event.type == QUIT:
				self.keep_going = False
			elif event.type == KEYDOWN:
				if event.key == K_ESCAPE:
					self.keep_going = False

		keys = pygame.key.get_pressed()
		if keys[K_LEFT]:
			self.model.mario.flip = True
			self.model.mario.x -= 5
			self.model.mario.updateImageNum()
			self.view.backgroundLocation += 2
		if keys[K_RIGHT]:
			self.model.mario.flip = False
			self.model.mario.x += 5
			self.view.backgroundLocation -= 2
			self.model.mario.updateImageNum()
		if keys[K_UP]:
			if(self.model.mario.frameCounter < 5):
				self.model.mario.vert_vel -= 5.3

print("Use the arrow keys to move. Press Esc to quit.")
pygame.init()
m = Model()
v = View(m)
c = Controller(m, v)
while c.keep_going:
	c.update()
	m.update()
	v.update()
	sleep(0.04)
print("Goodbye")


