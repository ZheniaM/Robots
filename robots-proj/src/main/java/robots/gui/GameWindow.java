package robots.gui;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Set;
import javax.swing.JPanel;
import robots.data.CashReader;
import robots.data.DataContainer;

public class GameWindow extends SaveMerge {
    private final GameVisualizer m_visualizer;
    static private final DataContainer DC = DataContainer.getInstance();

    public GameWindow() {
        super(DC.getContentNoException("game/title"), // "Игровое поле"
                true, true, true, true);
        m_visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
    }

    static public GameWindow[] load() throws IOException {
        String path = String.format("saves/%s/", GameWindow.class.getSimpleName());
        Set<String> files = CashReader.getAllFiles(path);
        GameWindow[] res = new GameWindow[files.size()];
        int i = 0;
        for (String filename : files) {
            GameWindow gw = new GameWindow();
            loadTo(gw, filename);
            res[i++] = gw;
        }
        return res;
    }
}
