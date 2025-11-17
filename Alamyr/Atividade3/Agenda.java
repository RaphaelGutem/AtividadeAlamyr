import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Agenda {

    private List<Contatinho> lista = new ArrayList<>();

    // Adiciona um contatinho
    public void addContatinho(Contatinho c) {
        lista.add(c);
    }

    // Ordena pelo nome
    public void ordenarLista() {
        Collections.sort(lista, new Comparator<Contatinho>() {
            @Override
            public int compare(Contatinho c1, Contatinho c2) {
                return c1.getNome().compareToIgnoreCase(c2.getNome());
            }
        });
    }

    // Salva a lista ordenada em um arquivo
    public void salvarLista() {
        ordenarLista();

        try {
            FileWriter writer = new FileWriter("contatinhos.txt");

            for (Contatinho c : lista) {
                writer.write(c.toString() + "\n");
            }

            writer.close();
            System.out.println("Arquivo salvo com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo.");
        }
    }

    // Método do exercício 4 — Buscar contatinho pelo nome
    public Contatinho buscarContatinho(String nomeProcurado) {

        try (BufferedReader reader = new BufferedReader(new FileReader("contatinhos.txt"))) {
            String linha;

            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split("#");
                // dados[0] = nome

                if (dados[0].equalsIgnoreCase(nomeProcurado)) {
                    return new Contatinho(dados[0], dados[1], dados[2], dados[3]);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo.");
        }

        return null; // não encontrado
    }

    // Teste opcional (você pode remover se quiser)
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        agenda.addContatinho(new Contatinho("Nick Ferreira", "nick@gmail.com", "83988888", "Professor"));
        agenda.addContatinho(new Contatinho("Ana", "ana@gmail.com", "99999999", "Aluna"));
        agenda.addContatinho(new Contatinho("Bruno", "bruno@gmail.com", "98888888", "Amigo"));

        agenda.salvarLista();

        Contatinho c = agenda.buscarContatinho("Jorge");

        if (c != null) {
            System.out.println("Encontrado: " + c);
        } else {
            System.out.println("Contatinho não encontrado!");
        }
    }
}
