package risk;

import risk.operations.*;
import risk.board.Territory;
import risk.operations.Error;
import risk.player.Player;
import risk.GameResources;
import risk.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Federico on 22/11/2015.
 */

public class RiskLogic {

    int firstTurn;
    GameState gamestate;
    List<Territory> territories;
    Territory territory;

    public RiskLogic(GameState gamestate){
        firstTurn = 0;
        territory = null;
        this.gamestate = gamestate;
        territories = new ArrayList<Territory>(gamestate.getTerritoriesPlayersMap().keySet());
    }


    public Operation makeMove(String string, GameState gamestate){
        territory = stringToTerritory(string);
        //Fortify phase
        if(gamestate.getPhase() == Phases.FORTIFY || gamestate.getPhase() == Phases.INITIAL ){

            if(checkFortifyUnits(gamestate.getCurrentPlayerTurn())) {
                if (checkFortify(gamestate.getCurrentPlayerTurn(), territory)) {
                    fortify(gamestate.getCurrentPlayerTurn(), territory);
                    return new Fortify(territory);
                } else {
                    //error not in your territories
                    return new Error(territory.getTerritoryName() + " not in your Territories");
                }
            }
            else{
                //error not enough units
                return new Error(territory.getTerritoryName() + "don't have free units");
            }


        }


        //Attack phase
        if(gamestate.getPhase() == Phases.ATTACK){

            if(gamestate.getAttackFrom() == null){

                if(checkAttackFrom(gamestate.getCurrentPlayerTurn(), territory)){

                    if(checkAttackFromUnits(gamestate.getCurrentPlayerTurn(), territory)){
                        gamestate.setAttackFrom(territory);
                        return new TerritorySelected(territory);
                    }
                    else{
                        return new Error(territory.getTerritoryName() + " not enough units");
                    }

                }
                else{

                    return new Error(territory.getTerritoryName() + " not in your territories");

                }

            }
            else{

                if(gamestate.getAttackFrom() == territory){

                    gamestate.setAttackFrom(null);
                    return new TerritoryUnselected(territory);
                }
                else{

                    if(checkAttackTo(gamestate.currentPlayerTurn,gamestate.getAttackFrom(),territory)){
                        Territory attackFrom = gamestate.getAttackFrom();
                        gamestate.setAttackFrom(null);
                        attack(gamestate.currentPlayerTurn,attackFrom,territory);
                        return new Attack(attackFrom,territory);
                    }
                    else{
                        return new Error(territory.getTerritoryName() + " not in neighbours");
                    }

                }

            }


        }


        //Move phase
        if(gamestate.getPhase() == Phases.MOVE){

            if (gamestate.getMoveFrom() == null) {

                if (checkMoveFrom(gamestate.getCurrentPlayerTurn(), territory)) {
                    if (checkMoveFromUnits(gamestate.getCurrentPlayerTurn(), territory)) {
                        gamestate.setMoveFrom(territory);
                        return new TerritorySelected(territory);
                    } else {
                        //not enough units in move from
                        return new Error(territory.getTerritoryName() + " movefrom not enough units");
                    }
                } else {
                    // move from not in your territories
                    return new Error(territory.getTerritoryName() + " movefrom not in your territories");
                }


            } else {

                if (checkMoveTo(gamestate.getCurrentPlayerTurn(), territory)) {
                    //unselect
                    if (territory == gamestate.getMoveFrom()) {
                        gamestate.setMoveFrom(null);
                        return new TerritoryUnselected(territory);
                    } else {
                        Territory moveFrom = gamestate.getMoveFrom();
                        gamestate.setMoveFrom(null);
                        move(gamestate.getCurrentPlayerTurn(), moveFrom, territory);
                        gamestate.nextPhase();
                        return new Move(moveFrom, territory);

                    }

                } else {
                    return new Error(territory.getTerritoryName() + " moveto not in your territories ");
                }
            }
        }
        return new Error("Invalid operation");
    }


