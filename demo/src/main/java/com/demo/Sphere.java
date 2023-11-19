package com.demo;

/**
 * Sphere is a 3D shape which could be drawn in View.
 * @author Muslima Karimova (2130288) & Fahim Samady (2035827)
 * @version 1.0
 */
public class Sphere {

    private double radius;
    private Vector center;
    private Vector color;

    public Sphere(double radius, Vector center, Vector color) {
        this.radius = radius;
        this.center = center;
        this.color = color;
    }

    double a;
    double b;
    double c;
    double discriminant;
    double t;

    public boolean doesIntersect(Vector originCenter, Vector direction) {
        a = direction.dot(direction);
        b = 2 * originCenter.dot(direction);
        c = originCenter.dot(originCenter) - Math.pow(radius, 2); // sphere radius
        discriminant = b*b - 4*a*c;
        return discriminant >= 0;
    }

    public double getT() {
        t = (-b - Math.sqrt(discriminant)) / 2*a;
        return t;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Vector getCenter() {
        return center;
    }

    public void setCenter(Vector center) {
        this.center = center;
    }

    public Vector getColor() {
        return color;
    }

    public void setColor(Vector color) {
        this.color = color;
    }

}
