import java.util.Arrays;

public class Week7Assignment {

    // ==========================================
    // Problem 1: The Health Bar
    // ==========================================
    public static class Character {
        private int health;
        private final int maxHealth;

        public Character(int maxHealth) {
            this.maxHealth = maxHealth;
            this.health = maxHealth; // starts at maximum health
        }

        public void takeDamage(int amount) {
            this.health -= amount;
            if (this.health < 0) {
                this.health = 0; // floored at 0
            }
        }

        public void heal(int amount) {
            this.health += amount;
            if (this.health > this.maxHealth) {
                this.health = this.maxHealth; // capped at max health
            }
        }

        public int getHealth() {
            return this.health;
        }
    }

    // ==========================================
    // Problem 2: The Playlist
    // ==========================================
    public static class Playlist {
        private String[] songs;
        private int count;

        public Playlist(int maxSize) {
            this.songs = new String[maxSize];
            this.count = 0;
        }

        public void addSong(String title) {
            if (count < songs.length) {
                songs[count++] = title;
            }
        }

        public String[] getSongs() {
            // Returns a safe copy containing only the songs added so far
            return Arrays.copyOf(songs, count);
        }

        public int getSongCount() {
            return count;
        }
    }

    // ==========================================
    // Problem 3: The Password Checker
    // ==========================================
    public static class PasswordChecker {
        private final String password;

        public PasswordChecker(String password) {
            this.password = password;
        }

        public String getStrength() {
            int len = password.length();
            if (len < 6) {
                return "Weak";
            } else if (len >= 6 && len <= 9) {
                return "Medium";
            } else {
                return "Strong";
            }
        }
    }

    // ==========================================
    // Problem 4: The Traffic Light
    // ==========================================
    public static class TrafficLight {
        private final String id;
        private String color;

        public TrafficLight(String id) {
            this.id = id;
            this.color = "RED"; // Starts on RED
        }

        public void next() {
            if (color.equals("RED")) {
                color = "GREEN";
            } else if (color.equals("GREEN")) {
                color = "YELLOW";
            } else if (color.equals("YELLOW")) {
                color = "RED";
            }
        }

        public String getColor() {
            return this.color;
        }

        public String getId() {
            return this.id;
        }
    }

    // ==========================================
    // Problem 5: The Shopping Cart
    // ==========================================
    public static class Cart {
        private final String cartId;
        private double[] prices;
        private int itemCount;

        public Cart(String cartId, int capacity) {
            this.cartId = cartId;
            this.prices = new double[capacity];
            this.itemCount = 0;
        }

        public void addItem(double price) {
            if (itemCount < prices.length) {
                prices[itemCount++] = price;
            }
        }

        public double getTotal() {
            double total = 0;
            for (int i = 0; i < itemCount; i++) {
                total += prices[i];
            }
            return total;
        }

        public int getItemCount() {
            return itemCount;
        }
    }

    // ==========================================
    // MAIN METHOD: Test All Examples
    // ==========================================
    public static void main(String[] args) {
        System.out.println("--- Problem 1 Test: Character Health Bar ---[cite: 47]");
        Character c = new Character(100);
        c.takeDamage(30);
        System.out.println("After 30 damage -> health = " + c.getHealth());
        c.heal(50);
        System.out.println("After healing 50 -> health = " + c.getHealth() + " (capped)");
        c.takeDamage(150);
        System.out.println("After 150 damage -> health = " + c.getHealth() + " (floored)");

        System.out.println("\n--- Problem 2 Test: Playlist Encapsulation ---[cite: 48]");
        Playlist p = new Playlist(10);
        p.addSong("Song A");
        p.addSong("Song B");
        String[] copy = p.getSongs();
        copy[0] = "Hacked"; 
        System.out.println("p.getSongs()[0] is still: " + p.getSongs()[0]);
        System.out.println("Song count: " + p.getSongCount());

        System.out.println("\n--- Problem 3 Test: Password Checker ---[cite: 50]");
        PasswordChecker pc = new PasswordChecker("abcd");
        System.out.println("abcd strength -> " + pc.getStrength());
        PasswordChecker pc2 = new PasswordChecker("abcdefghij");
        System.out.println("abcdefghij strength -> " + pc2.getStrength());

        System.out.println("\n--- Problem 4 Test: Traffic Light ---[cite: 51]");
        TrafficLight t = new TrafficLight("TL-9");
        System.out.println("Initial -> " + t.getColor());
        t.next();
        System.out.println("After next() -> " + t.getColor());
        t.next();
        System.out.println("After next() -> " + t.getColor());
        t.next();
        System.out.println("After next() -> " + t.getColor());

        System.out.println("\n--- Problem 5 Test: Shopping Cart ---[cite: 52]");
        Cart cart = new Cart("CART-5", 20);
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);
        System.out.println("Total -> " + cart.getTotal());
        System.out.println("Item Count -> " + cart.getItemCount());
    }
}
