import javax.swing.*;
import java.awt.*;

public class CTriangleDecorator implements IShape {
    private static final String TYPE = "triangle";
    private static class TriangleDrawer extends JPanel {
        private final int[] xArray;
        private final int[] yArray;

        TriangleDrawer(int[] xArray, int[] yArray) {
            this.xArray = xArray;
            this.yArray = yArray;
        }
        public void paint(Graphics g) {
            g.drawPolygon(xArray, yArray, 3);
        }
    }

    public static class CTriangleCreator {
        private static CTriangleDecorator instance = null;
        public static CTriangleDecorator getInstance() {
            if (instance == null) {
                instance = new CTriangleDecorator();
            }
            return instance;
        }
    }

    private CPoint vertex1;
    private CPoint vertex2;
    private CPoint vertex3;

    @Override
    public JPanel getPanel() {
        int[] xArray = {(int) vertex1.getX(), (int) vertex2.getX(), (int) vertex3.getX()};
        int[] yArray = {(int) vertex1.getY(), (int) vertex2.getY(), (int) vertex3.getY()};
        return new TriangleDrawer(xArray, yArray);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void accept(IShapesVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public double getPerimeter() {
        double side1 = getLine(vertex1, vertex2);
        double side2 = getLine(vertex2, vertex3);
        double side3 = getLine(vertex3, vertex1);

        return side1 + side2 + side3;
    }

    @Override
    public double getArea() {
        return Math.abs(
                (vertex2.getX() - vertex1.getX()) * (vertex3.getY() - vertex1.getY()) -
                        (vertex3.getX() - vertex1.getX()) * (vertex2.getY() - vertex1.getY())) / 2;
    }

    private double getLine(CPoint from, CPoint to) {
        double leg1 = Math.abs(from.getX() - to.getX());
        double leg2 = Math.abs(from.getY() - to.getY());
        return Math.sqrt(leg1 * leg1 + leg2 * leg2);
    }

    public void setData(CPoint vertex1, CPoint vertex2, CPoint vertex3) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
    }
}