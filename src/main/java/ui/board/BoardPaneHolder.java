package ui.board;

public class BoardPaneHolder {
    public BoardPaneHolder(BoardPaneBase boardPane) {
        this.boardPane = boardPane;
    }

    public BoardPaneBase getBoardPane() {
        return boardPane;
    }

    public void setBoardPane(BoardPaneBase boardPane) {
        this.boardPane = boardPane;
    }

    private BoardPaneBase boardPane;
}
