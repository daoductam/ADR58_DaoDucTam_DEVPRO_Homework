package com.tamdao.adr58_daoductam_day8;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private NoteDatabase database;
    private NoteAdapter adapter;
    private RecyclerView rvNotes;
    private Button btnAddNote;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvNotes = findViewById(R.id.rvNotes);
        btnAddNote = findViewById(R.id.btnAddNote);
        etSearch = findViewById(R.id.etSearch);

        setupRecyclerView();
        database = NoteDatabase.getDatabase(this);

        // Read (All)
        database.noteDao().getAllNotes().observe(this, notes -> {
            if (notes != null && !notes.isEmpty()) {
                adapter.setNotes(notes);
            } else {
                insertInitialData();
            }
        });

        // Create
        btnAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });

        // Search
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = "%" + s.toString() + "%";
                database.noteDao().searchNotes(query).observe(MainActivity.this, notes -> {
                    adapter.setNotes(notes);
                });
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupRecyclerView() {
        adapter = new NoteAdapter();
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(adapter);

        adapter.setOnNoteClickListener(new NoteAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                // Update
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                intent.putExtra("NOTE_ID", note.getId());
                intent.putExtra("NOTE_TITLE", note.getTitle());
                intent.putExtra("NOTE_TYPE", note.getType());
                startActivity(intent);
            }

            @Override
            public void onNoteLongClick(Note note) {
                // Delete
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete Note")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            NoteDatabase.databaseWriteExecutor.execute(() -> {
                                database.noteDao().delete(note);
                            });
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    private void insertInitialData() {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            database.noteDao().insert(new Note("UX/UI Design Tutorial", "30 jun, 2026", "00:04:59", "youtube", true));
            database.noteDao().insert(new Note("Digital Marketing Journey", "12 jul, 2026", "00:01:52", "voice", false));
            database.noteDao().insert(new Note("Progress in the profession", "12 jul, 2026", "00:05:12", "folder", false));
        });
    }
}