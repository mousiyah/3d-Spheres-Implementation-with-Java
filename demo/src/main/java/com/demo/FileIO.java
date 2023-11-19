package com.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileIO reads and writes shapes to and from the file.
 * @author Muslima Karimova (2130288) & Fahim Samady (2035827)
 * @version 1.0
 */
public class FileIO {

    public static String spheresFileName = "data/spheres.txt";

    public static void readSpheres(ArrayList<Sphere> spheres) {
        File file = new File(spheresFileName);
        Scanner in;

        String[] data;
        double[] values;
        try {
            in = new Scanner(file);
            while(in.hasNextLine()) {
                data = in.nextLine().split(" ");
                values = stringToDouble(data);
                spheres.add(new Sphere(
                        values[0],
                        new Vector(values[1], values[2], values[3]),
                        new Vector(values[4], values[5], values[6])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + spheresFileName + " not found.");
        }

    }

    public static void writeSpheres(ArrayList<Sphere> spheres) {
        try {
            PrintWriter writer = new PrintWriter(spheresFileName);
            writer.print("");
            for (Sphere sphere : spheres) {
                writer.write(sphere.getRadius() + " " +
                        sphere.getCenter().x + " " + sphere.getCenter().y + " " + sphere.getCenter().z + " " +
                        sphere.getColor().x + " " + sphere.getColor().y + " " + sphere.getColor().z);
                if (spheres.indexOf(sphere) != spheres.size() - 1) {
                    writer.write("\n");
                }
            }

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File " + spheresFileName + " not found.");
        }
    }

    public static double[] stringToDouble(String[] data) {
        double[] values = new double[7];
        values[0] = Double.parseDouble(data[0]);
        values[1] = Double.parseDouble(data[1]);
        values[2] = Double.parseDouble(data[2]);
        values[3] = Double.parseDouble(data[3]);
        values[4] = Double.parseDouble(data[4]);
        values[5] = Double.parseDouble(data[5]);
        values[6] = Double.parseDouble(data[6]);

        return values;
    }

}
