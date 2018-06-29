package com.exemplo.biblioteca.dao;

import com.exemplo.biblioteca.entidades.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LivrosDao {
    @PersistenceContext(unitName = "bibliotecaPU")
    EntityManager em;
    
    public List<Livro> buscaTodosLivros() {
        return em.createQuery("SELECT l FROM Livro l").getResultList();
    }

    public List<Livro> buscaLivroPorTitulo(String titulo) {
        List<Livro> livrosARetornar = new ArrayList<>();
        for (Livro livro : buscaTodosLivros()) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                livrosARetornar.add(livro);
            }
        }
        return livrosARetornar;
    }

    public void adicionaLivro(Livro livro) {
        em.persist(livro);
    }

    public void removeLivro(Livro livro) {
        em.remove(livro);
    }
}
