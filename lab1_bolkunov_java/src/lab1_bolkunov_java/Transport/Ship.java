package lab1_bolkunov_java.Transport;

import java.awt.*;
import java.util.Random;

public class Ship extends Vehicle {
    private static final int defaultShipWidth = 50;
    private static final int defaultShipHeight = 120;

    protected final int shipWidth;
    public int getShipWidth() {
        return shipWidth;
    }

    protected final int shipHeight;
    public int getShipHeight() {
        return shipHeight;
    }

    protected Direction lastDirection = Direction.Right;

    public Direction getLastDirection() {
        return lastDirection;
    }

    public Ship(int maxSpeed, float weight, Color mainColor) {
        this(maxSpeed, weight, mainColor, defaultShipWidth, defaultShipHeight);
    }

    protected Ship(int maxSpeed, float weight, Color mainColor, int shipWidth, int shipHeight) {
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.shipWidth = shipWidth;
        this.shipHeight = shipHeight;
    }

    @Override
    public void moveTransport(Direction direction) {
        float step = maxSpeed * 500 / weight;
        float offsetMult = 2f;
        switch (direction) {
            case Right:
                if (posX + step + shipHeight * offsetMult < pictureWidth) {
                    posX += step;
                }
                break;
            case Left:
                if (posX - step - shipHeight * offsetMult > 0) {
                    posX -= step;
                }
                break;
            case Up:
                if (posY - step - shipHeight * offsetMult > 0) {
                    posY -= step;
                }
                break;
            case Down:
                if (posY + step + shipHeight * offsetMult < pictureHeight) {
                    posY += step;
                }
                break;
        }
        lastDirection = direction;
    }

    @Override
    public void drawTransport(Graphics g) {
        Random rnd = new Random();

        Color brown = Color.getHSBColor(32, 100, 38);

        //MOTOR SHIP BASE
        int[] boatPolygonPointsX = new int[6];
        int[] boatPolygonPointsY = new int[6];
        if (lastDirection == Direction.Left || lastDirection == Direction.Right) {
            boatPolygonPointsX[0] = posX - (shipHeight * 2 / 3);
            boatPolygonPointsY[0] = posY - (shipWidth / 2);
            boatPolygonPointsX[1] = posX + (shipHeight * 2 / 3);
            boatPolygonPointsY[1] = posY - (shipWidth / 2);
            boatPolygonPointsX[2] = posX + shipHeight;
            boatPolygonPointsY[2] = posY;
            boatPolygonPointsX[3] = posX + (shipHeight * 2 / 3);
            boatPolygonPointsY[3] = posY + (shipWidth / 2);
            boatPolygonPointsX[4] = posX - (shipHeight * 2 / 3);
            boatPolygonPointsY[4] = posY + (shipWidth / 2);
            boatPolygonPointsX[5] = posX - shipHeight;
            boatPolygonPointsY[5] = posY;
        } else {
            boatPolygonPointsX[0] = posX - (shipWidth / 2);
            boatPolygonPointsY[0] = posY - (shipHeight * 2 / 3);
            boatPolygonPointsX[1] = posX - (shipWidth / 2);
            boatPolygonPointsY[1] = posY + (shipHeight * 2 / 3);
            boatPolygonPointsX[2] = posX;
            boatPolygonPointsY[2] = posY + shipHeight;
            boatPolygonPointsX[3] = posX + (shipWidth / 2);
            boatPolygonPointsY[3] = posY + (shipHeight * 2 / 3);
            boatPolygonPointsX[4] = posX + (shipWidth / 2);
            boatPolygonPointsY[4] = posY - (shipHeight * 2 / 3);
            boatPolygonPointsX[5] = posX;
            boatPolygonPointsY[5] = posY - shipHeight;
        }
        g.setColor(mainColor);
        g.fillPolygon(boatPolygonPointsX, boatPolygonPointsY, boatPolygonPointsX.length);
        g.setColor(Color.black);
        g.drawPolygon(boatPolygonPointsX, boatPolygonPointsY, boatPolygonPointsX.length);
    }
}
