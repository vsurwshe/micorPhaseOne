package org.profile.controller;

import java.util.List;

import org.domain.entity.ProfileTypeModel;
import org.repository.profile.ProfileTypeRepository;
import org.service.apiService.ErrorServiceMessage;
import org.service.apiService.LogService;
import org.service.apiService.ResponseEntityResult;
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

    //------------ Custom Method Declarations

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