package com.example.notesapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.model.Todo;
import com.example.notesapp.model.Priority;
import com.example.notesapp.utility.Utility;
import com.example.notesapp.viewmodel.ToDoViewModel;
import com.example.notesapp.views.ui.ToDo.InsertTodo;
import com.pedromassango.doubleclick.DoubleClick;
import com.pedromassango.doubleclick.DoubleClickListener;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    private List<Todo> TodoList = new ArrayList<>();
    private final Context context;

    @SuppressLint("NotifyDataSetChanged")
    public void setTodo(List<Todo> Todo) {
        this.TodoList = Todo;
        notifyDataSetChanged();
    }

    ToDoViewModel todoViewModel;

    public ToDoAdapter(Context context) {
        this.context = context;
    }

    public void setTodoViewModel(ToDoViewModel todoViewModel) {
        this.todoViewModel = todoViewModel;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_recycler_block, parent, false);
        return new ToDoViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {

        Todo todo = TodoList.get(position);
        holder.TodoHeadingTV.setText(todo.getTodoHeading());
        holder.TodoBodyTV.setText(todo.getTodoBody());

        //setting priority to Todo
        PriorityColorSetter(TodoList.get(position).getTodoPriority(), holder);

        holder.block.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Utility.getTodoAleartBuilder(todo, context, todoViewModel);
                return true;
            }
        });

        holder.block.setOnClickListener(new DoubleClick(new DoubleClickListener() {
            @Override
            public void onSingleClick(View view) {
                if (holder.TodoBodyTV.getVisibility() == View.VISIBLE) {
                    holder.TodoBodyTV.setVisibility(View.GONE);
                } else {
                    holder.TodoBodyTV.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onDoubleClick(View view) {
                Intent intent = new Intent(context, InsertTodo.class);
                intent.putExtra("TodoID", todo.getId());
                intent.putExtra("heading", todo.getTodoHeading());
                intent.putExtra("subHeading", todo.getTodoBody());
                intent.putExtra("priority", todo.getTodoPriority().toString());
                intent.putExtra("intentType", "Update");

                context.startActivity(intent);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return TodoList.size();
    }

    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        public final TextView TodoHeadingTV, TodoBodyTV;
        public final View TodoPriorityV;
        public final ConstraintLayout block;

        public ToDoViewHolder(View view) {
            super(view);
            TodoHeadingTV = view.findViewById(R.id.TodoHeading);
            TodoBodyTV = view.findViewById(R.id.TodoBody);
            TodoPriorityV = view.findViewById(R.id.priorityView);
            block = view.findViewById(R.id.Todo_recycler_block);
        }
    }


    @SuppressLint("ResourceAsColor")
    public void PriorityColorSetter(Priority priority, ToDoViewHolder holder) {
        if (priority == Priority.MEDIUM) {
            holder.TodoPriorityV.setBackgroundColor(Color.parseColor("#EFB700"));
        } else if (priority == Priority.HIGH) {
            holder.TodoPriorityV.setBackgroundColor(Color.parseColor("#B81D13"));
        } else {
            holder.TodoPriorityV.setBackgroundColor(Color.parseColor("#008450"));
        }
    }
}
