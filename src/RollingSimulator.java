import java.util.Random;

public class RollingSimulator {
    /*
    Based on Set 7!
    Hello! This is the source code for my TFT rolling simulator!
    The goal of this is to find out the odds of finding a specific unit.
    This is mostly for figuring out rolling on 7 or 8 and seeing the best outcome in case you're contested.
    Levels 6-9 will have the code for them but not 1-5 as there are no scenarios where they're needed.

    For some general knowledge, the champion pool is as stands:
    1 Cost: 29
    2 Cost: 22
    3 Cost: 18
    4 Cost: 12
    5 Cost: 10

    The total amount of units per cost:
    1 Cost: 13
    2 Cost: 13
    3 Cost: 13
    4 Cost: 11
    5 Cost: 8

    For those who do not play TFT, each shop has 5 units in them. The champion pool means that
    there are only a specific amount of that one champion per its cost. The two main factors
    in determining what is inside your shop. Your level, which increases the odds of higher cost
    champions appearing and the other champions in and out of the pool

     */
    public Unit s1 = new Unit("Karma", 1,2);
    public Unit s2 = new Unit("Karma", 1,2);
    public Unit s3 = new Unit("Karma", 1,2);
    public Unit s4 = new Unit("Karma", 1,2);
    public Unit s5 = new Unit("Karma", 1,2);
    public Unit[] shop = new Unit[] {s1,s2,s3,s4,s5};
    public Unit[] shop2 = new Unit[5];
    public int level; // Decided to make all vars public instead of private because I was too lazy to do getters / setters
    public int oneCostOdds;
    public int twoCostOdds;
    public int threeCostOdds;
    public int fourCostOdds;
    public int fiveCostOdds;
    public int oneCostPool = 13*29; // 13 total one-cost units and each of them have 29 copies.
    public int twoCostPool = 13*22;
    public int threeCostPool = 13*18;
    public int fourCostPool = 12*11;
    public int fiveCostPool = 5*8;
    public String[] oneCostNames = new String[]{"Aatrox", "Ezreal","Heimerdinger", "Karma", "Leona",
                                                "Nidalee", "Sejuani", "Senna", "Sett", "Skarner",
                                                "Tahm Kench", "Taric", "Vladimir"};
    public String[] twoCostNames = new String[]{"Ashe", "Braum","Gnar", "Jinx", "Kayn",
                                                "Lillia", "Nami", "Qiyana", "Shen", "Thresh",
                                                "Tristana", "Twitch", "Yone"};
    public String[] threeCostNames = new String[]{"Anivia", "Diana","Elise", "Illaoi", "Lee Sin",
                                                "Lulu", "Nunu", "Olaf", "Ryze", "Swain",
                                                "Sylas", "Varus", "Volibear"};
    public String[] fourCostNames = new String[]{"Corki", "Daeja","Hecarim", "Idas", "Neeko",
                                                "Ornn", "Shi Oh Yu", "Sona", "Sy'fen", "Talon","Xayah"};
    public String[] fiveCostNames = new String[]{"Ao Shin", "Aurelion Sol","Bard", "Pyke", "Shyvana",
                                                "Soraka", "Yasuo", "Zoe"};

    public RollingSimulator(int level){
        this.level = level;
    }
    public static void main(String[] args) {
        // ADD SCANNER INPUT HERE TO ASK FOR LEVELS 6-9
        // ADD CHECKER TO MAKE SURE THEY GET IT CORRECT
        RollingSimulator sim = new RollingSimulator(9);
        sim.setShopOdds(sim.level);
        System.out.println("You are rolling at level " + sim.level + " with:");
        System.out.println(sim.oneCostOdds + "% odds for a 1 cost");
        System.out.println(sim.twoCostOdds + "% odds for a 2 cost");
        System.out.println(sim.threeCostOdds + "% odds for a 3 cost");
        System.out.println(sim.fourCostOdds + "% odds for a 4 cost");
        System.out.println(sim.fiveCostOdds + "% odds for a 5 cost");
        int x = sim.rollUnit(1);
        System.out.println(sim.oneCostNames[x]);
        int x2 = sim.rollUnit(2);
        System.out.println(sim.twoCostNames[x2]);
        int x3 = sim.rollUnit(3);
        System.out.println(sim.threeCostNames[x3]);
        int x4 = sim.rollUnit(4);
        System.out.println(sim.fourCostNames[x4]);





        sim.displayShop();
    }

    public void setShopOdds(int level) {
        switch (level) {

            case 6:
                this.oneCostOdds = 25;
                this.twoCostOdds = 40;
                this.threeCostOdds = 30;
                this.fourCostOdds = 5;
                this.fiveCostOdds = 0;
                break;

            case 7:
                this.oneCostOdds = 19;
                this.twoCostOdds = 30;
                this.threeCostOdds = 35;
                this.fourCostOdds = 15;
                this.fiveCostOdds = 1;
                break;

            case 8:
                this.oneCostOdds = 16;
                this.twoCostOdds = 20;
                this.threeCostOdds = 35;
                this.fourCostOdds = 25;
                this.fiveCostOdds = 4;
                break;

            case 9:
                this.oneCostOdds = 9;
                this.twoCostOdds = 15;
                this.threeCostOdds = 30;
                this.fourCostOdds = 30;
                this.fiveCostOdds = 16;
                break;
        }
    }

    public int rollCost(int level){
        // RNG pick number from 0 to 99.
        return -1;
    }

    public int rollUnit(int cost){
        int result = 0;
        switch (cost) {

            case 1:
                int rand = new Random().nextInt(oneCostNames.length);
                result = rand;
                break;

            case 2:
                int rand2 = new Random().nextInt(twoCostNames.length);
                result = rand2;
                break;

            case 3:
                int rand3 = new Random().nextInt(threeCostNames.length);
                result = rand3;
                break;

            case 4:
                int rand4 = new Random().nextInt(fourCostNames.length);
                result = rand4;
                break;

            case 5:
                int rand5 = new Random().nextInt(fiveCostNames.length);
                result = rand5;
                break;

        }
        return result;
    }

    public void displayShop(){
        String shopString = "";
        for (int i = 0; i < 4; i++){
            shopString += shop[i].name + " | ";
        }
        shopString += shop[4].name;
        System.out.println(shopString);
    }
}
