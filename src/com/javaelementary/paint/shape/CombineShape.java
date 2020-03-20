package com.javaelementary.paint.shape;

import com.google.gson.annotations.Expose;
import com.javaelementary.Const;
import com.javaelementary.paint.Direction;
import com.javaelementary.paint.Shape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CombineShape implements Serializable {
    @Expose
    private List<Shape> groupShapes;

    public CombineShape(List<Shape> groupShapes) {
        this.groupShapes = new ArrayList<>(groupShapes);
    }

    public boolean isActive(double x, double y) {
        for (Shape shape : groupShapes) {
            if (x >= shape.getX() && x <= shape.getX() + Const.SHAPE_SIZE && y >= shape.getY() && y <= shape.getY() + Const.SHAPE_SIZE) {
                return true;
            }
        }
        return false;
    }

    public void draw() {
        for (Shape shape : groupShapes) {
            if (shape.isFill()) {
                shape.drawFill();
            } else {
                shape.drawStroke();
            }
        }
    }

    public void drawActive() {
        for (Shape shape : groupShapes) {
            shape.drawActive();
        }
    }

    public void fill() {
        for (Shape shape : groupShapes) {
            shape.setFill(true);
        }
    }

    public void move(Direction direction) {
        for (Shape shape : groupShapes) {
            shape.move(direction);
        }
    }

    public void grow() {
        for (Shape shape : groupShapes) {
            shape.grow();
        }
    }

    public void shrink() {
        for (Shape shape : groupShapes) {
            shape.shrink();
        }
    }

    public CombineShape cloneCombineShape() {
        List<Shape> cloneShapes = new ArrayList<>();
        for (Shape shape : groupShapes) {
            cloneShapes.add(shape.clone());
        }
        return new CombineShape(cloneShapes);
    }
}