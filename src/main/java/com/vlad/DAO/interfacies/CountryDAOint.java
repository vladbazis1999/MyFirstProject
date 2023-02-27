package com.vlad.DAO.interfacies;

import com.vlad.Entity.CountryEntity;
import com.vlad.Entity.JobEntity;
import com.vlad.Entity.PersonEntity;

import java.util.List;
import java.util.Set;

public interface CountryDAOint
{
        List<CountryEntity> allCountries();

        CountryEntity takeCountry(long id);

        List<PersonEntity> getAllPeopleInCountry(long id);
        Set<JobEntity> getAllJobInThisCountry(long id);
        void save(CountryEntity countryEntity);
        void update(long id, CountryEntity countryEntity);
        void delete(long id);
}
