package ru.nsu.kamkina.model;

import java.io.*;
import java.util.Scanner;

public class SudokuModel {

    String[][] puzzle = new String[9][9];

    private int timeMin = 0;
    private int timeSec = 0;

    public void fillPuzzle(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    puzzle[i][j] = sc.next();
                }
            }
        }
        sc.close();
    }

    public boolean[] validate(String[] M) {
        boolean[] check = {true, true, true, true, true, true, true, true, true};
        for (int i = 0; i < 9; i++)
            for (int j = i + 1; j < 9; j++) {
                if (M[i].equals("0")) {
                    break;
                } else if (M[i].equals(M[j])) {
                    check[i] = false;
                    check[j] = false;
                }
            }
        return check;
    }

    public void setTime(int value) {
        timeMin = value;
        timeSec = value;
    }

    public void incTime() {
        timeSec++;
        if (timeSec % 60 == 0) {
            timeMin++;
            timeSec = 0;
        }
    }

    public String[][] getPuzzle() {
        return puzzle;
    }

    public int getTimeMin() {
        return timeMin;
    }

    public int getTimeSec() {
        return timeSec;
    }
}
