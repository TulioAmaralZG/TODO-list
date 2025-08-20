document.addEventListener('DOMContentLoaded', function (){
    const form = document.getElementById('tarefaForm');

    carregarTarefas();

    form.addEventListener('submit', function (e){
        e.preventDefault();

        const dataHoje = new Date();


            const nome = document.getElementById('nome').value;
            const descricao = document.getElementById('descricao').value;
            const dataTermino =  document.getElementById('dataTermino').value;
            const prioridade = document.getElementById('prioridade').value;
            const categoria = document.getElementById('categoria').value;
            const status = document.getElementById('status').value;

        const dataTeste = new Date(dataTermino);

        if(!nome){
            document.getElementById('nome').focus();
            return;
        }
        if(!descricao){
            document.getElementById('descricao').focus();
            return;
        }
        if(dataTeste < dataHoje){
            document.getElementById('dataTermino').focus();
            return;
        }
        if(!prioridade || prioridade < 1 || prioridade > 5){
            document.getElementById('prioridade').focus();
            return;
        }
        if(!categoria){
            document.getElementById('categoria').focus();
            return;
        }


        const tarefa = {
            id: Date.now(),
            nome: nome,
            descricao: descricao,
            dataTermino: dataTermino,
            prioridade: prioridade,
            categoria: categoria,
            status: status
        };

        salvarTarefa(tarefa);

        form.reset();

        carregarTarefas();
    });
});

function salvarTarefa(tarefa){
    let tarefas = JSON.parse(localStorage.getItem('tarefas') || '[]');

    tarefas.push(tarefa)

    localStorage.setItem('tarefas', JSON.stringify(tarefas))
}

function carregarTarefas(){
    const listaTarefas = document.querySelector('.lista-tarefas ul');
    const tarefas = JSON.parse(localStorage.getItem('tarefas') || '[]');

    listaTarefas.innerHTML = '';

    tarefas.forEach(tarefa => {
        const li = document.createElement('li');

        // Cria o elemento para o nome
        const nomeSpan = document.createElement('span');
        nomeSpan.textContent = tarefa.nome;
        nomeSpan.className = 'editable';
        nomeSpan.title = 'Clique para editar';
        nomeSpan.style.cursor = 'pointer';

        // Adiciona o evento de clique para editar
        nomeSpan.addEventListener('click', function() {
            const novoNome = prompt('Editar nome:', tarefa.nome);
            if (novoNome !== null && novoNome.trim() !== '') {
                atualizarTarefa(tarefa.id, 'nome', novoNome.trim());
            }
        });

        li.innerHTML = `
            <div>
                <span>Nome: </span>
                
                <br>
                <span>Data termino: ${formatarData(tarefa.dataTermino)}</span><br>
                <span>Prioridade: ${tarefa.prioridade}</span><br>
                <span>Categoria: ${tarefa.categoria}</span><br>
                <span>Status: </span><br>
                <span>Descrição: ${tarefa.descricao}</span><br>
            </div>
            <div>
                <button onClick="excluirTarefa(${tarefa.id})">Excluir</button><br>
            </div>`;

        // Insere o span do nome editável no lugar do placeholder
        const nomePlaceholder = li.querySelector('span:first-child');
        nomePlaceholder.parentNode.insertBefore(nomeSpan, nomePlaceholder.nextSibling);

        const statusModificar = document.createElement('select');
        statusModificar.className = 'status-select';
        statusModificar.innerHTML = `
            <option value="todo" ${tarefa.status === 'todo' ? 'selected' : ''}>Todo</option> 
            <option value="doing" ${tarefa.status === 'doing' ? 'selected' : ''}>Doing</option> 
            <option value="done" ${tarefa.status === 'done' ? 'selected' : ''}>Done</option> 
       `;

        statusModificar.addEventListener('change', function() {
            atualizarTarefa(tarefa.id, 'status', this.value);
        });

        const statusSpan = Array.from(li.getElementsByTagName('span'))
            .find(span => span.textContent.includes('Status: '));
        if (statusSpan) {
            statusSpan.parentNode.insertBefore(statusModificar, statusSpan.nextSibling);
            statusSpan.parentNode.removeChild(statusSpan);
        }

        listaTarefas.appendChild(li);
    });
}

function formatarData(data){
    if(!data) return '';
    const [ano, mes, dia] = data.split('-')
    return `${dia}/${mes}/${ano}`;
}

//Excluir a tarefa, acessa o JSON e filtra pelo o id
function excluirTarefa(id){
    let tarefas = JSON.parse(localStorage.getItem('tarefas') || '[]');
    tarefas = tarefas.filter(tarefa => tarefa.id !== id)

    localStorage.setItem('tarefas', JSON.stringify(tarefas));
    carregarTarefas();
}

function atualizarTarefa(id, campo, valor) {
    // Obtém todas as tarefas do localStorage
    let tarefas = JSON.parse(localStorage.getItem('tarefas') || '[]');

    // Encontra o índice da tarefa com o ID especificado
    const index = tarefas.findIndex(tarefa => tarefa.id === id);

    // Se a tarefa for encontrada, atualiza o campo especificado
    if (index !== -1) {
        // Atualiza apenas o campo específico
        tarefas[index][campo] = valor;

        // Salva as alterações no localStorage
        localStorage.setItem('tarefas', JSON.stringify(tarefas));

        // Recarrega a lista de tarefas para refletir as alterações
        carregarTarefas();
        return true;
    }
    return false;
}
