package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

public class Frequencia extends SugarRecord {
    private Long   idAluno;
    private Long   idTurma;
    private int    pcFrequencia;

    public Frequencia() {
    }

    public Frequencia(Long idAluno, Long idTurma, int pcFrequencia) {
        this.idAluno = idAluno;
        this.idTurma = idTurma;
        this.pcFrequencia = pcFrequencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequencia frequencia = (Frequencia) o;
        return ((idAluno == frequencia.idAluno) && (idTurma == frequencia.idTurma));
    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public int getPcFrequencia() {
        return pcFrequencia;
    }

    public void setPcFrequencia(int pcFrequencia) {
        this.pcFrequencia = pcFrequencia;
    }

    /*    @Override
        public int hashCode() {
            return Objects.hash(nome);
        }
    */
    @Override
    public String toString() {
        return pcFrequencia + "%";
    }
}
