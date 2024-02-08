package br.com.fintech.system.model;

import java.util.Scanner;

import br.com.fintech.system.enumerator.TipoAcao;

public class Onboarding {
	
	//atributos
	private String tutorialResumo;
	// vai puxar o tipo do onboarding pelo TipoAcao
	
	// Método contrutor xuxu
	
	public Onboarding() {
		
	};
	
	// Método get
	
	public String getTutorialRemumo() {
		return tutorialResumo;
	}
	
	// Método set
	// não precisa
	
	// Método da lógica			
	
	Scanner sc = new Scanner(System.in);
		
	public void realizarOnboarding(Scanner sc) {
		
			        
		System.out.println("Bem-vindo ao Onboarding!");
        System.out.println("Selecione a funcionalidade para a qual deseja assistir ao vídeo:");
	     
	        TipoAcao acao = null;
	        while (acao == null) {
	            System.out.println("Digite a ação desejada:");
	            System.out.println("[1] Login");
	            System.out.println("[2] Onboarding");
	            System.out.println("[3] Transação");
	            System.out.println("[4] Dashboard");
	            System.out.println("[5] Sair");

	            int escolha = sc.nextInt();

	            switch (escolha) {
	                case 1:
	                    acao = TipoAcao.LOGIN;
	    	            System.out.println("Rodando vídeo LOGIN.");
	                    break;
	                case 2:
	                    acao = TipoAcao.ONBOARDING;
	    	            System.out.println("Rodando vídeo ONBOARDING.");
	                    break;
	                case 3:
	                    acao = TipoAcao.TRANSACAO;
	    	            System.out.println("Rodando vídeo TRANSAÇÃO.");
	                    break;
	                case 4:
	                    acao = TipoAcao.DASHBOARD;
	    	            System.out.println("Rodando vídeo DASHBOARD.");
	                    break;
	                case 5:
	                    System.out.println("Saindo do sistema. Até logo!");
	                    return;
	                default:
	                    System.out.println("Escolha uma opção válida.");
	            }
	        }
	}
}
