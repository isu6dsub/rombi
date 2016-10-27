from Adafruit_PWM_Servo_Driver import PWM
import time

pwm = PWM(0x40)
pwm.setPWMFreq(240)

MOTORS = [1,2]

for m in MOTORS:
 pwm.setPWM(m, 0, 0)

raw_input("Press [Enter] once the squid is plugged in")

for m in MOTORS:
 pwm.setPWM(m, 0, 2400)
 pwm.setPWM(m, 0, 1600)

try:
 while(True):
  
  line = raw_input(
