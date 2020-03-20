package com.javaelementary.paint.shape;

import com.javaelementary.paint.Board;
import com.javaelementary.paint.DisplayDriver;
import com.javaelementary.paint.Shape;

import java.io.Serializable;

public class Hexagon extends BaseShape implements Shape, Serializable {
    public Hexagon(double x, double y, DisplayDriver displayDriver, Board board) {
        super(x, y, displayDriver, board);
    }

    @Override
    public void drawStroke() {
        displayDriver.setColor(color.toHex());
        displayDriver.setLineWidth();
        displayDriver.drawStrokeHexagon(x, y, size);
    }

    @Override
    public void drawFill() {
        displayDriver.setColor(color.toHex());
        displayDriver.drawHexagon(x, y, size);
    }

    @Override
    public void drawActive() {
        displayDriver.setColor();
        displayDriver.setLineWidth();
        displayDriver.drawStrokeHexagon(x, y, size);
    }
}