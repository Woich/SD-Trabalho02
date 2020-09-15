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
		    	System.out.println("2 - Listar minhas ações");
		    	System.out.println("3 - Registrar ordem de compra de ação");
		    	System.out.println("4 - Registrar ordem de venda de ação");
		    	System.out.println("5 - Mostrar lista de cotações");
		    	System.out.println("6 - Registrar interesse em notificações");
		    	System.out.println("7 - Mostrar lista de Empresas");
		    	System.out.println("-1 - Sair");
		    	
		    	escolha = scannerInt.nextInt();  
		    	
		    	switch (escolha) {		    	
		    		case 1:
		    			System.out.println("Digite o nome da Empresa");
		    			String nomeEmpresa = scannerString.nextLine();
		    			System.out.println("Digite a quantidade de ações da Empresa");
		    			int qtdAcoes = scannerInt.nextInt();
		    			boolean incluido = referenciaServidor.insertEmpresa(nomeEmpresa, qtdAcoes, idCliente, true);
		    			if(!incluido) {
		    				System.out.println("Nã foi possível incluir a empresa");
		    			}
		    			break;
		    			
		    		case 2:
		    			System.out.println("Digite o código da ação");
		    			String codigoRemover = scannerString.nextLine();
		    			//referenciaServidor.removeCotacao(codigoRemover, idCliente);
		    			break;
		    		
		    		case 3:
		    			System.out.println("Digite o código da ação");
		    			String codigoComprar = scannerString.nextLine();
		    			System.out.println("Digite o valor mÃ¡ximo que deseja pagar");
		    			int valorMaximoCompra = scannerInt.nextInt();
		    			System.out.println("Digite o prazo mÃ¡ximo da ordem (em minutos)");
		    			int prazoCompra = scannerInt.nextInt();
		    			//referenciaServidor.comprarAcao(codigoComprar, valorMaximoCompra, prazoCompra, idCliente);
		    			break;
		    		
		    		case 4: 
		    			System.out.println("Digite o código da ação");
		    			String codigoVender = scannerString.nextLine();
		    			System.out.println("Digite o valor mÃ­nimo que deseja receber pela aÃ§Ã£o");
		    			int valorMinimoVenda = scannerInt.nextInt();
		    			System.out.println("Digite o prazo mÃ¡ximo da ordem (em minutos)");
		    			int prazoVenda = scannerInt.nextInt();
		    			//referenciaServidor.venderAcao(codigoVender, valorMinimoVenda, prazoVenda, idCliente);
		    			break;
		    			
		    		case 5: 
		    			//referenciaServidor.listarCotacoes(idCliente);
		    			break;
		    		
		    		case 6: 
		    			System.out.println("Digite o código da aÃ§Ã£o");
		    			String codigoNotificacao = scannerString.nextLine();
		    			System.out.println("Digite o limite de ganho");
		    			int limiteGanho = scannerInt.nextInt();
		    			System.out.println("Digite o limite de perda");
		    			int limitePerda = scannerInt.nextInt();
		    			//mÃ©todo de registrar interesse 
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
