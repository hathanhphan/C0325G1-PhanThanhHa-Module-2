package ss14_sort_algorithm.thuc_hanh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class InsertionSortVisualization extends JFrame {
    private static final int ARRAY_SIZE = 20;
    private static final int MAX_VALUE = 200;
    private static final int MIN_DELAY = 10;
    private static final int MAX_DELAY = 1000;

    private int[] array;
    private JPanel arrayPanel;
    private JSlider speedSlider;
    private JButton sortButton;
    private JButton resetButton;
    private boolean isSorting = false;
    private int currentIndex = -1;
    private int comparingIndex = -1;

    public InsertionSortVisualization() {
        setTitle("Insertion Sort Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        initializeArray();
        setupUI();
    }

    private void initializeArray() {
        array = new int[ARRAY_SIZE];
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt(MAX_VALUE) + 10;
        }
    }

    private void setupUI() {
        setLayout(new BorderLayout());

        // Panel điều khiển phía trên
        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBackground(Color.LIGHT_GRAY);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Thanh điều chỉnh tốc độ
        JLabel speedLabel = new JLabel("Speed:");
        speedLabel.setFont(new Font("Arial", Font.BOLD, 14));

        speedSlider = new JSlider(1, 100, 50);
        speedSlider.setPreferredSize(new Dimension(200, 30));
        speedSlider.setBackground(Color.LIGHT_GRAY);

        // Nút điều khiển
        sortButton = new JButton("Insertion Sort");
        sortButton.setFont(new Font("Arial", Font.BOLD, 12));
        sortButton.setPreferredSize(new Dimension(120, 30));
        sortButton.addActionListener(new SortButtonListener());

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 12));
        resetButton.setPreferredSize(new Dimension(80, 30));
        resetButton.addActionListener(e -> resetArray());

        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
        controlPanel.add(Box.createHorizontalStrut(20));
        controlPanel.add(sortButton);
        controlPanel.add(resetButton);

        // Panel hiển thị mảng
        arrayPanel = new ArrayPanel();
        arrayPanel.setBackground(Color.WHITE);
        arrayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(controlPanel, BorderLayout.NORTH);
        add(arrayPanel, BorderLayout.CENTER);
    }

    private class ArrayPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawArray(g);
        }

        private void drawArray(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int barWidth = (panelWidth - 40) / ARRAY_SIZE;
            int maxBarHeight = panelHeight - 60;

            for (int i = 0; i < array.length; i++) {
                int barHeight = (int) ((double) array[i] / MAX_VALUE * maxBarHeight);
                int x = 20 + i * barWidth;
                int y = panelHeight - barHeight - 30;

                // Chọn màu cho cột
                Color barColor = getBarColor(i);
                g2d.setColor(barColor);
                g2d.fillRect(x, y, barWidth - 2, barHeight);

                // Viền cột
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, barWidth - 2, barHeight);

                // Hiển thị giá trị
                g2d.setFont(new Font("Arial", Font.PLAIN, 10));
                FontMetrics fm = g2d.getFontMetrics();
                String value = String.valueOf(array[i]);
                int textX = x + (barWidth - fm.stringWidth(value)) / 2;
                int textY = panelHeight - 10;
                g2d.drawString(value, textX, textY);
            }
        }

        private Color getBarColor(int index) {
            if (index == currentIndex) {
                return Color.RED; // Phần tử đang được chèn
            } else if (index == comparingIndex) {
                return Color.ORANGE; // Phần tử đang so sánh
            } else if (index < currentIndex) {
                return Color.GREEN; // Phần đã được sắp xếp
            } else {
                return new Color(173, 216, 230); // Màu xanh nhạt
            }
        }
    }

    private class SortButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isSorting) {
                startSorting();
            }
        }
    }

    private void startSorting() {
        isSorting = true;
        sortButton.setEnabled(false);
        resetButton.setEnabled(false);
        speedSlider.setEnabled(false);

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                insertionSort();
                return null;
            }

            @Override
            protected void done() {
                isSorting = false;
                sortButton.setEnabled(true);
                resetButton.setEnabled(true);
                speedSlider.setEnabled(true);
                currentIndex = -1;
                comparingIndex = -1;
                arrayPanel.repaint();
            }
        };
        worker.execute();
    }

    private void insertionSort() throws InterruptedException {
        for (int i = 1; i < array.length; i++) {
            currentIndex = i;
            int key = array[i];
            int j = i - 1;

            arrayPanel.repaint();
            Thread.sleep(getDelay());

            // Di chuyển các phần tử lớn hơn key về phía sau
            while (j >= 0 && array[j] > key) {
                comparingIndex = j;
                arrayPanel.repaint();
                Thread.sleep(getDelay());

                array[j + 1] = array[j];
                j = j - 1;

                arrayPanel.repaint();
                Thread.sleep(getDelay());
            }

            array[j + 1] = key;
            comparingIndex = -1;
            arrayPanel.repaint();
            Thread.sleep(getDelay());
        }
    }

    private int getDelay() {
        int sliderValue = speedSlider.getValue();
        return MAX_DELAY - (sliderValue - 1) * (MAX_DELAY - MIN_DELAY) / 99;
    }

    private void resetArray() {
        if (!isSorting) {
            initializeArray();
            currentIndex = -1;
            comparingIndex = -1;
            arrayPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Sử dụng giao diện mặc định, không cần set Look and Feel
                new InsertionSortVisualization().setVisible(true);
            }
        });
    }
}
