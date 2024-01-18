import javax.swing.*;

interface IShape {
    double getPerimeter();
    double getArea();
    JPanel getPanel();
    String getType();
    void accept(IShapesVisitor visitor);
}
