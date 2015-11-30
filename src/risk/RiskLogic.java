package risk;

import risk.operations.*;
import risk.board.Territory;
import risk.operations.Error;
import risk.player.Player;
import risk.GameResources;
import risk.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Federico on 22/11/2015.
 */
//� la mia macchiana a stati finiti
public class RiskLogic {

    GameState gamestate;

    RiskLogic(GameState gamestate) {
        this.gamestate = gamestate;
    }



    public Operation makeMove(Territory  territory, GameState gamestate){

        //fase di fortificazione
        if(gamestate.getPhase() == Phases.FORTIFY){

            //non ci sono casi di territori selezionati
            if(gamestate.getFortify() == null){

                if(checkFortify(gamestate.getCurrentPlayerTurn(),territory)){
                    gamestate.setFortify(territory);
                    return new TerritorySelected(territory);
                }
                else{
                    //errore non è un tuo territorio non puoi fortificarlo;
                    return new Error(territory.getTerritoryName() + " not in your Territories");
                }
            }
            //ci sono già territori selezionati
            else{

                if(checkFortify(gamestate.getCurrentPlayerTurn(),territory)){

                    if(territory == gamestate.getFortify()){
                        //lo annullo
                        gamestate.setFortify(null);
                        return new Fortify(territory);
                    }
                    else{
                        //il territorio selezionato è diverso da quello di prima aspetto nuova conferma
                        //prima di cambiarlo bisogna prima deselezionare poi riselezionare
                        Territory fortify = gamestate.getFortify();
                        gamestate.setFortify(null);
                        return new TerritoryUnselected(fortify);
                    }
                }
                else{
                    //errore non è un tuo territorio non puoi fortificarlo;
                    gamestate.setFortify(null);
                    return new Error(territory.getTerritoryName() + " not in your Territories");
                }


            }
        }





        //fase di movimento
        if(gamestate.getPhase() == Phases.MOVE){

            if(gamestate.getMoveFrom()==null){

                if(checkMoveFrom(gamestate.getCurrentPlayerTurn(),territory)){
                    gamestate.setMoveFrom(territory);
                    return new TerritorySelected(territory);
                }
                else{
                    return new Error(territory.getTerritoryName() + " movefrom not in your territories");
                }


            }
            else{

                if(checkMoveTo(gamestate.getCurrentPlayerTurn(),territory)){
                    //deseleziono
                    if(territory==gamestate.getMoveFrom()){
                        gamestate.setMoveFrom(null);
                        return new TerritoryUnselected(territory);
                    }
                    else{
                        if(gamestate.getMoveTo() == null){
                            gamestate.setMoveTo(territory);
                            return new TerritorySelected(territory);

                        }
                        else{
                            if(gamestate.getMoveTo() == territory) {
                                Territory moveFrom=gamestate.getMoveFrom();
                                gamestate.setMoveFrom(null);
                                gamestate.setMoveTo(null);
                                return new Move(moveFrom,territory);
                            }
                            else{
                                Territory unselected = gamestate.getMoveTo();
                                gamestate.setMoveTo(null);
                                return new TerritoryUnselected(unselected);

                            }
                        }
                    }

                }
                else{
                    return new Error(territory.getTerritoryName() + " moveto not in your territories ");
                }
            }




        }


        //fase di attacco
        if(gamestate.getPhase() == Phases.ATTACK){

            if(gamestate.getAttackFrom() == null){

                if(checkAttackFrom(gamestate.getCurrentPlayerTurn(),territory)){
                    gamestate.setAttackFrom(territory);
                    return new TerritorySelected(territory);
                }
                else{

                    return new Error(territory.getTerritoryName() + " not in your territories or not enough units");

                }

            }
            else{

                if(gamestate.getAttackFrom() == territory){

                    gamestate.setAttackFrom(null);
                    return new TerritoryUnselected(territory);
                }
                else{
                    if(gamestate.getAttackTo() == null){
                        if(checkAttackTo(gamestate.currentPlayerTurn,gamestate.getAttackFrom(),territory)){
                            gamestate.setAttackTo(territory);
                        }
                        else{
                            return new Error(territory.getTerritoryName() + " not in neighbours");
                        }
                    }
                    else{
                        if(territory == gamestate.getAttackTo()){
                            Territory attackFrom = gamestate.getAttackFrom();
                            Territory attackTo = gamestate.getAttackTo();
                            gamestate.setAttackFrom(null);
                            gamestate.setAttackFrom(null);
                            return new Attack(attackFrom,attackTo);
                        }
                        else{
                            Territory attackTo=gamestate.getAttackTo();
                            gamestate.setAttackTo(null);
                            return new TerritoryUnselected(attackTo);
                        }
                    }
                }

            }


        }

        return new Error("Invalid operation");

    }


