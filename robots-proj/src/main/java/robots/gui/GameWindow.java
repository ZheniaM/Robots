package robots.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
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
}
