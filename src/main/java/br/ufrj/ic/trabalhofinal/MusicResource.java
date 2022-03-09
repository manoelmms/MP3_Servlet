package br.ufrj.ic.trabalhofinal;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sun.xml.internal.ws.util.StringUtils;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Path("/listar")
public class MusicResource {

    public static final String[] genreList = {"-1 - Nenhum" ,"0 - Blues", "1 - Classic Rock", "2 - Country", "3 - Dance", "4 - Disco", "5 - Funk", "6 - Grunge",
            "7 - Hip-Hop", "8 - Jazz", "9 - Metal", "10 - New Age", "11 - Oldies", "12 - Other", "13 - Pop", "14 - R&B", "15 - Rap", "16 - Reggae","17 - Rock",
            "18 - Techno", "19 - Industrial", "20 - Alternative", "21 - Ska", "22 - Death Metal", "23 - Pranks", "24 - Soundtrack", "25 - Euro-Techno", "26 - Ambient",
            "27 - Trip-Hop", "28 - Vocal", "29 - Jazz+Funk", "30 - Fusion", "31 - Trance", "32 - Classical", "33 - Instrumental", "34 - Acid", "35 - House", "36 - Game",
            "37 - Sound Clip", "38 - Gospel", "39 - Noise", "40 - Alternative Rock", "41 - Bass", "42 - Soul", "43 - Punk", "44 - Space", "45 - Meditative", "46 - Instrumental Pop",
            "47 - Instrumental Rock", "48 - Ethnic", "49 - Gothic", "50 - Darkwave", "51 - Techno-Industrial", "52 - Electronic", "53 - Pop-Folk", "54 - Eurodance",
            "55 - Dream", "56 - Southern Rock", "57 - Comedy", "58 - Cult", "59 - Gangsta", "60 - Top 40", "61 - Christian Rap", "62 - Pop/Funk", "63 - Jungle", "64 - Native US",
            "65 - Cabaret", "66 - New Wave", "67 - Psychadelic", "68 - Rave", "69 - Showtunes", "70 - Trailer", "71 - Lo-Fi", "72 - Tribal", "73 - Acid Punk", "74 - Acid Jazz",
            "75 - Polka", "76 - Retro", "77 - Musical", "78 - Rock & Roll", "79 - Hard Rock", "80 - Folk", "81 - Folk-Rock", "82 - National Folk", "83 - Swing", "84 - Fast Fusion",
            "85 - Bebob", "86 - Latin", "87 - Revival", "88 - Celtic", "89 - Bluegrass", "90 - Avantgarde", "91 - Gothic Rock", "92 - Progressive Rock", "93 - Psychedelic Rock",
            "94 - Symphonic Rock", "95 - Slow Rock", "96 - Big Band", "97 - Chorus", "98 - Easy Listening", "99 - Acoustic", "100 - Humour", "101 - Speech", "102 - Chanson",
            "103 - Opera", "104 - Chamber Music", "105 - Sonata", "106 - Symphony", "107 - Booty Bass", "108 - Primus", "109 - Porn Groove", "110 - Satire", "111 - Slow Jam",
            "112 - Club", "113 - Tango", "114 - Samba", "115 - Folklore", "116 - Ballad", "117 - Power Ballad", "118 - Rhythmic Soul", "119 - Freestyle", "120 - Duet",
            "121 - Punk Rock", "122 - Drum Solo", "123 - Acapella", "124 - Euro-House", "125 - Dance Hall", "126 - Goa", "127 - Drum & Bass", "128 - Club - House",
            "129 - Hardcore", "130 - Terror", "131 - Indie", "132 - BritPop", "133 - Negerpunk", "134 - Polsk Punk", "135 - Beat", "136 - Christian Gangsta Rap", "137 - Heavy Metal",
            "138 - Black Metal", "139 - Crossover", "140 - Contemporary Christian", "141 - Christian Rock", "142 - Merengue", "143 - Salsa", "144 - Thrash Metal", "145 - Anime",
            "146 - JPop", "147 - Synthpop"};

