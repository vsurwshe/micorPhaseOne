package org.profile.controller;

import java.util.List;

import org.domain.model.ProfileTypeModel;
import org.profile.service.ErrorServiceMessage;
import org.profile.service.LogService;
import org.profile.service.ResponseEntityResult;
import org.repository.repo.ProfileTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/profile")
public class ProfileTypeController {

    @Autowired
    private ProfileTypeRepository profileTypeRepo;

    @GetMapping(value="/types")
    public ResponseEntity<?> getProfileTypes() {
        return this.findAllProfiles();
    }

    //------------ Custom Method Declartions

    private ResponseEntity<?> findAllProfiles() {
        List<ProfileTypeModel> profileType=null;
        try {
            profileType=  profileTypeRepo.findAll();
            if(profileType.isEmpty()){
                return ResponseEntityResult.badRequest(ErrorServiceMessage.NO_REC_PROFILETYPE);
            }         
        } catch (Exception e) {
            LogService.setLogger(e.getMessage());
            return ResponseEntityResult.badRequest(e.getMessage());
        }
        return ResponseEntityResult.successResponseEntity(profileType);
    }
}