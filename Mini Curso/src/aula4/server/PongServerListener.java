package aula4.server;

import java.util.Random;

import aula4.model.Ball;
import aula4.model.Paddle;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.linear.Point2D;
import br.com.etyllica.network.examples.action.ActionServerListener;
import br.com.etyllica.network.examples.action.model.State;
import br.com.etyllica.network.realtime.model.KeyAction;

public class PongServerListener extends ActionServerListener<Paddle, State> {

	private int w;
	private int h;

	private int firstPlayerId = 0;
	private int secondPlayerId = 0;
	
	private int count = 0;
	
	private Ball ball;
	
	private boolean started = false;
	
	public static final int BALL_ID = -999;
	
	public PongServerListener(int interval, int w, int h) {
		super(interval);

		this.w = w;
		this.h = h;
		
		initServer();
	}
	
	private void initServer() {
		
		ball = new Ball(w/2, h/2, 20, 20);
		
		State ballState = new State();
		
		ballState.id = BALL_ID;
		ballState.x = ball.getX();
		ballState.y = ball.getY();
				
		states.put(BALL_ID, ballState);
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

		if(count == 0) {
			paddle = createFirstPlayer(id);
		} else {
			paddle = createSecondPlayer(id);
			startGame();
		}

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
	
	private void startGame() {
		started = true;

		ball.setCoordinates(w/2-ball.getW()/2, h/2-ball.getH()/2);
		
		Random random = new Random();
		ball.setAngle(random.nextInt(360));
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

	@Override
	public void execute() {
		if(started) {
			ball.update();
			
			updateCollision(firstPlayerId);
			updateCollision(secondPlayerId);
			
			int border = 30;
			
			if(ball.getX() < -ball.getW()) {
				startGame();
			}
			
			if(ball.getX() > w) {
				startGame();
			}
			
			if((ball.getY() < -ball.getH()+border) || (ball.getY()+ball.getH() > h-border)) {
				ball.alternateDy();
			}
			
			State ballState = states.get(BALL_ID);
			ballState.x = ball.getX();
			ballState.y = ball.getY();
			
		}
	}
		
	private void updateCollision(int paddleId) {
		
		Paddle paddle = players.get(paddleId);
			
		if(ball.colideRect(paddle)) {
			
			double ballCenterX = ball.getX()+ball.getW()/2;
			double ballCenterY = ball.getY()+ball.getH()/2;
			
			double paddleCenterX = paddle.getX()+paddle.getW()/2;
			double paddleCenterY = paddle.getY()+paddle.getH()/2;
			
			double angle = Point2D.angle(paddleCenterX, paddleCenterY, ballCenterX, ballCenterY);			
			
			ball.setAngle(angle);
		}			
	}

}
