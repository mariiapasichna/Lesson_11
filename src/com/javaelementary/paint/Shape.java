package com.javaelementary.paint;

public interface Shape extends Cloneable {
    void drawStroke();

    void drawFill();

    void drawActive();

    void grow();

    void shrink();

    double getX();

    double getY();

    void move(Direction direction);

    boolean isActive(double x, double y);

    boolean isFill();

    void setFill(boolean fill);

    boolean getFill();

    void setColor(MyColor color);

    MyColor getColor();

    Shape clone();
}