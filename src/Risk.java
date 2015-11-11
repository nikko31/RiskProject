import board.Board;
import game.GameMaster;

/**
 * Created by nikko31 on 11/11/15.
 */
public class Risk {
    public Risk() {
        char human_players = 1;
        GameMaster master = game_setup(human_players);
        //start gui
        run_game(master);
    }

    private void run_game(GameMaster master) {
        while (!master.ended) {
            run_turn(master);
        }
    }

    private void run_turn(GameMaster master) {
        master.player_take_turn();
        master.end_turn();
    }


    public GameMaster game_setup(char number_human_players) {
        int _DEV_HUMAN_PLAYERS = number_human_players;
        Board game_board = Board.generate_empty_board();
        GameMaster game_master = new GameMaster(game_board);
        game_master.generate_players(_DEV_HUMAN_PLAYERS);
        game_board.dev_random_assign_owners(game_master);
        return game_master;
    }
}
