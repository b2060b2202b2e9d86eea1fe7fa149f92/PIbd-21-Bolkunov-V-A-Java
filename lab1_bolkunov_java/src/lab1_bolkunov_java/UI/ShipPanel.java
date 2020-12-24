package lab1_bolkunov_java.UI;

import lab1_bolkunov_java.Transport.*;
import lab1_bolkunov_java.Transport.Extensions.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.*;

public class ShipPanel extends JPanel {
    private final int gridRowCount = 12;
    private final int gridColumnCount = 8;
    private JPanel[][] panelHolder;

    private JButton moveUpButton;
    private JButton moveDownButton;
    private JButton moveLeftButton;
    private JButton moveRightButton;

    private final String upButtonIconPath = "res/Up.png";
    private final String downButtonIconPath = "res/Down.png";
    private final String leftButtonIconPath = "res/Left.png";
    private final String rightButtonIconPath = "res/Right.png";

    private ITransport transport;

    private Random rnd;

    public ShipPanel() {
        rnd = new Random();

        setLayout(new java.awt.GridLayout(gridRowCount, gridColumnCount));
        panelHolder = new JPanel[gridRowCount][gridColumnCount];
        for (int row = 0; row < gridRowCount; row++)
            for (int clmn = 0; clmn < gridColumnCount; clmn++) {
                panelHolder[row][clmn] = new JPanel();
                add(panelHolder[row][clmn]);
            }

        //������ ��� �������� ����
        moveDownButton = new JButton();
        moveDownButton.setIcon(getScaledImageIcon(downButtonIconPath));
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveShip(Direction.Down);
            }
        });
        panelHolder[gridRowCount - 1][gridColumnCount - 2].add(moveDownButton);

        //������ ��� �������� �����
        moveUpButton = new JButton();
        moveUpButton.setIcon(getScaledImageIcon(upButtonIconPath));
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveShip(Direction.Up);
            }
        });
        panelHolder[gridRowCount - 2][gridColumnCount - 2].add(moveUpButton);

        //������ ��� �������� �����
        moveLeftButton = new JButton();
        moveLeftButton.setIcon(getScaledImageIcon(leftButtonIconPath));
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveShip(Direction.Left);
            }
        });
        panelHolder[gridRowCount - 1][gridColumnCount - 3].add(moveLeftButton);

        //������ ��� �������� ������
        moveRightButton = new JButton();
        moveRightButton.setIcon(getScaledImageIcon(rightButtonIconPath));
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveShip(Direction.Right);
            }
        });
        panelHolder[gridRowCount - 1][gridColumnCount - 1].add(moveRightButton);

    }
    
    private ImageIcon getScaledImageIcon(String path) {
        int newWidth = 40, newHeight = 40;
        ImageIcon icon = new ImageIcon(path);
        Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void moveShip(Direction dir) {
        transport.moveTransport(dir);
        this.repaint();
    }

    public void setShip(ITransport ship){
        this.transport = ship;
        transport.setPosition(200 + rnd.nextInt(100), 200 + rnd.nextInt(100), this.getWidth(), this.getHeight());
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (transport != null) {
            transport.drawTransport(g);
        }
    }
}
