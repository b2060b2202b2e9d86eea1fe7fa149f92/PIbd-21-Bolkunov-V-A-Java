package lab1_bolkunov_java.UI;

import lab1_bolkunov_java.Transport.ITransport;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class ShipForm {
    private JFrame frame;

    private JPanel shipPanel;

    private final static Dimension preferedSize = new Dimension(1200,800);

    public ShipForm() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 800);
        frame.setPreferredSize(preferedSize);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        shipPanel = new ShipPanel();
        shipPanel.setBounds(100, 100, 1200, 800);
        shipPanel.setPreferredSize(preferedSize);
        frame.getContentPane().add(shipPanel, BorderLayout.CENTER);
    }

    public static void drawPanel(ITransport ship){
        ShipForm shipForm = new ShipForm();
        ((ShipPanel)shipForm.shipPanel).setShip(ship);
        shipForm.frame.pack();
        shipForm.frame.setVisible(true);
        shipForm.frame.setPreferredSize(preferedSize);
    }
}
