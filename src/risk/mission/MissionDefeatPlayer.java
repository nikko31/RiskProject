package risk.mission;

import risk.GameState;
import risk.board.Territory;
import risk.player.Player;

import java.awt.*;
import java.util.Map;

/**
 * Created by Federico on 17/12/2015.
 */
public class MissionDefeatPlayer implements Mission {
    private Color playerToDef;

    public MissionDefeatPlayer(Color playerToDef) {
        this.playerToDef = playerToDef;
    }

    @Override
    public boolean checkHitMission(GameState gameState, Player playerCurrent) {
        System.out.println("controllo missione sconfiggi");
        int counter = 0;
        for(Player player : gameState.getPlayers()){
            if (player.getPlayerColor().equals(playerToDef)
                    && !gameState.getCurrentPlayerTurn().getPlayerColor().equals(playerToDef)){
                System.out.println("c'è giocatore avversario con colore richiesto");
                return false;
            }

        }

        if(gameState.getPlayerEliminated().get(playerToDef) != null)
        for (Player player : gameState.getPlayers()) {
            if (player.getPlayerColor().equals(playerToDef)
                    && !gameState.getCurrentPlayerTurn().getPlayerColor().equals(playerToDef)) {
                System.out.println("c'è giocatore avversario con colore richiesto");
                if (gameState.getPlayerEliminated().get(playerToDef) != null) {
                    System.out.println("c'è giocatore avversario con colore richiestoe l'ho eliminato io");
                    if(gameState.getPlayerEliminated().get(playerToDef).equals(gameState.getCurrentPlayerTurn())){
                        System.out.println("c'è giocatore avversario con colore richiesto ho vinto");
                        return true;
                    }
                    else{
                        return false;
                    }

                } else {
                    System.out.println("c'è giocatore avversario con colore richiesto e non l'ho eliminato io");
                    for (Territory territory : gameState.getTerritoriesPlayersMap().keySet()) {
                        if (gameState.getPlayerTer(territory).equals(gameState.getCurrentPlayerTurn())) {
                            counter++;
                        }
                    }
                    return counter >= 24;
                }


            }


        }


        if (playerToDef.equals(gameState.getCurrentPlayerTurn().getPlayerColor())) {
            for (Territory territory : gameState.getTerritoriesPlayersMap().keySet()) {
                if (gameState.getPlayerTer(territory).equals(gameState.getCurrentPlayerTurn())) {
                    counter++;
                }
            }
            if (counter >= 24) {
                return true;
            }
        }

        return false;


    }


    @Override
    public String toString() {
        return "you have to defeat: " + " " + this.playerToDef.toString() + " if is your color or someone defeat him," +
                " you have to conquer 24 territories ";
    }

}