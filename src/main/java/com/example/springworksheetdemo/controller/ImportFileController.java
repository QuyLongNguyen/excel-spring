package com.example.springworksheetdemo.controller;

import com.example.springworksheetdemo.dto.ImportSheetResult;
import com.example.springworksheetdemo.service.ImportSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("file")
public class ImportFileController {

    @Autowired
    ImportSheetService importSheetService;

    @GetMapping
    public String showImportPage() {
        return "file";
    }

    @PostMapping
    public String importFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            ImportSheetResult importSheetResult = importSheetService.uploadFile(file);
            redirectAttributes.addFlashAttribute("message", importSheetResult.getMessage());
            redirectAttributes.addFlashAttribute("importSheetErrors", importSheetResult.getImportSheetErrors());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "redirect:file";
    }
}
