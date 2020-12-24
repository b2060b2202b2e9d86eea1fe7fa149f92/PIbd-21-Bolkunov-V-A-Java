package lab1_bolkunov_java.UI;

import lab1_bolkunov_java.Transport.Extensions.HugePipesExtension;
import lab1_bolkunov_java.Transport.Extensions.IExtension;
import lab1_bolkunov_java.Transport.Extensions.PipesExtension;
import lab1_bolkunov_java.Transport.Extensions.SmallPipesExtension;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.IOException;

public class ShipConfigDialog extends JDialog {
    private IShipAction shipAction;

    private TransferHandler transferHandler;

    private JPanel contentPane;
    private JPanel shipTypePanel;
    private JPanel previewPanel;
    private JPanel colorsPanel;
    private JPanel parametersPanel;
    private JPanel buttonsPanel;

    private JPanel shipTypeChooserPanel;
    private JPanel motorShipTypeChooserPanel;

    private JPanel mainColorPanel;
    private JPanel additionalColorPanel;

    private JPanel redPanel;
    private JPanel yellowPanel;
    private JPanel pinkPanel;
    private JPanel bluePanel;
    private JPanel blackPanel;
    private JPanel greenPanel;
    private JPanel whitePanel;
    private JPanel orangePanel;

    private JSpinner speedSpinner;
    private JSpinner weightSpinner;

    private JPanel groupPanel;
    private JCheckBox additionalBoatCheckBox;
    private JCheckBox helipadCheckBox;
    private JCheckBox fireCheckBox;
    private JCheckBox smokeCheckBox;

    private JPanel extensionPanel;
    private JSpinner extensionCountSpinner;
    private JPanel extensionsContainerPanel;
    private JPanel smallPipesExtensionPanel;
    private JPanel pipesExtensionPanel;
    private JPanel hugePipesExtensionPanel;

    private JButton addButton;
    private JButton cancelButton;

    public ShipConfigDialog(IShipAction action) {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);

        shipAction = action;

        additionalBoatCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ((PreviewPanel) previewPanel).setAdditionalBoat(additionalBoatCheckBox.isSelected());
            }
        });

        helipadCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ((PreviewPanel) previewPanel).setHelipad(helipadCheckBox.isSelected());
            }
        });

        fireCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ((PreviewPanel) previewPanel).setFire(fireCheckBox.isSelected());
            }
        });

        smokeCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ((PreviewPanel) previewPanel).setSmoke(smokeCheckBox.isSelected());
            }
        });

        previewPanel.setTransferHandler(new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean importData(TransferSupport info) {
                Transferable t = info.getTransferable();
                try {
                    if (t.getTransferData(DataFlavor.stringFlavor) instanceof IExtension) {
                        ((PreviewPanel) previewPanel).setExtension((IExtension) t.getTransferData(DataFlavor.stringFlavor));
                        ((PreviewPanel) previewPanel).setExtensionCount((int) extensionCountSpinner.getValue());
                        return true;
                    } else {
                        String str = (String) t.getTransferData(DataFlavor.stringFlavor);
                        switch (str) {
                            case "ship": {
                                ((PreviewPanel) previewPanel).createShip();
                                return true;
                            }
                            case "motorShip": {
                                ((PreviewPanel) previewPanel).createMotorShip();
                                return true;
                            }
                            default:
                                return false;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            public boolean canImport(TransferSupport info) {
                try {
                    return info.isDataFlavorSupported(DataFlavor.stringFlavor) || info.getTransferable().getTransferData(DataFlavor.stringFlavor) instanceof IExtension;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        mainColorPanel.setTransferHandler(new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean importData(TransferSupport info) {
                Transferable t = info.getTransferable();
                try {
                    if (t.getTransferData(DataFlavor.stringFlavor) instanceof Color) {
                        ((PreviewPanel) previewPanel).setMainColor((Color) t.getTransferData(DataFlavor.stringFlavor));
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }

            public boolean canImport(TransferSupport info) {
                try {
                    return info.isDataFlavorSupported(DataFlavor.stringFlavor) && info.getTransferable().getTransferData(DataFlavor.stringFlavor) instanceof Color;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        additionalColorPanel.setTransferHandler(new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean importData(TransferSupport info) {
                Transferable t = info.getTransferable();
                try {
                    if (t.getTransferData(DataFlavor.stringFlavor) instanceof Color) {
                        ((PreviewPanel) previewPanel).setAdditionalColor((Color) t.getTransferData(DataFlavor.stringFlavor));
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }

            public boolean canImport(TransferSupport info) {
                try {
                    return info.isDataFlavorSupported(DataFlavor.stringFlavor) && info.getTransferable().getTransferData(DataFlavor.stringFlavor) instanceof Color;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        extensionCountSpinner.setModel(new SpinnerNumberModel(1, 1, 4, 1));
        weightSpinner.setModel(new SpinnerNumberModel(1000, 700, 1300, 1));
        speedSpinner.setModel(new SpinnerNumberModel(100, 70, 130, 1));

        //Ship types
        shipTypeChooserPanel.setTransferHandler(getStringTransferHandler("ship"));
        shipTypeChooserPanel.addMouseListener(getMouseAdapter());

        motorShipTypeChooserPanel.setTransferHandler(getStringTransferHandler("motorShip"));
        motorShipTypeChooserPanel.addMouseListener(getMouseAdapter());

        //Extensions
        smallPipesExtensionPanel.setTransferHandler(getExtensionTransferHandler(new SmallPipesExtension((int) extensionCountSpinner.getValue())));
        smallPipesExtensionPanel.addMouseListener(getMouseAdapter());

        pipesExtensionPanel.setTransferHandler(getExtensionTransferHandler(new PipesExtension((int) extensionCountSpinner.getValue())));
        pipesExtensionPanel.addMouseListener(getMouseAdapter());

        hugePipesExtensionPanel.setTransferHandler(getExtensionTransferHandler(new HugePipesExtension((int) extensionCountSpinner.getValue())));
        hugePipesExtensionPanel.addMouseListener(getMouseAdapter());

        //Colors
        redPanel.setTransferHandler(getColorTransferHandler(redPanel.getBackground()));
        redPanel.addMouseListener(getMouseAdapter());

        yellowPanel.setTransferHandler(getColorTransferHandler(yellowPanel.getBackground()));
        yellowPanel.addMouseListener(getMouseAdapter());

        pinkPanel.setTransferHandler(getColorTransferHandler(pinkPanel.getBackground()));
        pinkPanel.addMouseListener(getMouseAdapter());

        bluePanel.setTransferHandler(getColorTransferHandler(bluePanel.getBackground()));
        bluePanel.addMouseListener(getMouseAdapter());

        blackPanel.setTransferHandler(getColorTransferHandler(blackPanel.getBackground()));
        blackPanel.addMouseListener(getMouseAdapter());

        greenPanel.setTransferHandler(getColorTransferHandler(greenPanel.getBackground()));
        greenPanel.addMouseListener(getMouseAdapter());

        whitePanel.setTransferHandler(getColorTransferHandler(whitePanel.getBackground()));
        whitePanel.addMouseListener(getMouseAdapter());

        orangePanel.setTransferHandler(getColorTransferHandler(orangePanel.getBackground()));
        orangePanel.addMouseListener(getMouseAdapter());

        //Buttons
        Dialog dialog = this;

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (shipAction != null) {
                    shipAction.Action(((PreviewPanel) previewPanel).getTransport());
                }
                dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    private void createUIComponents() {
        previewPanel = new PreviewPanel();
    }

    private MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    JComponent c = (JComponent) e.getSource();
                    TransferHandler handler = c.getTransferHandler();
                    handler.exportAsDrag(c, e, TransferHandler.COPY);
                }
            }
        };
    }

    private TransferHandler getStringTransferHandler(String transferedData) {
        return new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean canImport(TransferSupport support) {
                return false;
            }

            protected Transferable createTransferable(JComponent c) {
                return new Transferable() {
                    @Override
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[]{DataFlavor.stringFlavor};
                    }

                    @Override
                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor == DataFlavor.stringFlavor;
                    }

                    @Override
                    public Object getTransferData(DataFlavor flavor) {
                        return transferedData;
                    }
                };
            }
        };
    }

    private TransferHandler getColorTransferHandler(Color color) {
        return new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean canImport(TransferSupport support) {
                return false;
            }

            protected Transferable createTransferable(JComponent c) {
                return new Transferable() {
                    @Override
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[]{DataFlavor.stringFlavor};
                    }

                    @Override
                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor == DataFlavor.stringFlavor;
                    }

                    @Override
                    public Object getTransferData(DataFlavor flavor) {
                        return color;
                    }
                };
            }
        };
    }

    private TransferHandler getExtensionTransferHandler(IExtension transferedExtension) {
        return new TransferHandler() {
            public int getSourceActions(JComponent c) {
                return TransferHandler.COPY;
            }

            public boolean canImport(TransferSupport support) {
                return false;
            }

            protected Transferable createTransferable(JComponent c) {
                return new Transferable() {
                    @Override
                    public DataFlavor[] getTransferDataFlavors() {
                        return new DataFlavor[]{DataFlavor.stringFlavor};
                    }

                    @Override
                    public boolean isDataFlavorSupported(DataFlavor flavor) {
                        return flavor == DataFlavor.stringFlavor;
                    }

                    @Override
                    public Object getTransferData(DataFlavor flavor) {
                        transferedExtension.setExtensionCount((int) extensionCountSpinner.getValue());
                        return transferedExtension;
                    }
                };
            }
        };
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
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1, true, false));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        shipTypePanel = new JPanel();
        shipTypePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(shipTypePanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, 300), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Тип корабля");
        shipTypePanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        shipTypeChooserPanel = new JPanel();
        shipTypeChooserPanel.setLayout(new BorderLayout(0, 0));
        shipTypePanel.add(shipTypeChooserPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        shipTypeChooserPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JLabel label2 = new JLabel();
        label2.setHorizontalAlignment(0);
        label2.setHorizontalTextPosition(0);
        label2.setText("Обычный корбль");
        shipTypeChooserPanel.add(label2, BorderLayout.CENTER);
        motorShipTypeChooserPanel = new JPanel();
        motorShipTypeChooserPanel.setLayout(new BorderLayout(0, 0));
        shipTypePanel.add(motorShipTypeChooserPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        motorShipTypeChooserPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JLabel label3 = new JLabel();
        label3.setHorizontalAlignment(0);
        label3.setHorizontalTextPosition(0);
        label3.setText("Теплоход");
        motorShipTypeChooserPanel.add(label3, BorderLayout.CENTER);
        panel1.add(previewPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, 300), null, 0, false));
        colorsPanel = new JPanel();
        colorsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 4, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(colorsPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, 300), null, 0, false));
        mainColorPanel = new JPanel();
        mainColorPanel.setLayout(new BorderLayout(0, 0));
        colorsPanel.add(mainColorPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setHorizontalAlignment(0);
        label4.setHorizontalTextPosition(0);
        label4.setText("Основной цвет");
        mainColorPanel.add(label4, BorderLayout.CENTER);
        additionalColorPanel = new JPanel();
        additionalColorPanel.setLayout(new BorderLayout(0, 0));
        colorsPanel.add(additionalColorPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setHorizontalAlignment(0);
        label5.setHorizontalTextPosition(0);
        label5.setText("Дополнительный цвет");
        additionalColorPanel.add(label5, BorderLayout.CENTER);
        redPanel = new JPanel();
        redPanel.setLayout(new BorderLayout(0, 0));
        redPanel.setBackground(new Color(-62464));
        colorsPanel.add(redPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        redPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        pinkPanel = new JPanel();
        pinkPanel.setLayout(new BorderLayout(0, 0));
        pinkPanel.setBackground(new Color(-65387));
        colorsPanel.add(pinkPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pinkPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        whitePanel = new JPanel();
        whitePanel.setLayout(new BorderLayout(0, 0));
        whitePanel.setBackground(new Color(-1378049));
        colorsPanel.add(whitePanel, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        whitePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        blackPanel = new JPanel();
        blackPanel.setLayout(new BorderLayout(0, 0));
        blackPanel.setBackground(new Color(-16777216));
        colorsPanel.add(blackPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        blackPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        yellowPanel = new JPanel();
        yellowPanel.setLayout(new BorderLayout(0, 0));
        yellowPanel.setBackground(new Color(-3072));
        colorsPanel.add(yellowPanel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        yellowPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        bluePanel = new JPanel();
        bluePanel.setLayout(new BorderLayout(0, 0));
        bluePanel.setBackground(new Color(-16726529));
        colorsPanel.add(bluePanel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        bluePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        greenPanel = new JPanel();
        greenPanel.setLayout(new BorderLayout(0, 0));
        greenPanel.setBackground(new Color(-8782080));
        colorsPanel.add(greenPanel, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        greenPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        orangePanel = new JPanel();
        orangePanel.setLayout(new BorderLayout(0, 0));
        orangePanel.setBackground(new Color(-30720));
        colorsPanel.add(orangePanel, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        orangePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
        final JLabel label6 = new JLabel();
        label6.setHorizontalAlignment(0);
        label6.setHorizontalTextPosition(0);
        label6.setText("Цвета");
        colorsPanel.add(label6, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        parametersPanel = new JPanel();
        parametersPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 9, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(parametersPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, 300), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Максимальная скорость:");
        parametersPanel.add(label7, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Вес:");
        parametersPanel.add(label8, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        speedSpinner = new JSpinner();
        parametersPanel.add(speedSpinner, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        weightSpinner = new JSpinner();
        parametersPanel.add(weightSpinner, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setHorizontalAlignment(0);
        label9.setHorizontalTextPosition(0);
        label9.setText("Параметры");
        parametersPanel.add(label9, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 9, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        groupPanel = new JPanel();
        groupPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        parametersPanel.add(groupPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 2, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        additionalBoatCheckBox = new JCheckBox();
        additionalBoatCheckBox.setText("Спасательная шлюпка");
        groupPanel.add(additionalBoatCheckBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        helipadCheckBox = new JCheckBox();
        helipadCheckBox.setText("Вертолетная площадка");
        groupPanel.add(helipadCheckBox, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fireCheckBox = new JCheckBox();
        fireCheckBox.setText("Пожар");
        groupPanel.add(fireCheckBox, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        smokeCheckBox = new JCheckBox();
        smokeCheckBox.setText("Дым");
        groupPanel.add(smokeCheckBox, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(buttonsPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, 300), null, 0, false));
        addButton = new JButton();
        addButton.setText("Добавить");
        buttonsPanel.add(addButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Отменить");
        buttonsPanel.add(cancelButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        extensionPanel = new JPanel();
        extensionPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(extensionPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, 300), null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Дополнения");
        extensionPanel.add(label10, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Колличество:");
        extensionPanel.add(label11, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        extensionCountSpinner = new JSpinner();
        extensionCountSpinner.setDoubleBuffered(false);
        extensionPanel.add(extensionCountSpinner, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        extensionsContainerPanel = new JPanel();
        extensionsContainerPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        extensionPanel.add(extensionsContainerPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        smallPipesExtensionPanel = new JPanel();
        smallPipesExtensionPanel.setLayout(new BorderLayout(0, 0));
        extensionsContainerPanel.add(smallPipesExtensionPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setHorizontalAlignment(0);
        label12.setText("Маленькие трубы");
        smallPipesExtensionPanel.add(label12, BorderLayout.CENTER);
        pipesExtensionPanel = new JPanel();
        pipesExtensionPanel.setLayout(new BorderLayout(0, 0));
        extensionsContainerPanel.add(pipesExtensionPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setHorizontalAlignment(0);
        label13.setHorizontalTextPosition(0);
        label13.setText("Средние трубы");
        pipesExtensionPanel.add(label13, BorderLayout.CENTER);
        hugePipesExtensionPanel = new JPanel();
        hugePipesExtensionPanel.setLayout(new BorderLayout(0, 0));
        extensionsContainerPanel.add(hugePipesExtensionPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setHorizontalAlignment(0);
        label14.setHorizontalTextPosition(0);
        label14.setText("Огромные трубы");
        hugePipesExtensionPanel.add(label14, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}

