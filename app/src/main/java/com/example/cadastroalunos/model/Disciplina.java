package com.example.cadastroalunos.model;

import androidx.annotation.Nullable;
import com.orm.SugarRecord;

import java.util.Objects;

public class Disciplina extends SugarRecord {
    private String nome;
    private String raProfessor;

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

    public String getRaProfessor() {
        return raProfessor;
    }

    public void setRaProfessor(String raProfessor) {
        this.raProfessor = raProfessor;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nome='" + nome + '\'' +
                ", raProfessor='" + raProfessor + '\'' +
                '}';
    }
}