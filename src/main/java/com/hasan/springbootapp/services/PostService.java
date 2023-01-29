package com.hasan.springbootapp.services;

import com.hasan.springbootapp.entities.Post;
import com.hasan.springbootapp.entities.User;
import com.hasan.springbootapp.repos.PostRepository;
import com.hasan.springbootapp.requests.PostCreateRequest;
import com.hasan.springbootapp.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository,UserService userService) {

        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent())
//            findBy bir kalıp objenin(entitiy) içerisindeki herhangi bir alanla findByi bu şekilde birleştirebiliyoruz.
//            bu tarz custom metodları yazıp direk çağırabiliyoruz gerisini jpa hallediyoruz biz kodlamasını yapmıyoruz.
            return postRepository.findByUserId(userId.get());
        return  postRepository.findAll();
    }

    public Post getOnePostbyId(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUserById(newPostRequest.getUserId());
        if (user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
