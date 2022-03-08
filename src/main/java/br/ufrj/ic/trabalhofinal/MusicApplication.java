package br.ufrj.ic.trabalhofinal;

import com.mpatric.mp3agic.ID3v1;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

    protected static LinkedHashMap<String, Object> immutableData(String filepath){
        LinkedHashMap<String, Object> mp3Info = new LinkedHashMap<>();
        try {
            Mp3File mp3file = new Mp3File(filepath);
            mp3Info.put("duração (min:seg)", secondsToMinutesColonSeconds(mp3file.getLengthInSeconds()));
            mp3Info.put("tamanho", (mp3file.getLength() + " bytes"));
            mp3Info.put("bitrate", (mp3file.getBitrate() + " Hz"));
            mp3Info.put("versão", mp3file.getVersion());
            mp3Info.put("sample", mp3file.getSampleRate());
            mp3Info.put("channel", mp3file.getChannelMode());
            mp3Info.put("id3v1", englishBoolToPortugueseHave(mp3file.hasId3v1Tag()));
            mp3Info.put("id3v2", englishBoolToPortugueseHave(mp3file.hasId3v2Tag()));
            mp3Info.put("custom", englishBoolToPortugueseHave(mp3file.hasCustomTag()));
            return mp3Info;

        }catch (Exception e){
            return null;
        }
    }

    protected static LinkedHashMap<String, String> mutableData(String filepath){
        LinkedHashMap<String, String> id3v2Map = new LinkedHashMap<>();

        try {
            Mp3File mp3file = new Mp3File(filepath);
            ID3v2 id3v2Tag;

            if (mp3file.hasId3v2Tag()) {
                id3v2Tag = mp3file.getId3v2Tag();
            }
            else if(mp3file.hasId3v1Tag()){
                id3v2Tag = new ID3v24Tag();
                mp3file.setId3v2Tag(id3v2Tag);
                ID3v1 id3v1Tag;
                //id3v2Tag = (ID3v2) mp3file.getId3v1Tag(); /*tem que ver se isso funciona*/
                id3v1Tag = mp3file.getId3v1Tag();
                id3v2Tag.setTitle(id3v1Tag.getTitle());
                id3v2Tag.setArtist(id3v1Tag.getArtist());
                id3v2Tag.setAlbum(id3v1Tag.getAlbum());
                id3v2Tag.setTrack(id3v1Tag.getTrack());
                id3v2Tag.setYear(id3v1Tag.getYear());
                id3v2Tag.setGenreDescription(id3v1Tag.getGenreDescription());
                id3v2Tag.setComposer(""); /*ID3V1 não tem a tag Composer*/
                id3v2Tag.setOriginalArtist(""); /*ID3V1 não tem a tag OriginalArtist*/
                id3v2Tag.setComposer(id3v1Tag.getComment());
                id3v2Tag.setCopyright(""); /*ID3V1 não tem a tag Copyright*/
                id3v2Tag.setUrl(""); /*ID3V1 não tem a tag Url*/
                id3v2Tag.setEncoder(""); /*ID3V1 não tem a tag Encoder*/
            } else{
                id3v2Tag = new ID3v24Tag();
                mp3file.setId3v2Tag(id3v2Tag);
            }


            id3v2Map.put("título", id3v2Tag.getTitle());
            id3v2Map.put("artista", id3v2Tag.getArtist());
            id3v2Map.put("álbum", id3v2Tag.getAlbum());
            id3v2Map.put("faixa", id3v2Tag.getTrack());
            id3v2Map.put("ano", id3v2Tag.getYear());
            id3v2Map.put("gênero", id3v2Tag.getGenreDescription());
            id3v2Map.put("compositor", id3v2Tag.getComposer());
            id3v2Map.put("artista original", id3v2Tag.getOriginalArtist());
            id3v2Map.put("comentário", id3v2Tag.getComment());
            id3v2Map.put("copyright", id3v2Tag.getCopyright());
            id3v2Map.put("url", id3v2Tag.getUrl());
            id3v2Map.put("encoder", id3v2Tag.getEncoder());
            //id3v2tag.put("Imagem do album", id3v2Tag.getAlbumImage());
            return id3v2Map;

        }catch (Exception e){
            return null;
        }
    }

    protected static String secondsToMinutesColonSeconds(long seconds){
        return (int)seconds / 60 + ":" + (int)seconds % 60;
    }

    protected static String englishBoolToPortugueseHave(boolean bool){
        return bool?"Possui":"Não possui";
    }
}
