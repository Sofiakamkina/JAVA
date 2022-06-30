package ru.nsu.kamkina;

import ru.nsu.kamkina.controller.SudokuController;
import ru.nsu.kamkina.model.SudokuModel;
import ru.nsu.kamkina.view.SudokuView;

public class App {

    public static void main(String[] args) {
        SudokuModel model = new SudokuModel();
        SudokuView view = new SudokuView();
        new SudokuController(view, model);
    }
}
