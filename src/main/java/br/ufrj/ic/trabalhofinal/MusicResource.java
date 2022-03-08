package br.ufrj.ic.trabalhofinal;

import com.sun.xml.internal.ws.util.StringUtils;

import javax.ws.rs.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Path("/listar")
public class MusicResource {

    /*public static String css = "/music-resource.css";*/

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
            formhtml += "<div class=\"form-div\">";
            formhtml += "<label class=\"mutable-label\" for='" + tags.getKey() + "'>" + StringUtils.capitalize(tags.getKey()) + ":</label>";
            formhtml += "<input type=\"text\" name='" + tags.getKey() + "'value='" + stringWrittenNull(tags.getValue()) + "'>"; //TODO: forms pegando somente uma palavra
            formhtml += "</div>";
        }

        formhtml += "<input type=\"submit\" value=\"Salvar\"></input>";
        formhtml += "</fieldset></form>";

        return formhtml;
    }





    public static String createFinalHTML(String filename){
        String html = "<html><head><meta charset=\"UTF-8\">" +
                      "<title>Dados do MP3</title>" +
                      Styles.MusicResourceCSS() +
                      //"<link rel='stylesheet' type='text/css' href='" + HttpServletRequest.getPathInfo() + "/music-resource.css'/>" +
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
