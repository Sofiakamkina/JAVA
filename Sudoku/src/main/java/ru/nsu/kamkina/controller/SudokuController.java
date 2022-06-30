package ru.nsu.kamkina.controller;

import ru.nsu.kamkina.model.SudokuModel;
import ru.nsu.kamkina.view.SudokuView;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;

public class SudokuController {

    private final SudokuView view;

    private final SudokuModel model;

    private final Timer timeCount;

    public SudokuController(SudokuView theView, SudokuModel theModel) {
        this.view = theView;
        this.model = theModel;

        timeCount = new Timer(1000, new actionTimer());

        this.view.addMenuEasyButtonListener(new EasyListener());
        this.view.addMenuNormalButtonListener(new NormalListener());
        this.view.addMenuHardButtonListener(new HardListener());

        this.view.addGetBackToMenuListener(new BackListener());

        this.view.addPuzzleButtonListener(new PuzzleButton());

        this.view.addCheckButtonListener(new CheckListener());
        this.view.addEndGameButtonListener(new EndGameListener());
        this.view.checkingCellOverWriting(new WriteChecking());
    }

    class EasyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.remove(view.getGameMenu());
            view.add(view.getChoiceEasyMenu(), BorderLayout.CENTER);
            view.revalidate();
            view.repaint();
        }
    }

    class NormalListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.remove(view.getGameMenu());
            view.add(view.getChoiceNormalMenu(), BorderLayout.CENTER);
            view.revalidate();
            view.repaint();
        }
    }

    class HardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.remove(view.getGameMenu());
            view.add(view.getChoiceHardMenu(), BorderLayout.CENTER);
            view.revalidate();
            view.repaint();
        }
    }

    class PuzzleButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                for (int i = 0; i < 3; i++) {
                    if (e.getSource() == view.getEasyPuzzles(i)) {
                        view.remove(view.getChoiceEasyMenu());
                        view.setEmptyEverything();
                        setFromFileToCells("easy/easy" + (i + 1) + ".txt");
                    } else if (e.getSource() == view.getNormalPuzzles(i)) {
                        view.remove(view.getChoiceNormalMenu());
                        view.setEmptyEverything();
                        setFromFileToCells("normal/normal" + (i + 1) + ".txt");
                    } else if (e.getSource() == view.getHardPuzzles(i)) {
                        view.remove(view.getChoiceHardMenu());
                        view.setEmptyEverything();
                        setFromFileToCells("hard/hard" + (i + 1) + ".txt");
                    }
                }
                view.add(view.getGameField(), BorderLayout.WEST);
                view.add(view.getChoice(), BorderLayout.EAST);
                timeStart();
                view.setVisible(true);
                view.revalidate();
                view.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (Objects.equals(e.getActionCommand(), "Quit easy")) {
                view.remove(view.getChoiceEasyMenu());
            } else if (Objects.equals(e.getActionCommand(), "Quit normal")) {
                view.remove(view.getChoiceNormalMenu());
            } else if (Objects.equals(e.getActionCommand(), "Quit hard")) {
                view.remove(view.getChoiceHardMenu());
            }
            view.setMainMenu();
        }
    }

    class CheckListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean endOfGame = true;
            boolean[] checkRow, checkCol, checkSquare;

            for (int i = 0; i < view.getGridSize(); i++) {
                String[] row = new String[9];
                String[] col = new String[9];
                String[] square = new String[9];

                for (int j = 0; j < view.getGridSize(); j++) {
                    row[j] = view.getTextField(i, j).getText();
                    col[j] = view.getTextField(j, i).getText();
                    square[j] = view.getTextField((i / 3) * 3 + j / 3, i * 3 % 9 + j % 3).getText();
                }

                checkRow = model.validate(row);
                checkCol = model.validate(col);
                checkSquare = model.validate(square);

                for (int j = 0; j < view.getGridSize(); j++) {
                    if (!checkRow[j]) {
                        view.getTextField(i, j).setBackground(view.getOpenCellTextNo());
                        endOfGame = false;
                    }

                    if (!checkCol[j]) {
                        view.getTextField(j, i).setBackground(view.getOpenCellTextNo());
                        endOfGame = false;
                    }

                    if (!checkSquare[j]) {
                        view.getTextField((i / 3) * 3 + j / 3, i * 3 % 9 + j % 3).setBackground(view.getOpenCellTextNo());
                        endOfGame = false;
                    }

                    if (!(view.getTextField(i, j).isVisible())) {
                        view.getTextField(i, j).setBackground(view.getClosedCellBgcolor());
                    }

                    if ((endOfGame) && (j == 8) && (i == 8)) {
                        view.colorEverything();
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "The game is over! You ended the puzzle in " + model.getTimeMin() +
                                " minutes " + model.getTimeSec() + " seconds");
                        timeStop();
                        view.remove(view.getGameField());
                        view.remove(view.getChoice());
                        view.setMainMenu();
                    }
                }
            }
        }
    }

    class EndGameListener implements ActionListener {
        public void actionPerformed(ActionEvent arg) {
            if (JOptionPane.showConfirmDialog(view, "Are you sure?") == 0) {
                timeStop();
                view.remove(view.getGameField());
                view.remove(view.getChoice());
                view.setMainMenu();
            }
        }
    }

    public class WriteChecking extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            Object key = e.getSource();
            int keyCode = e.getKeyCode();

            for (int i = 0; i < view.getGridSize(); i++) {
                for (int j = 0; j < view.getGridSize(); j++) {
                    if (key == view.getTextField(i, j)) {
                        if ((view.getTextField(i, j).getText().length() < 1) &&
                                ((keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_9) ||
                                        (keyCode >= KeyEvent.VK_NUMPAD1 && keyCode <= KeyEvent.VK_NUMPAD9) ||
                                        (keyCode == KeyEvent.VK_UP) || (keyCode == KeyEvent.VK_DOWN) ||
                                        (keyCode == KeyEvent.VK_RIGHT) || (keyCode == KeyEvent.VK_LEFT) ||
                                        (keyCode == KeyEvent.VK_TAB) || (keyCode == KeyEvent.VK_BACK_SPACE))) {
                            view.getTextField(i, j).setEditable(true);
                            view.getTextField(i, j).setBackground(view.getOpenCellBgcolor());
                        } else {
                            view.getTextField(i, j).setEditable(true);
                            view.getTextField(i, j).setBackground(view.getOpenCellTextNo());
                        }
                    }
                }
            }
        }
    }

    public void setFromFileToCells(String fileName) throws IOException {
        model.fillPuzzle(fileName);

        for (int i = 0; i < view.getGridSize(); i++) {
            for (int j = 0; j < view.getGridSize(); j++) {
                if (model.getPuzzle()[i][j].equals("0")) {
                    view.getTextField(i, j).setBackground(view.getOpenCellBgcolor());
                } else {
                    view.getTextField(i, j).setText(model.getPuzzle()[i][j]);
                    view.getTextField(i, j).setBackground(view.getClosedCellBgcolor());
                    view.getTextField(i, j).setEnabled(false);
                }
            }
        }
    }

    public class actionTimer implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.incTime();
            view.setTimeLabel(model.getTimeMin(), model.getTimeSec());
            if (model.getTimeSec() % 5 == 0) {
                view.clearCheck();
            }
        }
    }

    public void timeStart() {
        model.setTime(0);
        timeCount.start();
    }

    public void timeStop() {
        timeCount.stop();
        view.setTimeLabel(0, 0);
    }
}