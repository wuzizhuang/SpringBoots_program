@RestController
@RequestMapping("/users")
public class UserController{
    @Autowired
    private UserRepository userRepository;
}