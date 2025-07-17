package org.example.learn;

public class ShapeFactory {
    public static Shape getShape(String shapeName) {
        if (shapeName.equalsIgnoreCase("Square")) {
            return new Square(); // returns as Shape
        } else if (shapeName.equalsIgnoreCase("Circle")) {
            return new Circle(); // returns as Shape
        } else {
            throw new IllegalArgumentException("Unknown Shape: " + shapeName);
        }
    }
}
