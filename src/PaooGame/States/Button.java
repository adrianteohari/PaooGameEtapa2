package PaooGame.States;

import java.awt.*;

public class Button {
    String _name;

    public Button(String name){
        this._name=name;
    }
    public void Draw(Graphics g, int x, int y){
        g.drawString(_name,x,y);
    }
}