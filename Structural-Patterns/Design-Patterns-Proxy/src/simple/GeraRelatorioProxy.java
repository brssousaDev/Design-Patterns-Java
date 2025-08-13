package simple;

import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;

import static java.time.LocalTime.now;

public class GeraRelatorioProxy implements ProxyInterface{

    private static String relatorio;

    @Override
    public void execute() {

        System.out.println(now());
        if(relatorio == null){
            var classeReal = new GeraRelatorioReal();
            classeReal.execute();
            relatorio = classeReal.getRelatorio();
        } else {
            System.out.println(relatorio);
        }
        System.out.println(now());

    }
}
