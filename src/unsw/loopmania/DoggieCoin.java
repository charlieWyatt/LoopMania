package unsw.loopmania;

import java.util.Random;

//Implemented using the Singlton Pattern to ensure there is only ever one Doggie Coin Object

public class DoggieCoin {

    private static DoggieCoin doggieCoin = new DoggieCoin();

    private double stdDev = 0.01;
    private double price = 1000;
    private int price_floor = 2;
    private double mean = 1; // mean increse per tick
    private int numHeld = 0;
 
    private DoggieCoin(){}
 
    /**
     * Can only return this instance of the object
     */
    public static DoggieCoin getDoggieCoin(){
       return doggieCoin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double setPrice) {
        price = setPrice;
    }

    public int getNumHeld() {
        return numHeld;
    }

    public void addDoggieCoins(int amount) {
        numHeld += amount;
    }

    public double getMean() {
        return mean;
    }

    public double getStdDev() {
        return stdDev;
    }

    public void setStdDev(double newStdDev) {
        stdDev = newStdDev;
    }

    public void setMean(double newMean) {
        mean = newMean;
    }

    /**
     * Resets the standard deviation and the mean to their default values.
     */
    public void setDefault() {
        stdDev = 0.01;
        mean = 1;
    }

    /**
     * Randomly fluctuates the price, by a normal distributed amount with a mean of 1.
     * @precondition price >= price_floor
     * @postcondiiton price >= price_floor
     */
    public void priceFluctuate() {
        Random normDist = new Random();
        // gives value from -inf to inf
        double effect = normDist.nextGaussian();

        price *= Math.exp(effect*stdDev) * mean;
        if(price < price_floor) {
            price = price_floor;
        }
    }
 }
