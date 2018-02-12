package pa03;
import java.awt.Color;
import java.awt.Graphics;

public class NicoleCircle extends CircleShape{
  public Color borderColor; // the color of the border of the circle


  public NicoleCircle(){
    super();
    this.color = new Color(153,51,153);
    this.borderColor = new Color(255,255,0);
  }

//create a circle with particular color and border color
  public void draw(Graphics g) {
      int u = (int)(x-radius);
      int v = (int)(y-radius);
      int w = (int)(2*radius);
      int h = w;
      g.setColor( color );
      g.fillOval( u,v,w,h );
      g.setColor( borderColor );
      g.drawOval( u,v,w,h );
  }

  public static void main(String[] args){
    NicoleCircle x = new NicoleCircle();
    System.out.println("my circle is " +x);
  }

  public String toString(){
    return "NicoleCircle:" + super.toString();
  }

/**
change the values of x,y,vx,vy to keep the circle on the board
change the color of the circle once the circle comes to the edge
*/
  public void keepOnBoard(){
    if (this.x < this.radius) {
      // it went off the left edge! do something!
      this.vx = -this.vx;
      this.x = this.radius;
      this.color = new Color(255, 102, 217);

    }else if (this.x > CircleShape.boardWidth-this.radius) {
      // it went off the right edge! do something!
      this.vx = -this.vx;
      this.x = CircleShape.boardWidth-this.radius;
      this.color = new Color(153,51,153);
    }

    if (this.y < this.radius){
      // it went above the top edge!
      this.vy = -this.vy;
      this.y = this.radius;
      this.color = new Color(255, 204, 242);

    } else if (this.y > CircleShape.boardHeight-this.radius) {
      // it went below the bottom edge!
      this.vy = -this.vy;
      this.y = CircleShape.boardHeight-this.radius;
      this.color = new Color(153,51,153);
    }
  }


}
