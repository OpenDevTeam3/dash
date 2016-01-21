
import java.awt.Point;

import gameframework.drawing.GameCanvas;
import gameframework.game.GameData;
import gameframework.game.GameDefaultImpl;
import gameframework.game.GameLevel;
import gameframework.gui.GameWindow;


public class DashWindow extends GameWindow{

	
	public DashWindow(String gameName, GameCanvas gameCanvas, GameData data) {
		super(gameName, gameCanvas, data);
		
		GameLevel level=new GameLevelDash(data,50);
		data.addLevel(level);
		GameDefaultImpl game=new GameDefaultImpl(data);
		
		this.createGUI();
				
		Player player = new Player(gameCanvas,data);
		Terrain terrain = new Terrain(gameCanvas,data);
		
		data.getUniverse().addGameEntity(player);
		data.getUniverse().addGameEntity(terrain);
		
		for(int i = 0 ;i<10;i++){
			BlockTerrain block = new BlockTerrain(data, ""+i%5, new Point(i*100,400));
			data.getUniverse().addGameEntity(block);
		}
			
		
		game.start();
		
		
	}

	public static void main(String[] args) {
		GameData data=new GameData(new ConfigurationDash(30,50,24, 1));
		new DashWindow("Dash",data.getCanvas() ,data);
	}
}
