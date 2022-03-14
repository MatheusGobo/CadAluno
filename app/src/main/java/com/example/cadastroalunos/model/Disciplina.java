package com.example.cadastroalunos.model;

import androidx.annotation.Nullable;
import com.orm.SugarRecord;

import java.util.Objects;

public class Disciplina extends SugarRecord {
    private String nome;
    private int raProfessor;

    public Disciplina(){

    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Disciplina disciplina = (Disciplina) obj;
        return nome == disciplina.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRaProfessor() {
        return raProfessor;
    }

    public void setRaProfessor(int raProfessor) {
        this.raProfessor = raProfessor;
    }

    @Override
    public String toString() {
        return nome;
    }
}