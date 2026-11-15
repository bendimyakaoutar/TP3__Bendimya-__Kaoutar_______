<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="UTF-8">
		<title>Gestion de produits</title>
		<style>
			body{
				font-family : Arial,sans-serif;
				margin : 20px;
				background : #ffffff;
				color:#222222;
			}
			body.dark {
				background: #1e1e1e;
				color: #eeeeee;
			}
			body.dark a{
				color: #8ab4f8;
			}
			body.dark input,
			body.dark textarea,
			body.dark button{
				background: #333333;
				color: #ffffff;
				border: 1px solid #666666;
			}
			</style>
		</head>
	<body class="${cookie.theme.value eq 'dark'?'dark':''}">
		
		
	