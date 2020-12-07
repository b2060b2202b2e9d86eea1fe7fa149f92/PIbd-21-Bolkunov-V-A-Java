package lab1_bolkunov_java.Transport;

import lab1_bolkunov_java.Transport.Extensions.IExtension;

import java.awt.*;

public class Pier<T1 extends ITransport, T2 extends IExtension> {
    private final T1[] places;

    private final int pictureWidth;
    private final int pictureHeight;

    private final int placeSizeWidth = 350;
    private final int placeSizeHeight = 100;

    public Pier(int pictureWidth, int pictureHeight) {
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;

        int widthPlaceCount = pictureWidth / placeSizeWidth;
        int heightPlaceCount = pictureHeight / placeSizeHeight;

        places = (T1[]) new ITransport[widthPlaceCount * heightPlaceCount];
    }

    //OPERATORS

    //'+' - operator
    public boolean add(T1 ship) {
        for (int i = 0; i < places.length; i++) {
            if (places[i] == null) {
                places[i] = ship;
                int width = pictureWidth / placeSizeWidth;
                int height = pictureHeight / placeSizeHeight;
                int column = i / height;
                int row = i % height;
                ship.setPosition(column * placeSizeWidth + placeSizeWidth / 2, row * placeSizeHeight + placeSizeHeight / 2, pictureWidth, pictureHeight);
                return true;
            }
        }
        return false;
    }

    //'-' - operator
    public T1 substract( int index) {
        if (index >= 0 && index < places.length) {
            var res = places[index];
            places[index] = null;
            return res;
        }
        return null;
    }

    private int countOwnedPlaces() {
        int res = 0;
        for (int i = 0; i < places.length; i++) {
            if (places[i] != null) {
                res++;
            }
        }
        return res;
    }

    //'>' - operator
    public boolean more(int count) {
        return countOwnedPlaces() > count;
    }

    //'<' - operator
    public boolean less(int count) {
        return countOwnedPlaces() < count;
    }

    //DRAWING

    public void draw(Graphics g) {
        drawMarking(g);
        for (int i = 0; i < places.length; i++) {
            if (places[i] != null)
                places[i].drawTransport(g);
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
