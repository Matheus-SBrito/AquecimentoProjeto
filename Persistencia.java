import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

public class Persistencia {
    private final XStream xstream;

    public Persistencia() {
        this.xstream = new XStream(new DomDriver());
        // Configuração de segurança do XStream para permitir os tipos do projeto
        XStream.setupDefaultSecurity(this.xstream);
        this.xstream.addPermission(AnyTypePermission.ANY);
    }

    public void salvarCentral(CentralDeInformacoes central, String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
            try (PrintWriter gravar = new PrintWriter(arquivo)) {
                String xml = xstream.toXML(central);
                gravar.print(xml);
            }
        } catch (Exception e) {
            System.err.println("Erro ao salvar a central no arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }

    // Sobrecarga para uso conveniente com arquivo padrão
    public void salvarCentral(CentralDeInformacoes central) {
        salvarCentral(central, "central.xml");
    }

    public CentralDeInformacoes recuperarCentral(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (arquivo.exists() && arquivo.length() > 0) {
            try (FileInputStream fis = new FileInputStream(arquivo)) {
                Object obj = xstream.fromXML(fis);
                if (obj instanceof CentralDeInformacoes) {
                    return (CentralDeInformacoes) obj;
                }
            } catch (Exception e) {
                System.err.println("Erro ao recuperar a central do arquivo " + nomeArquivo + ": " + e.getMessage());
            }
        }
        return new CentralDeInformacoes();
    }

    // Sobrecarga para uso conveniente com arquivo padrão
    public CentralDeInformacoes recuperarCentral() {
        return recuperarCentral("central.xml");
    }
}