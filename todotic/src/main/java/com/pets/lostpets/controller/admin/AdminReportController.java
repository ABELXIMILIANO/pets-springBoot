package com.pets.lostpets.controller.admin;

import com.pets.lostpets.entity.Report;
import com.pets.lostpets.repository.ReportRepository;
import com.pets.lostpets.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Controller
@RequestMapping("/admin/reportes")
public class AdminReportController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @GetMapping("")
    public String index(Model model,
                        @PageableDefault(size = 4,sort = "nombreMascota") Pageable pageable,
                        @RequestParam(required = false) String nombreMascota) {
        Page<Report> reports;
        if(nombreMascota != null && !nombreMascota.trim().isEmpty()){
            reports = reportRepository.findByNombreMascotaContaining(nombreMascota, pageable);
        }else{
            reports = reportRepository.findAll(pageable);
        }
        model.addAttribute("reports", reports);
        return "admin/listReport";
    }

    @GetMapping("/newReport")
    String newReport(Model model){
        model.addAttribute("report" , new Report());
        return "admin/newReport";
    }

    @PostMapping("/newReport")
    String createNewReport(@Validated Report report, BindingResult bindingResult, Model model, RedirectAttributes ra){

        if(report.getImagen().isEmpty()){
            bindingResult.rejectValue("imagen","MultipartNotEmpty");
        }

        if(bindingResult.hasErrors() || report.getImagen().isEmpty()) {
            model.addAttribute("report" , report);
            return "admin/newReport";
        }

        String rutaImagen = fileSystemStorageService.store(report.getImagen());
        report.setRutaImagen(rutaImagen);

        report.setEstado(true); //Se inicializa reporte en 1 Activo

        reportRepository.save(report);

        ra.addFlashAttribute("msgExito", "Reporte Creado Correctamente");

        return "redirect:/admin/reportes";
    }

    @GetMapping("/editReport/{id}")
    String editReport(@PathVariable Long id, Model model){
        Report report = reportRepository
                .getById(id);
        model.addAttribute("report" , report);
        return "admin/editReport";
    }

    @PostMapping("/editReport/{id}")
    String upgradeReport(@PathVariable Long id,Report report, Model model, RedirectAttributes ra){
        Report reportDB = reportRepository
                .getById(id);

        if(!report.getImagen().isEmpty()) {
            String rutaImagen = fileSystemStorageService.store(report.getImagen());
            reportDB.setRutaImagen(rutaImagen);
        }

        reportDB.setNombreMascota(report.getNombreMascota());
        reportDB.setEdad(report.getEdad());
        reportRepository.save(reportDB);
        ra.addFlashAttribute("msgExito", "Reporte Actualizado");

        return "redirect:/admin/reportes";
    }

    @PostMapping("/deleteReport/{id}")
    String deleteReport(@PathVariable Long id, RedirectAttributes ra){
        Report reportDB = reportRepository
                .getById(id);
        reportRepository.delete(reportDB);
        ra.addFlashAttribute("msgExito", "Reporte Eliminado");
        return "redirect:/admin/reportes";
    }

}
