public class Calctrix {
    public static void usage(int exitCode){
        System.out.println("USAGE:");
        System.out.println("java Calctrix <input> <output>");
        System.exit(exitCode);
    }

    public static void main(String[] args) {
        usage(0);
    }
}
