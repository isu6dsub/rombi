class motor(object):
    
    minPWM = 800;
    maxPWM = 2400;
    midpoint = 1600;

    def _init_(self, number):
        self.speed = 0
        self.direction = 0
        self.channel = number

    def stop():
        """Set pwm channel to midpoint."""

    def setSpeed(speed):
        if(speed < 0 | speed > 100):
            return "Out of bounds"
        else:
            """Set the motor speed"""
            return "Set"

    def setDirection(direction):
        if(direction > 2 | direction < 0):
            return "Out of bounds"
        else:
            """Change usable pwm range"""
            return "Direction set."
            
