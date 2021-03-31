package it.iad2.scarsefourserver.controller;


import it.iad2.scarsefourserver.service.DashboardQuattroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class DashboardQuattroController {
    
    @Autowired
    DashboardQuattroService dashboardquattro;
    
    
  
}
