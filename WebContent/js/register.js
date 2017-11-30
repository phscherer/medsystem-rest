var baseHost = 'http://localhost:8080/medsystem-rest/';
var host = 'http://localhost:8080/medsystem-rest/services/usuarios/';

function criarUsuario() {
  var data = $('#formCadastro').serializeJSON();
  data = "{\"usuario\":" + JSON.stringify(data) + "}";
  $.ajax({
    url: host,
    type: 'POST',
    contentType: 'application/json',
    data: data,
    success: function(data) {
      $('#formCadastro')[0].reset();
      alert('Voce foi cadastrado com sucesso, pode fazer login!');
      window.location.href = baseHost + 'login.html';
    },
    error: function(data) {
      alert('Ocorreu um erro ao tentar efetuar o seu cadastro!');
    }
  });
}