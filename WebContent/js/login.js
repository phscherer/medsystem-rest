var baseHost = 'http://localhost:8080/medsystem-rest/';
var host = 'http://localhost:8080/medsystem-rest/services/usuarios/';

function enter() {
	var data = $("#formLogin").serializeJSON();
	$.ajax({
		url: host + 'login/' + data.nomeUsuario + '/' + data.senha,
		type: 'GET',
		contentType: 'application/json',
		data: data,
		success: function(data) {
			sessionStorage.setItem("usuarioLogado", JSON.stringify(data.usuario));
			window.location.href = baseHost + "index.html";
		},
		error: function(e) {
			alert('Login ou senha inválidos, tente novamente!');
		}
	});
}