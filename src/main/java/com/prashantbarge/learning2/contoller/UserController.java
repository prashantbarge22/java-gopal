package com.prashantbarge.learning2.contoller;

import com.prashantbarge.learning2.models.User;
import com.prashantbarge.learning2.service.CustomTable;
import com.prashantbarge.learning2.service.UserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CustomTable customTable;

    @Autowired
    public UserController(UserService userService, CustomTable customTable) {
        this.userService = userService;
        this.customTable = customTable;
    }

    @PostMapping("/sign-up")
    public User createUser(User userToSave){
      return userService.save(userToSave);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String  filename = file.getOriginalFilename();
        List<String> columnNames = new ArrayList<>();
        List<List<String>> columnValues = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // Initialize a CSVParser with the specified delimiter (; in this case) and treat the first record as the header
            CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().parse(reader);

            // Extract column names
            columnNames.addAll(csvParser.getHeaderNames());

            // Extract column values
            for (CSVRecord csvRecord : csvParser) {
                List<String> rowValues = new ArrayList<>();
                for (int i = 0; i < csvRecord.size(); i++) {
                    rowValues.add(csvRecord.get(i)); // Add each field to the rowValues
                }
                columnValues.add(rowValues); // Add the rowValues to the columnValues
            }
            customTable.saveDataToDB(columnNames,columnValues,filename);
            ResponseEntity.ok("File accepted");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
        return null;
    }

}
