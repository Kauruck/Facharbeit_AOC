package com.kauruck.UI;

import com.kauruck.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

    private MainPanel panel;

    public MainFrame(){
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.exit();
            }
        });

        panel = new MainPanel();
        panel.setFocusable(true);
        this.add(panel);

        Toolkit tk = Toolkit.getDefaultToolkit();
        this.setSize(tk.getScreenSize());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