    public void fortify(Player current_player, Territory territory) {
        //checkfortify() da implementare
        territory.setCurrentUnits(territory.getCurrentUnits() + 1);
        current_player.setFreeUnits(current_player.getFreeUnits() - 1);

        //else  do nothing

    }

    public void attack(Player attacker, Territory from, Territory to,GameState gamesate) {
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

            if(isPlayerOut(gamesate,gamesate.getPlayerTer(to))){
                gamesate.elimiatePlayer(gamesate.getPlayerTer(to));
            }
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


    //controlla se il territorio � occupato o meno
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

    /*
    public boolean checkMove(Player current_player, Territory from, Territory to){
        //controllo se i territori appartengono al player poi controllo che sul primo territorio ci sia piu di una armata
        if(current_player.getPlayerID()==gamestate.getPlayerTer(from).getPlayerID()
                && current_player.getPlayerID()==gamestate.getPlayerTer(to).getPlayerID()){
            //controllo che le unit� nel territorio corrente siano piu di una
            if(from.getCurrentUnits()>1){
                return true;
            }
        }

        return false;
    }
    */

    public boolean checkMoveFrom(Player current_player, Territory from){
        //controllo che il territorio appartenga al player poi controllo che sul primo territorio ci sia piu di una armata
        if(current_player.getPlayerID()==gamestate.getPlayerTer(from).getPlayerID()){
            //controllo che le unit� nel territorio corrente siano piu di una
            if(from.getCurrentUnits()>1){
                return true;
            }
        }

        return false;
    }

    public boolean checkMoveTo(Player current_player, Territory to){
        //controllo che il territorio appartenga al player
        if(current_player.getPlayerID()==gamestate.getPlayerTer(to).getPlayerID()){

                return true;

        }

        return false;
    }

    /*
    public boolean checkAttack(Player attacker, Territory from, Territory to){
        //controllo che il territorio di partenza sia mio e quello di arrivo non lo sia
        if(attacker.getPlayerID()==gamestate.getPlayerTer(from).getPlayerID()
                && attacker.getPlayerID()!=gamestate.getPlayerTer(to).getPlayerID() ){
            //controllo che il territorio di arrivo sia un mio vicino e che nel territorio di partenza ci siano almeno 2 unit�
            if(from.isNeighbour(to.getTerritoryID()) && from.getCurrentUnits()>1){
                return true;
            }
        }

        return false;

    }
    */

    public boolean checkAttackFrom(Player attacker, Territory from){
        //controllo che il territorio di partenza sia mio
        if(attacker.getPlayerID()==gamestate.getPlayerTer(from).getPlayerID()){
            //controllo che nel territorio di partenza ci siano almeno 2 unita
            if(from.getCurrentUnits()>1){
               return true;
            }
        }

        return false;

    }




    public boolean checkAttackTo(Player attacker,Territory from, Territory to){
        //controllo che il territorio di partenza sia mio
        if(attacker.getPlayerID()!=gamestate.getPlayerTer(to).getPlayerID()){
            //controllo che nel territorio di partenza ci siano almeno 2 unita
            if(to.isNeighbour(from.getTerritoryID())){
                return true;
            }
        }

        return false;

    }


    public boolean isPlayerOut(GameState gamestate,Player player){

        for (Territory territory : gamestate.getTerritoriesPlayersMap().keySet()) {
            if(gamestate.getPlayerTer(territory)==player){
                return false;
            }
        }
        return true;
    }








}