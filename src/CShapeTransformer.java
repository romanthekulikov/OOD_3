import javax.swing.*;

public class CShapeTransformer implements IShape {
    IShape shape;

    public CShapeTransformer(IShape shape) {
        this.shape = shape;
    }
    @Override
    public double getPerimeter() {
        return shape.getPerimeter();
    }

    @Override
    public double getArea() {
        return shape.getArea();
    }

    @Override
    public JPanel getPanel() {
        return shape.getPanel();
    }

    @Override
    public String getType() {
        return shape.getType();
    }

    @Override
    public void accept(IShapesVisitor visitor) {
        shape.accept(visitor);
    }
}
