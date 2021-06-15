package kr.ac.hansung.cst.recycleback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping(path = "/weRecycle/camera")
public class CameraController {

    @RequestMapping(path = "/ai", method = RequestMethod.POST)
    public ResponseEntity<?> retrieveImage(@RequestBody String img64encoded) throws IOException {
        if (img64encoded == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        URL url = new URL("http://localhost:5000/api/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String jsonInputString = "{img:" + img64encoded + "}";

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
//        if(facilities.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("totalDNum", Integer.toString(f_totalNum));
//
        //return new ResponseEntity<List<Facility>>(facilities, httpHeaders, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

