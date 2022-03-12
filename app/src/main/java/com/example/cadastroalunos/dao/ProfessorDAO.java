package com.example.cadastroalunos.dao;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.cadastroalunos.model.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorDAO {
    public static long salvar(Professor professor){
        try{
            return professor.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o Professor: "+ex.getMessage());
            return -1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Optional<Professor> getProfessor(int id){
        try{
            Professor professor = Professor.findById(Professor.class,id);
            Optional<Professor> opt = Optional.ofNullable(professor);
            return opt;
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o professor: "+ex.getMessage());
            return null;
        }
    }

    public static List<Professor> retornaProfessor(String where, String[] whereArgs, String orderBy){
        List<Professor> list = new ArrayList<>();
        try {
            list = Professor.find(Professor.class,where,whereArgs,"",orderBy,"");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao buscar os professores: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Professor professor){
        try {
            professor.delete();
            return true;
        }catch (Exception ex){
            Log.e("Erro","Erro ao excluir o professor: " + ex.getMessage());
            return false;
        }
    }

    public static Professor retornaPorRA(int ra) {
        try {
            List<Professor> lista = Professor.find(Professor.class, "ra = ?", new String[]{String.valueOf(ra)}, "", "", "");
            return lista.get(0);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar professor com RA ("+ra+"): " + ex.getMessage());
            return null;
        }
    }
}
