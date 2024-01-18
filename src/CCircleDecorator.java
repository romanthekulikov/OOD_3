import javax.swing.*;
import java.awt.*;

public class CCircleDecorator implements IShape {
    private static final String TYPE = "circle";
    private static class CircleDrawer extends JPanel {
        int radius, x, y;

        CircleDrawer(int radius, int x, int y) {
            this.radius = radius;
            this.x = x;
            this.y = y;
        }

        public void paint(Graphics g) {
            int diameter = radius * 2;
            g.drawOval(x, y, diameter, diameter);
        }
    }
    private CPoint center = new CPoint(0, 0);;
    private double radius = 0;

    public static class CCircleCreator {
        private static CCircleDecorator instance = null;
        public static CCircleDecorator getInstance() {
            if (instance == null) {
                instance = new CCircleDecorator();
            }
            return instance;
        }
    }

    @Override
    public JPanel getPanel() {
        return new CircleDrawer((int) radius, (int) center.getX(), (int) center.getY());
    }

    @Override
    public double getArea() {
        return Double.parseDouble(String.valueOf(Math.PI * radius * radius));
    }

    @Override
    public double getPerimeter() {
        return Double.parseDouble(String.valueOf(2 * Math.PI * radius));
    }
    @Override
    public String getType() {
        return TYPE;
    }

    public void accept(IShapesVisitor visitor) {
        visitor.visit(this);
    }

    public void setData(CPoint center, double radius) {
        this.center = center;
        this.radius = radius;
    }
}
