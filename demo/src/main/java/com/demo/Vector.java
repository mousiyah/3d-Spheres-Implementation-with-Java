package com.demo;

/**
 * Vector is a 3D point.
 * @author Muslima Karimova (2130288) & Fahim Samady (2035827)
 * @version 1.0
 */
public class Vector {
  double x;
  double y;
  double z;

  public Vector() {}
  public Vector(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  public double magnitude() {
    return Math.sqrt(x * x + y * y + z * z);
  }
  public void normalise() {
    double mag = magnitude();
    if (mag != 0) {
      x /= mag;
      y /= mag;
      z /= mag;
    }
  }

  public Vector cross(Vector v2) {
    return new Vector(y*v2.z-z*v2.y, z*v2.x-x*v2.z, x*v2.y-y*v2.x);
  }


  public double dot(Vector a) {
    return x * a.x + y * a.y + z * a.z;
  }
  public Vector sub(Vector a) {
    return new Vector(x - a.x, y - a.y, z - a.z);
  }
  public Vector add(Vector a) {
    return new Vector(x + a.x, y + a.y, z + a.z);
  }
  public Vector mult(Vector a) {
    return new Vector(x * a.x, y * a.y, z * a.z);
  }
  public Vector multby(double d) {
    return new Vector(d * x, d * y, d * z);
  }

  public void setAll(double d) {
    x = d;
    y = d;
    z = d;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public void setZ(double z) {
    this.z = z;
  }

}

