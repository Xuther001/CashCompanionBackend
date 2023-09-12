package com.app.chat.image;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ImageController {

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile image) {
        // Save the image to a local directory
        // You can adjust the path as needed
        String directory = "path/to/your/local/directory/";
        String filename = image.getOriginalFilename();

        try {
            image.transferTo(new File(directory + filename));
            return "Image uploaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading image";
        }
    }
}
