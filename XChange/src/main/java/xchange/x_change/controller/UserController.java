package xchange.x_change.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xchange.x_change.domain.Role;
import xchange.x_change.model.UserDTO;
import xchange.x_change.repos.RoleRepository;
import xchange.x_change.service.UserService;
import xchange.x_change.util.CustomCollectors;
import xchange.x_change.util.ReferencedException;
import xchange.x_change.util.ReferencedWarning;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) throws Exception {
        Set<UserRole> userRoles = new HashSet<>();

        Role role = new Role();
        role.setRoleType("ROLCUSTOMER");
    
        UserRole userRole = new UserRol();
        userRole.setUser(user);
        userRole.setRole(role);
    
        userRoles.add(userRol);
        return userService.saveUser(user, userRoles);
    }
    
   

    // private final UserService userService;
    // private final RoleRepository roleRepository;

    // public UserController(final UserService userService, final RoleRepository roleRepository) {
    //     this.userService = userService;
    //     this.roleRepository = roleRepository;
    // }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createUser(@RequestBody @Valid final UserDTO userDTO) {
        final Integer createdId = userService.create(userDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateUser(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final UserDTO userDTO) {
        userService.update(id, userDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") final Integer id) {
        final ReferencedWarning referencedWarning = userService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/userRoleRolesValues")
    public ResponseEntity<Map<Integer, String>> getUserRoleRolesValues() {
        return ResponseEntity.ok(roleRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Role::getId, Role::getRoleType)));
    }

}
