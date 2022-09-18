package com.example.warehouse.dlc;

import com.example.warehouse.game.Game;
import com.example.warehouse.game.GameRepository;
import com.example.warehouse.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DlcService {

    private final DlcRepository dlcRepository;

    @Autowired
    public DlcService(DlcRepository dlcRepository) {
        this.dlcRepository = dlcRepository;
    }

    public List<Dlc> getDlcs() throws MalformedURLException {
        return dlcRepository.findAll();
    }

    public void addNewDlc(Dlc dlc) {
        dlcRepository.save(dlc);
    }

    public void importDlcsFromCSV(MultipartFile file) {
        try {
            List<Dlc> tutorials = CSVHelper.csvToDlcs(file.getInputStream());
            dlcRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void deleteDlc(Long dlcId) {
        boolean exists = dlcRepository.existsById(dlcId);
        if(!exists){
            throw new IllegalStateException("Dlc with Id " + dlcId + " doesn't exist");
        }
        dlcRepository.deleteById(dlcId);
    }

    @Transactional
    public void updateDlc(Long dlcId, String name, String originalGame, String description, BigDecimal price) {
        Dlc dlc = dlcRepository.findById(dlcId)
                .orElseThrow(() -> new IllegalStateException("dlc with id " + dlcId + " does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(dlc.getName(), name)){
            Optional<Dlc> optionalDlc = dlcRepository.findDlcByName(name);
            if(optionalDlc.isPresent()){
                throw new IllegalStateException("data with name " + name + " already exists.");
            }
            dlc.setName(name);
        }

        if(originalGame != null && originalGame.length() > 0 && !Objects.equals(dlc.getOriginalGame(), originalGame)){
            dlc.setOriginalGame(originalGame);
        }

        if(description != null && description.length() > 0 && !Objects.equals(dlc.getDescription(), description)){
            dlc.setDescription(description);
        }

        if(price != null && price.compareTo(BigDecimal.ZERO) > 0){
            dlc.setPrice(price);
        }
    }

    public Dlc getDlc(Long dlcId) {
        Dlc dlc = dlcRepository.findById(dlcId)
                .orElseThrow(() -> new IllegalStateException("DLC with id " + dlcId + " does not exist"));
        return dlc;
    }
}
