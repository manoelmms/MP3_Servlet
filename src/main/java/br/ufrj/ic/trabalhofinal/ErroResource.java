package br.ufrj.ic.trabalhofinal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/erro")
public class ErroResource {
    @GET
    @Produces("text/html")
    public String erroHtml() {
        String html = "<html><head><meta charset=\"UTF-8\"><title>Erro!</title>" +
                Styles.ErroResourceCSS() +
                "</head>";

        html += "<body><main>" +
                "<h1>Deu ruim!</h1>" +
                "<p>O arquivo não foi Upado/Enviado!<br><br> Tente Novamente.</p>" +
                "<a class=\"button\" href=\"file\">Clique aqui para voltar ao menu inicial</a>" +
                "</main></body></html>";

        return html;
    }
}
