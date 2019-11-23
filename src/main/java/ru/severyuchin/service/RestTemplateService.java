package ru.severyuchin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.severyuchin.model.Role;
import ru.severyuchin.model.User;

import java.util.List;
import java.util.Set;

@Service
public class RestTemplateService implements UserDetailsService {
    private final String url = "http://localhost:8080/api/";

    HttpHeaders headers;

    private RestTemplate template;

    public RestTemplateService() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        HttpEntity<User> request = new HttpEntity<>(headers);
        ResponseEntity<User> response = template.exchange(
                url + "getUserByName/" + s,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<User>() {
                });
        return response.getBody();
    }

    public User getUserById(long id){
        HttpEntity<User> request = new HttpEntity<>(headers);
        ResponseEntity<User> response = template.exchange(
                url + "/openModalById/" + id,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<User>() {
                });
        return response.getBody();
    }

    public List<User> getUsers(){
        HttpEntity<List<User>> request = new HttpEntity<>(headers);
        ResponseEntity<List<User>> response = template.exchange(
                url+"getUsers",
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<List<User>>() {
                });
        return response.getBody();
    }

    public Set<Role> getRoleByName(String role){
        HttpEntity<Set<Role>> request = new HttpEntity<>(headers);
        ResponseEntity<Set<Role>> response = template.exchange(
                url+"getRoleByName/" + role.toUpperCase(),
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Set<Role>>() {
                });
        return response.getBody();
    }

    public void save(User user){
        HttpEntity<User> request = new HttpEntity<>(user,headers);
        template.exchange(
                url+"addUser",
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<Set<Role>>() {
                });
    }



    @Autowired
    public void restTemplate(RestTemplateBuilder builder) {
        template = builder
                .basicAuthentication("admin", "admin")
                .build();
    }

}
