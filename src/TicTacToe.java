import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JPanel textPanel = new JPanel();
    JLabel textLabel = new JLabel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;

    TicTacToe() {

        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText(currentPlayer + "'s Turn");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.DARK_GRAY);
        frame.add(boardPanel, BorderLayout.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                JButton button = new JButton();
                board[i][j] = button;

                button.setFont(new Font("Arial", Font.BOLD, 120));
                button.setFocusable(false);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (gameOver)
                            return;

                        JButton button = (JButton) e.getSource();

                        if (button.getText().equals("")) {

                            button.setText(currentPlayer);

                            checkWinner();

                            if (!gameOver) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s Turn");
                            }
                        }
                    }
                });

                boardPanel.add(button);
            }
        }

        frame.setVisible(true);
    }

    void checkWinner() {

        // Rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals(""))
                continue;

            if (board[i][0].getText().equals(board[i][1].getText())
                    && board[i][1].getText().equals(board[i][2].getText())) {

                for (int c = 0; c < 3; c++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        // Columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j].getText().equals(""))
                continue;

            if (board[0][j].getText().equals(board[1][j].getText())
                    && board[1][j].getText().equals(board[2][j].getText())) {

                for (int r = 0; r < 3; r++) {
                    setWinner(board[r][j]);
                }
                gameOver = true;
                return;
            }
        }

        // Main Diagonal
        if (!board[0][0].getText().equals("")
                && board[0][0].getText().equals(board[1][1].getText())
                && board[1][1].getText().equals(board[2][2].getText())) {

            setWinner(board[0][0]);
            setWinner(board[1][1]);
            setWinner(board[2][2]);

            gameOver = true;
            return;
        }

        // Anti Diagonal
        if (!board[0][2].getText().equals("")
                && board[0][2].getText().equals(board[1][1].getText())
                && board[1][1].getText().equals(board[2][0].getText())) {

            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);

            gameOver = true;
        }
    }

    void setWinner(JButton button) {
        button.setForeground(Color.GREEN);
        textLabel.setText(currentPlayer + " Wins!");
    }
}