package com.example.notesapp.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.notesapp.R;
import com.example.notesapp.databinding.ActivityHomePageBinding;
import com.example.notesapp.views.ui.ToDo.InsertTodo;
import com.example.notesapp.views.ui.ToDo.ToDoFragment;
import com.example.notesapp.views.ui.note.NotesFragment;
import com.example.notesapp.views.ui.note.notes_layout;

import java.util.Objects;

public class HomePage extends AppCompatActivity {

    private ActivityHomePageBinding binding;
    private TextView todoFragment, noteFragment;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        }

        todoFragment = findViewById(R.id.nav_todo);
        noteFragment = findViewById(R.id.nav_note);

        loadFragment(new ToDoFragment(), todoFragment);

        todoFragment.setOnClickListener(v -> loadFragment(new ToDoFragment(), todoFragment));
        noteFragment.setOnClickListener(v -> loadFragment(new NotesFragment(), noteFragment));
    }

    private void loadFragment(Fragment fragment, TextView textView) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        invalidateOptionsMenu(); // Refresh toolbar

        if (fragment instanceof NotesFragment) {
            setActionBarTitleBold("Note");
        } else if (fragment instanceof ToDoFragment) {
            setActionBarTitleBold("Todo");
        }

        selectedFragment(textView);
    }

    private void selectedFragment(TextView textView) {
        noteFragment.setTextColor(Color.parseColor("#A5A6A7"));
        todoFragment.setTextColor(Color.parseColor("#A5A6A7"));

        textView.setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void setActionBarTitleBold(String title) {
        SpannableString s = new SpannableString(title);
        s.setSpan(new StyleSpan(Typeface.BOLD), 0, s.length(), 0);
        actionBar.setTitle(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (id == R.id.insert) {
            if (fragment instanceof ToDoFragment) {
                Intent intent = new Intent(this, InsertTodo.class);
                intent.putExtra("intentType", "Insert");
                startActivity(intent);

            } else if (fragment instanceof NotesFragment) {
                Intent intent = new Intent(this, notes_layout.class);
                intent.putExtra("intentType", "Insert");
                startActivity(intent);

            } else {
                Toast.makeText(this, "Insert not supported here", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
