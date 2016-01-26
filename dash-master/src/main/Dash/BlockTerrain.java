import gameframework.drawing.DrawableImage;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.blocking.MoveBlocker;
import gameframework.motion.overlapping.Overlappable;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class BlockTerrain implements Overlappable,GameEntity,MoveBlocker{
	
	private static SpriteManagerDash sprite;
	private String type;
	
	private Point position;
	
	public BlockTerrain(GameData data,String type,Point p){
		if(sprite==null){
			sprite = new SpriteManagerDash(new DrawableImage("../../spriteplateforme.png", data.getCanvas()), 100, 1,2);
			sprite.increment();
			sprite.increment();
			sprite.setTypes("0","1","2","3","4","5");
		}
		this.type=type;
		this.position = p;
	}
	
	@Override
	public void draw(Graphics g) {
		sprite.setType(type);
	 	sprite.draw(g, position);
		
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(position.x,position.y,150, 150);
	}

	public SpriteManagerDash getSpriteBlockTerrainManager(){
		return sprite;
	}

	@Override
	public void draw(Graphics g, Point p) {
		sprite.setType(type);
	 	sprite.draw(g, new Point(position.x-p.x,position.y-p.y));
	}

	@Override
	public boolean isMovable() {
		return false;
	}

	@Override
	public Point getPosition() {
		return position;
	}
	
}
