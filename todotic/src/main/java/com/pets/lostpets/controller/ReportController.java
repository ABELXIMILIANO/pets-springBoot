package com.pets.lostpets.controller;

import com.pets.lostpets.entity.Report;
import com.pets.lostpets.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("")
    String index(Model model){
        List<Report> ultimosReports = reportRepository.OdenarReportes();
        model.addAttribute("ultimosReports", ultimosReports);
        return "index";
    }

    @GetMapping("reports")
    public String getReports(Model model,
                        @PageableDefault(size = 4,sort = "nombreMascota") Pageable pageable,
                        @RequestParam(required = false) String nombreMascota) {
        Page<Report> reports = reportRepository.findAll(pageable);

        model.addAttribute("reports", reports);
        return "list-reports";
    }

    @GetMapping("/repor/{id}")
    String getReport(@PathVariable Long id, Model model){
        Report report = reportRepository.getById(id);
        model.addAttribute("report", report);
        return "detalle-report";
    }

}
