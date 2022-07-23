import java.util.Scanner;

class MarketPlace {
    public static void main(String[] args) {
        Menu();
    }
    public static void Menu(){
        while (true) {
            System.out.println("Welcome to the Marketplace");
            System.out.println("Possible operations: " + "\n" +
                    "Add customer : 1" + "\n" +
                    "Add product : 2" + "\n" +
                    "See the customers : 3" + "\n" +
                    "See the products : 4" + "\n" +
                    "Make a purchase : 5" + "\n" +
                    "See customer's purchases : 6" + "\n" +
                    "See product's owners: 7" + "\n" +
                    "Exit Market : 0");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Press the appropriate number for the specific operation. For example: 1 - to add customer, 2 - to add product...");
            int userChoice = scanner.nextInt();
            sevenOptions(userChoice);

            if (userChoice == 0)
                System.out.println("Thanks for visiting the market!!!");
                break;
        }
    }

    public static void customerCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customer's first name: ");
        String firstName = null;
        firstName = scanner.nextLine();
        System.out.println("Customer's last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Customer's money: ");
        int money = 0;
        try {
            money = scanner.nextInt();
        }
        catch (Exception e) {
            System.out.println("Wrong input");
        }
        Customer.addCustomer(firstName, lastName, money);
    }

    public static void productCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Product: ");
        String name = scanner.nextLine();

        System.out.println("Price for it: ");
        int price = scanner.nextInt();

        Product.addProduct(name, price);
    }

    public static void purchaseCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a customer who wants to buy a product");
        Customer.showCustomers();
        int id = scanner.nextInt();
        Customer.makePurchase(id);
    }

    public static void showProductsCase() {
        Scanner scanner = new Scanner(System.in);
        Customer.showCustomers();
        System.out.println("Choose customer's id: ");
        int customerId = scanner.nextInt();
        Customer.userStorage[customerId-1].showPurchaseBasket();
    }

    public static void showOwnersCase() {
        Scanner scanner = new Scanner(System.in);
        Product.showProducts();
        System.out.println("Choose product's id: ");
        int productId = scanner.nextInt();
        Product.productStorage[productId-1].showOwners();
    }
    public static void sevenOptions(int index){
        if (index == 1) {
            customerCase();
        }
        else if (index == 2) {
            productCase();
        }
        else if (index == 3) {
            Customer.showCustomers();
        }
        else if (index == 4) {
            Product.showProducts();
        }
        else if (index == 5) {
            purchaseCase();
        }
        else if (index == 6) {
            showProductsCase();
        }
        else if (index == 7) {
            showOwnersCase();
        }

    }
    public static class Customer {
        private int id;
        private String firstName;
        private String lastName;
        private int money;

        public static Customer[] userStorage = new Customer[0];

        public static String[] purchasedProducts = new String[0];
        public Customer(int id, String firstName, String lastName, int money) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.money = money;
        }

        public static void addCustomer(String firstName, String lastName, int money) {
            Customer[] newUserStorage = new Customer[userStorage.length + 1];
            for (int i = 0; i < userStorage.length; i++)
                newUserStorage[i] = userStorage[i];

            newUserStorage[newUserStorage.length - 1] = new Customer(newUserStorage.length, firstName, lastName, money);

            userStorage = newUserStorage;
        }

        public static void showCustomers() {
            if (userStorage.length == 0)
                System.out.println("No customers yet");
            else {
                for (int i = 0; i < userStorage.length; i++)
                    System.out.println(userStorage[i].firstName + ": " + userStorage[i].id);
            }
        }

        public static void addProductToPurchase(int productId) {
            String[] newPurchasePack = new String[purchasedProducts.length + 1];

            for (int i = 0; i < purchasedProducts.length; i++)
                newPurchasePack[i] = purchasedProducts[i];

            newPurchasePack[newPurchasePack.length-1] = Product.productStorage[productId-1].name;

            purchasedProducts = newPurchasePack;
        }

        public static void showPurchaseBasket(){
            System.out.println("Purchased products: ");
            for (String product: purchasedProducts){
                System.out.println(product);
            }
        }
        public static void makePurchase(int customerId) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Product?");
            Product.showProducts();
            int productId = scanner.nextInt();
            userStorage[customerId-1].money -= Product.productStorage[productId-1].price;
            System.out.println("Customer's amount of money: " + userStorage[customerId-1].money);
            addProductToPurchase(productId);
            Product.addOwner(customerId);
        }

    }

    public static class Product {
        private int id;
        private String name;
        private int price;

        public static Product[] productStorage = new Product[0];

        public static String[] productOwners = new String[0];

        public Product(int id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public static void addProduct(String name, int price) {
            Product[] newProductStorage = new Product[productStorage.length + 1];
            for (int i = 0; i < productStorage.length; i++)
                newProductStorage[i] = productStorage[i];

            newProductStorage[newProductStorage.length - 1] = new Product(newProductStorage.length, name, price);

            productStorage = newProductStorage;
        }

        public static void showProducts() {
            if (productStorage.length == 0) {
                System.out.println("No products yet");
            }
            else {
                for (int i = 0; i < productStorage.length; i++)
                    System.out.println(productStorage[i].name + ": " + productStorage[i].id);
            }
        }

        public static void addOwner(int ownerId){
            String[] newOwner = new String[productOwners.length + 1];

            for (int i = 0; i < productOwners.length; i++)
                newOwner[i] = productOwners[i];

            newOwner[newOwner.length-1] = Product.productStorage[ownerId-1].name;

            productOwners = newOwner;
        }

        public static void showOwners(){
            System.out.println("Owner's of the product: ");
            for (String owner: productOwners){
                System.out.println(owner);
            }
        }

    }

}
