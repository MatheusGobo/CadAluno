package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Turma;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class TurmaAdapter extends RecyclerView.Adapter<TurmaAdapter.TurmaViewHolder> {
    private List<Turma> listaTurmas;
    private Context context;

    public TurmaAdapter(List<Turma> listaTurmas, Context context) {
        this.listaTurmas = listaTurmas;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return listaTurmas.size();
    }

    @Override
    public TurmaAdapter.TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_turma, parent, false);
        TurmaAdapter.TurmaViewHolder viewHolder = new TurmaAdapter.TurmaViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TurmaAdapter.TurmaViewHolder holder, int position) {
        Turma turma = listaTurmas.get(position);
        holder.edNomeTurma       .setText(String.valueOf(turma.getNome()));
        holder.edPeriodoTurma    .setText(turma.getPeriodo());
        holder.edDisciplinaTurma .setText(turma.getDisciplina());
        holder.edQtAlunosTurma   .setText(turma.getQtAlunos());
    }


    public class TurmaViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edNomeTurma;
        TextInputEditText edDisciplinaTurma;
        TextInputEditText edPeriodoTurma;
        TextInputEditText edQtAlunosTurma;
        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);

            edNomeTurma        = (TextInputEditText)itemView.findViewById(R.id.edNomeTurma);
            edDisciplinaTurma  = (TextInputEditText)itemView.findViewById(R.id.edDisciplinaTurma);
            edPeriodoTurma     = (TextInputEditText)itemView.findViewById(R.id.edPeriodoTurma);
            edQtAlunosTurma    = (TextInputEditText)itemView.findViewById(R.id.edQtAlunos);
        }
    }
}
