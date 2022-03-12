package com.example.cadastroalunos.dao;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.cadastroalunos.model.Turma;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TurmaDAO {
    public static long salvar(Turma turma){
        try{
            return turma.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar a turma: "+ex.getMessage());
            return -1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Optional<Turma> getTurma(int id){
        try{
            Turma turma = Turma.findById(Turma.class,id);
            Optional<Turma> opt = Optional.ofNullable(turma);
            return opt;
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar a turma: "+ex.getMessage());
            return null;
        }
    }

    public static List<Turma> retornaTurma(String where, String[] whereArgs, String orderBy){
        List<Turma> list = new ArrayList<>();
        try {
            list = Turma.find(Turma.class,where,whereArgs,"",orderBy,"");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao buscar as turmas: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Turma turma){
        try {
            turma.delete();
            return true;
        }catch (Exception ex){
            Log.e("Erro","Erro ao excluir a turma: "+ex.getMessage());
            return false;
        }
    }
}
