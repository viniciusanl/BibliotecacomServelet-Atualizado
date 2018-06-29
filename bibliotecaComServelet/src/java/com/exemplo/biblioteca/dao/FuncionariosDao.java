package com.exemplo.biblioteca.dao;

import com.exemplo.biblioteca.entidades.Aluno;
import com.exemplo.biblioteca.entidades.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FuncionariosDao {
    @PersistenceContext(unitName = "bibliotecaPU")
    EntityManager em;
    
    public List<Funcionario> buscaTodosFuncionarios() {
        return em.createQuery("SELECT f FROM Funcionario f").getResultList();
    }
    
    public List<Funcionario> buscaFuncionarioPorNome(String nome) {
        List<Funcionario> funcionariosARetornar = new ArrayList<>();
        for (Funcionario funcionario : buscaTodosFuncionarios()) {
            if (funcionario.getNome().toLowerCase().contains(nome.toLowerCase())) {
                funcionariosARetornar.add(funcionario);
            }
        }
        return funcionariosARetornar;
    }
    
    public void adicionaFuncionario(Funcionario funcionario) {
        em.persist(funcionario);
    }

    public void removeAluno(Funcionario funcionario) {
        em.remove(funcionario);
    }
}
