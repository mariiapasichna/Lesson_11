package com.javaelementary.paint.shape;

import com.javaelementary.paint.Board;
import com.javaelementary.paint.DisplayDriver;

public class Triangle extends BaseShape {
    public Triangle(double x, double y, DisplayDriver displayDriver, Board board) {
        super(x, y, displayDriver, board);
    }

    @Override
    public void drawStroke() {
        displayDriver.setColor(color.toHex());
        displayDriver.setLineWidth();
        displayDriver.drawStrokeTriangle(x, y, size);
    }

    @Override
    public void drawFill() {
        displayDriver.setColor(color.toHex());
        displayDriver.drawTriangle(x, y, size);
    }

    @Override
    public void drawActive() {
        displayDriver.setColor();
        displayDriver.setLineWidth();
        displayDriver.drawStrokeTriangle(x, y, size);
    }
}