package lab1_bolkunov_java.UI;

import lab1_bolkunov_java.Transport.*;
import lab1_bolkunov_java.Transport.Extensions.*;
import javax.swing.*;
import java.awt.*;

public class PierPanel extends JPanel {
    private Pier<ITransport, IExtension> pier;

    public PierPanel(){
        super();
        resetPier();
    }

    public void resetPier(){
        pier = new Pier<ITransport, IExtension>(this.getWidth(), this.getHeight());
    }

    public Pier<ITransport, IExtension> getPier() {
        return pier;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pier.draw(g);
    }

    public void redraw(){
        repaint();
    }
}
