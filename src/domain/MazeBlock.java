package domain;

public class MazeBlock {
    private boolean openUp;
    private boolean openRight;
    private boolean openDown;
    private boolean openLeft;

    public MazeBlock(boolean openUp, boolean openRight, boolean openDown, boolean openLeft) {
        this.openUp = openUp;
        this.openRight = openRight;
        this.openDown = openDown;
        this.openLeft = openLeft;
    }

    public boolean isOpenUp() {
        return openUp;
    }

    public void setOpenUp(boolean openUp) {
        this.openUp = openUp;
    }

    public boolean isOpenRight() {
        return openRight;
    }

    public void setOpenRight(boolean openRight) {
        this.openRight = openRight;
    }

    public boolean isOpenDown() {
        return openDown;
    }

    public void setOpenDown(boolean openDown) {
        this.openDown = openDown;
    }

    public boolean isOpenLeft() {
        return openLeft;
    }

    public void setOpenLeft(boolean openLeft) {
        this.openLeft = openLeft;
    }
}
