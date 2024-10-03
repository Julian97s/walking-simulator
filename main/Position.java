package main;
public class Position {
    private int position_x;
    private int position_y;

    public Position(int position_x, int position_y){
        this.position_x = position_x;
        this.position_y = position_y;
    }

    public int getPositionX(){
        return position_x;
    }
    
    public int getPositionY(){
        return position_y;
    }

    public Position moveBy(int delta_x, int delta_y){
        int new_x = this.position_x+delta_x;
        int new_y = this.position_y+delta_y;
        Position new_position = new Position(new_x,new_y);
        return new_position;
    }

    public Position fitBoundries(int min_x,int min_y, int max_x, int max_y){
        int bounded_x = Math.min(max_x, Math.max(this.position_x, min_x));
        int bounded_y = Math.min(max_y, Math.max(this.position_y, min_y));
        Position new_position = new Position(bounded_x,bounded_y);
        return new_position;
    }

    public double distanceTo(Position g_pos){
        double distance =0;
        double x_base = this.position_x-g_pos.getPositionX();
        double y_base = this.position_y-g_pos.getPositionY();
        double operation = Math.pow(x_base, 2) + Math.pow(y_base, 2);
        distance = Math.sqrt(operation);
        return distance;
    }
}
