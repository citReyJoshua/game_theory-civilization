/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civilization;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author shiro
 */
public class Player {
    final private int[] plays;
    
    private double earnings = 0;
    final public static int DONATE = 1;
    final public static int BOYCOTT = 0;
    final private static Random random = new Random();
    
    public Player(int turns){
        this.plays = new int[turns];
        for (int i = 0; i < turns; i++)
            this.plays[i] = Player.BOYCOTT;
    }
    
    public int getDonationCount(){
        int count = 0;
        for (int i = 0; i < this.plays.length; i++){
            if(this.plays[i] == DONATE)
                count++;
        }
        
        return count;
    }
    
    // for ai players, randomizes decision
    public int play(int turnIndex){
        
        int decision = random.nextInt(2);
        this.plays[turnIndex] = decision;
        
        if (decision == DONATE)
            return 1;
        else{
            this.earnings++;
            return 0;
        }
    }
    
    // for user player 
    public int play(int turnIndex, int play){
        this.plays[turnIndex] = play;
        
        if (play == DONATE) { 
            return 1;
        } else {
            this.earnings++;
            return 0;
        }
            
    }
    
    public String getEarnings(){
        DecimalFormat df = new DecimalFormat("0.00");      
        return (df.format(this.earnings));
    }
    
    public void reset(){
        for (int i = 0; i < this.plays.length; i++)
            plays[i] = Player.BOYCOTT;
        this.earnings = 0;
    }
    
    public void CalculateCut(int bankProfit, int totalDonations){
        double donationRate, cut;
        int donations = this.getDonationCount();
        
        donationRate = donations / (totalDonations * 1.0);
        
        cut = (bankProfit * donationRate) + donations;
        this.earnings += cut;
    }
}
