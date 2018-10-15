package io.sweetfab.sweetide.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.sweetfab.sweetide.exceptions.project.ProjectNotFoundException;
import io.sweetfab.sweetide.exceptions.user.UserNotFoundException;
import io.sweetfab.sweetide.models.entities.ProjectEntity;
import io.sweetfab.sweetide.models.entities.UserEntity;
import io.sweetfab.sweetide.models.services.ProjectService;
import io.sweetfab.sweetide.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private static final int USER_NOT_FOUND = -2;
    private static final int EXPIRED_TOKEN = -3;
    private static final int IMPROPER_TOKEN = -4;

    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestHeader("Authorization") String authorization, @RequestBody ProjectEntity data) {
        Map<String, Object> res = new HashMap<>();
        String token = authorization.replace("Bearer ", "");
        try {
            UserEntity user = this.userService.getUserByToken(token);
            this.projectService.createProject(user, data.getName(), data.getProjectType(), data.getDescription(), data.getBoardType());
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (ExpiredJwtException e) {
            res.put("reason", EXPIRED_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (JwtException e) {
            res.put("reason", IMPROPER_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (UserNotFoundException e) {
            res.put("reason", USER_NOT_FOUND);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @RequestBody Map<String, Integer> data) {
        Map<String, Object> res = new HashMap<>();
        String token = authorization.replace("Bearer ", "");
        try {
            int id = data.get("pid");
            UserEntity user = this.userService.getUserByToken(token);
            ProjectEntity project = this.projectService.getProject(user, id);
            this.projectService.deleteProject(project);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            res.put("reason", EXPIRED_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (JwtException e) {
            res.put("reason", IMPROPER_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (UserNotFoundException e) {
            res.put("reason", USER_NOT_FOUND);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestHeader("Authorization") String authorization, @RequestBody ProjectEntity data) {
        Map<String, Object> res = new HashMap<>();
        String token = authorization.replace("Bearer ", "");
        try {
            UserEntity user = this.userService.getUserByToken(token);
            ProjectEntity project = this.projectService.getProject(user, data.getId());
            project.setDescription(data.getDescription());
            project.setName(data.getName());
            this.projectService.saveProject(project);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            res.put("reason", EXPIRED_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (JwtException e) {
            res.put("reason", IMPROPER_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (UserNotFoundException e) {
            res.put("reason", USER_NOT_FOUND);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(@RequestHeader("Authorization") String authorization) {
        Map<String, Object> res = new HashMap<>();
        String token = authorization.replace("Bearer ", "");
        try {
            UserEntity user = this.userService.getUserByToken(token);
            List<ProjectEntity> list = this.projectService.getProjectList(user);
            res.put("projectList", list);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            res.put("reason", EXPIRED_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (JwtException e) {
            res.put("reason", IMPROPER_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (UserNotFoundException e) {
            res.put("reason", USER_NOT_FOUND);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{pid}")
    public ResponseEntity<?> getProject(@RequestHeader("Authorization") String authorization, @PathVariable("pid") int pid) {
        Map<String, Object> res = new HashMap<>();
        String token = authorization.replace("Bearer ", "");
        try {
            UserEntity user = this.userService.getUserByToken(token);
            ProjectEntity project = this.projectService.getProject(user, pid);
            res.put("pname", project.getName());
            res.put("source", project.getSource());
            res.put("modules", project.getModules());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            res.put("reason", EXPIRED_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (JwtException e) {
            res.put("reason", IMPROPER_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (UserNotFoundException e) {
            res.put("reason", USER_NOT_FOUND);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{pid}/save")
    public ResponseEntity<?> save(@RequestHeader("Authorization") String authorization, @PathVariable("pid") int pid, @RequestBody ProjectEntity data) {
        Map<String, Object> res = new HashMap<>();
        String token = authorization.replace("Bearer ", "");
        try {
            UserEntity user = this.userService.getUserByToken(token);
            ProjectEntity project = this.projectService.getProject(user, pid);
            project.setSource(data.getSource());
            project.setModules(data.getModules());
            this.projectService.saveProject(project);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (ExpiredJwtException e) {
            res.put("reason", EXPIRED_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (JwtException e) {
            res.put("reason", IMPROPER_TOKEN);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (UserNotFoundException e) {
            res.put("reason", USER_NOT_FOUND);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        } catch (ProjectNotFoundException e) {
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
