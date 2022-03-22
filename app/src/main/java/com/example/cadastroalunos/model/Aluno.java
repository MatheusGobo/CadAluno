package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Aluno extends SugarRecord {

    private int ra;
    private String nome;
    private String cpf;
    private String dtNasc;
    private String dtMatricula;
    private String idTurma;

    public Aluno() {
    }

    public Aluno(int ra, String nome, String cpf, String dtNasc, String dtMatricula, String idTurma) {
        this.ra = ra;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.dtMatricula = dtMatricula;
        this.idTurma = idTurma;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getDtMatricula() {
        return dtMatricula;
    }

    public void setDtMatricula(String dtMatricula) {
        this.dtMatricula = dtMatricula;
    }

    public String getIdTurma() {
        return idTurma;
    }

    public void setId_turma(String idTurma) {
        this.idTurma = idTurma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return ra == aluno.ra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ra);
    }
}
