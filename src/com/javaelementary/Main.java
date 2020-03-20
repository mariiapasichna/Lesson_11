package com.javaelementary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javaelementary.paint.Board;
import com.javaelementary.paint.Direction;
import com.javaelementary.paint.DisplayDriver;
import com.javaelementary.platform.DisplayDriverImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    private GraphicsContext gc;
    private Board board;
    private Direction direction;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFxSample");
        Canvas canvas = new Canvas();
        canvas.setWidth(Const.BOARD_WIDTH);
        canvas.setHeight(Const.BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        DisplayDriver displayDriver = new DisplayDriverImpl(gc);
        board = new Board(displayDriver);
        drawFrame();
        scene.setOnMouseClicked(this::mouseClick);
        scene.setOnKeyPressed(this::keyPress);
    }

    private void keyPress(KeyEvent keyEvent) {
        drawFrame();
        switch (keyEvent.getCode()) {
            case DIGIT1:
                board.addCircle();
                break;
            case DIGIT2:
                board.addSquare();
                break;
            case DIGIT3:
                board.addTriangle();
                break;
            case DIGIT4:
                board.addHexagon();
                break;
            case UP:
                direction = Direction.UP;
                board.move(direction);
                break;
            case DOWN:
                direction = Direction.DOWN;
                board.move(direction);
                break;
            case RIGHT:
                direction = Direction.RIGHT;
                board.move(direction);
                break;
            case LEFT:
                direction = Direction.LEFT;
                board.move(direction);
                break;
            case F1:
                board.fill();
                break;
            case F2:
                board.grow();
                break;
            case F3:
                board.shrink();
                break;
            case F4:
                board.combineShapesToList();
                break;
            case F5:
                board.combineShapes();
                break;
            case F6:
                board.delete();
                break;
            case F7:
                board.cloneShape();
                break;
            case DIGIT8:
                save(board);
                break;
            case DIGIT9:
                board.createBoard(create());
                break;
            case ESCAPE:
                board.clear();
                break;
        }
        board.drawShape();
    }
    private void mouseClick(MouseEvent mouseEvent) {
        board.setX(mouseEvent.getX());
        board.setY(mouseEvent.getY());
        board.Active();
    }

    private void drawFrame() {
        gc.clearRect(0, 0, Const.BOARD_WIDTH, Const.BOARD_HEIGHT);
        gc.setFill(Const.BOARD_COLOR);
        gc.fillRect(0, 0, Const.BOARD_WIDTH, Const.BOARD_HEIGHT);
    }

    private void save(Board board) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Save.txt"))) {
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create();
            writer.write(gson.toJson(board));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Board create() {
        String saveFrom = null;
        try (BufferedReader br = new BufferedReader(new FileReader("Save.txt"))) {
            while ((saveFrom = br.readLine()) != null) {
                System.out.println(saveFrom);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(saveFrom, Board.class);
    }
}