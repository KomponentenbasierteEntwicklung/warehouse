package com.example.warehouse.dlc;

import com.example.warehouse.game.Game;
import com.example.warehouse.game.GameService;
import com.example.warehouse.helper.CSVHelper;
import com.example.warehouse.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/dlcs")
public class DlcController {

    private final DlcService dlcService;

    @Autowired
    public DlcController(DlcService dlcService) {
        this.dlcService = dlcService;
    }

    @GetMapping
    public List<Dlc> getDlcs() throws MalformedURLException {
        return dlcService.getDlcs();
    }

    @GetMapping(path = "{dlcId}")
    public Dlc getDlc(@PathVariable("dlcId") Long dlcId) {
        return dlcService.getDlc(dlcId);
    }

    @PostMapping
    public void registerNewDlc(@Valid @RequestBody Dlc dlc){
        dlcService.addNewDlc(dlc);
    }

    @PostMapping("/import")
    public ResponseEntity<ResponseMessage> importFromCSV(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                dlcService.importDlcsFromCSV(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                System.out.println(e);
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @DeleteMapping(path = "{dlcId}")
    public void deleteDlc(@PathVariable("dlcId") Long dlcId){
        dlcService.deleteDlc(dlcId);
    }

    @PutMapping(path = "{dlcId}")
    public void updateDlc(@PathVariable("dlcId") Long dlcId, @RequestParam(required = false) String name,
                           @RequestParam(required = false) String originalGame, @RequestParam(required = false) String description,
                           @RequestParam(required = false) BigDecimal price){
        dlcService.updateDlc(dlcId, name, originalGame, description, price);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
