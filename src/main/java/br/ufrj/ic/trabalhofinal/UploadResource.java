package br.ufrj.ic.trabalhofinal;

import javax.ws.rs.*;

@Path("/file") //Página Inicial
public class UploadResource{

    @GET
    @Produces("text/html")
    public String uploadForm() {
        String html = "<html><head><meta charset=\"UTF-8\"><title>Enviar MP3</title>" +
                Styles.UploadResourceCSS() +
                "</head>" +

                "<body>" +
                "<form action=\"upload\" method=\"post\" enctype=\"multipart/form-data\">" +
                "<h1>Escolha um arquivo MP3:</h1>" +
                "<div class=\"file-selector\">" +
                "<label for=\"file\">Clique aqui para escolher o arquivo<p>" +
                "<input type=\"file\" accept=\"audio/mpeg\" id=\"file\" name=\"file\">" +
                "</div>" +
                "<input type=\"submit\" id=\"enviar\"/>" +
                "</form>" +
                "</body>" +
                "</html>";

        return html;
    }
}