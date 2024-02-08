<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title> 
<link rel="stylesheet" href="estilo.css">
</head>
<body class="container">
<section class="area-register">
        <div class="register">
            <div>
                <img src="" alt=""> <!--logo se tiver -->
            </div>
            <h1>ACESSE SUA CONTA</h1>
            <form action="cadastro" method="post">


				<section class="primary_form">
					<div>
	                <label for="nome">Nome</label>
	                <input type="text" class="text" name="nome" placeholder="Nome">
	                </div>
	                
	                <div>
	                <label for="nomeSocial">Nome Social</label>
	                <input type="text" class="text" name="nomeSocial" placeholder="Como quer ser chamado: ">
	                </div>
	
					<div>
					<label for="cpf">Digite seu cpf.</label>
	                <input type="text" class="text" name="cpf" placeholder="Digite seu CPF:">
					</div>	
	
	
					<div>
					<label for="email">Digite seu e-mail.</label>
	                <input type="text" class="text" name="email" placeholder="Seu melhor e-mail" autofocus>
					</div>
	
					<div>
			        <label for="senha">Crie uma senha.</label>
	                <input type="text" class="text" name="senha" placeholder="Digite a senha.">
					</div>	
				</section>
	<hr>
                			
  				<div class="secondary_form">
  					<label for="tipoTel">Tipo do telefone</label>
  					<select id="tipoTel" name="tipoTel">
  						<option value="1">Comercial<option>
  						<option value="2">Residencial<option>
  						<option value="3">Celular<option>
  					</select>
  					
  					<div>
	  					<label for="telefone">Telefone</label>
	  					<input type="text" name="telefone" id="telefone">
  					</div>
  				</div>
  				
  			<hr>	
  				
  				<section class="third_section">
	  				<div>
		  				<label for="cep">CEP</label>
		  				<input type="number" name="cep" id="cep">
	  				</div>
	  				
	  				<div>
		  				<label for="rua">Rua</label>
		  				<input type="text" name="rua" id="rua">
	  				</div>
	  				
	  			  	<div>
		  				<label for="nrEndereco">Número</label>
		  				<input type="number" name="nrEndereco" id="nrEndereco">
	  				</div>
	  			
	  			  	<div>
		  				<label for="bairro">Bairro</label>
		  				<input type="text" name="bairro" id="bairro">
	  				</div>
	  				
	  			
	  				<div>
		  				<label for="estado">Estado</label>
		  				<input type="text" name="estado" id="estado">
	  				</div>
	  				
	 
	  				<div>
		  				<label for="cidade">Cidade</label>
		  				<input type="text" name="cidade" id="cidade">
	  				</div>			
  				
  				</section>
  				
  		
                <ul class="termos">
                    <li>
                        <div class="checkboxx">
                            <input type="checkbox" class="checkbox" name="termo">
                        </div>
                        <label for="termo">Eu concordo com os <a href="#">termos de uso da Fintech</a></label>
                    </li>
                </ul>

                <input type="submit" class="submit" value="Cadastrar">

            </form>
            <p>Já tem uma conta? <a href="index.jsp">Faça login!</a></p>
        </div>
    </section>
</body>
</html>