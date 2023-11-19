package com.demo;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * View is a class for image and rendering it.
 * @author Muslima Karimova (2130288) & Fahim Samady (2035827)
 * @version 1.0
 */
public class View {

    public static int width;
    public static int height;
    public static WritableImage image;
    public static ImageView imageView;
    public static PixelWriter pixelWriter;
    public static ArrayList<Sphere> spheres;

    public static Vector direction = new Vector(0,0,1);
    public static Vector sphereCenter = new Vector(0,0,0);
    public static Vector rayOrigin = new Vector(0,0,0);
    public static Vector intersectionPoint;
    public static Vector originCenter;
    public static Vector Light = new Vector(0, 0, 10000);
    public static Vector lightSource;
    public static Vector surfaceNormal;
    public static Vector surface = new Vector(-150,-150,-220);
    public static Vector color = new Vector(0,0,0);
    public static Double t;
    public static Double minT;
    public static Integer sphereId;
    public static double shade;
    public static Camera camera = new Camera(new Vector(0, 0, 0));

    public View(int imageWidth, int imageHeight) {
        width = imageWidth;
        height = imageWidth;
        image = new WritableImage(imageWidth, imageHeight);
        imageView = new ImageView(image);
        pixelWriter = image.getPixelWriter();

        setSpheres();
        render();
    }

    public void setSpheres() {
        spheres = new ArrayList<>();
        FileIO.readSpheres(spheres);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public static void render() {
        camera.setup();

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {

                // initial pixel color - set to black
                color.setAll(0.07);

                // calculate position
                rayOrigin.x = i - (double) width /2;
                rayOrigin.y = j - (double) height /2;
                rayOrigin.z = 0;

                // Camera
                rayOrigin = camera.VRP
                        .add(camera.VRV.multby(rayOrigin.x))
                        .add(camera.VUV.multby(rayOrigin.y));
                direction = camera.VPN;

                t = null;
                minT = null;
                sphereId = null;

                for (int k = 0; k < spheres.size(); k++) {
                    sphereCenter = spheres.get(k).getCenter();
                    originCenter = rayOrigin.sub(sphereCenter);

                    // find minimum t;
                    if (spheres.get(k).doesIntersect(originCenter, direction)) { // if sphere intersects
                        t = spheres.get(k).getT();
                        if (minT == null) {
                            minT = t;
                            sphereId = k;
                        } else {
                            if (t < minT && t > 0) {
                                minT = t;
                                sphereId = k;
                            }
                        }
                    }
                }

                if (minT != null) {
                    sphereCenter = spheres.get(sphereId).getCenter();
                    intersectionPoint = rayOrigin.add(direction.multby(minT));

                    // calculate shade
                    lightSource = Light.sub(intersectionPoint);
                    lightSource.normalise();
                    surfaceNormal = intersectionPoint.sub(sphereCenter).sub(surface);
                    surfaceNormal.normalise();
                    shade = lightSource.dot(surfaceNormal);

                    if (shade >= 0) {
                        // set correct shade
                        color.setAll(shade);
                        color = color.mult(spheres.get(sphereId).getColor()); // sphere color
                    } else {
                        color.setAll(Math.abs(shade));
                        color = color.mult(spheres.get(sphereId).getColor());
                    }
                }

                // set correct color
                pixelWriter.setColor(i, j,
                        Color.color(color.x, color.y, color.z, 1.0));



                //TEST PRACTICE

                // grey scale
                double grey = (color.x+ color.y+ color.z)/3;
                pixelWriter.setColor(i, j,
                        Color.color(grey, grey, grey, 1.0));

                // threshold
                if (grey < 0.5) {
                    pixelWriter.setColor(i, j,
                            Color.color(0, 0, 0, 1.0));
                } else {
                    pixelWriter.setColor(i, j,
                            Color.color(1, 1, 1, 1.0));
                }


            }
        }

    }
}
