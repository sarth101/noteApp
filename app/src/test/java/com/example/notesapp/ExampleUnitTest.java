package com.example.notesapp;

import org.junit.Test;

import static org.junit.Assert.*;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.example.notesapp.model.Note;
import com.example.notesapp.viewmodel.NotesViewModel;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}