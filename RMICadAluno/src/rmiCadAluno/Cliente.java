package rmiCadAluno;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente{
    
    Conexao conn;
    public static void cliente() throws RemoteException {
        try {
            IAluno iAluno = (IAluno) Naming.lookup("//localhost/ProcessRMI");
            Scanner lerSaida = new Scanner(System.in);
            while (true) {
                switch (menu()) {
                    case 1: { //CADASTRAR ALUNO
                        String nome, matricula, curso;
                        System.out.println("INFORME O NOME: ");
                        nome = lerSaida.next();
                        System.out.println("INFORME A MATRICULA: ");
                        matricula = lerSaida.next();
                        System.out.println("INFORME O CURSO: ");
                        curso = lerSaida.next();
                        System.out.println(iAluno.cadastrar(nome, matricula, curso));
                        break;
                    }
                    case 2: { //LISTAR ALUNOS                       
                        ArrayList<String> alunos = (ArrayList) iAluno.listar(); 
                        for (String aluno: alunos) { 
                           System.out.println(aluno+"\n\n"); 
                        }  
                        break;
                    }
                    case 3: { //BUSCAR ALUNO
                        System.out.println("INFORME A MATRICULA: ");
                        System.out.println(iAluno.buscarAluno(String.valueOf(lerSaida.nextInt() ) ) );
                        break;
                    }
                    case 4: { //REMOVER ALUNO
                        System.out.println("INFORME A MATRICULA: ");
                        System.out.println(iAluno.excluir(String.valueOf(lerSaida.nextInt() ) ) );
                        break;
                    }
                    case 0: {
                        System.exit(0);
                        break;
                    }
                    default:
                        System.out.println("OPCAO INVALIDA!");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }

    public static int menu(){
        Scanner ler = new Scanner(System.in); // ler a variavel digitada
        System.out.println("---------CADASTRO DE CLIENTE ONLINE---------\n");
        System.out.println("INFORME A AÇÃO PARA OS DADOS DO ALUNO: \n");
        System.out.println("1 - CADASTRAR ALUNO\n2 - LISTAR ALUNOS\n3 - BUSCAR ALUNO\n4 - REMOVER ALUNO\n0- SAIR");
        System.out.println("--------------------------------------------");
        System.out.println("Digite a opção: ");
        return Integer.parseInt(ler.nextLine());
    }
    
     public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    cliente();
                } catch (RemoteException ex) {
                    System.out.println("Exception:"+ex);
                }
            }
        });
    }
}
