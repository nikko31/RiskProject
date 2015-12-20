package risk;

import java.awt.*;
import java.util.*;
import java.util.List;


public class GameResources {

    public enum cardType {INFANTRY, CAVALRY, ARTILLERY, WILDCARD}


    public static final Map<Integer, Color> MISSION_DESTROY = new HashMap<>();
        static{
            MISSION_DESTROY.put(0,Color.RED);
            MISSION_DESTROY.put(1,Color.cyan);
            MISSION_DESTROY.put(2,Color.decode("#FF33FF"));
            MISSION_DESTROY.put(3,Color.decode("#00FF00"));
            MISSION_DESTROY.put(4,Color.decode("#0000FF"));
            MISSION_DESTROY.put(5,Color.yellow);
        }

    public static final Map<Integer, ArrayList<Integer>> MISSION_CONTINENT =  new HashMap<>();
        static{
            MISSION_CONTINENT.put(0,new ArrayList<>(Arrays.asList(4, 1)));
            MISSION_CONTINENT.put(1,new ArrayList<>(Arrays.asList(0, 5)));
            MISSION_CONTINENT.put(2,new ArrayList<>(Arrays.asList(4, 3)));
            MISSION_CONTINENT.put(3,new ArrayList<>(Arrays.asList(0, 3)));
            MISSION_CONTINENT.put(4,new ArrayList<>(Arrays.asList(2, 1)));
            MISSION_CONTINENT.put(5,new ArrayList<>(Arrays.asList(2, 5)));
        }

    /*

    public static final Map<Integer, ArrayList<Integer>> MISSION_CONTINENT_CHOICE = new HashMap<>();
        static{
            MISSION_CONTINENT_CHOICE.put(10, new ArrayList<>(Arrays.asList(2, 1,10)));
            MISSION_CONTINENT_CHOICE.put(11, new ArrayList<>(Arrays.asList(2, 5,10)));
        }
        */

    public static final Map<Integer, Integer> MISSION_TERRITORY = new HashMap<>();
        static{
        MISSION_TERRITORY.put(18,2);
        MISSION_TERRITORY.put(24,1);
        }


    public static final Map<Integer, String> CARD_ID_STRING = new HashMap<>();

        static{

            CARD_ID_STRING.put(0,"I");
            CARD_ID_STRING.put(1,"C");
            CARD_ID_STRING.put(2,"A");
            CARD_ID_STRING.put(3,"I");
            CARD_ID_STRING.put(4,"C");
            CARD_ID_STRING.put(5,"A");
            CARD_ID_STRING.put(6,"I");
            CARD_ID_STRING.put(7,"C");
            CARD_ID_STRING.put(8,"A");
            CARD_ID_STRING.put(9,"I");
            CARD_ID_STRING.put(10,"C");
            CARD_ID_STRING.put(11,"A");
            CARD_ID_STRING.put(12,"I");
            CARD_ID_STRING.put(13,"C");
            CARD_ID_STRING.put(14,"A");
            CARD_ID_STRING.put(15,"I");
            CARD_ID_STRING.put(16,"C");
            CARD_ID_STRING.put(17,"A");
            CARD_ID_STRING.put(18,"I");
            CARD_ID_STRING.put(19,"C");
            CARD_ID_STRING.put(20,"A");
            CARD_ID_STRING.put(21,"I");
            CARD_ID_STRING.put(22,"C");
            CARD_ID_STRING.put(23,"A");
            CARD_ID_STRING.put(24,"I");
            CARD_ID_STRING.put(25,"C");
            CARD_ID_STRING.put(26,"A");
            CARD_ID_STRING.put(27,"I");
            CARD_ID_STRING.put(28,"C");
            CARD_ID_STRING.put(29,"A");
            CARD_ID_STRING.put(30,"I");
            CARD_ID_STRING.put(31,"C");
            CARD_ID_STRING.put(32,"A");
            CARD_ID_STRING.put(33,"I");
            CARD_ID_STRING.put(34,"C");
            CARD_ID_STRING.put(35,"A");
            CARD_ID_STRING.put(36,"I");
            CARD_ID_STRING.put(37,"C");
            CARD_ID_STRING.put(38,"A");
            CARD_ID_STRING.put(39,"I");
            CARD_ID_STRING.put(40,"C");
            CARD_ID_STRING.put(41,"A");
            CARD_ID_STRING.put(42,"I");
            CARD_ID_STRING.put(43,"W");
            CARD_ID_STRING.put(44,"W");
        }

