import java.awt.Point;
import java.util.Iterator;

import gameframework.game.GameConfiguration;
import gameframework.game.GameData;
import gameframework.game.GameEntity;

public class Camera {

	private Point position;
	private GameData data;
	private int speed;
	
	public Camera(GameData data) {
		this.data=data;
		this.speed=((ConfigurationDash)data.getConfiguration()).getVitesse();
		position=new Point(0,0);
				
	}
	
	public Point getPosition() {
		return position;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void moveCamera() {
		position.x+=speed;
		
		Iterator<GameEntity> gt = data.getUniverse().getGameEntitiesIterator();
		for (; gt.hasNext();) {
			GameEntity tmp = gt.next();
			if(tmp instanceof BlockTerrain && ((BlockTerrain) tmp).getPosition().x-200<position.x)
				data.getUniverse().removeGameEntity(tmp);
		}
	}

}
