package utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileUtils {

    public static List<String> printLargeFileContents(String absoluteFilePath) throws IOException {

        if (new File(absoluteFilePath).isFile()){
            try(Stream<String> lines = Files.lines(Path.of(absoluteFilePath))){
                return lines.toList();
            }
        }
            return null;
    }

}
