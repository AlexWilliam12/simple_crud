package br.senac.tads.dswa.exemplos;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public List<Carro> listar() {
        return repository.findAll();
    }

    public Carro procurar(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado"));
    }

    public void adicionar(CarroDto dto) {
        Carro carro = new Carro();
        BeanUtils.copyProperties(dto, carro);
        repository
                .findOne(Example.of(carro))
                .ifPresent(c -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro já registrado");
                });
        repository.save(carro);
    }

    public void atualizar(Long id, CarroDto dto) {
        repository.findById(id)
                .ifPresentOrElse(carro -> {
                    BeanUtils.copyProperties(dto, carro);
                    carro.setId(id);
                    repository.save(carro);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado!");
                });
    }

    public void excluir(Long id) {
        repository.findById(id)
                .ifPresentOrElse(c -> repository.deleteById(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado!");
                        });
    }
}