import com.aspose.imaging.system.collections.Generic.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CAssistant implements IAssistant {
    private static final String FILE_READ_EX = "Файл не найден или поврежден";
    private static final String PARAMETER_READ_EX = "Не хватает аргументов или они были неправильно записаны";
    public static final String WRITE_FILE_EX = "Не удалось открыть выходной файл";
    private static final String TRIANGLE = "triangle";
    private static final String RECTANGLE = "rectangle";
    private static final String CIRCLE = "circle";
    @Override
    public List<String> getShapeStringsFromFile(String fileDir) {
        List<String> shapes = new List<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileDir));
            String line;
            while ((line = reader.readLine()) != null) {
                shapes.add(line);
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException(FILE_READ_EX);
        }

        return shapes;
    }

    @Override
    public IShape getBaseShapeFromString(String str) {
        String[] parameters = str.toLowerCase().trim().replaceAll("\\s+", " ").split(" ");
        try {
            CPoint beginPoint = new CPoint(Double.parseDouble(parameters[1]), Double.parseDouble(parameters[2]));
            String shapeType = parameters[0];
            switch (shapeType) {
                case (TRIANGLE) -> {
                    CPoint vertex2 = new CPoint(Double.parseDouble(parameters[3]), Double.parseDouble(parameters[4]));
                    CPoint vertex3 = new CPoint(Double.parseDouble(parameters[5]), Double.parseDouble(parameters[6]));
                    CTriangleDecorator triangle = CTriangleDecorator.CTriangleCreator.getInstance();
                    triangle.setData(beginPoint, vertex2, vertex3);

                    return new CShapeTransformer(triangle);
                }
                case (RECTANGLE) -> {
                    CPoint endPoint = new CPoint(Double.parseDouble(parameters[3]), Double.parseDouble(parameters[4]));
                    CRectangleDecorator rectangle = CRectangleDecorator.CRectangleCreator.getInstance();
                    rectangle.setData(beginPoint, endPoint);

                    return new CShapeTransformer(rectangle);
                }
                case (CIRCLE) -> {
                    double radius = Double.parseDouble(parameters[3]);
                    CCircleDecorator circle = CCircleDecorator.CCircleCreator.getInstance();
                    circle.setData(beginPoint, radius);

                    return new CShapeTransformer(circle);
                }
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(PARAMETER_READ_EX);
        }

        throw new IllegalArgumentException(PARAMETER_READ_EX);
    }

    @Override
    public void clearOutputFile(String fileDir) {
        try (FileWriter writer = new FileWriter(fileDir, false))
        {
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException(WRITE_FILE_EX);
        }
    }

//    @Override
//    public void writeAreaAndPerimeterToFile(IShape shape, String fileDir) {
//        try (FileWriter writer = new FileWriter(fileDir, true))
//        {
//            String shapeType = shape.getType();
//            String area = getFullDoubleString(shape.getArea());
//            String perimeter = getFullDoubleString(shape.getPerimeter());
//            String result = String.format("%s: perimeter %s, area %s\n", shapeType, perimeter, area);
//            writer.write(result);
//        } catch (IOException e) {
//            throw new RuntimeException(WRITE_FILE_EX);
//        }
//    }
//
//    private String getFullDoubleString(double number) {
//        String strNumber = String.valueOf(number);
//        int dotPos = strNumber.indexOf(".");
//        int ePos = strNumber.indexOf("E");
//        if (dotPos == -1 || ePos == -1) {
//            return strNumber;
//        }
//        strNumber = strNumber.replace(".", "");
//        int factor = Integer.parseInt(strNumber.substring(ePos, strNumber.length()));
//        strNumber = strNumber.substring(0, ePos - 1);
//        int additionalZeroCount = factor - strNumber.length();
//        for (int i = 0; i < additionalZeroCount; i++) {
//            strNumber = String.format(strNumber + "0");
//        }
//        return strNumber;
//    }

    @Override
    public void logError(String error) {
        System.out.println(error);
    }
}