    public List<Operation> nextPhase(){
        List<Operation> operations = new ArrayList<Operation>();
        if(gamestate.getPhase() == Phases.INITIAL){
            gamestate.nextPhase();
            firstTurn++;
            operations.add(new NewPhase(Phases.END_TURN));
        }
        if(gamestate.getPhase() == Phases.BONUS){
            int units;
            units = territoriesBonus();
            gamestate.nextPhase();
            operations.add(new UnitsBonus(units));
            operations.add(new NewPhase(Phases.FORTIFY));
            return operations;

        }

        if(gamestate.getPhase() == Phases.FORTIFY){
            gamestate.nextPhase();
            operations.add(new NewPhase(Phases.ATTACK));
            return operations;

        }
        else if((gamestate.getPhase() == Phases.ATTACK)){
            gamestate.nextPhase();
            if(gamestate.getAttackFrom() != null){
                operations.add(new TerritoryUnselected(gamestate.getAttackFrom()));
                gamestate.setAttackFrom(null);
            }
            operations.add(new NewPhase(Phases.MOVE));
            return operations;

        }
        else if(gamestate.getPhase() == Phases.MOVE){
            gamestate.nextPhase();
            if(gamestate.getMoveFrom() != null){
                operations.add(new TerritoryUnselected(gamestate.getMoveFrom()));
                gamestate.setMoveFrom(null);
            }
            operations.add(new NewPhase(Phases.END_TURN));
            return operations;

        }
        else if(gamestate.getPhase() == Phases.END_TURN){


            if(checkVictory()){
                operations.add(new Victory(gamestate.getCurrentPlayerTurn()));
                return operations;
            }
            else if(firstTurn >= gamestate.getPlayers().size()){
                gamestate.nextPlayer();
                gamestate.setPhase(Phases.BONUS);
                operations.add(new NewPhase(Phases.BONUS));
                return operations;

            }
            else{
                gamestate.nextPlayer();
                gamestate.nextPhase();
                operations.add(new NewPhase(Phases.INITIAL));
                return operations;

            }

        }
        else{
            operations.add(new Error("Invalid Operation"));
            return operations;

        }
    }

    //-------------------------------------FORTIFY METHOD-------------------------------------------
    public void fortify(Player current_player, Territory territory) {

        territory.setCurrentUnits(territory.getCurrentUnits() + 1);
        current_player.setFreeUnits(current_player.getFreeUnits() - 1);

    }

    public boolean checkFortifyUnits(Player current_player){
        //check to have one units or more
        if(current_player.getFreeUnits()>0){

            return true;

        }
        return false;
    }

    public boolean checkFortify(Player current_player, Territory territory){
        //check if the territory is my
        if(current_player.getPlayerID()==gamestate.getPlayerTer(territory).getPlayerID()){
            return true;
        }
        else{
            return false;
        }


    }

    //---------------------------------ATTACK METHOD---------------------------------------


    public boolean attack(Player attacker, Territory from, Territory to) {
        int attackdice, defencedice;
        attackdice = GameResources.getMaxDiceRollsForAttacker(from.getCurrentUnits());
        defencedice = GameResources.getMaxDiceRollsForDefender(to.getCurrentUnits());
        ArrayList<Integer> attackdices = this.rollDice(attackdice);
        ArrayList<Integer> defecedices = this.rollDice(defencedice);
        //use the dices to save the number of victory
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
        //remove troops in attacker territory
        removeUnits(from, defencedice);


        if(checkIsConquered(to,attackdice)){
            Player player = null;
            player = gamestate.getPlayerTer(to);

            if(isPlayerOut(player)){
                gamestate.elimiatePlayer(player);
            }
            occupyTerritory(attacker,from,to,attackdice);
            return true;


        }
        else{
            removeUnits(to, attackdice);
            return false;

        }


    }


    public boolean checkAttackFromUnits(Player attacker, Territory from){
        //check if in to have enough units to attack

        if(from.getCurrentUnits()>1){
            return true;
        }
        else{
            return false;
        }



    }

