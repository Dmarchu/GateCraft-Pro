import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedLabel extends JLabel {
    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor;

    public RoundedLabel(String text, int arcWidth, int arcHeight) {
        super(text);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.backgroundColor = getBackground();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(backgroundColor);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));

        g2d.setColor(getForeground());
        FontMetrics metrics = g2d.getFontMetrics(getFont());
        int textX = (getWidth() - metrics.stringWidth(getText())) / 2;
        int textY = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        g2d.drawString(getText(), textX, textY);

        g2d.dispose();
    }
}