package lab1_bolkunov_java;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class MotorShip {
    private int posX;

    public int getPosX() {
        return posX;
    }

    private int posY;

    public int getPosY() {
        return posY;
    }

    private int pictureWidth;
    private int pictureHeight;

    private final int shipWidth = 50;
    private final int shipHeight = 120;

    private int maxSpeed;

    public int getMaxSpeed() {
        return maxSpeed;
    }

    private float weight;

    public float getWeight() {
        return weight;
    }

    private Color mainColor;

    public Color getMainColord() {
        return mainColor;
    }

    private Color additionalColor;

    public Color getAdditionalColor() {
        return additionalColor;
    }

    private boolean additionalBoat;

    public boolean getAdditionalBoat() {
        return additionalBoat;
    }

    private boolean helicopterPad;

    public boolean getHelicopterPad() {
        return helicopterPad;
    }

    private boolean smoke;

    public boolean getSmoke() {
        return smoke;
    }

    private boolean fire;

    public boolean getFire() {
        return fire;
    }

    private Pipes pipes;

    public void setPipeCount(int count) {
        pipes.setPipeCount(count);
    }

    private Direction lastDirection = Direction.Right;

    public Direction getLastDirection() {
        return lastDirection;
    }

    public MotorShip(int maxSpeed, float weight, Color mainColor, Color additionalColor, boolean additionalBoat, boolean helipad, boolean smoke, boolean fire, PipeCount pipeCount) {
        Random rnd = new Random();

        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.mainColor = mainColor;
        this.additionalColor = additionalColor;

        this.additionalBoat = additionalBoat;
        this.helicopterPad = helipad;
        this.smoke = smoke;
        this.fire = fire;

        pipes = new Pipes(1);
    }

    public void setPosition(int x, int y, int width, int height) {
        posX = x;
        posY = y;
        pictureWidth = width;
        pictureHeight = height;
    }

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

        int[] innerSturcturePolygonPointsX = new int[]{boatPolygonPointsX[0], boatPolygonPointsX[2], boatPolygonPointsX[4]};
        int[] innerSturcturePolygonPointsY = new int[]{boatPolygonPointsY[0], boatPolygonPointsY[2], boatPolygonPointsY[4]};

        g.setColor(mainColor);
        g.fillPolygon(innerSturcturePolygonPointsX, innerSturcturePolygonPointsY, innerSturcturePolygonPointsY.length);
        g.setColor(Color.black);
        g.drawPolygon(innerSturcturePolygonPointsX, innerSturcturePolygonPointsY, innerSturcturePolygonPointsX.length);

        innerSturcturePolygonPointsX = new int[]{boatPolygonPointsX[1], boatPolygonPointsX[3], boatPolygonPointsX[5]};
        innerSturcturePolygonPointsY = new int[]{boatPolygonPointsY[1], boatPolygonPointsY[3], boatPolygonPointsY[5]};
        g.setColor(mainColor);
        g.fillPolygon(innerSturcturePolygonPointsX, innerSturcturePolygonPointsY, innerSturcturePolygonPointsX.length);
        g.setColor(Color.black);
        g.drawPolygon(innerSturcturePolygonPointsX, innerSturcturePolygonPointsY, innerSturcturePolygonPointsX.length);

        //BOAT
        if (additionalBoat) {
            int boatPointX, boatPointY;
            g.setColor(brown);
            if (lastDirection == Direction.Left || lastDirection == Direction.Right) {
                boatPointX = posX - (shipHeight * 2 / 3);
                boatPointY = posY + (shipWidth / 2);
                g.fillOval(boatPointX, boatPointY, (int) (shipHeight / 3), (int) (shipWidth / 3));
            } else {
                boatPointX = posX + (shipWidth / 2);
                boatPointY = posY - (shipHeight * 2 / 3);
                g.fillOval(boatPointX, boatPointY, (int) (shipWidth / 3), (int) (shipHeight / 3));
            }
        }

        //PAD
        if (helicopterPad) {
            int helipadSidesOffset = 3;

            g.setColor(additionalColor);
            g.fillRect((posX - shipWidth / 2 + helipadSidesOffset),
                    (posY - shipWidth / 2 + helipadSidesOffset),
                    shipWidth - helipadSidesOffset * 2,
                    shipWidth - helipadSidesOffset * 2);

            g.setColor(Color.black);
            g.drawRect((posX - shipWidth / 2 + helipadSidesOffset),
                    (posY - shipWidth / 2 + helipadSidesOffset),
                    shipWidth - helipadSidesOffset * 2,
                    shipWidth - helipadSidesOffset * 2);
        }

        pipes.Draw(g, this);

        //FIRE
        if (fire) {
            int despertionRadius = 15;
            for (int i = 0; i < 30 + rnd.nextInt(25); i++) {
                int rad = 5 + rnd.nextInt(10);
                g.setColor(Color.red);
                g.fillArc(posX - despertionRadius + rnd.nextInt(despertionRadius * 2),
                        posY - despertionRadius + rnd.nextInt(despertionRadius * 2),
                        rad, rad, rnd.nextInt(360), rnd.nextInt(360));
            }
        }
    }
}
