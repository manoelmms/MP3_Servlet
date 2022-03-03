package br.ufrj.ic.trabalhofinal;

import javax.ws.rs.*;

@Path("/listar")
public class MusicResource {
    @GET
    @Produces("text/html")
    public String listar(@QueryParam("q") @DefaultValue("") String filepath) {
        try {
            return MusicApplication.infoMP3("/Users/manoelsilva/" + filepath);//nao gosta de espaço
        } catch (Exception e) {
            return null;
        }
    }
}
