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
        for (Prize prize : new ArrayList<>(this.pricesList)){
            Position current_prize_pos = new Position(prize.getPositionX(),prize.getPositionY());
            Position current_actor_pos = new Position(a.getPositionX(),a.getPositionY());
            //double current_distance = prize.getPosition().distanceTo(a.getPosition()); // should i change this se i dont pass a reference to the object?
            double current_distance = current_prize_pos.distanceTo(current_actor_pos); // should i change this se i dont pass a reference to the object?
            if (current_distance == 0){
                pricesList.remove(prize);
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

    //Where and how should i be checking if the new positions are within boundries?

    public void move(Tortoise t){
        Prize closets_prize = this.checkClosestPrize(t);

        int delta_x = 0;
        int delta_y = 0;
    
        /* i have a prize and a prize is an actor, so i can get a position from this actor, then i can use
         * closest_Prize.getPositionX() and closest_Prize.getPositionY().
         * 
         * Now i have to figure out how to calculate from my tortoise.getPositionX() % closest_prize.getPositionX(). if thats
         *
        */

        //if my prize ever happen to be in position x = 0 then the modulo will equal to 0 even if theyre are on different x's 
        // ex; prize (1,1) and tortoise (7,5) whenever i do the modulo itll be 0 and my tortoise is not going to move
        // i havent test it yet but i believe thats what is going to happen
        // could i do something like initializing two ints like:
        /*
         * int t_posX = t.getPositionX();
         * int p_posX = closest_prize.get
         */


        // from the modulo all im doing is to know if i need to move in the determined axis, im adding one because when the position x or y equals to 1 the resultan modulo will always be zero and that will be a bug,
        // by adding one on both t and prize x's ill make sure that if they're different will always return a number >0 as their modulo.
        if ( (t.getPositionX()+1) % (closets_prize.getPositionX()+1) > 0 ){ //if module if greater than 0 it means i have to move either to the right or the left
             // if my x position is greater than the prize's x position, it means im to the right, and ill have to move one step to the left(decrease my x position) to get closer to my prize
            if( t.getPositionX() > closets_prize.getPositionX() ){
                delta_x -= 1;
             // else if im to the left of my prize, ill have to take one step to the right (increase my x position)
            } else if ( t.getPositionX() < closets_prize.getPositionX() ){
                delta_x += 1;
            }
        }
        if ((t.getPositionY()+1) % (closets_prize.getPositionY()+1) > 0 ){ //if my reminder is greater than 0 i have to move up or down
            // if my position in y is greater than the prize's y, it means im above the prize and i have to take a step south 
            if (t.getPositionY() > closets_prize.getPositionY()){
                delta_y -= 1;
            // else if my y is smaller, i have to take a step north closer to the prize
            } else if( t.getPositionY() < closets_prize.getPositionY() ){
                delta_y += 1;
            }
        }
        t.moveActor(delta_x,delta_y); // now ill move my toroise closer to the prize.
        
    }

    public void move(Rabbit r){
        Prize closets_prize = this.checkClosestPrize(r);

        // Position current_position = new Position(getPricesCount(), getPricesCount());

        boolean has_energy = true;

        int x_modulo = r.getPositionX()+1 % closets_prize.getPositionX()+1; 
        int y_modulo = r.getPositionY()+1 % closets_prize.getPositionY()+1;
        int delta_x = 0;
        int delta_y = 0;

        if (has_energy){
            if (x_modulo == 1 || y_modulo == 1){ 
                if (r.getPositionX() > closets_prize.getPositionX()){
                    delta_x -= 1;
                } else if (r.getPositionX() < closets_prize.getPositionX()){
                    delta_x +=1;
                } else if (delta_x == 0 &&(r.getPositionY()>closets_prize.getPositionY())){
                    delta_y -=1;
                } else if (delta_x == 0 &&(r.getPositionY()<closets_prize.getPositionY())){
                    delta_y +=1;
                }
            } else if (x_modulo > 1 || y_modulo > 1){
                if (r.getPositionX() > closets_prize.getPositionX()){
                    delta_x -=2;
                } else if (r.getPositionX() < closets_prize.getPositionX()){
                    delta_x +=2;
                } else if (delta_x == 0 &&(r.getPositionY()>closets_prize.getPositionY())){
                    delta_y -=2;
                } else if (delta_x == 0 &&(r.getPositionY()<closets_prize.getPositionY())){
                    delta_y +=2;
                }
            }
            has_energy = r.burnEnergy();
        }
        r.moveActor(delta_x,delta_y);
    }

    // tortouse can move 1 spet in a direction. 
    //is the prize to my right or to mi left?
    // it its to my right is x = 1 and to my left x = -1 and so on.
    // step intesd of having the bounds as paramethers will have the movement visitor
}
