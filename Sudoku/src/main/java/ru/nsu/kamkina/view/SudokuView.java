package ru.nsu.kamkina.view;

import ru.nsu.kamkina.controller.SudokuController.WriteChecking;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class SudokuView extends JFrame {

    private final JPanel gameMenu = new JPanel();

    private final JLabel title = new JLabel("Sudoku");

    private final JButton menuEasy = new JButton("EASY");
    private final JButton menuNormal = new JButton("NORMAL");
    private final JButton menuHard = new JButton("HARD");

    private final JButton[] easyPuzzles = new JButton[3];
    private final JButton[] normalPuzzles = new JButton[3];
    private final JButton[] hardPuzzles = new JButton[3];

    private final JButton getFromEasy = new JButton("Quit easy");
    private final JButton getFromNormal = new JButton("Quit normal");
    private final JButton getFromHard = new JButton("Quit hard");

    private final JPanel choiceEasyMenu = new JPanel();
    private final JPanel choiceNormalMenu = new JPanel();
    private final JPanel choiceHardMenu = new JPanel();

    private final JPanel gameField = new JPanel();

    private final JPanel choice = new JPanel();

    private final JButton endGame = new JButton("END");

    private final JButton checkBut = new JButton("CHECK");

    private final JLabel time = new JLabel();

    private int row;

    private int col;

    private static final int GRID_SIZE = 9;

    private static final int SUBGRID_SIZE = 3;

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;

    private static final int CELL_SIZE = 60;

    private static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
    private static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;

    private static final Color OPEN_CELL_COLOR = Color.YELLOW;
    private static final Color CLOSED_CELL_COLOR = new Color(240, 240, 240);

    private static final Color OPEN_CELL_TEXT_YES = new Color(0, 255, 0);
    private static final Color OPEN_CELL_TEXT_NO = Color.RED;

    private static final Color BACKGROUND_MENU = new Color(255, 255, 204);

    private static final Color BACKGROUND_EASY = new Color(204, 255, 255);
    private static final Color BACKGROUND_NORMAL = new Color(204, 230, 255);
    private static final Color BACKGROUND_HARD = new Color(204, 204, 255);

    private static final Color CHOICE = new Color(204, 255, 204);

    private static final Color TIME = new Color(100, 204, 255);

    private static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

    private static final Font MENU = new Font("Arial", Font.BOLD, 40);

    private static final JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];

    Border jTextBorderLeftTop = BorderFactory.createMatteBorder(4, 4, 1, 1, Color.DARK_GRAY);
    Border jTextBorderLeft = BorderFactory.createMatteBorder(1, 4, 1, 1, Color.DARK_GRAY);
    Border jTextBorderLeftBottom = BorderFactory.createMatteBorder(1, 4, 4, 1, Color.DARK_GRAY);
    Border jTextBorderBottom = BorderFactory.createMatteBorder(1, 1, 4, 1, Color.DARK_GRAY);
    Border jTextBorderRightBottom = BorderFactory.createMatteBorder(1, 1, 4, 4, Color.DARK_GRAY);
    Border jTextBorderRight = BorderFactory.createMatteBorder(1, 1, 1, 4, Color.DARK_GRAY);
    Border jTextBorderRightTop = BorderFactory.createMatteBorder(4, 1, 1, 4, Color.DARK_GRAY);
    Border jTextBorderTop = BorderFactory.createMatteBorder(4, 1, 1, 1, Color.DARK_GRAY);
    Border jTextBorderCenter = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.DARK_GRAY);

    public SudokuView() {
        setMainMenu();
        setChoiceMenu();
        setGameFieldPanels();

        this.setTitle("Sudoku");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setVisible(true);
        this.setFocusable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void setMainMenu() {
        gameMenu.setLayout(null);
        gameMenu.setBackground(BACKGROUND_MENU);

        title.setFont(MENU);
        title.setBounds(320, 50, 280, 60);

        gameMenu.add(title);

        setJButtonField(menuEasy, BACKGROUND_EASY);
        menuEasy.setBounds(300, 150, 200, 40);
        gameMenu.add(menuEasy);

        setJButtonField(menuNormal, BACKGROUND_NORMAL);
        menuNormal.setBounds(300, 210, 200, 40);
        gameMenu.add(menuNormal);

        setJButtonField(menuHard, BACKGROUND_HARD);
        menuHard.setBounds(300, 270, 200, 40);
        gameMenu.add(menuHard);

        this.add(gameMenu);

        this.setResizable(false);
        this.revalidate();
        this.repaint();
    }

    public void setChoiceMenu() {
        choiceEasyMenu.setLayout(null);
        choiceEasyMenu.setBackground(BACKGROUND_EASY);

        choiceNormalMenu.setLayout(null);
        choiceNormalMenu.setBackground(BACKGROUND_NORMAL);

        choiceHardMenu.setLayout(null);
        choiceHardMenu.setBackground(BACKGROUND_HARD);

        for (int i = 0; i < 3; ++i) {
            easyPuzzles[i] = new JButton("" + (i + 1));
            normalPuzzles[i] = new JButton("" + (i + 1));
            hardPuzzles[i] = new JButton("" + (i + 1));

            easyPuzzles[i].setBounds(150 + (180 * i), 200, 120, 120);
            normalPuzzles[i].setBounds(150 + (180 * i), 200, 120, 120);
            hardPuzzles[i].setBounds(150 + (180 * i), 200, 120, 120);

            setJButtonField(easyPuzzles[i], CHOICE);
            setJButtonField(normalPuzzles[i], CHOICE);
            setJButtonField(hardPuzzles[i], CHOICE);

            choiceEasyMenu.add(easyPuzzles[i]);
            choiceNormalMenu.add(normalPuzzles[i]);
            choiceHardMenu.add(hardPuzzles[i]);
        }
        setJButtonField(getFromEasy, BACKGROUND_MENU);
        setJButtonField(getFromNormal, BACKGROUND_MENU);
        setJButtonField(getFromHard, BACKGROUND_MENU);

        getFromEasy.setBounds(650, 20, 130, 25);
        getFromNormal.setBounds(650, 20, 130, 25);
        getFromHard.setBounds(650, 20, 130, 25);

        choiceEasyMenu.add(getFromEasy);
        choiceNormalMenu.add(getFromNormal);
        choiceHardMenu.add(getFromHard);
    }

    public void setGameFieldPanels() {
        gameField.setLayout(new GridLayout(getGridSize(), getGridSize()));
        settingTextFields();
        settingBorders();
        gameField.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        choice.setLayout(null);
        choice.setBackground(BACKGROUND_MENU);

        time.setLayout(null);
        time.setBackground(TIME);

        setJButtonField(checkBut, CHOICE);
        setJButtonField(endGame, CHOICE);

        checkBut.setBounds(80, 220, 100, 50);
        endGame.setBounds(80, 320, 100, 50);

        time.setBounds(80, 120, 100, 50);
        time.setFont(FONT_NUMBERS);

        choice.add(checkBut);
        choice.add(endGame);
        choice.add(time);
        choice.setPreferredSize(new Dimension(FRAME_WIDTH - CANVAS_WIDTH, FRAME_HEIGHT - CANVAS_HEIGHT));
    }

    public void settingTextFields() {
        for (row = 0; row < getGridSize(); row++) {
            for (col = 0; col < getGridSize(); col++) {
                tfCells[row][col] = new JTextField();

                gameField.add(tfCells[row][col]);

                tfCells[row][col].setEditable(true);
                tfCells[row][col].setBackground(getOpenCellBgcolor());

                tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
                tfCells[row][col].setFont(FONT_NUMBERS);
            }
        }
    }

    public void settingBorders() {
        for (row = 0; row < getGridSize(); row++) {
            for (col = 0; col < getGridSize(); col++) {
                if ((row + 1) % SUBGRID_SIZE == 1) {
                    if ((col + 1) % SUBGRID_SIZE == 1) tfCells[row][col].setBorder(jTextBorderLeftTop);
                    else if ((col + 1) % SUBGRID_SIZE == 2) tfCells[row][col].setBorder(jTextBorderTop);
                    else tfCells[row][col].setBorder(jTextBorderRightTop);
                } else if ((row + 1) % SUBGRID_SIZE == 2) {
                    if ((col + 1) % SUBGRID_SIZE == 1) tfCells[row][col].setBorder(jTextBorderLeft);
                    else if ((col + 1) % SUBGRID_SIZE == 2) tfCells[row][col].setBorder(jTextBorderCenter);
                    else tfCells[row][col].setBorder(jTextBorderRight);
                } else {
                    if ((col + 1) % SUBGRID_SIZE == 1) tfCells[row][col].setBorder(jTextBorderLeftBottom);
                    else if ((col + 1) % SUBGRID_SIZE == 2) tfCells[row][col].setBorder(jTextBorderBottom);
                    else tfCells[row][col].setBorder(jTextBorderRightBottom);
                }
            }
        }
    }

    public void setJButtonField(JButton but, Color col) {
        but.setFocusPainted(false);
        but.setBorderPainted(true);
        but.setBackground(col);
    }

    public JTextField getTextField(int r, int c) {
        return tfCells[r][c];
    }

    public void colorEverything() {
        for (int i = 0; i < getGridSize(); i++) {
            for (int j = 0; j < getGridSize(); j++) {
                tfCells[i][j].setBackground(OPEN_CELL_TEXT_YES);
            }
        }
    }

    public void clearCheck() {
        for (int i = 0; i < getGridSize(); ++i) {
            for (int j = 0; j < getGridSize(); ++j) {
                if (tfCells[i][j].getBackground() != CLOSED_CELL_COLOR) {
                    tfCells[i][j].setBackground(OPEN_CELL_COLOR);
                }
            }
        }
    }

    public void setEmptyEverything() {
        for (int i = 0; i < getGridSize(); i++) {
            for (int j = 0; j < getGridSize(); j++) {
                tfCells[i][j].setText(null);
            }
        }
    }

    public void addCheckButtonListener(ActionListener CheckListener) {
        checkBut.addActionListener(CheckListener);
    }

    public void addEndGameButtonListener(ActionListener EndGameListener) {
        endGame.addActionListener(EndGameListener);
    }

    public void checkingCellOverWriting(WriteChecking e) {
        for (row = 0; row < getGridSize(); row++)
            for (col = 0; col < getGridSize(); col++) {
                tfCells[row][col].addKeyListener(e);
            }
    }

    public void addMenuEasyButtonListener(ActionListener EasyListener) {
        menuEasy.addActionListener(EasyListener);
    }

    public void addMenuNormalButtonListener(ActionListener NormalListener) {
        menuNormal.addActionListener(NormalListener);
    }

    public void addMenuHardButtonListener(ActionListener HardListener) {
        menuHard.addActionListener(HardListener);
    }

    public void addPuzzleButtonListener(ActionListener PuzzleButton) {
        for (int i = 0; i < 3; i++) {
            easyPuzzles[i].addActionListener(PuzzleButton);
            normalPuzzles[i].addActionListener(PuzzleButton);
            hardPuzzles[i].addActionListener(PuzzleButton);
        }
    }

    public void addGetBackToMenuListener(ActionListener BackListener) {
        getFromEasy.addActionListener(BackListener);
        getFromNormal.addActionListener(BackListener);
        getFromHard.addActionListener(BackListener);
    }

    public JPanel getGameMenu() {
        return gameMenu;
    }

    public JButton getEasyPuzzles(int i) {
        return easyPuzzles[i];
    }

    public JButton getNormalPuzzles(int i) {
        return normalPuzzles[i];
    }

    public JButton getHardPuzzles(int i) {
        return hardPuzzles[i];
    }

    public JPanel getChoiceEasyMenu() {
        return choiceEasyMenu;
    }

    public JPanel getChoiceNormalMenu() {
        return choiceNormalMenu;
    }

    public JPanel getChoiceHardMenu() {
        return choiceHardMenu;
    }

    public JPanel getGameField() {
        return gameField;
    }

    public JPanel getChoice() {
        return choice;
    }


    public void setTimeLabel(int min, int sec) {
        if (sec < 10) {
            time.setText(min + " : " + "0" + sec);
        } else {
            time.setText(min + " : " + sec);
        }
    }

    public int getGridSize() {
        return GRID_SIZE;
    }

    public Color getOpenCellTextNo() {
        return OPEN_CELL_TEXT_NO;
    }

    public Color getOpenCellBgcolor() {
        return OPEN_CELL_COLOR;
    }

    public Color getClosedCellBgcolor() {
        return CLOSED_CELL_COLOR;
    }
}
