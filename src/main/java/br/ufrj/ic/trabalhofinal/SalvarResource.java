package br.ufrj.ic.trabalhofinal;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

import javax.ws.rs.*;
import java.util.Map;

@Path("/salvar")
public class SalvarResource {
    @GET
    @Produces("text/html")
    public String Salvar(@QueryParam("filename") @DefaultValue("") String filename, @QueryParam("titulo") @DefaultValue("") String titulo){
        return salvarParam(titulo, filename);
    }
    protected static String salvarParam(String titulo, String filename){
        try {
            Mp3File mp3file = new Mp3File(MusicApplication.filepath);
            Map<String, Object> mp3Info = MusicApplication.infoMP3(MusicApplication.filepath);
            ID3v2 id3v2Tag;
            id3v2Tag = mp3file.getId3v2Tag();
            id3v2Tag.setTitle(titulo);
            mp3file.save(filename);
            return MusicApplication.sucessoHTML();
        }catch (Exception e){
            return MusicApplication.erroHTML();
        }

    }


}
