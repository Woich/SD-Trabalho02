package HelloWorld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class Cliente {

	public static void main(String[] args) {
		try {
			

			Registry referenciaServicoNomes = LocateRegistry.getRegistry(1099);
			
			InterfaceServ referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("Servidor");

			CliImpl clienteControle = new CliImpl(referenciaServidor);
			
			UUID idCliente = referenciaServidor.registrar(clienteControle);
			
			int escolha = 0;
			
		    Scanner scannerInt = new Scanner(System.in);
		    Scanner scannerString = new Scanner(System.in);
		    
		    while(escolha != -1) {
		    
		    	System.out.println("O que deseja fazer?\n");
		    	System.out.println("1 - Inserir uma nova empresa");
		    	System.out.println("2 - Listar minhas a��es");
		    	System.out.println("3 - Registrar ordem de compra de a��o");
		    	System.out.println("4 - Registrar ordem de venda de a��o");
		    	System.out.println("5 - Mostrar lista de cota��es");
		    	System.out.println("6 - Registrar interesse em notifica��es");
		    	System.out.println("7 - Mostrar lista de Empresas");
		    	System.out.println("-1 - Sair");
		    	
		    	escolha = scannerInt.nextInt();  
		    	
		    	switch (escolha) {		    	
		    		case 1:
		    			System.out.println("Digite o nome da Empresa");
		    			String nomeEmpresa = scannerString.nextLine();
		    			System.out.println("Digite a quantidade de a��es da Empresa");
		    			int qtdAcoes = scannerInt.nextInt();
		    			boolean incluido = referenciaServidor.insertEmpresa(nomeEmpresa, qtdAcoes, idCliente, true);
		    			if(!incluido) {
		    				System.out.println("N� foi poss�vel incluir a empresa");
		    			}
		    			break;
		    			
		    		case 2:
		    			System.out.println("Digite o c�digo da a��o");
		    			String codigoRemover = scannerString.nextLine();
		    			//referenciaServidor.removeCotacao(codigoRemover, idCliente);
		    			break;
		    		
		    		case 3:
		    			System.out.println("Digite o c�digo da a��o");
		    			String codigoComprar = scannerString.nextLine();
		    			System.out.println("Digite o valor máximo que deseja pagar");
		    			int valorMaximoCompra = scannerInt.nextInt();
		    			System.out.println("Digite o prazo máximo da ordem (em minutos)");
		    			int prazoCompra = scannerInt.nextInt();
		    			//referenciaServidor.comprarAcao(codigoComprar, valorMaximoCompra, prazoCompra, idCliente);
		    			break;
		    		
		    		case 4: 
		    			System.out.println("Digite o c�digo da a��o");
		    			String codigoVender = scannerString.nextLine();
		    			System.out.println("Digite o valor mínimo que deseja receber pela ação");
		    			int valorMinimoVenda = scannerInt.nextInt();
		    			System.out.println("Digite o prazo máximo da ordem (em minutos)");
		    			int prazoVenda = scannerInt.nextInt();
		    			//referenciaServidor.venderAcao(codigoVender, valorMinimoVenda, prazoVenda, idCliente);
		    			break;
		    			
		    		case 5: 
		    			//referenciaServidor.listarCotacoes(idCliente);
		    			break;
		    		
		    		case 6: 
		    			System.out.println("Digite o c�digo da ação");
		    			String codigoNotificacao = scannerString.nextLine();
		    			System.out.println("Digite o limite de ganho");
		    			int limiteGanho = scannerInt.nextInt();
		    			System.out.println("Digite o limite de perda");
		    			int limitePerda = scannerInt.nextInt();
		    			//método de registrar interesse 
		    			break;
		    		
		    		case 7:
		    			System.out.println("CODIGO | NOME");
		    			for(Empresa item : referenciaServidor.listarEmpresas()) {
		    				System.out.println(item.getCodigo() + " | " + item.getNome());
		    			}
		    			break;
		    		default: 
		    			break;
		    			
		    		
		    	}
		    }
		
		}
		catch(Exception a) { System.out.println(a.getMessage()); }
	}

}
