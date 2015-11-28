package risk.operations;

import risk.board.Territory;

/**
 * Created by Federico on 28/11/2015.
 */
public class Fortify implements Operation {

        public Territory fortify;

        public Fortify(Territory fortify) {
            this.fortify = fortify;
        }

        public Territory getFortify() {
            return fortify;
        }

        @Override
        public String operationString() {
            return "FORTIFY: " + this.fortify.getTerritoryName();
        }
}
