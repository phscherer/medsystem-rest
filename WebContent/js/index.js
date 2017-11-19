var baseHost = 'http://localhost:8080/medsystem-rest/';
var host = 'http://localhost:8080/medsystem-rest/services/usuarios/';

function exibirUsuarioLogado() {
  var user = JSON.parse(sessionStorage.getItem('usuarioLogado'));
  $("userLogado").html(usuarioLogado.nome);
}

function sair() {
  sessionStorage.removeItem("usuarioLogado");
  window.location.href = baseHost + "login.html";
}