    public static final Map<Integer, String> TERRITORY_NAME = new HashMap<>();

        static {
            TERRITORY_NAME.put(0, "Alaska");
            TERRITORY_NAME.put(1, "North West Territory");
            TERRITORY_NAME.put(2, "Alberta");
            TERRITORY_NAME.put(3, "Western United States");
            TERRITORY_NAME.put(4, "Central America");
            TERRITORY_NAME.put(5, "Greenland");
            TERRITORY_NAME.put(6, "Ontario");
            TERRITORY_NAME.put(7, "Quebec");
            TERRITORY_NAME.put(8, "Eastern United States");
            TERRITORY_NAME.put(9, "Venezuela");
            TERRITORY_NAME.put(10, "Peru");
            TERRITORY_NAME.put(11, "Brazil");
            TERRITORY_NAME.put(12, "Argentina");
            TERRITORY_NAME.put(13, "Iceland");
            TERRITORY_NAME.put(14, "Scandinavia");
            TERRITORY_NAME.put(15, "Ukraine");
            TERRITORY_NAME.put(16, "Great Britain");
            TERRITORY_NAME.put(17, "Northern Europe");
            TERRITORY_NAME.put(18, "Western Europe");
            TERRITORY_NAME.put(19, "Southern Europe");
            TERRITORY_NAME.put(20, "North Africa");
            TERRITORY_NAME.put(21, "Egypt");
            TERRITORY_NAME.put(22, "Congo");
            TERRITORY_NAME.put(23, "East Africa");
            TERRITORY_NAME.put(24, "South Africa");
            TERRITORY_NAME.put(25, "Madagascar");
            TERRITORY_NAME.put(26, "Siberia");
            TERRITORY_NAME.put(27, "Ural");
            TERRITORY_NAME.put(28, "China");
            TERRITORY_NAME.put(29, "Afghanistan");
            TERRITORY_NAME.put(30, "Middle East");
            TERRITORY_NAME.put(31, "India");
            TERRITORY_NAME.put(32, "Siam");
            TERRITORY_NAME.put(33, "Yakutsk");
            TERRITORY_NAME.put(34, "Irkutsk");
            TERRITORY_NAME.put(35, "Mongolia");
            TERRITORY_NAME.put(36, "Japan");
            TERRITORY_NAME.put(37, "Kamchatka");
            TERRITORY_NAME.put(38, "Indonesia");
            TERRITORY_NAME.put(39, "New Guinea");
            TERRITORY_NAME.put(40, "Western Australia");
            TERRITORY_NAME.put(41, "Eastern Australia");
        }

    public static final Map<String, Integer> SVG_ID_MAP = new HashMap<>();

        static {
            SVG_ID_MAP.put("alaska", 0);
            SVG_ID_MAP.put("northwest_territory", 1);
            SVG_ID_MAP.put("alberta", 2);
            SVG_ID_MAP.put("western_united_states", 3);
            SVG_ID_MAP.put("central_america", 4);
            SVG_ID_MAP.put("greenland", 5);
            SVG_ID_MAP.put("ontario", 6);
            SVG_ID_MAP.put("quebec", 7);
            SVG_ID_MAP.put("eastern_united_states", 8);
            SVG_ID_MAP.put("venezuela", 9);
            SVG_ID_MAP.put("peru", 10);
            SVG_ID_MAP.put("brazil", 11);
            SVG_ID_MAP.put("argentina", 12);
            SVG_ID_MAP.put("iceland", 13);
            SVG_ID_MAP.put("scandinavia", 14);
            SVG_ID_MAP.put("ukraine", 15);
            SVG_ID_MAP.put("great_britain", 16);
            SVG_ID_MAP.put("northern_europe", 17);
            SVG_ID_MAP.put("western_europe", 18);
            SVG_ID_MAP.put("southern_europe", 19);
            SVG_ID_MAP.put("north_africa", 20);
            SVG_ID_MAP.put("egypt", 21);
            SVG_ID_MAP.put("congo", 22);
            SVG_ID_MAP.put("east_africa", 23);
            SVG_ID_MAP.put("south_africa", 24);
            SVG_ID_MAP.put("madagascar", 25);
            SVG_ID_MAP.put("siberia", 26);
            SVG_ID_MAP.put("ural", 27);
            SVG_ID_MAP.put("china", 28);
            SVG_ID_MAP.put("afghanistan", 29);
            SVG_ID_MAP.put("middle_east", 30);
            SVG_ID_MAP.put("india", 31);
            SVG_ID_MAP.put("siam", 32);
            SVG_ID_MAP.put("yakutsk", 33);
            SVG_ID_MAP.put("irkutsk", 34);
            SVG_ID_MAP.put("mongolia", 35);
            SVG_ID_MAP.put("japan", 36);
            SVG_ID_MAP.put("kamchatka", 37);
            SVG_ID_MAP.put("indonesia", 38);
            SVG_ID_MAP.put("new_guinea", 39);
            SVG_ID_MAP.put("western_australia", 40);
            SVG_ID_MAP.put("eastern_australia", 41);
        }

