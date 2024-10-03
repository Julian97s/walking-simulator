package main;

import actors.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MovementVisitor {
    // ATTRIBUTES

    private Position upper_bound;
    private Position lower_bound;
    private List<Prize> pricesList;


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
        int ran_x = r.nextInt(this.lower_bound.getPositionX(),this.upper_bound.getPositionX());
        // its okay the exclusiveness because i want it to be one less otherwise it migh go out of bounce
        int ran_y = r.nextInt(this.lower_bound.getPositionY(),this.upper_bound.getPositionY());
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

    private Prize checkClosestPrize(Actor a){
        double lowest_distance = 2000000;
        Prize closestPrize = null;
        for (Prize prize : this.pricesList){
            double current_distance = prize.getPosition().distanceTo(a.getPosition());
            if (current_distance == 0){
                //remove it from the list. book keeping. also delite it from the diplay
                //track if prize is in hte list of not dont show it
                this.addPrize();
                a.addPoint();
            } else {
                //if lowest 
                if (current_distance < lowest_distance ){
                    lowest_distance = current_distance;
                    closestPrize = prize;
                }
            }
        }
        return closestPrize;
    }

    //
    public void move(Tortoise t){
        Prize closets_prize = this.checkClosestPrize(t);
        t.moveActor(closets_prize.getPositionX(),closets_prize.getPositionY()); // how or what am i moving here?
        t.step(this.lower_bound, this.upper_bound);
    }

    public void move(Rabbit t){
        Prize closets_prize = this.checkClosestPrize(t);
        t.moveActor(closets_prize.getPositionX(),closets_prize.getPositionY());
        t.step(this.lower_bound, this.upper_bound);
    }
}
