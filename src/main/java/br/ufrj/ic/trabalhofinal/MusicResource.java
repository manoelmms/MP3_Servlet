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
        immutables += "<ul>";
        immutables += "<li> A duração é " + infoMP3.get("duracao") + " seg</li>"; // TODO: mudar para minuto:segundos
        immutables += "<li> O tamanho é " + infoMP3.get("tamanho") + " bytes</li>";
        immutables += "<li> A bitrate é " + infoMP3.get("bitrate") + " kbps</li>";
        immutables += "<li> A versao é " + infoMP3.get("versao") + " </li>";
        immutables += "<li> A taxa de amostragem é " + infoMP3.get("sample") + " Hz</li>";
        immutables += "<li> O canal é do tipo " + infoMP3.get("channel") + "</li>";
        immutables += "<li> ID3v1 " + infoMP3.get("id3v1") + "</li>";
        immutables += "<li> ID3v2 " + infoMP3.get("id3v2") + "</li>";
        immutables += "<li> Custom " + infoMP3.get("custom") + "</li>";
        immutables += "</ul></div>";
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
                "    background-color: #0a0101;\n" +
                /*"    background-image: linear-gradient(135deg, pink, purple);\n" +*/
                "}\n";

        css +=  "nav{\n" +
                "    display: flex;\n" +
                "    justify-content: space-around;\n" +
                "    align-items: center;\n" +
                "    background: purple;\n" +
                "    height: 8vh;" +
                "}\n";

        css +=  "a{\n" +
                "    color: white;\n" +
                "    text-decoration: none;\n" +
                "}\n\n" +

                "a:hover{\n" +
                "    color: red;\n" +
                "    opacity: 0.8;\n" +
                "}\n";

        css +=  "main{\n" +
                "      display: grid;\n" +
                "      grid-gap: 20px;\n" +
                "      height: 92vh;" +
                "}\n";

        css +=  "form, .immutables {\n" +
                "    width: 99%;\n" +
                "    background: white;\n" +
                "    padding: 35px 35px;\n" +
                "    margin: 5px;" +
                "    border-radius: 10px;\n" +
                "    grid-row: 2;\n" +
                "    opacity: 0.7;\n" +
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
                "}\n\n" +

                "legend, h2{\n" +
                "    font-size: 40px;\n" +
                "    margin-bottom: 10px;\n" +
                "    font-weight: 500;\n" +
                "}\n";

        css +=  ".form-div {\n" +
                "    width: calc(100%/2 - 20px);\n" +
                "    margin-bottom: 1vh;\n" +
                "}\n";

        css +=  "input{\n" +
                "    height: 30px;\n" +
                "    width: 100%;\n" +
                "    padding-left: 15px;\n" +
                "    border-radius: 10px;\n" +
                "}\n\n" +

                ".mutable-label{\n" +
                "    font-weight: 500;\n" +
                "    margin-bottom: 10px;\n" +
                "}\n";

        css+=   ".immutables {\n" +
                "    grid-column: 1;\n" +
                "}\n";

        css +=  ".immutables ul{\n" +
                "    list-style: none;\n" +
                "}\n\n" +

                ".immutables li{\n" +
                "    margin-top: 1vh;\n" +
                "}\n\n" +

                ".immutables li::before{\n" +
                "    content: \"■\";\n" +
                "    padding-right: 8px;\n" +
                "}\n";

        css+=   "h1{\n" +
                "    text-align: center;\n" +
                "    justify-content: center;\n" +
                "    color: white;\n" +
                "\n";


        css += "</style>";
        return css;
    }


    public static String createFinalHTML(String filename){
        String html = "<html><head><meta charset=\"UTF-8\">" +
                      "<title>Dados do MP3</title>" +
                      createCSS() +
                      "</head>";

        html += "<body><header>" +
                "<nav><h1>Informações sobre a música</h1><p><a href=\"file\">Escolher outra música →</a></p></nav></header>"+
                "<main>" + immutableObjectsHTML() + htmlForm(filename) + "</main><body></html>";
        /*html = html.replace("$formadicionar", htmlForm());*/ // TODO: consertar o replace
        return html;
    }
}
