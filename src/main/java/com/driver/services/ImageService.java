package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(description,dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
       imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String[] screen = screenDimensions.split("X");
        int screenArea = Integer.parseInt(screen[0])*Integer.parseInt(screen[1]);
        String[] imageDimensions = image.getDimensions().split("X");
        int imageArea = Integer.parseInt(imageDimensions[0])*Integer.parseInt(imageDimensions[1]);
        return screenArea/imageArea;
    }
}
