package mario.stages.first;

import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.cinematics.parallax.ImageParallax;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.TextLayer;
import br.com.vite.export.MapExporter;

public class YoshiIsland2 extends Stage {

	public YoshiIsland2(int w, int h) {
		super(w, h);
	}

	private List<TextLayer> texts = new ArrayList<TextLayer>();

	public void load() {
		super.load();

		createFirstSlide();

		try {
			map = MapExporter.loadMap("yoshiland2.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		background = new ImageParallax("background/forest.png");
		background.setProximity(4);
	}

	public void draw(Graphic g) {
		super.draw(g);
		
		for(TextLayer text: texts) {
			//TODO REMOVER *2
			g.setTransform(AffineTransform.getTranslateInstance(map.getOffsetX(), 0));
			g.setFontSize(text.getSize());
			g.drawShadow(text.getX(), text.getY(), text.getText());			
		}

		g.resetTransform();
	}

	private void createFirstSlide() {

		//Slide 0
		texts.add(createText(0, 80, 38, "Rubyllica"));

		texts.add(createText(0, 170, 30, "Ruby + Etyllica"));

		texts.add(createText(0, 240, 30, "github.com/rubyllica"));
		
		texts.add(createText(0, 340, 30, "Por Yuri Pourre"));

		//Slide 1
		texts.add(createText(1, 80, 38, "O que é Etyllica?"));

		texts.add(createText(1, 170, 30, "A etyllica é uma engine de jogos"));
		texts.add(createText(1, 200, 30, "feita em Java Puro"));

		texts.add(createText(1, 250, 30, "Tem o objetivo de tornar o"));
		texts.add(createText(1, 280, 30, "desenvolvimento muito rápido"));

		texts.add(createText(1, 330, 30, "Quero baixar um único arquivo e "));
		texts.add(createText(1, 360, 30, "ter meu ambiente configurado"));

		//Slide 2
		texts.add(createText(2, 80, 38, "Algumas funcionalidades:"));

		texts.add(createText(2, 170, 30, "Gerenciamento de Resources"));

		texts.add(createText(2, 250, 30, "Fullscreen"));

		texts.add(createText(2, 330, 30, "Aceita Diversos Formatos"));
		texts.add(createText(2, 360, 30, "(jpg, bmp, png, pcx, tga, gif)"));

		//Slide 3
		texts.add(createText(3, 80, 38, "Exibição de Imagens"));

		texts.add(createText(3, 170, 30, "- Posicionamento"));

		texts.add(createText(3, 250, 30, "- Opacidade"));

		texts.add(createText(3, 330, 30, "- Rotação"));

		//Slide 4
		texts.add(createText(4, 80, 38, "Animação"));

		texts.add(createText(4, 170, 30, "- Animação por Sprites"));

		texts.add(createText(4, 250, 30, "- Animação por Pivot"));
		
		texts.add(createText(4, 330, 30, "- Sistema de Tween Animation"));
				
		//Slide 5
		texts.add(createText(5, 80, 38, "Input"));

		texts.add(createText(5, 170, 30, "- Mouse"));

		texts.add(createText(5, 250, 30, "- Teclado"));
		
		texts.add(createText(5, 330, 30, "- Joystick"));
		
		//Slide 6
		texts.add(createText(6, 80, 38, "Colisão"));

		texts.add(createText(6, 170, 30, "- Colisão por BoundingBox"));

		texts.add(createText(6, 250, 30, "- Colisão Circular"));
		
		texts.add(createText(6, 330, 30, "- Colisão Isométrica"));

	}

	private TextLayer createText(int slide, int y, float size, String text) {

		int slideOffset = 50+slide*540;

		TextLayer textLayer = new TextLayer(slideOffset, y, text);
		textLayer.setSize(size);

		return textLayer;
	}
}
