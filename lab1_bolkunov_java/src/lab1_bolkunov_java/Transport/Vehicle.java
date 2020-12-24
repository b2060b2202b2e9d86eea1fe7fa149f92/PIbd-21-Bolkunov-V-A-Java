package lab1_bolkunov_java.Transport;

import java.awt.*;

public abstract class Vehicle implements ITransport {
    protected static final char separator = ';';

    protected int posX;

    public int getPosX() {
        return posX;
    }

    protected int posY;

    public int getPosY() {
        return posY;
    }

    protected int pictureWidth;
    protected int pictureHeight;

    protected int maxSpeed;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    protected float weight;

    public float getWeight() {
        return weight;
    }

    protected Color mainColor;

    public Color getMainColor() {
        return mainColor;
    }

    public void setPosition(int x, int y, int width, int height) {
        posX = x;
        posY = y;
        pictureWidth = width;
        pictureHeight = height;
    }

    public abstract void drawTransport(Graphics g);

    public abstract void moveTransport(Direction direction);
}
