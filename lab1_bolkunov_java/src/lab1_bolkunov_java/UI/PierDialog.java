package lab1_bolkunov_java.UI;

import lab1_bolkunov_java.Transport.*;
import lab1_bolkunov_java.Transport.Extensions.IExtension;
import lab1_bolkunov_java.Transport.Extensions.PipesExtension;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;
import java.util.Stack;

public class PierDialog extends JFrame {
    private final Random rnd = new Random();
    private final PierCollection pierCollection;
    private Stack<ITransport> extractedTransport;

    private JPanel mainPanel;
    private JPanel pierPanel;
    private JButton createShipButton;
    private JSpinner pierPlaceSpinner;
    private JButton takeShipButton;
    private JPanel controlsPanel;
    private JButton createPierButton;
    private JButton removePierButton;
    private JList pierListBox;
    private JTextField pierNameTextField;
    private JButton drawShipButton;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem saveSingleMenuItem;
    private JMenuItem loadSIngleMenuItem;

    public PierDialog() {
        $$$setupUI$$$();
        setContentPane(mainPanel);
        //setModal(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ShipConfigDialog configDialog = new ShipConfigDialog(new IShipAction() {
                    @Override
                    public void Action(Vehicle ship) {
                        if (ship != null) {
                            if (((PierPanel) pierPanel).getPier().add(ship)) {
                                ((PierPanel) pierPanel).redraw();
                            } else {
                                showDialog("Причал переполнен");
                            }
                        } else {
                            showDialog("Корабль не был создан");
                        }
                        ((PierPanel) pierPanel).redraw();
                    }
                });
                configDialog.pack();
                configDialog.setVisible(true);
            }
        });

        extractedTransport = new Stack<ITransport>();

        takeShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ITransport ship = ((PierPanel) pierPanel).getPier().substract((int) pierPlaceSpinner.getValue());
                if (ship != null) {
                    extractedTransport.push(ship);
                } else {
                    showDialog("Данное парковочное место свободно либо не существует");
                }
                ((PierPanel) pierPanel).redraw();
            }
        });

        drawShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (extractedTransport.empty()) {
                    showDialog("Извлеченные корабли отсутствуют");
                } else {
                    ITransport extractedShip = extractedTransport.pop();
                    ShipForm.drawPanel(extractedShip);
                }
            }
        });

        this.setResizable(false);
        ((PierPanel) pierPanel).redraw();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
                ((PierPanel) pierPanel).redraw();
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                System.exit(0);
            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {
            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {
            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {
            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {
            }

        });

        pierCollection = new PierCollection(pierPanel.getPreferredSize().width, pierPanel.getPreferredSize().height);

        pierListBox.setListData(pierCollection.getKeys());

        pierListBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String selectedValue = (String) pierListBox.getSelectedValue();
                ((PierPanel) pierPanel).setPier(pierCollection.getPier(selectedValue));
                ((PierPanel) pierPanel).repaint();
            }
        });

        createPierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pierNameTextField.getText() != "" && pierCollection.addPier(pierNameTextField.getText())) {
                    pierListBox.setListData(pierCollection.getKeys());
                }
            }
        });

        removePierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pierCollection.getPier((String) pierListBox.getSelectedValue()) != null) {
                    pierCollection.removePier((String) pierListBox.getSelectedValue());
                    pierListBox.setListData(pierCollection.getKeys());
                    ((PierPanel) pierPanel).setPier(null);
                    ((PierPanel) pierPanel).repaint();
                }
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Сохранить причал");
                if (fc.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    if (pierCollection.saveAllData(fc.getSelectedFile().getAbsolutePath())) {
                        showDialog("Сохранение успешно");
                    } else {
                        showDialog("Сохранение не удалось");
                    }
                }
            }
        });

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Загрузить причал");
                if (fc.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    if (pierCollection.loadAllData(fc.getSelectedFile().getAbsolutePath())) {
                        showDialog("Загрузка успешна");
                    } else {
                        showDialog("Загрузка не удалась");
                    }
                    pierListBox.setListData(pierCollection.getKeys());
                }
            }
        });

        saveSingleMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Сохранить причал");
                if (fc.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    if (pierCollection.saveData(fc.getSelectedFile().getAbsolutePath(), ((String) pierListBox.getSelectedValue()))) {
                        showDialog("Сохранение успешно");
                    } else {
                        showDialog("Сохранение не удалось");
                    }
                }
            }
        });

        loadSIngleMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Загрузить причал");
                if (fc.showSaveDialog(mainPanel) == JFileChooser.APPROVE_OPTION) {
                    if (pierCollection.loadData(fc.getSelectedFile().getAbsolutePath())) {
                        showDialog("Загрузка успешна");
                    } else {
                        showDialog("Загрузка не удалась");
                    }
                    pierListBox.setListData(pierCollection.getKeys());
                }
            }
        });
    }

    private void showDialog(String text) {
        JOptionPane.showMessageDialog(this, text);
    }

    public static void main(String[] args) {
        PierDialog dialog = new PierDialog();
        dialog.pack();
        dialog.setVisible(true);
        //System.exit(0);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setPreferredSize(new Dimension(1200, 800));
        pierPanel.setPreferredSize(new Dimension(1000, 800));
        mainPanel.add(pierPanel, BorderLayout.CENTER);
        controlsPanel = new JPanel();
        controlsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        controlsPanel.setPreferredSize(new Dimension(200, 800));
        mainPanel.add(controlsPanel, BorderLayout.EAST);
        createShipButton = new JButton();
        createShipButton.setText("Создать корабль");
        controlsPanel.add(createShipButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        controlsPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        controlsPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Забрать корабль");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Номер места:");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pierPlaceSpinner = new JSpinner();
        panel1.add(pierPlaceSpinner, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        takeShipButton = new JButton();
        takeShipButton.setLabel("Забрать корабль");
        takeShipButton.setText("Забрать корабль");
        panel1.add(takeShipButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        drawShipButton = new JButton();
        drawShipButton.setText("Нарисовать корабль");
        panel1.add(drawShipButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        controlsPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        controlsPanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Причалы:");
        panel2.add(label3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        createPierButton = new JButton();
        createPierButton.setText("Создать причал");
        panel2.add(createPierButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        removePierButton = new JButton();
        removePierButton.setText("Удалить причал");
        panel2.add(removePierButton, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pierListBox = new JList();
        panel2.add(pierListBox, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        pierNameTextField = new JTextField();
        panel2.add(pierNameTextField, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JMenuBar menuBar1 = new JMenuBar();
        menuBar1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(menuBar1, BorderLayout.NORTH);
        final JMenu menu1 = new JMenu();
        menu1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        menu1.setText("Файл");
        menuBar1.add(menu1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        saveMenuItem = new JMenuItem();
        saveMenuItem.setText("Сохранить");
        menu1.add(saveMenuItem);
        loadMenuItem = new JMenuItem();
        loadMenuItem.setText("Загрузить");
        menu1.add(loadMenuItem);
        saveSingleMenuItem = new JMenuItem();
        saveSingleMenuItem.setText("Сохранить парковку");
        menu1.add(saveSingleMenuItem);
        loadSIngleMenuItem = new JMenuItem();
        loadSIngleMenuItem.setText("Загрузить парковку");
        menu1.add(loadSIngleMenuItem);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

    private void createUIComponents() {
        pierPanel = new PierPanel();
    }
}
