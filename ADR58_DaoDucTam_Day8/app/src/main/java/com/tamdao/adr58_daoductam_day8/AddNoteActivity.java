package com.tamdao.adr58_daoductam_day8;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etNoteTitle;
    private RadioGroup rgType;
    private Button btnSave;
    private TextView tvAddHeader;
    private NoteDatabase database;
    private int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etNoteTitle = findViewById(R.id.etNoteTitle);
        rgType = findViewById(R.id.rgType);
        btnSave = findViewById(R.id.btnSave);
        tvAddHeader = findViewById(R.id.tvAddHeader);
        database = NoteDatabase.getDatabase(this);

        // Check if we are editing an existing note
        if (getIntent().hasExtra("NOTE_ID")) {
            noteId = getIntent().getIntExtra("NOTE_ID", -1);
            String title = getIntent().getStringExtra("NOTE_TITLE");
            String type = getIntent().getStringExtra("NOTE_TYPE");

            tvAddHeader.setText("Edit Note");
            btnSave.setText("Update Note");
            etNoteTitle.setText(title);

            if ("voice".equals(type)) {
                ((RadioButton) findViewById(R.id.rbVoice)).setChecked(true);
            } else if ("folder".equals(type)) {
                ((RadioButton) findViewById(R.id.rbFolder)).setChecked(true);
            } else {
                ((RadioButton) findViewById(R.id.rbYoutube)).setChecked(true);
            }
        }

        btnSave.setOnClickListener(v -> {
            saveNote();
        });
    }

    private void saveNote() {
        String title = etNoteTitle.getText().toString().trim();
        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
            return;
        }

        String type = "youtube";
        int checkedId = rgType.getCheckedRadioButtonId();
        if (checkedId == R.id.rbVoice) {
            type = "voice";
        } else if (checkedId == R.id.rbFolder) {
            type = "folder";
        }

        String currentDate = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(new Date());
        String duration = "00:00:00"; 

        if (noteId == -1) {
            // CREATE
            Note note = new Note(title, currentDate, duration, type, true);
            NoteDatabase.databaseWriteExecutor.execute(() -> {
                database.noteDao().insert(note);
                finish();
            });
        } else {
            // UPDATE
            Note updatedNote = new Note(title, currentDate, duration, type, false);
            updatedNote.setId(noteId);
            NoteDatabase.databaseWriteExecutor.execute(() -> {
                database.noteDao().update(updatedNote);
                finish();
            });
        }
    }
}