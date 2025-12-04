import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class WorkSpace extends JPanel {
    public static ArrayList<Icon> gateTypes;
    public static ArrayList<Gates> sources, components;
    public static ArrayList<Line2D> cables;

    private int selectionX, selectionY, width, height, mouseX, mouseY;
    public boolean haveTo = false;
    private Rectangle selectionRectangle;
    private float scale = 1.0f;

    public WorkSpace() {
        super();

        gateTypes = new ArrayList<>();
        components = new ArrayList<>();
        setGateTypes();

        selectionRectangle = new Rectangle();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (App.components.isVisible()) {
                    if (!App.timer.isRunning()) App.setComponentsVisible(false);
                    else haveTo = true;
                }
                if (Components.gate != -1) {
                    components.add(new Gates(gateTypes.get(Components.gate), (int) (e.getX() / scale), (int) (e.getY() / scale), Components.gate));
                    Components.gate = -1;
                }
                selectionX = e.getX();
                selectionY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectionRectangle.setBounds(0, 0, 0, 0);
                repaint();
                App.sideBar.setVisible(false);
                App.sideBar.setVisible(true);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int position = -1;
                for (int i = 0; i < components.size(); i++) {
                    if (components.contains(e.getPoint())) {
                        position = i;
                        break;
                    }
                }
                if (position != -1) {
                    if (components.get(position).getType() == 7) {
                        components.set(position, new Gates(gateTypes.get(10), components.get(position).getX(), components.get(position).getY(), 10));
                    } else if (components.get(position).getType() == 10) {
                        components.set(position, new Gates(gateTypes.get(7), components.get(position).getX(), components.get(position).getY(), 7));
                    }
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                if (Components.gate != -1) repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (haveTo && !App.timer.isRunning()) {App.setComponentsVisible(false); haveTo = false;}
                if (App.dialog.isVisible()) App.dialog.setVisible(false);
                mouseX = e.getX();
                mouseY = e.getY();
                int currentX = e.getX();
                int currentY = e.getY();
                width = Math.abs(currentX - selectionX);
                height = Math.abs(currentY - selectionY);
                int x = Math.min(selectionX, currentX);
                int y = Math.min(selectionY, currentY);
                selectionRectangle.setBounds(x, y, width, height);
                repaint();
            }
        });

        addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            if (notches < 0) {
                if (!(scale > 1.5)) scale *= 1.1f;
            } else {
                if (!(scale < 0.5)) scale /= 1.1f;
            }
            revalidate();
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.scale(scale, scale);
        setGateTypes();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        if (Components.gate != -1) gateTypes.get(Components.gate).paintIcon(App.workSpace, g2d, (int) (mouseX / scale), (int) (mouseY / scale));

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        for (int i = 0; i < components.size(); i++) {
            components.get(i).setIcon(gateTypes.get(components.get(i).getType()));
            components.get(i).getIcon().paintIcon(App.workSpace, g2d, components.get(i).getX(), components.get(i).getY());
        }

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.05f));

        g2d.setColor(Utils.blue.brighter());
        g2d.fill(selectionRectangle);

        g2d.dispose();
        App.sideBar.setVisible(false);
        App.sideBar.setVisible(true);
    }

    public void setGateTypes() {
        gateTypes.clear();
        gateTypes.add(Utils.scaledComponentIcon("AND.png", (int) (175 * scale), (int) (75 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("NAND.png", (int) (175 * scale), (int) (75 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("OR.png", (int) (175 * scale), (int) (75 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("NOR.png", (int) (175 * scale), (int) (75 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("XOR.png", (int) (175 * scale), (int) (75 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("XNOR.png", (int) (175 * scale), (int) (75 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("NOT.png", (int) (175 * scale), (int) (75 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("LED0.png", (int) (175 * scale), (int) (55 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("SRC0.png", (int) (80 * scale), (int) (50 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("CLK.png", (int) (80 * scale), (int) (50 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("LED1.png", (int) (175 * scale), (int) (55 * scale)));
        gateTypes.add(Utils.scaledComponentIcon("SRC1.png", (int) (80 * scale), (int) (50 * scale)));
    }
}

