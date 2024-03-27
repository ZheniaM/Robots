package robots.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.io.IOException;
import java.util.Set;
import javax.swing.JPanel;
import robots.data.CashReader;
import robots.data.DataContainer;
import robots.log.LogChangeListener;
import robots.log.LogEntry;
import robots.log.LogWindowSource;
import robots.log.Logger;

public class LogWindow extends SaveMerge implements LogChangeListener {
    private LogWindowSource m_logSource;
    private TextArea m_logContent;
    static private final DataContainer DC = DataContainer.getInstance();


    public LogWindow(LogWindowSource logSource) {
        super(DC.getContentNoException("logger/title"), // "Протокол работы"
                true, true, true, true);
        m_logSource = logSource;
        m_logSource.registerListener(this);
        m_logContent = new TextArea("");
        m_logContent.setSize(200, 500);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_logContent, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        updateLogContent();
    }

    private void updateLogContent() {
        StringBuilder content = new StringBuilder();
        for (LogEntry entry : m_logSource.all()) {
            content.append(entry.getMessage()).append("\n");
        }
        m_logContent.setText(content.toString());
        m_logContent.invalidate();
    }

    @Override
    public void onLogChanged() {
        EventQueue.invokeLater(this::updateLogContent);
    }

    static public LogWindow[] load() throws IOException {
        String path = String.format("saves/%s/", LogWindow.class.getSimpleName());
        Set<String> files = CashReader.getAllFiles(path);
        LogWindow[] res = new LogWindow[files.size()];
        int i = 0;
        for (String filename : files) {
            LogWindow lw = new LogWindow(Logger.getDefaultLogSource());
            loadTo(lw, filename);
            res[i++] = lw;
        }
        return res;
    }
}
