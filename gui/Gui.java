package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import gui.panel.BoardPanel;
import gui.panel.ControlPanel;
import logic.Board;
import logic.Square;
 
/* Name: Mai Quoc Binh
 ITDSIU21077
*/

public class Gui extends JFrame implements ICommon, ITrans {
  private static final long serialVersionUID = -5479701518838741039L;
  private static final String TITLE = "MineSweeper";
  public static final int FRAME_WIDTH = 730;
  public static final int FRAME_HEIGHT = 600;
  private BoardPanel boardPanel;
  private ControlPanel controlPanel;
  private Board board;
  private boolean isHardMode;
 
  public Gui(boolean isHardMode) {
    board = new Board(isHardMode);
    initComp();
    addComp();
    addEvent();
  }
  public void openStartWindow() {
    StartWindow startWindow = new StartWindow();
    startWindow.setVisible(true);
    dispose(); // Đóng cửa sổ hiện tại
}
  public void setHardMode(boolean hardMode) {
        this.isHardMode = hardMode;
    }

    public boolean isHardMode() {
        return isHardMode;
    }
 
  @Override
  public void initComp() {
    setTitle(TITLE);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    setLayout(null);
  }
    @Override
  public void addComp() {
    boardPanel = new BoardPanel();
    boardPanel.setBounds(10, 60, 700, 500);
    add(boardPanel);
    boardPanel.addListener(this);
 
    controlPanel = new ControlPanel();
    controlPanel.setBounds(10, 0, 700, 60);
    add(controlPanel);
    controlPanel.addListener(this);
  }
 
  @Override
  public void addEvent() {
    WindowListener wd = new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int kq = JOptionPane.showConfirmDialog(Gui.this, "Bạn có muốn thoát không?",
         "Thông báo", JOptionPane.YES_NO_OPTION);
        if (kq == JOptionPane.YES_OPTION) {
          dispose();
        }
      }
    };
    addWindowListener(wd);
  }
 
  @Override
  public Square[][] getListSquare() {
    return board.getListSquare();
  }
 
  @Override
public void play(int x, int y) {
    boolean check = board.play(x, y);
    if (!check) { board.showAllSquares(); }

    boolean isHardMode = true; // Thay bằng cách lấy thông tin từ cửa sổ "START" khi người chơi chọn
    boardPanel.updateBoard(isHardMode);

    // cập nhật số ô chưa mở vào controlPanel
    int numSquareClosed = boardPanel.getNumSquareClosed();
    controlPanel.updateStatus(numSquareClosed);
}
 // Trong class Gui
@Override
public void target(int x, int y) {
    board.target(x, y);
    boolean isHardMode = true; // Thay bằng cách lấy thông tin từ cửa sổ "START" khi người chơi chọn
    boardPanel.updateBoard(isHardMode);
}

// Trong class BoardPanel
public void updateBoard(boolean isHardMode) {
}

 
  // Trong class Gui
@Override
public void restart() {
    openStartWindow();
}



 @Override
public void undo() {
    board.undo();
    boolean isHardMode = true; // Thay bằng cách lấy thông tin từ cửa sổ "START" khi người chơi chọn
    boardPanel.updateBoard(isHardMode);
    int numSquareClosed = boardPanel.getNumSquareClosed();
    controlPanel.updateStatus(numSquareClosed);
}

   public void setDifficulty(boolean isHardMode) {
        // Xử lý khi chọn chế độ "Dễ" hoặc "Khó"
        // Cập nhật thông tin vào BoardPanel
        boardPanel.updateBoard(isHardMode);
    }
}
