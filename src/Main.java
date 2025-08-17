
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        List<Tarefa> list = new ArrayList<>();
        boolean opcao = true;
        LocalDate dataAtual = LocalDate.now();


        String nomeItem;
        String categoriaItem;
        String descricaoItem;
        int prioridadeItem = 0;
        int statusItem = 0;
        LocalDate prazoTarefa = null;
        int modoDeExibicao = 0;
        int testandoLimite = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int listaComTesteDeTarefas = 0;
        System.out.println("Deseja que tenha tarefas adicionadas? [0 - sim/qualquer numero para nao]");
        listaComTesteDeTarefas = scanner.nextInt();
        if(listaComTesteDeTarefas == 0){

            Tarefa tarefaUm = new Tarefa("Arroz", "Comidinha gostosa", 1, "Comida", 1, LocalDate.parse("11/09/2025", formatter));
            Tarefa tarefaDois = new Tarefa("Arroz", "Comidinha gostosa", 2, "Comida", 2, LocalDate.parse("11/09/2025", formatter));
            Tarefa tarefaTres = new Tarefa("Arroz", "Comidinha gostosa", 3, "Comida", 3, LocalDate.parse("11/09/2025", formatter));
            Tarefa tarefaQuatro = new Tarefa("Arroz", "Comidinha gostosa", 4, "Comida", 1, LocalDate.parse("11/09/2025", formatter));
            Tarefa tarefaCinco = new Tarefa("Arroz", "Comidinha gostosa", 5, "Comida", 2, LocalDate.parse("11/09/2025", formatter));

            list.add(tarefaUm);
            list.add(tarefaDois);
            list.add(tarefaTres);
            list.add(tarefaQuatro);
            list.add(tarefaCinco);

        }

        while(opcao){

            int escolhaOpcao = 0;


            if(list.isEmpty()){
                System.out.println("-----------------------------------------");
                System.out.println("Lista vazia!");
                System.out.println("-----------------------------------------");

            }else if(modoDeExibicao == 0){

                System.out.println("-----------------------------------------");
                System.out.println("Done: " + escolhaOpcao + " ToDo: " + escolhaOpcao + "Doing: " + escolhaOpcao);
                System.out.println("-----------------------------------------");
                list.stream().forEach(item -> {
                    String statusStr = switch(item.getStatus()) {
                        case 1 -> "A fazer";
                        case 2 -> "Fazendo";
                        case 3 -> "Feito";
                        default -> "Desconhecido (" + item.getStatus() + ")";
                    };
                    System.out.println("ID: " + item.getId() + "  2.Nome: " + item.getName());
                    System.out.println("3.Descricao: " + item.getDescription());
                    System.out.println("4.Categoria: " + item.getCategoria());
                    System.out.println("5.Prioridade: "  + item.getPrioridade() + "  6.Status: " + statusStr);
                    System.out.println("7. Data: " + item.getDataTarefa() + " (" +  (long) ChronoUnit.DAYS.between(LocalDate.now(), item.getDataTarefa()) + " dias)");
                    System.out.println("-----------------------------------------");
                });

            }else if(modoDeExibicao == 1){

                System.out.println("Categoria");

            }else if(modoDeExibicao == 2){

                list.stream()
                        .sorted((t1, t2) -> Integer.compare(t1.getPrioridade(), t2.getPrioridade()))
                        .forEach(item -> {
                            String statusStr = switch(item.getStatus()) {
                                case 1 -> "A fazer";
                                case 2 -> "Fazendo";
                                case 3 -> "Feito";
                                default -> "Desconhecido (" + item.getStatus() + ")";
                            };
                            System.out.println("ID: " + item.getId() + "  2.Nome: " + item.getName());
                            System.out.println("3.Descricao: " + item.getDescription());
                            System.out.println("4.Categoria: " + item.getCategoria());
                            System.out.println("5.Prioridade: "  + item.getPrioridade() + "  6.Status: " + statusStr);
                            System.out.println("7. Data: " + item.getDataTarefa() + " (" +  (long) ChronoUnit.DAYS.between(LocalDate.now(), item.getDataTarefa()) + " dias)");
                            System.out.println("-----------------------------------------");
                        });


            }else if(modoDeExibicao == 3){
                System.out.println("-----------------------------------------");
                System.out.println("Tarefas ordenadas por status:");
                System.out.println("-----------------------------------------");
                list.stream()
                        .sorted((t1, t2) -> Integer.compare(t1.getStatus(), t2.getStatus()))
                        .forEach(item -> {
                            String statusStr = switch(item.getStatus()) {
                                case 1 -> "A fazer";
                                case 2 -> "Fazendo";
                                case 3 -> "Feito";
                                default -> "Desconhecido (" + item.getStatus() + ")";
                            };
                            System.out.println("ID: " + item.getId() + "  Nome: " + item.getName());
                            System.out.println("Status: " + statusStr + " | Prioridade: " + item.getPrioridade());
                            System.out.println("Categoria: " + item.getCategoria());
                            System.out.println("Descrição: " + item.getDescription());
                            System.out.println("Data: " + item.getDataTarefa().format(formatter) +
                                    " (" + (long) ChronoUnit.DAYS.between(LocalDate.now(), item.getDataTarefa()) + " dias)");
                            System.out.println("-----------------------------------------");
                        });
            }



            System.out.println("1 - Adicionar item\n2 - Excluir item\n3 - Modificar item\n4 - Sair do programa\n5 - Modo de exibicao");
            System.out.print("Escolha uma opcao:");
            escolhaOpcao = scanner.nextInt();

            switch (escolhaOpcao){
                case 1: // Adicionar um item
                    scanner.nextLine();
                    System.out.print("Adionando Item:\n");
                    System.out.print("Nome: ");
                    nomeItem = scanner.nextLine();
                    System.out.print("Categoria: ");
                    categoriaItem = scanner.nextLine();
                    System.out.print("Descricao: ");
                    descricaoItem = scanner.nextLine();


                    while(prioridadeItem == 0){
                        try{
                            System.out.print("Prioridade: (1 a 5)");
                            prioridadeItem = scanner.nextInt();
                            scanner.nextLine();
                            if(prioridadeItem < 0 || prioridadeItem > 5){

                                prioridadeItem = 0;
                                System.out.println("Erro: Valor fora dos parametros!");
                                return;
                            }
                        }catch(InputMismatchException e) {
                            System.out.println("não é um número de prioridade, Digite um numero!!!");
                            scanner.nextLine();
                            prioridadeItem = 0;
                        }
                    }


                    while(statusItem == 0){
                        try{
                            System.out.println("Status: (1 - Todo, 2 - doing, 3 - done)");
                            statusItem = scanner.nextInt();
                            scanner.nextLine();
                            if(statusItem < 1 || 3 < statusItem){
                                System.out.println("Valor fora dos parametros!!");
                                statusItem = 0;
                            }
                        }catch(InputMismatchException e){
                            System.out.println("Valor Incorreto!");
                            scanner.nextLine();
                            statusItem = 0;
                        }

                    }



                    while(prazoTarefa == null){
                        System.out.println("Prazo Tarefa: (dd/MM/yyyy)");
                        String dataDigitada = scanner.nextLine();

                        try{

                            prazoTarefa = LocalDate.parse(dataDigitada, formatter);
                            if(prazoTarefa.isBefore(dataAtual)){
                                System.out.println("Erro: Data digitada é menor que a data atual");
                                prazoTarefa = null;
                            }
                        }catch(DateTimeParseException e){
                            System.out.println("Erro: Formato de data invalido!!");
                        }
                    }

                    Tarefa novaTarefa = new Tarefa(nomeItem, descricaoItem, prioridadeItem, categoriaItem, statusItem, prazoTarefa);

                    prioridadeItem = 0;
                    statusItem = 0;
                    prazoTarefa = null;

                    list.add(novaTarefa);

                    break;
                case 2: // Excluir o item
                    if(!list.isEmpty()){
                        int excluirObjeto = 0;
                        System.out.println("Digite o ID do item: ");
                        try{
                            excluirObjeto = scanner.nextInt() - 1;
                            list.remove(excluirObjeto);
                        }catch(InputMismatchException e){
                            System.out.println("Valor nao e um numero!!!");
                            return;
                        }
                    }
                    break;
                case 3: // Modificar item
                    int modificarItemEscolha;
                    int modificarItem;
                    if(!list.isEmpty()){
                        System.out.println("Informe o ID do item que deseja modificar: ");
                        modificarItemEscolha = scanner.nextInt();
                        scanner.nextLine();

                        if(list.stream().anyMatch(obj -> obj.getId() == modificarItemEscolha)){
                            System.out.println("-----------------------------------------");
                            list.stream()
                                    .filter(item -> item.getId() == modificarItemEscolha)
                                    .forEach(item -> {
                                        System.out.println("ID: " + item.getId() + "  2.Nome: " + item.getName());
                                        System.out.println("3.Descricao: " + item.getDescription());
                                        System.out.println("4.Categoria: " + item.getCategoria());
                                        System.out.println("5.Prioridade: " + item.getPrioridade() + "  6.Status: " + item.getStatus());
                                        System.out.println("7. Data: " + item.getDataTarefa());
                                        System.out.println("-----------------------------------------");
                                    });
                            System.out.println("Digite o numero do item que deseja modificar: ");
                            modificarItem = scanner.nextInt();
                            scanner.nextLine();

                            if(modificarItem == 2){
                                String novoNome;
                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .forEach(item -> {
                                            System.out.println("2.Nome: " + item.getName());
                                        });

                                System.out.println("Digite o novo nome: ");
                                novoNome = scanner.nextLine();

                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .findFirst()
                                        .ifPresent(item -> item.setName(novoNome));
                                System.out.println("Nome atualizado!");


                            }
                            else if(modificarItem == 3){
                                String novaDescricao;
                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .forEach(item -> {
                                            System.out.println("3.Descricao: " + item.getDescription());
                                        });

                                System.out.println("Digite a nova descricao: ");
                                novaDescricao = scanner.nextLine();

                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .findFirst()
                                        .ifPresent(item -> item.setDescription(novaDescricao));
                                System.out.println("Descricao atualizado!");

                            }
                            else if(modificarItem == 4){
                                String novaCategoria;
                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .forEach(item -> {
                                            System.out.println("4.Categoria: " + item.getCategoria());
                                        });

                                System.out.println("Digite a nova categoria: ");
                                novaCategoria = scanner.nextLine();

                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .findFirst()
                                        .ifPresent(item -> item.setName(novaCategoria));
                                System.out.println("Categoria atualizado!");

                            }
                            else if(modificarItem == 5){
                                int novaPrioridade;
                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .forEach(item -> {
                                            System.out.println("5.Prioridade: " + item.getPrioridade());
                                        });

                                System.out.println("Digite a nova prioridade: ");
                                novaPrioridade = scanner.nextInt();
                                scanner.nextLine();

                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .findFirst()
                                        .ifPresent(item -> item.setPrioridade(novaPrioridade));
                                System.out.println("Prioridade atualizada!");

                            }
                            else if(modificarItem == 6){
                                int novoStatus;
                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .forEach(item -> {
                                            System.out.println("6. Status: " + item.getStatus());
                                        });

                                System.out.println("Digite o novo status: ");
                                novoStatus = scanner.nextInt();
                                scanner.nextLine();

                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .findFirst()
                                        .ifPresent(item -> item.setStatus(novoStatus));
                                System.out.println("Status atualizado!");
                            }
                            else if(modificarItem == 7){
                                String novaData;
                                LocalDate prazoTarefaLocal = null;
                                list.stream()
                                        .filter(item -> item.getId() == modificarItemEscolha)
                                        .forEach(item -> {
                                            System.out.println("7. Data: " + item.getDataTarefa());
                                        });

                                System.out.println("Digite uma nova data: (dd/MM/yyyy)");
                                novaData = scanner.nextLine();

                                while(prazoTarefaLocal == null){
                                    try{

                                        prazoTarefaLocal  = LocalDate.parse(novaData, formatter);

                                        final LocalDate dataValida = prazoTarefaLocal;

                                        list.stream()
                                                .filter(item -> item.getId() == modificarItemEscolha)
                                                .findFirst()
                                                .ifPresent(item -> item.setDataTarefa(dataValida));
                                        System.out.println("Data atualizada!");
                                    }catch(DateTimeParseException e){
                                        System.out.println("Erro: Formato de data invalido!!");
                                    }
                                }
                            }

                            scanner.nextLine();

                        }
                    }

                    break;
                case 4: // sair do programa
                    opcao = false;
                    break;
                case 5: // mudar o modo de exibição
                    modoDeExibicao = testandoLimite % 4;
                    testandoLimite++;

                    System.out.println(modoDeExibicao);
                    break;

            }

        }
        scanner.close();
    }
}