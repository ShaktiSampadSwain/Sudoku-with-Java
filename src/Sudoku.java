import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Sudoku {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Sudoku");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JButton numSelected  = null;
    int errors = 0;

    String[] puzzle = {
            "--74916-5",
            "2---6-3-9",
            "-----7-1-",
            "-586----4",
            "--3----9-",
            "--62--187",
            "9-4-7---2",
            "67-83----",
            "81--45---"
    };
    String[] solution = {
            "387491625",
            "241568379",
            "569327418",
            "758619234",
            "123784596",
            "496253187",
            "934176852",
            "675832941",
            "812945763"
    };

    Sudoku(){
        //frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Ariel",Font.BOLD,30));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("SUDOKU : 0");

        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(9,9));
        setupTiles();
        frame.add(boardPanel, BorderLayout.CENTER);

        buttonPanel.setLayout(new GridLayout(1,9));
        setupButton();
        frame.add(buttonPanel, BorderLayout.SOUTH);





        frame.setVisible(true);
    }

    void setupTiles(){
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Tile tile = new Tile(r,c);
                char tileChar = puzzle[r].charAt(c);
                if(tileChar != '-'){
                    tile.setFont(new Font("Ariel", Font.BOLD,20));
                    tile.setText(String.valueOf(tileChar));
                    tile.setBackground(Color.WHITE );
                }
                else{
                    tile.setFont(new Font("Ariel",Font.PLAIN,20));
                    tile.setBackground(Color.GRAY);
                }
                int top = 1,left = 1,bottom = 1,right = 1;
                if (r == 2 && c == 2 || r == 5 && c == 5 || r == 2 && c == 5 || r == 5 && c == 2) {
                    //tile.setBorder(BorderFactory.createMatteBorder(1,1,3,3, Color.black));
                    bottom = 3;
                    right = 3;
                }
                else if(c == 2 || c == 5){
                    //tile.setBorder(BorderFactory.createMatteBorder(1,1,1,3, Color.black));
                    right = 3;
                }
                else if(r == 2 || r == 5){
                    //tile.setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.black));
                    bottom = 3;
                }
//                else {
//                    tile.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
//                }
                tile.setBorder(BorderFactory.createMatteBorder(top,left,bottom,right, Color.black));


                boardPanel.add(tile);
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Tile tile = (Tile) e.getSource();
                        int r = tile.r;
                        int c = tile.c;
                        if (numSelected != null){
                            if(tile.getText() != ""){
                                return;
                            }
                            String numSelectedText = numSelected.getText();
                            String tileSolotion = String.valueOf(solution[r].charAt(c));

                            if (tileSolotion.equals(numSelectedText)) {
                                tile.setText(numSelectedText);
                            }
                            else {
                                errors += 1;
                                textLabel.setText("Errors : " + String.valueOf(errors));
                            }
                        }

                    }
                });
            }

        }
    }
    void setupButton(){
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Ariel", Font.BOLD,20));
            button.setFocusable(false);
            button.setBackground(Color.WHITE);
            button.setText(String.valueOf(i));
            buttonPanel.add(button);

            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    if(numSelected != null)
                        numSelected.setBackground(Color.white);
                    numSelected = button;
                    numSelected.setBackground(Color.lightGray);
                }
            });
        }
    }
}
