package com.exemplo.biblioteca.dao;

import com.exemplo.biblioteca.entidades.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AlunosDao {
    @PersistenceContext(unitName = "bibliotecaPU")
    EntityManager em;
    
    public List<Aluno> buscaTodosAlunos() {
        return em.createQuery("SELECT a FROM Aluno a").getResultList();
    }

    public List<Aluno> buscaAlunoPorNome(String nome) {
        List<Aluno> alunosARetornar = new ArrayList<>();
        for (Aluno aluno : buscaTodosAlunos()) {
            if (aluno.getNome().toLowerCase().contains(nome.toLowerCase())) {
                alunosARetornar.add(aluno);
            }
        }
        return alunosARetornar;
    }

    public void adicionaAluno(Aluno aluno) {
        em.persist(aluno);
    }

    public void removeAluno(Aluno aluno) {
        em.remove(aluno);
    }
}
