package com.exemplo.biblioteca.servlet;

import com.exemplo.biblioteca.dao.LivrosDao;
import com.exemplo.biblioteca.entidades.Livro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pesquisar-livro-servlet")
public class PesquisaLivroServlet extends HttpServlet {
    
    @Inject
    private LivrosDao dao;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<ul>");
            
            String titulo = req.getParameter("titulo");
            List<Livro>livros;
            if(titulo == null || titulo.isEmpty()){
                livros = dao.buscaTodosLivros();
            } else{
                livros = dao.buscaLivroPorTitulo(titulo);
            }
            livros.forEach((livro) -> {
                out.println("<li>" + livro.getTitulo() + "</li>");
        });
            
            out.println("</ul>");
            out.println("<a href=\"/bibliotecaComServelet\">Biblioteca</a>");
            out.println("</body></html>");
        }
}