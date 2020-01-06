import java.awt.*;

public class Ball {
    private int x,y;
    private final int DIAMETER = 30;
    private final int SPEED = 8;

    double dx = SPEED, dy = SPEED;

    Board board;

    public Ball(Board board){
        x = 0;
        y = 0;


        //'this' keyword references the object that is executing or calling the 'this' reference

        this.board = board;
        //the 'this'.board is the board that is created in THIS CLASS, THE BALL CLASS, and is equalling the board in the BOARD CLASS
    }

    public void move(){

        //LEFT & RIGHT
        if(x <= 0){
            dx*=-1;
            board.setcScore(board.getcScore()+1);
        }
        if(x+DIAMETER >= board.getWidth()){
            dx*=-1;
            board.setpScore(board.getpScore()+1);
        }
        if(y<= 0 || y+DIAMETER >= board.getHeight()){
            dy*=-1;

        }

        x+=dx;
        y+=dy;
    }

    public void setPosition(int x, int y){
        this.x = x - DIAMETER/2;
        this.y = y - DIAMETER/2;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,DIAMETER,DIAMETER);
    }

    double MAXANGLE = 5*Math.PI/12; //70 degree

    public void checkCollisions(Paddle other){
        if(getBounds().intersects(other.getBounds())){
           double paddleY = other.getBounds().getY();
           double paddleC = other.getBounds().getHeight()/2;
           double ballY = y;

           double relativeIntersect = (paddleY+paddleC) - ballY;
           double normalIntersection = relativeIntersect/paddleC;
           double bounceAngle = MAXANGLE + normalIntersection;

           if(y+DIAMETER/2<paddleY){
               ballY = y+DIAMETER;
           }
           else if(y+DIAMETER/2 > paddleY+other.getBounds().getHeight()){
               ballY = y;
           }
           else
               ballY = y+DIAMETER;

           if(x<board.getWidth()/2){
                dx *= -1;
               //dx = (int)(SPEED * Math.cos(bounceAngle));
           }
           if(x>board.getWidth()/2){
               dx *= -1;
               //dx = (int)(SPEED *-Math.cos(bounceAngle));
           }

           //dy=(int)(SPEED * -Math.sin(bounceAngle));
        }
    }

    public void paint(Graphics g){
        g.fillOval(x,y,DIAMETER,DIAMETER);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getDiam() {
        return DIAMETER;
    }
}
