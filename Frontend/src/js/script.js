const form = document.querySelector("#busca-linha");
const inputLinha = document.querySelector("#input-linha");
const PROXY_BASE = `http://localhost:8080/proxy`;

async function submit(id) {
  try {
    const URL_BUSCA = `${PROXY_BASE}/Linha/Buscar?termosBusca=${id}`;
    const resultado = await $.ajax({
      url: URL_BUSCA,
      method: "GET",
    });

    if (!resultado || resultado.length === 0) {
      console.warn("Nenhuma linha encontrada para o ID:", id);
      return;
    }
    const codigoLinha = resultado[3].cl; 
    const URL_HORARIO = `${PROXY_BASE}/Previsao/Linha?codigoLinha=${codigoLinha}`;

    const resultadoHorario = await $.ajax({
      url: URL_HORARIO,
      method: "GET",
    });

    console.log("Resultados obtidos:", { linha: resultado[3], horario: resultadoHorario });
    return resultadoHorario;

  } catch (error) {
    if (error.status === 401) {
      console.error("ERRO 401: Falha na autenticação. Verifique seu token de API.", error);
    } else {
      console.error("ERRO na requisição AJAX:", error);
    }
  }
}

form.addEventListener("submit", function (event) {
  event.preventDefault();
  submit(inputLinha.value);
});