import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static int width = 1200, height = 800, widthmem = 1200, heightmem = 800;
    public static int posX, posY, lastX, lastY;
    public static JFrame main;
    public static JLayeredPane panel;
    public static JPanel topBar, sideBar;
    public static JButton closeB, maxB, minB;
    public static RoundedButton saveB, downB, gateB, runB, cableB, configB, infoB, userB, projectB, forumB, inviteB;
    public static RoundedLabel info;
    public static Project dialog;
    public static Components components;
    public static WorkSpace workSpace;
    public static Timer timer = new Timer(0,null);
    public static boolean running = false;
    public static Color temp;

    public static void startApp() {
        MouseListener bl = new buttonListener();
        //if(!Login.isLogged()) Login.showLogin();
        main = new JFrame("GateCraft Pro"); main.setUndecorated(true); main.setVisible(true); main.setLayout(null); main.setIconImage(Utils.scaledImage("l3p.png",50,50));
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); main.setSize(width,height); main.setBackground(new Color(0,0,0,0));
        main.setLocationRelativeTo(null); main.getRootPane().setBorder(BorderFactory.createLineBorder(Utils.gray,3,true));

        panel = new JLayeredPane(); panel.setLocation(0,0); panel.setSize(main.getSize()); panel.setLayout(null); main.add(panel);
        topBar = new JPanel(); topBar.setBounds(0,0,width, 50); topBar.setBackground(Utils.black);
        topBar.setBorder(BorderFactory.createLineBorder(Utils.gray,3,false)); topBar.setLayout(null); panel.add(topBar);

        JLabel cornerLogo = new JLabel(new NoScalingIcon(Utils.scaledIcon("l3p.png",34,34)));
        cornerLogo.setBounds(10,10,34,34); topBar.add(cornerLogo);

        sideBar = new JPanel(); sideBar.setBounds(0, 47,50,height - 50); sideBar.setBackground(Utils.black);
        sideBar.setBorder(BorderFactory.createLineBorder(Utils.gray,2,false)); sideBar.setLayout(null); panel.add(sideBar);

        info = new RoundedLabel("Texto", 15,15); info.setBounds(50,50,50,25); info.setVisible(false);
        info.setBackground(Utils.black); info.setFont(new Font("Verdana", Font.BOLD, 10)); panel.add(info);

        workSpace = new WorkSpace(); workSpace.setBounds(50,50,width - 50, height - 50); workSpace.setBackground(Utils.darkGray);
        workSpace.setLayout(null); panel.add(workSpace);

        dialog = new Project(); panel.add(dialog);

        components = new Components(); panel.add(components);

        //Botones

        closeB = new JButton(); closeB.setBounds(width - 52,3,44,44);
        closeB.setIcon(Utils.scaledIcon("cross.png", 20,20)); closeB.setBackground(Utils.black);
        closeB.setFocusable(false); closeB.setBorder(null);
        closeB.addActionListener(e -> System.exit(0)); topBar.add(closeB); closeB.addMouseListener(bl);

        maxB = new JButton(); maxB.setBounds(width - 102,3,44,44);
        maxB.setIcon(Utils.scaledIcon("maxB2.png", 20,20)); maxB.setBackground(Utils.black);
        maxB.setFocusable(false); maxB.setBorder(null);
        maxB.addActionListener(e -> {
            if (main.getExtendedState() != JFrame.MAXIMIZED_BOTH) maximize();
            else minimize();
        }); topBar.add(maxB); maxB.addMouseListener(bl);

        minB = new JButton(); minB.setBounds(width - 152,3,44,44);
        minB.setIcon(Utils.scaledIcon("minB.png", 20,20)); minB.setBackground(Utils.black);
        minB.setFocusable(false); minB.setBorder(null);
        minB.addActionListener(e -> main.setState(Frame.ICONIFIED)); topBar.add(minB); minB.addMouseListener(bl);

        saveB = new RoundedButton(15,15); saveB.setBounds(5,25,40,40); saveB.setFocusable(false); saveB.setBorder(null);
        saveB.setIcon(Utils.scaledIcon("folder.png", 25,25)); saveB.setBackground(null);
        saveB.addActionListener(e -> {}); sideBar.add(saveB); saveB.addMouseListener(bl);

        downB = new RoundedButton(15,15); downB.setBounds(5,75,40,40); downB.setFocusable(false); downB.setBorder(null);
        downB.setIcon(Utils.scaledIcon("download.png", 25,25)); downB.setBackground(null);
        downB.addActionListener(e -> {}); sideBar.add(downB); downB.addMouseListener(bl);

        gateB = new RoundedButton(15,15); gateB.setBounds(5,125,40,40); gateB.setFocusable(false); gateB.setBorder(null);
        gateB.setIcon(Utils.scaledIcon("gate.png", 25,20)); gateB.setBackground(null);
        gateB.addActionListener(e -> {
            if (!timer.isRunning()) setComponentsVisible(components.getX() < 50);
        }); sideBar.add(gateB); gateB.addMouseListener(bl);

        cableB = new RoundedButton(15,15); cableB.setBounds(5,175,40,40); cableB.setFocusable(false); cableB.setBorder(null);
        cableB.setIcon(Utils.scaledIcon("cable.png", 20,20)); cableB.setBackground(null);
        cableB.addActionListener(e -> {}); sideBar.add(cableB); cableB.addMouseListener(bl);

        runB = new RoundedButton(15,15); runB.setBounds(5,225,40,40); runB.setFocusable(false); runB.setBorder(null);
        runB.setIcon(Utils.scaledIcon("run.png", 17,17)); runB.setBackground(null);
        runB.addActionListener(e -> {
            if (running) runB.setIcon(Utils.scaledIcon("run.png", 17,17));
            else runB.setIcon(Utils.scaledIcon("activerun.png", 17,17));
            running = !running;
        }); sideBar.add(runB); runB.addMouseListener(bl);

        configB = new RoundedButton(15,15); configB.setBounds(5,275,40,40); configB.setFocusable(false); configB.setBorder(null);
        configB.setIcon(Utils.scaledIcon("config.png", 20,20)); configB.setBackground(null);
        configB.addActionListener(e -> {}); sideBar.add(configB); configB.addMouseListener(bl);

        infoB = new RoundedButton(15,15); infoB.setBounds(5,325,40,40); infoB.setFocusable(false); infoB.setBorder(null);
        infoB.setIcon(Utils.scaledIcon("info.png", 20,20)); infoB.setBackground(null);
        infoB.addActionListener(e -> {}); sideBar.add(infoB); infoB.addMouseListener(bl);

        projectB = new RoundedButton("Project Name",15,15); projectB.setBounds(50,10,200,30); projectB.setFocusable(false); projectB.setBorder(null);
        projectB.setIcon(Utils.scaledIcon("desp.png", 7,7)); projectB.setIconTextGap(5); projectB.setBackground(null);
        projectB.setForeground(Color.white); projectB.setFont(new Font("Verdana", Font.PLAIN, 15)); projectB.setHorizontalTextPosition(SwingConstants.LEFT);
        projectB.addActionListener(e -> dialog.setVisible(!dialog.isVisible())); topBar.add(projectB); projectB.addMouseListener(bl);

        userB = new RoundedButton(15,15); userB.setBounds(width - 202,5,40,40); userB.setFocusable(false); userB.setBorder(null);
        userB.setIcon(Utils.scaledIcon("user.png", 20,20)); userB.setBackground(null);
        userB.addActionListener(e -> {}); topBar.add(userB); userB.addMouseListener(bl);

        forumB = new RoundedButton(15,15); forumB.setBounds(width - 252,5,40,40); forumB.setFocusable(false); forumB.setBorder(null);
        forumB.setIcon(Utils.scaledIcon("forum.png", 20,20)); forumB.setBackground(null);
        forumB.addActionListener(e -> {}); topBar.add(forumB); forumB.addMouseListener(bl);

        inviteB = new RoundedButton(15,15); inviteB.setBounds(width - 302,5,40,40); inviteB.setFocusable(false); inviteB.setBorder(null);
        inviteB.setIcon(Utils.scaledIcon("invite.png", 23,23)); inviteB.setBackground(null);
        inviteB.addActionListener(e -> {}); topBar.add(inviteB); inviteB.addMouseListener(bl);

        panel.setLayer(workSpace,0);
        panel.setLayer(info,100);
        panel.setLayer(components,100);
        panel.setLayer(topBar,200);
        panel.setLayer(sideBar,200);
        panel.setLayer(dialog, 1000);

        if (Utils.getConfig("Fullscreen") != null && Utils.getConfig("Fullscreen").equals("true")) maximize();
        else minimize();

        //Listeners

        topBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                main.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                posX = e.getX();
                posY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                main.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                if (main.getY() < 20) maximize();
            }
        });

        topBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (Utils.getConfig("Fullscreen").equals("true")) minimize();
                int newX = main.getLocation().x + e.getX() - posX;
                int newY = main.getLocation().y + e.getY() - posY;
                main.setLocation(newX, newY);
            }
        });

        main.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeAll();
            }
        });

        main.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                main.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        main.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (Utils.getConfig("Selected file") != null) Utils.newConfig("Selected file", Utils.getConfig("Selected file"));
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                minB.setFocusPainted(false);
                minB.repaint();
            }
        });

        main.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getX() > width - 5 && e.getY() > height - 5) main.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                else if (e.getX() > width - 5 && e.getY() > 50) main.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                else if (e.getY() > height - 5) main.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                else main.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (main.getCursor().equals(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR))) {
                    int deltaX = e.getX() - lastX;
                    int deltaY = e.getY() - lastY;
                    Dimension size = main.getSize();
                    int width2 = size.width;
                    int height2 = size.height;
                    if (width2 + deltaX < 1200) width2 = 1200 - deltaX;
                    if (height2 + deltaY < 800) height2 = 800 - deltaY;
                    width = width2 + deltaX;
                    height = height2 + deltaY;
                    resizeAll();
                    lastX = e.getX();
                    lastY = e.getY();
                } else if (main.getCursor().equals(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR))) {
                    int deltaX = e.getX() - lastX;
                    Dimension size = main.getSize();
                    int width2 = size.width;
                    if (width2 + deltaX < 1200) width2 = 1200 - deltaX;
                    width = width2 + deltaX;
                    resizeAll();
                    lastX = e.getX();
                } else if (main.getCursor().equals(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR))) {
                    int deltaY = e.getY() - lastY;
                    Dimension size = main.getSize();
                    int height2 = size.height;
                    if (height2 + deltaY < 800) height2 = 800 - deltaY;
                    height = height2 + deltaY;
                    resizeAll();
                    lastY = e.getY();
                }
            }
        });
    }

    public static void maximize() {
        maxB.setIcon(Utils.scaledIcon("maxB.png", 20,20));
        main.getRootPane().setBorder(null);
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        widthmem = width; heightmem = height;
        width = Toolkit.getDefaultToolkit().getScreenSize().width;
        height = Toolkit.getDefaultToolkit().getScreenSize().height;
        Utils.newConfig("Fullscreen", "true");
        resizeAll();
    }

    public static void minimize() {
        maxB.setIcon(Utils.scaledIcon("maxB2.png", 20,20));
        main.getRootPane().setBorder(BorderFactory.createLineBorder(Utils.gray,3,true));
        main.setExtendedState(JFrame.NORMAL);
        width = widthmem; height = heightmem;
        Utils.newConfig("Fullscreen", "false");
        resizeAll();
    }

    public static void resizeAll() {
        main.setSize(width, height);
        panel.setSize(width, height);
        topBar.setBounds(0,0,width, 50);
        sideBar.setBounds(0, 47,50,height - 50);
        workSpace.setBounds(40,50,width - 40, height - 50);
        dialog.setLocation((main.getWidth() / 2) - (dialog.getWidth() / 2), (main.getHeight() / 2) - (dialog.getHeight() / 2));
        components.setBounds(-300,50, 300 - 85, App.height - 50);
        //Botones
        closeB.setBounds(width - 52,3,44,44);
        maxB.setBounds(width - 102,3,44,44);
        minB.setBounds(width - 152,3,44,44);
        saveB.setBounds(5,25,40,40);
        downB.setBounds(5,75,40,40);
        gateB.setBounds(5,125,40,40);
        cableB.setBounds(5,175,40,40);
        runB.setBounds(5,225,40,40);
        configB.setBounds(5,275,40,40);
        infoB.setBounds(5,325,40,40);
        userB.setBounds(width - 202,5,40,40);
        forumB.setBounds(width - 252,5,40,40);
        inviteB.setBounds(width - 302,5,40,40);
    }

    public static void setComponentsVisible(boolean b) {
        if (b) {
            timer = new Timer(10, e1 -> {
                if (components.getX() < 50) {
                    components.setLocation(components.getX() + 10, components.getY());
                    timer.restart();
                } else {
                    timer.stop();
                }
            });
        } else {
            timer = new Timer(10, e1 -> {
                if (components.getX() > -300) {
                    components.setLocation(components.getX() - 10, components.getY());
                    timer.restart();
                } else {
                    timer.stop();
                }
            });
        } timer.start();
    }

    private static class buttonListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            main.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            temp = e.getComponent().getBackground();
            if (e.getComponent().equals(closeB)) e.getComponent().setBackground(new Color(255, 55, 55));
            else e.getComponent().setBackground(e.getComponent().getBackground().brighter());
            info.setVisible(true);
            info.setSize(50,25);
            adjustInfo(e.getComponent());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            main.setCursor(Cursor.getDefaultCursor());
            e.getComponent().setBackground(temp);
            info.setVisible(false);
        }
    }

    public static void adjustInfo(Component c) {
        if (c.equals(saveB)) {
            info.setLocation(saveB.getX() + saveB.getWidth() + 10, saveB.getY() + 50);
            info.setText("Open");
        } else if (c.equals(downB)) {
            info.setLocation(downB.getX() + downB.getWidth() + 10, downB.getY() + 50);
            info.setText("Save");
        } else if (c.equals(gateB)) {
            info.setLocation(gateB.getX() + gateB.getWidth() + 10, gateB.getY() + 50);
            info.setSize(info.getWidth() + 25, info.getHeight());
            info.setText("Components");
        } else if (c.equals(cableB)) {
            info.setLocation(cableB.getX() + cableB.getWidth() + 10, cableB.getY() + 50);
            info.setText("Wiring");
        } else if (c.equals(runB)) {
            info.setLocation(runB.getX() + runB.getWidth() + 10, runB.getY() + 50);
            info.setSize(info.getWidth() + 25, info.getHeight());
            info.setText("Run circuit");
        } else if (c.equals(configB)) {
            info.setLocation(configB.getX() + configB.getWidth() + 10, configB.getY() + 50);
            info.setText("Config");
        } else if (c.equals(infoB)) {
            info.setLocation(infoB.getX() + infoB.getWidth() + 10, infoB.getY() + 50);
            info.setText("Info");
        } else if (c.equals(userB)) {
            info.setLocation(userB.getX(), userB.getY() + userB.getHeight() + 10);
            info.setText("User");
        } else if (c.equals(forumB)) {
            info.setLocation(forumB.getX(), forumB.getY() + forumB.getHeight() + 10);
            info.setText("Forum");
        } else if (c.equals(inviteB)) {
            info.setLocation(inviteB.getX(), inviteB.getY() + inviteB.getHeight() + 10);
            info.setText("Share");
        } else {
            info.setVisible(false);
        }
    }
}