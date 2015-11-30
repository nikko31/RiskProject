package risk.operations;

import risk.player.Player;

/**
 * Created by Federico on 30/11/2015.
 */
public class Victory implements Operation {
    private Player player;

    public Victory(Player player) {
        this.player=player;
    }

    public Player getPlayer(){
        return player;
    }

    @Override
    public String operationString() {
        return "VICTORY: " + this.getPlayer().getPlayerName() + " WIN THE GAME";
    }
}