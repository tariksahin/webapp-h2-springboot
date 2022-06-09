package org.example.demo.coincap;

import org.example.demo.CoincapEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CoincapController {

    @Autowired
    private CoincapService coincapService;

    @GetMapping(value = "/latest-10-bitcoin-entry")
    @ResponseBody
    public List<CoincapEntry> get10LatestItems() {
        return coincapService.get10Latest();
    }
    @RequestMapping(value = "/delete-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        coincapService.deleteById(id);

        return new ResponseEntity<String>("Delete operation successfull for the id: "+id, HttpStatus.OK);
    }
}
