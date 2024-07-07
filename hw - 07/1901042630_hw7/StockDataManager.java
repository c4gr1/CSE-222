/**
 * Manages the AVL tree and provides methods for data management such as adding stocks,
 * removing stocks, searching for stocks, and updating stock information.
 */
public class StockDataManager {
    private AVLTree avlTree;

    /**
     * Constructs a new StockDataManager.
     */
    public StockDataManager() {
        this.avlTree = new AVLTree();
    }

    /**
     * Adds a stock to the AVL tree.
     *
     * @param symbol the stock symbol
     * @param price the stock price
     * @param volume the trading volume
     * @param marketCap the market capitalization
     */
    public void addStock(String symbol, double price, long volume, long marketCap) {
        Stock stock = new Stock(symbol, price, volume, marketCap);
        avlTree.insert(stock);
    }

    /**
     * Removes a stock from the AVL tree.
     *
     * @param symbol the stock symbol to remove
     */
    public void removeStock(String symbol) {
        avlTree.delete(symbol);
    }

    /**
     * Searches for a stock in the AVL tree.
     *
     * @param symbol the stock symbol to search for
     * @return the stock if found, otherwise null
     */
    public Stock searchStock(String symbol) {
        return avlTree.search(symbol);
    }

    /**
     * Updates a stock in the AVL tree.
     *
     * @param symbol the current stock symbol
     * @param newSymbol the new stock symbol
     * @param newPrice the new stock price
     * @param newVolume the new trading volume
     * @param newMarketCap the new market capitalization
     */
    public void updateStock(String symbol, String newSymbol, double newPrice, long newVolume, long newMarketCap) {
        Stock stock = avlTree.search(symbol);
        if (stock != null) {
            stock.setSymbol(newSymbol);
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
            avlTree.insert(stock);
            avlTree.delete(symbol);
        }
    }

    /**
     * Performs an in-order traversal of the AVL tree and prints the stocks.
     */
    public void inOrderTraversal() {
        avlTree.inOrderTraversal();
    }

    /**
     * Performs a pre-order traversal of the AVL tree and prints the stocks.
     */
    public void preOrderTraversal() {
        avlTree.preOrderTraversal();
    }

    /**
     * Performs a post-order traversal of the AVL tree and prints the stocks.
     */
    public void postOrderTraversal() {
        avlTree.postOrderTraversal();
    }
}
