package risk.board;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by FEDE on 08/12/2015.
 */
public class Mission {
    private int missionId;
    private int kind=0;
    private int terrTocon;
    private Color playerToDef;
    private ArrayList<Integer> contiToCon;

    public Mission(int missionId,int terrTocon){
        this.missionId = missionId;
        this.terrTocon = terrTocon;
        this.kind = 2;
        this.playerToDef = null;
        this.contiToCon = null;
    }
    public Mission(int missionId,ArrayList<Integer> contiToCon){
        this.missionId = missionId;
        this.contiToCon =contiToCon;
        this.kind = 1;
        this.playerToDef = null;
        this.terrTocon = 0;
    }
    public Mission(int missionId,Color playerToDef){
        this.missionId = missionId;
        this.playerToDef =playerToDef;
        this.kind = 0;
        this.contiToCon = null;
        this.contiToCon = null;
    }



    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public int getTerrTocon() {
        return terrTocon;
    }

    public void setTerrTocon(int terrTocon) {
        this.terrTocon = terrTocon;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public Color getPlayerToDef() {
        return playerToDef;
    }

    public void setPlayerToDef(Color playerToDef) {
        this.playerToDef = playerToDef;
    }

    public ArrayList<Integer> getContiToCon() {
        return contiToCon;
    }



    public void setContiToCon(ArrayList<Integer> contiToCon) {
        this.contiToCon = contiToCon;
    }

    public String toString(){
        switch(kind){
            case 0:{
                return("you have to destroy the" + this.getPlayerToDef().toString() +" player" );
            }
            case 1:{
                return("you have to conquest country");
            }
            case 2:{
                return ("you have to conquest territory");
            }
            default :return "MISSING MISSION";
        }
    }
}
