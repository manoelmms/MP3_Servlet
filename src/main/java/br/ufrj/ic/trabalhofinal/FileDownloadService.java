/*
 *Manoel Marcelo da Silva (DRE: 121088349)
 *Lucas de Lyra Monteiro (DRE: 121039714)
 */

package br.ufrj.ic.trabalhofinal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/download/*")
public class FileDownloadService extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            PrintWriter out = response.getWriter();
            String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");  //Recebe o filename pela URL

            // Ajusta o tipo de conteúdo e o header para o response
            response.setContentType("audio/mpeg3");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

            FileInputStream inputStream = new FileInputStream("./" + filename); //Ajusta o caminho do arquivo

            // Faz a leitura pelo arquivo e grava para o output
            int in;
            while ((in = inputStream.read()) != -1) {
                out.write(in);
            }

            // Fecha o  FileInputStream e o PrintWriter
            inputStream.close();
            out.close();
        } catch (Exception e) {
            response.sendRedirect("/TrabalhoFinal-1.0-SNAPSHOT/api/erro");
        }
    }
}