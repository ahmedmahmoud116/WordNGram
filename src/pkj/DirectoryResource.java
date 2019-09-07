package pkj;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("serial")
public class DirectoryResource extends JFrame{

	public DirectoryResource(){
		
	}
	
	public Iterable<File> selectedFiles(){
		Iterable<File> it = null;
		JFileChooser chose = new JFileChooser();
		chose.setMultiSelectionEnabled(true);
		chose.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = chose.showOpenDialog(this);
		if(result == JFileChooser.APPROVE_OPTION) {
			
			File[] fl  = chose.getSelectedFiles();
			ArrayList<File> al = new ArrayList<>(Arrays.asList(fl));
			it = al;
			return it;
		}
		return it;
	}
}
