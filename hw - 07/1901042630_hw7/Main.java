import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        StockDataManager manager = new StockDataManager();

        // Generate random input file
        String inputFilename = "random_input.txt";
        generateRandomInputFile(inputFilename, 5, 3, 5, 1);

        // Process the generated input file
        processInputFile(manager, inputFilename);

        // Perform and print tree traversals
        System.out.println("In-Order Traversal:");
        manager.inOrderTraversal();

        System.out.println("Pre-Order Traversal:");
        manager.preOrderTraversal();

        System.out.println("Post-Order Traversal:");
        manager.postOrderTraversal();

        // Perform performance analysis
        performanceAnalysis(manager);
    }

    /**
     * Processes the input file and executes the commands on the StockDataManager.
     *
     * @param manager the StockDataManager instance
     * @param inputFilename the name of the input file
     */
    private static void processInputFile(StockDataManager manager, String inputFilename) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String command = parts[0];

                switch (command) {
                    case "ADD":
                        manager.addStock(parts[1], Double.parseDouble(parts[2]), Long.parseLong(parts[3]), Long.parseLong(parts[4]));
                        System.out.println("Added stock: " + parts[1]);
                        break;
                    case "REMOVE":
                        manager.removeStock(parts[1]);
                        System.out.println("Removed stock: " + parts[1]);
                        break;
                    case "SEARCH":
                        Stock stock = manager.searchStock(parts[1]);
                        if (stock != null) {
                            System.out.println("Found stock: " + stock);
                        } else {
                            System.out.println("Stock not found: " + parts[1]);
                        }
                        break;
                    case "UPDATE":
                        manager.updateStock(parts[1], parts[2], Double.parseDouble(parts[3]), Long.parseLong(parts[4]), Long.parseLong(parts[5]));
                        System.out.println("Updated stock: " + parts[1] + " to " + parts[2]);
                        break;
                    default:
                        System.out.println("Invalid command: " + command);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a random input file with the specified number of commands.
     *
     * @param filename the name of the file to generate
     * @param addCount the number of ADD commands
     * @param removeCount the number of REMOVE commands
     * @param searchCount the number of SEARCH commands
     * @param updateCount the number of UPDATE commands
     */
    private static void generateRandomInputFile(String filename, int addCount, int removeCount, int searchCount, int updateCount) {
        Random random = new Random();
        List<String> commands = new ArrayList<>();
        String[] symbols = new String[addCount];

        // Generate ADD commands
        for (int i = 0; i < addCount; i++) {
            String symbol = generateRandomSymbol(random);
            symbols[i] = symbol;
            double price = 10 + (1000 - 10) * random.nextDouble();
            long volume = 1000 + (long) ((10000 - 100) * random.nextDouble());
            long marketCap = 1000000 + (long) ((100000 - 1000) * random.nextDouble());
            commands.add(String.format(Locale.US, "ADD %s %.2f %d %d", symbol, price, volume, marketCap));
        }

        // Generate REMOVE commands
        for (int i = 0; i < removeCount; i++) {
            String symbol = symbols[random.nextInt(addCount)];
            commands.add(String.format("REMOVE %s", symbol));
        }

        // Generate SEARCH commands
        for (int i = 0; i < searchCount; i++) {
            String symbol = symbols[random.nextInt(addCount)];
            commands.add(String.format("SEARCH %s", symbol));
        }

        // Generate UPDATE commands
        for (int i = 0; i < updateCount; i++) {
            String symbol = symbols[random.nextInt(addCount)];
            String newSymbol = generateRandomSymbol(random);
            double newPrice = 10 + (1000 - 10) * random.nextDouble();
            long newVolume = 1000 + (long) ((10000 - 100) * random.nextDouble());
            long newMarketCap = 1000000 + (long) ((100000 - 1000) * random.nextDouble());
            commands.add(String.format(Locale.US, "UPDATE %s %s %.2f %d %d", symbol, newSymbol, newPrice, newVolume, newMarketCap));
        }

        // Shuffle the commands to randomize the order
        Collections.shuffle(commands);

        // Write commands to the file
        try (FileWriter writer = new FileWriter(filename)) {
            for (String command : commands) {
                writer.write(command + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a random stock symbol.
     *
     * @param random the Random instance
     * @return the generated stock symbol
     */
    private static String generateRandomSymbol(Random random) {
        int length = 3 + random.nextInt(3);
        StringBuilder symbol = new StringBuilder();
        for (int i = 0; i < length; i++) {
            symbol.append((char) ('A' + random.nextInt(26)));
        }
        return symbol.toString();
    }

    /**
     * Performs performance analysis for different operations on the AVL tree.
     *
     * @param manager the StockDataManager instance
     */
    private static void performanceAnalysis(StockDataManager manager) {
        int[] sizes = {100, 1000, 10000};
        List<Integer> dataPointsX = new ArrayList<>();
        List<Long> addTimes = new ArrayList<>();
        List<Long> removeTimes = new ArrayList<>();
        List<Long> searchTimes = new ArrayList<>();
        List<Long> updateTimes = new ArrayList<>();

        for (int size : sizes) {
            dataPointsX.add(size);
            String filename = "input_" + size + ".txt";
            generateRandomInputFile(filename, size, size / 10, size, size / 10);

            long startTime, endTime;

            // Measure ADD time
            startTime = System.nanoTime();
            processInputFile(manager, filename);
            endTime = System.nanoTime();
            addTimes.add((endTime - startTime) / size);

            // Measure REMOVE time
            startTime = System.nanoTime();
            processInputFile(manager, filename);
            endTime = System.nanoTime();
            removeTimes.add((endTime - startTime) / (size / 10));

            // Measure SEARCH time
            startTime = System.nanoTime();
            processInputFile(manager, filename);
            endTime = System.nanoTime();
            searchTimes.add((endTime - startTime) / size);

            // Measure UPDATE time
            startTime = System.nanoTime();
            processInputFile(manager, filename);
            endTime = System.nanoTime();
            updateTimes.add((endTime - startTime) / (size / 10));
        }

        // Visualize performance data
        new GUIVisualization("ADD Operation", dataPointsX, addTimes);
        new GUIVisualization("REMOVE Operation", dataPointsX, removeTimes);
        new GUIVisualization("SEARCH Operation", dataPointsX, searchTimes);
        new GUIVisualization("UPDATE Operation", dataPointsX, updateTimes);
    }
}
