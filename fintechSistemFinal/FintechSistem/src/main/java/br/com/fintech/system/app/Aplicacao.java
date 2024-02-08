package br.com.fintech.system.app;

public class Aplicacao {
	
//	
//	
//    private List<Cadastro> usuarios = new ArrayList<>();
//
//    public List<Cadastro> getUsuarios() {
//        return usuarios;
//    }
//
//    public Aplicacao() {}
//
//    public static void main(String[] args) {
//
//    	Scanner sc = new Scanner(System.in);
//    	Aplicacao aplicacao = new Aplicacao();
//    	Cadastro usuarioExistente = null;
//        boolean usuarioLogado = false;
//        
//        System.out.println("Bem-vindo ao Sistema Fintech!");
//
//    	
//    	while (true) {
//    	    if (!usuarioLogado) {
//    	        System.out.println("Digite seu nome de usuário (ou 'sair' para encerrar):");
//    	        String nomeUsuario = sc.next();
//
//    	        if (nomeUsuario.equalsIgnoreCase("sair")) {
//    	            break;
//    	        }
//
//    	        usuarioExistente = aplicacao.buscarUsuario(nomeUsuario); 
//
//    	        if (usuarioExistente == null) {
//                  System.out.println("Bem-vindo! Para acessar o sistema Fintech, é necessário fazer o cadastro:");
//                  Cadastro novoUsuario = criarNovoUsuario(nomeUsuario, sc);
//                  aplicacao.usuarios.add(novoUsuario);
//                  
//                  CadastroDAO newGet = new CadastroDAO();
//                  ArrayList<Cadastro> usuarios = newGet.getTUsuarios();
//                  System.out.println("cd_usuario | t_tipo_tel_cd_tipo_tel | nr_telefone | nm_usuario | nm_social | nr_cpf");
//                  
//                  for (Cadastro usuario : usuarios) {
//
//                      System.out.println(usuario.getCdUsuario() + " | " + usuario.getTipoTelefone() + " | " + usuario.getTelefone() + " | " + usuario.getNome() + " | " + usuario.getNomeSocial() + " | " + usuario.getCpf());
//                  }
//                  
//                  System.out.println();
//                  
//                  ConexaoDAO.getAll();
//                  
//                  System.out.println();
//                  
//    	        } else {
//    	            realizarLogin(usuarioExistente, sc, aplicacao);
//    	            usuarioLogado = true; 
//    	        }
//    	    } else {
//
//              System.out.println("Digite 'sim' caso deseje executar outra ação! Digite 'sair' para encerrar:");
//              String acao = sc.next();
//  
//              if (acao.equalsIgnoreCase("sair")) {
//                  break;
//              }else {
//            	  aplicacao.realizarAcoesDoUsuario(usuarioExistente, sc);
//              }
//    	    }
//    	}
//    	
//    	sc.close();
//    	
//    }
//
//    public Cadastro buscarUsuario(String nome) {
//        for (Cadastro usuario : usuarios) {
//            if (usuario.getNome().equalsIgnoreCase(nome)) {
//                return usuario;
//            }
//        }
//        return null;
//    }
//
//    public static Cadastro criarNovoUsuario(String nomeUsuario, Scanner sc) {
//        System.out.println("Iniciando o cadastro do(@): " + nomeUsuario);
//        CadastroDAO newPost = new CadastroDAO();
////        ArrayList<Cadastro> usuarios = newDao.getTUsuarios();
////        System.out.println("cd_usuario | t_tipo_tel_cd_tipo_tel | nr_telefone | nm_usuario | nm_social | nr_cpf");
////        for (Cadastro usuario : usuarios) {
////
////            System.out.println(usuario.getCdUsuario() + " | " + usuario.getTipoTelefone() + " | " + usuario.getTelefone() + " | " + usuario.getNome() + " | " + usuario.getNomeSocial() + " | " + usuario.getCpf());
////        }
////        
////        System.out.println();
////        
////        ConexaoDAO.getAll();
////        
////        System.out.println();
//            	
//        Cadastro novoUsuario = new Cadastro();
//        novoUsuario.setEmailLogin("");
//        novoUsuario.setSenhaLogin("");
//        novoUsuario.cadastro();
//        
//        newPost.postTUsuarios(novoUsuario);
//        
//        return novoUsuario;
//	    
//	    
//    }
//
//    public static void realizarLogin(Cadastro usuario, Scanner sc, Aplicacao aplicacao) {
//        System.out.println("Bem-vindo de volta, " + usuario.getNome() + "! Realize o login:");
//        System.out.print("Digite seu email: ");
//        String emailLogin = sc.next();
//        System.out.print("Digite sua senha: ");
//        String senhaLogin = sc.next();
//
//        if (usuario.login(emailLogin, senhaLogin)) {
//            System.out.println("Login realizado com sucesso!");
//            aplicacao.realizarAcoesDoUsuario(usuario, sc);
//        } else {
//            System.out.println("Erro ao tentar realizar o login. Verifique suas credenciais e tente novamente.");
//        }
//    }
//
//    public void realizarAcoesDoUsuario(Cadastro usuario, Scanner sc) {
//        TipoAcao acao = null;
//        Dashboard dashboard = new Dashboard();
//
//        while (acao == null) {
//            System.out.println("Digite a ação desejada:");
//            System.out.println("[1] Onboarding");
//            System.out.println("[2] Transação");
//            System.out.println("[3] Dashboard");
//            System.out.println("[4] Sair");
//
//            int escolha = sc.nextInt();
//
//            switch (escolha) {
//                case 1:
//                    acao = TipoAcao.ONBOARDING;
//                    Onboarding onboarding = new Onboarding();
//                    onboarding.realizarOnboarding(sc);
//                    break;
//                case 2:
//                    acao = TipoAcao.TRANSACAO;
//                    Transacao transacao = new Transacao(usuarios);
//                    transacao.realizarTransacao(sc);
//                    break;
//                case 3:
//                    acao = TipoAcao.DASHBOARD;
//                    System.out.println("Digite a data de início (no formato YYYY-MM-DD):");
//                    String dataInicioStr = sc.next();
//                    LocalDate dataInicio = LocalDate.parse(dataInicioStr);
//
//                    System.out.println("Digite a data de fim (no formato YYYY-MM-DD):");
//                    String dataFimStr = sc.next();
//                    LocalDate dataFim = LocalDate.parse(dataFimStr);
//
//                    dashboard.mostrarResumo(dataInicio, dataFim);
//                    break;
//                case 4:
//                    System.out.println("Saindo do sistema. Até logo!");
//                    return;
//                default:
//                    System.out.println("Escolha uma opção válida.");
//            }
//        }
//    }
}
