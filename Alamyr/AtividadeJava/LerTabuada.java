import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LerTabuada {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int num = sc.nextInt();

        String nomeArquivo = "tabuada_" + num + ".txt";

        File arquivo = new File(nomeArquivo);

        // Verifica se o arquivo existe
        if (!arquivo.exists()) {
            System.out.println("O arquivo '" + nomeArquivo + "' não existe!");
        } else {
            System.out.println("Conteúdo do arquivo:");
            System.out.println("---------------------");

            try {
                Scanner leitor = new Scanner(arquivo);

                while (leitor.hasNextLine()) {
                    System.out.println(leitor.nextLine());
                }

                leitor.close();

            } catch (FileNotFoundException e) {
                System.out.println("Erro ao ler o arquivo.");
            }
        }

        sc.close();
    }
}
