package br.ufrj.ic.trabalhofinal;

import java.io.*;
import java.net.URLDecoder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/api/download/*")
public class FileDownloadService extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            // Get PrintWriter object
            PrintWriter out = response.getWriter();
            String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
            // Set the content type and header of the response.
            response.setContentType("audio/mpeg3");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

            // Get FileInputStream object to identify the path
            FileInputStream inputStream = new FileInputStream("./" + filename);

            // Loop through the document and write into the
            // output.
            int in;
            while ((in = inputStream.read()) != -1) {
                out.write(in);
            }
            // Close FileInputStream and PrintWriter object
            inputStream.close();
            out.close();
        }catch (Exception e){
            response.sendRedirect("/TrabalhoFinal-1.0-SNAPSHOT/api/erro");
        }
    }
}