package br.com.academia.model;

import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

public class ItemTreino {
    @DocumentReference
    @Field("exercicio")
    private Exercicio exercicio;

    @Field("series")
    private Integer series;

    @Field("repeticoes")
    private Integer repeticoes;

    @Field("tempo_descanso_segundos")
    private Integer tempoDescansoSegundos;

    public ItemTreino() {}

    public ItemTreino(Exercicio exercicio, Integer series, Integer repeticoes, Integer tempoDescansoSegundos) {
        this.exercicio = exercicio;
        this.series = series;
        this.repeticoes = repeticoes;
        this.tempoDescansoSegundos = tempoDescansoSegundos;
    }

    public Exercicio getExercicio() { return exercicio; }
    public void setExercicio(Exercicio exercicio) { this.exercicio = exercicio; }
    public Integer getSeries() { return series; }
    public void setSeries(Integer series) { this.series = series; }
    public Integer getRepeticoes() { return repeticoes; }
    public void setRepeticoes(Integer repeticoes) { this.repeticoes = repeticoes; }
    public Integer getTempoDescansoSegundos() { return tempoDescansoSegundos; }
    public void setTempoDescansoSegundos(Integer tempoDescansoSegundos) { this.tempoDescansoSegundos = tempoDescansoSegundos; }
}
