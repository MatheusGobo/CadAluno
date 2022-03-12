package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Turma extends SugarRecord {
    private String   periodo;
    private int      qtAlunos;
    private String   disciplina;
    private String   nome;
    private String   regime;

    public Turma() {
    }

    public Turma(String periodo, int qtAlunos, String disciplina, String nome, String regime) {
        this.periodo = periodo;
        this.qtAlunos = qtAlunos;
        this.disciplina = disciplina;
        this.nome = nome;
        this.regime = regime;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getQtAlunos() {
        return qtAlunos;
    }

    public void setQtAlunos(int qtAlunos) {
        this.qtAlunos = qtAlunos;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        disciplina = disciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return nome.equals(turma.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}