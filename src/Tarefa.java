import java.time.LocalDate;

public class Tarefa {


    public static int proximoId = 1;
    private int id;
    private String name;
    private String description;
    private int prioridade;
    private String categoria;
    private int status;
    private LocalDate dataTarefa;
    private int horasAntes = 0;



    public Tarefa(String name, String description, int prioridade, String categoria, int status, LocalDate dataTarefa, int horasAntes) {
        this.id = proximoId++;
        this.name = name;
        this.categoria = categoria;
        this.description = description;
        this.prioridade = prioridade;
        this.status = status;
        this.dataTarefa = dataTarefa;
        this.horasAntes = horasAntes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getProximoId() {
        return proximoId;
    }

    public static void setProximoId(int proximoId) {
        Tarefa.proximoId = proximoId;
    }

    public LocalDate getDataTarefa() {
        return dataTarefa;
    }

    public void setDataTarefa(LocalDate dataTarefa) {
        this.dataTarefa = dataTarefa;
    }

    public void setHorasAntes(int horasAntes) {
        this.horasAntes = horasAntes;
    }

    public int getHorasAntes() {
        return horasAntes;
    }
}
