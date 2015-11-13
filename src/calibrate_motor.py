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

pwm.setPWMFreq(60)                        # Set frequency to 60 Hz

MOTOR_MIN = 1
MOTOR_END = 3

# Change speed of continuous servo on channel O
raw_input("Unplug the squid and press [Enter].")
for i in range(MOTOR_MIN, MOTOR_END):
  pwm.setPWM(i, 0, 4096)
raw_input("Press [Enter] once the squid is plugged in.")
time.sleep(2)
for i in range(MOTOR_MIN, MOTOR_END):
  pwm.setPWM(i, 0, 385)
time.sleep(1)

try:
  while (True):
    line = raw_input("[motor] [value]: ").split()
    if not len(line) == 2:
      continue

    motor = int(line[0])
    amt = int(line[1])

    if motor < MOTOR_MIN or motor >= MOTOR_END:
      print "Invalid motor number"
      continue

    pwm.setPWM(motor, 0, int(amt))
except KeyboardInterrupt:
  pass

