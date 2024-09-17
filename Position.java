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
    
    public int getPrositonY(){
        return position_y;
    }

    public Position moveBy(int delta_x, int delta_y){
        int new_x = this.position_x+delta_x;
        int new_y = this.position_y+delta_y
        Position new_position = new Position(new_x,new_y);
        return new_position;
    }

    public void fitBoundries(int max__x,int min_x, int max_y, int min_y){
        // math.min math.min
        // the bigger of the lowe bound and the smaller of the upper bound
        int new
    }
}
