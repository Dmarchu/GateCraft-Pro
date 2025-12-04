import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

public class Project extends JPanel {
    public Project() {
        setSize(500,375);
        setVisible(!Boolean.parseBoolean(Utils.getConfig("Last")));
        setVisible(false);
        setLocation((App.main.getWidth() / 2) - (getWidth() / 2), (App.main.getHeight() / 2) - (getHeight() / 2));
        setLayout(null);

        JPanel topBar = new JPanel(); topBar.setBounds(0,0,500,25); topBar.setLayout(null);
        topBar.setBackground(Utils.black);

        JLabel mainLogo = new JLabel("New Project"); mainLogo.setBounds(5,0,200,25);
        mainLogo.setIcon(Utils.scaledIcon("l3p.png",20,20)); mainLogo.setHorizontalTextPosition(SwingConstants.RIGHT);
        mainLogo.setVerticalTextPosition(SwingConstants.CENTER); mainLogo.setForeground(Color.white);

        topBar.add(mainLogo); add(topBar); topBar.repaint();

        JLayeredPane installPanel = new JLayeredPane();
        installPanel.setBounds(0,20,500,600); installPanel.setBackground(Utils.black);
        installPanel.setLayout(null); add(installPanel);

        JLabel background = new JLabel();
        background.setBounds(0,0,500,600); background.setIcon(Utils.scaledIcon("installb.png", 1000, 800));
        installPanel.add(background); installPanel.setLayer(background,0); background.setLayout(null);

        JLabel logo = new JLabel(); logo.setBounds(5,30,216,72);
        logo.setIcon(Utils.scaledIcon("l2p.png", 216,72));
        installPanel.add(logo); installPanel.setLayer(logo, 100);

        JTextField projectname = new JTextField("Project Name"); projectname.setOpaque(false); projectname.setForeground(Color.white); projectname.setFont(new Font("Verdana",Font.BOLD,15));
        TitledBorder b = BorderFactory.createTitledBorder(new LineBorder(Utils.darkBlue,3,true), "Project Name:"); b.setTitleColor(Utils.darkBlue);
        projectname.setBorder(b); projectname.setBounds(10,115,250,50); projectname.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                App.projectB.setText(projectname.getText());
            }
        }); installPanel.add(projectname); installPanel.setLayer(projectname,200);

        JTextField projectpath = new JTextField(); projectpath.setOpaque(false); projectpath.setForeground(Color.white); projectpath.setFont(new Font("Verdana",Font.BOLD,10));
        TitledBorder c = BorderFactory.createTitledBorder(new LineBorder(Utils.darkBlue,3,true), "Project Location:"); c.setTitleColor(Utils.darkBlue);
        projectpath.setBorder(c); projectpath.setBounds(10,190,213,50); installPanel.add(projectpath); installPanel.setLayer(projectpath,200); projectpath.setText(Utils.pDir);

        JFileChooser chooser = new JFileChooser(); chooser.setCurrentDirectory(new File(projectpath.getText())); chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setBackground(Utils.black); chooser.setForeground(Utils.blue); chooser.setDialogTitle("New project"); chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.addActionListener(e -> {
            if(chooser.getSelectedFile() != null) projectpath.setText(chooser.getSelectedFile().getPath());
        });

        JButton pathButton = new JButton(); pathButton.setBounds(220 - 3,197,41,41); pathButton.setBackground(Utils.black);
        pathButton.setIcon(Utils.scaledIcon("folder.png", 25,25)); pathButton.setFocusable(false);
        pathButton.setBorder(new LineBorder(Utils.darkBlue,3,true)); installPanel.add(pathButton); installPanel.setLayer(pathButton, 300);
        pathButton.addActionListener(e -> {
            chooser.setCurrentDirectory(new File(projectpath.getText()));
            chooser.showDialog(App.main, "Select");
        });

        projectname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (projectname.getText().length() > 17) projectname.setText(projectname.getText().substring(0,17));
            }
        });

        JButton create = new JButton("Create Project"); create.setFocusable(false);
        create.setBackground(Utils.gray); create.setForeground(Color.white); create.setBounds(350,325,125,25);
        create.addActionListener(e -> {
            File[] files = new File(projectpath.getText()).listFiles();
            if (!projectname.getText().isEmpty() || !Utils.contains(new File(projectpath + "/" + projectname), files)) {
                Utils.newConfig("Selected file", projectpath.getText());
                setVisible(false);
                App.main.setAlwaysOnTop(true);
                App.main.setAlwaysOnTop(false);
                //todo: Crear archivo
            } else {
                System.out.println("su pene no es válido");
            }
        }); installPanel.add(create); installPanel.setLayer(create, 100);

        projectname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (projectname.getText().trim().isEmpty()) {
                    projectname.setText("Project Name");
                }
            }
        });
    }
}
