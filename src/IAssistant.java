import com.aspose.imaging.system.collections.Generic.List;

public interface IAssistant {
    List<String> getShapeStringsFromFile(String fileDir);
    IShape getBaseShapeFromString(String str);

    void clearOutputFile(String fileDir);
    //void writeAreaAndPerimeterToFile(IShape shape, String fileDir);
    void logError(String error);
}
