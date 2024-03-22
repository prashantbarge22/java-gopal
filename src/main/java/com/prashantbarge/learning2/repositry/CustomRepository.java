package com.prashantbarge.learning2.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface CustomRepository {
//
//    @Transactional
//    @Modifying
//    @Query(value = "CREATE TABLE IF NOT EXISTS :tableName (:columDefinition)", nativeQuery = true)
//    void createTable(String tableName,String columDefinition);
//
//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO :tableName (column1, column2) VALUES (:value1, :value2)", nativeQuery = true)
//    void insertData(String tableName, String value1, String value2);
}
