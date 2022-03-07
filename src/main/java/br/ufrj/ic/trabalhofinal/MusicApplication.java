package br.ufrj.ic.trabalhofinal;

import com.mpatric.mp3agic.ID3v1;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;


@ApplicationPath("/api")
@MultipartConfig
public class MusicApplication extends Application{
    public static String filepath = "./output.mp3";

    protected static String sucessoHTML(){
        String html = "<html><head><meta charset=\"UTF-8\"><title>Sucesso!</title></head>";
        html += "<body><h2>Sucesso no Upload!</h2><p>O arquivo foi gravado com sucesso!</p><p><a href=\"file\">Voltar</a></p></body></html>";
        return html;
    }

    protected static String erroHTML(){
        String html = "<html><head><meta charset=\"UTF-8\"><title>Erro!</title></head>";
        html += "<body><h2>Deu ruim!</h2><p>O arquivo não foi Gravado!</p><p><a href=\"file\">Voltar</a></p></body></html>";
        return html;
    }

    protected static Map<String, Object> infoMP3(String filepath){
        Map<String, Object> mp3Info = new HashMap<>();
        try {
            Mp3File mp3file = new Mp3File(filepath);
            mp3Info.put("duracao", mp3file.getLengthInSeconds());
            mp3Info.put("tamanho", mp3file.getLength());
            mp3Info.put("bitrate", mp3file.getBitrate());
            mp3Info.put("versao", mp3file.getVersion());
            mp3Info.put("sample", mp3file.getSampleRate());
            mp3Info.put("channel", mp3file.getChannelMode());
            mp3Info.put("id3v1", mp3file.hasId3v1Tag());
            mp3Info.put("id3v2", mp3file.hasId3v2Tag());
            mp3Info.put("custom", mp3file.hasCustomTag());
            return mp3Info;

        }catch (Exception e){
            return null;
        }
    }



    protected static Map<String, Object> showId3v2Tags(String filepath){
        Map<String, Object> id3v2Map = new HashMap<String, Object>();
        try {
            Mp3File mp3file = new Mp3File(filepath);
            ID3v2 id3v2Tag;

            if (mp3file.hasId3v2Tag()) {
                id3v2Tag = mp3file.getId3v2Tag();
            } else if(mp3file.hasId3v1Tag()){
                // mp3 does not have an ID3v2 tag, let's create one..
                id3v2Tag = new ID3v24Tag();
                mp3file.setId3v2Tag(id3v2Tag);
                ID3v1 id3v1Tag;
                id3v1Tag = mp3file.getId3v1Tag();
                id3v2Tag.setTrack(id3v1Tag.getTrack());
                id3v2Tag.setArtist(id3v1Tag.getArtist());//TODO: Fazer as outras tags

            }else{
                id3v2Tag = new ID3v24Tag();
                mp3file.setId3v2Tag(id3v2Tag);
            }
            id3v2Map.put("faixa", id3v2Tag.getTrack());
            id3v2Map.put("artista", id3v2Tag.getArtist());
            id3v2Map.put("album", id3v2Tag.getAlbum());
            id3v2Map.put("titulo", id3v2Tag.getTitle());
            id3v2Map.put("ano", id3v2Tag.getYear());
            id3v2Map.put("genero", id3v2Tag.getGenreDescription());
            id3v2Map.put("comentario", id3v2Tag.getComment());
            id3v2Map.put("compositor", id3v2Tag.getComposer());
            id3v2Map.put("artista original", id3v2Tag.getOriginalArtist());
            id3v2Map.put("copyright", id3v2Tag.getCopyright());
            id3v2Map.put("url", id3v2Tag.getUrl());
            id3v2Map.put("encoder", id3v2Tag.getEncoder());
            //id3v2tag.put("Imagem do album", id3v2Tag.getAlbumImage());
            return id3v2Map;

        }catch (Exception e){
            return null;
        }
    }
}
