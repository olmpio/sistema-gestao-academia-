package br.com.academia.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "pagamentos")
public class Pagamento {
    @Id
    private String id;

    @DocumentReference
    @Field("contrato")
    private Contrato contrato;

    @Field("valor")
    private BigDecimal valor;

    @Field("status")
    private String status;

    @Field("data_pagamento")
    private LocalDate dataPagamento;

    public Pagamento() {}

    public Pagamento(String id, Contrato contrato, BigDecimal valor, String status, LocalDate dataPagamento) {
        this.id = id;
        this.contrato = contrato;
        this.valor = valor;
        this.status = status;
        this.dataPagamento = dataPagamento;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
