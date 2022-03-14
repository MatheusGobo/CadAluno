package com.example.cadastroalunos.dao;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;

public class DisciplinaDAO {
    public static long salvar(Disciplina disciplina){
        try{
            return disciplina.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o disciplina: "+ex.getMessage());
            return -1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Optional<Disciplina> getDisciplina(int id){
        try{
            Disciplina disciplina = Disciplina.findById(Disciplina.class, id);
            Optional<Disciplina> opt = Optional.ofNullable(disciplina);
            return opt;
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar o disciplina: "+ex.getMessage());
            return null;
        }
    }

    public static List<Disciplina> retornaDisciplina(String where, String[] whereArgs, String orderBy){
        List<Disciplina> list = new ArrayList<>();
        try {
            list = Disciplina.find(Disciplina.class,where,whereArgs,"",orderBy,"");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao buscar os disciplinas: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Disciplina disciplina){
        try {
            disciplina.delete();
            return true;
        }catch (Exception ex){
            Log.e("Erro","Erro ao excluir a disciplina: "+ex.getMessage());
            return false;
        }
    }

    public static Disciplina retornaPorNome(String nome) {
        try {
            List<Disciplina> lista = Disciplina.find(Disciplina.class, "nome = ?", new String[]{String.valueOf(nome)}, "", "", "");
            return lista.get(0);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar Disciplina com o nome ("+nome+"): " + ex.getMessage());
            return null;
        }
    }
}