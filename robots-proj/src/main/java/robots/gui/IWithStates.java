package robots.gui;

import java.io.IOException;

public interface IWithStates {
	public void save(String name, int id);

	static public void loadTo(SaveMerge frame, String filename) {
        throw new UnsupportedOperationException("Unimplemented method 'loadTo'");
	};

	static public<T extends SaveMerge> T[] load() throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'load'");
	};
}
