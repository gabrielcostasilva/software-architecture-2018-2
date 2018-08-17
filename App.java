import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String args[]) {
        List<String> listaNomes = new ArrayList<>();
        Scanner entrada = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.print("Informe 1 para inserir, 2 para listar, 0 para sair");
            opcao = entrada.nextInt();

            switch (opcao) {
            case 1:
                System.out.print("Informe um nome: ");
                listaNomes.add(entrada.next());
                break;

            case 2:
                System.out.println("Listando nomes: ");
                listaNomes.stream().forEach(System.out::println);
                break;

            case 0:
                System.out.println("bye");
            }

        }
    }
}
