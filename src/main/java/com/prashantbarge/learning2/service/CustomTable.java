package com.prashantbarge.learning2.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomTable {

    @PersistenceContext
    private final EntityManager entityManager;

    public CustomTable(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveDataToDB(List<String> columnNames,List<List<String>> columnValues,String filename){
        String tableName = removeExtension(filename);
       createTable(columnNames,tableName);
        insertData(columnNames,columnValues,tableName);

    }

    private String removeExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return filename.substring(0, lastDotIndex);
        } else {
            return filename; // No extension found
        }
    }


    @Transactional
    public void createTable(List<String> columnNames,String tableName) {
        StringBuilder createTableQuery = new StringBuilder("CREATE TABLE ");
        createTableQuery.append(tableName).append(" (");
        for (String columnName : columnNames) {
            createTableQuery.append(columnName).append(" VARCHAR(255), ");
        }
        createTableQuery.delete(createTableQuery.length() - 2, createTableQuery.length()); // Remove the last comma and space
        createTableQuery.append(")");
        // Execute create table query
        Query createTable = entityManager.createNativeQuery(createTableQuery.toString());
        createTable.executeUpdate();
    }

    public void insertData(List<String> columnNames,List<List<String>> columnValues , String tableName ){
        // Insert data query
        StringBuilder insertDataQuery = new StringBuilder("INSERT INTO ");
        insertDataQuery.append(tableName).append(" (");

        for (String columnName : columnNames) {
            insertDataQuery.append(columnName).append(", ");
        }
        insertDataQuery.delete(insertDataQuery.length() - 2, insertDataQuery.length()); // Remove the last comma and space
        insertDataQuery.append(") VALUES (");
        for (int i = 0; i < columnNames.size(); i++) {
            insertDataQuery.append("?, ");
        }
        insertDataQuery.delete(insertDataQuery.length() - 2, insertDataQuery.length()); // Remove the last comma and space
        insertDataQuery.append(")");
        // Execute insert data query for each row
        for (List<String> row : columnValues) {
            Query insertData = entityManager.createNativeQuery(insertDataQuery.toString());
            for (int i = 0; i < row.size(); i++) {
                insertData.setParameter(i + 1, row.get(i));
            }
            insertData.executeUpdate();
        }
    }


}
