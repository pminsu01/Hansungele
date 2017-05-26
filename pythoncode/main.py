import RPi.GPIO as GPIO
import time
from picamera import PiCamera
import rasp_server

pin = 18 # servomotor pin-18
left_angle = 8.5 # A1 sensor motor
center_angle = 7.5 # A2 sensor motor
right_angle = 5.5 # A3 sensor motor

GPIO.setmode(GPIO.BCM)

GPIO.setup(27,GPIO.IN) # A1 sensor
GPIO.setup(23,GPIO.OUT) # A1 LED
GPIO.setup(24,GPIO.OUT) # A1 LED

GPIO.setup(5,GPIO.IN) # A2 sensor
GPIO.setup(12,GPIO.OUT) # A2 LED
GPIO.setup(13,GPIO.OUT) # A2 LED

GPIO.setup(26,GPIO.IN) # A3 sensor
GPIO.setup(20,GPIO.OUT) # A3 LED
GPIO.setup(21,GPIO.OUT) # A3 LED

GPIO.setup(pin,GPIO.OUT) # servo motor
pwm = GPIO.PWM(pin,50)
pwm.start(0)

def doAngle(angle):
	pwm.ChangeDutyCycle(angle)

camera = PiCamera()
filename='image.jpg'

count1=0
count2=0
count3=0


try:
	while True:

			i=GPIO.input(27) # A1 sensor 
			GPIO.output(23,0)
			GPIO.output(24,0)
			if i == False:
				count1 +=1
				print('Motion a1=' + str(count1))
				GPIO.output(23,1) # A1 LED-red
				time.sleep(0.5)
				if count1 == 5:
					doAngle(left_angle)
					camera.resolution=(800,600) # Camera
					camera.rotation = 180
					camera.framerate = 13
					camera.zoom = (0.2, 0.2, 0.6, 0.6)
					camera.start_preview()
					time.sleep(4)
					camera.capture(filename)
					car_where="a1"
					rasp_server.find_images(filename,car_where)
					camera.stop_preview()
			if i== True:
				count1= 0
				count1 +=1
				GPIO.output(24,1) # A1 LED-green
				time.sleep(0.5)

			j=GPIO.input(5) # A2 sensor
			GPIO.output(12,0)
			GPIO.output(13,0)				
			if j == False:
				doAngle(center_angle)
				count2 +=1
				print('Motion a2=' + str(count2))
				GPIO.output(12,1) # A2 LED-red
				time.sleep(0.5)
				if count2 == 5:
					camera.resolution=(800,600) # Camera
					camera.rotation = 180
					camera.framerate = 13
					camera.zoom = (0.0, 0.0, 1.0, 1.0)
					camera.start_preview()
					time.sleep(5)
					camera.capture(filename)
					car_where="a2"
					rasp_server.find_images(filename,car_where)
					camera.stop_preview()
			if j== True:
				count2= 0
				count2 +=1
				GPIO.output(13,1) # A2 LED-green
				time.sleep(0.5)

			k = GPIO.input(26) # A3 sensor
			GPIO.output(20,0)
			GPIO.output(21,0)
			if k == False:
				doAngle(right_angle)
				count3 +=1
				print('Motion A3=' + str(count3))
				GPIO.output(20,1) # A3 LED-red
				time.sleep(0.5)
				if count3 == 5:
					camera.resolution=(800,600) # Camera
					camera.rotation = 180
					camera.framerate=13
					camera.zoom = (0.0, 0.0, 1.0, 1.0)
					camera.start_preview()
					time.sleep(5)
					camera.capture(filename)
					car_where="a3"
					rasp_server.find_images(filename,car_where)
					camera.stop_preview()
			if k == True:
				count3 = 0
				count3 += 1
				GPIO.output(21,1) # A3 LED-green
				time.sleep(0.5)


			
except KeyboardInterrupt:
	pass
pwm.stop()
GPIO.cleanup()

