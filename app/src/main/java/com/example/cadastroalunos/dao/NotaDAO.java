package com.example.cadastroalunos.dao;

import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.example.cadastroalunos.model.Nota;
import com.example.cadastroalunos.model.Turma;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotaDAO {
    public static long salvar(Nota nota){
        try{
            return nota.save();
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar a nota: "+ex.getMessage());
            return -1;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Optional<Nota> getNota(int id){
        try{
            Nota nota = Nota.findById(Nota.class, id);
            Optional<Nota> opt = Optional.ofNullable(nota);
            return opt;
        }catch (Exception ex){
            Log.e("Erro", "Erro ao salvar a Nota: "+ex.getMessage());
            return null;
        }
    }

    public static List<Nota> retornaNota(String where, String[] whereArgs, String orderBy){
        List<Nota> list = new ArrayList<>();
        try {
            list = Nota.find(Nota.class,where,whereArgs,"",orderBy,"");
        }catch (Exception ex){
            Log.e("Erro", "Erro ao buscar as notas: "+ex.getMessage());
        }
        return list;
    }

    public static boolean delete(Nota nota){
        try {
            nota.delete();
            return true;
        }catch (Exception ex){
            Log.e("Erro","Erro ao excluir a nota: "+ex.getMessage());
            return false;
        }
    }

    public static Float retornaMedia(Long idAluno, String idTurma) {
        List<Nota> list = new ArrayList<>();
        Float media = Float.valueOf(0);
        int soma = 0;

        try {
            list = retornaNota("id_aluno = ? AND id_turma = ?", new String[]{String.valueOf(idAluno),
                                                                                   String.valueOf(idTurma)}, "");
            Turma turma = TurmaDAO.retornaPorID(Long.parseLong(idTurma));
            if (turma.getRegime().toUpperCase().equals("ANUAL")) {
                if (list.size() >= 4) {
                    for (Nota not : list) {
                        soma = soma + not.getnota();
                    }
                    media = Float.valueOf(soma / 4);
                } else
                    media = Float.valueOf(0);
            }else {
                if (list.size() >= 2) {
                    for (Nota not : list) {
                        soma = soma + not.getnota();
                    }
                    media = Float.valueOf(soma / 2);

                } else
                    media = Float.valueOf(0);
            }

        }catch (Exception ex){
            Log.e("Erro", "Erro ao buscar as notas: "+ex.getMessage());
        }


        return media;
    }
}