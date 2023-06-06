package pe.edu.upc.connection2connection.controllers;

import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.connection2connection.dtos.ReclutadorDTO;
import pe.edu.upc.connection2connection.entities.Empresa;
import pe.edu.upc.connection2connection.entities.Reclutador;
import pe.edu.upc.connection2connection.services.IReclutadorService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reclutadores")
public class ReclutadorController {

    @Autowired
    private IReclutadorService rS;

    @PostMapping
    public void registrar(@RequestBody ReclutadorDTO dto) {
        ModelMapper m = new ModelMapper();
        Reclutador r = m.map(dto, Reclutador.class);
        rS.insertar(r);
    }


    @GetMapping
    public List<ReclutadorDTO> listar() {
        return rS.listar().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,ReclutadorDTO.class);

        }).collect(Collectors.toList());
    }


    @PostMapping("/buscarEmpresa")
    @ApiOperation(value = "Buscar reclutadores por empresa")
    public List<ReclutadorDTO> buscar(@RequestBody Empresa empresa) {
        return rS.buscarEmpresa(empresa).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReclutadorDTO.class);
        }).collect(Collectors.toList());
    }

}