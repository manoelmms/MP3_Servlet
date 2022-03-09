package br.ufrj.ic.trabalhofinal;

public class Styles {

    public static String UploadResourceCSS(){
        String css = "<style>";

        css += "*{\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    box-sizing: border-box;\n" +
                "    font-family: Verdana, SansSerif;\n" +
                "}\n";

        css +=  "body{\n" +
                "    background-color: #13293D;\n" +
                "    display: flex;\n" +
                "    align-items: center;\n" +
                "    justify-content: center;\n" +
                "}\n";

        css +=  "form{\n" +
                "    width: 75vh;\n" +
                "    height: 75vh;\n" +
                "    background: #E8F1F2;\n" +
                "    margin: 5px;\n" +
                "    padding: 20px 35px 10px;\n" +
                "    border-radius: 10px;\n" +
                "    display: grid;\n" +
                "    box-sizing: border-box;\n" +
                "    grid-template-rows: repeat(5, auto);\n" +
                "}\n";

        css += "h1, .file-selector{\n" +
                "    display: flex;\n" +
                "    align-items: center;\n" +
                "    justify-content: center;\n" +
                "    text-align: center;\n" +
                "}\n";

        css +=  ".file-selector{\n" +
                "    grid-row: 2/5;\n" +
                "    position: relative;\n" +
                "    background-color: rgba(0, 0, 255, 0.1);\n" +
                "    border-radius: 10px;\n" +
                "    margin-bottom: 10px;\n" +
                "}\n\n" +

                "label{\n" +
                "    display: flex;\n" +
                "    width: 100%;\n" +
                "    height: 100%;\n" +
                "    color: black;\n" +
                "    align-items: center;\n" +
                "    flex-direction: column;\n" +
                "    justify-content: center;\n" +
                "    cursor: pointer;\n" +
                "}\n";

        css +=  "#file{\n" +
                "    position: absolute;\n" +
                "    bottom: 20px;\n" +
                "    left: 20px;\n" +
                "}\n" +
                "\n" +
                "::file-selector-button {\n" +
                "    display: none;\n" +
                "}\n";

        css +=  "p{\n" +
                "    text-decoration: underline;\n" +
                "}\n";

        css +=  "#enviar{\n" +
                "    border: none;\n" +
                "    margin-left: auto;\n" +
                "    margin-right: auto;\n" +
                "    border-radius: 10px;\n" +
                "    width: 50%;\n" +
                "    text-decoration: none;\n" +
                "    display: inline-block;\n" +
                "    font-size: 16px;\n" +
                "    background-color: rgba(0, 0, 255, 0.1);\n" +
                "    cursor: pointer;\n" +
                "}\n\n" +

                "#enviar:hover, .file-selector:hover{\n" +
                "    background-color: rgba(133, 25, 150, 0.1);\n" +
                "}\n";

        css += "</style>";
        return css;
    }



