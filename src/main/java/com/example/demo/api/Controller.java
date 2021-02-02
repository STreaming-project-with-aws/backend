package com.example.demo.api;

import com.example.demo.authentication.JwtRequest;
import com.example.demo.authentication.JwtResponse;
import com.example.demo.authentication.JwtTokenUtil;
import com.example.demo.authentication.JwtUserDetailsService;
import com.example.demo.dao.*;
import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("twitchsl/v1")
public class Controller {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private UsersDataAccessService usersDataAccessService;

    @Autowired
    private ViewsDataAccessService viewsDataAccessService;

    @Autowired
    private CommentsDataAccessService commentsDataAccessService;

    @Autowired
    private StreamDataAccessService streamDataAccessService;


/*    @Autowired
    private ViewsDao viewsDao;*/

    @GetMapping("users")
    public List<User> getUsers(){
        return usersDao.findAll();
    }

    @GetMapping("users/{id}")
    public List<User> getUserById(@PathVariable("id") int id) {
        return usersDataAccessService.getUserById(id);
    }

    @RequestMapping(value = "/setview", method = RequestMethod.POST)
    public ResponseEntity<Views> setview(@RequestBody Views singleview) throws Exception {
        viewsDataAccessService.setView(singleview);
//        return singleview.getVideoId();
        return ResponseEntity.ok(new Views(singleview.getId(),singleview.getUsersName(),singleview.getVideoId()));
    }


    @GetMapping("viewcount")
    public Object getCount(){
        return viewsDataAccessService.getCount();
    }

    @RequestMapping(value = "/setcomment", method = RequestMethod.POST)
    public ResponseEntity<Comment> setcomment(@RequestBody Comment singlecomment) throws Exception {
        commentsDataAccessService.setComment(singlecomment);
//        return singleview.getVideoId();
        return ResponseEntity.ok(new Comment(singlecomment.getUsersName(), singlecomment.getContent(), singlecomment.getDate(), singlecomment.getVideoId()));
    }

    @GetMapping("getcomments/{videoid}")
    public List<Comment> getCommentsById(@PathVariable("videoid") String videoid) {
        return commentsDataAccessService.getCommentsByVideoIdUp(videoid);
    }

    @RequestMapping(value = "/setstream", method = RequestMethod.POST)
    public ResponseEntity<Stream> setstream(@RequestBody Stream singlestream) throws Exception {
        streamDataAccessService.setStream(singlestream);
//        return singleview.getVideoId();
        return ResponseEntity.ok(new Stream(singlestream.getUsersName(),singlestream.getStreamName(), singlestream.getType(), singlestream.getGame(), singlestream.getDate(), singlestream.getPhotoUrl(), singlestream.getKey()));
    }

    @GetMapping("streams")
    public List<Stream> getStreams(){
        return streamDataAccessService.getStreams();
    }


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        ResponseEntity.ok(userDetailsService.save(user));

        authenticate(user.getUsername(), user.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));

    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }



}
