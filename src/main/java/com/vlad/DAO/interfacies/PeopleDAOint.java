package com.vlad.DAO.interfacies;


import com.vlad.Entity.PersonEntity;

import java.util.List;

public interface PeopleDAOint
{
        List<PersonEntity> allPeople();

        PersonEntity takePerson(long id);

        void save(PersonEntity personEntity);
        void update(long id, PersonEntity personEntity);
        void delete(long id);
}
