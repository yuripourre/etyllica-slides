

import mario.editor.MarioMapEditor;
import mario.stages.first.YoshiIsland2;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.loader.JoystickLoader;

public class MarioExample extends EtyllicaFrame {

	private static final long serialVersionUID = 1L;

	public MarioExample() {
		//super(255, 236);
		//super(800, 600);
		super(512, 432);
		
		//initJoysick = true;
	}
	
	public static void main(String[] args){
		MarioExample marioExample = new MarioExample();
		marioExample.init();
	}
	
	@Override
	public Application startApplication() {
		
		/*hideCursor();
		addLoader(MultimediaLoader.getInstance());
		return new YoshiHouse(w,h);*/
		JoystickLoader.getInstance().start(1);
		new Thread(JoystickLoader.getInstance()).start();
		
		return new YoshiIsland2(w,h);
		
		//return new MarioMapEditor(w, h);
	}
	
}
