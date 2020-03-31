import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lferracini
 * @project = json-project
 * @since <pre>29/03/2020</pre>
 */
public class Siglas {
    static String siglas = "GÊNESIS = GEN\n" +
            "ÊXODO = EXO\n" +
            "LEVÍTICO = LEV\n" +
            "NÚMEROS = NUM\n" +
            "DEUTERONÔMIO = DEU\n" +
            "JOSUÉ = JS\n" +
            "JUÍZES = JZ\n" +
            "RUTE = RT\n" +
            "1O. LIVRO DE SAMUEL = 1 SM\n" +
            "2O. LIVRO DE SAMUEL = 2 SM\n" +
            "1O. LIVRO DE REIS = 1 RS\n" +
            "2O. LIVRO DE REIS = 2 RS\n" +
            "1O. LIVRO DE CRÔNICAS = 1 CR\n" +
            "2O. LIVRO DE CRÔNICAS = 2 CR\n" +
            "ESDRAS = ED\n" +
            "NEEMIAS = NE  \n" +
            "ESTER = ET\n" +
            "JÓ = JÓ\n" +
            "SALMOS = SL\n" +
            "PROVÉRBIOS  = PV\n" +
            "ECLESIASTES = EC\n" +
            "CÂNTICO DOS CÂNTICOS = CT\n" +
            "ISAÍAS = IS\n" +
            "JEREMIAS = JR\n" +
            "LAMENTAÇÕES DE JEREMIAS = LM\n" +
            "EZEQUIEL = EZ\n" +
            "DANIEL = DN\n" +
            "OSÉIAS = OS\n" +
            "JOEL = JL\n" +
            "AMÓS = AM\n" +
            "OBADIAS = OB\n" +
            "JONAS = JN\n" +
            "MIQUÉIAS = MQ\n" +
            "NAUM = NA\n" +
            "HABACUQUE = HC\n" +
            "SOFONIAS = SF\n" +
            "AGEU = AG\n" +
            "ZACARIAS = ZC\n" +
            "MALAQUIAS = ML\n" +
            "MATEUS = MT\n" +
            "MARCOS = MC\n" +
            "LUCAS = LC\n" +
            "JOÃO = JO\n" +
            "ATOS DOS APÓSTOLOS = AT\n" +
            "ROMANOS = RM\n" +
            "1A. CARTA AOS CORÍNTIOS = 1 CO\n" +
            "2A. CARTA AOS CORÍNTIOS = 2 CO\n" +
            "GÁLATAS = GL\n" +
            "EFÉSIOS = EF\n" +
            "FILIPENSES = FP\n" +
            "COLOSSENSES = CL\n" +
            "1A. CARTA AOS TESSALONICENSES = 1 TS\n" +
            "2A. CARTA AOS TESSALONICENSES = 2 TS\n" +
            "1A. CARTA A TIMÓTEO = 1 TM\n" +
            "2A. CARTA A TIMÓTEO = 2 TM\n" +
            "TITO = TT\n" +
            "FILEMON = FM\n" +
            "HEBREUS = HB\n" +
            "TIAGO = TG\n" +
            "1A. CARTA DE PEDRO = 1 PE\n" +
            "2A. CARTA DE PEDRO = 2 PE\n" +
            "1A. CARTA DE JOÃO = 1 JO\n" +
            "2A. CARTA DE JOÃO = 2 JO\n" +
            "3A. CARTA DE JOÃO = 3 JO\n" +
            "JUDAS = JD\n" +
            "APOCALIPSE = AP\n";

    public static void main(String[] args) throws IOException {
        String[] livros = siglas.split("\n");
        List<String> siglasLivros = new ArrayList<>();

        for (String livro : livros) {
            siglasLivros.add(livro.split(" = ")[1].trim().toUpperCase());
        }
        siglasLivros.forEach(System.out::println);
        List<String> arquivos = getArquivos();
        arquivos.forEach(System.out::println);

    }

    private static List<String> getArquivos() throws IOException {
        String path = "C:\\Meus Sites\\bible\\www.bible.com\\pt\\bible\\129\\";

        List<String> arquivos = Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .map( e-> e.toFile().getName())
                .filter(e -> e.endsWith(".html"))
                .collect(Collectors.toList());
        return arquivos;
    }
}