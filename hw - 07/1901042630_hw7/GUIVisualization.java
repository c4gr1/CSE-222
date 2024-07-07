import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A simple graphical user interface (GUI) to visualize the performance graphs.
 */
public class GUIVisualization extends JFrame {
    private List<Integer> dataPointsX;
    private List<Long> dataPointsY;
    private String title;

    /**
     * Constructs a new GUIVisualization.
     *
     * @param title the title of the graph
     * @param dataPointsX the x-axis data points
     * @param dataPointsY the y-axis data points
     */
    public GUIVisualization(String title, List<Integer> dataPointsX, List<Long> dataPointsY) {
        this.title = title;
        this.dataPointsX = dataPointsX;
        this.dataPointsY = dataPointsY;
        initializeUI();
    }

    /**
     * Initializes the user interface.
     */
    private void initializeUI() {
        setTitle("Performance Graph Visualization: " + title);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGraph((Graphics2D) g);
    }

    /**
     * Draws the graph on the given Graphics2D object.
     *
     * @param g2 the Graphics2D object
     */
    private void drawGraph(Graphics2D g2) {
        int padding = 50;
        int labelPadding = 50;
        int pointWidth = 4;
        int numberYDivisions = 10;
        int numberXDivisions = Math.max(20, dataPointsX.size() - 1); // Ensure at least 20 divisions
        int width = getWidth() - (2 * padding) - labelPadding;
        int height = getHeight() - (2 * padding) - labelPadding;
        double xScale = ((double) width) / (numberXDivisions);
        double yScale = ((double) height) / (getMaxYValue() - getMinYValue());

        // Draw background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, width, height);
        g2.setColor(Color.BLACK);

        // Draw axes
        g2.drawLine(padding + labelPadding, padding, padding + labelPadding, height + padding);
        g2.drawLine(padding + labelPadding, height + padding, width + padding + labelPadding, height + padding);

        // Draw labels
        g2.drawString("Size", width / 2 + padding, height + padding + 40);
        g2.drawString("Time (ns)", padding - 40, height / 2 + padding);

        // Draw y-axis grid lines and labels
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = height + padding - (i * height / numberYDivisions);
            int y1 = y0;
            if (dataPointsY.size() > 0) {
                g2.setColor(Color.BLACK);
                String yLabel = ((int) (getMinYValue() + (getMaxYValue() - getMinYValue()) * ((i * 1.0) / numberYDivisions))) + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(x0 + pointWidth, y0, width + padding + labelPadding, y1);
        }

        // Draw x-axis grid lines and labels
        for (int i = 0; i < numberXDivisions + 1; i++) {
            int x0 = i * (width) / (numberXDivisions) + padding + labelPadding;
            int x1 = x0;
            int y0 = height + padding;
            int y1 = y0 - pointWidth;
            if (dataPointsX.size() > 1) {
                g2.setColor(Color.BLACK);
                String xLabel = String.format("%d", dataPointsX.get(0) + i * (dataPointsX.get(dataPointsX.size() - 1) - dataPointsX.get(0)) / numberXDivisions);
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(xLabel);
                g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                g2.drawLine(x0, y0, x1, y1);
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawLine(x0, y0 - pointWidth, x1, padding);
            }
        }

        // Draw data points
        g2.setColor(Color.RED);
        for (int i = 0; i < dataPointsX.size(); i++) {
            int x = (int) ((dataPointsX.get(i) - dataPointsX.get(0)) * 1.0 / (dataPointsX.get(dataPointsX.size() - 1) - dataPointsX.get(0)) * width + padding + labelPadding);
            int y = (int) ((getMaxYValue() - dataPointsY.get(i)) * 1.0 / (getMaxYValue() - getMinYValue()) * height + padding);
            g2.fillOval(x - pointWidth / 2, y - pointWidth / 2, pointWidth, pointWidth);
            g2.drawString("(" + dataPointsX.get(i) + ", " + dataPointsY.get(i) + ")", x, y - 5);
        }
    }

    /**
     * Gets the maximum y-axis value.
     *
     * @return the maximum y-axis value
     */
    private long getMaxYValue() {
        return dataPointsY.stream().max(Long::compare).orElse(1L);
    }

    /**
     * Gets the minimum y-axis value.
     *
     * @return the minimum y-axis value
     */
    private long getMinYValue() {
        return dataPointsY.stream().min(Long::compare).orElse(0L);
    }
}
