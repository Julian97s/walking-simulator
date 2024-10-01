package main;

import actors.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovementVisitor {
    // ATTRIBUTES

    Position upper_bound;
    Position lower_bound;
    List<Prize> pricesList;


    // METHODS

    public MovementVisitor(Position g_lower, Position g_upper){
        this.upper_bound = g_upper;
        this.lower_bound = g_lower;
        this.pricesList = new ArrayList<>();
    }

    // add a prize to the list
    public Prize addPrize(){
        Random r = new Random();
        //create a random location within the boundries
        int ran_x = r.nextInt(this.lower_bound.getPositionX(),this.upper_bound.getPositionX()+1);
        int ran_y = r.nextInt(this.lower_bound.getPositionY(),this.upper_bound.getPositionY()+1);
        // construct a prize
        Prize p = new Prize(ran_x,ran_y);
        // add to pricesList
        this.pricesList.add(p);
        //return a the contructed prize
        return p;
    }

    //get prices count
    public int getPricesCount(){
        return this.pricesList.size();
    }

    private void checkClosestPrize(Actor a){
        for (Prize prize : this.pricesList){
            prize.get(a.getPositionX(),a.getPositionY());
        }
    }
}
