
package run;

import config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {

    public static void clearConsole(){
        for(int i = 0; i < 5; i++){
            System.out.println("");
        }
    }
    
    public static int menu(){
        Scanner ler = new Scanner(System.in);
        System.out.println("---------CADASTRO DE CLIENTE ONLINE---------\n");
        System.out.println("INFORME A AÇÃO PARA OS DADOS DO ALUNO: \n");
        System.out.println("1 - CADASTRAR ALUNO\n2 - LISTAR ALUNOS\n3 - BUSCAR ALUNO\n4 - REMOVER ALUNO\n0- SAIR");
        System.out.println("--------------------------------------------");
        System.out.println("Digite a opção: ");
        return Integer.parseInt(ler.nextLine());
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(Config.LOCALHOST, Config.IP);
        DataOutputStream mandaParaServer = new DataOutputStream(socket.getOutputStream());
        DataInputStream lerDoServer = new DataInputStream(socket.getInputStream());
        Scanner lerSaida = new Scanner(System.in);
        while (true) {
            int op = menu();
            mandaParaServer.writeInt(op);
            mandaParaServer.flush();
            switch (op) {
                case 1: { //CADASTRAR ALUNO
                    String nome, matricula, curso;
                    System.out.println("INFORME O NOME: ");
                    nome = lerSaida.next();
                    System.out.println("INFORME A MATRICULA: ");
                    matricula = lerSaida.next();
                    System.out.println("INFORME O CURSO: ");
                    curso = lerSaida.next();

                    mandaParaServer.writeUTF(nome);
                    mandaParaServer.flush();
                    mandaParaServer.writeUTF(matricula);
                    mandaParaServer.flush();
                    mandaParaServer.writeUTF(curso);
                    mandaParaServer.flush();

                    clearConsole();
                    System.out.println(lerDoServer.readUTF());
                    break;
                }
                case 2: { //LISTAR ALUNOS                    
                    clearConsole();
                    System.out.println(lerDoServer.readUTF());
                    break;
                }
                case 3: { //BUSCAR ALUNO
                    System.out.println("INFORME A MATRICULA: ");
                    String matricula = lerSaida.next();
                    mandaParaServer.writeUTF(matricula);
                    mandaParaServer.flush();

                    clearConsole();
                    System.out.println(lerDoServer.readUTF());
                    break;
                }
                case 4: { //REMOVER ALUNO
                    System.out.println("INFORME A MATRICULA: ");
                    String matricula = lerSaida.next();
                    mandaParaServer.writeUTF(matricula);
                    mandaParaServer.flush();

                    clearConsole();
                    System.out.println(lerDoServer.readUTF());
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
    }
}



