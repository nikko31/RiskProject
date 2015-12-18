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
    private boolean firstcheck;

    public MissionDefeatPlayer(Color playerToDef) {
        this.firstcheck = true;
        this.playerToDef = playerToDef;
    }

    @Override
    public boolean checkHitMission(GameState gameState, Player playerCurrent) {
        System.out.println("controllo missione sconfiggi");
        int counter = 0;

        if(firstcheck){
            for(Player player : gameState.getPlayers()){
                if (player.getPlayerColor().equals(playerToDef)
                        && !gameState.getCurrentPlayerTurn().getPlayerColor().equals(playerToDef)){
                    System.out.println("c'è giocatore avversario con colore richiesto");
                    firstcheck = false;
                    return false;
                }

            }

        }
        if(firstcheck){
            for (Territory territory : gameState.getTerritoriesPlayersMap().keySet()) {
                System.out.println("tu hai quel colore allora conquista i territory");
                if (gameState.getPlayerTer(territory).equals(gameState.getCurrentPlayerTurn())) {
                    counter++;
                }
            }
            if (counter >= 24) {
                return true;
            }
            return false;

        }
        else{
            if(gameState.getPlayerEliminated().get(playerToDef) != null){
                System.out.println("c'è giocatore avversario con colore richiesto");
                if(gameState.getPlayerEliminated().get(playerToDef).equals(gameState.getCurrentPlayerTurn())){
                    System.out.println("c'è giocatore avversario con colore richiesto e l ho eliminato io");
                    return true;
                }
                else{
                    System.out.println("c'è giocatore avversario con colore richiesto e non l'ho eliminato io");
                    for (Territory territory : gameState.getTerritoriesPlayersMap().keySet()) {
                        if (gameState.getPlayerTer(territory).equals(gameState.getCurrentPlayerTurn())) {
                            counter++;
                        }
                    }
                    return counter >= 24;

                }
            }
            return false;

        }





    }

    /*
    MISSION_DESTROY.put(0,Color.RED);
    MISSION_DESTROY.put(1,Color.cyan);
    MISSION_DESTROY.put(2,Color.decode("#FF33FF"));
    MISSION_DESTROY.put(3,Color.decode("#00FF00"));
    MISSION_DESTROY.put(4,Color.decode("#0000FF"));
    MISSION_DESTROY.put(5,Color.yellow);
    */
    /*
    private String colorToString(Color color){
        switch(color.getRGB()){
            case  16711680:
                return "RED";
            case 65280:
                return "GREEN";
            case :
                retrun "";
            case 255
            retrun " BLUE";
            case :
            retrun "";
            case :
            retrun "";
        }
    }
    */


    @Override
    public String toString() {
        return "you have to defeat: " + " " + this.playerToDef.toString() + " if is your color or someone defeat him," +
                " you have to conquer 24 territories ";
    }




}