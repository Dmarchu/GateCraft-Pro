import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
    private int arcWidth;
    private int arcHeight;
    private int iconTextGap = 30;

    public RoundedButton(String text, int arcWidth, int arcHeight) {
        super(text);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    public RoundedButton(int arcWidth, int arcHeight) {
        super();
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(getBackground());
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight));

        Icon icon = getIcon();
        if (icon != null) {
            int iconWidth = icon.getIconWidth();
            int iconHeight = icon.getIconHeight();
            int iconX;
            int iconY;
            if (getText() == null || getText().isEmpty()) {
                iconX = (getWidth() - iconWidth) / 2;
                iconY = (getHeight() - iconHeight) / 2;
            } else {
                iconX = getWidth() - (iconWidth + iconTextGap) / 2;
                iconY = (getHeight() - (int) (0.5 * iconHeight)) / 2;
            }
            icon.paintIcon(this, g2d, iconX, iconY);
        }

        if (getText() != null && !getText().isEmpty()) {
            g2d.setColor(getForeground());
            FontMetrics metrics = g2d.getFontMetrics(getFont());
            int textX = (getWidth() - getTextWidth()) / 2;
            int textY = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
            g2d.drawString(getText(), textX, textY);
        }

        g2d.dispose();
    }

    private int getTextWidth() {
        return getFontMetrics(getFont()).stringWidth(getText());
    }

    private int getIconWidth() {
        Icon icon = getIcon();
        return (icon != null) ? icon.getIconWidth() : 0;
    }
}