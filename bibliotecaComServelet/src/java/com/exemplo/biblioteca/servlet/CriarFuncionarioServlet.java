package com.exemplo.biblioteca.servlet;

import com.exemplo.biblioteca.dao.FuncionariosDao;
import com.exemplo.biblioteca.entidades.Funcionario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/cria-funcionario-servlet"})
public class CriarFuncionarioServlet extends HttpServlet {
    
    @Inject
    private FuncionariosDao dao;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            try{
                String nome = req.getParameter ("nome");
                String idade = req.getParameter ("idade");
            
                Funcionario novoFuncionario = new Funcionario();
                novoFuncionario.setNome(nome);
                novoFuncionario.setIdade(Integer.valueOf(idade));
                dao.adicionaFuncionario(novoFuncionario);
                out.println("<h1>Funcionario adicionado com sucesso</h1>");
            }catch(NumberFormatException ex){
                out.println("<h1>Erro ao adicionar novo funcionário</h1>");                
            }
            
            out.println("<a href=\"/bibliotecaComServelet\">Biblioteca</a>");
            out.println("</body></html>");  
        }

}
