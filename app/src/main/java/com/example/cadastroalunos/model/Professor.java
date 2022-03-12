package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

import java.util.Objects;

public class Professor extends SugarRecord {
    private int    ra;
    private String nome;
    private String cpf;
    private String dtNascimento;
    private String dtMatricula;
    private String periodo;
    private String curso;

    public Professor(){

    }

    public Professor(int ra, String nome, String cpf, String dtNascimento, String dtMatricula,  String periodo, String curso) {
        this.ra = ra;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNascimento = dtNascimento;
        this.dtMatricula = dtMatricula;
        this.periodo = periodo;
        this.curso = curso;
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

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getDtMatricula() {
        return dtMatricula;
    }

    public void setDtMatricula(String dtMatricula) {
        this.dtMatricula = dtMatricula;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return ra == professor.ra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ra);
    }
}
