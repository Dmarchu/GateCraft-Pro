import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class StartUp {
    public static Timer timer;
    public static void createStartUpFrame() {
        JFrame startupFrame = new JFrame("GateCraft Pro"); startupFrame.setResizable(false); startupFrame.setIconImage(Utils.scaledImage("l3p.png",32,32));
        startupFrame.setSize(500,350); startupFrame.setUndecorated(true); startupFrame.setLocationRelativeTo(null);
        startupFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); startupFrame.setVisible(true);
        startupFrame.setBackground(Utils.black); startupFrame.setLayout(null); startupFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Utils.gray, 5, true));

        JLayeredPane startupPanel = new JLayeredPane();
        startupPanel.setBounds(0,0,500,400); startupPanel.setBackground(Utils.black);
        startupPanel.setLayout(null); startupFrame.add(startupPanel);

        JLabel background = new JLabel();
        background.setBounds(0,0,520,420); background.setIcon(Utils.scaledIcon("installb.png", 510, 420));
        startupPanel.add(background); startupPanel.setLayer(background,0); background.setLayout(null);

        JLabel logo = new JLabel(); logo.setBounds(5,20,216,72);
        logo.setIcon(Utils.scaledIcon("l2p.png", 216,72));
        startupPanel.add(logo); startupPanel.setLayer(logo, 100);

        timer = new Timer(0, e1 -> {
            startupFrame.dispose();
            App.startApp();
            timer.stop();
        }); timer.start();
    }
}
