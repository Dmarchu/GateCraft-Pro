import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Uninstall {

    public static void uninstall() {

    }

    public static void createUnInstallFrame() {
        JFrame installFrame = new JFrame("GateCraft Pro Install"); installFrame.setResizable(false);
        installFrame.setSize(500,395); installFrame.setUndecorated(true); installFrame.setLocationRelativeTo(null);
        installFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); installFrame.setVisible(true);
        installFrame.setBackground(Utils.black); installFrame.setLayout(null); installFrame.getRootPane().setBorder(BorderFactory.createLineBorder(Utils.gray, 5, true));

        JPanel topBar = new JPanel(); topBar.setBounds(0,0,500,25); topBar.setLayout(null);
        topBar.setBackground(Utils.black);

        JLabel mainLogo = new JLabel("GateCraft Pro Installation"); mainLogo.setBounds(5,0,200,25);
        mainLogo.setIcon(Utils.scaledIcon("l3p.png",20,20)); mainLogo.setHorizontalTextPosition(SwingConstants.RIGHT);
        mainLogo.setVerticalTextPosition(SwingConstants.CENTER); mainLogo.setForeground(Color.white);

        JButton close = new JButton(); close.setBounds(470,0,20,20);
        close.setIcon(Utils.scaledIcon("cross.png", 15,15));
        close.setBackground(Utils.black); close.setFocusable(false); close.setBorder(null);
        close.addActionListener(e -> {
            installFrame.dispose();
        }); topBar.add(close); topBar.add(mainLogo); installFrame.add(topBar); topBar.repaint();

        JLayeredPane installPanel = new JLayeredPane();
        installPanel.setBounds(0,20,500,400); installPanel.setBackground(Utils.black);
        installPanel.setLayout(null);installFrame.add(installPanel);

        JLabel background = new JLabel();
        background.setBounds(0,0,520,420); background.setIcon(Utils.scaledIcon("installb.png", 510, 420));
        installPanel.add(background); installPanel.setLayer(background,0); background.setLayout(null);

        JLabel logo = new JLabel(); logo.setBounds(5,30,216,72);
        logo.setIcon(Utils.scaledIcon("l2p.png", 216,72));
        installPanel.add(logo); installPanel.setLayer(logo, 100);

        JButton installButton = new JButton("Install");
        installButton.setBackground(Utils.gray); installButton.setForeground(Color.white);
        installButton.setBounds(400,15,75,25);
        installButton.addActionListener(e -> {
            installFrame.dispose();
        });

        JPanel bottomBar = new JPanel();
        bottomBar.setBackground(Utils.black); bottomBar.setBorder(BorderFactory.createLineBorder(Utils.gray, 3, true));
        bottomBar.setBounds(-5,315,510,85); bottomBar.setLayout(null);
        installPanel.add(bottomBar); installPanel.setLayer(bottomBar, 100); bottomBar.add(installButton);
    }

    /*public static void moveFiles() {
        File directorioRecursos = new File(Uninstall.class.getResource("/mainDir").getPath());
        File[] archivosRecursos = directorioRecursos.listFiles();
        if (archivosRecursos != null) {
            for (File archivo : archivosRecursos) {
                try {
                    Files.copy(archivo.toPath(), Paths.get(Utils.dir + "\\" + archivo.getName()));
                } catch (IOException ignored) {}
            }
        }
        if (needsInstallation) StartUp.createStartUpFrame();
    }

    public static void flushFiles(Path directorio) {
        try {
            Files.walk(directorio)
                    .sorted((p1, p2) -> -p1.compareTo(p2))
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException ignored) {}
                    });
        } catch (IOException ignored) {}
    }

    public static boolean containsAll(File[] array1, File[] array2) {
        if (array1 == null || array2 == null) return false;
        String[] nombresArray1 = fileNames(array1);
        String[] nombresArray2 = fileNames(array2);

        Arrays.sort(nombresArray1);
        Arrays.sort(nombresArray2);

        return Arrays.equals(nombresArray1, nombresArray2);
    }

    private static String[] fileNames(File[] array) {
        String[] nombres = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            nombres[i] = array[i].getName();
        }
        return nombres;
    }*/
}
