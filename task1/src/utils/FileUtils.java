package utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    public static List<String> printLargeFileContents(String absoluteFilePath) throws IOException {

        if (new File(absoluteFilePath).isFile()){
            return Files.lines(Path.of(absoluteFilePath)).toList();
        }
            return null;
    }

}
