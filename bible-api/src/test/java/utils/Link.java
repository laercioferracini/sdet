package utils;

/**
 * @author lferracini
 * @project = sdet-multi-project
 * @since <pre>24/05/2020</pre>
 */
public class Link {
    private final String numero;
    String titulo;
    String autor;
    String ano;
    String link;

    public Link(String numero, String titulo, String autor, String ano, String link) {
        this.numero = numero;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.link = link;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getAno() {
        return ano;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "Link{" +
                "numero='" + numero + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano='" + ano + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
