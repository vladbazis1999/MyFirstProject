package com.vlad.DAO.interfacies;

import com.vlad.Entity.CarEntity;

import java.util.List;

public interface CarDAOint
{
    List<CarEntity> allCars();

    CarEntity takeCar(long id);

    void save(CarEntity carEntity);
    void update(long id, CarEntity carEntity);
    void delete(long id);
}
