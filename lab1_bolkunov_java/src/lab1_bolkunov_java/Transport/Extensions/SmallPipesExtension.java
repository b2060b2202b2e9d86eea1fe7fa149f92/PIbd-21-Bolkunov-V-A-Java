package lab1_bolkunov_java.Transport.Extensions;

import lab1_bolkunov_java.Transport.Direction;
import lab1_bolkunov_java.Transport.MotorShip;

import java.awt.*;
import java.util.Random;

public class SmallPipesExtension implements IExtension {
    private Random rnd;
    private ExtensionCount extensionCount;
    private int pipeRadius;

    private SmallPipesExtension() {
        rnd = new Random();
        pipeRadius = rnd.nextInt(5) + 5;
    }

    public SmallPipesExtension(int count) {
        this();
        setExtensionCount(count);
    }

    public void setExtensionCount(int count) {
        extensionCount = IExtension.convertIntToExtensionCount(count);
    }

    public ExtensionCount getExtensionCount(){
        return extensionCount;
    }

    public void Draw(Graphics g, MotorShip transport) {
        if (extensionCount != null) {
            int[] boatPolygonPointsX = new int[6];
            int[] boatPolygonPointsY = new int[6];
            if (transport.getLastDirection() == Direction.Left || transport.getLastDirection() == Direction.Right) {
                boatPolygonPointsX[0] = transport.getPosX() - (transport.getShipHeight() * 2 / 3);
                boatPolygonPointsY[0] = transport.getPosY() - (transport.getShipWidth() / 2);
                boatPolygonPointsX[1] = transport.getPosX() + (transport.getShipHeight() * 2 / 3);
                boatPolygonPointsY[1] = transport.getPosY() - (transport.getShipWidth() / 2);
                boatPolygonPointsX[2] = transport.getPosX() + transport.getShipHeight();
                boatPolygonPointsY[2] = transport.getPosY();
                boatPolygonPointsX[3] = transport.getPosX() + (transport.getShipHeight() * 2 / 3);
                boatPolygonPointsY[3] = transport.getPosY() + (transport.getShipWidth() / 2);
                boatPolygonPointsX[4] = transport.getPosX() - (transport.getShipHeight() * 2 / 3);
                boatPolygonPointsY[4] = transport.getPosY() + (transport.getShipWidth() / 2);
                boatPolygonPointsX[5] = transport.getPosX() - transport.getShipHeight();
                boatPolygonPointsY[5] = transport.getPosY();
            } else {
                boatPolygonPointsX[0] = transport.getPosX() - (transport.getShipWidth() / 2);
                boatPolygonPointsY[0] = transport.getPosY() - (transport.getShipHeight() * 2 / 3);
                boatPolygonPointsX[1] = transport.getPosX() - (transport.getShipWidth() / 2);
                boatPolygonPointsY[1] = transport.getPosY() + (transport.getShipHeight() * 2 / 3);
                boatPolygonPointsX[2] = transport.getPosX();
                boatPolygonPointsY[2] = transport.getPosY() + transport.getShipHeight();
                boatPolygonPointsX[3] = transport.getPosX() + (transport.getShipWidth() / 2);
                boatPolygonPointsY[3] = transport.getPosY() + (transport.getShipHeight() * 2 / 3);
                boatPolygonPointsX[4] = transport.getPosX() + (transport.getShipWidth() / 2);
                boatPolygonPointsY[4] = transport.getPosY() - (transport.getShipHeight() * 2 / 3);
                boatPolygonPointsX[5] = transport.getPosX();
                boatPolygonPointsY[5] = transport.getPosY() - transport.getShipHeight();
            }

            g.setColor(transport.getAdditionalColor());

            switch (extensionCount) {
                case Single: {
                    g.fillOval(boatPolygonPointsX[2] - pipeRadius, boatPolygonPointsY[2] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.setColor(Color.black);
                    g.drawOval(boatPolygonPointsX[2] - pipeRadius, boatPolygonPointsY[2] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    if(transport.getSmoke()) {
                        drawSmoke(g, boatPolygonPointsX[2], boatPolygonPointsY[2]);
                    }
                    break;
                }

                case Double: {
                    g.fillOval(boatPolygonPointsX[2] - pipeRadius, boatPolygonPointsY[2] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.fillOval(boatPolygonPointsX[5] - pipeRadius, boatPolygonPointsY[5] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.setColor(Color.black);
                    g.drawOval(boatPolygonPointsX[2] - pipeRadius, boatPolygonPointsY[2] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.drawOval(boatPolygonPointsX[5] - pipeRadius, boatPolygonPointsY[5] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    if(transport.getSmoke()) {
                        drawSmoke(g, boatPolygonPointsX[2], boatPolygonPointsY[2]);
                        drawSmoke(g, boatPolygonPointsX[5], boatPolygonPointsY[5]);
                    }
                    break;
                }

                case Triple: {

                    for (int i = 0; i < boatPolygonPointsX.length; i += 2) {
                        g.fillOval(boatPolygonPointsX[i] - pipeRadius, boatPolygonPointsY[i] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    }
                    g.setColor(Color.black);
                    for (int i = 0; i < boatPolygonPointsX.length; i += 2) {
                        g.drawOval(boatPolygonPointsX[i] - pipeRadius, boatPolygonPointsY[i] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    }
                    if(transport.getSmoke()) {
                        for (int i = 0; i < boatPolygonPointsX.length; i += 2) {
                            drawSmoke(g, boatPolygonPointsX[i], boatPolygonPointsY[i]);
                        }
                    }
                    break;
                }

                case Quadruple: {
                    g.fillOval(boatPolygonPointsX[0] - pipeRadius, boatPolygonPointsY[0] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.fillOval(boatPolygonPointsX[1] - pipeRadius, boatPolygonPointsY[1] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.fillOval(boatPolygonPointsX[3] - pipeRadius, boatPolygonPointsY[3] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.fillOval(boatPolygonPointsX[4] - pipeRadius, boatPolygonPointsY[4] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.setColor(Color.black);
                    g.drawOval(boatPolygonPointsX[0] - pipeRadius, boatPolygonPointsY[0] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.drawOval(boatPolygonPointsX[1] - pipeRadius, boatPolygonPointsY[1] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.drawOval(boatPolygonPointsX[3] - pipeRadius, boatPolygonPointsY[3] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    g.drawOval(boatPolygonPointsX[4] - pipeRadius, boatPolygonPointsY[4] - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                    if(transport.getSmoke()) {
                        drawSmoke(g, boatPolygonPointsX[0], boatPolygonPointsY[0]);
                        drawSmoke(g, boatPolygonPointsX[1], boatPolygonPointsY[1]);
                        drawSmoke(g, boatPolygonPointsX[3], boatPolygonPointsY[3]);
                        drawSmoke(g, boatPolygonPointsX[4], boatPolygonPointsY[4]);
                    }
                    break;
                }
            }
        }
    }

    private void drawSmoke(Graphics g,int pipePointX,int pipePointY){
        int despertionRadius = 15;
        for (int j = 0; j < 5 + rnd.nextInt(10); j++) {
            int rad = 2 + rnd.nextInt(10);
            g.setColor(Color.gray);
            g.fillOval(pipePointX - despertionRadius + rnd.nextInt(despertionRadius + 1),
                    pipePointY - despertionRadius + rnd.nextInt(despertionRadius + 1), rad, rad);
        }
    }

    @Override
    public String toString() {
        return "Маленькие трубы";
    }
}
