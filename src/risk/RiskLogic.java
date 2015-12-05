package risk;

import risk.operations.*;
import risk.board.Territory;
import risk.operations.Error;
import risk.player.Player;
import risk.GameResources;
import risk.GameState;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by Federico on 22/11/2015.
 */

public class RiskLogic {

    int firstTurn;
    GameState gameState;
    List<Territory> territories;
    Territory territory;

    public RiskLogic(GameState gameState){
        firstTurn = 0;
        territory = null;
        this.gameState = gameState;
        territories = new ArrayList<Territory>(gameState.getTerritoriesPlayersMap().keySet());
    }




    public Operation makeMove(String string){
        territory = stringToTerritory(string);
        //Fortify phase
        if(gameState.getPhase() == Phases.FORTIFY || gameState.getPhase() == Phases.INITIAL ){

            if(checkFortifyUnits(gameState.getCurrentPlayerTurn())) {
                if (checkFortify(gameState.getCurrentPlayerTurn(), territory)) {
                    fortify(gameState.getCurrentPlayerTurn(), territory);
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
        if(gameState.getPhase() == Phases.ATTACK){

            if(gameState.getAttackFrom() == null){

                if(checkAttackFrom(gameState.getCurrentPlayerTurn(), territory)){

                    if(checkAttackFromUnits(gameState.getCurrentPlayerTurn(), territory)){
                        gameState.setAttackFrom(territory);
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

                if(gameState.getAttackFrom() == territory){

                    gameState.setAttackFrom(null);
                    return new TerritoryUnselected(territory);
                }
                else{

                    if(checkAttackTo(gameState.currentPlayerTurn,gameState.getAttackFrom(),territory)){
                        Territory attackFrom = gameState.getAttackFrom();
                        gameState.setAttackFrom(null);
                        if(attack(gameState.currentPlayerTurn,attackFrom,territory)){
                            return new AttackConquest(attackFrom,territory,gameState.getCurrentPlayerTurn().getPlayerColor());
                        }
                        return new Attack(attackFrom,territory);
                    }
                    else{
                        return new Error(territory.getTerritoryName() + " not in neighbours");
                    }

                }

            }


        }


        //Move phase
        if(gameState.getPhase() == Phases.MOVE){

            if (gameState.getMoveFrom() == null) {

                if (checkMoveFrom(gameState.getCurrentPlayerTurn(), territory)) {
                    if (checkMoveFromUnits(gameState.getCurrentPlayerTurn(), territory)) {
                        gameState.setMoveFrom(territory);
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

                if (checkMoveTo(gameState.getCurrentPlayerTurn(), territory)) {
                    //unselect
                    if (territory == gameState.getMoveFrom()) {
                        gameState.setMoveFrom(null);
                        return new TerritoryUnselected(territory);
                    } else {
                        Territory moveFrom = gameState.getMoveFrom();
                        gameState.setMoveFrom(null);
                        move(gameState.getCurrentPlayerTurn(), moveFrom, territory);
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
        gameState.setLastphase(gameState.getPhase());
        List<Operation> operations = new ArrayList<Operation>();

        if(gameState.getPhase() == Phases.INITIAL){
            gameState.nextPhase();
            operations.add(new NewPhase(Phases.END_TURN));
            firstTurn++;
            return operations;

        }
        if(gameState.getPhase() == Phases.BONUS){
            int units;
            units = territoriesBonus();
            gameState.nextPhase();
            operations.add(new UnitsBonus(units));
            operations.add(new NewPhase(Phases.FORTIFY));
            return operations;

        }

        if(gameState.getPhase() == Phases.FORTIFY){
            gameState.nextPhase();
            operations.add(new NewPhase(Phases.ATTACK));
            return operations;

        }
        else if((gameState.getPhase() == Phases.ATTACK)){
            gameState.nextPhase();
            if(gameState.getAttackFrom() != null){
                operations.add(new TerritoryUnselected(gameState.getAttackFrom()));
                gameState.setAttackFrom(null);
            }
            operations.add(new NewPhase(Phases.MOVE));
            return operations;

        }
        else if(gameState.getPhase() == Phases.MOVE){
            gameState.nextPhase();
            if(gameState.getMoveFrom() != null){
                operations.add(new TerritoryUnselected(gameState.getMoveFrom()));
                gameState.setMoveFrom(null);
            }
            operations.add(new NewPhase(Phases.END_TURN));
            return operations;

        }
        else if(gameState.getPhase() == Phases.END_TURN){


            if(checkVictory()){
                operations.add(new Victory(gameState.getCurrentPlayerTurn()));
                return operations;
            }
            else if(firstTurn >= gameState.getPlayers().size()){
                System.out.println(firstTurn + " " + gameState.getPlayers().size());
                gameState.nextPlayer();
                gameState.setPhase(Phases.BONUS);
                operations.add(new NewPhase(Phases.BONUS));
                return operations;

            }
            else{
                gameState.nextPlayer();
                gameState.nextPhase();
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
        if(current_player.getPlayerID()==gameState.getPlayerTer(territory).getPlayerID()){
            return true;
        }
        else{
            return false;
        }


    }

    //---------------------------------ATTACK METHOD---------------------------------------


    public boolean attack(Player attacker, Territory from, Territory to) {
        int attackdice, defencedice,removeatt,removedefe;
        attackdice = GameResources.getMaxDiceRollsForAttacker(from.getCurrentUnits());
        defencedice = GameResources.getMaxDiceRollsForDefender(to.getCurrentUnits());
        ArrayList<Integer> attackdices = this.rollDice(attackdice);
        ArrayList<Integer> defecedices = this.rollDice(defencedice);
        //use the dices to save the number of victory
        removeatt = 0;
        removedefe = 0;

        for (int i = 0; i < defecedices.size(); i++) {
            System.out.println(attackdices.get(i) + " " +defecedices.get(i));
            if(attackdices.get(i)>defecedices.get(i)){
                removedefe++;
            }
            else{
                removeatt++;
            }

        }
        //remove troops in attacker territory
        removeUnits(from, removeatt);


        if(checkIsConquered(to,attackdice)){
            Player player = null;
            player = gameState.getPlayerTer(to);

            if(isPlayerOut(player)){
                gameState.elimiatePlayer(player);
            }
            occupyTerritory(attacker,from,to,attackdice-removeatt);
            return true;


        }
        else{
            removeUnits(to, removedefe);
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
        if(attacker.getPlayerID()==gameState.getPlayerTer(from).getPlayerID()){

            return true;
        }
        else{
            return false;
        }

    }


    public boolean checkAttackTo(Player attacker,Territory from, Territory to){
        //control not in my territory
        if(attacker.getPlayerID()!=gameState.getPlayerTer(to).getPlayerID()){
            //control is neighbour
            if(to.isNeighbour(from.getTerritoryID())){
                return true;
            }
        }

        return false;

    }


    public boolean isPlayerOut(Player player){

        for (Territory territory : gameState.getTerritoriesPlayersMap().keySet()) {
            if(gameState.getPlayerTer(territory)==player){
                return false;
            }
        }
        return true;
    }


    //reload the map of territories in gameState
    public void occupyTerritory(Player current_player,Territory from, Territory to,int units){
        from.setCurrentUnits(from.getCurrentUnits()-units);
        gameState.setPlayerTer(to, current_player);
        to.setCurrentUnits(units);
        System.out.println(current_player.getPlayerName() + " "+ to.getTerritoryName() +" " +to.getCurrentUnits());

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
        Collections.sort(dice,Collections.reverseOrder());
        for(Integer inte : dice){
            System.out.println(inte+" ");
        }
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
        if(current_player.getPlayerID()==gameState.getPlayerTer(from).getPlayerID()){
            return true;
        }
        else{
            return false;
        }


    }

    //check if the territory is my
    public boolean checkMoveTo(Player current_player, Territory to){
        if(current_player.getPlayerID()==gameState.getPlayerTer(to).getPlayerID()){
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
            if(gameState.getPlayerTer(territory)==gameState.getCurrentPlayerTurn()){
                territoryCount++;
            }
        }

        addBonusUnits(territoryCount/3);
        return territoryCount/3;

    }


    public void addBonusUnits(int units){
        gameState.getCurrentPlayerTurn().setFreeUnits(gameState.getCurrentPlayerTurn().getFreeUnits() + units);
    }

    public void countryBonus(){
        //to implement
    }


    //-----------------------OTHER METHOD---------------------------------------

    public boolean checkVictory(){
        List<Territory> territories = new ArrayList<>(gameState.getTerritoriesPlayersMap().keySet());
        int count=0;
        for(Territory territory : territories ){
            if(gameState.getPlayerTer(territory)==gameState.getCurrentPlayerTurn()){
                count++;
            }
            if(count>22){
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

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}