package com.example.cadastroalunos.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.FrequenciaDAO;
import com.example.cadastroalunos.dao.NotaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Frequencia;
import com.example.cadastroalunos.model.Turma;
import com.google.android.material.textfield.TextInputEditText;
import org.w3c.dom.Text;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder> {

    private List<Aluno> listaAlunos;
    private Context context;

    public AlunoAdapter(Context context, List<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
        this.context = context;
    }

    public class AlunoViewHolder extends RecyclerView.ViewHolder {

        TextInputEditText edRaAluno;
        TextInputEditText edNomeAluno;
        TextInputEditText edCpfAluno;
        TextInputEditText edTurma;
        TextInputEditText edDtMatricula;
        TextInputEditText edDtNascimento;
        TextInputEditText edAprovacao;

        public AlunoViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaAluno = (TextInputEditText) itemView.findViewById(R.id.edRaAluno);
            edNomeAluno = (TextInputEditText) itemView.findViewById(R.id.edNomeAluno);
            edCpfAluno = (TextInputEditText) itemView.findViewById(R.id.edCpfAluno);
            edTurma = (TextInputEditText) itemView.findViewById(R.id.edTurma);
            edDtMatricula = (TextInputEditText) itemView.findViewById(R.id.edDtMatricula);
            edDtNascimento = (TextInputEditText) itemView.findViewById(R.id.edDtNasc);
            edAprovacao = (TextInputEditText) itemView.findViewById(R.id.edAprovacao);
        }
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_aluno, parent, false);

        AlunoAdapter.AlunoViewHolder viewHolder = new AlunoViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        Aluno aluno = listaAlunos.get(position);

        holder.edRaAluno.setText(String.valueOf(aluno.getRa()));
        holder.edCpfAluno.setText(aluno.getCpf());
        holder.edNomeAluno.setText(aluno.getNome());

        Turma turma = TurmaDAO.retornaPorID(Long.parseLong(aluno.getIdTurma()));
        holder.edTurma.setText(turma.getNome());

        holder.edDtMatricula.setText(aluno.getDtMatricula());
        holder.edDtNascimento.setText(aluno.getDtNasc());

        Float mediaAluno = NotaDAO.retornaMedia(aluno.getId(), aluno.getIdTurma());
        int pcfrequencia = 0;
        try {
            List<Frequencia> freq = FrequenciaDAO.retornaFrequencia("id_aluno = ? and id_turma = ?", new String[]{String.valueOf(aluno.getId()),
                    aluno.getIdTurma()}, "");
            if (freq.size() > 0)
              pcfrequencia = freq.get(0).getPcFrequencia();
        } catch (Exception e) {
            Log.e("AlunoAdapter", "Erro ao buscar frequência do aluno, Erro = " + e.getMessage());
        }
        if (mediaAluno >= 70) {
            if (pcfrequencia > 70) {
                holder.edAprovacao.setText("Aluno aprovado Média ( " + mediaAluno + "), Percentual de Frequência( " + pcfrequencia + "%)");
                holder.edAprovacao.setTextColor(Color.GREEN);
            } else {
                holder.edAprovacao.setText("Aluno Reprovado por frequência, Frequência ( " + pcfrequencia + "%)");
                holder.edAprovacao.setTextColor(Color.RED);
            }
        } else {
            holder.edAprovacao.setText("Aluno Reprovado por Nota, Média ( " + mediaAluno + ")");
            holder.edAprovacao.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return listaAlunos.size();
    }
}
