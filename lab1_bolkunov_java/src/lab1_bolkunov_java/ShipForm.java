package lab1_bolkunov_java;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Button;

public class ShipForm {
    private JFrame frame;

    private JPanel shipPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShipForm window = new ShipForm();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public ShipForm() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shipPanel = new ShipPanel();
        shipPanel.setBounds(100, 100, 1200, 800);
        frame.getContentPane().add(shipPanel, BorderLayout.CENTER);
    }

}
