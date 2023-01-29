package com.hasan.springbootapp.controllers;

import com.hasan.springbootapp.entities.Post;
import com.hasan.springbootapp.requests.PostCreateRequest;
import com.hasan.springbootapp.requests.PostUpdateRequest;
import com.hasan.springbootapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

//    RequestParam -> Bize gelen requestin(./posts?userId={userId}) içerisindeki parametreleri pars et ve sağında bulundan değişkenin içerisine at demektir.metod parameresindeki userId yi api urlde mapliyor direk bunu.
//    Optional paramaetre gelebilirde gelmeyebilirde. geldiği durumda (./posts?userId={userId}) userId ye göre postları getircem.gelmediği durumda da (/posts)
//    urli çağırmış oluyor, yani tüm postları getirmiş olacak.
    @GetMapping
    public List<Post> getALlPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

//    Biri(@RequestParam) requestin içerisindeki parametreleri parçalayıp alıyor.Diğeri (@PathVariable) direk pathin kendisinde yani / sonraki pathdeki değişkenin değerini
//    alıp postId değişkeninine atıyor.
//    /posts/{postId}
    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostbyId(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }
}
