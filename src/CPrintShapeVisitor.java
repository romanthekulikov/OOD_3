import java.io.FileWriter;
import java.io.IOException;

public class CPrintShapeVisitor implements IShapesVisitor {
    private String outDir = "";
    public CPrintShapeVisitor(String outDir) {
        this.outDir = outDir;
    }
    @Override
    public void visit(CTriangleDecorator triangle) {
        String shapeType = triangle.getType();
        String area = getFullDoubleString(triangle.getArea());
        String perimeter = getFullDoubleString(triangle.getPerimeter());

        printShape(shapeType, area, perimeter);
    }

    @Override
    public void visit(CCircleDecorator circle) {
        String shapeType = circle.getType();
        String area = getFullDoubleString(circle.getArea());
        String perimeter = getFullDoubleString(circle.getPerimeter());

        printShape(shapeType, area, perimeter);
    }

    @Override
    public void visit(CRectangleDecorator rectangle) {
        String shapeType = rectangle.getType();
        String area = getFullDoubleString(rectangle.getArea());
        String perimeter = getFullDoubleString(rectangle.getPerimeter());

        printShape(shapeType, area, perimeter);
    }

    private void printShape(String shapeType, String area, String perimeter) {
        try (FileWriter writer = new FileWriter(outDir, true))
        {
            String result = String.format("%s: perimeter %s, area %s\n", shapeType, perimeter, area);
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException(CAssistant.WRITE_FILE_EX);
        }
    }

    private String getFullDoubleString(double number) {
        String strNumber = String.valueOf(number);
        int dotPos = strNumber.indexOf(".");
        int ePos = strNumber.indexOf("E");
        if (dotPos == -1 || ePos == -1) {
            return strNumber;
        }
        strNumber = strNumber.replace(".", "");
        int factor = Integer.parseInt(strNumber.substring(ePos, strNumber.length()));
        strNumber = strNumber.substring(0, ePos - 1);
        int additionalZeroCount = factor - strNumber.length();
        for (int i = 0; i < additionalZeroCount; i++) {
            strNumber = String.format(strNumber + "0");
        }
        return strNumber;
    }
}
