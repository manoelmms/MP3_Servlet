package br.ufrj.ic.trabalhofinal;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;

import javax.ws.rs.*;
import java.util.Map;

@Path("/salvar")
public class SalvarResource {
    @GET
    @Produces("text/html")
    public String Salvar(@QueryParam("título") @DefaultValue("") String titulo, @QueryParam("artista") @DefaultValue("") String artista,
                         @QueryParam("álbum") @DefaultValue("") String album, @QueryParam("faixa") @DefaultValue("") String faixa,
                         @QueryParam("ano") @DefaultValue("") String ano,
                         @QueryParam("gênero") @DefaultValue("") String genero,
                         @QueryParam("compositor") @DefaultValue("") String compositor, @QueryParam("artista original") @DefaultValue("") String original,
                         @QueryParam("comentário") @DefaultValue("") String comentario, @QueryParam("copyright") @DefaultValue("") String copyright,
                         @QueryParam("url") @DefaultValue("") String url, @QueryParam("encoder") @DefaultValue("") String encoder,
                         @QueryParam("filename") @DefaultValue("") String filename){
        return salvarParam(titulo, artista, album, faixa, ano, genero, compositor, original, comentario, copyright, url, encoder, filename);
    }
    protected static String salvarParam(String titulo, String artista, String album, String faixa, String ano,
                                        String genero,
                                        String compositor,
                                        String original, String comentario, String copyright, String url, String encoder,
                                        String filename){
        try {
            filename += ".mp3";
            Mp3File mp3file = new Mp3File(MusicApplication.filepath);
            ID3v2 oldId3v2Tag;
            oldId3v2Tag = new ID3v24Tag();
            oldId3v2Tag = mp3file.getId3v2Tag();
            byte[] albumImageData = oldId3v2Tag.getAlbumImage();
            String mimeType = oldId3v2Tag.getAlbumImageMimeType();

            mp3file.removeId3v2Tag();
            ID3v2 id3v2Tag;
            id3v2Tag = new ID3v24Tag();
            mp3file.setId3v2Tag(id3v2Tag);
            id3v2Tag.setTitle(titulo);
            id3v2Tag.setArtist(artista);
            id3v2Tag.setAlbum(album);
            id3v2Tag.setTrack(faixa);
            id3v2Tag.setYear(ano);
            if(!genero.equals("-1")) {
                id3v2Tag.setGenre(Integer.valueOf(genero));
            }
            id3v2Tag.setComposer(compositor);
            id3v2Tag.setOriginalArtist(original);
            id3v2Tag.setComment(comentario);
            id3v2Tag.setCopyright(copyright);
            id3v2Tag.setUrl(url);
            id3v2Tag.setEncoder(encoder);
            id3v2Tag.setAlbumImage(albumImageData, mimeType);
            mp3file.save(filename);
            return sucessoHTML(filename);
        }catch (Exception e){
            return MusicApplication.erroHTML();
        }
    }


    protected static String sucessoHTML(String filename){
        String html = "<html><head><meta charset=\"UTF-8\"><title>Sucesso!</title></head>";
        html += "<body><header><nav>" +
                "<h1>Download do Arquivo</h1><p><a href=\"file\">Voltar</a></p>" +
                "</nav></header>" +
                "<main>"+
                "<h2>Sucesso no Upload!</h2>" +
                "<a href=\"download\\" + filename + "\">DOWNLOAD</a>" +
                "</main></body></html>";
        return html;
    }
}
