import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class ContaBancaria {
    int numeroConta;
    String titular;
    String senha;
    double saldo;
    boolean bloqueada;
    int tentativasSenha;
    ArrayList<String> extrato;

    public ContaBancaria(int numeroConta, String titular, String senha, double saldoInicial) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.senha = senha;
        this.saldo = saldoInicial;
        this.bloqueada = false;
        this.tentativasSenha = 0;
        this.extrato = new ArrayList<>();
        this.extrato.add("Abertura de conta com deposito inicial: R$ " + saldoInicial);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Random gerador = new Random();
        ArrayList<ContaBancaria> bancoDeDados = new ArrayList<>();
        
        // 🚨 AS CONTAS DE TESTE DE VOLTA AQUI:
        bancoDeDados.add(new ContaBancaria(1001, "Maria Silva", "123", 500.0));
        bancoDeDados.add(new ContaBancaria(1002, "Pedro Santos", "456", 100.0));

        int opcaoHome = 0;
        while (opcaoHome != 3) {
            System.out.println("\n=====================================");
            System.out.println("       JAVA BANK SYSTEM v2.0         ");
            System.out.println("=====================================");
            System.out.println("1 - Entrar na sua Conta (Login)");
            System.out.println("2 - Criar Nova Conta");
            System.out.println("3 - Desligar Sistema");
            System.out.print("Escolha uma opcao: ");
            opcaoHome = teclado.nextInt();
            teclado.nextLine();

            if (opcaoHome == 2) {
                System.out.println("\n--- CRIE SUA CONTA ---");
                System.out.print("Nome Completo: ");
                String nome = teclado.nextLine();
                System.out.print("Crie uma Senha: ");
                String senha = teclado.nextLine();
                System.out.print("Deposito Inicial (R$): ");
                double depIn = teclado.nextDouble();
                teclado.nextLine();
                
                int numG = 1000 + gerador.nextInt(9000);
                bancoDeDados.add(new ContaBancaria(numG, nome, senha, depIn));
                System.out.println("\n🎉 CONTA CRIADA! Numero para Login: " + numG);
                
            } else if (opcaoHome == 1) {
                System.out.print("\nNumero da conta: ");
                int numLog = teclado.nextInt();
                teclado.nextLine();
                
                ContaBancaria conta = null;
                for (ContaBancaria c : bancoDeDados) {
                    if (c.numeroConta == numLog) {
                        conta = c;
                        break;
                    }
                }
                
                if (conta == null) {
                    System.out.println("❌ Conta nao encontrada.");
                    continue;
                }
                if (conta.bloqueada) {
                    System.out.println("❌ CONTA BLOQUEADA!");
                    continue;
                }

                System.out.print("Senha para " + conta.titular + ": ");
                String sLog = teclado.nextLine();
                
                if (conta.senha.equals(sLog)) {
                    System.out.println("\n🔓 Login efetuado!");
                    conta.tentativasSenha = 0;
                    int opM = 0;
                    
                    while (opM != 5) {
                        System.out.println("\n--- MENU (Nº " + conta.numeroConta + ") ---");
                        System.out.println("1 - Saldo/Extrato\n2 - Deposito\n3 - Saque\n4 - Transferencia\n5 - Sair");
                        System.out.print("Opcao: ");
                        opM = teclado.nextInt();
                        teclado.nextLine();
                        
                        switch (opM) {
                            case 1:
                                System.out.println("\nSaldo: R$ " + conta.saldo);
                                System.out.println("--- EXTRATO ---");
                                for (String t : conta.extrato) {
                                    System.out.println(">> " + t);
                                }
                                break;
                            case 2:
                                System.out.print("Valor deposito: R$ ");
                                double d = teclado.nextDouble();
                                if (d > 0) {
                                    conta.saldo += d;
                                    conta.extrato.add("Deposito: +R$ " + d);
                                    System.out.println("✅ Deposito OK!");
                                }
                                break;
                            case 3:
                                System.out.print("Valor saque: R$ ");
                                double s = teclado.nextDouble();
                                if (s <= conta.saldo && s > 0) {
                                    conta.saldo -= s;
                                    conta.extrato.add("Saque: -R$ " + s);
                                    System.out.println("✅ Saque OK!");
                                } else {
                                    System.out.println("❌ Saldo insuficiente.");
                                }
                                break;
                            case 4:
                                System.out.print("Conta destino: ");
                                int cDest = teclado.nextInt();
                                System.out.print("Valor da Transferencia: R$ ");
                                double vT = teclado.nextDouble();
                                teclado.nextLine();
                                
                                ContaBancaria dest = null;
                                for (ContaBancaria c : bancoDeDados) {
                                    if (c.numeroConta == cDest) {
                                        dest = c;
                                        break;
                                    }
                                }
                                
                                if (dest == null) {
                                    System.out.println("❌ Conta destino nao encontrada.");
                                } else if (dest.numeroConta == conta.numeroConta) {
                                    System.out.println("❌ Voce nao pode transferir para si mesmo.");
                                } else if (conta.saldo < vT || vT <= 0) {
                                    System.out.println("❌ Saldo insuficiente.");
                                } else {
                                    conta.saldo -= vT;
                                    dest.saldo += vT;
                                    conta.extrato.add("PIX enviado para " + dest.titular + ": -R$ " + vT);
                                    dest.extrato.add("PIX recebido de " + conta.titular + ": +R$ " + vT);
                                    System.out.println("✅ Transferencia concluida!");
                                }
                                break;
                        }
                    }
                } else {
                    conta.tentativasSenha++;
                    System.out.println("❌ Senha incorreta! Tentativas: " + conta.tentativasSenha + "/3");
                    if (conta.tentativasSenha >= 3) {
                        conta.bloqueada = true;
                        System.out.println("🚨 CONTA BLOQUEADA POR SEGURANÇA!");
                    }
                }
            }
        }
        System.out.println("Sistema desligado. Ate logo!");
        teclado.close();
    }
}
