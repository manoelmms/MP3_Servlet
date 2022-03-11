/*
 *Manoel Marcelo da Silva (DRE: 121088349)
 *Lucas de Lyra Monteiro (DRE: 121039714)
 */

package br.ufrj.ic.trabalhofinal;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet("/api/upload")
@MultipartConfig
public class FileUploadService extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Recebe o Input do file
        String fileName = nameWithoutAmpersand(Paths.get(filePart.getSubmittedFileName()).getFileName().toString()); // Nome do Arquivo
        File uploads = new File("./"); //Prepara a gravação no local escolhido
        File file = new File(uploads, "output.mp3"); //Grava como output.mp3

        try (InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING); //Grava no arquivo preparado
            response.sendRedirect("/TrabalhoFinal-1.0-SNAPSHOT/api/listar?q="+fileName);
        } catch (IOException e) {
            response.sendRedirect("/TrabalhoFinal-1.0-SNAPSHOT/api/erro");
        }
    }

    //Nomes com & dão problema ao ler
    private String nameWithoutAmpersand(String filename) {
        StringBuilder str = new StringBuilder();

        for (int character=0; character <filename.length(); ++character) {
            if (filename.charAt(character) != '&') {
                str.append(filename.charAt(character));
            } else {
                str.append(("%26"));
            }
        }
        return str.toString();
    }
}