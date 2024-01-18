import javax.swing.*;
import java.util.List;

public class Main {
    private static final String NAME_WINDOW = "Крутая прога с фигурками";
    private static final int WIDTH_WINDOW = 500;
    private static final int HEIGHT_WINDOW = 500;

    public static void main(String[] args) {
        IAssistant assistant = new CAssistant();
        JFrame frame = createFrame();
        try {
            assistant.clearOutputFile(args[1]);
            String outDir = args[1];
            CPrintShapeVisitor printShapeVisitor = new CPrintShapeVisitor(outDir);
            List<String> shapeStrings = assistant.getShapeStringsFromFile(args[0]);

            for (String shapeString : shapeStrings) { //Проходимся foreach по списку фигур из input
                try {
                    IShape shape = assistant.getBaseShapeFromString(shapeString);
                    //assistant.writeAreaAndPerimeterToFile(shape, args[1]);
                    shape.accept(printShapeVisitor);
                    JPanel shapePanel = shape.getPanel();//Берем фигуру для отрисовки
                    frame.add(shapePanel);
                    frame.repaint();
                    frame.setVisible(true);
                } catch (RuntimeException exception) { //Ловим файловые исключения
                    assistant.logError(exception.getMessage());
                }
            }
        } catch (IllegalArgumentException exception) {
            assistant.logError(exception.getMessage());
        }
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame(NAME_WINDOW);
        frame.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        return frame;
    }
}