    public static final Map<Integer, String> SVG_NAME_MAP = new HashMap<>();

        static {
            SVG_NAME_MAP.put(0, "alaska");
            SVG_NAME_MAP.put(1, "northwest_territory");
            SVG_NAME_MAP.put(2, "alberta");
            SVG_NAME_MAP.put(3, "western_united_states");
            SVG_NAME_MAP.put(4, "central_america");
            SVG_NAME_MAP.put(5, "greenland");
            SVG_NAME_MAP.put(6, "ontario");
            SVG_NAME_MAP.put(7, "quebec");
            SVG_NAME_MAP.put(8, "eastern_united_states");
            SVG_NAME_MAP.put(9, "venezuela");
            SVG_NAME_MAP.put(10, "peru");
            SVG_NAME_MAP.put(11, "brazil");
            SVG_NAME_MAP.put(12, "argentina");
            SVG_NAME_MAP.put(13, "iceland");
            SVG_NAME_MAP.put(14, "scandinavia");
            SVG_NAME_MAP.put(15, "ukraine");
            SVG_NAME_MAP.put(16, "great_britain");
            SVG_NAME_MAP.put(17, "northern_europe");
            SVG_NAME_MAP.put(18, "western_europe");
            SVG_NAME_MAP.put(19, "southern_europe");
            SVG_NAME_MAP.put(20, "north_africa");
            SVG_NAME_MAP.put(21, "egypt");
            SVG_NAME_MAP.put(22, "congo");
            SVG_NAME_MAP.put(23, "east_africa");
            SVG_NAME_MAP.put(24, "south_africa");
            SVG_NAME_MAP.put(25, "madagascar");
            SVG_NAME_MAP.put(26, "siberia");
            SVG_NAME_MAP.put(27, "ural");
            SVG_NAME_MAP.put(28, "china");
            SVG_NAME_MAP.put(29, "afghanistan");
            SVG_NAME_MAP.put(30, "middle_east");
            SVG_NAME_MAP.put(31, "india");
            SVG_NAME_MAP.put(32, "siam");
            SVG_NAME_MAP.put(33, "yakutsk");
            SVG_NAME_MAP.put(34, "irkutsk");
            SVG_NAME_MAP.put(35, "mongolia");
            SVG_NAME_MAP.put(36, "japan");
            SVG_NAME_MAP.put(37, "kamchatka");
            SVG_NAME_MAP.put(38, "indonesia");
            SVG_NAME_MAP.put(39, "new_guinea");
            SVG_NAME_MAP.put(40, "western_australia");
            SVG_NAME_MAP.put(41, "eastern_australia");
        }

    public static final Map<Integer, List<Integer>> CONNECTIONS = new HashMap<>();

