package com.exemplo.biblioteca.servlet;

import com.exemplo.biblioteca.dao.LivrosDao;
import com.exemplo.biblioteca.entidades.Livro;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/cria-livro-servlet")
public class CriarLivroServlet extends HttpServlet {
    
    @Inject
    private LivrosDao dao;
    
    @Override
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            
            try{
                String titulo = req.getParameter ("titulo");
                String autor = req.getParameter ("autor");
                String numPaginas = req.getParameter ("paginas");
                
                Livro novoLivro = new Livro();
                novoLivro.setTitulo(titulo);
                novoLivro.setAutor(autor);
                novoLivro.setNumPaginas(Integer.valueOf(numPaginas));
                dao.adicionaLivro(novoLivro);
                out.println("<h1>Livro adicionado com sucesso</h1>");
            } catch(NumberFormatException ex){
                out.println("<h1>Erro ao adicionar novo livro</h1>");                
            }
            
            out.println("<a href=\"/bibliotecaComServelet\">Biblioteca</a>");
            out.println("</body></html>");  
        }
}