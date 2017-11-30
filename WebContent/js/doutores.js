var baseHost = 'http://localhost:8080/medsystem-rest/';
var hostDoutores = 'http://localhost:8080/medsystem-rest/services/';

function cadastrarDoutor() {
  var userLogado = JSON.parse(sessionStorage.getItem('usuarioLogado'));
  var data = $('#addDoutor').serializeJSON();
  data = "{\"doutor\":" + JSON.stringify(data) + "}";
  
  $.ajax({
    url: hostDoutores + 'doutores/',
    type: 'POST',
    contentType: 'application/json',
    data: data,
    success: function(data) {
      $('#addDoutor')[0].reset();
      alert('Doutor cadastrado com sucesso!');
    },
    error: function(data) {
      alert('Ocorreu um erro ao tentar cadastrar o doutor!');
    }
  });
}

function listTodosOsDoutores() {
  $('#doutoresGrid').html('');
  
  $.ajax({
    url: hostDoutores + 'doutores',
    type: 'GET',
    contentType: 'application/json',
    success: function(data) {
    	if ($.isArray(data.doutores.link)) {
        for (var i = 0; i < data.doutores.link.length; i++) {
          var link = data.doutores.link[i]['@href'];
          segueLinkDoutor(link);
        }
      } else {
        var link = data.doutores.link['@href'];
        segueLinkDoutor(link);
      }
    },
    error: function() {
      alert('Erro ao carregar os doutores cadastrados!');
    }
  });
}

function segueLinkDoutor(link) {
  $.ajax({
    url: hostDoutores + link,
    type: 'GET',
    success: function(data) {
      addDoutoresNaGrid(data.doutor);
    },
    error: function(data) {
      console.log('Erro ao listar o doutor de ID ' + data.doutor.id);
    }
  });
}

function addDoutoresNaGrid(doutor) {
  var data = '<tr>'
           +   '<td>' + doutor.id + '</td>'
           +   '<td>' + doutor.nome + '</td>'
           +   '<td>' + doutor.faixaIdade + '</td>'
           + '</tr>';
  $('#doutoresGrid').append(data);
}