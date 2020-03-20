package com.javaelementary.paint.shape;

import com.javaelementary.Const;
import com.javaelementary.paint.Direction;
import com.javaelementary.paint.MyColor;
import com.javaelementary.paint.Shape;
import com.javaelementary.paint.ShapeType;

import java.util.ArrayList;
import java.util.List;

public class CombinedShape implements Shape{
    ShapeType shapeType = ShapeType.COMBINED;
    private List<Shape> groupShapes;

    public CombinedShape(List<Shape> groupShapes) {
        this.groupShapes = new ArrayList<>(groupShapes);
    }

    public List<Shape> getGroupShapes() {
        return groupShapes;
    }

    public boolean isActive(double x, double y) {
        for (Shape shape : groupShapes) {
            if (x >= shape.getX() && x <= shape.getX() + Const.SHAPE_SIZE && y >= shape.getY() && y <= shape.getY() + Const.SHAPE_SIZE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFill() {
        return false;
    }

    @Override
    public void setFill(boolean fill) {
    }

    @Override
    public boolean getFill() {
        return false;
    }

    @Override
    public void setColor(MyColor color) {
    }

    @Override
    public MyColor getColor() {
        return null;
    }

    @Override
    public Shape clone() {
        return null;
    }

    public void drawCombined() {
        for (Shape shape : groupShapes) {
            if (shape.isFill()) {
                shape.drawFill();
            } else {
                shape.drawStroke();
            }
            if (shape instanceof CombinedShape){
                ((CombinedShape) shape).drawCombined();
            }
        }
    }

    @Override
    public void drawStroke() {
    }

    @Override
    public void drawFill() {
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

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    public CombinedShape cloneCombinedShape() {
        List<Shape> cloneShapes = new ArrayList<>();
        for (Shape shape : groupShapes) {
            cloneShapes.add(shape.clone());
        }
        return new CombinedShape(cloneShapes);
    }
}