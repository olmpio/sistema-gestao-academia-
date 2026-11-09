package br.com.academia.service;

import br.com.academia.model.Aluno;
import br.com.academia.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaEstiverCadastrado() {
        Aluno alunoExistente = new Aluno();
        alunoExistente.setId("1");
        alunoExistente.setEmail("teste@email.com");

        Aluno novoAluno = new Aluno();
        novoAluno.setId("2");
        novoAluno.setEmail("teste@email.com");

        when(alunoRepository.findByEmail("teste@email.com")).thenReturn(Optional.of(alunoExistente));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            alunoService.salvar(novoAluno);
        });

        assertEquals("Já existe um aluno cadastrado com este e-mail.", exception.getMessage());
        verify(alunoRepository, times(1)).findByEmail("teste@email.com");
        verify(alunoRepository, never()).save(any());
    }
}
