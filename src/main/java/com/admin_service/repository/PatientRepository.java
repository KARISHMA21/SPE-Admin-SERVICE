package com.admin_service.repository;

import com.admin_service.bean.entity.EntityRes;
import com.admin_service.bean.entity.PatientEntity;

import com.admin_service.bean.model.MinorAccounts;
import com.admin_service.bean.response.PatientDemographicsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PatientRepository extends CrudRepository<PatientEntity,String> {

//    @Query("select  t from PatientEntity  t  where t.pid =:pid")
//    @Query("select t.pid,t.name,t.adhaar,t.dob,t.age,t.gender,t.phone,t.address,t.email,t.minor,t.disabled,t.iscapable from PatientEntity t  where t.pid =:pid")
//    public PatientDemographicsResponse getPatientDemographicsById(@Param("pid") String pid);
    @Query("select  t from PatientEntity  t  where t.pid =:pid")
    public PatientEntity getPatientDemographicsById(@Param("pid") String pid);
    @Query("select  t from PatientEntity  t  where t.guardian.pid =:pid")
    public List<PatientEntity> getMinors(@Param("pid") String pid);


    Optional<PatientEntity> findByUniqueid(String uniqueid);

    @Modifying
    @Query("UPDATE PatientEntity cl SET " +
            "cl.name=:Name ,cl.gender=:Gender ,cl.phone=:Phone ,cl.email=:Email ,cl.address=:Address " +
            "WHERE " +
            "cl.pid=:pid")
    public void updateProfile(
            @Param("pid") String pid,
            @Param("Name") String Name,
            @Param("Gender") String Gender,
            @Param("Phone") BigInteger Phone,
            @Param("Email") String Email,
            @Param("Address") String Address
        );

}


