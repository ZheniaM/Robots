package robots.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JInternalFrame;
import robots.data.CashReader;
import robots.data.CashWriter;

public abstract class SaveMerge extends JInternalFrame implements IWithStates {

	public SaveMerge(String name, boolean b1, boolean b2, boolean b3, boolean b4) {
		super(name, b1, b2, b3, b4);
	}

	public void save(String name, int id) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("Location", getLocation());
		data.put("Size", getSize());
		data.put("Selected", isSelected());
		try {
			String dir = String.format("saves/%s", name);
			String filename = String.format("%s/%d.save", dir, id);
			new CashWriter(filename).writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getData(long id) throws IOException, ClassNotFoundException {
		return (HashMap<String, Object>) new CashReader(
				String.format("saves/%s/%d.save", getName(), id)).readObject();
	}

	static public void loadTo(SaveMerge frame, String filename) {
		try {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> data =
					(HashMap<String, Object>) new CashReader(filename).readObject();
			frame.setLocation((Point) data.get("Location"));
			frame.setSize((Dimension) data.get("Size"));
			frame.setSelected((boolean) data.get("Selected"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
