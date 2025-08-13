package simple;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Teste com usuário autorizado ===");
        var relatorio1 = new GeraRelatorioProxy("admin");
        relatorio1.readReport();

        System.out.println("\n=== Teste com usuário não autorizado ===");
        var relatorio2 = new GeraRelatorioProxy("user");
        relatorio2.readReport();
    }
}
