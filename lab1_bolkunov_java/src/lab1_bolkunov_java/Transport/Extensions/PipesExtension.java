package lab1_bolkunov_java.Transport.Extensions;

import lab1_bolkunov_java.Transport.Direction;
import lab1_bolkunov_java.Transport.MotorShip;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class PipesExtension implements IExtension //КлассДоп
{
    private Random rnd;
    private ExtensionCount extensionCount;
    private int pipeRadius;

    private PipesExtension() {
        rnd = new Random();
        pipeRadius = rnd.nextInt(4) + 12;
    }

    public PipesExtension(int count) {
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
        if (extensionCount != null)
            for (int i = 0; i < IExtension.convertExtensionCountToInt(extensionCount) + 1; i++) {
                //PIPES
                if (transport.getHelicopterPad() & i == (int) IExtension.convertExtensionCountToInt(extensionCount) / 2)
                    continue;

                int pipePointX, pipePointY;
                if (transport.getLastDirection() == Direction.Left || transport.getLastDirection() == Direction.Right) {
                    pipePointX = transport.getPosX() + (-IExtension.convertExtensionCountToInt(extensionCount) / 2 + i) * pipeRadius * 3;
                    pipePointY = transport.getPosY();
                } else {
                    pipePointX = transport.getPosX();
                    pipePointY = transport.getPosY() + (-IExtension.convertExtensionCountToInt(extensionCount) / 2 + i) * pipeRadius * 3;
                }

                g.setColor(transport.getAdditionalColor());
                g.fillOval(pipePointX - pipeRadius, pipePointY - pipeRadius, pipeRadius * 2, pipeRadius * 2);
                g.setColor(Color.black);
                g.drawOval(pipePointX - pipeRadius, pipePointY - pipeRadius, pipeRadius * 2, pipeRadius * 2);

                //SMOKE
                if (transport.getSmoke()) {
                    int despertionRadius = 15;
                    for (int j = 0; j < 5 + rnd.nextInt(10); j++) {
                        int rad = 2 + rnd.nextInt(10);
                        g.setColor(Color.gray);
                        g.fillOval(pipePointX - despertionRadius + rnd.nextInt(despertionRadius + 1),
                                pipePointY - despertionRadius + rnd.nextInt(despertionRadius + 1), rad, rad);
                    }
                }
            }
    }

    @Override
    public String toString() {
        return "Трубы";
    }
}
