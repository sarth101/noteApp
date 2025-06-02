package com.example.notesapp.views.ui.note;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.adapter.CategoryAdapter;
import com.example.notesapp.adapter.NotesAdapter;
import com.example.notesapp.databinding.FragmentNoteBinding;
import com.example.notesapp.listner.onCategoryClickListner;
import com.example.notesapp.model.Note;
import com.example.notesapp.viewmodel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotesFragment extends Fragment {
    private RecyclerView notesRecyclerView;
    private RecyclerView categoryListView;
    private NotesAdapter notesAdapter;
    private ImageView nothingToSee;
    private CategoryAdapter categoryAdapter;

    private FragmentNoteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NoteViewModel noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        binding = FragmentNoteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        notesRecyclerView = root.findViewById(R.id.noteList);
        categoryListView = root.findViewById(R.id.categoryList);

        notesAdapter = new NotesAdapter(root.getContext());
        notesAdapter.setNoteViewModel(noteViewModel);


        notesRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        notesRecyclerView.setAdapter(notesAdapter);

        nothingToSee = root.findViewById(R.id.notFoundImage);

        categoryAdapter = new CategoryAdapter(new onCategoryClickListner() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onCategoryClick(String category) {

                if (category.equals("All")) {
                    noteViewModel.getAllNote().observe(getViewLifecycleOwner(), notesAdapter::setNoteList);

                } else {
                    noteViewModel.getNoteByCategory(category).observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
                        @Override
                        public void onChanged(List<Note> notes) {
                            notesAdapter.setNoteList(notes);
                            notesAdapter.notifyDataSetChanged();
                        }
                    });
                }

            }
        });

        noteViewModel.getCategories().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> categories) {
                categoryAdapter.setCategoryList(categories);
                categoryAdapter.notifyDataSetChanged();
            }
        });

        categoryListView.setLayoutManager(new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryListView.setAdapter(categoryAdapter);


        noteViewModel.getAllNote().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesAdapter.setNoteList(notes);
                if (notes.isEmpty()){
                    nothingToSee.setVisibility(View.VISIBLE);
                }else {
                    nothingToSee.setVisibility(View.INVISIBLE);
                }
            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}