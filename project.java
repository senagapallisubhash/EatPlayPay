import java.util.*;
import java.text.DecimalFormat;

class project {

    // Config
    static final int WIDTH = 80;
    static final String STAR = "*";
    static final DecimalFormat DF = new DecimalFormat("0.00");
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    // ANSI
    static final String RESET = "\u001B[0m", BOLD = "\u001B[1m", CYAN = "\u001B[36m", BRIGHT_CYAN = "\u001B[96m",
            BRIGHT_MAGENTA = "\u001B[95m", GREEN = "\u001B[32m", YELLOW = "\u001B[33m", RED = "\u001B[31m",
            BRIGHT_YELLOW = "\u001B[93m", BRIGHT_BLUE = "\u001B[94m", BRIGHT_PINK = "\u001B[95m",
            BG_BLACK = "\u001B[40m";

    // Customer & cart
    static String customerUsername, customerName, customerMobile, customerAddress, customerPincode, customerPassword;
    static ArrayList<String> cartItems = new ArrayList<>();
    static ArrayList<Integer> cartQty = new ArrayList<>();
    static ArrayList<Integer> cartUnitPrice = new ArrayList<>();

    // Menus (kept as in original)


    static String[] vegDawat = { "Paneer Butter Masala", "Veg Biryani", "Gobi 65", "Dal Fry", "Veg Manchurian",
            "Aloo Paratha", "Curd Rice", "Mushroom Fry", "Veg Thali", "Masala Dosa" };
    static int[] vegDawatPrice = { 180, 150, 120, 130, 140, 90, 80, 160, 200, 100 };

    static String[] nonvegDawat = { "Chicken Biryani", "Mutton Curry", "Fish Fry", "Chicken 65", "Egg Curry",
            "Prawns Fry", "Chicken Tikka", "Mutton Biryani", "Fish Curry", "Pepper Chicken" };
    static int[] nonvegDawatPrice = { 250, 300, 220, 180, 120, 260, 240, 320, 200, 210 };

    static String[] vegDilse = { "Paneer Tikka", "Veg Fried Rice", "Mushroom Curry", "Kaju Paneer", "Veg Noodles",
            "Veg Pizza", "Idli Sambar", "Veg Cutlet", "Curd Rice", "Puri Curry" };
    static int[] vegDilsePrice = { 200, 140, 160, 220, 130, 180, 70, 90, 80, 110 };

    static String[] nonvegDilse = { "Chicken Curry", "Chicken Noodles", "Fish Biryani", "Mutton Fry",
            "Prawns Curry", "Egg Biryani", "Chicken Lollipop", "Grilled Chicken", "Mutton Keema",
            "Special Chicken Biryani" };
    static int[] nonvegDilsePrice = { 220, 150, 260, 300, 280, 160, 170, 250, 310, 280 };

    static String[] vegChinese = { "Veg Fried Rice", "Veg Noodles", "Paneer Manchurian", "Chilli Mushroom",
            "Spring Roll", "Paneer 65", "Crispy Corn", "Veg Schezwan Rice", "Veg Momos", "Paneer Momos" };
    static int[] vegChinesePrice = { 140, 130, 160, 150, 120, 170, 110, 150, 100, 120 };

    static String[] nonvegChinese = { "Chicken Fried Rice", "Egg Noodles", "Chicken Manchurian", "Chilli Chicken",
            "Chicken Lollipop", "Fish Manchurian", "Prawns Fried Rice", "Schezwan Chicken Rice", "Chicken Momos",
            "Prawns Momos" };
    static int[] nonvegChinesePrice = { 160, 140, 180, 170, 190, 220, 240, 200, 130, 160 };

    static String[] desserts = { "Gulab Jamun", "Rasmalai", "Ice Cream Cup", "Chocolate Lava Cake", "Fruit Custard",
            "Brownie", "Kesari", "Payasam", "Matka Kulfi", "Cheesecake" };
    static int[] dessertsPrice = { 60, 90, 40, 120, 70, 100, 50, 60, 80, 140 };

    static String[] drinks = { "Mineral Water (1 bottle)", "Thums Up", "Coke", "Sprite", "Fresh Lime Juice",
            "Mango Shake", "Cold Coffee", "Iced Tea", "Badam Milk", "Oreo Shake" };
    static int[] drinksPrice = { 25, 40, 40, 40, 50, 80, 90, 60, 70, 100 };

