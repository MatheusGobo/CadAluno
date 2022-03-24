package com.example.cadastroalunos.dao;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.cadastroalunos.model.Frequencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FrequenciaDAO {
    public static long salvar(Frequencia frequencia){
        try{
            return frequencia.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao lançar a frequência: "+ex.getMessage());
            return -1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Optional<Frequencia> getTurma(int id){
        try{
            Frequencia frequencia = Frequencia.findById(Frequencia.class,id);
            Optional<Frequencia> opt = Optional.ofNullable(frequencia);
            return opt;
        }catch (Exception ex){
            Log.e("Erro", "Erro ao lançar a frequência: "+ex.getMessage());
            return null;
        }
    }

    public static List<Frequencia> retornaFrequencia(String where, String[] whereArgs, String orderBy){
        List<Frequencia> list = new ArrayList<>();
        try {
            list = Frequencia.find(Frequencia.class,where, whereArgs,"",orderBy,"");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao lançar a frequência: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Frequencia frequencia){
        try {
            frequencia.delete();
            return true;
        }catch (Exception ex){
            Log.e("Erro","Erro ao lançar a frequência: "+ex.getMessage());
            return false;
        }
    }
/*
    public static Turma retornaPorID(Long id) {
        try {
            List<Turma> lista = Turma.find(Turma.class, "id = ?", new String[]{String.valueOf(id)}, "", "", "");
            return lista.get(0);
        } catch (Exception ex) {
            Log.e("Erro", "Erro ao retornar a turma ("+id+"): " + ex.getMessage());
            return null;
        }
    }*/
}
