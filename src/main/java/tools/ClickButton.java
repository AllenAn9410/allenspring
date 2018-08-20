package tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ClickButton {
    private int buttonX = 2;
    private int buttonY = 2;
    private int buttonWidth;
    private int buttonHeight;
    int[] levels = {5, 10, 15};
    JFrame frame;
    JPanel panel;
    JButton clickButton;

    public ClickButton(int indexLevel) {
        if (indexLevel < 0 || indexLevel > levels.length) {
            buttonWidth = 1;
        } else {
            buttonWidth = levels[indexLevel];
        }
        buttonHeight = buttonWidth;
        frame = new JFrame("CLICK TEST");
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        clickButton = new JButton();
        clickButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

        panel.add(clickButton);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

        clickButton.addActionListener(new ButtonClick());

    }

    private class ButtonClick implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            buttonX = getNum();
            buttonY = getNum();
            clickButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
            panel.validate();
            panel.repaint();
        }
    }

    private int getNum() {
        Random a = new Random();
        return a.nextInt(685);
    }

}
