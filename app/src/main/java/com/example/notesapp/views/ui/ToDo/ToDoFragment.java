package com.example.notesapp.views.ui.ToDo;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.notesapp.R;
import com.example.notesapp.adapter.ToDoAdapter;
import com.example.notesapp.databinding.FragmentTodoBinding;
import com.example.notesapp.model.Todo;
import com.example.notesapp.viewmodel.ToDoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ToDoFragment extends Fragment {

    private FragmentTodoBinding binding;
    private RecyclerView todoRecyclerView;
    private ToDoAdapter toDoAdapter;
    private ToDoViewModel toDoViewModel;
    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ToDoViewModel todoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        binding = FragmentTodoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //view model
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
//        toDoViewModel.getAllTodos().observe(getViewLifecycleOwner(), ToDo -> toDoAdapter.setTodo(ToDo));

        //recycler view
        todoRecyclerView =  root.findViewById(R.id.TodosRecyclerView);
        toDoAdapter = new ToDoAdapter(root.getContext());
        todoRecyclerView.setAdapter(toDoAdapter);
        toDoAdapter.setTodoViewModel(todoViewModel);
        todoRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        imageView = root.findViewById(R.id.notFoundImage);
        toDoViewModel.getAllTodos().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                if (todos.isEmpty()){
                    imageView.setVisibility(View.VISIBLE);
                }else{
                    imageView.setVisibility(View.GONE);
                }
                toDoAdapter.setTodo(todos);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        toDoAdapter = null;
        todoRecyclerView = null;
    }
}