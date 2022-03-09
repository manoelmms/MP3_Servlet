package br.ufrj.ic.trabalhofinal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/erro")
public class ErroResource {

    @GET
    @Produces("text/html")
    public String erroHtml() {
        String html = "<html><head><meta charset=\"UTF-8\"><title>Erro!</title></head>";
        html += "<body><h2>Deu ruim!</h2><p>O arquivo não foi Upado/Enviado! Tente Novamente.</p><p><a href=\"file\">Voltar</a></p></body></html>";

        return html;
    }
}
