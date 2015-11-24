package risk;

import risk.board.Territory;
import risk.player.Player;
import risk.GameResources;
import risk.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Federico on 22/11/2015.
 */
//è la mia macchiana a stati finiti
public class RiskLogic {

    GameState gamestate;

    RiskLogic(){
        gamestate=new GameState();//va inizializzato
    }
    public void fortify(Player current_player, Territory territory) {
        //checkfortify() da implementare
        territory.setCurrentUnits(territory.getCurrentUnits() + 1);
        current_player.setFreeUnits(current_player.getFreeUnits() - 1);

        //else  do nothing

    }

    public void attack(Player attacker, Territory from, Territory to) {
        //checkattackpossibility() da implementare
        int attackdice, defencedice;
        attackdice = GameResources.getMaxDiceRollsForAttacker(from.getCurrentUnits());
        defencedice = GameResources.getMaxDiceRollsForDefender(to.getCurrentUnits());
        ArrayList<Integer> attackdices = this.rollDice(attackdice);
        ArrayList<Integer> defecedices = this.rollDice(defencedice);
        //riutilizzo attackdice e defencedice per mettere le singole vittorie
        attackdice =0;
        defencedice=0;

        for (int i = 0; i < defecedices.size(); i++) {
            if(attackdices.get(i)>defecedices.get(i)){
                attackdice++;
            }
            else{
                defencedice++;
            }

        }
        removeTroops(from,defencedice);


        if(checkIsConquered(to,attackdice)){
            occupyTerritory(attacker,from,to,attackdice);
        }
        else{
            removeTroops(to,attackdice);

        }


    }

    //sposta le una truppa dallo stato from a to
    public void move(Player current_player, Territory from, Territory to) {
        from.setCurrentUnits(from.getCurrentUnits() - 1);
        to.setCurrentUnits(to.getCurrentUnits() + 1);
    }

    //va in gamestate ad aggiornare la mappa dei territori
    public void occupyTerritory(Player current_player,Territory from, Territory to,int units){
        from.setCurrentUnits(from.getCurrentUnits()-units);
        gamestate.setPlayerTer(to, current_player);
        to.setCurrentUnits(units);
    }

    public void removeTroops(Territory territory,int troops){
        territory.setCurrentUnits(territory.getCurrentUnits()-troops);
    }


    public ArrayList<Integer> rollDice(int n) {
        ArrayList<Integer> dice = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            dice.add((new Random().nextInt(5)) + 1);
        }
        Collections.sort(dice);
        return dice;
    }


    //controlla se il territorio è occupato o meno
    public boolean checkIsConquered(Territory territory,int units){
        if(territory.getCurrentUnits()<=units){
            return true;
        }

        return false;
    }




    public boolean checkFortify(Player current_player, Territory territory){
        //controllo di avere almeno una armata
        if(current_player.getFreeUnits()>0){
            //controllo che il territorio sia mio
            if(current_player.getPlayerID()==gamestate.getPlayerTer(territory).getPlayerID()){
                return true;
            }

        }
        return false;
    }


    public boolean checkMove(Player current_player, Territory from, Territory to){
        //controllo se i territori appartengono al player poi controllo che sul primo territorio ci sia piu di una armata
        if(current_player.getPlayerID()==gamestate.getPlayerTer(from).getPlayerID()
                && current_player.getPlayerID()==gamestate.getPlayerTer(to).getPlayerID()){
            //controllo che le unità nel territorio corrente siano piu di una
            if(from.getCurrentUnits()>1){
                return true;
            }
        }

        return false;
    }

    public boolean checkAttack(Player attacker, Territory from, Territory to){
        //controllo che il territorio di partenza sia mio e quello di arrivo non lo sia
        if(attacker.getPlayerID()==gamestate.getPlayerTer(from).getPlayerID()
                && attacker.getPlayerID()!=gamestate.getPlayerTer(to).getPlayerID() ){
            //controllo che il territorio di arrivo sia un mio vicino e che nel territorio di partenza ci siano almeno 2 unità
            if(from.isNeighbour(to.getTerritoryID()) && from.getCurrentUnits()>1){
                return true;
            }
        }

        return false;

    }







}