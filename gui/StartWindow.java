package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {
    public StartWindow() {
        setTitle("Bắt đầu Game");
        setSize(300,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnEasy = new JButton("Dễ");
        JButton btnHard = new JButton("Khó");

        btnEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGameWindow(false); // false: Dễ
            }
        });

        btnHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openGameWindow(true); // true: Khó
            }
        });

        setLayout(null);
        btnEasy.setBounds(50, 40, 80, 30);
        btnHard.setBounds(170, 40, 80, 30);

        add(btnEasy);
        add(btnHard);
        setLocationRelativeTo(null);
    }

    private void openGameWindow(boolean isHardMode) {
        Gui gameWindow = new Gui(isHardMode);
        gameWindow.setVisible(true);
        dispose(); // Đóng cửa sổ "START" sau khi mở cửa sổ game chính
    }
}
