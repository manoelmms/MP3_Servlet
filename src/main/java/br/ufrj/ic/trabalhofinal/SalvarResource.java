package br.ufrj.ic.trabalhofinal;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;

import javax.ws.rs.*;

@Path("/salvar")
public class SalvarResource {

    @GET
    @Produces("text/html")
    public String Salvar(@QueryParam("título") @DefaultValue("") String titulo,
                         @QueryParam("artista") @DefaultValue("") String artista,
                         @QueryParam("álbum") @DefaultValue("") String album,
                         @QueryParam("faixa") @DefaultValue("") String faixa,
                         @QueryParam("ano") @DefaultValue("") String ano,
                         @QueryParam("gênero") @DefaultValue("") String genero,
                         @QueryParam("compositor") @DefaultValue("") String compositor,
                         @QueryParam("artista original") @DefaultValue("") String original,
                         @QueryParam("comentário") @DefaultValue("") String comentario,
                         @QueryParam("copyright") @DefaultValue("") String copyright,
                         @QueryParam("url") @DefaultValue("") String url,
                         @QueryParam("encoder") @DefaultValue("") String encoder,
                         @QueryParam("artista do álbum") @DefaultValue("") String artAlbum,
                         @QueryParam("filename") @DefaultValue("") String filename){

        try {
            filename += ".mp3";
            Mp3File mp3file = new Mp3File(MusicApplication.FILEPATH);

            ID3v2 oldId3v2Tag = null;
            byte[] albumImageData = null;
            String mimeType = null;

            if (mp3file.hasId3v2Tag()) {
                oldId3v2Tag = mp3file.getId3v2Tag();
                albumImageData = oldId3v2Tag.getAlbumImage();
                mimeType = oldId3v2Tag.getAlbumImageMimeType();
            }

            mp3file.removeId3v2Tag();
            ID3v2 id3v2Tag;
            id3v2Tag = new ID3v24Tag();

            mp3file.setId3v2Tag(id3v2Tag);
            id3v2Tag.setTitle(titulo);
            id3v2Tag.setArtist(artista);
            id3v2Tag.setAlbum(album);
            id3v2Tag.setTrack(faixa);
            id3v2Tag.setYear(ano);
            id3v2Tag.setComposer(compositor);
            id3v2Tag.setOriginalArtist(original);
            id3v2Tag.setAlbumArtist(artAlbum);
            id3v2Tag.setComment(comentario);
            id3v2Tag.setCopyright(copyright);
            id3v2Tag.setUrl(url);
            id3v2Tag.setEncoder(encoder);

            if (!genero.equals("-1")) {
                id3v2Tag.setGenre(Integer.parseInt(genero));
            }

            if (oldId3v2Tag != null) {
                id3v2Tag.setAlbumImage(albumImageData, mimeType);
            }

            mp3file.save(filename);
            return sucessoHTML(filename);

        } catch (Exception e) {
            return MusicApplication.erroHtml(e.getMessage());
        }
    }


    private static String sucessoHTML(String filename) {
        String html = "<html><head><meta charset=\"UTF-8\"><title>Sucesso!</title>" +
                Styles.SalvarResourceCSS() +
                "</head>";

        html += "<body><header><nav>" +
                "<h1>Download do Arquivo</h1><p><a href=\"file\">Escolher outra música →</a></p>" +
                "</nav></header>" +
                "<main>"+
                "<h2>Sucesso no Upload!</h2>" +
                "<div id=\"download-div\">" +
                "<form method=\"GET\" action=\"download\\" + filename + "\">" +
                "<input id=\"download-image\" type=\"submit\" value=\"Download\">\n" +
                "</form>" +
                "</div>" +
                "</main></body></html>";

        return html;
    }
}
