import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

public class RollingSimulator {

    public Unit[] shop = new Unit[5];
    public int level; // Decided to make all vars public instead of private because I was too lazy to do getters / setters
    public int oneCostOdds;
    public int twoCostOdds;
    public int threeCostOdds;
    public int fourCostOdds;
    public int fiveCostOdds;
    public ArrayList<Unit> oneCostPool = new ArrayList<Unit>(); // 13*29 - 13 total one-cost units and each of them have 29 copies.
    public ArrayList<Unit> twoCostPool = new ArrayList<Unit>(); // 13*22
    public ArrayList<Unit> threeCostPool = new ArrayList<Unit>(); // 13*18
    public ArrayList<Unit> fourCostPool = new ArrayList<Unit>(); // 12*11
    public ArrayList<Unit> fiveCostPool = new ArrayList<Unit>(); // 8*10
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
        setShopOdds(level);
        setChampPool(level);
    }

    public static void main(String[] args) {
        RollingSimulator sim = new RollingSimulator(7);
        System.out.println("You are rolling at level " + sim.level + " with:");
        System.out.println(sim.oneCostOdds + "% odds for a 1 cost");
        System.out.println(sim.twoCostOdds + "% odds for a 2 cost");
        System.out.println(sim.threeCostOdds + "% odds for a 3 cost");
        System.out.println(sim.fourCostOdds + "% odds for a 4 cost");
        System.out.println(sim.fiveCostOdds + "% odds for a 5 cost"+ "\r\n");

        //ask unit you want in STRING, ask how many you want in INT, with how much gold Y, and keep level in mind
        float Odds = sim.calculateOdds(sim.level, "Corki", 4, 1, 50);
        System.out.println("Your odds of hitting 1 Corki's with 50g at level 7 is " + Odds);
        sim.removeFromPool("Corki", 4);
        sim.removeFromPool("Corki", 4);
        sim.removeFromPool("Corki", 4);
        for (int k = 0; k < 30; k++){
            sim.shop = sim.shopRoll(sim.level);
        }
        sim.shop = sim.shopRoll(sim.level);
        sim.displayShop();

        for (int i = 0; i < sim.fourCostPool.size(); i++) // loop to show champ pool
            System.out.println(sim.fourCostPool.get(i).getName());
    }

    public float calculateOdds(int level, String unit,int cost, int amount, double gold){
        int champsInPool = howMany(cost, unit);
        int poolSize = 0;
        double percent = 0;
        gold = Math.floorDiv((int) gold,2);
        gold = gold * 5;
        int result = 100;
        int costOdds = 0;
        if (cost == 1){
            poolSize = oneCostPool.size();
            costOdds = oneCostOdds;
        } else if (cost == 2) {
            poolSize = twoCostPool.size();
            costOdds = twoCostOdds;
        } else if (cost == 3){
            poolSize = threeCostPool.size();
            costOdds = threeCostOdds;
        } else if (cost == 4){
            poolSize = fourCostPool.size();
            costOdds = fourCostOdds;
        } else if (cost == 5){
            poolSize = fiveCostPool.size();
            costOdds = fiveCostOdds;
        }

        float r1 = divFactorials(champsInPool,amount); // guaranteed correct
        float r2 = divFactorials(poolSize - champsInPool, (int) (gold - amount));
        float r3 = divFactorials(poolSize, (int) gold);
        r1 = r1*r2/r3;

        return r1;
//        for (int i = 0; i < amount; i++){
//            percent = (champsInPool - i) / poolSize / costOdds * 100;
//            result *= Math.pow(percent, (gold*5)-1) * (Math.pow(1 - percent, 1));
//
//            removeFromPool(unit, cost);
//        }
//        int f1 = (int) ((gold*5)-1);
//        int f2 = (int) ((gold*5)-amount);
//        f1 = f1/f2;
//        return f1*result;
        //units left in pool divided by the entire cost pool
        //hypergeometric distribution
        // for loop at the end with amount as the interval
    }

    public int divFactorials (int n, int k) { // per stackoverflow
        int result = 1;
        for (int i = n; i > k; i--) {
        result *= i;
        }
    return Math.abs(result);
    }

    public int howMany(int cost, String unit) {
        int amt = 0;
        if (cost == 1) {
            for (int i = 0; i < oneCostPool.size(); i++) {
                if (oneCostPool.get(i).getName().equals(unit)) {
                    amt++;
                }
            }
        } else if (cost == 2) {
            for (int i = 0; i < twoCostPool.size(); i++) {
                if (twoCostPool.get(i).getName().equals(unit)) {
                    amt++;
                }
            }
        } else if (cost == 3) {
            for (int i = 0; i < threeCostPool.size(); i++) {
                if (threeCostPool.get(i).getName().equals(unit)) {
                    amt++;
                }
            }
        } else if (cost == 4) {
            for (int i = 0; i < fourCostPool.size(); i++) {
                if (fourCostPool.get(i).getName().equals(unit)) {
                    amt++;
                }
            }
        } else if (cost == 5) {
            for (int i = 0; i < fiveCostPool.size(); i++) {
                if (fiveCostPool.get(i).getName().equals(unit)) {
                    amt++;
                }
            }
        }
        return amt;
    }

    public Unit[] shopRoll(int level){
        for (int i = 0; i < 5; i++){
            int unitCost = rollCost(level);
            String unitRoll = rollUnit(unitCost);
            Unit shopUnit = new Unit(unitRoll, unitCost);
            // removeFromPool(unitRoll,unitCost); if my sim accounted for boards this would be uncommented.
            shop[i] = shopUnit;
        }
        return shop;
    }

    public void removeFromPool(String name, int cost){ // Time complexity of n^2, try to remake this to n
        int counter = 0;
        if (cost == 1){
            while (oneCostPool.get(counter).getName() != name){
                counter++;
            }
            oneCostPool.remove(counter);
        } else if (cost == 2){
            while (twoCostPool.get(counter).getName() != name){
                counter++;
            }
            twoCostPool.remove(counter);
        } else if (cost == 3){
            while (threeCostPool.get(counter).getName() != name){
                counter++;
            }
            threeCostPool.remove(counter);
        } else if (cost == 4){
            while (fourCostPool.get(counter).getName() != name){
                counter++;
            }
            fourCostPool.remove(counter);
        } else if (cost == 5){
            while (fiveCostPool.get(counter).getName() != name){
                counter++;
            }
            fiveCostPool.remove(counter);
        }

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
        if (level == 6) {
            int rand = new Random().nextInt(100);
            if (rand >= 0 && rand <= 24) {
                return 1;
            } else if (rand >= 25 && rand <= 64) {
                return 2;
            } else if (rand >= 65 && rand <= 94) {
                return 3;
            } else if (rand >= 95 && rand <= 99) {
                return 4;
            }

        } else if (level == 7) {
            int rand = new Random().nextInt(100);
            if (rand >= 0 && rand <= 18) {
                return 1;
            } else if (rand >= 19 && rand <= 48) {
                return 2;
            } else if (rand >= 49 && rand <= 83) {
                return 3;
            } else if (rand >= 84 && rand <= 98) {
                return 4;
            } else if (rand == 99){
                return 5;
            }

        } else if (level == 8) {
            int rand = new Random().nextInt(100);
            if (rand >= 0 && rand <= 16) {
                return 1;
            } else if (rand >= 17 && rand <= 36) {
                return 2;
            } else if (rand >= 37 && rand <= 70) {
                return 3;
            } else if (rand >= 71 && rand <= 95) {
                return 4;
            } else if (rand >= 96 && rand <= 99){
                return 5;
            }

        } else if (level == 9) {
            int rand = new Random().nextInt(100);
            if (rand >= 0 && rand <= 8) {
                return 1;
            } else if (rand >= 9 && rand <= 23) {
                return 2;
            } else if (rand >= 24 && rand <= 53) {
                return 3;
            } else if (rand >= 54 && rand <= 83) {
                return 4;
            } else if (rand >= 84 && rand <= 99){
                return 5;
            }
        }
        return -1;
    }

    public String rollUnit(int cost){
        String result = "";
        switch (cost) {

            case 1:
                int rand = new Random().nextInt(oneCostNames.length);
                result = oneCostNames[rand];
                break;

            case 2:
                int rand2 = new Random().nextInt(twoCostNames.length);
                result = twoCostNames[rand2];
                break;

            case 3:
                int rand3 = new Random().nextInt(threeCostNames.length);
                result = threeCostNames[rand3];
                break;

            case 4:
                int rand4 = new Random().nextInt(fourCostNames.length);
                result = fourCostNames[rand4];
                System.out.println(result + " rolled.");
                break;

            case 5:
                int rand5 = new Random().nextInt(fiveCostNames.length);
                result = fiveCostNames[rand5];
                break;

        }
        return result;
    }

    public void setChampPool(int level){
        if (level == 6){
            for (int i = 0; i < 13; i++){
                for (int j = 0; j < 29; j++){
                    Unit oneCost = new Unit(oneCostNames[i], 1);
                    oneCostPool.add(oneCost);
                }
            }
            for (int i = 0; i < 13; i++){
                for (int j = 0; j < 22; j++){
                    Unit twoCost = new Unit(twoCostNames[i], 2);
                    twoCostPool.add(twoCost);
                }
            }
            for (int i = 0; i < 13; i++){
                for (int j = 0; j < 18; j++){
                    Unit threeCost = new Unit(threeCostNames[i], 3);
                    threeCostPool.add(threeCost);
                }
            }
            for (int i = 0; i < 11; i++){
                for (int j = 0; j < 12; j++){
                    Unit fourCost = new Unit(fourCostNames[i], 4);
                    fourCostPool.add(fourCost);
                }
            }
        } else {
            for (int i = 0; i < 13; i++){
                for (int j = 0; j < 29; j++){
                    Unit oneCost = new Unit(oneCostNames[i], 1);
                    oneCostPool.add(oneCost);
                }
            }
            for (int i = 0; i < 13; i++){
                for (int j = 0; j < 22; j++){
                    Unit twoCost = new Unit(twoCostNames[i], 2);
                    twoCostPool.add(twoCost);
                }
            }
            for (int i = 0; i < 13; i++){
                for (int j = 0; j < 18; j++){
                    Unit threeCost = new Unit(threeCostNames[i], 3);
                    threeCostPool.add(threeCost);
                }
            }
            for (int i = 0; i < 11; i++){
                for (int j = 0; j < 12; j++){
                    Unit fourCost = new Unit(fourCostNames[i], 4);
                    fourCostPool.add(fourCost);
                }
            }
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 10; j++){
                    Unit fiveCost = new Unit(fiveCostNames[i], 5);
                    fiveCostPool.add(fiveCost);
                }
            }
        }
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
