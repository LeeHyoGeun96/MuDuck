package MuDuck.MuDuck.auth.jwt.filter;

import MuDuck.MuDuck.auth.utils.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    private final ExceptionResponse exceptionResponse;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request,response);
        } catch (JwtException ex){
            setErrorResponse(request, response, ex);
        }
    }

    private void setErrorResponse(HttpServletRequest request, HttpServletResponse response, JwtException ex)
            throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = exceptionResponse.createUnauthorizedErrorResponse(request, response, ex);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
