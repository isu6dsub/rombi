#!/usr/bin/python

from Adafruit_PWM_Servo_Driver import PWM
import time

# ===========================================================================
# Example Code
# ===========================================================================

# Initialise the PWM device using the default address
pwm = PWM(0x40)
# Note if you'd like more debug output you can instead run:
#pwm = PWM(0x40, debug=True)

pwm.setPWMFreq(240)                        # Set frequency to 60 Hz

MOTOR_LIST = [1,2]

# Change speed of continuous servo on channel O
#raw_input("Unplug the squid and press [Enter].")
#for i in MOTOR_LIST:
#  pwm.setPWM(i, 0, 4096)
pwm.setPWM(1, 0, 0)
pwm.setPWM(2, 0, 0)
raw_input("Press [Enter] once the squid is plugged in.")
#time.sleep(2)
#for i in MOTOR_LIST:
#  pwm.setPWM(i, 0, 385)
#time.sleep(1)

#try:
#  amt = 0
#  while (amt >= 0 and amt < 4095):
#    print "Trying", amt
#    pwm.setPWM(1, 0, int(amt))
#    time.sleep(0.008)
#    amt += 1
#except KeyboardInterrupt:
#  print "Ending calibration"

#pwm.setPWM(1, 0, 0)

try:
  while (True):
    line = raw_input("[motor] [value]: ").split()
    if not len(line) == 2:
      continue

    motor = int(line[0])
    amt = int(line[1])

    if not motor in MOTOR_LIST:
      print "Invalid motor number"
      continue

    pwm.setPWM(motor, 0, int(amt))
except KeyboardInterrupt:
  print("")

