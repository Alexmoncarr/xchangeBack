package xchange.x_change.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xchange.x_change.domain.User;
import xchange.x_change.model.AuthenticationResponse;
import xchange.x_change.model.LoginRequest;
import xchange.x_change.model.RegisterRequest;
import xchange.x_change.repos.UserRepository;
import xchange.x_change.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * The `register` function in a Java Spring controller registers a new user if the username is not
   * already taken.
   *
   * @param request - Firstname
   * @return The method `register` in the controller is returning a `ResponseEntity<String>`. If the
   * username provided in the `RegisterRequest` already exists in the database, it returns a response
   * with "Username already exists" and a status of BAD_REQUEST. If the registration is successful
   * and the user is saved in the database, it returns a response with "User registered successfully"
   * and a status of CREATED.
   */
  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
    if (userRepository.existsByUsernameIgnoreCase(request.getUsername())) {
      return new ResponseEntity<>(
        "Username already exists",
        HttpStatus.BAD_REQUEST
      );
    }

    User user = new User();
    user.setFirstname(request.getFirstname());
    user.setLastname(request.getLastname());
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    userRepository.save(user);

    return new ResponseEntity<>(
      "User registered successfully",
      HttpStatus.CREATED
    );
  }

  /**
   * The `login` function in a Java Spring application handles user authentication and generates a
   * JWT token for the authenticated user.
   *
   * @param request The `request` parameter in the `login` method is of type `LoginRequest`, which is
   * likely a custom class representing the login request data sent by the client. It probably
   * contains fields such as `username` and `password` that are used for authentication.
   * @return The `login` method is returning a `ResponseEntity` object with an
   * `AuthenticationResponse` object containing a JWT token.
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
      )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtUtil.generateToken(authentication);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
