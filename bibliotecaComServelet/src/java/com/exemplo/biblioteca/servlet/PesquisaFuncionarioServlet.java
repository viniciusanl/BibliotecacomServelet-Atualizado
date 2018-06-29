package com.exemplo.biblioteca.servlet;

import com.exemplo.biblioteca.dao.FuncionariosDao;
import com.exemplo.biblioteca.entidades.Funcionario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pesquisar-funcionario-servlet")
public class PesquisaFuncionarioServlet extends HttpServlet{
    
    @Inject
    private FuncionariosDao dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException{
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<ul>");
            
            String nome = req.getParameter("nome");
            List<Funcionario>funcionarios;
            if(nome == null || nome.isEmpty()){
                funcionarios = dao.buscaTodosFuncionarios();
            } else{
                funcionarios = dao.buscaFuncionarioPorNome(nome);
            }
            funcionarios.forEach((funcionario) -> {
                out.println("<li>" + funcionario.getNome() + "</li>");
        });

           out.println("</ul>");
            out.println("<a href=\"/bibliotecaComServelet\">Biblioteca</a>");
            out.println("</body></html>");
        }
}
