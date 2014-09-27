import aula1.ImageDrawing;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;


public class Project extends EtyllicaFrame {

	private static final long serialVersionUID = 1L;

	public Project() {
		super(800, 600);
	}
	
	public static void main(String[] args) {
		Project project = new Project();
		project.init();
	}

	@Override
	public Application startApplication() {
		
		String path = Project.class.getResource("").toString();
		setPath(path+"../");
		
		return new ImageDrawing(w, h);
	}

}