    @GET
    @Produces("text/html")
    public String listar(@QueryParam("q") @DefaultValue("") String filename) {
        return createFinalHTML(filename);
    }


    private static String immutableObjectsHTML() throws IOException, UnsupportedTagException, InvalidDataException {
        Map<String, Object> immutableInfoMP3 = MusicApplication.immutableData(MusicApplication.filepath);
        String immutables = "<div class=immutables>";
        immutables += "<h2>Dados imutáveis</h2>";
        immutables += "<ul>";

        for (Map.Entry<String, Object> tags : immutableInfoMP3.entrySet()){
            immutables += "<li>" + StringUtils.capitalize(tags.getKey())  + ": " + tags.getValue() + "</li>";
        }
        immutables += "</ul></div>";
        return immutables;
    }


    private static String htmlForm(String filename) throws IOException, UnsupportedTagException, InvalidDataException {
        LinkedHashMap<String, String> mutableInfoMP3 = MusicApplication.mutableData(MusicApplication.filepath);
        mutableInfoMP3.put("filename", filename.replace(".mp3", ""));

        String formhtml = "<form method=\"GET\" action=\"salvar\">";
        formhtml += "<fieldset><legend>Dados mutáveis</legend>";


        for (Map.Entry<String,String> tags : mutableInfoMP3.entrySet()){
            formhtml += "<div class=\"form-div\">";
            formhtml += "<label class=\"mutable-label\" for='" + tags.getKey() + "'>" + StringUtils.capitalize(tags.getKey()) + ":</label>";
            if (!tags.getKey().equals("gênero")){
                formhtml += "<input type=\"text\" name='" + tags.getKey() + "'value='" + stringWrittenNull(tags.getValue()) + "'>";
            }else{
                formhtml += createGenreSelect();
            }


            formhtml += "</div>";
        }


        formhtml += "<input type=\"submit\" value=\"Salvar\"></input>";
        formhtml += "</fieldset></form>";

        return formhtml;
    }

    private static LinkedHashMap<Integer, String> genresMap(){
        LinkedHashMap<Integer, String> genres = new LinkedHashMap<>();

        for(String g : genreList){
            String[] pares = g.split(" - ");
            genres.put(Integer.valueOf(pares[0]), pares[1]);
        }
        return genres;
    }

    private static String createGenreSelect() throws IOException, UnsupportedTagException, InvalidDataException{
        LinkedHashMap<Integer, String> genres = genresMap();
        LinkedHashMap<String, String> mutableInfoMP3 = MusicApplication.mutableData(MusicApplication.filepath);

        String selectHtml = "<select name=\"gênero\">";

        for(Map.Entry<Integer,String> gen : genres.entrySet()){
            if(mutableInfoMP3.get("gênero")!=null){
                if(mutableInfoMP3.get("gênero").equals(gen.getValue())){
                    selectHtml += "<option value='" + gen.getKey() + "'selected>" + gen.getValue() + "</option>";
                }
                else{
                    selectHtml += "<option value='" + gen.getKey() + "'>" + gen.getValue() + "</option>";
                }
            }else{
                selectHtml += "<option value='" + gen.getKey() + "'>" + gen.getValue() + "</option>";
            }
        }
        selectHtml += "</select>";

        return selectHtml;
    }


    public static String createFinalHTML(String filename){
        try{
            String html = "<html><head><meta charset=\"UTF-8\">" +
                    "<title>Dados do MP3</title>" +
                    Styles.MusicResourceCSS() +
                    //"<link rel='stylesheet' type='text/css' href='" + HttpServletRequest.getPathInfo() + "/music-resource.css'/>" +
                    "</head>";

            html += "<body>" +
                    "<header>" +
                    "<nav><h1>Informações sobre a música</h1><p><a href=\"file\">Escolher outra música →</a></p></nav>" +
                    "</header>"+
                    "<main>" + immutableObjectsHTML() + htmlForm(filename) + "</main>" +
                    "<body></html>";

            return html;
        }catch(Exception e){
            return MusicApplication.erroHTML(e.getMessage());
        }
    }

    public static String stringWrittenNull(Object object){
        return object==null?"":object.toString();
    }
}