    public static String MusicResourceCSS(){
        String css = "<style>";
        css += "*{\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    box-sizing: border-box;\n" +
                "    font-family: Verdana, SansSerif;\n" +
                "}\n";

        css +=  "body{\n" +
                "    background-color: #13293D;\n" +
                "}\n";

        css +=  "nav{\n" +
                "    display: flex;\n" +
                "    justify-content: space-between;\n" +
                "    align-items: center;\n" +
                "    background: #006494;\n" +
                "    height: 8vh;\n" +
                "}\n";

        css +=  "a{\n" +
                "    color: white;\n" +
                "    text-decoration: none;\n" +
                "    margin-right: 3vh;\n" +
                "}\n\n" +

                "a:hover{\n" +
                "    color: red;\n" +
                "    opacity: 0.8;\n" +
                "}\n";

        css +=  "main{\n" +
                "      display: grid;\n" +
                "      margin-top: 3vh;" +
                "      grid-gap: 20px;\n" +
                "      height: 89vh;\n" +
                "}\n";

        css +=  "form, .immutables {\n" +
                "    width: 99%;\n" +
                "    background: #E8F1F2;\n" +
                "    padding: 35px 35px;\n" +
                "    margin-left: 5px;\n" +
                "    margin-right: 5px;\n" +
                "    border-radius: 10px;\n" +
                "}\n";

        css +=  "form{\n" +
                "    grid-column: 2;\n" +
                "}\n";

        css +=  "fieldset{\n" +
                "    font-size: 15px;\n" +
                "    display: flex;\n" +
                "    flex-wrap: wrap;\n" +
                "    justify-content: space-between;\n" +
                "    border: none;\n" +
                "}\n\n" +

                "legend, h2{\n" +
                "    font-size: 40px;\n" +
                "    margin-bottom: 10px;\n" +
                "    font-weight: 500;\n" +
                "}\n";

        css +=  ".form-div{\n" +
                "    width: calc(100%/2 - 20px);\n" +
                "    margin-bottom: 1vh;\n" +
                "}\n";

        css +=  "input, select{\n" +
                "    height: 30px;\n" +
                "    width: 100%;\n" +
                "    padding-left: 15px;\n" +
                "    border-radius: 10px;\n" +
                "}\n\n" +

                ".mutable-label{\n" +
                "    font-weight: 500;\n" +
                "    margin-bottom: 10px;\n" +
                "}\n";

        css+=   ".immutables{\n" +
                "    grid-column: 1;\n" +
                "}\n";

        css +=  ".immutables ul{\n" +
                "    list-style: none;\n" +
                "}\n\n" +

                ".immutables li{\n" +
                "    margin-top: 1vh;\n" +
                "}\n\n" +

                ".immutables li::before{\n" +
                "    content: \"■\";\n" +
                "    padding-right: 8px;\n" +
                "}\n";

        css+=   "h1{\n" +
                "    text-align: center;\n" +
                "    justify-content: center;\n" +
                "    color: white;\n" +
                "    margin-left: 3vh;\n"+
                "}\n";


        css += "</style>";
        return css;
    }

    public static String SalvarResourceCSS(){
        String css = "<style>";
        css += "*{\n" +
                "    margin: 0;\n" +
                "    padding: 0;\n" +
                "    box-sizing: border-box;\n" +
                "    font-family: Verdana, SansSerif;\n" +
                "}\n";

        css +=  "body{\n" +
                "    background-color: #13293D;\n" +
                "    vertical-align: top;\n" +
                "}\n";

        css +=  "nav{\n" +
                "    display: flex;\n" +
                "    justify-content: space-between;\n" +
                "    align-items: center;\n" +
                "    background: #006494;\n" +
                "    height: 8vh;\n" +
                "}\n";

        css +=  "a{\n" +
                "    color: white;\n" +
                "    text-decoration: none;\n" +
                "    margin-right: 3vh;\n" +
                "}\n\n" +

                "a:hover{\n" +
                "    color: red;\n" +
                "    opacity: 0.8;\n" +
                "}\n";

        css +=  "main{\n" +
                "    position: relative;\n" +
                "    margin: auto;\n" +
                "    top: 50%;\n" +
                "    transform: translateY(-50%);\n" +
                "    width: 75vh;\n" +
                "    height: 75vh;\n" +
                "    background: #E8F1F2;\n" +
                "    padding: 35px 35px 10px;\n" +
                "    border-radius: 10px;\n" +
                "    display: grid;\n" +
                "    box-sizing: border-box;\n" +
                "    grid-template-rows: repeat(3, auto);\n" +
                "}\n";

        css+=   "h2{\n" +
                "    flex-direction: column;\n" +
                "    justify-content: center;\n" +
                "    align-items: center;\n" +
                "    display: flex;\n" +
                "    width: 100%;\n" +
                "}\n\n" +

                "h1{\n" +
                "    color: white;\n" +
                "    margin-left: 3vh;\n" +
                "}\n";


        css +=  ".download-div{\n" +
                "    position: absolute;\n" +
                "    grid-row: 2/5;\n" +
                "    background-color: rgba(0, 0, 255, 0.1);\n" +
                "    margin-bottom: 10px;\n" +
                "}\n";

        css +=  "#download-image{\n" +
                "    color: black;\n" +
                "    margin-bottom: auto;\n" +
                "    background-color: rgba(0, 0, 255, 0.1);\n" +
                "    width: 100%;\n" +
                "    height: 100%;\n" +
                "    border-radius: 10px;\n" +
                "    border: none;\n" +
                "    cursor: pointer;\n" +
                "    font-size: 20px;\n" +
                "}\n\n" +

                "#download-image:hover{\n" +
                "    color: black;\n" +
                "    background-color: rgba(9, 236, 100, 0.2);;\n" +
                "}\n";
        css += "</style>";
        return css;
    }
}
