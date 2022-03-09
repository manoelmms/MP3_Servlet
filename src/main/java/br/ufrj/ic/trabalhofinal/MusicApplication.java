package br.ufrj.ic.trabalhofinal;

import com.mpatric.mp3agic.*;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.annotation.MultipartConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;


@ApplicationPath("/api")
@MultipartConfig
public class MusicApplication extends Application{
    public static String filepath = "./output.mp3";

    protected static String erroHtml(String exception){

        String html = "<html><head><meta charset=\"UTF-8\"><title>Erro!</title></head>";
        html += "<body><h2>Deu ruim!</h2><p>O arquivo não pôde ser Lido/Gravado ou seu formato é incompartível! Erro: '"+ exception +"'</p><p><a href=\"file\">Voltar</a></p></body></html>";

        return html;
    }

    //Lê todas as informações imutáveis do MP3 recebido e coloca em um LinkedHashMap
    protected static LinkedHashMap<String, Object> immutableData(String filepath) throws IOException, UnsupportedTagException, InvalidDataException {
        LinkedHashMap<String, Object> mp3Info = new LinkedHashMap<>();

            Mp3File mp3file = new Mp3File(filepath);
            mp3Info.put("duração (min:seg)", secondsToMinutesColonSeconds(mp3file.getLengthInSeconds()));
            mp3Info.put("tamanho", (mp3file.getLength() + " bytes"));
            mp3Info.put("taxa de bits", mp3file.getBitrate() + " kbps " + "(" + vbrString(mp3file.isVbr())+")");
            mp3Info.put("versão do MP3", mp3file.getVersion());
            mp3Info.put("taxa de amostragem", mp3file.getSampleRate() + "Hz");
            mp3Info.put("channel", mp3file.getChannelMode());
            mp3Info.put("id3v1", englishBoolToPortugueseHave(mp3file.hasId3v1Tag()));
            mp3Info.put("id3v2", englishBoolToPortugueseHave(mp3file.hasId3v2Tag()));
            mp3Info.put("custom", englishBoolToPortugueseHave(mp3file.hasCustomTag()));

            return mp3Info;
    }

    //Lê todas as informações mutáveis do MP3 recebido e coloca em um LinkedHashMap
    protected static LinkedHashMap<String, String> mutableData(String filepath) throws IOException, UnsupportedTagException, InvalidDataException {
        LinkedHashMap<String, String> id3v2Map = new LinkedHashMap<>();

            Mp3File mp3file = new Mp3File(filepath);
            ID3v2 id3v2Tag;

            if (mp3file.hasId3v2Tag()) {
                id3v2Tag = mp3file.getId3v2Tag();
            } else if(mp3file.hasId3v1Tag()) {
                id3v2Tag = new ID3v24Tag();
                mp3file.setId3v2Tag(id3v2Tag);
                ID3v1 id3v1Tag;
                id3v1Tag = mp3file.getId3v1Tag();

                id3v2Tag.setTitle(id3v1Tag.getTitle());
                id3v2Tag.setArtist(id3v1Tag.getArtist());
                id3v2Tag.setAlbum(id3v1Tag.getAlbum());
                id3v2Tag.setTrack(id3v1Tag.getTrack());
                id3v2Tag.setYear(id3v1Tag.getYear());
                id3v2Tag.setGenreDescription(id3v1Tag.getGenreDescription());
                id3v2Tag.setAlbumArtist("");                    /* ID3V1 não tem a tag AlbumArtist */
                id3v2Tag.setComposer("");                       /* ID3V1 não tem a tag Composer */
                id3v2Tag.setOriginalArtist("");                 /* ID3V1 não tem a tag OriginalArtist */
                id3v2Tag.setComment(id3v1Tag.getComment());
                id3v2Tag.setCopyright("");                      /* ID3V1 não tem a tag Copyright */
                id3v2Tag.setUrl("");                            /* ID3V1 não tem a tag Url */
                id3v2Tag.setEncoder("");                        /* ID3V1 não tem a tag Encoder */
            } else {
                id3v2Tag = new ID3v24Tag();
                mp3file.setId3v2Tag(id3v2Tag);
            }

            id3v2Map.put("título", id3v2Tag.getTitle());
            id3v2Map.put("artista", id3v2Tag.getArtist());
            id3v2Map.put("álbum", id3v2Tag.getAlbum());
            id3v2Map.put("faixa", id3v2Tag.getTrack());
            id3v2Map.put("ano", id3v2Tag.getYear());
            id3v2Map.put("gênero", id3v2Tag.getGenreDescription());
            id3v2Map.put("artista do álbum", id3v2Tag.getAlbumArtist());
            id3v2Map.put("compositor", id3v2Tag.getComposer());
            id3v2Map.put("artista original", id3v2Tag.getOriginalArtist());
            id3v2Map.put("comentário", id3v2Tag.getComment());
            id3v2Map.put("copyright", id3v2Tag.getCopyright());
            id3v2Map.put("url", id3v2Tag.getUrl());
            id3v2Map.put("encoder", id3v2Tag.getEncoder());

            return id3v2Map;
    }

    private static String vbrString(boolean vbr) {
        if (vbr) {
            return "VBR";
        }
        return "CBR";
    }

    protected static String secondsToMinutesColonSeconds(long seconds) {
        return (int)seconds / 60 + ":" + String.format("%02d", seconds % 60);
    }

    protected static String englishBoolToPortugueseHave(boolean bool) {
        return bool?"Possui":"Não possui";
    }
}