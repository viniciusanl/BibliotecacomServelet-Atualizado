package com.exemplo.biblioteca.servlet;

import com.exemplo.biblioteca.dao.AlunosDao;
import com.exemplo.biblioteca.entidades.Aluno;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pesquisar-aluno-servlet")
public class PesquisaAlunoServlet extends HttpServlet {
    
    @Inject
    private AlunosDao dao;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<ul>");
            
            String nome = req.getParameter("nome");
            List<Aluno>alunos;
            if(nome == null || nome.isEmpty()){
                alunos = dao.buscaTodosAlunos();
            } else{
                alunos = dao.buscaAlunoPorNome(nome);
            }
            alunos.forEach((aluno) -> {
                out.println("<li>" + aluno.getNome() + "</li>");
        });
            
            out.println("</ul>");
            out.println("<a href=\"/bibliotecaComServelet\">Biblioteca</a>");
            out.println("</body></html>");
        }
}