package br.ufrj.ic.trabalhofinal;

import com.sun.xml.internal.ws.util.StringUtils;

import javax.ws.rs.*;
import java.util.LinkedHashMap;
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
        Map<String, Object> immutableInfoMP3 = MusicApplication.immutableData(MusicApplication.filepath);
        String immutables = "<div class=immutables>";
        immutables += "<h2>Dados imutáveis</h2>";
        immutables += "<ul>";

        for (Map.Entry<String, Object> tags : immutableInfoMP3.entrySet()){
            immutables += "<li>" + StringUtils.capitalize(tags.getKey())  + ": " + tags.getValue() + "</li>";
        }
        // TODO: mudar para minuto:segundos
        immutables += "</ul></div>";
        return immutables;
    }


    private static String htmlForm(String filename) {
        LinkedHashMap<String, String> mutableInfoMP3 = MusicApplication.mutableData(MusicApplication.filepath);
        mutableInfoMP3.put("filename", filename);

        String formhtml = "<form method=\"GET\" action=\"salvar\">";
        formhtml += "<fieldset><legend>Dados mutáveis</legend>";


        for (Map.Entry<String,String> tags : mutableInfoMP3.entrySet()){
            formhtml += "<div class=\"form-div\">\n";
            formhtml += "<label class=\"mutable-label\" for='" + tags.getKey() + "'>" + StringUtils.capitalize(tags.getKey()) + ":</label>";
            formhtml += "<input type=\"text\" name='" + tags.getKey() + "'value='" + stringWrittenNull(tags.getValue()) + "'>"; //TODO: forms pegando somente uma palavra
            formhtml += "</div>\n";
        }

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

        css +=  ".form-div{\n" +
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

        css+=   ".immutables{\n" +
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
                      //"<link rel='stylesheet' type='text/css' href='" + HttpServletRequest.getPathInfo() + "/style.css'/>" +
        "</head>";

        html += "<body>" +
                "<header>" +
                "<nav><h1>Informações sobre a música</h1><p><a href=\"file\">Escolher outra música →</a></p></nav>" +
                "</header>"+
                "<main>" + immutableObjectsHTML() + htmlForm(filename) + "</main>" +
                "<body></html>";
        /*html = html.replace("$formadicionar", htmlForm());*/ // TODO: consertar o replace
        return html;
    }

    public static String stringWrittenNull(Object object){
        return object==null?"":object.toString();
    }
}
