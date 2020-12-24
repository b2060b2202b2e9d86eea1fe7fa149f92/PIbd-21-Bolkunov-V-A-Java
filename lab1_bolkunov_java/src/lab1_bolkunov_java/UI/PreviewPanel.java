package lab1_bolkunov_java.UI;

import lab1_bolkunov_java.Transport.Extensions.IExtension;
import lab1_bolkunov_java.Transport.Extensions.PipesExtension;
import lab1_bolkunov_java.Transport.MotorShip;
import lab1_bolkunov_java.Transport.Ship;
import lab1_bolkunov_java.Transport.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.security.cert.Extension;

public class PreviewPanel extends JPanel {

    private Ship transport;

    private Color mainColor;
    private Color additionalColor;

    private int speed;
    private float weight;

    private boolean additionalBoat;
    private boolean helipad;
    private boolean smoke;
    private boolean fire;

    private IExtension extension;
    private int extensionCount;

    public PreviewPanel() {
        super();

        mainColor = Color.BLACK;
        additionalColor = Color.BLACK;

        speed = 100;
        weight = 1000;

        additionalBoat = false;
        helipad = false;
        smoke = false;
        fire = false;

        extensionCount = 1;
        extension = null;
    }

    public Vehicle getTransport() {
        return transport;
    }

    public void createShip() {
        transport = new Ship(speed, weight, mainColor);
        repaint();
    }

    public void createMotorShip() {
        transport = new MotorShip(speed, weight, mainColor, additionalColor, additionalBoat, helipad, smoke, fire);
        if (extension != null) {
            ((MotorShip) transport).setExtension(extension);
            ((MotorShip) transport).setExtensionCount(extensionCount);
        }
        repaint();
    }

    private void updateShip() {
        if (transport != null) {
            if (transport instanceof MotorShip) {
                createMotorShip();
            } else {
                createShip();
            }
        }
    }

    public void setMainColor(Color mainColor) {
        this.mainColor = mainColor;
        updateShip();
        repaint();
    }

    public void setAdditionalColor(Color additionalColor) {
        this.additionalColor = additionalColor;
        updateShip();
        repaint();
    }

    public void setAdditionalColor(int speed) {
        this.speed = speed;
        updateShip();
        repaint();
    }

    public void setAdditionalColor(float weight) {
        this.weight = weight;
        updateShip();
        repaint();
    }

    public void setHelipad(boolean val) {
        helipad = val;
        updateShip();
        repaint();
    }

    public void setAdditionalBoat(boolean val) {
        additionalBoat = val;
        updateShip();
        repaint();
    }

    public void setSmoke(boolean val) {
        smoke = val;
        updateShip();
        repaint();
    }

    public void setFire(boolean val) {
        fire = val;
        updateShip();
        repaint();
    }

    public void setExtension(IExtension ext){
        extension = ext;
        if(transport instanceof MotorShip)
            ((MotorShip)transport).setExtension(extension);
        repaint();
    }

    public void setExtensionCount(int count){
        extensionCount = count;
        if(transport instanceof MotorShip)
            ((MotorShip)transport).setExtensionCount(count);
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (transport != null) {
            transport.setPosition(200,100,this.getWidth(),this.getHeight());
            transport.drawTransport(g);
        }
    }
}
