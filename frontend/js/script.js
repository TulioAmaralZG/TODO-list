document.addEventListener('DOMContentLoaded', function (){
    const form = document.getElementById('tarefaForm');

    carregarTarefas();

    form.addEventListener('submit', function (e){
        e.preventDefault();

        const tarefa = {
            id: Date.now(),
            nome: document.getElementById('nome').value,
            descricao: document.getElementById('descricao').value,
            dataTermino: document.getElementById('dataTermino').value,
            prioridade: document.getElementById('prioridade').value,
            categoria: document.getElementById('categoria').value,
            status: document.getElementById('status').value
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

       const statusModificar = document.createElement('select');

       statusModificar.className = 'status-select';
       statusModificar.innerHTML = `
            <option value="todo" ${tarefa.status === 'todo' ? 'selected' : ''}>Todo</option> 
            <option value="todo" ${tarefa.status === 'doing' ? 'selected' : ''}>Doing</option> 
            <option value="todo" ${tarefa.status === 'done' ? 'selected' : ''}>Done</option> 
       `;

       statusModificar.addEventListener('change', function (){
          const tarefas = JSON.parse(localStorage.getItem('tarefas') || '[]');
          const tarefaAtualizada = tarefas.find(t => t.id === tarefa.id);

          if(tarefaAtualizada){
              tarefaAtualizada.status = this.value;

              localStorage.setItem('tarefas', JSON.stringify(tarefas));

          }

       });



       li.innerHTML = `<div>
            <span>Nome: ${tarefa.nome}</span><br>
            <span>Data termino: ${formatarData(tarefa.dataTermino)}</span><br>
            <span>Prioridade: ${tarefa.prioridade}</span><br>
            <span>Categoria: ${tarefa.categoria}</span><br>
            <span>Status: </span><br>
            <span>Descrição: ${tarefa.descricao}</span><br>
            </div>
            <div>
            <button onClick="excluirTarefa(${tarefa.id})">Excluir</button><br>
            </div>`;

       //Inserir o select no status
        const statusSpan = Array.from(li.getElementsByTagName('span'))
            .find(span => span.textContent.includes('Status: '));
        statusSpan.parentNode.insertBefore(statusModificar, statusSpan.nextSibling);
        statusSpan.parentNode.removeChild(statusSpan);

       listaTarefas.appendChild(li);
    });
}

function formatarData(data){
    if(!data) return '';
    const [ano, mes, dia] = data.split('-')
    return `${dia}/${mes}/${ano}`;
}

function excluirTarefa(id){
    let tarefas = JSON.parse(localStorage.getItem('tarefas') || '[]');
    tarefas = tarefas.filter(tarefa => tarefa.id !== id)

    localStorage.setItem('tarefas', JSON.stringify(tarefas));
    carregarTarefas();
}
