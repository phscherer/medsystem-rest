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
      alert('Bem vindo, ' + data.usuario.nome + '!');
      window.location.href = baseHost + "consultas/list-per-patient.html";
    },
    error: function(data) {
      console.log(data);
      alert('Login ou senha inválidos, tente novamente!');
    }
  });
}