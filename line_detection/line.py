#!/usr/bin/python

import cv2
import numpy as np

img = cv2.imread('horizontal_small.jpg')
hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
mask = cv2.inRange(hsv, np.array([25, 210, 30]), np.array([50, 255, 255]))

gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
edges = cv2.Canny(gray, 50, 150, apertureSize = 3)

cv2.imshow("Edges", mask)

cv2.waitKey(0)
cv2.destroyAllWindows()