    public boolean checkAttackFrom(Player attacker, Territory from){
        //che the from territory is mine
        if(attacker.getPlayerID()==gamestate.getPlayerTer(from).getPlayerID()){

            return true;
        }
        else{
            return false;
        }

    }


    public boolean checkAttackTo(Player attacker,Territory from, Territory to){
        //control not in my territory
        if(attacker.getPlayerID()!=gamestate.getPlayerTer(to).getPlayerID()){
            //control is neighbour
            if(to.isNeighbour(from.getTerritoryID())){
                return true;
            }
        }

        return false;

    }


    public boolean isPlayerOut(Player player){

        for (Territory territory : gamestate.getTerritoriesPlayersMap().keySet()) {
            if(gamestate.getPlayerTer(territory)==player){
                return false;
            }
        }
        return true;
    }


    //reload the map of territories in gamestate
    public void occupyTerritory(Player current_player,Territory from, Territory to,int units){
        from.setCurrentUnits(from.getCurrentUnits()-units);
        gamestate.setPlayerTer(to, current_player);
        to.setCurrentUnits(units);
    }

    //remove units in a territory
    public void removeUnits(Territory territory,int troops){
        territory.setCurrentUnits(territory.getCurrentUnits() - troops);
    }

    // roll an array of n dice and sort it
    public ArrayList<Integer> rollDice(int n) {
        ArrayList<Integer> dice = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            dice.add((new Random().nextInt(5)) + 1);
        }
        Collections.sort(dice);
        return dice;
    }


    //check if you conquer the territory
    public boolean checkIsConquered(Territory territory,int units){
        if(territory.getCurrentUnits()<=units){
            return true;
        }

        return false;
    }


    //-----------------------------MOVE METHOD------------------------------

    //move units in territories from a to
    public void move(Player current_player, Territory from, Territory to) {
        from.setCurrentUnits(from.getCurrentUnits() - 1);
        to.setCurrentUnits(to.getCurrentUnits() + 1);
    }

    //check to have enough units
    public boolean checkMoveFromUnits(Player current_player,Territory from){
        if(from.getCurrentUnits()>1){
            return true;
        }
        else{
            return false;
        }

    }

    //check if the territory is my
    public boolean checkMoveFrom(Player current_player, Territory from){
        if(current_player.getPlayerID()==gamestate.getPlayerTer(from).getPlayerID()){
            return true;
        }
        else{
            return false;
        }


    }

    //check if the territory is my
    public boolean checkMoveTo(Player current_player, Territory to){
        if(current_player.getPlayerID()==gamestate.getPlayerTer(to).getPlayerID()){
            return true;
        }
        else{
            return false;
        }

    }

    //-----------------------BONUS METHOD----------------------------------------

    public int territoriesBonus(){
        int territoryCount=0;
        int count=0;
        for(Territory territory : territories ){
            if(gamestate.getPlayerTer(territory)==gamestate.getCurrentPlayerTurn()){
                territoryCount++;
            }
        }

        addBonusUnits(territoryCount/3);
        return territoryCount/3;

    }


    public void addBonusUnits(int units){
        gamestate.getCurrentPlayerTurn().setFreeUnits(gamestate.getCurrentPlayerTurn().getFreeUnits() + units);
    }

    public void countryBonus(){
        //to implement
    }


    //-----------------------OTHER METHOD---------------------------------------

    public boolean checkVictory(){
        List<Territory> territories = new ArrayList<>(gamestate.getTerritoriesPlayersMap().keySet());
        int count=0;
        for(Territory territory : territories ){
            if(gamestate.getPlayerTer(territory)==gamestate.getCurrentPlayerTurn()){
                count++;
            }
            if(count>25){
                return true;
            }
        }

        return false;
    }

    public Territory stringToTerritory(String string){
        for(Territory territory : territories){
            if(territory.getTerritoryName().equals(string)){
                return territory;
            }
        }

        return null;
    }












}