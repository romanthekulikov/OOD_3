import javax.swing.*;
import java.awt.*;

public class CRectangleDecorator implements IShape {
    private static final String TYPE = "rectangle";
    private static class RectangleDrawer extends JPanel {
        int x, y, width, height;

        RectangleDrawer(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void paint(Graphics g) {
            g.drawRect(x, y, width, height);
        }
    }

    public static class CRectangleCreator {
        private static CRectangleDecorator instance = null;
        public static CRectangleDecorator getInstance() {
            if (instance == null) {
                instance = new CRectangleDecorator();
            }

            return instance;
        }
    }

    private CPoint firstPoint = new CPoint(0, 0);
    private CPoint secondPoint = new CPoint(0, 0);

    @Override
    public JPanel getPanel() {
        return new RectangleDrawer((int) firstPoint.getX(), (int) firstPoint.getY(), (int) getWidth(), (int) getHeight());
    }

    @Override
    public double getPerimeter() {
        return (getWidth() + getHeight()) * 2;
    }

    @Override
    public double getArea() {
        return getWidth() * getHeight();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void accept(IShapesVisitor visitor) {
        visitor.visit(this);
    }

    public void setData(CPoint firstPoint, CPoint secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }

    private double getWidth() {
        return Math.abs(firstPoint.getX() - secondPoint.getX());
    }

    private double getHeight() {
        return Math.abs(firstPoint.getY() - secondPoint.getY());
    }
}
