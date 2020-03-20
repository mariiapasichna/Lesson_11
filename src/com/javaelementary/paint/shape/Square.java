package com.javaelementary.paint.shape;

import com.javaelementary.paint.Board;
import com.javaelementary.paint.DisplayDriver;
import com.javaelementary.paint.Shape;
import com.javaelementary.paint.ShapeType;

public class Square extends BaseShape implements Shape {
    ShapeType shapeType = ShapeType.SQUARE;
    public Square(double x, double y, DisplayDriver displayDriver, Board board) {
        super(x, y, displayDriver, board);
    }

    @Override
    public void drawStroke() {
        displayDriver.setColor(color.toHex());
        displayDriver.setLineWidth();
        displayDriver.drawStrokeSquare(x, y, size);
    }

    @Override
    public void drawFill() {
        displayDriver.setColor(color.toHex());
        displayDriver.drawSquare(x, y, size);
    }

    @Override
    public void drawActive() {
        displayDriver.setColor();
        displayDriver.setLineWidth();
        displayDriver.drawStrokeSquare(x, y, size);
    }
}