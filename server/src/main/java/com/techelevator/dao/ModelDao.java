package com.techelevator.dao;

import com.techelevator.model.Model;

import java.util.List;

public interface ModelDao {

    List<Model> getAllModels();


    Model getModelById(int id);


    Model createModel(Model model);

    Model updateModel(Model model);

    int deleteModelById(int id);
}
