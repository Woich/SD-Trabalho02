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
		    Scanner scannerDouble = new Scanner(System.in);
		    Scanner scannerString = new Scanner(System.in);
		    
		    while(escolha != -1) {
		    
		    	System.out.println("O que deseja fazer?\n");
		    	System.out.println("1 - Inserir uma nova empresa");
		    	System.out.println("2 - Listar minhas ações");
		    	System.out.println("3 - Registrar ordem de compra de ação");
		    	System.out.println("4 - Registrar ordem de venda de ação");
		    	System.out.println("5 - Registrar interesse em notificações");
		    	System.out.println("6 - Remover interesse em notificações");
		    	System.out.println("7 - Listar Interesses");
		    	System.out.println("8 - Registrar aumento geral de preço");
		    	System.out.println("9 - Registrar diminuição geral de preço");
		    	System.out.println("10 - Mostrar lista de Empresas");
		    	System.out.println("11 - Listar Notificações");
		    	System.out.println("-1 - Sair");
		    	
		    	escolha = scannerInt.nextInt();  
		    	
		    	switch (escolha) {		    	
		    		case 1:
		    			System.out.println("Digite o nome da Empresa(minimo 3 caracteres)");
		    			String nomeEmpresa = scannerString.nextLine();
		    			System.out.println("Digite a quantidade de ações da Empresa");
		    			int qtdAcoes = scannerInt.nextInt();
		    			boolean incluido = referenciaServidor.insertEmpresa(nomeEmpresa, qtdAcoes, idCliente, true);
		    			if(!incluido) {
		    				System.out.println("Nã foi possível incluir a empresa");
		    			}
		    			break;
		    			
		    		case 2:
		    			System.out.println("-----------------------------------------");
		    			System.out.println("MINHAS AÇÕES");
		    			System.out.println("-----------------------------------------");
		    			System.out.println("CODIGO | VALOR COMPRA");
		    			List<Acao> listaMinhasAcoes = referenciaServidor.listarAcoesCliente(idCliente);
		    			if(listaMinhasAcoes != null) {
		    				for(Acao item : listaMinhasAcoes) {
			    				System.out.println("-----------------------------------------");
			    				System.out.println(item.getCodigo() + " | " + item.getPrecoDeCompra());
			    			}
		    			}
		    			System.out.println("-----------------------------------------");
		    			System.out.println("");
		    			break;
		    		
		    		case 3:
		    			System.out.println("-----------------------------------------");
		    			System.out.println("EMPRESAS DISPONÍVEIS");
		    			System.out.println("-----------------------------------------");
		    			System.out.println("CODIGO | NOME | VALOR EMPRESA");
		    			for(Empresa item : referenciaServidor.listarEmpresas()) {
		    				System.out.println("-----------------------------------------");
		    				System.out.println(item.getCodigo() + " | " + item.getNome()+ " | " +item.getValorEmpresa());
		    			}
		    			System.out.println("-----------------------------------------");
		    			System.out.println("");
		    			System.out.println("De qual empresa deseja comprar?(Informar Código)");
		    			String codigoEmpresaCompra = scannerString.nextLine();
		    			System.out.println("Qual o valor máximo que deseja pagar?");
		    			int maxPagar = scannerInt.nextInt();
		    			System.out.println("Quantas Ações deseja compar?");
		    			int qtdCompra = scannerInt.nextInt();
		    			System.out.println("Qual o tempo que essa ordem vai ficar ativa em minutos?");
		    			int minCompra = scannerInt.nextInt();
		    			referenciaServidor.comprarAcao(codigoEmpresaCompra, maxPagar, minCompra, idCliente, qtdCompra);
		    			break;
		    		
		    		case 4: 
		    			System.out.println("-----------------------------------------");
		    			System.out.println("MINHAS AÇÕES");
		    			System.out.println("-----------------------------------------");
		    			System.out.println("COD EMPRESA | CODIGO | VALOR COMPRA");
		    			List<Acao> listaAcoes = referenciaServidor.listarAcoesCliente(idCliente);
		    			if(listaAcoes != null) {
		    				for(Acao item : listaAcoes) {
			    				System.out.println("-----------------------------------------");
			    				System.out.println(item.getEmpresa().getCodigo() + " | " +item.getCodigo() + " | " + item.getPrecoDeCompra());
			    			}
		    			}
		    			System.out.println("-----------------------------------------");
		    			System.out.println("");
		    			System.out.println("De qual empresa deseja vender?(Informar Código da Empresa)");
		    			String codigoAcaoVenda = scannerString.nextLine();
		    			System.out.println("Qual o valor minimo que deseja receber?");
		    			int minReceber = scannerInt.nextInt();
		    			System.out.println("Quantas Ações deseja vender?");
		    			int qtdVenda = scannerInt.nextInt();
		    			System.out.println("Qual o tempo que essa ordem vai ficar ativa em minutos?");
		    			int minVenda = scannerInt.nextInt();
		    			referenciaServidor.venderAcao(codigoAcaoVenda, minReceber, minVenda, idCliente, qtdVenda);
		    			break;
		    		
		    		case 5: 
		    			System.out.println("-----------------------------------------");
		    			System.out.println("EMPRESAS DISPONÍVEIS");
		    			System.out.println("-----------------------------------------");
		    			System.out.println("CODIGO | NOME | VALOR EMPRESA");
		    			for(Empresa item : referenciaServidor.listarEmpresas()) {
		    				System.out.println("-----------------------------------------");
		    				System.out.println(item.getCodigo() + " | " + item.getNome()+ " | " +item.getValorEmpresa());
		    			}
		    			System.out.println("-----------------------------------------");
		    			System.out.println("");
		    			System.out.println("Qual a empresa desejada (Informar Código):");
		    			String codEmpresa = scannerString.nextLine();
		    			referenciaServidor.registrarInteresse(idCliente, codEmpresa);
		    			break;
		    		
		    		case 6: 
		    			System.out.println("-----------------------------------------");
		    			System.out.println("EMPRESAS COM INTERESSE");
		    			System.out.println("-----------------------------------------");
		    			System.out.println("CODIGO | NOME");
		    			List<Empresa> empresasInteresse = referenciaServidor.listarEmpresasInteressado(idCliente);
		    			if(empresasInteresse != null) {
		    				for(Empresa item : empresasInteresse) {
			    				System.out.println("-----------------------------------------");
			    				System.out.println(item.getCodigo() + " | " + item.getNome());
			    			}
		    			}
		    			System.out.println("-----------------------------------------");
		    			System.out.println("");
		    			System.out.println("Qual a empresa desejada (Informar Código):");
		    			String codEmpresaInteresse = scannerString.nextLine();
		    			referenciaServidor.removeInteresse(idCliente, codEmpresaInteresse);
		    			break;
		    			
		    		case 7: 
		    			System.out.println("-----------------------------------------");
		    			System.out.println("EMPRESAS COM INTERESSE");
		    			System.out.println("-----------------------------------------");
		    			System.out.println("CODIGO | NOME");
		    			List<Empresa> listaEmpresasInteresse = referenciaServidor.listarEmpresasInteressado(idCliente);
		    			if(listaEmpresasInteresse != null) {
		    				for(Empresa item : listaEmpresasInteresse) {
			    				System.out.println("-----------------------------------------");
			    				System.out.println(item.getCodigo() + " | " + item.getNome());
			    			}
		    			}
		    			System.out.println("-----------------------------------------");
		    			System.out.println("");
		    			break;
		    		
		    		case 8: 
		    			System.out.println("De quanto será o aumento?");
		    			double valorCotacao = scannerDouble.nextDouble();
		    			referenciaServidor.insertCotacao(valorCotacao);
		    			break;
		    		
		    		case 9: 
		    			System.out.println("De quanto será a reducao?");
		    			double valorCotacaoReducao = scannerDouble.nextDouble();
		    			referenciaServidor.removeCotacao(valorCotacaoReducao);
		    			break;
		    			
		    		case 10:
		    			System.out.println("-----------------------------------------");
		    			System.out.println("CODIGO | NOME | VALOR EMPRESA");
		    			for(Empresa item : referenciaServidor.listarEmpresas()) {
		    				System.out.println("-----------------------------------------");
		    				System.out.println(item.getCodigo() + " | " + item.getNome()+ " | " +item.getValorEmpresa());
		    			}
		    			System.out.println("-----------------------------------------");
		    			break;
		    		
		    		case 11: 
		    			clienteControle.listarNotificacoes();
		    			break;
		    		
		    		default: 
		    			break;
		    			
		    		
		    	}
		    }
		
		}
		catch(Exception a) { System.out.println(a.getMessage()); }
	}

}
