using Microsoft.AspNetCore.Mvc;
using UserAuth.Models;
using UserAuth.Services;

namespace UserAuth.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly UserService _userService;
        private readonly JWTService _jwtService;

        public UserController(UserService userService, JWTService jwtService)
        {
            _userService = userService;
            _jwtService = jwtService;
        }

        [HttpPost("register")]
        public async Task<IActionResult> Register(UserDTO userDTO)
        {
            if (await _userService.Register(userDTO))
            {
                return Ok("Registration successful");
            }

            return BadRequest("User already exists");
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login(UserDTO userDTO)
        {
            var user = await _userService.Login(userDTO);
            if (user != null)
            {
                System.Diagnostics.Debug.WriteLine("In Controller" + user.UserName);
                string jwtToken = _jwtService.GenerateToken(user);
                return Ok(new { token = jwtToken });
            }
            return Unauthorized("Invalid username or password");
        }
    }
}
