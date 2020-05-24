package utils;

import java.nio.file.Path;
import java.util.List;

import static utils.FilesUtils.*;

/**
 * @author lferracini
 * @project = sdet-multi-project
 * @since <pre>24/05/2020</pre>
 */
public class CreateHTMLFile {

    public static void createHTMLFile() {
        List<String> links = readFile("bible-api/files/links.txt");
        StringBuilder html = new StringBuilder("<!DOCTYPE html>\n");
        html.append("<html lang=\"en\">\n");

        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <title>Title</title>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        int i = 0;
        for (String link : links) {
            html.append("<a href=\"").append(link).append("\">pdf ").append(i++).append("</a>\n");
        }

        html.append("</body>\n");
        html.append("</html>");
        Path path = Path.of("bible-api/src/test/resources/links1.html");
        createFile(path);
        writeFile(path, html.toString());
    }

    public static void main(String[] args) {
        //copyFiles("F:\\Dropbox\\Books\\Bible\\CJB\\");
        createHTMLFile();

    }
}
