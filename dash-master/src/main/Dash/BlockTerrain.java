import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.MoveStrategy;
import gameframework.motion.MoveStrategyDefaultImpl;
import gameframework.motion.MoveStrategyStraightLine;
import gameframework.motion.SpeedVector;
import gameframework.motion.blocking.MoveBlocker;


public class BlockTerrain extends GameMovable implements GameEntity,Drawable,MoveBlocker{
	
	private static SpriteManagerDash sprite;
	private String type;
	
	public BlockTerrain(GameData data,String type,Point p){
		if(sprite==null){
			sprite = new SpriteManagerDash(new DrawableImage("../../spriteplateforme.png", data.getCanvas()), 100, 1,2);
			sprite.increment();
			sprite.increment();
			sprite.setTypes("0","1","2","3","4","5");
		}
		this.type=type;
		
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		this.setPosition(p);
		
		MoveStrategyStraightLine moveStrategy=new MoveStrategyStraightLine(getPosition(),new Point(-1000,getPosition().y));
		moveStrategy.setSpeed(20);
		moveDriver.setStrategy(moveStrategy);
		
	}
	
	@Override
	public void draw(Graphics g) {
		sprite.setType(type);
	 	sprite.draw(g, getPosition());
		
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(getPosition().x,getPosition().y,150, 150);
	}

	public SpriteManagerDash getSpriteBlockTerrainManager(){
		return sprite;
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if(getPosition().x+150<0){
			this.setPosition(new Point(1000,getPosition().y));
		}
	}
	
}
