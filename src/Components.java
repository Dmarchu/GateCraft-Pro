import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Components extends JPanel {
    public static int gate = -1;

    public Components() {
        FlowLayout fl = new FlowLayout(FlowLayout.CENTER); fl.setVgap(15);
        setSize((App.width / 4) - 85, App.height - 50);
        setLocation(-App.width / 4,50);
        setBackground(new Color(0,0,0,10));
        setBorder(BorderFactory.createLineBorder(Utils.gray));
        setLayout(fl);
        for (int i = 0; i < 10; i++) add(new Butoneh(i));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getX() > (App.width / 4) - 85) App.setComponentsVisible(false);
            }
        });
    }

    private static class Butoneh extends JButton {
        public Butoneh(int i) {
            setSize((App.width / 4) - 10, 50);
            setBackground(new Color(0,0,0,0));
            setFocusable(false);
            setUI(new BasicButtonUI() {/***/ @Override
                protected void paintButtonPressed(Graphics g, AbstractButton b) {}
            });

            TitledBorder tb = new TitledBorder(new LineBorder(Color.white,0,true),"Example");
            tb.setTitleColor(Color.white); tb.setTitleFont(new Font("Verdana", Font.PLAIN, 12));
            switch (i) {
                case 0: setIcon(Utils.scaledComponentIcon("AND.png", (App.width / 4) - 135, 65));
                    tb.setTitle("AND Gate"); setBorder(tb);
                    break;
                case 1: setIcon(Utils.scaledComponentIcon("NAND.png", (App.width / 4) - 135, 65));
                    tb.setTitle("NAND Gate"); setBorder(tb);
                    break;
                case 2: setIcon(Utils.scaledComponentIcon("OR.png", (App.width / 4) - 135, 65));
                    tb.setTitle("OR Gate"); setBorder(tb);
                    break;
                case 3: setIcon(Utils.scaledComponentIcon("NOR.png", (App.width / 4) - 135, 65));
                    tb.setTitle("NOR Gate"); setBorder(tb);
                    break;
                case 4: setIcon(Utils.scaledComponentIcon("XOR.png", (App.width / 4) - 135, 65));
                    tb.setTitle("XOR Gate"); setBorder(tb);
                    break;
                case 5: setIcon(Utils.scaledComponentIcon("XNOR.png", (App.width / 4) - 135, 65));
                    tb.setTitle("XNOR Gate"); setBorder(tb);
                    break;
                case 6: setIcon(Utils.scaledComponentIcon("NOT.png", (App.width / 4) - 135, 65));
                    tb.setTitle("NOT Gate"); setBorder(tb);
                    break;
                case 7: setIcon(Utils.scaledComponentIcon("LED0.png", (App.width / 4) - 135, 55));
                    tb.setTitle("LED"); setBorder(tb);
                    break;
                case 8: setIcon(Utils.scaledComponentIcon("SRC0.png", (App.width / 4) - 180, 65));
                    tb.setTitle("Logic Source"); setBorder(tb);
                    break;
                case 9: setIcon(Utils.scaledComponentIcon("CLK.png", (App.width / 4) - 180, 65));
                    tb.setTitle("Clock"); setBorder(tb);
                    break;
            }
            addActionListener(e -> {App.components.setVisible(false); App.components.setVisible(true); gate = i;});
        }
    }
}
