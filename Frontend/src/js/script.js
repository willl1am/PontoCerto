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
    $("#tabela-corpo").empty();
    const tablebody = $("#tabela-corpo");
    resultado.forEach(async function (linha, i) {
      const horario = await pegaHorario(resultado[i].cl);
      console.log("Horário da linha", resultado[i].cl, ":", horario);
      const dadoTabela = `
      <tr>
        <td>${linha.cl}</td>
        <td>${linha.lc}</td>
        <td>${linha.lt}</td>
        <td>${linha.sl}</td>
        <td>${linha.tl}</td>
        <td>${linha.tp}</td>
        <td>${linha.ts}</td>
        <td>${horario.hr}</td>
      </tr>
      `;
      tablebody.append(dadoTabela);
    });

    console.log("Linha encontrada:", resultado);

    console.log("Resultados obtidos:", {
      linha: resultado[3],
      horario: resultadoHorario,
    });
    return resultadoHorario;
  } catch (error) {
    if (error.status === 401) {
      console.error(
        "ERRO 401: Falha na autenticação. Verifique seu token de API.",
        error
      );
    } else {
      console.error("ERRO na requisição AJAX:", error);
    }
  }
}

async function pegaHorario(codigoLinha) {
  const URL_HORARIO = `${PROXY_BASE}/Previsao/Linha?codigoLinha=${codigoLinha}`;
  return await $.ajax({
    url: URL_HORARIO,
    method: "GET",
  });
}

form.addEventListener("submit", function (event) {
  event.preventDefault();
  submit(inputLinha.value);
});
