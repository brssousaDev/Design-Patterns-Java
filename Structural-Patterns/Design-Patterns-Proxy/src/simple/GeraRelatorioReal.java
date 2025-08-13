package simple;

public class GeraRelatorioReal implements ProxyInterface{

    public GeraRelatorioReal() {
        try {
            Thread.sleep(3600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void readReport() {
        System.out.println("Relatório lido com sucesso!");
    }

}
