using System;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using UserAuth.Models;

namespace UserAuth.Services
{
    public class UserService
    {
        private readonly UserDbContext _context;

        public UserService(UserDbContext context)
        {
            _context = context;
        }

        public async Task<bool> Register(UserDTO userDTO)
        {
            var existingUser = await _context.Users.FirstOrDefaultAsync(u => u.UserName == userDTO.UserName);
            if (existingUser != null)
            {
                return false;
            }

            string passwordHash = HashPassword(userDTO.Password);

            var newUser = new User
            {
                Id = Guid.NewGuid().ToString(),
                UserName = userDTO.UserName,
                PasswordHash = passwordHash
            };

            _context.Users.Add(newUser);
            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<User> Login(UserDTO userDTO)
        {
            var user = await _context.Users.FirstOrDefaultAsync(u => u.UserName == userDTO.UserName);
            if (user == null)
            {
                return null;
            }

            if (VerifyPassword(userDTO.Password, user.PasswordHash))
            {
                return user;
            }

            return null;
        }

        private string HashPassword(string password)
        {
            using (var sha256 = SHA256.Create())
            {
                byte[] hashedBytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(password));
                return Convert.ToBase64String(hashedBytes);
            }
        }

        private bool VerifyPassword(string enteredPassword, string hashedPassword)
        {
            string enteredPasswordHash = HashPassword(enteredPassword);
            return enteredPasswordHash == hashedPassword;
        }
    }
}
