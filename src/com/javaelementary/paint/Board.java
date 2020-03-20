package com.javaelementary.paint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.javaelementary.paint.shape.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {
    @Expose
    private double x;
    @Expose
    private double y;

    private final DisplayDriver displayDriver;
    @Expose
    private List<Shape> shapes = new ArrayList<>();
    @Expose
    private List<Shape> groupShapes = new ArrayList<>();
    @Expose
    private List<CombineShape> combineShapes = new ArrayList<>();

    public Board(DisplayDriver displayDriver) {
        this.x = 0;
        this.y = 0;
        this.displayDriver = displayDriver;
    }

    public void createBoard (Board board) {
        x = board.x;
        y = board.y;
        shapes = board.shapes;
        groupShapes = board.groupShapes;
        combineShapes = board.combineShapes;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addCircle() {
        shapes.add(new Circle(10, 10, displayDriver, this));
    }

    public void addSquare() {
        shapes.add(new Square(10, 10, displayDriver, this));
    }

    public void addTriangle() {
        shapes.add(new Triangle(10, 10, displayDriver, this));
    }

    public void addHexagon() {
        shapes.add(new Hexagon(10, 10, displayDriver, this));
    }

    public void drawShape() {
        for (Shape shape : shapes) {
            if (shape.isFill()) {
                shape.drawFill();
            } else {
                shape.drawStroke();
            }
            if (shape.isActive(x, y)) {
                shape.drawActive();
            }
        }
        for (Shape shape : groupShapes) {
            if (shape.isFill()) {
                shape.drawFill();
            } else {
                shape.drawStroke();
            }
            if (shape.isActive(x, y)) {
                shape.drawActive();
            }
        }
        for (CombineShape combineShape : combineShapes) {
            combineShape.draw();
            if (combineShape.isActive(x, y)) {
                combineShape.drawActive();
            }
        }
    }

    public void move(Direction direction) {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.move(direction);
                break;
            }
        }
        for (CombineShape combineShape : combineShapes) {
            if (combineShape.isActive(x, y)) {
                combineShape.move(direction);
                break;
            }
        }
    }

    public void fill() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.setFill(true);
                break;
            }
        }
        for (CombineShape combineShape : combineShapes) {
            if (combineShape.isActive(x, y)) {
                combineShape.fill();
                break;
            }
        }
    }

    public void grow() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.grow();
                break;
            }
        }
        for (CombineShape combineShape : combineShapes) {
            if (combineShape.isActive(x, y)) {
                combineShape.grow();
                break;
            }
        }
    }

    public void shrink() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.shrink();
                break;
            }
        }
        for (CombineShape combineShape : combineShapes) {
            if (combineShape.isActive(x, y)) {
                combineShape.shrink();
                break;
            }
        }
    }

    public void delete() {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).isActive(x, y)) {
                shapes.remove(i);
                break;
            }
        }
        for (CombineShape combineShape : combineShapes) {
            if (combineShape.isActive(x, y)) {
                combineShapes.remove(combineShape);
                break;
            }
        }
    }

    public void combineShapesToList() {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).isActive(x, y)) {
                groupShapes.add(shapes.get(i));
                shapes.remove(i);
                break;
            }
        }
    }

    public void combineShapes() {
        combineShapes.add(new CombineShape(groupShapes));
        groupShapes.clear();
    }

    public void Active() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shape.drawActive();
                break;
            }
        }
        for (CombineShape combineShape : combineShapes) {
            if (combineShape.isActive(x, y)) {
                combineShape.drawActive();
                break;
            }
        }
    }

    public void cloneShape() {
        for (Shape shape : shapes) {
            if (shape.isActive(x, y)) {
                shapes.add(shape.clone());
                break;
            }
        }
        for (CombineShape combineShape : combineShapes) {
            if (combineShape.isActive(x, y)) {
                combineShapes.add(combineShape.cloneCombineShape());
                break;
            }
        }
    }

    public void clear() {
        shapes.clear();
        groupShapes.clear();
        combineShapes.clear();
    }
}