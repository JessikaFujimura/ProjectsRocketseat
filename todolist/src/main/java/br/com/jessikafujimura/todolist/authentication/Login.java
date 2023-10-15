package br.com.jessikafujimura.todolist.authentication;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.jessikafujimura.todolist.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Login extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            String authorization = request.getHeader("Authorization");
            String authEncode = authorization.substring("Basic".length()).trim();
            String authDecode = new String(Base64.getDecoder().decode(authEncode));
            String[] credentials = authDecode.split(":");

            var user = this.userRepository.findByUserName(credentials[0]);
            if(user.isEmpty() || Objects.isNull(user)){
                response.sendError(401);
            } else {
                var passwordVerified = BCrypt.verifyer().verify(credentials[1].toCharArray(), user.get().getPassword());
                if(passwordVerified.verified){
                    request.setAttribute("idUser", user.get().getUserId() );
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);

                }
                
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().equals("/users/user") && request.getMethod().equals(HttpMethod.POST.name());
    }

}
