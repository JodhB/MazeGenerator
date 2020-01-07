package gui;

import domain.Maze;
import domain.MazeBlock;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collection;

public class MazeDisplay extends Application {
    public static final int SIZE = 10;
    public static final int MAZELENGTH = 100;
    public static final int MAZEWIDTH = 100;
    public static final Maze maze = new Maze(MAZELENGTH, MAZEWIDTH);

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Collection<Node> mazeLines = new ArrayList<>();

        MazeBlock[][] mazeBlocks = maze.getMazeBlocks();
        for (int x = 0; x < MAZELENGTH; x++) {
            for (int y = 0; y < MAZEWIDTH; y++) {
                MazeBlock mazeBlock = mazeBlocks[x][y];
                if (!mazeBlock.isOpenUp()) mazeLines.add(new Line(x * SIZE, y * SIZE, (x + 1) * SIZE, y * SIZE));
                if (!mazeBlock.isOpenLeft()) mazeLines.add(new Line(x * SIZE, y * SIZE, x * SIZE, (y + 1) * SIZE));
            }
        }
        mazeLines.add(new Line(SIZE * MAZELENGTH, 0, SIZE * MAZELENGTH, SIZE * MAZEWIDTH));
        mazeLines.add(new Line(0, SIZE * MAZEWIDTH, SIZE * MAZELENGTH, SIZE * MAZEWIDTH));

        Group root = new Group(mazeLines);
        Scene scene = new Scene(root, MAZEWIDTH * SIZE, MAZELENGTH * SIZE);
        stage.setScene(scene);
        stage.show();
    }
}
