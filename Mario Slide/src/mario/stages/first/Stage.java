package mario.stages.first;

import java.awt.Color;
import java.awt.geom.AffineTransform;

import mario.player.Mario;
import br.com.etyllica.cinematics.parallax.ImageParallax;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.effects.light.LightSource;
import br.com.etyllica.effects.light.ShadowLayer;
import br.com.tide.input.controller.Controller;
import br.com.tide.input.controller.EasyController;
import br.com.vite.map.Map;

public class Stage extends Application {

	protected Map map;
	
	protected Controller controller;
	
	protected Mario mario;
	
	protected int groundPosition = 392;
	
	protected ImageParallax background;
	
	private ShadowLayer shadow;
	
	private LightSource iris;
	
	public Stage(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		mario = new Mario(30, groundPosition);
		
		controller = new EasyController(mario);
		
		shadow = new ShadowLayer(0, 0, w, h);
		
		iris = new LightSource(mario.getX(), mario.getY(), 500);
		
		updateAtFixedRate(50);
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
				
		//mario.handleEvent(event);
		controller.handleEvent(event);

		return GUIEvent.NONE;
	}
	
	private static final int LOCK_SCENE = 192;
	
	@Override
	public void timeUpdate(long now) {
		mario.update(now);
			
		int offset = mario.getX()-LOCK_SCENE;
		
		if(offset>0) {
			mario.setX(LOCK_SCENE);
			map.setOffsetX(-offset);
			background.setOffset(offset);
			
			iris.setCoordinates(LOCK_SCENE+8-iris.getIntensity()/2, mario.getY()+16-iris.getIntensity()/2);
		}
		
	}

	@Override
	public void draw(Graphic g) {
		
		g.setTransform(AffineTransform.getTranslateInstance(0, 38));
		
		background.draw(g);
		
		map.draw(g);
		
		mario.draw(g);
		
		if(map.getOffsetX()<-3700) {
			
			int intensity = 4200+map.getOffsetX();

			if(intensity > 5) {
				
				iris.setIntensity(intensity);
			
				shadow.drawLights(g, iris);
				
			} else {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, w, h);
			}
		}
		
		g.setColor(Color.WHITE);
		g.drawShadow(100, 100, Integer.toString(map.getOffsetX()));
	}
	
}
