var baseHost = 'http://localhost:8080/medsystem-rest/';
var hostConsultas = 'http://localhost:8080/medsystem-rest/services/';

function agendarConsultas() {
  var userLogado = JSON.parse(sessionStorage.getItem('usuarioLogado'));
  var data = $('#addNewSerie').serializeJSON();
  data = "{\"consulta\":" + JSON.stringify(data) + "}";
  
  $.ajax({
    url: hostConsultas + 'consultas/usuario/' + userLogado.id,
    type: 'POST',
    contentType: 'application/json',
    data: data,
    success: function(data) {
      $('#addNewSerie')[0].reset();
      alert('Consulta agendada com sucesso!');
    },
    error: function(data) {
      alert('Ocorreu um erro ao agendar a consulta!');
    }
  });
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
           + '</tr>';
  $('#consultasGrid').append(data);
}