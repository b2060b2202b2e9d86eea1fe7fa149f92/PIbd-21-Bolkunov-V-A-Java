package lab1_bolkunov_java.Transport;

import jdk.jshell.spi.ExecutionControl;
import lab1_bolkunov_java.Transport.Extensions.IExtension;

import java.awt.*;
import java.util.ArrayList;

public class Pier<T1 extends ITransport, T2 extends IExtension> {
    private final ArrayList<T1> places;
    private final int maxSize;

    private final int pictureWidth;
    private final int pictureHeight;

    private final int placeSizeWidth = 350;
    private final int placeSizeHeight = 100;

    public Pier(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;

        int widthPlaceCount = pictureWidth / placeSizeWidth;
        int heightPlaceCount = pictureHeight / placeSizeHeight;

        places = new ArrayList<T1>();
        maxSize = widthPlaceCount * heightPlaceCount;
    }

    //OPERATORS

    //'+' - operator
    public boolean add(T1 ship) {
        if (places.size() < maxSize) {
            places.add(ship);
            int width = pictureWidth / placeSizeWidth;
            int height = pictureHeight / placeSizeHeight;
            int column = places.indexOf(ship) / height;
            int row = places.indexOf(ship) % height;
            ship.setPosition(column * placeSizeWidth + placeSizeWidth / 2, row * placeSizeHeight + placeSizeHeight / 2, pictureWidth, pictureHeight);
            return true;
        }
        return false;
    }

    //'-' - operator
    public T1 substract(int index) {
        if (index >= 0 && index < places.size()) {
            T1 res = places.get(index);
            places.remove(index);
            return res;
        }
        return null;
    }

    //'>' - operator
    public boolean more(int count) {
        return places.size() > count;
    }

    //'<' - operator
    public boolean less(int count) {
        return places.size() < count;
    }

    public T1 getTransport(int index) {
        if(places.size() > index)
            return places.get(index);
        else
            return null;
    }

    //DRAWING

    public void draw(Graphics g) {
        drawMarking(g);
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i) != null)
                places.get(i).drawTransport(g);
        }
    }

    private void drawMarking(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i < pictureWidth / placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / placeSizeHeight + 1; ++j) {
                g.drawLine(i * placeSizeWidth, j * placeSizeHeight, i * placeSizeWidth + placeSizeWidth / 2, j * placeSizeHeight);
            }
            g.drawLine(i * placeSizeWidth, 0, i * placeSizeWidth, (pictureHeight / placeSizeHeight) * placeSizeHeight);
        }
    }
}
