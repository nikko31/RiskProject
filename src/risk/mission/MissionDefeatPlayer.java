package risk.mission;

import risk.GameState;
import risk.board.Territory;
import risk.player.Player;
import java.awt.*;

/**
 * Created by Federico on 17/12/2015.
 */
public class MissionDefeatPlayer implements Mission {
    private Color playerToDef;
    private boolean firstcheck;
    private boolean choiche;

    public MissionDefeatPlayer(Color playerToDef) {
        this.firstcheck = true;
        this.playerToDef = playerToDef;
        this.choiche = true;
    }

    @Override
    public boolean checkHitMission(GameState gameState, Player playerCurrent) {
        System.out.println("checkDefeatMission");
        int counter = 0;

        if(firstcheck){
            for(Player player : gameState.getPlayers()){
                if (player.getPlayerColor().equals(playerToDef)
                        && !gameState.getCurrentPlayerTurn().getPlayerColor().equals(playerToDef)){
                    System.out.println("there is one player with the request color");
                    choiche = false;
                    return false;
                }

            }
            firstcheck = false;


        }
        if(choiche){
            System.out.println("you can't self destroy: conquest 24 territories ");
            for (Territory territory : gameState.getTerritoriesPlayersMap().keySet()) {
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
                System.out.println("there is a player with the color you search");
                if(gameState.getPlayerEliminated().get(playerToDef).equals(gameState.getCurrentPlayerTurn())){
                    System.out.println("i eliminate the player");
                    return true;
                }
                else{
                    System.out.println("someone eliminate my objective");
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


    private String colorToString(Color color){

        if(color.equals(Color.RED)){
            return "RED";
        }
        else if(color.equals(Color.cyan)){
            return "CYAN";
        }
        else if(color.equals(Color.yellow)){
            return "YELLOW";
        }
        else if(color.equals(Color.decode("#FF33FF"))){
            return "PURPLE";
        }
        else if(color.equals(Color.decode("#00FF00"))){
            return "GREEN";
        }
        else if(color.equals(Color.decode("#0000FF"))){
            return "BLUE";
        }
        else {
            return "error";
        }


    }



    @Override
    public String toString() {
        return "you have to defeat: " + " " + this.playerToDef.toString() +" "+ colorToString(playerToDef) + " player, if is your color or someone defeat him," +
                " you have to conquer 24 territories ";
    }




}