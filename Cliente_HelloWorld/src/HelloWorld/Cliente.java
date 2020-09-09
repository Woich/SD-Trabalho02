package HelloWorld;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.UUID;


public class Cliente {

	public static void main(String[] args) {
		try {
			
			Registry referenciaServicoNomes = LocateRegistry.getRegistry();
			InterfaceServ referenciaServidor = (InterfaceServ) referenciaServicoNomes.lookup("HelloWorld");
			
			UUID idCliente = referenciaServidor.registrar(new CliImpl(referenciaServidor));

			
			new CliImpl(referenciaServidor);
			
			int escolha = 0;
			
		    Scanner scanner = new Scanner(System.in);
		    
		    while(escolha != 7) {
		    
		    	System.out.println("O que deseja fazer?\n");
		    	System.out.println("1 - Inserir ação na lista de cotações");
		    	System.out.println("2 - Remover ação da lista de cotações");
		    	System.out.println("3 - Registrar ordem de compra de ação");
		    	System.out.println("4 - Registrar ordem de venda de ação");
		    	System.out.println("5 - Mostrar lista de cotações");
		    	System.out.println("6 - Registrar interesse em notificações");
		    	System.out.println("7 - Sair");


		    

		    	escolha = scanner.nextInt();  
		    	
		    	switch (escolha) {		    	
		    		case 1:
		    			System.out.println("Digite o código da ação");
		    			String codigoInserir = scanner.nextLine();
		    			referenciaServidor.insertCotacao(codigoInserir, idCliente);
		    			break;
		    			
		    		case 2:
		    			System.out.println("Digite o código da ação");
		    			String codigoRemover = scanner.nextLine();
		    			referenciaServidor.removeCotacao(codigoRemover, idCliente);
		    			break;
		    		
		    		case 3:
		    			System.out.println("Digite o código da ação");
		    			String codigoComprar = scanner.nextLine();
		    			System.out.println("Digite o valor máximo que deseja pagar");
		    			int valorMaximoCompra = scanner.nextInt();
		    			System.out.println("Digite o prazo máximo da ordem (em minutos)");
		    			int prazoCompra = scanner.nextInt();
		    			referenciaServidor.comprarAcao(codigoComprar, valorMaximoCompra, prazoCompra, idCliente);
		    			break;
		    		
		    		case 4: 
		    			System.out.println("Digite o código da ação");
		    			String codigoVender = scanner.nextLine();
		    			System.out.println("Digite o valor mínimo que deseja receber pela ação");
		    			int valorMinimoVenda = scanner.nextInt();
		    			System.out.println("Digite o prazo máximo da ordem (em minutos)");
		    			int prazoVenda = scanner.nextInt();
		    			referenciaServidor.venderAcao(codigoVender, valorMinimoVenda, prazoVenda, idCliente);
		    			break;
		    			
		    		case 5: 
		    			referenciaServidor.listarCotacoes(idCliente);
		    			break;
		    		
		    		case 6: 
		    			System.out.println("Digite o código da ação");
		    			String codigoNotificacao = scanner.nextLine();
		    			System.out.println("Digite o limite de ganho");
		    			int limiteGanho = scanner.nextInt();
		    			System.out.println("Digite o limite de perda");
		    			int limitePerda = scanner.nextInt();
		    			//método de registrar interesse 
		    			break;
		    			
		    		default: 
		    			break;
		    			
		    		
		    	}
		    }
		
		}
		catch(Exception a) { }
	}

}
