var baseHost = 'http://localhost:8080/medsystem-rest/';
var hostConsultas = 'http://localhost:8080/medsystem-rest/services/';

function agendarConsultas() {
  var userLogado = JSON.parse(sessionStorage.getItem('usuarioLogado'));
  var data = $('#addNewConsulta').serializeJSON();
  data = "{\"consulta\":" + JSON.stringify(data) + "}";
  
  $.ajax({
    url: hostConsultas + 'consultas/usuario/' + userLogado.id,
    type: 'POST',
    contentType: 'application/json',
    data: data,
    success: function(data) {
      $('#addNewConsulta')[0].reset();
      alert('Consulta agendada com sucesso!');
    },
    error: function(data) {
      alert('Ocorreu um erro ao agendar a consulta!');
    }
  });
}

function cancelarConsulta(titulo) {
  if (confirm('Deseja cancelar esta consulta?')) {
    $.ajax({
      url: hostConsultas + 'consultas/' + titulo,
      type: 'DELETE',
      success: function(data) {
        listConsultasPorPaciente();
      },
      error: function(data) {
        alert('Erro ao cancelar a consulta');
      }
    });
  }
}

function listConsultasPorPaciente() {
  var userLogado = JSON.parse(sessionStorage.getItem('usuarioLogado'));
  $('#consultasGrid').html('');
  
  $.ajax({
    url: hostConsultas + 'consultas/usuario/' + userLogado.id,
    type: 'GET',
    contentType: 'application/json',
    success: function(data) {
      if ($.isArray(data.consultas.link)) {
        for (var i = 0; i < data.consultas.link.length; i++) {
          var link = data.consultas.link[i]['@href'];
          segueLinkConsulta(link);
        }
      } else {
        var link = data.consultas.link['@href'];
        segueLinkConsulta(link);
      }
    },
    error: function() {
      alert('Erro ao carregar as consultas do paciente!');
    }
  });
}

function listTodasAsConsultas() {
  $('#consultasGrid').html('');
  
  $.ajax({
    url: hostConsultas + 'consultas',
    type: 'GET',
    contentType: 'application/json',
    success: function(data) {
    	if ($.isArray(data.consultas.link)) {
        for (var i = 0; i < data.consultas.link.length; i++) {
          var link = data.consultas.link[i]['@href'];
          segueLinkConsulta(link);
        }
      } else {
        var link = data.consultas.link['@href'];
        segueLinkConsulta(link);
      }
    },
    error: function() {
      alert('Erro ao carregar as consultas agendadas!');
    }
  });
}

function carregarConsultaParaEdicao(titulo) {
  $.ajax({
    url: hostConsultas + 'consultas/' + titulo,
    type: 'GET',
    success: function(data) {
      var consulta = data.consulta;
      $('#editId').val(consulta.id);
      $('#editTitulo').val(consulta.titulo);
      $('#editData').val(consulta.dataConsulta);
      $('#editObservacoes').val(consulta.observacoes);
      $('#editarModalNomeConsulta').html(consulta.titulo);
      console.log('deu boa!');
    },
    error: function(data) {
      alert('Erro ao carregar a consulta para edição.');
    }
  });
}

function salvarAtualizacaoConsulta() {
  var data = $('#editarConsultaForm').serializeJSON();
  data = "{\"consulta\":" + JSON.stringify(data) + "}";
  
  $.ajax({
    url: hostConsultas + 'consultas/',
    type: 'PUT',
    contentType: 'application/json',
    data: data,
    success: function(data) {
      $('#editarModal').modal('toggle');
      $('#editarConsultaForm')[0].reset();
      listConsultasPorPaciente();
    },
    error: function(data) {
      alert('Erro ao atualizar a consulta!');
    }
  });
}

function segueLinkConsulta(link) {
  $.ajax({
    url: hostConsultas + link,
    type: 'GET',
    success: function(data) {
      addConsultasNaGrid(data.consulta);
    },
    error: function(data) {
      console.log('Erro ao listar a consulta de ID ' + data.consulta.id);
    }
  });
}

function addConsultasNaGrid(consulta) {
  var data = '<tr>'
               + '<td>' + consulta.id + '</td>'
               + '<td>' + consulta.titulo + '</td>'
               + '<td>' + consulta.dataConsulta + '</td>'
               + '<td>' + 'Dr. Foo Bar' + '</td>'
               + "<td width='5%'><img src='../icons/pencil-icon.png' title='Atualizar consulta' width='20px' height='20px' style='cursor: pointer' data-toggle='modal' data-target='#editarModal' onClick='carregarConsultaParaEdicao(\""+consulta.titulo+"\")'></td>"
		       + "<td width='5%'><img src='../icons/trash-icon.png' title='Excluir consulta' width='20px' height='20px' style='cursor: pointer' onClick='cancelarConsulta(\""+consulta.titulo+"\")'></td>"
           + '</tr>';
  $('#consultasGrid').append(data);
}