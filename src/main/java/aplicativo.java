import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class aplicativo {

    public static void main(String[] args) throws IOException {

        ArrayList<String> agenda = new ArrayList();
        Scanner ler = new Scanner(System.in);
        int opcao;

        importar(agenda);

        do {
            System.out.printf("****** APS AGENDA *******\n");
            System.out.printf("*************************\n");
            System.out.printf("**** Menu Principal *****\n");
            System.out.printf("[ 1 ] Incluir Contato\n");
            System.out.printf("[ 2 ] Excluir Contato\n");
            System.out.printf("[ 3 ] Listar Contatos\n");
            System.out.printf("[ 4 ] Pesquisar Contato\n");
            System.out.printf("[ 0 ] Encerrar o Programa\n");
            System.out.printf("\nOpção Desejada: ");

            opcao = ler.nextInt();

            switch (opcao) {
                case 1: incluir(agenda); break;
                case 2: excluir(agenda); break;
                case 3: listar(agenda); break;
                case 4: pesquisar(agenda);
            }

            System.out.printf("\n\n");

        } while (opcao != 0);

        exportar(agenda);
    }

    public static void importar(ArrayList<String> agenda) {
        try {
            FileReader arq = new FileReader("agenda.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine(); // lê a primeira linha
            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
            while (linha != null) {
                agenda.add(linha);
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.",
                    e.getMessage());
        }
    }

    public static void exportar(ArrayList<String> agenda)
            throws IOException {
        FileWriter arq = new FileWriter("agenda.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        int i, n = agenda.size();
        for (i=0; i<n; i++) {
            gravarArq.printf("%s%n", agenda.get(i));
        }
        gravarArq.close();
    }


    public static void incluir(ArrayList<String> agenda) {
        Scanner ler = new Scanner(System.in);
        String nome, telefone;

        System.out.printf("\nInforme o nome do contato:\n");
        nome = ler.nextLine();

        System.out.printf("\nInforme o telefone do contato:\n");
        telefone = ler.nextLine();

        // grava os dados no final da "lista"
        agenda.add(nome + ";" + telefone);
    }

    public static void excluir(ArrayList<String> agenda) {
        Scanner ler = new Scanner(System.in);
        int i;

        listar(agenda);

        System.out.printf("\nInforme a posição a ser excluída:\n");
        i = ler.nextInt();

        try {
            agenda.remove(i);
        } catch (IndexOutOfBoundsException e) {
            // exceção lançada para indicar que um índice (i)
            // está fora do intervalo válido (de 0 até agenda.size()-1)
            System.out.printf("\nErro: posição inválida (%s).\n\n",
                    e.getMessage());
        }
    }

    public static void listar(ArrayList<String> agenda) {
        System.out.printf("\nListadando os itens da Agenda:\n");
        int i, n = agenda.size();
        for (i=0; i<n; i++) {
            System.out.printf("Posição %d- %s\n", i, agenda.get(i));
        }
        System.out.printf("---------------------------------------");
    }

    public static void pesquisar(ArrayList<String> agenda) {
        Scanner ler = new Scanner(System.in);
        String s;

        System.out.printf("\nInforme o nome do contato:\n");
        s = ler.nextLine();

        int i, n = agenda.size();
        s = s.toUpperCase();
        String dados[];
        for (i=0; i<n; i++) {
            // informando "joão", por exemplo, na entrada serão mostrados
            // todos os contatos que possuem "joão" no nome
            if (agenda.get(i).toUpperCase().indexOf(s) != -1) {
                dados = agenda.get(i).split(";");
                System.out.printf("\nNome....: %s", dados[0]);
                System.out.printf("\nTelefone: %s\n", dados[1]);
            }
        }
    }

}
