package risk.board;

import risk.GameMaster;

import java.util.ArrayList;

/**
 * Created by nikko31 on 11/11/15.
 */
public class Board {
    String name;
    ArrayList<Continent> continents;
    ArrayList<Territory> territories;

    public static Board generate_empty_board() {
    }

    public void dev_random_assign_owners(GameMaster game_master) {

    }

    public int getNumContinents(){
        return continents.size();
    }

    public int getNumTerritories(){
        return territories.size();
    }

    public int calcualte_player_territory_armies(int uid){
        int territory_count=0;
        for(Territory t: territories){
            if(t.getOwner()==uid){
                territory_count++;
            }
        }
        int armies=territory_count/3;

        if(armies<3){
            return 3;
        }

        return armies;
    }


    public int calculate_player_continent_armies(int uid){
        int armies=0;
        for(Continent c: continents){
            Boolean owned=true;
            for(Territory t: c.getTerritories()){
                if(t.getOwner()!=uid){
                    owned=false;
                }
            }
            if(owned){
                armies+=c.getBonusArmies();
            }
        }
        return armies;
    }
}
