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

    private JButton createMotorShipButton;
    private JButton moveUpButton;
    private JButton moveDownButton;
    private JButton moveLeftButton;
    private JButton moveRightButton;
    private JSpinner transportExtensionCountSpinner;
    private JSpinner transportExtensionSpinner;

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

        //Кнопка для создания корабля
        createMotorShipButton = new JButton("Создать корабль");
        createMotorShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                createShip();
            }
        });
        panelHolder[0][0].add(createMotorShipButton);


        //Кнопка для создания теплохода
        createMotorShipButton = new JButton("Создать теплоход");
        createMotorShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                createMotorShip();
            }
        });
        panelHolder[0][1].add(createMotorShipButton);

        //Кнопка для движения вниз
        moveDownButton = new JButton();
        moveDownButton.setIcon(getScaledImageIcon(downButtonIconPath));
        moveDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveShip(Direction.Down);
            }
        });
        panelHolder[gridRowCount - 1][gridColumnCount - 2].add(moveDownButton);

        //Кнопка для движения вверх
        moveUpButton = new JButton();
        moveUpButton.setIcon(getScaledImageIcon(upButtonIconPath));
        moveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveShip(Direction.Up);
            }
        });
        panelHolder[gridRowCount - 2][gridColumnCount - 2].add(moveUpButton);

        //Кнопка для движения влево
        moveLeftButton = new JButton();
        moveLeftButton.setIcon(getScaledImageIcon(leftButtonIconPath));
        moveLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveShip(Direction.Left);
            }
        });
        panelHolder[gridRowCount - 1][gridColumnCount - 3].add(moveLeftButton);

        //Кнопка для движения вправо
        moveRightButton = new JButton();
        moveRightButton.setIcon(getScaledImageIcon(rightButtonIconPath));
        moveRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveShip(Direction.Right);
            }
        });
        panelHolder[gridRowCount - 1][gridColumnCount - 1].add(moveRightButton);

        //Крутилка для выбора улучшения
        SpinnerListModel spinnerExtensionModel = new SpinnerListModel(new IExtension[]{new SmallPipesExtension(1), new HugePipesExtension(1), new PipesExtension(1)});
        transportExtensionSpinner = new JSpinner(spinnerExtensionModel);
        transportExtensionSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg) {
                if (transport instanceof MotorShip) {
                    ((MotorShip) transport).setExtension(getCurrentSelectedExtension());
                    ((MotorShip) transport).setExtensionCount(IExtension.convertExtensionCountToInt(getCurrentSelectedPipeCount()));
                    repaint();
                }
            }
        });
        transportExtensionSpinner.setEnabled(false);
        transportExtensionSpinner.setPreferredSize(new Dimension(80, 20));
        panelHolder[0][gridColumnCount - 1].add(transportExtensionSpinner);

        //Крутилка для выбора кол-ва
        SpinnerListModel spinnerCountModel = new SpinnerListModel(ExtensionCount.values());
        transportExtensionCountSpinner = new JSpinner(spinnerCountModel);
        transportExtensionCountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg) {
                if (transport instanceof MotorShip) {
                    ((MotorShip) transport).setExtensionCount(IExtension.convertExtensionCountToInt(getCurrentSelectedPipeCount()));
                    repaint();
                }
            }
        });
        transportExtensionCountSpinner.setEnabled(false);
        transportExtensionCountSpinner.setPreferredSize(new Dimension(80, 20));
        panelHolder[1][gridColumnCount - 1].add(transportExtensionCountSpinner);
    }

    private ExtensionCount getCurrentSelectedPipeCount() {
        return (ExtensionCount) transportExtensionCountSpinner.getValue();
    }

    private IExtension getCurrentSelectedExtension() {
        return (IExtension) transportExtensionSpinner.getValue();
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

    private void createShip() {
        transport = new Ship(100 + rnd.nextInt(200), 1000 + rnd.nextInt(1000), Color.pink);
        transport.setPosition(200 + rnd.nextInt(100), 200 + rnd.nextInt(100), this.getWidth(), this.getHeight());
        this.repaint();
    }

    private void createMotorShip() {
        transport = new MotorShip(100 + rnd.nextInt(200), 1000 + rnd.nextInt(1000), Color.pink, Color.cyan, true, true, true, true);
        transport.setPosition(200 + rnd.nextInt(100), 200 + rnd.nextInt(100), this.getWidth(), this.getHeight());
        ((MotorShip) transport).setExtension(getCurrentSelectedExtension());
        ((MotorShip) transport).setExtensionCount(IExtension.convertExtensionCountToInt(getCurrentSelectedPipeCount()));
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (transport != null) {
            transport.drawTransport(g);
            if (transport instanceof MotorShip) {
                transportExtensionCountSpinner.setEnabled(true);
                transportExtensionSpinner.setEnabled(true);
            } else {
                transportExtensionCountSpinner.setEnabled(false);
                transportExtensionSpinner.setEnabled(false);
            }
        }
    }
}
