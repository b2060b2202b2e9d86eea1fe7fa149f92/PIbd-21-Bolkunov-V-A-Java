package lab1_bolkunov_java.UI;

import lab1_bolkunov_java.Transport.*;
import lab1_bolkunov_java.Transport.Extensions.*;
import javax.swing.*;
import java.awt.*;

public class PierPanel extends JPanel {
    private Pier<Vehicle, IExtension> pier;

    public PierPanel(){
        super();
    }

    public Pier<Vehicle, IExtension> getPier() {
        return pier;
    }

    public void setPier(Pier<Vehicle, IExtension> pier) { this.pier = pier; }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(pier != null){
            pier.draw(g);
        }
    }

    public void redraw(){
        repaint();
    }
}
