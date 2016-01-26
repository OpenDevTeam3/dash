import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gameframework.drawing.Drawable;
import gameframework.drawing.DrawableImage;
import gameframework.drawing.GameCanvas;
import gameframework.drawing.SpriteManagerDefaultImpl;
import gameframework.game.GameData;
import gameframework.game.GameEntity;
import gameframework.motion.GameMovable;
import gameframework.motion.MoveStrategy;
import gameframework.motion.MoveStrategyKeyboard8Dir;
import gameframework.motion.blocking.MoveBlocker;
import gameframework.motion.overlapping.Overlappable;


public class Player extends GameMovable implements GameEntity,Drawable,MoveBlocker{

	private SpriteManagerDash sprite;
	private int anime=0;
	private boolean animenormal=false;
	private int saut;
	private boolean ontheground;
	DrawableImage image;
	//private GameCanvas gameCanvas;
	
	
	public Player(GameCanvas gameCanvas,GameData data) {
		super();
		ontheground=false;
		
		MoveStrategyPlayer moveStrategy=new MoveStrategyPlayer(this);
		moveDriver.setStrategy(moveStrategy);
		
		gameCanvas.addKeyListener(moveStrategy);
		
		
		moveDriver.setmoveBlockerChecker(data.getMoveBlockerChecker());
		
		
		//this.gameCanvas=gameCanvas;
		image = new DrawableImage("../../playersprite.png", gameCanvas);
		
		this.sprite = new SpriteManagerDash(image, 200, 8);
		this.sprite.setTypes("normal","1","2","course");
		this.sprite.setType("course");
		
		setPosition(new Point(100, 50));
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(getPosition().x,getPosition().y, 50*4, 50*4);
	}
	
	@Override
	public void draw(Graphics g, Point p) {
		sprite.draw(g, new Point(getPosition().x-p.x,getPosition().y-p.y),46,50);
		
		// hit box
		g.drawRect(getPosition().x-p.x,getPosition().y-p.y, 50*4, 50*4);	}

	@Override
	public void draw(Graphics g) {
		
		sprite.draw(g, getPosition(),46,50);
		
		// hit box
		g.drawRect(getPosition().x,getPosition().y, 50*4, 50*4);
	}

	
	@Override
	public void oneStepMoveAddedBehavior() {
		if(animenormal && (anime++)==9){
			this.sprite.setType("course");
			this.sprite.reset();
		}
		
		this.sprite.increment();
		getSpeedVector().getDirection().y++;
		
	}
	
	public int jump() {
		this.ontheground=false;
		sprite.setType("2");
		sprite.reset();
		animenormal=true;
		anime=0;
		return getSpeedVector().getDirection().y=-5;
	}
	


	public boolean isOntheground() {
		return ontheground;
	}
	
	public void setOntheground(boolean ontheground) {
		this.ontheground=ontheground;
	}

	
}
