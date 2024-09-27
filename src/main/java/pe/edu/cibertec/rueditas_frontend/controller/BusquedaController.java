package pe.edu.cibertec.rueditas_frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.rueditas_frontend.dto.BusquedaResponseDTO;
import pe.edu.cibertec.rueditas_frontend.viewmodel.BusquedaModel;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/matricula")
public class BusquedaController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/inicio")
    public String inicio(Model model){

        BusquedaModel busquedaModel = new BusquedaModel("00","","","","","","");
        model.addAttribute("busquedaModel", busquedaModel);
        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscarVehiculo(@RequestParam String nroMatricula, Model model){

        if (nroMatricula.matches("^[A-Za-z0-9]{3}-[A-Za-z0-9]{3}$")){

            try {
                String URL = "http://localhost:8081/autos/buscar?nroplaca=" + nroMatricula;
                Map<String, String> parametros = new HashMap<>();
                parametros.put("nroplaca", nroMatricula);
                BusquedaResponseDTO busquedaResponseDTO = restTemplate.postForObject(URL, null, BusquedaResponseDTO.class, parametros);

                if (busquedaResponseDTO.codigo().equals("00")){
                    BusquedaModel busquedaModel = new BusquedaModel("00","",busquedaResponseDTO.marca(),busquedaResponseDTO.modelo(),busquedaResponseDTO.nasientos(),busquedaResponseDTO.precio(),busquedaResponseDTO.color());
                    model.addAttribute("busquedaModel", busquedaModel);

                    return "busqueda";
                }

                BusquedaModel busquedaModel = new BusquedaModel("01","No se encontro vehiculo con la placa digitada", "", "", "", "", "");
                model.addAttribute("busquedaModel", busquedaModel);

                return "inicio";

            }catch (Exception e){

                BusquedaModel busquedaModel = new BusquedaModel("99", "Error : "+e.getMessage(), "", "", "", "", "");
                model.addAttribute("busquedaModel", busquedaModel);

                return "inicio";
            }

        }else{
            BusquedaModel busquedaModel = new BusquedaModel("01", "Debe digitar una placa valida", "", "", "", "", "");
            model.addAttribute("busquedaModel", busquedaModel);

            return "inicio";
        }

    }


}
