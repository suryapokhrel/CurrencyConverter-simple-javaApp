import javax.swing.JFrame;


public class Converter {
	public static void main(String[] args) {
        JFrame frame = new JFrame("Converter");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        MainPanel panel = new MainPanel();
        frame.setJMenuBar(panel.setupMenu());
        
        frame.getContentPane().add(panel);
        
        
        frame.pack();
        frame.setVisible(true);
    }
}
