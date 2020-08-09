package examples;

import java.awt.*;

import model.Camera;

public class ColorSnap {

    public static void printSnap(Camera camera) {
        System.out.println(camera.snap(new Color(125, 125, 125)));
    }

    public static void main(String[] args) {
        printSnap(new Camera());

        printSnap(new Camera(Color::brighter));

        printSnap(new Camera(Color::darker));

        printSnap(new Camera(Color::brighter, Color::darker));
    }
}
