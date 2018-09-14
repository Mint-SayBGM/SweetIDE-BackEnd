package io.sweetfab.sweetideapi.controllers;

import io.sweetfab.sweetideapi.models.entities.ProjectEntity;
import io.sweetfab.sweetideapi.models.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProjectEntity project){
        if (!project.isValid()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        projectRepository.save(project);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Map<String, Integer> req) {
        try {
            int type = req.get("type");
            int pid = req.get("pid");
            ProjectEntity found = projectRepository.findById(pid).orElse(null);
            if (found == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            projectRepository.deleteProjectEntityByIdAndType(pid, type);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Map<String, Object> req) {
        int type = (int) req.get("type");
        int pid = (int) req.get("pid");
        String pname = (String) req.get("pname");
        String description = (String) req.get("description");
        ProjectEntity project = projectRepository.getOne(pid);

    }

}