    // Billing state
    static double lastSubtotal = 0.0;
    static int lastDiscountPercent = 0;
    static double lastFinalAmount = 0.0;

    // Validation & OTP
    static boolean validUsername(String u) { return u != null && u.trim().length() >= 4; }
    static boolean validMobile(String m) { return m != null && m.matches("[6-9]\\d{9}"); }
    static boolean validPincode(String p) { return p != null && p.matches("\\d{6}"); }
    static boolean validPassword(String p) { return p != null && p.length() >= 8; }
    static int genOTP() { return 100000 + rand.nextInt(900000); }

    // Helpers
    static void centerPrint(String s, String color) {
        System.out.println();
        if (s == null) s = "";
        if (s.length() >= WIDTH) {
            System.out.println(color + s + RESET);
            return;
        }
        int pad = (WIDTH - s.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pad; i++) sb.append(" ");
        sb.append(color + s + RESET);
        System.out.println(sb.toString());
    }

    static void centerPrint(String s) { centerPrint(s, CYAN); }

    static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s);
        return sb.toString();
    }

    static void cyberBoxTitle(String title) {
        System.out.println();
        String top = BRIGHT_MAGENTA + "\u2554" + repeat("\u2550", WIDTH - 2) + "\u2557" + RESET;
        String bot = BRIGHT_MAGENTA + "\u255A" + repeat("\u2550", WIDTH - 2) + "\u255D" + RESET;

        System.out.println(top);
        centerPrint(title, BRIGHT_YELLOW);
        System.out.println(bot);
    }

    static void printEatPlayPayBanner() {
        
        String[] banner = {
                "/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\",
                "|                                                                                                          |",
                "|  EEEEE      AAAAA     TTTTT           PPPPP     L       AAAAA     Y   Y        PPPPP     AAAAA     Y   Y |",
                "|  E          A   A       T             P   P     L       A   A      Y Y         P   P     A   A      Y Y  |",
                "|  EEEE       AAAAA       T      ==     PPPPP     L       AAAAA       Y    ==    PPPPP     AAAAA       Y   |",
                "|  E          A   A       T             P         L       A   A       Y          P         A   A       Y   |",
                "|  EEEEE      A   A       T             P         LLLLL   A   A       Y          P         A   A       Y   |",
                "|                                                                                                          |",
                "/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\" };

        for (int i = 0; i < banner.length; i++) {
            String line = banner[i];

            if (i == 0 || i == banner.length - 1)
                System.out.println(BRIGHT_PINK + BG_BLACK + line + RESET);

            else if (i == 1 || i == banner.length - 2)
                System.out.println(BRIGHT_PINK + BG_BLACK + line + RESET);

            else {
                String content = line.substring(1, line.length() - 1);
                System.out.println(
                        BRIGHT_PINK + BG_BLACK + "|" + RESET + BRIGHT_BLUE + BG_BLACK + content + RESET + BRIGHT_PINK
                                + BG_BLACK + "|" + RESET);
            }
        }
    }

    // Menus & Cart
    static void showMenuAndAdd(String title, String[] items, int[] prices) {
        System.out.println();
        cyberBoxTitle(" " + title + " ");

        for (int i = 0; i < items.length; i++) {
            String line = String.format("%2d) %-40s  %d", i + 1, items[i], prices[i]);
            centerPrint(line, BRIGHT_CYAN);
        }

        System.out.println(repeat(STAR, WIDTH));

        while (true) {
            System.out.print("Enter item number to add (or '0' to go back): ");
            String s = sc.nextLine().trim();
            int idx;

            try {
                idx = Integer.parseInt(s);
            } catch (Exception e) {
                centerPrint("Invalid input. Enter a number.", RED);
                continue;
            }

            if (idx == 0) return;
            if (idx < 1 || idx > items.length) {
                centerPrint("Invalid item number.", RED);
                continue;
            }

            int price = prices[idx - 1];
            System.out.print("Enter quantity: ");
            int qty;

            try {
                qty = Integer.parseInt(sc.nextLine().trim());
                if (qty < 1) qty = 1;
            } catch (Exception e) {
                qty = 1;
            }

            cartItems.add(items[idx - 1]);
            cartQty.add(qty);
            cartUnitPrice.add(price);

            centerPrint(items[idx - 1] + " x" + qty + " added to cart.", GREEN);
            System.out.print("Add more from this menu? (y/n): ");

            String c = sc.nextLine().trim().toLowerCase();
            if (!c.equals("y")) return;
        }
    }

    static void showCart() {
        System.out.println();
        cyberBoxTitle(" YOUR CART ");

        if (cartItems.isEmpty()) {
            centerPrint("Cart is empty.", RED);
        } else {
            double subtotal = 0;

            for (int i = 0; i < cartItems.size(); i++) {
                int lineTotal = cartUnitPrice.get(i) * cartQty.get(i);
                String line = String.format("%2d) %-35s x%-3d  %d",
                        i + 1, cartItems.get(i), cartQty.get(i), lineTotal);
                centerPrint(line, BRIGHT_CYAN);
                subtotal += lineTotal;
            }

            centerPrint(repeat("-", WIDTH), YELLOW);
            centerPrint("Subtotal (before discount): " + DF.format(subtotal), BRIGHT_YELLOW);
        }

        System.out.println(repeat(STAR, WIDTH));
    }

    // Spinner & Games
    static String spinnerSelectGame() {
        System.out.println();
        cyberBoxTitle(" SPIN WHEEL ");

        long start = System.currentTimeMillis();
        char[] spinner = { '|', '/', '-', '\\' };
        int i = 0;

        while (System.currentTimeMillis() - start < 3000) {
            System.out.print("\r" + spinner[i % spinner.length] + " Spinning...");
            try { Thread.sleep(120); } catch (Exception e) {}
            i++;
        }

        System.out.print("\r");

        String[] games = { "Food Riddle", "Hangman Food", "Word Puzzle" };
        String chosen = games[rand.nextInt(games.length)];

        centerPrint("Wheel chose -> " + chosen, BRIGHT_YELLOW);
        System.out.println(repeat(STAR, WIDTH));

        return chosen;
    }

    static boolean playFoodRiddle() {
        System.out.println();

        String[][] qs = {
                { "I am yellow and sweet; people love my pulp in summer. What am I?", "MANGO" },
                { "Round sweet, soaked in syrup, often served warm. Name me.", "GULAB JAMUN" },
                { "A triangular fried snack, often filled with spiced potatoes. What am I?", "SAMOSA" },
                { "I am a cold creamy dessert in a clay pot, a popular frozen treat. What am I?", "KULFI" },
                { "A sweet made from milk, condensed and shaped into balls soaked in saffron syrup. What am I?",
                        "RASMALAI" }
        };

        int idx = rand.nextInt(qs.length);
        String q = qs[idx][0], ans = qs[idx][1];

        cyberBoxTitle(" FOOD RIDDLE ");
        centerPrint(q, BRIGHT_CYAN);

        System.out.print("Your answer: ");
        String user = sc.nextLine().trim().toUpperCase();

        if (user.equals(ans) || user.contains(ans) || ans.contains(user)) {
            centerPrint("Correct!", GREEN);
            return true;
        } else {
            centerPrint("Incorrect. Correct answer: " + ans, RED);
            return false;
        }
    }

    static boolean playHangman() {
        System.out.println();

        String[] words = { "BIRYANI", "NOODLES", "MANCHURIAN", "SPRINGROLL", "PANEER", "PIZZA", "PASTA", "MOMOS",
                "DOSA", "KULFI" };

        String secret = words[rand.nextInt(words.length)];
        char[] shown = new char[secret.length()];
        Arrays.fill(shown, '_');

        int attempts = 6;
        Set<Character> guessed = new HashSet<>();

        cyberBoxTitle(" HANGMAN (FOOD) ");

        while (attempts > 0) {
            centerPrint("Word: " + String.valueOf(shown).replaceAll("", " ").trim(), BRIGHT_CYAN);
            centerPrint("Attempts left: " + attempts, YELLOW);

            System.out.print("Enter a letter: ");
            String s = sc.nextLine().trim().toUpperCase();

            if (s.length() == 0) {
                centerPrint("Enter a letter.", RED);
                continue;
            }

            char ch = s.charAt(0);

            if (guessed.contains(ch)) {
                centerPrint("Already guessed.", RED);
                continue;
            }

            guessed.add(ch);

            boolean found = false;
            for (int i = 0; i < secret.length(); i++)
                if (secret.charAt(i) == ch) {
                    shown[i] = ch;
                    found = true;
                }

            if (!found) {
                attempts--;
                centerPrint("Wrong guess.", RED);
            }

            if (String.valueOf(shown).equals(secret)) {
                centerPrint("You guessed it: " + secret, GREEN);
                return true;
            }
        }

        centerPrint("Out of attempts! The word was: " + secret, RED);
        return false;
    }

    static boolean playWordPuzzle() {
        System.out.println();

        String[] words = { "CHOWMEIN", "MANCHURIAN", "SPRINGROLL", "DIMSUM", "MOMO", "SZECHUAN", "HOTPOT",
                "NOODLES" };

        String chosen = words[rand.nextInt(words.length)];
        List<Character> chars = new ArrayList<>();

        for (char c : chosen.toCharArray()) chars.add(c);
        Collections.shuffle(chars);

        StringBuilder sb = new StringBuilder();
        for (char c : chars) sb.append(c);

        String scrambled = sb.toString();

        cyberBoxTitle(" WORD PUZZLE ");
        centerPrint("Unscramble this: " + scrambled, BRIGHT_CYAN);

        System.out.print("Your answer: ");
        String ans = sc.nextLine().trim().toUpperCase().replaceAll("\\s+", "");

        if (ans.equals(chosen)) {
            centerPrint("Correct!", GREEN);
            return true;
        } else {
            centerPrint("Incorrect. Correct answer: " + chosen, RED);
            return false;
        }
    }

    // Billing
    static double computeSubtotal() {
        double sum = 0;
        for (int i = 0; i < cartItems.size(); i++)
            sum += cartUnitPrice.get(i) * cartQty.get(i);
        return sum;
    }

    static double computeAndShowBillApplyDiscountIfWin(boolean wonGame) {
        double subtotal = computeSubtotal();
        centerPrint("Subtotal (before discount): " + DF.format(subtotal), BRIGHT_YELLOW);

        int discount = wonGame ? 20 : 0;
        double discountAmount = subtotal * discount / 100.0;
        double afterDiscount = subtotal - discountAmount;

        double cgst = afterDiscount * 0.05, sgst = afterDiscount * 0.05;
        double payable = afterDiscount + cgst + sgst;

        lastSubtotal = subtotal;
        lastDiscountPercent = discount;
        lastFinalAmount = payable;

        cyberBoxTitle(" FINAL BILL ");
        centerPrint(String.format("Subtotal           : %s", DF.format(subtotal)), BRIGHT_YELLOW);
        centerPrint(String.format("Discount (%d%%)      : -%s", discount, DF.format(discountAmount)),
                BRIGHT_YELLOW);
        centerPrint(String.format("After Discount     : %s", DF.format(afterDiscount)), BRIGHT_YELLOW);
        centerPrint(String.format("CGST (5%%)         : %s", DF.format(cgst)), BRIGHT_YELLOW);
        centerPrint(String.format("SGST (5%%)         : %s", DF.format(sgst)), BRIGHT_YELLOW);
        centerPrint(String.format("Amount Payable     : %s", DF.format(payable)), GREEN);

        System.out.println(repeat(STAR, WIDTH));
        return payable;
    }

    // Payment
    static boolean doPayment(double amount) {
        System.out.println();

        if (amount <= 0) {
            centerPrint("No amount to pay.", RED);
            return false;
        }

        cyberBoxTitle(" PAYMENT METHODS ");
        centerPrint("1) UPI (mobile)");
        centerPrint("2) Card");
        centerPrint("3) Cash on Delivery (COD)");

        System.out.println(repeat(STAR, WIDTH));
        System.out.print("Enter choice: ");
        String choice = sc.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("Enter UPI (mobile number): ");
            String upi = sc.nextLine().trim();

            if (!validMobile(upi)) {
                centerPrint("Invalid UPI mobile.", RED);
                return false;
            }

            int otp = genOTP();
            centerPrint("Payment OTP (simulation): " + otp, YELLOW);

            System.out.print("Enter OTP: ");
            String ent = sc.nextLine().trim();

            if (!ent.matches("\\d+") || Integer.parseInt(ent) != otp) {
                centerPrint("Invalid OTP. Payment failed.", RED);
                return false;
            }

            centerPrint("Payment of " + DF.format(amount) + " successful via UPI " + upi, GREEN);
            printReceipt(lastSubtotal, lastDiscountPercent, lastFinalAmount, "UPI", upi, null, null);
            return true;

        } else if (choice.equals("2")) {
            System.out.print("Enter Bank Name: ");
            String bank = sc.nextLine().trim();

            System.out.print("Enter 16-digit Card Number: ");
            String card = sc.nextLine().trim();

            if (!card.matches("\\d{16}")) {
                centerPrint("Invalid card number.", RED);
                return false;
            }

            System.out.print("Enter Expiry (MM/YY): ");
            String expiry = sc.nextLine().trim();

            if (!expiry.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                centerPrint("Invalid expiry format.", RED);
                return false;
            }

            System.out.print("Enter CVV (3 digits): ");
            String cvv = sc.nextLine().trim();

            if (!cvv.matches("\\d{3}")) {
                centerPrint("Invalid CVV.", RED);
                return false;
            }

            int otp = genOTP();
            centerPrint("Payment OTP (simulation): " + otp, YELLOW);

            System.out.print("Enter OTP: ");
            String ent = sc.nextLine().trim();

            if (!ent.matches("\\d+") || Integer.parseInt(ent) != otp) {
                centerPrint("Invalid OTP. Payment failed.", RED);
                return false;
            }

            centerPrint("Payment of " + DF.format(amount) + " successful via Card ending " + card.substring(12),
                    GREEN);

            printReceipt(lastSubtotal, lastDiscountPercent, lastFinalAmount, "CARD",
                    "XXXX-XXXX-XXXX-" + card.substring(12), bank, expiry);

            return true;

        } else if (choice.equals("3")) {
            centerPrint("Order placed. Pay " + DF.format(amount) + " on delivery (COD).", GREEN);
            centerPrint("YOUR COD ORDER IS SUCCESSFULLY PLACED!", GREEN);

            printReceipt(lastSubtotal, lastDiscountPercent, lastFinalAmount, "COD", null, null, null);
            return true;

        } else {
            centerPrint("Invalid payment choice.", RED);
            return false;
        }
    }

    // Receipt (modified to print COD-specific final line)
    static void printReceipt(double subtotal, int discountPercent, double amountPayable, String paymentMode,
                             String upiOrCardLast4, String bankName, String expiry) {

        System.out.println();

        double discountAmount = subtotal * discountPercent / 100.0;
        double afterDiscount = subtotal - discountAmount;
        double cgst = afterDiscount * 0.05;
        double sgst = afterDiscount * 0.05;

        cyberBoxTitle(" RECEIPT ");

        centerPrint("Customer : " +
                ((customerName == null || customerName.isEmpty()) ? customerUsername : customerName),
                BRIGHT_YELLOW);
        centerPrint("Mobile   : " + customerMobile, BRIGHT_YELLOW);
        centerPrint("Address  : " + customerAddress, BRIGHT_YELLOW);
        centerPrint("Pincode  : " + customerPincode, BRIGHT_YELLOW);

        centerPrint(repeat("-", WIDTH), YELLOW);

        for (int i = 0; i < cartItems.size(); i++) {
            int lineTotal = cartUnitPrice.get(i) * cartQty.get(i);
            centerPrint(String.format("%2d) %-35s x%-3d  %d", i + 1, cartItems.get(i), cartQty.get(i), lineTotal),
                    CYAN);
        }

        centerPrint(repeat("-", WIDTH), YELLOW);

        centerPrint(String.format("Subtotal        : %s", DF.format(subtotal)), BRIGHT_YELLOW);
        centerPrint(String.format("Discount (%d%%)   : -%s", discountPercent, DF.format(discountAmount)),
                BRIGHT_YELLOW);
        centerPrint(String.format("After Discount  : %s", DF.format(afterDiscount)), BRIGHT_YELLOW);
        centerPrint(String.format("CGST (5%%)      : %s", DF.format(cgst)), BRIGHT_YELLOW);
        centerPrint(String.format("SGST (5%%)      : %s", DF.format(sgst)), BRIGHT_YELLOW);
        centerPrint(String.format("TOTAL PAYABLE   : %s", DF.format(amountPayable)), GREEN);

        centerPrint(repeat(STAR, WIDTH), BRIGHT_MAGENTA);

        centerPrint("Payment Mode: " + paymentMode, BRIGHT_CYAN);

        if ("UPI".equals(paymentMode) && upiOrCardLast4 != null)
            centerPrint("UPI ID: " + upiOrCardLast4, BRIGHT_CYAN);

        if ("CARD".equals(paymentMode) && upiOrCardLast4 != null) {
            centerPrint("Card: " + upiOrCardLast4, BRIGHT_CYAN);
            if (bankName != null && !bankName.isEmpty()) centerPrint("Bank: " + bankName, BRIGHT_CYAN);
            if (expiry != null && !expiry.isEmpty()) centerPrint("Expiry: " + expiry, BRIGHT_CYAN);
        }

        centerPrint(repeat(STAR, WIDTH), BRIGHT_MAGENTA);

        if ("COD".equals(paymentMode))
	{
            centerPrint("YOUR COD ORDER IS SUCCESSFULLY PLACED!", GREEN);
	    centerPrint(" o                                  ", GREEN);
	    centerPrint("/|\\ FLASH MAN - ORDER DELIVERED!!!!", GREEN);
	    centerPrint("/ \\                                ", GREEN);

	}
        else
	{
            centerPrint("PAYMENT SUCCESSFUL - THANK YOU. VISIT AGAIN!", GREEN);	
	    centerPrint(" o                                  ", GREEN);
	    centerPrint("/|\\ FLASH MAN - ORDER DELIVERED!!!!", GREEN);
	    centerPrint("/ \\                                ", GREEN);
	}

        centerPrint(repeat(STAR, WIDTH), BRIGHT_MAGENTA);
    }

    // Main
    public static void main(String[] args) {

        printEatPlayPayBanner();

        // Signup
        cyberBoxTitle(" SIGNUP ");

        while (true) {
            System.out.print("Enter username (min 4 chars): ");
            String u = sc.nextLine().trim();
            if (!validUsername(u)) {
                centerPrint("Invalid username.", RED);
                continue;
            }
            customerUsername = u;
            break;
        }

        while (true) {
            System.out.print("Enter mobile (10 digits starting 6-9): ");
            String m = sc.nextLine().trim();

            if (!validMobile(m)) {
                centerPrint("Invalid mobile.", RED);
                continue;
            }

            customerMobile = m;
            int otp = genOTP();
            centerPrint("OTP (simulation): " + otp, YELLOW);

            System.out.print("Enter OTP: ");
            String ent = sc.nextLine().trim();

            if (!ent.matches("\\d+") || Integer.parseInt(ent) != otp) {
                centerPrint("Incorrect OTP.", RED);
                continue;
            }

            centerPrint("Mobile verified.", GREEN);
            break;
        }

        while (true) {
            System.out.print("Enter address: ");
            String a = sc.nextLine().trim();
            if (a.length() < 5) {
                centerPrint("Invalid address.", RED);
                continue;
            }

            customerAddress = a;
            break;
        }

        while (true) {
            System.out.print("Enter 6-digit pincode: ");
            String p = sc.nextLine().trim();
            if (!validPincode(p)) {
                centerPrint("Invalid pincode.", RED);
                continue;
            }
            customerPincode = p;
            break;
        }

        while (true) {
            System.out.print("Enter password (min 8 chars): ");
            String pw = sc.nextLine();

            if (!validPassword(pw)) {
                centerPrint("Invalid password.", RED);
                continue;
            }

            System.out.print("Confirm password: ");
            String cpw = sc.nextLine();

            if (!cpw.equals(pw)) {
                centerPrint("Passwords do not match.", RED);
                continue;
            }

            customerPassword = pw;
            break;
        }

        System.out.print("Enter your display name (for receipt): ");
        customerName = sc.nextLine().trim();
        if (customerName.isEmpty()) customerName = customerUsername;

        // Login
        while (true) {
            cyberBoxTitle(" LOGIN ");
            centerPrint("1) Login   2) Exit", BRIGHT_CYAN);

            System.out.print("Choose: ");
            String opt = sc.nextLine().trim();

            if (opt.equals("1")) {
                System.out.print("Username: ");
                String lu = sc.nextLine().trim();

                System.out.print("Password: ");
                String lp = sc.nextLine();

                if (lu.equals(customerUsername) && lp.equals(customerPassword)) {
                    centerPrint("Login successful. Welcome, " + customerName, GREEN);
                    break;
                } else {
                    centerPrint("Invalid credentials.", RED);
                }

            } else if (opt.equals("2")) {
                centerPrint("See you soon!... Bye!", YELLOW);
                return;

            } else {
                centerPrint("Invalid option.", RED);
            }
        }

        // Main menu
        while (true) {
            List<String> mainMenu = Arrays.asList(
                    "1) Dawat - Vegetarian",
                    "2) Dawat - Non-Vegetarian",
                    "3) DilSe - Vegetarian",
                    "4) DilSe - Non-Vegetarian",
                    "5) Chinese - Vegetarian",
                    "6) Desserts",
                    "7) Drinks",
                    "8) View Cart",
                    "9) Billing & Spin Wheel",
                    "10) Exit"
            );

            cyberBoxTitle(" MAIN MENU ");

            for (String line : mainMenu) centerPrint(line, BRIGHT_CYAN);

            System.out.println(repeat(STAR, WIDTH));
            System.out.print("Enter choice (1-10): ");

            String chs = sc.nextLine().trim();
            int ch = -1;

            try {
                ch = Integer.parseInt(chs);
            } catch (Exception e) {
                centerPrint("Enter a number 1-10.", RED);
                continue;
            }

            switch (ch) {
                case 1:
                    showMenuAndAdd("DAWAT - Vegetarian", vegDawat, vegDawatPrice);
                    break;

                case 2:
                    showMenuAndAdd("DAWAT - Non-Vegetarian", nonvegDawat, nonvegDawatPrice);
                    break;

                case 3:
                    showMenuAndAdd("DILSE - Vegetarian", vegDilse, vegDilsePrice);
                    break;

                case 4:
                    showMenuAndAdd("DILSE - Non-Vegetarian", nonvegDilse, nonvegDilsePrice);
                    break;

                case 5:
                    showMenuAndAdd("CHINESE - Vegetarian", vegChinese, vegChinesePrice);
                    break;

                case 6:
                    showMenuAndAdd("DESSERTS", desserts, dessertsPrice);
                    break;

                case 7:
                    showMenuAndAdd("DRINKS", drinks, drinksPrice);
                    break;

                case 8:
                    showCart();
                    break;

                case 9:
                    if (cartItems.isEmpty()) {
                        centerPrint("Cart is empty.", RED);
                        break;
                    }

                    String game = spinnerSelectGame();
                    boolean won = false;

                    if ("Food Riddle".equals(game)) won = playFoodRiddle();
                    else if ("Hangman Food".equals(game)) won = playHangman();
                    else if ("Word Puzzle".equals(game)) won = playWordPuzzle();

                    if (won)
                        centerPrint("You won! Fixed 20% discount will be applied.", GREEN);
                    else
                        centerPrint("No discount (you didn't win).", RED);

                    double payable = computeAndShowBillApplyDiscountIfWin(won);

                    while (true) {
                        boolean paid = doPayment(payable);

                        if (paid) {
                            centerPrint("Order completed. Thank you!", GREEN);
                            return;
                        } else {
                            centerPrint("Payment failed or cancelled.", RED);

                            System.out.print("Do you want to retry payment? (y/n): ");
                            String retry = sc.nextLine().trim().toLowerCase();

                            if (retry.equals("y")) {
                                centerPrint("Retrying payment...", YELLOW);
                                continue;
                            } else {
                                centerPrint("Payment not completed. Returning to main menu.", YELLOW);
                                break;
                            }
                        }
                    }
                    break;

                case 10:
                    centerPrint("Goodbye!", YELLOW);
                    return;

                default:
                    centerPrint("Invalid choice.", RED);
                    break;
            }
        }
    }
}
