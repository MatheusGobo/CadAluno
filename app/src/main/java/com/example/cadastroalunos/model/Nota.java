package com.example.cadastroalunos.model;

import com.orm.SugarRecord;

public class Nota extends SugarRecord {
    private Long   idAluno;
    private Long   idTurma;
    private int    nota;

    public Nota() {
    }

    public Nota(Long idAluno, Long idTurma, int nota) {
        this.idAluno = idAluno;
        this.idTurma = idTurma;
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nota frequencia = (Nota) o;
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

    public int getnota() {
        return nota;
    }

    public void setnota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return nota + "%";
    }
}
