package domain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Maze {

    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        private static final Direction[] VALUES = values();
        private static final Random RANDOM = new Random();
        public static Direction randomDirection() {
            return VALUES[RANDOM.nextInt(VALUES.length)];
        }
    }

    int length;
    int width;
    private MazeBlock[][] mazeBlocks;
    private Direction[][] randomWalkStorage;
    private ArrayList<Point> unusedPoints;
    private static final Random RAND = new Random();

    public Maze(int length, int width) {
        this.length = length;
        this.width = width;
        mazeBlocks = new MazeBlock[length][width];
        randomWalkStorage = new Direction[length][width];
        addUnusedPoints();
        generate();
    }

    public MazeBlock[][] getMazeBlocks() {
        return mazeBlocks;
    }

    private void addUnusedPoints() {
        unusedPoints = new ArrayList<Point>();
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                unusedPoints.add(new Point(x, y));
            }
        }
    }

    private void generate() {
        setInitialBlock();
        randomWalk();
    }

    private void setInitialBlock() {
        int randomIndex = RAND.nextInt(length * width);
        Point point = unusedPoints.get(randomIndex);
        mazeBlocks[point.x][point.y] = new MazeBlock(false, false, false, false);
        unusedPoints.remove(randomIndex);
    }

    private void randomWalk() {
        if (unusedPoints.isEmpty()) return;
        int randomIndex = RAND.nextInt(unusedPoints.size());
        Point point = unusedPoints.get(randomIndex);
        int currentX = point.x;
        int currentY = point.y;

        while(mazeBlocks[currentX][currentY] == null) {
            switch(Direction.randomDirection()) {
                case UP:
                    randomWalkStorage[currentX][currentY] = Direction.UP;
                    if (currentY > 0) currentY -= 1;
                    break;
                case RIGHT:
                    randomWalkStorage[currentX][currentY] = Direction.RIGHT;
                    if (currentX < length - 1) currentX += 1;
                    break;
                case DOWN:
                    randomWalkStorage[currentX][currentY] = Direction.DOWN;
                    if (currentY < width - 1) currentY += 1;
                    break;
                case LEFT:
                    randomWalkStorage[currentX][currentY] = Direction.LEFT;
                    if (currentX > 0) currentX -= 1;
                    break;
            }
        }
        addMazePart(point.x, point.y, currentX, currentY);
        randomWalk();
    }

    private void addMazePart(int currentX, int currentY, int endX, int endY) {
        mazeBlocks[currentX][currentY] = new MazeBlock(false, false, false, false);
        removeUnusedPoint(currentX, currentY);
        while (currentX != endX || currentY != endY) {
            switch(randomWalkStorage[currentX][currentY]) {
                case UP:
                    mazeBlocks[currentX][currentY].setOpenUp(true);
                    currentY -= 1;
                    if (mazeBlocks[currentX][currentY] == null) {
                        mazeBlocks[currentX][currentY] = new MazeBlock(false, false, true, false);
                        removeUnusedPoint(currentX, currentY);
                    } else {
                        mazeBlocks[currentX][currentY].setOpenDown(true);
                    }
                    break;
                case RIGHT:
                    mazeBlocks[currentX][currentY].setOpenRight(true);
                    currentX += 1;
                    if (mazeBlocks[currentX][currentY] == null) {
                        mazeBlocks[currentX][currentY] = new MazeBlock(false, false, false, true);
                        removeUnusedPoint(currentX, currentY);
                    } else {
                        mazeBlocks[currentX][currentY].setOpenLeft(true);
                    }
                    break;
                case DOWN:
                    mazeBlocks[currentX][currentY].setOpenDown(true);
                    currentY += 1;
                    if (mazeBlocks[currentX][currentY] == null) {
                        mazeBlocks[currentX][currentY] = new MazeBlock(true, false, false, false);
                        removeUnusedPoint(currentX, currentY);
                    } else {
                        mazeBlocks[currentX][currentY].setOpenUp(true);
                    }
                    break;
                case LEFT:
                    mazeBlocks[currentX][currentY].setOpenLeft(true);
                    currentX -= 1;
                    if (mazeBlocks[currentX][currentY] == null) {
                        mazeBlocks[currentX][currentY] = new MazeBlock(false, true, false, false);
                        removeUnusedPoint(currentX, currentY);
                    } else {
                        mazeBlocks[currentX][currentY].setOpenRight(true);
                    }
                    break;
            }
        }
        randomWalkStorage = new Direction[length][width];
    }

    private void removeUnusedPoint(int x, int y) {
        for (int i = 0; i < unusedPoints.size(); i++) {
            Point point = unusedPoints.get(i);
            if (point.x == x && point.y == y) {
                unusedPoints.remove(i);
                return;
            }
        }
    }
}
