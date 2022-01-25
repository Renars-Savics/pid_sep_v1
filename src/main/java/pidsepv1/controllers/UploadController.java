package pidsepv1.controllers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import pidsepv1.domains.Table;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UploadController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `Table` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Table.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of table content
                List tables = csvToBean.parse();
                //tables.sort(Comparator.comparing(Table::getPid));
                tables = new ArrayList<>(new LinkedHashSet<Table>(tables));

                List<Table> tables2 = (List<Table>) tables.stream()
                        .sorted(Comparator.comparing(Table::getLayer))
                        .collect(Collectors.toList());

                // save table list on model
                model.addAttribute("tables2", tables2);
                model.addAttribute("status", true);


            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";

    }
}
