import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;

public class Persistencia {
    private XSStream xstream = new XSStream(new DomDriver());
    private File arquivo = new File("central.xml");

    public void salvarCentral(CentralDeInformacoes central){
        String xml = xstream.toXML(central);

        try{
            if (!arquivo.exists()){
                arquivo.createNewFile();
            }
            PrintWriter gravar = new PrintWriter(arquivo);
            gravar.print(xml);
            gravar.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public CentralDeInformacoes recuperarCentral() {
        try{
            if(arquivo.exists()){
                FileInputStream fis = new FileInputStream(arquivo);
                return (CentralDeInformacoes) xstream.fromXML(fis);

            }
        }catch (Exception e){
                e.printStackTrace();
        }
        return new CentralDeInformacoes();
    }
}