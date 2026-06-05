package com.example.BloggingProject.controller;



import com.example.BloggingProject.model.Post;
import com.example.BloggingProject.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @Autowired
    PostRepo repo;

    @GetMapping("/")
    public String viewHomePage(Model model){
        // Change "post" to "listPosts"
        model.addAttribute("listPosts", repo.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String newPost(Model model){
        model.addAttribute("post",new Post());
        return "newPost";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post){
        repo.save(post);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable int id, Model model) {
        // We add .orElseThrow() to safely get the Post object or handle an error
        Post existingPost = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));

        model.addAttribute("post", existingPost);
        return "edit_post";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable int id){
        repo.deleteById(id);
        return "redirect:/";
    }




}
