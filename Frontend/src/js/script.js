const API_KEY = "d65d0944d0486342616133bbeeebee3054cecc049be2a4a096361f2768714542";
// const URL_BASE = 'http://api.olhovivo.sptrans.com.br/v2.1/Login/Autenticar?token=d65d0944d0486342616133bbeeebee3054cecc049be2a4a096361f2768714542';
const URL_BASE = 'http://api.olhovivo.sptrans.com.br/v2.1';

// const response = $.ajax({
//     type: 'POST',
//     url: `${URL_BASE}/Login/Autenticar?token=${API_KEY}`,
//     contentType: 'application/json',
// });

function autenticar() {
    return $.ajax({
        type: 'POST',
        url: `${URL_BASE}/Login/Autenticar?token=${API_KEY}`,
        contentType: 'application/json',
        xhrFields: {
            withCredentials: true // MUITO IMPORTANTE: Garante que o cookie seja enviado/recebido
        }
    })
    .done(function(data) {
        if (data === true) {
            console.log("✅ Autenticação bem-sucedida! Cookie de sessão definido.");
            // O cookie é definido automaticamente no navegador nesta etapa.
            // Agora podemos chamar a função de busca.
            buscarLinha('3006-10'); 
        } else {
            console.error("❌ Erro na autenticação. Retorno: ", data);
        }
    })
    .fail(function(jqXHR, textStatus, errorThrown) {
        console.error("❌ Falha na requisição de autenticação:", textStatus, errorThrown);
    });
}

$("#teste").on("click", function() {
  console.log("Clicou no botão de teste");
  autenticar();
});