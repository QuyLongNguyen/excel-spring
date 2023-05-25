package com.example.springworksheetdemo.resources;

import com.example.springworksheetdemo.dto.CandidateDTO;
import com.example.springworksheetdemo.dto.ImportResult;
import com.example.springworksheetdemo.service.CandidateService;
import com.example.springworksheetdemo.service.ImportSheetService;
import com.example.springworksheetdemo.sheet.CandidateSheetRowData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/candidates")
public class CandidateResources {

    @Autowired
    private ImportSheetService importSheetService;

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/import")
    public ResponseEntity<ImportResult<CandidateDTO>> importCandidates(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        ImportResult<CandidateDTO> importResult = importSheetService.importFile(multipartFile, CandidateSheetRowData.SHEET_NAME,  CandidateSheetRowData.class, CandidateDTO.class);
        candidateService.saveAll(importResult.getData());
        if(!importResult.getErrors().isEmpty()){
            return ResponseEntity.badRequest().body(importResult);
        }
        return ResponseEntity.ok(importResult);
    }


    @PostMapping("/all")
    public ResponseEntity saveCandidates(@RequestBody List<CandidateDTO> candidatesDTO){
        return candidateService.saveAll(candidatesDTO)  != null ? ResponseEntity.ok().build()
                                                                : ResponseEntity.internalServerError().build();
    }
}
