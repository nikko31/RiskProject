package game;

import board.Board;

/**
 * Created by nikko31 on 11/11/15.
 */
public class GameMaster {
    final static int MAX_PLAYERS = 6;
    final static int MIN_PLAYERS = 2;
    public boolean ended;

    public GameMaster(Board game_board) {
        new GameMaster(game_board, 6);
    }

    public GameMaster(Board game_board, int num_players) {
        this.ended = false;
        if (num_players <= MIN_PLAYERS || num_players >= MAX_PLAYERS) {
            //print error log
        }
    }

    public void generate_players(int dev_human_players) {
    }

    public void player_take_turn() {
    }

    public void end_turn() {
    }
}
