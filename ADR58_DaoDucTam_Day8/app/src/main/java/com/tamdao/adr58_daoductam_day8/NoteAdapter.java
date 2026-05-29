package com.tamdao.adr58_daoductam_day8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnNoteClickListener clickListener;

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
        void onNoteLongClick(Note note);
    }

    public void setOnNoteClickListener(OnNoteClickListener listener) {
        this.clickListener = listener;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note, clickListener);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivType;
        private final TextView tvTitle;
        private final TextView tvDateTime;
        private final TextView tvNewBadge;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ivType = itemView.findViewById(R.id.ivType);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvNewBadge = itemView.findViewById(R.id.tvNewBadge);
        }

        public void bind(Note note, OnNoteClickListener listener) {
            tvTitle.setText(note.getTitle());
            tvDateTime.setText(note.getDate() + "  •  " + note.getDuration());
            tvNewBadge.setVisibility(note.isNew() ? View.VISIBLE : View.GONE);

            if ("youtube".equals(note.getType())) {
                ivType.setImageResource(R.drawable.ic_youtube);
            } else if ("voice".equals(note.getType())) {
                ivType.setImageResource(R.drawable.ic_voice);
            } else if ("folder".equals(note.getType())) {
                ivType.setImageResource(R.drawable.ic_folder);
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onNoteClick(note);
            });

            itemView.setOnLongClickListener(v -> {
                if (listener != null) listener.onNoteLongClick(note);
                return true;
            });
        }
    }
}