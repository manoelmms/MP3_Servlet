package br.ufrj.ic.trabalhofinal;

import javax.ws.rs.*;
import java.util.Map;

@Path("/listar")
public class MusicResource {

    /*public static String css = "/style.css";*/

    @GET
    @Produces("text/html")
    public String listar(@QueryParam("q") @DefaultValue("") String filename) {
        return createFinalHTML(filename);
    }


    private static String immutableObjectsHTML(){
        Map<String, Object> infoMP3 = MusicApplication.infoMP3(MusicApplication.filepath);
        String immutables = "<div class=immutables>";
        immutables += "<h2>Dados imutáveis</h2>";
        immutables += "<p>A duração é " + infoMP3.get("duracao") + " seg</p>"; // TODO: mudar para minuto:segundos
        immutables += "<p> O tamanho é " + infoMP3.get("tamanho") + " bytes</p>";
        immutables += "<p> A bitrate é " + infoMP3.get("bitrate") + " kbps</p>";
        immutables += "<p> A versao é " + infoMP3.get("versao") + "</p>";
        immutables += "<p> A taxa de amostragem é " + infoMP3.get("sample") + " Hz</p>";
        immutables += "<p> O canal é do tipo " + infoMP3.get("channel") + "</p>";
        immutables += "<p> ID3v1 " + infoMP3.get("id3v1") + "</p>";
        immutables += "<p> ID3v2 " + infoMP3.get("id3v2") + "</p>";
        immutables += "<p> Custom " + infoMP3.get("custom") + "</p>";
        immutables += "</div>";
        return immutables;
    }


    private static String htmlForm(String filename) {
        Map<String, Object> showId3v2Tags = MusicApplication.showId3v2Tags(MusicApplication.filepath);

        String formhtml = "<form method=\"GET\" action=\"salvar\">";

        formhtml += "<fieldset><legend>Dados mutáveis</legend>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"filename\">Nome do arquivo:</label>";
        formhtml += "<input type=\"filename\" id=\"filename\" name=\"filename\" value='" + filename + "'>"; // alterar para remover o ".mp3" ou não?
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"titulo\">Titulo:</label>";
        formhtml += "<input type=\"text\" id=\"titulo\" name=\"titulo\" value='" + showId3v2Tags.get("titulo") + "' >"; //TODO: forms pegando somente uma palavra
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"artista\">Artista:</label>";
        formhtml += "<input type=\"text\" id=\"artista\" name=\"artista\" value='" + showId3v2Tags.get("artista") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"album\">Album:</label>";
        formhtml += "<input type=\"text\" id=\"album\" name=\"album\" value='" + showId3v2Tags.get("album") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"faixa\">Faixa:</label>";
        formhtml += "<input type=\"text\" id=\"faixa\" name=\"faixa\" value='" + showId3v2Tags.get("faixa") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"ano\">Ano:</label>";
        formhtml += "<input type=\"text\" id=\"ano\" name=\"ano\" value='" + showId3v2Tags.get("ano") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"genero\">Genero:</label>";
        formhtml += "<input type=\"text\" id=\"genero\" name=\"genero\" value='" + showId3v2Tags.get("genero") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"compositor\">Compositor:</label>";
        formhtml += "<input type=\"text\" id=\"compositor\" name=\"compositor\" value='" + showId3v2Tags.get("compositor") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"artista original\">Artista original:</label>";
        formhtml += "<input type=\"text\" id=\"artista original\" name=\"artista original\" value='" + showId3v2Tags.get("artista original") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"comentario\">Comentario:</label>";
        formhtml += "<input type=\"text\" id=\"comentario\" name=\"comentario\" value='" + showId3v2Tags.get("comentario") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"copyright\">Copyright:</label>";
        formhtml += "<input type=\"text\" id=\"copyright\" name=\"copyright\" value='" + showId3v2Tags.get("copyright") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"URL\">url:</Label>";
        formhtml += "<input type=\"text\" id=\"url\" name=\"url\" value='" + showId3v2Tags.get("url") + "'>";
        formhtml += "</div>";

        formhtml += "<div class=\"form-div\">";
        formhtml += "<label class=\"mutable-label\" for=\"encoder\">Encoder:</label>";
        formhtml += "<input type=\"text\" id=\"encoder\" name=\"encoder\" value='" + showId3v2Tags.get("encoder") + "'>";
        formhtml += "</div>";

        formhtml += "<input type=\"submit\" value=\"Salvar\"></input>";

        formhtml += "</fieldset></form>";
        return formhtml;
    }


    public static String createCSS (){
        String css = "<style>";

        css += "*{\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    box-sizing: border-box;\n" +
                "    font-family: Verdana, SansSerif;\n" +
                "}\n";

        css +=  "body{\n" +
                "      display: grid;\n" +
                "      background: #0a0101;\n" +
                "      grid-gap: 20px;\n" +
                "}\n";

        css +=  "form, .immutables {\n" +
                "    width: 100%;\n" +
                "    background: white;\n" +
                "    padding: 40px 40px;\n" +
                "    margin: 10px;" +
                "    border-radius: 10px;\n" +
                "    grid-row: 2;" +
                "}\n";

        css +=  "form{\n" +
                "    grid-column: 2;\n" +
                "}\n";

        css +=  "fieldset{\n" +
                "    font-size: 15px;\n" +
                "    display: flex;\n" +
                "    flex-wrap: wrap;\n" +
                "    justify-content: space-between;\n" +
                "    border: none;\n" +
                "}\n" +
                "legend, h2{\n" +
                "    font-size: 40px;\n" +
                "    margin-bottom: 10px;\n" +
                "    font-weight: 500;\n" +
                "}\n";

        css +=  ".form-div {\n" +
                "    width: calc(100%/2 - 20px);\n" +
                "}\n";

        css +=  "input{\n" +
                "    height: 30px;\n" +
                "    width: 100%;\n" +
                "    padding-left: 15px;\n" +
                "    border-radius: 10px;\n" +
                "}\n" +
                ".mutable-label{\n" +
                "    font-weight: 500;\n" +
                "    margin-bottom: 10px;\n" +
                "}\n";

        css+=   ".immutables {\n" +
                "    grid-column: 1;\n" +
                "}\n";

        css+=   "h1{\n" +
                "    text-align: center;\n" +
                "    justify-content: center;\n" +
                "    color: aquamarine;\n" +
                "    grid-column: 1 / span 2;\n" +
                "    grid-row: 1;\n" +
                "\n";

        css +=  "a{\n" +
                "    grid-row: 3;\n" +
                "}";

        css += "</style>";
        return css;
    }


    public static String createFinalHTML(String filename){
        String html = "<html><head><meta charset=\"UTF-8\">" +
                      "<title>AAAAAAAAA</title>" +
                      createCSS() +
                      "</head>";

        html += "<body><h1>Informações sobre a música</h1>" + immutableObjectsHTML() + htmlForm(filename) + "<p><a href=\"file\">Cancelar</a></p><body></html>";
        /*html = html.replace("$formadicionar", htmlForm());*/ // TODO: consertar o replace
        return html;
    }
}
