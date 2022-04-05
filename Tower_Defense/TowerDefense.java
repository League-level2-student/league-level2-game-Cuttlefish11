import javax.swing.JFrame;

public class TowerDefense {
public static void main(String[] args) {
	GamePanel gp = new GamePanel();
	JFrame frame = new JFrame;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	LeagueInvaders(){
	}
	void setup(){
		frame.add(gp);
		frame.setSize(WIDTH, HEIGHT);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.addKeyListener(gp);
	}
public static void main(String[] args) {
	LeagueInvaders ln = new LeagueInvaders();
	ln.setup();
}
}
}