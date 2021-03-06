#!/usr/bin/python

import cv2
import numpy as np

cap = cv2.VideoCapture(0)

try:
  while True:

    #img = cv2.imread('horizontal_small.jpg')
    ret, img = cap.read()

    if ret == 0:
      continue

    hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
    mask = cv2.inRange(hsv, np.array([30, 100, 1]), np.array([70, 140, 255]))

    #cv2.imshow("Mask", mask)
    #cv2.waitKey(0)
    #cv2.destroyAllWindows()

    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    edges = cv2.Canny(mask, 50, 150, apertureSize = 3)

    #cv2.imshow("Canny", edges)
    #cv2.waitKey(0)
    #cv2.destroyAllWindows()

    lines = cv2.HoughLines(edges, 1, np.pi/180, 200)
    #lines = cv2.HoughLinesP(edges, 1, np.pi/180, 200, 3, 10)

    if lines is None:
      print "FUCK"
    else:
      #for x1, y1, x2, y2 in lines[0]:
      # draw the line on the original image and display it
      for rho, theta in lines[0]:
        a = np.cos(theta)
        b = np.sin(theta)
        x0 = a*rho
        y0 = b*rho
        x1 = int(x0 + 1000*(-b))
        y1 = int(y0 + 1000*(a))
        x2 = int(x0 - 1000*(-b))
        y2 = int(y0 - 1000*(a))

        cv2.line(img,(x1,y1),(x2,y2),(0,0,255),2)

    cv2.imshow("Detected Line", mask)
    if cv2.waitKey(1) & 0xFF == ord('q'):
      break

except KeyboardInterrupt:
  pass

cap.release()
cv2.destroyAllWindows()