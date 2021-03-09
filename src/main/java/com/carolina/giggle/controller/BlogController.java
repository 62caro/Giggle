package com.carolina.giggle.controller;

import com.carolina.giggle.entity.Post;
import com.carolina.giggle.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model model, @PageableDefault(sort = {"id"}) Pageable pageable){
        Iterable<Post> posts = postRepository.findAllByUser(getCurrentUsername());
        model.addAttribute("posts", posts);
        return "user/blog";
    }

    @GetMapping("/blog/add")
    public String blogAdd(){
        return "user/blog_add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam("title") String title,
                                    @RequestParam("anons") String anons,
                                    @RequestParam("full_text") String full_text, Model model){
        Post post = new Post(title, anons, full_text, getCurrentUsername());
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);

        return "user/blog_details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("posts", res);
        return "user/blog_edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@RequestParam("title") String title,
                                 @RequestParam("anons") String anons,
                                 @RequestParam("full_text") String full_text,
                                 @PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog/" + id;
    }

    @GetMapping("/blog/{id}/delete")
    public String blogDetailsDelete(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
