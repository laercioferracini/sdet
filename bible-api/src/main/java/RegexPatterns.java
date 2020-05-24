import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.chrono.MinguoEra;
import java.time.chrono.ThaiBuddhistDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lferracini
 * @project = sdet-multi-project
 * @since <pre>02/05/2020</pre>
 */
public class RegexPatterns {
    public static final String EXAMPLE_TEST = "This is my small example string which I'm going to use for pattern matching.";

    public static void main(String[] args) throws IOException {
        String baseDir = System.getProperty("user.dir");
        String fileSeparator = System.getProperty("file.separator");
        //System.getProperties().forEach((k, v) -> System.out.println(k + "-" + v));
        String path = fileSeparator.concat("bible-api" + fileSeparator + "files" + fileSeparator + "Gen" + fileSeparator);

        List<Path> fileNames = getFileNames(baseDir.concat(path));
        //fileNames.forEach(System.out::println);
        List<String> lines;
        try (Stream<String> stream = Files.lines(fileNames.get(0), StandardCharsets.UTF_8)) {

//            lines = stream.filter(e -> e.startsWith("Parashah"))
//                    .collect(Collectors.toList());
            List<IntStream> collect = stream.filter(e -> e.startsWith("Parashah"))
                    .map(String::chars)
                    .collect(Collectors.toList());
            List<int[]> collect1 = collect.stream().map(IntStream::toArray).collect(Collectors.toList());
            for (int[] ints : collect1) {
                for (int i = ints.length - 1; i >= 0; i--) {
                    System.out.println(Character.getName(ints[i]));
                }
            }

        }

        ThaiBuddhistDate buddhistDate = ThaiBuddhistDate.now();
        System.out.println(buddhistDate.toString());
        System.out.println(java.time.chrono.HijrahDate.now().toString());
        System.out.println(java.time.chrono.JapaneseDate.now().toString());
        System.out.println(MinguoEra.BEFORE_ROC.toString());
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,2);

        System.out.printf("DIA DO MÊS: %s%n", calendar.get(Calendar.DAY_OF_MONTH));
        System.out.printf("DIA DA SEMANA: %s%n", calendar.get(Calendar.DAY_OF_WEEK));

        int dif = diferencaEmDias(calendar, Calendar.getInstance());
        System.out.println(dif);


        //lines.forEach(System.out::println);

//        Pattern pattern = Pattern.compile("[\r\n]+\\w+");
//        // in case you would like to ignore case sensitivity,
//        // you could use this statement:
//        //Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(EXAMPLE_TEST);
//        // check all occurance
//        while (matcher.find()) {
//            System.out.print("Start index: " + matcher.start());
//            System.out.print(" End index: " + matcher.end() + " ");
//            System.out.printf("Group: %s%n", matcher.group());
//        }
//        // now create a new pattern and matcher to replace whitespace with tabs
//        Pattern replace = Pattern.compile("\\s+");
//        Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
//        System.out.println(matcher2.replaceAll("\t"));
    }

    public static int diferencaEmDias(Calendar c1, Calendar c2) {
        long m1 = c1.getTimeInMillis();
        long m2 = c2.getTimeInMillis();
        return (int) ((m2 - m1) / (24 * 60 * 60 * 1000));
    }

    private static List<Path> getFileNames(String path) throws IOException {
        return Files.list(Paths.get(path))
                .filter(Files::isRegularFile)
                .map(Path::toAbsolutePath)
                .collect(Collectors.toList());
    }
}
