package com.vlad.DAO.interfacies;

import com.vlad.Entity.CountryEntity;
import com.vlad.Entity.JobEntity;
import com.vlad.Entity.PersonEntity;

import java.util.List;
import java.util.Set;

public interface JobDAOint
{
    List<JobEntity> allJobs();

    JobEntity takeJob(long id);
    List<PersonEntity> getAllPeopleInJob(long id);
    public Set<CountryEntity> getAllCountiesWithThisJob(long id);
    void save(JobEntity jobEntity);
    void update(long id, JobEntity jobEntity);
    void delete(long id);
}
