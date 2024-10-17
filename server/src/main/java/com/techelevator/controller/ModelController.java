package com.techelevator.controller;

import com.techelevator.dao.ModelDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Device;
import com.techelevator.model.Model;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173")
@RequestMapping("/models")
public class ModelController {

    private final ModelDao modelDao;

    public ModelController(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    @GetMapping
    public List<Model> getAllModels() {
        return modelDao.getAllModels();
    }
    @GetMapping(path = "/{id}")
    public Model getModelById(@PathVariable int id) {
        return modelDao.getModelById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Model createModel(@Valid @RequestBody Model model) {
        return modelDao.createModel(model);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(path = "/{id}")
    public Model updateModel(@Valid @RequestBody Model model, @PathVariable int id) {

        model.setId(id);
        try {
            return modelDao.updateModel(model);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Model Not Found");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteModel(@PathVariable int id) {
        modelDao.deleteModelById(id);
    }
}
