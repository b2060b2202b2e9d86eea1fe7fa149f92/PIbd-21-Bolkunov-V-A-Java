package lab1_bolkunov_java.Transport;

import lab1_bolkunov_java.Transport.Extensions.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class MotorShip extends Ship {
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

    private IExtension extension;

    public void setExtensionCount(int count) {
        extension.setExtensionCount(count);
    }

    public MotorShip(int maxSpeed, float weight, Color mainColor, Color additionalColor, boolean additionalBoat, boolean helipad, boolean smoke, boolean fire) {
        super(maxSpeed, weight, mainColor);

        this.additionalColor = additionalColor;
        this.additionalBoat = additionalBoat;
        this.helicopterPad = helipad;
        this.smoke = smoke;
        this.fire = fire;
    }

    public void setExtension(IExtension extension) {
        this.extension = extension;
    }

    public void drawTransport(Graphics g) {
        Random rnd = new Random();

        Color brown = Color.getHSBColor(32, 100, 38);

        super.drawTransport(g);

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

        //PIPES AND SMOKE, PATTERNS AND PILLARS
        if (extension != null) {
            extension.Draw(g, this);
        }

        //SMOKE WITHOUT PIPES
        if (smoke && extension == null) {
            int despertionRadius = 15;
            for (int j = 0; j < 5 + rnd.nextInt(10); j++) {
                int rad = 2 + rnd.nextInt(10);
                g.setColor(Color.gray);
                g.fillOval(posX - despertionRadius + rnd.nextInt(despertionRadius + 1),
                        posY - despertionRadius + rnd.nextInt(despertionRadius + 1), rad, rad);
            }
        }

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
