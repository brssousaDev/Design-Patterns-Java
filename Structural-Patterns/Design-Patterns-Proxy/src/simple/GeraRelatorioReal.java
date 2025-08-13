package simple;

public class GeraRelatorioReal implements ProxyInterface{

    private String relatorio;

    @Override
    public void execute() {
        try {
            Thread.sleep(3600);
            relatorio = "Gerando relatório";
            System.out.println(relatorio);
            System.out.println("Classe Real");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRelatorio() {
        return relatorio;
    }
}
