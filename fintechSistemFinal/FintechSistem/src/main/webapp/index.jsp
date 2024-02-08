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
	    <section class="area-login">
        <div class="login">
            <div>
                <img src="" alt="">
            </div>
            <h1>ACESSE SUA CONTA</h1>
            <form action="login" method="post">
                <label for="login">Endereço de E-mail</label>
                <input  id="usuario" type="text" class="text" name="login" placeholder="Endereço de e-mail" autofocus>
                <label for="senha">Senha</label>
                <input id="senha" type="password" class="text" name="senha" placeholder="Senha">
         
                <input type="submit" class="submit" value="Entrar">

            </form>
            <p>Ainda não tem uma conta? <a href="register.jsp">Crie uma agora!</a></p>
        </div>
    </section>
</body>
</html>
