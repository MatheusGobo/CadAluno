package com.example.cadastroalunos.dao;

import android.util.Log;
import com.example.cadastroalunos.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public static long salvar(Aluno aluno) {
        try {
            return aluno.save();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao salvar o aluno: " + ex.getMessage());
            return -1;
        }
    }

    public static Aluno getAluno(int id) {
        try {
            return Aluno.findById(Aluno.class, id);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar o aluno: " + ex.getMessage());
            return null;
        }
    }

    public static List<Aluno> retornaAlunos(String where, String[] whereargs, String orderBy) {
        List<Aluno> list = new ArrayList<>();

        try {
            list = Aluno.find(Aluno.class, where, whereargs, "", orderBy, "");
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar lista de alunos: " + ex.getMessage());
        }

        return list;
    }

    public static boolean delete (Aluno aluno) {
        try {
            return Aluno.delete(aluno);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao apagar aluno: " + ex.getMessage());
            return false;
        }
    }

    public static Aluno retornaPorRA(int ra) {
        try {
            List<Aluno> lista = Aluno.find(Aluno.class, "ra = ?", new String[]{String.valueOf(ra)}, "", "", "");
            return lista.get(0);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar professor com RA ("+ra+"): " + ex.getMessage());
            return null;
        }
    }

    public static int retornaQtTurma(int idTurma){
        try {
            List<Aluno> lista = Aluno.find(Aluno.class, "id_turma = ?", new String[]{String.valueOf(idTurma)}, "", "", "");
            return lista.size();
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar professor com RA ("+idTurma+"): " + ex.getMessage());
            return 0;
        }
    }


}
