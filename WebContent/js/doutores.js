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

function excluirDoutor(nome) {
  if (confirm('Deseja excluir este doutor?')) {
    $.ajax({
      url: hostDoutores + 'doutores/' + nome,
      type: 'DELETE',
      success: function(data) {
        listTodosOsDoutores();
      },
      error: function(data) {
        alert('Erro ao tentar excluir o doutor.');
      }
    });
  }
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

function carregarDoutorParaEdicao(nome) {
  $.ajax({
    url: hostDoutores + 'doutores/' + nome,
    type: 'GET',
    success: function(data) {
      var doutor = data.doutor;
      $('#editId').val(doutor.id);
      $('#editNome').val(doutor.nome);
      $('#editFaixaIdade').val(doutor.faixaIdade);
      $('#editarModalNomeDoutor').html(doutor.nome);
    },
    error: function(data) {
      alert('Erro ao carregar a consulta para edição.');
    }
  });
}

function salvarAtualizacaoDoutor() {
  var data = $('#editarDoutorForm').serializeJSON();
  data = "{\"doutor\":" + JSON.stringify(data) + "}";
  
  $.ajax({
    url: hostDoutores + 'doutores/',
    type: 'PUT',
    contentType: 'application/json',
    data: data,
    success: function(data) {
      $('#editarModal').modal('toggle');
      $('#editarDoutorForm')[0].reset();
      listTodosOsDoutores();
    },
    error: function(data) {
      alert('Erro ao tentar atualizar o doutor.');
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
           + "<td width='5%'><img src='../icons/pencil-icon.png' title='Atualizar doutor' width='20px' height='20px' style='cursor: pointer' data-toggle='modal' data-target='#editarModal' onClick='carregarDoutorParaEdicao(\""+doutor.nome+"\")'></td>"
	       + "<td width='5%'><img src='../icons/trash-icon.png' title='Excluir doutor' width='20px' height='20px' style='cursor: pointer' onClick='excluirDoutor(\""+doutor.nome+"\")'></td>"
           + '</tr>';
  $('#doutoresGrid').append(data);
}