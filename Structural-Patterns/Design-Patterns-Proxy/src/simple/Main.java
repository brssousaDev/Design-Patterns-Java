package simple;

public class Main {

    public static void main(String[] args) {
        var gerarRelatorio = new GeraRelatorioProxy();
        gerarRelatorio.execute();
        System.out.println("--------------------------");
        gerarRelatorio.execute();
    }
}
