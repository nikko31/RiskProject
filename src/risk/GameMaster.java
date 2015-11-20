package risk;


import risk.board.Board;
import risk.player.HumanGuiRiskPlayer;

import java.util.ArrayList;

/**
 * Created by nikko31 on 11/11/15.
 */
public class GameMaster {
    public static final int MAX_PLAYERS = 6;
    public static final int MIN_PLAYERS = 2;
    public static final String REINFORCE = "reinforce";
    public static final String ATTACK = "attack";
    public static final String FORTIFY = "fortify";
    public static final String UNDEFINED = "undefined";

    public boolean ended;
    public String phase;
    public Board board;
    public ArrayList<HumanGuiRiskPlayer> players;
    public int _current_player;
    public int _num_players;

    public GameMaster(Board game_board) {
        new GameMaster(game_board, 6);
    }

    public GameMaster(Board game_board, int num_players) {
        if (num_players <= MIN_PLAYERS || num_players >= MAX_PLAYERS) {
            //print error log
        }
        this.ended = false;
        this.phase = UNDEFINED;
        this.board = game_board;
        this._current_player = 0;
        this._num_players = num_players;
    }

    public void generate_players(int number_human_players) {
        for (int i = 0; i < number_human_players; i++) {
            this.players.add(new HumanGuiRiskPlayer("Human" + Integer.toString(i)));
        }
    }

    public void player_take_turn() {
    }

    public void end_turn() {
    }
}