        static {
            CONNECTIONS.put(0, new ArrayList<>(Arrays.asList(1, 2, 37)));
            CONNECTIONS.put(1, new ArrayList<>(Arrays.asList(0, 2, 6, 5)));
            CONNECTIONS.put(2, new ArrayList<>(Arrays.asList(0, 1, 6, 3)));
            CONNECTIONS.put(3, new ArrayList<>(Arrays.asList(2, 6, 4, 8)));
            CONNECTIONS.put(4, new ArrayList<>(Arrays.asList(3, 8, 9)));
            CONNECTIONS.put(5, new ArrayList<>(Arrays.asList(1, 6, 7, 13)));
            CONNECTIONS.put(6, new ArrayList<>(Arrays.asList(1, 2, 3, 5, 7, 8)));
            CONNECTIONS.put(7, new ArrayList<>(Arrays.asList(5, 6, 8)));
            CONNECTIONS.put(8, new ArrayList<>(Arrays.asList(3, 4, 6, 7)));
            CONNECTIONS.put(9, new ArrayList<>(Arrays.asList(4, 10, 11)));
            CONNECTIONS.put(10, new ArrayList<>(Arrays.asList(9, 11, 12)));
            CONNECTIONS.put(11, new ArrayList<>(Arrays.asList(9, 10, 12, 20)));
            CONNECTIONS.put(12, new ArrayList<>(Arrays.asList(10, 11)));
            CONNECTIONS.put(13, new ArrayList<>(Arrays.asList(5, 16, 14)));
            CONNECTIONS.put(14, new ArrayList<>(Arrays.asList(15, 17, 13, 16)));
            CONNECTIONS.put(15, new ArrayList<>(Arrays.asList(14, 27, 29, 30, 17, 19)));
            CONNECTIONS.put(16, new ArrayList<>(Arrays.asList(13, 17, 18, 14)));
            CONNECTIONS.put(17, new ArrayList<>(Arrays.asList(14, 15, 16, 18, 19)));
            CONNECTIONS.put(18, new ArrayList<>(Arrays.asList(16, 17, 19, 20)));
            CONNECTIONS.put(19, new ArrayList<>(Arrays.asList(15, 17, 18, 20, 21, 30)));
            CONNECTIONS.put(20, new ArrayList<>(Arrays.asList(11, 18, 19, 21, 22, 23)));
            CONNECTIONS.put(21, new ArrayList<>(Arrays.asList(19, 20, 23, 30)));
            CONNECTIONS.put(22, new ArrayList<>(Arrays.asList(20, 23, 24)));
            CONNECTIONS.put(23, new ArrayList<>(Arrays.asList(20, 21, 22, 24, 25, 30)));
            CONNECTIONS.put(24, new ArrayList<>(Arrays.asList(22, 23, 25)));
            CONNECTIONS.put(25, new ArrayList<>(Arrays.asList(23, 24)));
            CONNECTIONS.put(26, new ArrayList<>(Arrays.asList(27, 28, 33, 34, 35)));
            CONNECTIONS.put(27, new ArrayList<>(Arrays.asList(15, 26, 28, 29)));
            CONNECTIONS.put(28, new ArrayList<>(Arrays.asList(26, 27, 29, 31, 32, 35)));
            CONNECTIONS.put(29, new ArrayList<>(Arrays.asList(15, 27, 28, 30, 31)));
            CONNECTIONS.put(30, new ArrayList<>(Arrays.asList(15, 21, 29, 31, 19, 23)));
            CONNECTIONS.put(31, new ArrayList<>(Arrays.asList(28, 29, 30, 32)));
            CONNECTIONS.put(32, new ArrayList<>(Arrays.asList(28, 31, 38)));
            CONNECTIONS.put(33, new ArrayList<>(Arrays.asList(26, 34, 37)));
            CONNECTIONS.put(34, new ArrayList<>(Arrays.asList(26, 33, 35, 37)));
            CONNECTIONS.put(35, new ArrayList<>(Arrays.asList(28, 34, 36, 37, 26)));
            CONNECTIONS.put(36, new ArrayList<>(Arrays.asList(35, 37)));
            CONNECTIONS.put(37, new ArrayList<>(Arrays.asList(0, 33, 34, 35, 36)));
            CONNECTIONS.put(38, new ArrayList<>(Arrays.asList(32, 40, 39)));
            CONNECTIONS.put(39, new ArrayList<>(Arrays.asList(38, 40, 41)));
            CONNECTIONS.put(40, new ArrayList<>(Arrays.asList(38, 39, 41)));
            CONNECTIONS.put(41, new ArrayList<>(Arrays.asList(39, 40)));
        }

        public static int getMaxDiceRollsForAttacker(int units) {
            if (units < 2) {
                return 0;
            }
            return (units - 1) >= 3 ? 3 : (units - 1);
        }

        public static int getMaxDiceRollsForDefender(int units) {
            if (units < 1) {
                return 0;
            }
            return units >= 3 ? 3 : units;
        }

        public static int getStarterUnitsNumber(int nPlayers) {
            switch (nPlayers) {
                case 2:
                    return 40;
                case 3:
                    return 35;
                case 4:
                    return 30;
                case 5:
                    return 25;
                case 6:
                    return 20;
                default:
                    return 0;
            }

        }


    }
