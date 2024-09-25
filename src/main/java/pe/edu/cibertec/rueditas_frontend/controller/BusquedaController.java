package pe.edu.cibertec.rueditas_frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.rueditas_frontend.viewmodel.BusquedaModel;

@Controller
@RequestMapping("/matricula")
public class BusquedaController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/inicio")
    public String inicio(Model model){

        BusquedaModel busquedaModel = new BusquedaModel("00","","","","","","");
        model.addAttribute("busquedaModel", busquedaModel);
    return "busqueda";
    }

    @PostMapping("/buscar")
    public String buscarVehiculo(@RequestParam String matricula, Model model){

        BusquedaModel busquedaModel = new BusquedaModel("00","","","","","","");
        model.addAttribute("busquedaModel", busquedaModel);

        return "";
    }


}
