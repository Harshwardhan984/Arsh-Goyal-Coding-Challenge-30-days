

class accounter{
     private String name;
    private String accountNumber;
    private String accountType;
    private double balance;
    
   
        public accounter() {
            name = "";
            accountNumber = "";
            accountType = "";
            balance = 0;
        }
    public accounter(String name, String accountNumber, String accountType){
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = 0;
    }
        public accounter(String name, String accountNumber, String accountType, double balance){
            this.name = name;
            this.accountNumber = accountNumber;
            this.accountType = accountType;
            this.balance = balance;
        }

        public String display_accounter(){
            return "Name: " + name + "\nAccount Number: " + accountNumber + "\nAccount Type: " + accountType + "\nBalance: " + balance;
        }
        public void print_balance(){
            System.out.println("Balance: " + balance);
        }

        public void deposit(double amount){
            if(amount >= 0){
            this.balance += amount;
            System.out.println("Deposited: " + amount);
            }
            else
            System.out.println("negtive amount");
        }

        public void withdraw(double amount){
            if(amount <= balance){
            this.balance -= amount;
            System.out.println("Withdrew: " + amount);
            }
            else
            System.out.println("Insufficient balance");
        }

        public boolean checkBalance(double amount){
            if(amount <= balance){
            return true;
            }
            else
            return false;
        }

    }
    public class program1{
    public static void main(String[] args) {
       accounter a1 = new accounter("a1", "1", "saving");
       accounter a2 = new accounter("a2", "2", "saving");
       accounter a3 = new accounter("a3", "3", "saving");
       System.out.println(a1.display_accounter());
       System.out.println(a2.display_accounter());
       System.out.println(a3.display_accounter());
       a1.deposit(1000);
       a2.deposit(1001);
       System.out.println(a1.display_accounter());
       System.out.println(a2.display_accounter());
       a1.withdraw(500);
       System.out.println(a1.display_accounter());
       System.out.println(a2.checkBalance(500));
       System.out.println(a2.checkBalance(1500));
    }
}