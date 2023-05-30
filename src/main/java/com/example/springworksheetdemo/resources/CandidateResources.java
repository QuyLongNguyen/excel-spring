package com.example.springworksheetdemo.resources;

import com.example.springworksheetdemo.dto.CandidateDTO;
import com.example.springworksheetdemo.dto.ImportResult;
import com.example.springworksheetdemo.service.CandidateService;
import com.example.springworksheetdemo.service.FileService;
import com.example.springworksheetdemo.service.SheetService;
import com.example.springworksheetdemo.sheet.CandidateSheetRowData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/candidates")
public class CandidateResources {

    @Autowired
    private SheetService sheetService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private FileService fileService;

    @PostMapping("/import")
    public ResponseEntity<ImportResult<CandidateDTO>> importCandidates(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        ImportResult<CandidateDTO> importResult = sheetService.importFile(multipartFile, CandidateSheetRowData.SHEET_NAME,  CandidateSheetRowData.class, CandidateDTO.class);
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

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> exportCandidates() throws IOException {
        InputStream inputStream;
        HttpHeaders httpHeaders;

        try {
            inputStream = fileService.getResourceFile("template.xlsx");
        }catch (IOException e){
            return ResponseEntity.notFound().build();
        }
        List<CandidateDTO> candidatesDto = candidateService.findAll();
        ByteArrayInputStream byteArrayInputStream = sheetService.exportFile(inputStream,
                                                                            CandidateSheetRowData.SHEET_NAME,
                                                                            CandidateSheetRowData.class,
                                                                            candidatesDto);
        httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=candidate.xlsx");
        return ResponseEntity.ok().headers(httpHeaders).body(new InputStreamResource(byteArrayInputStream));
    }


}
