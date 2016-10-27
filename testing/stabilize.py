#!/usr/bin/python

from Adafruit_PWM_Servo_Driver import PWM
import time
from serial import Serial

# ===========================================================================
# Example Code
# ===========================================================================

# Initialise the PWM device using the default address
pwm = PWM(0x40)
# Note if you'd like more debug output you can instead run:
#pwm = PWM(0x40, debug=True)

pwm.setPWMFreq(60)                        # Set frequency to 60 Hz

MOTORS = {
  0: 1,
  1: 2,
}

imu = Serial(port='/dev/ttyUSB0', baudrate=57600)

print "Calibrating IMU..."
while not imu.readline().strip().startswith('!ANG:'):
  pass

try:
  while (True):
    imuLine = imu.readline().strip()
    if not imuLine.startswith("!ANG:"):
      continue

    imuData = [ float(x) for x in imuLine[5:].split(",") ]

    motorSpeed0 = 385 + (imuData[1] / 90.0) * 20
    motorSpeed1 = 400 + (imuData[1] / 90.0) * -20

    print "motorSpeed0:", motorSpeed0, " motorSpeed1:", motorSpeed1

    pwm.setPWM(MOTORS[0], 0, int(motorSpeed0))
    pwm.setPWM(MOTORS[1], 0, int(motorSpeed1))
except KeyboardInterrupt:
  pass

for motor in MOTORS:
  pwm.setPWM(motor, 0, 0)

