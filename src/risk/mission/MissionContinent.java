package risk.mission;

import risk.GameState;
import risk.board.Continent;
import risk.board.Territory;
import risk.player.Player;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Federico on 17/12/2015.
 */
public class MissionContinent implements Mission {
    private ArrayList<Integer> contiToCon;
    private int kind;
    private Color playerColor;
    private int playerId;


    public MissionContinent(int kind,ArrayList<Integer> contiToCon){
        this.contiToCon = contiToCon;
        this.kind =kind;
    }

    @Override
    public boolean checkHitMission(int playerId,Color playerColor, GameState gameState) {
        boolean flag0, flag1, flag2;
        flag0 = true;
        flag1 = true;
        flag2 = true;
        for (Continent continent : gameState.getContinents()) {
            if (continent.getContinentId() == this.getContiToCon().get(0)) {
                for (Territory territory : continent.getTerritories()) {
                    if (gameState.getPlayerTer(territory).getPlayerID() != playerId) {
                        flag0 = false;
                    }
                }
            }
            if (continent.getContinentId() == this.getContiToCon().get(1)) {
                for (Territory territory : continent.getTerritories()) {
                    if (gameState.getPlayerTer(territory).getPlayerID() != playerId) {
                        flag1 = false;
                    }
                }
            }
        }
        if(kind > 3){
            for (Continent continent : gameState.getContinents()){
                if (continent.getContinentId() != this.getContiToCon().get(0) && continent.getContinentId() != this.getContiToCon().get(1)) {
                    for (Territory territory : continent.getTerritories()) {
                        if (gameState.getPlayerTer(territory).getPlayerID() != playerId) {
                            flag2 = false;
                        }
                    }
                    if(flag2){
                        return flag0 && flag1;
                    }
                }


            }
            return false;


        }
        return flag0 && flag1;

    }

    @Override
    public String toString() {
        String string = "you have to conquer: " + convNumberContinet(this.contiToCon.get(0)) + " and " +convNumberContinet(this.contiToCon.get(1));
        if(kind <=3){
            return string;
        }
        else{
            return string + " and a third continet to choose ";
        }


    }

    private String convNumberContinet(int number){
        switch(number){
            case 0:
                return "NORTH AMERICA";
            case 1:
                return "SOUTH AMERICA";
            case 2:
                return "EUROPE";
            case 3:
                return "AFRICA";
            case 4:
                return "ASIA";
            case 5:
                return"OCEANIA";
            default:
                return "error";
        }
    }

    public ArrayList<Integer> getContiToCon() {
        return contiToCon;
    }

    public void setContiToCon(ArrayList<Integer> contiToCon) {
        this.contiToCon = contiToCon;
    }


}
