package com.javaelementary.paint.shape;

import com.javaelementary.Const;
import com.javaelementary.paint.*;

import java.util.Random;

public abstract class BaseShape implements Shape {
    protected double x;
    protected double y;
    protected double size = Const.SHAPE_SIZE;
    protected DisplayDriver displayDriver;
    protected Board board;
    protected MyColor color;
    protected boolean fill;

    public BaseShape(double x, double y, DisplayDriver displayDriver, Board board) {
        this.x = x;
        this.y = y;
        this.displayDriver = displayDriver;
        this.board = board;
        Random random = new Random();
        color = MyColor.values()[random.nextInt(MyColor.values().length)];
        this.fill = false;
    }

    @Override
    public boolean getFill() {
        return this.fill;
    }

    @Override
    public void setColor(MyColor color) {
        this.color = color;
    }

    @Override
    public MyColor getColor() {
        return this.color;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setFill(boolean fill) {
        this.fill = fill;
    }

    @Override
    public boolean isFill() {
        return fill;
    }

    @Override
    public boolean isActive(double x, double y) {
        if (x >= this.x && x <= this.x + size && y >= this.y && y <= this.y + size) {
            return true;
        }
        return false;
    }

    @Override
    public void shrink() {
        this.size -= 5;
    }

    @Override
    public void grow() {
        this.size += 5;
    }

    @Override
    public void move(Direction direction) {
        if (x + Const.SHAPE_SIZE >= Const.BOARD_WIDTH - 5) {
            x -= 1;
        } else if (y + Const.SHAPE_SIZE >= Const.BOARD_HEIGHT - 5) {
            y -= 1;
        } else if (x <= 5) {
            x += 1;
        } else if (y <= 5) {
            y += 1;
        } else {
            if (direction == Direction.RIGHT) {
                x += 5;
            } else if (direction == Direction.LEFT) {
                x -= 5;
            } else if (direction == Direction.UP) {
                y -= 5;
            } else if (direction == Direction.DOWN) {
                y += 5;
            }
        }
    }

    @Override
    public Shape clone() {
        try {
            return (BaseShape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}