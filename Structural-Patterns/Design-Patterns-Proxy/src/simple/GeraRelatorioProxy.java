package simple;

import static java.time.LocalTime.now;

public class GeraRelatorioProxy implements ProxyInterface{

    private static String usuario;

    public GeraRelatorioProxy(String usuario) {
        this.usuario = usuario;
        System.out.println("[Proxy] Criando instância do Proxy em: " + now());
    }

    @Override
    public void readReport() {

        System.out.println("[Proxy] Iniciando Leitura do relatório em: " + now());

        if (usuario != null && usuario.equals("admin")) {
            var relatorioReal = new GeraRelatorioReal();
            relatorioReal.readReport();
        } else {
            System.out.println("Acesso negado! Usuário não autorizado.");
        }

        System.out.println("[Proxy] Finalizando Leitura do relatório em: " + now());
    }
}
