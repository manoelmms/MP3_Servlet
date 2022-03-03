package br.ufrj.ic.trabalhofinal;

import com.mpatric.mp3agic.Mp3File;


import javax.servlet.annotation.MultipartConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;


@ApplicationPath("/api")
@MultipartConfig
public class MusicApplication extends Application{

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

    protected static String infoMP3(String filepath) throws Exception{
        try {
            Mp3File mp3file = new Mp3File(filepath);
            long duracao = mp3file.getLengthInSeconds();
            int bitrate = mp3file.getBitrate();
            boolean id3v1 = mp3file.hasId3v1Tag();
            boolean id3v2 = mp3file.hasId3v2Tag();
            boolean custom = mp3file.hasCustomTag();
            String html = "<html><head><meta charset=\"UTF-8\"><title>Dados do MP3</title></head>";
            html += "<body><h2>"+duracao+""+id3v1+""+id3v2+""+custom+"</h2><p>"+bitrate+"</p><p><a href=\"file\">Voltar</a></p></body></html>";
            return html;
        }catch (Exception e){
            return erroHTML();
        }
    }

}
