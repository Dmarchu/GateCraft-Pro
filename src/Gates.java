import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gates {
    private Icon icon;
    private int x, y, type;
    private Point output;
    private ArrayList<Point> inputs;

    public Gates(Icon icon, int x, int y, int type) {
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.inputs = new ArrayList<>();
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ArrayList<Point> getInputs() {
        return inputs;
    }

    public int getInputsLength() {
        return inputs.size();
    }

    public Point getOutput() {
        return output;
    }

    public int getType() {
        return type;
    }
}
