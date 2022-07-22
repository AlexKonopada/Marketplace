import java.util.Scanner;

class MarketPlace {
    public static void main(String[] args) {
        while (true)
            Menu();
    }

    public static void Menu(){
        System.out.println("Welcome to the Marketplace");
        System.out.println("Possible operations: " + "\n" +
                           "Add customer : 1" + "\n" +
                           "Add product : 2" + "\n" +
                           "See the customers : 3" + "\n" +
                           "See the products : 4" + "\n" +
                           "Make a purchase : 5");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press the appropriate number for the specific operation. For example: 1 - to add customer, 2 - to add product...");
        int userChoice = scanner.nextInt();
        sevenOptions(userChoice);

    }
    public static  void customerCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customer's first name: ");
        String firstName = scanner.nextLine();

        System.out.println("Customer's last name: ");
        String lastName = scanner.nextLine();

        System.out.println("Customer's money: ");
        int money = scanner.nextInt();

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
        System.out.println("Choose customer: ");
        int customerId = scanner.nextInt();

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

        }
        else if (index == 7) {

        }

    }
    public static class Customer {
        private int id;
        private String firstName;
        private String lastName;
        private int money;

        public static Customer[] userStorage = new Customer[0];

        public static Product[] purchasedProducts = new Product[0];
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
            Product[] newPurchasePacker = new Product[purchasedProducts.length + 1];

            for (int i = 0; i < newPurchasePacker.length; i++)
                newPurchasePacker[i] = purchasedProducts[i];

            newPurchasePacker[newPurchasePacker.length - 1] = Product.productStorage[productId];

            purchasedProducts = newPurchasePacker;
        }
        public static void showPurchaseBasket(){
            System.out.println("Purchased products: ");
            for (int i = 0; i < purchasedProducts.length; i++){
                System.out.println(purchasedProducts[i]);
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
        }

    }

    public static class Product {
        private int id;
        private String name;
        private int price;

        public static Product[] productStorage = new Product[0];

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




    }

}

