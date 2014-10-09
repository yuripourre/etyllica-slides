package aula4.server;

import aula4.model.Paddle;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.network.examples.action.ActionServerListener;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.model.KeyAction;

public class PongServer extends ActionServerListener<Paddle, State> {

	private int w;
	private int h;

	private int firstPlayerId = 0;
	private int secondPlayerId = 0;

	private int count = 0;

	public PongServer(int interval, int w, int h) {
		super(interval);

		this.w = w;
		this.h = h;
	}

	@Override
	public void handleKey(int id, KeyAction action) {

		if(id != firstPlayerId && id != secondPlayerId)
			return;
			
		if(action.key == KeyEvent.TSK_UP_ARROW) {

			if(action.state == KeyState.PRESSED) {
				players.get(id).setUpPressed(true);
			}

			if(action.state == KeyState.RELEASED) {
				players.get(id).setUpPressed(false);
			}

		} else if(action.key == KeyEvent.TSK_DOWN_ARROW) {

			if(action.state == KeyState.PRESSED) {
				players.get(id).setDownPressed(true);
			}

			if(action.state == KeyState.RELEASED) {
				players.get(id).setDownPressed(false);
			}

		}
	}

	@Override
	public Paddle createPlayer(int id, State state) {

		Paddle paddle = null;

		if(count == 0)
			paddle = createFirstPlayer(id);
		else 
			paddle = createSecondPlayer(id);

		count++;
		
		return paddle;
	}

	private Paddle createFirstPlayer(int id) {
		firstPlayerId = id;

		Paddle paddle = new Paddle(20, h/2-120/2, 20, 120);

		return paddle;
	}

	private Paddle createSecondPlayer(int id) {
		secondPlayerId = id;

		Paddle paddle = new Paddle(w-20-20, h/2-120/2, 20, 120);

		return paddle;
	}

	@Override
	public void updatePlayer(Paddle player) {
		player.update();
	}

	@Override
	public void updateState(State state, Paddle player) {
		state.x = player.getX();
		state.y = player.getY();				
	}

	@Override
	public Class<?> getStateClass() {
		return State.class;
	}

	@Override
	public State createState(int id) {
		State state = new State();
		state.id = id;
		
		return state;
	}		

}
