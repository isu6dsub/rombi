from . import motor

class Sub(object):

    def _init_(self):
        """Read Submarine Configuration"""
        self.config = [];
        if os.path.exists("../sub.config"):
            with open("../sub.config", 'r') as file:
                """Will Need to add validation later"""
                for line in file:
                    pair = line.split('=')
                    config[count(config)] = pair
                    
        """Begin Motor Configuration"""
        self.motors = [];
        i = 1
        while i < 7:
            new_motor = motor(i)
            self.motors[i] = (new_motor)
        

